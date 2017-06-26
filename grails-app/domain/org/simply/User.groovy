package org.simply

import grails.plugin.springsecurity.SpringSecurityService
import grails.util.Holders
import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString
import grails.compiler.GrailsCompileStatic

@GrailsCompileStatic
@EqualsAndHashCode(includes='username')
@ToString(includes='username', includeNames=true, includePackage=false)
class User implements Serializable {

	private static final long serialVersionUID = 1

	SpringSecurityService _springSecurityService

	String username
	String password
	boolean enabled = true
	boolean accountExpired
	boolean accountLocked
	boolean passwordExpired

	User(String uname, String pwd) {
		this.username = uname
		this.password = pwd
	}

	Set<Role> getAuthorities() {
		(UserRole.findAllByUser(this) as List<UserRole>)*.role as Set<Role>
	}

	def beforeInsert() {
		encodePassword()
	}

	def beforeUpdate() {
		if (isDirty('password')) {
			encodePassword()
		}
	}

	protected void encodePassword() {
		if(!_springSecurityService) _springSecurityService = (SpringSecurityService)Holders.applicationContext.getBean('springSecurityService')
		password = _springSecurityService?.passwordEncoder ? _springSecurityService.encodePassword(password) : password
	}

	static transients = ['_springSecurityService']

	static constraints = {
		password nullable: false, blank: false, password: true
		username nullable: false, blank: false, unique: true
	}

	static mapping = {
		password column: '`password`'
	}
}
