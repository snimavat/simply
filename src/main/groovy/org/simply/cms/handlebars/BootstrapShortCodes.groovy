package org.simply.cms.handlebars

import com.github.jknack.handlebars.Options
import groovy.transform.CompileStatic
import org.springframework.stereotype.Component

@Component
@CompileStatic
class BootstrapShortCodes implements HandlerbarsHelperSource {

	String bsAlert(Options options) {
		String style = options.hash('style', "info")
		return """<div class="alert alert-$style">${options.fn(this)}</div> """
	}

	String bsWell(Options options) {
		String style = options.hash('style', "well-sm")
		return """<div class="well $style">${options.fn(this)}</div>"""
	}

	//contextual styles
	String bsText(Options options) {
		String style = options.hash('style', "primary")
		return """<span class="text-$style">${options.fn(this)}</span>"""
	}

	//contextual backgrounds
	String bsBg(Options options) {
		String style = options.hash('style', "primary")
		return """<p class="bg-$style">${options.fn(this)}</p>"""
	}

}
