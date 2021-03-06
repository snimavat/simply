
grails.config.locations = ["classpath:prod-config.groovy",]

// Added by the Spring Security Core plugin:
grails.plugin.springsecurity.userLookup.userDomainClassName = 'org.simply.User'
grails.plugin.springsecurity.userLookup.authorityJoinClassName = 'org.simply.UserRole'
grails.plugin.springsecurity.authority.className = 'org.simply.Role'

grails.plugin.springsecurity.securityConfigType = "InterceptUrlMap"

grails.plugin.springsecurity.interceptUrlMap = [
		[pattern: '/',               access: ['permitAll']],
		[pattern: '/error',          access: ['permitAll']],
		[pattern: '/index',          access: ['permitAll']],
		[pattern: '/index.gsp',      access: ['permitAll']],
		[pattern: '/shutdown',       access: ['permitAll']],
		[pattern: '/assets/**',      access: ['permitAll']],
		[pattern: '/**/js/**',       access: ['permitAll']],
		[pattern: '/**/css/**',      access: ['permitAll']],
		[pattern: '/**/images/**',   access: ['permitAll']],
		[pattern: '/**/favicon.ico', access: ['permitAll']],
		[pattern: '/login',          access: ['permitAll']],
		[pattern: '/login/**',       access: ['permitAll']],
		[pattern: '/logout',         access: ['permitAll']],
		[pattern: '/logout/**',      access: ['permitAll']],
		[pattern: '/admin/**',       access: ['ROLE_ADMIN']],
		[pattern: '/dbconsole/**',   access: ['ROLE_ADMIN']],
		[pattern: '/console/**',   	 access: ['ROLE_ADMIN']],
		[pattern: '/**',       		 access: ['permitAll']],

]

grails.plugin.springsecurity.filterChain.chainMap = [
	[pattern: '/assets/**',      filters: 'none'],
	[pattern: '/**/js/**',       filters: 'none'],
	[pattern: '/**/css/**',      filters: 'none'],
	[pattern: '/**/images/**',   filters: 'none'],
	[pattern: '/**/favicon.ico', filters: 'none'],
	[pattern: '/**',             filters: 'JOINED_FILTERS']
]

grails.plugin.fields.disableLookupCache = false
grails.plugins.disqus.shortname = "test-me-1"
grails.plugins.disqus.powered = false

simply.cms.cache.varnish.host = "http://localhost"

grails {
	plugin {
		databasemigration {
			updateOnStart = true
			changelogFileName = 'changelog.groovy'
			updateOnStartFileName = ["changelog.groovy"]
		}
	}
}

grails {
	mongodb {
		databaseName = "simply"
	}
}

environments {
	production {
		grails.plugin.fields.disableLookupCache = false
		grails.plugins.disqus.shortname = "nimavat-me"
		grails.mongodb.host = "mongo"
		simply.cms.cache.varnish.host = "http://varnish"
		grails {
			assets {
				bundle = false
				storagePath = "/assets"
			}
		}
	}

	test {
		grails.plugin.databasemigration.updateOnStart = false
	}
}

