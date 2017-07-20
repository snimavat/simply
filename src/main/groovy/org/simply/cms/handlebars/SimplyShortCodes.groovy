package org.simply.cms.handlebars

import com.github.jknack.handlebars.Options
import groovy.transform.CompileStatic
import org.springframework.stereotype.Component

@Component
@CompileStatic
class SimplyShortCodes implements HandlerbarsHelperSource {

	String bold(Options options) {
		return "<strong>" + options.fn(this) + "</strong>"
	}
}
