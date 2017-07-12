package org.simply.cms.cache

import grails.plugins.rest.client.RestBuilder
import grails.web.mapping.LinkGenerator
import groovy.transform.CompileStatic
import groovy.util.logging.Slf4j
import org.springframework.beans.factory.annotation.Value


@Slf4j
@CompileStatic
class VarnishCacheInvalidator {
	LinkGenerator grailsLinkGenerator

	@Value('${simply.cms.cache.varnish.host}')
	String varnishHost

	void ban(Map params) {
		try {
			String link = grailsLinkGenerator.link(params)
			RestBuilder rest = new RestBuilder(registerConverters: false)
			String url = "${varnishHost}${link}"
			rest.get(url) {
				header 'x-ban', "true"
			}
			log.info("Ban added for url $url")
		}catch (Exception e) {
			log.warn("Failed to add ban", e)
		}
	}

	void purge(Map params) {
		String link = grailsLinkGenerator.link(params)
		RestBuilder rest = new RestBuilder(registerConverters:false)
		rest.get("${varnishHost}/${link}") {
			header 'x-perge', "true"
		}
	}

}
