package org.simply

import grails.compiler.GrailsCompileStatic
import org.simply.cms.Site
import org.simply.cms.SiteContextHolder

@GrailsCompileStatic
class CmsInterceptor {
	SiteContextHolder siteContextHolder

	CmsInterceptor() {
		match(controller:"content")
	}

	boolean before() {
		String host = request.serverName
		log.debug "Request received host: $host, uri: $request.requestURI, forward-uri: $request.forwardURI"

		if(host) { host = host.trim().toLowerCase()}
		Site site = Site.findByHostname(host) ?: Site.DEFAUTL
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
