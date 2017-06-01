package com.lapsi.cms.pages

import grails.gorm.DetachedCriteria
import grails.plugins.crudify.core.LookupType
import org.apache.commons.lang.StringUtils

class Page implements LookupType {
	static scaffold = [include:['slug', 'title', 'published'], exclude:['urlPath']]

	Page parent

	String treePath = "01"
	Integer childCount = 0
	Integer level = 0

	String slug
	String urlPath
	String title
	String keywords
	String metaDescription

	Boolean published = false

	Date dateCreated
	Date lastUpdated

	static transients = ['templateName', 'context', 'name', 'childs']

	static constraints = {
		parent nullable: true
		slug nullable: false, blank: false, index: "idx_slug"
		title nullable: false, blank: false
		urlPath nullable: true, blank: true
		keywords nullable: true
		metaDescription nullable: true, widget:"textarea"

		treePath nullable: true, blank: true
		childCount nullable: true
		level nullable: true
		dateCreated nullable: true
		lastUpdated nullable: true
	}

	static mapping = {
		tablePerHierarchy false
	}

	static namedQueries = {
		live {
			eq "published", true
		}
	}

	def beforeValidate() {
		if(id == null || isDirty("slug")) updateUrlPath()
	}

	def beforeInsert() {
		if(parent) {
			parent.childCount = parent.childCount + 1
			treePath = parent.treePath + "." + StringUtils.leftPad(String.valueOf(parent.childCount), 2, '0')
			level = StringUtils.countMatches(treePath, ".")
			parent.save()
		}
	}


	String getTemplateName() {
		return StringUtils.uncapitalize(getClass().simpleName)
	}

	Map getContext() {
		Map context = ["page": this]
		return context
	}

	String getName() { return title }

	String updateUrlPath() {
		if(parent) urlPath = parent.urlPath + this.slug + "/"
		else urlPath = "/" //Root page.
		return urlPath
	}

	Page route(List<String> pathComponents) {
		if(pathComponents && pathComponents.size() > 0) {
			String childSlug = pathComponents[0]
			List<String> remainingComponents = pathComponents.drop(1)
			Page subpage = childs.findBySlug(childSlug.trim())
			if(!subpage) return null
			else return subpage.route(remainingComponents)
		} else {
			if(this.published) return this
			else return null
		}
	}


	DetachedCriteria<Page> getChilds() {
		def criteria = new DetachedCriteria(getClass()).build {
			eq 'parent', this
		}

		return criteria
	}

	static Page getROOT() {
		return Page.findByTreePath("01")
	}

}
