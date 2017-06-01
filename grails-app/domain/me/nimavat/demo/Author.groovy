package me.nimavat.demo

import grails.databinding.BindingFormat
import grails.plugins.crudify.core.LookupType

/**
 * Created by sudhir on 12/03/17.
 */
class Author implements LookupType {
	String name

	@BindingFormat("dd/MM/yyyy")
	Date dob
}
