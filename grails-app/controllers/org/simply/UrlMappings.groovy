package org.simply

class UrlMappings {
    static final String ADMIN_PREFIX = "admin"
    static final String CONTENT_PREFIX = ""

    static final List<String> FORBIDDEN = [
            'plugins',
            'WEB-INF',
            'assets',
            'console',
            'is-tomcat-running',
            'logoff',
            'logout',
            'login',
            ADMIN_PREFIX
    ]


    static mappings = {

        "/admin"(redirect: "/admin/pages")
        "/admin/block/$action"(controller: "block", namespace:"admin")

        "/admin/pages/$id?/$action?(.$format)?" {
            controller = "page"
            namespace = "admin"
        }

        String prefix = CONTENT_PREFIX //TODO grab from config
        String contentURI = (prefix ? '/' : '') + prefix

        name "page-serve": "${contentURI}/$uri**"(controller: "content") {
            action = ['GET': 'serve', 'POST': 'update']
            constraints {
                uri(validator: { path ->
                    return isCmsPage(path)
                })
            }
        }

        "/$controller/$action?/$id?(.$format)?"{
            constraints {
                // apply constraints here
            }
        }

    }

    static boolean isCmsPage(String path) {
        return !FORBIDDEN.any({p -> path?.startsWith(p)})
    }
}
