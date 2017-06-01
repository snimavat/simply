package com.lapsi.cms

import com.lapsi.cms.pages.Page
import grails.test.mixin.integration.Integration
import grails.transaction.Rollback
import spock.lang.Specification

@Integration
@Rollback
class PageSpec extends Specification  {

	def "test route"() {
		setup:
		Page one = new Page(slug: "one", title: "one", published: true).save(failOnError:true)
		Page two = new Page(parent: one, title: "two", slug: "two", published: true).save(failOnError:true)
		Page three = new Page(parent:two, title:"three", slug: "three", published: true).save(failOnError:true)

		expect:
		one == one.route([])
		two == one.route(["two"])
		three == one.route(["two", "three"])
		null == one.route(["two", "three", "four"])
		three == three.route([])
		null == three.route(["two"])
	}
}
