package org.simply.cms.admin

import org.apache.commons.lang.StringUtils
import org.simply.cms.PagesService
import org.simply.cms.Site
import org.simply.cms.SiteContextHolder
import org.simply.cms.pages.Page
import org.springframework.beans.factory.annotation.Autowired

class ContentController {
	@Autowired
	PagesService pagesService

	@Autowired
	SiteContextHolder siteContextHolder

	def serve() {
		Site site = siteContextHolder.site

		if(site) {
			params
			List components = request.forwardURI.split("/").findAll( { !StringUtils.isEmpty(it)})
			Page page = site.rootPage.route(components)
			if(!page) {
				render status: 404, text: "not found"
			} else {
				String view = pagesService.findDisplayViewForPage(page, site)
				if(view) {
					log.trace("Using view $view for page ${page.class.simpleName} and site $site.hostname")
					render view: view, model: page.getContext(request, params)
				} else {
					log.error("No view found for page class ${page.class.simpleName}, site ${site.name}")
					render status: 404, text: "not found"
				}
			}
		} else {
			log.warn("Invalid site")
			render status: 404
		}
	}
}
