package org.simply.cms.admin

import grails.plugins.crudify.core.Pager
import org.simply.cms.PagesService
import org.simply.cms.pages.Page
import grails.core.GrailsApplication
import grails.core.GrailsClass
import grails.gorm.PagedResultList
import grails.util.GrailsClassUtils
import org.grails.core.artefact.DomainClassArtefactHandler

class PageController {
	static namespace = "admin"
	static defaultAction = "list"

	PagesService pagesService
	GrailsApplication grailsApplication

	def create() {
		Page instance = createInstance()
		String createView = pagesService.findViewForPage("create", instance, null)
		if (request.method == "GET") {
			respond instance, view: createView, model: model(instance)
		}
		else if (request.method == "POST") {
			instance.validate()
			if (instance.hasErrors()) {
				respond instance, view: createView, model: model(instance)
				return
			} else {
				Page.withTransaction {
					saveInstance(instance)
				}
				redirect action: "list", params: [id: instance.parentId]
			}

		}

	}

	def edit(Long id) {
		Page instance = Page.get(id)
		String editView = pagesService.findViewForPage("edit", instance, null)

		if (request.method == "GET") {
			respond instance, view: editView, model: model(instance)
			return
		}

		if (request.method == "POST") {
			bindData instance, params
			instance.validate()
			if (instance.hasErrors()) {
				respond instance, view: editView, model: model(instance)
				return
			} else {
				Page.withTransaction {
					updateInstance(instance)
				}

				redirect action: "list", params: [id: instance.parentId]
			}
		}
	}

	private Map model(Page instance) {
		Map model = new HashMap();
		model.put("instance", instance);
		model.putAll(extranModel(instance));
		return model;
	}

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
			Pager pager = new Pager(params)
			PagedResultList<Page> list = page.childs.list(max: pager.max, offset: pager.offset) {
				order("dateCreated", "desc")
			}
			Map resp = [list: list, total: list.totalCount, page:page]
			if(request.xhr) {
				render(template: "pageList", model: resp)
				return
			}
			else respond resp
		}
	}

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


	void saveInstance(Page p) {
		pagesService.save(p)
	}


	void updateInstance(Page page) {
		pagesService.save(page)
	}

}
