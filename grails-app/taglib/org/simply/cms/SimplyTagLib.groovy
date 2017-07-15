package org.simply.cms

import org.simply.cms.pages.Page

class SimplyTagLib {
    static namespace = "cms"

    final static String DISPLAY_TEMPLATE = "display"

    static defaultEncodeAs = [taglib:'html']
    static encodeAsForTags = [renderBlock: 'none', pageLink:"none", 'breadcrumb':'none']

    BlockService blockService
    PagesService pagesService
    SiteContextHolder siteContextHolder


    Closure renderBlock = {Map attrs ->
        def block = attrs.block
        String template = blockService.getBlockTemplate(block.class, DISPLAY_TEMPLATE)
        out << render(template: template, model:[block:block])
    }

    Closure pageLink = {attrs, body ->
        Page page = (Page) attrs.page
        if(page && siteContextHolder.site) {
            String pageUri = pagesService.generateLink(page, siteContextHolder.site)
            out << g.link(controller:"content", params:[uri:pageUri], mapping: "page-serve", body)
        }
    }

    Closure breadcrumb = { attrs ->
        Map model = [controller:attrs.controller ?: 'page', action:attrs.action ?: 'list']
        Page page = attrs.page
        List<Page> pages = []
        while (page != Page.ROOT) {
            Page parent = page.parent
            pages << page
            page = parent
        }

        pages << Page.ROOT
        model.pages = pages.reverse()
        model.page = attrs.page
        out << render(template: "/common/templates/breadcrumb", model:model)
    }

}
