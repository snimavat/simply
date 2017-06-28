package org.simply.cms.admin

import org.apache.commons.lang.StringUtils
import org.simply.cms.PagesService
import org.simply.cms.Site
import org.simply.cms.SiteContextHolder
import org.simply.cms.pages.RouteResult

class ContentController {
	PagesService pagesService
	SiteContextHolder siteContextHolder

	def serve() {
		Site site = siteContextHolder.site

		if(site) {
			params
			List components = request.forwardURI.split("/").findAll( { !StringUtils.isEmpty(it)}) as List
			RouteResult routeResult = site.rootPage.route(request, params, components)

			if(!routeResult) {
				log.warn("Page not found $request.forwardURI")
				render status: 404
			} else {
				String view = pagesService.findViewForPage(routeResult.view, routeResult.page, site)
				if(view) {
					log.trace("Using view $view for page ${routeResult.page.class.simpleName} and site $site.hostname")
					render view: view, model: routeResult.model
				} else {
					log.error("No view found for page class ${routeResult.page.class.simpleName}, site ${site.name}")
					render status: 404, text: "not found"
				}
			}
		} else {
			log.warn("Invalid site")
			render status: 404
		}
	}
}
