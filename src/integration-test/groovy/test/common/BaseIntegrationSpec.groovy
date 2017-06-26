package test.common

import org.simply.User
import grails.build.support.MetaClassRegistryCleaner
import grails.plugin.springsecurity.userdetails.GrailsUser
import grails.util.GrailsMetaClassUtils
import org.codehaus.groovy.runtime.HandleMetaClass
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.authority.AuthorityUtils
import org.springframework.security.core.context.SecurityContextHolder
import spock.lang.Ignore
import spock.lang.Specification

/**
 * @author snimavat
 */
abstract class BaseIntegrationSpec extends Specification {
    JdbcTemplate jdbcTemplate
    private MetaClassRegistryCleaner registryCleaner


    void cleanup() {
        //clear meta class changes after each test, if they are not already cleared.
        if(registryCleaner) {
            clearMetaClassChanges()
        }
    }


    @Ignore
    void authenticate(User user, String... roles) {
        roles = roles.collect({ "ROLE_" + it})
        List authorities = AuthorityUtils.createAuthorityList(roles)

        GrailsUser grailsUser = new GrailsUser(user.username, user.password, user.enabled, true, true, true, authorities, user.id)
        SecurityContextHolder.context.authentication = new UsernamePasswordAuthenticationToken(grailsUser, user.password, authorities)
    }



    @Ignore
    def parseNum(val){
        def matcher = val =~ /\d*\.\d*/
        //log.debug "found a number and its"+ matcher[0]
        return new BigDecimal(matcher[0])
    }

    void mockRender(def controller) {
        MetaClass metaClass = GrailsMetaClassUtils.getMetaClass(controller)
        metaClass.render = { Map args ->
            GrailsMetaClassUtils.getMetaClass(controller).renderArgs = args
        }

        if(controller.metaClass instanceof HandleMetaClass) ((HandleMetaClass)controller.metaClass).replaceDelegate()
    }

    /**
     * Start tracking all mata class changes made after this call, so it can all be undone later.
     */
    void trackMetaClassChanges() {
        registryCleaner = MetaClassRegistryCleaner.createAndRegister()
        GroovySystem.metaClassRegistry.addMetaClassRegistryChangeEventListener(registryCleaner)
    }

    /**
     * Undo all metaclass changes done since last call to trackMetaClassChanges()
     */
    void clearMetaClassChanges() {
        if(registryCleaner) {
            registryCleaner.clean()
            GroovySystem.metaClassRegistry.removeMetaClassRegistryChangeEventListener(registryCleaner)
            registryCleaner = null
        }
    }
 }
