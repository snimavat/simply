package org.simply

import org.simply.cms.block.FlexiBlockBinder
import grails.boot.GrailsApp
import grails.boot.config.GrailsAutoConfiguration

class Application extends GrailsAutoConfiguration {
    static void main(String[] args) {
        GrailsApp.run(Application, args)
    }

    @Override
    Closure doWithSpring() {
        { ->
            flexiBlockBinder(FlexiBlockBinder, ref("grailsApplication"))
        }
    }
}