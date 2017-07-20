package org.simply.cms.pages

import grails.compiler.GrailsCompileStatic
import grails.gorm.PagedResultList
import grails.plugins.crudify.core.Pager
import groovy.transform.CompileDynamic
import groovy.transform.CompileStatic
import groovy.transform.TypeCheckingMode

import javax.servlet.http.HttpServletRequest

@GrailsCompileStatic
class BlogIndexPage extends Page implements RoutablePageTrait {

	String intro

	static mapping = {
		intro type: "text"
	}

	static constraints = {
		intro nullable: true, blank: false, widget: "textarea"
	}

	@Route("/")
	@CompileDynamic
	RouteResult index(Map params) {
		Map context = super.getContext(null, params)

		Pager pager = new Pager(params)
		PagedResultList<BlogPage> pages = getChilds(-1).list(max: pager.max, offset: pager.offset) {
			eq "published", true
			order("dateCreated", "desc")
		}

		context['childs'] = pages
		context['totalCount'] = pages.totalCount
		context['currentPage'] = params.int('page') ?: 1
		context['totalPageCount'] = pager.getTotalPages(pages.totalCount)
		return new RouteResult(this, templateName, context)
	}

	@Route("/category/{categoryName}")
	@CompileDynamic
	RouteResult category(Map params) {
		Map context = super.getContext(null, params)
		int max = params.int("max") ?: 10
		int offset = params.int("offset") ?: 0

		PagedResultList<BlogPage> pages = getChilds(-1).list(max: max, offset: offset) {
			eq "published", true
			eq 'category', BlogCategory.findByName(params.categoryName).id
		}

		context['childs'] = pages
		context['totalCount'] = pages.totalCount

		return new RouteResult(this, templateName, context)
	}

}
