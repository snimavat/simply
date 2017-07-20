package org.simply

import com.github.jknack.handlebars.Handlebars
import grails.boot.GrailsApp
import grails.boot.config.GrailsAutoConfiguration
import org.simply.cms.handlebars.HandlerbarsHelperSource
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration

@ComponentScan
@Configuration
class Application extends GrailsAutoConfiguration {
    static void main(String[] args) {
        GrailsApp.run(Application, args)
    }

    @Bean(name = "handlebars")
    Handlebars configure(List<HandlerbarsHelperSource> sources) {
        Handlebars handlebars = new Handlebars()

        sources.each {HandlerbarsHelperSource source ->
            handlebars.registerHelpers(source)
        }

        return handlebars
    }

}