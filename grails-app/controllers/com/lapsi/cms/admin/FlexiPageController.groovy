package com.lapsi.cms.admin

import com.lapsi.cms.pages.Page
import com.lapsi.cms.PagesService
import grails.core.GrailsApplication
import grails.core.GrailsClass
import grails.gorm.PagedResultList
import grails.plugins.crudify.CreateAction
import grails.plugins.crudify.EditAction
import grails.util.GrailsClassUtils
import org.grails.core.artefact.DomainClassArtefactHandler

class FlexiPageController implements CreateAction<Page>, EditAction<Page> {
	Class domainClass = Page
	static namespace = "admin"
	static defaultAction = "list"

	PagesService pagesService
	GrailsApplication grailsApplication

	Map extranModel(Page p) {
		Map model = [props:'']
		Map scaffold = GrailsClassUtils.getStaticPropertyValue(p.class, 'scaffold')
		if(scaffold) model.props = scaffold.include.join(",")
		return model
	}
	def index() {
		redirect action:"list", params:params
	}

	def addSubpage(Long id) {
		Page parent = Page.get(id)
		List<Class> pageTypes = pagesService.getPageTypes()
		return [parent:parent, pageTypes:pageTypes]
	}

	def list(Long id) {
		if(id == null) redirect(action: "list", params: [id:Page.ROOT.id])
		else {
			Page page = Page.get(id)
			PagedResultList<Page> list = page.childs.list(max: 10, offset: 0)
			Map resp = [list: list, total: list.totalCount]
			respond resp
		}
	}

	@Override
	Page createInstance() {
		Long parentId = params.long('id')
		String type = params.type
		GrailsClass pageClass = grailsApplication.getArtefactByLogicalPropertyName(DomainClassArtefactHandler.TYPE, type)


		Page parent
		if(parentId != null) parent = Page.get(parentId)
		else parent = Page.ROOT
		Page page = pageClass.clazz.newInstance()
		page.properties = params
		page.parent = parent
		return page
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
