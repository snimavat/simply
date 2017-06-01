package com.lapsi.cms.admin

import com.lapsi.cms.pages.Page
import com.lapsi.cms.PagesService
import com.lapsi.cms.Site
import org.apache.commons.lang.StringUtils
import org.springframework.beans.factory.annotation.Autowired

/**
 * Created by sudhir on 28/05/17.
 */
class ContentController {
	@Autowired
	PagesService pagesService

	def serve() {
		String host = request.serverName
		println "Request received $host, $request.requestURI, $request.forwardURI"

		if(host) { host = host.trim().toLowerCase()}
		Site site = Site.findByHostname(host)
		if(site) {

			List components = request.forwardURI.split("/").findAll( { !StringUtils.isEmpty(it)})
			Page page = site.rootPage.route(components)
			if(!page) {
				render status: 404, text: "not found"
			} else {
				String view = pagesService.findViewForPage(page, site)
				if(view) {
					log.trace("Using view $view for page ${page.class.simpleName} and site $site.hostname")
					render view: view, model: page.context
				} else {
					log.error("No view found for page class ${page.class.simpleName}, site ${site.name}")
					render status: 404, text: "not found"
				}
			}
		}
	}
}
