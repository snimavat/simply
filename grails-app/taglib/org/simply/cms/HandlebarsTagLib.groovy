package org.simply.cms

import com.github.jknack.handlebars.Handlebars
import com.github.jknack.handlebars.Template
import groovy.transform.CompileStatic

@CompileStatic
class HandlebarsTagLib {
	static namespace = "hb"

	static encodeAsForTags = [render:"none"]
	static returnObjectForTags = ['render']

	Handlebars handlebars

	def render = {Map attrs ->
		String template = attrs.template
		def context = attrs.context
		Template t = handlebars.compileInline(template)
		return t.apply(context)
	}
}
