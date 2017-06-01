package com.lapsi.cms

import com.github.slugify.Slugify
import com.lapsi.cms.pages.Page
import grails.compiler.GrailsCompileStatic
import grails.core.GrailsApplication
import grails.transaction.NotTransactional
import grails.transaction.Transactional
import grails.web.mapping.LinkGenerator
import groovy.transform.CompileDynamic
import groovy.transform.CompileStatic
import groovy.transform.TypeCheckingMode
import org.apache.commons.lang.Validate
import org.grails.web.gsp.io.GrailsConventionGroovyPageLocator
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.jdbc.core.JdbcTemplate

import static org.grails.io.support.GrailsResourceUtils.appendPiecesForUri

@Transactional
@GrailsCompileStatic
class PagesService {
	private static final String PAGES_DIR = "/pages"
	private static final String SITE_VIEWS_DIR = "/sites"

	@Autowired
	GrailsConventionGroovyPageLocator groovyPageLocator

	@Autowired
	LinkGenerator grailsLinkGenerator

	@Autowired
	JdbcTemplate jdbcTemplate

	@Autowired
	GrailsApplication grailsApplication

	private static Slugify slg = new Slugify()

	@NotTransactional
	List<Class> getPageTypes() {
		return (List<Class>) grailsApplication.config.getProperty('simply.cms.pageClasses', List)
	}

	Page save(Page page) {
		if(page.slug) page.slug = slg.slugify(page.slug)
		boolean isNew = (page.id == null)
		if(isNew) {
			page.updateUrlPath()
		}
		else {
			if(page.isDirty("slug") || page.isDirty("parent")) {
				String oldPath = page.getPersistentValue("urlPath")
				page.updateUrlPath()
				String newPath = page.urlPath

				if(oldPath != newPath) {
					updateChildUrls(oldPath, newPath, page)
				}
			}
		}

		page.save()
		return page
	}

	/*
	private String getNextRootTreePath() {
		int count = Page.countByLevel(0)
		return StringUtils.leftPad(String.valueOf(count + 1), 2, '0')
	}*/

	void updateChildUrls(String old, String newPath, Page page) {
		String query = """
			update page 
			set url_path = concat(?, SUBSTRING(url_path, ?)), 
			version = version + 1 
			where tree_path like ?
			and id <> ?
		"""
		jdbcTemplate.update(query, newPath, old.length() + 1, page.treePath + '%', page.id)
	}

	@CompileDynamic
	String generateLink(Page page, Site site) {
		Page siteRoot = site.rootPage
		Validate.notNull(siteRoot, "No root page for site $site.name")
		if(page.urlPath.startsWith(siteRoot.urlPath)) {
			String relativeUri = page.urlPath[siteRoot.urlPath.length()..-1]
			return grailsLinkGenerator.link([mapping: "page-serve", params:[uri:relativeUri]])
		} else {
			throw new RuntimeException("Page $page.urlPath does not belong to site $site.name")
		}
	}

	@CompileStatic(TypeCheckingMode.SKIP)
	List getUrlParts(Page page) {
		List<List> siteRootPaths = Site.list().collect({[it.id, it.rootPage.urlPath, it.url]})

		List parts = siteRootPaths.findResult {List it ->
			def (siteId, rootPath, siteUrl) = it
			if(page.urlPath.startsWith(rootPath)) { return [siteId, page.urlPath[rootPath.length()..-1], siteUrl]}
		}

		return parts

	}

	protected


	@NotTransactional
	String findViewForPage(Page page, Site site) {
		String view = page.templateName
		List<String> templateResolveOrder = []
		templateResolveOrder << appendPiecesForUri(SITE_VIEWS_DIR, site.hostname, "pages", view)
		templateResolveOrder << appendPiecesForUri(PAGES_DIR, view)

		return findView(templateResolveOrder)
	}

	@NotTransactional
	private String findView(List<String> candidates) {
		String path = candidates.findResult { String path ->
			def source = groovyPageLocator.findViewByPath(path)
			if(source) return path
			else return null
		}

		return path
	}
}
