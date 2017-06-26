package org.simply

import org.simply.cms.Site
import org.simply.cms.SiteContextHolder


class CmsInterceptor {
	SiteContextHolder siteContextHolder

	CmsInterceptor() {
		match(controller:"content")
	}

	boolean before() {
		String host = request.serverName
		log.trace "Request received $host, $request.requestURI, $request.forwardURI"

		if(host) { host = host.trim().toLowerCase()}
		Site site = Site.findByHostname(host)
		if(site) {
			siteContextHolder.site = site
			return true
		} else {
			log.warn("Invalid site: $host")
			render status: 404
			return false
		}

	}


}
