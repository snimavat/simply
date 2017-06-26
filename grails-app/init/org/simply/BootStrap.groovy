package org.simply

import org.simply.cms.pages.FlexiPage
import org.simply.cms.pages.HomePage
import org.simply.cms.pages.Page
import org.simply.cms.Site

class BootStrap {

    def init = { servletContext ->
        createAdminUser()
        createRootPage()
    }

    void createRootPage() {
        Site site = Site.findByHostname("localhost")
        if(!site) {
            Site.withTransaction {
                Page root = new Page(title: "ROOT", slug: "/", published: false).save(failOnError:true)
                Page homePage = new HomePage(title: "nimavat.me", slug: "home", parent: root, published: true).save(failOnError: true)
                site = new Site(name: "localhost", hostname: "localhost", rootPage: homePage).save(failOnError: true)

                Page flexiPage = new FlexiPage(parent: homePage, slug: "test-page", title: "Test Page").save(failOnError:true)
            }
        }
    }

    void createAdminUser() {
        Role.withTransaction {
            Role adminRole = new Role('ROLE_ADMIN').save(failOnError:true)
            Role userRole = new Role('ROLE_USER').save(failOnError:true)

            def testUser = new User('me', 'password').save(failOnError:true)

            UserRole.create testUser, adminRole, true

            assert User.count() == 1
            assert Role.count() == 2
            assert UserRole.count() == 1
        }
    }
}
