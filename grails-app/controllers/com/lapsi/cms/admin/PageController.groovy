package com.lapsi.cms.admin

import com.lapsi.cms.pages.Page
import com.lapsi.cms.PagesService
import grails.plugins.crudify.CrudController

class PageController implements CrudController<Page> {
	Class domainClass = Page
	static namespace = "admin"
	static defaultAction = "list"

	PagesService pagesService

	def index() {
		redirect action:"list", params:params
	}

	@Override
	void saveInstance(Page p) {
		pagesService.save(p)
	}

	@Override
	void updateInstance(Page page) {
		pagesService.save(page)
	}

}
