package org.simply.cms.pages

import groovy.transform.CompileStatic
import groovy.transform.SelfType
import groovy.transform.TypeCheckingMode
import org.grails.io.support.AntPathMatcher
import org.simply.cms.utils.BeanUtils

import javax.servlet.http.HttpServletRequest
import java.lang.reflect.Method

@SelfType(Page)
@CompileStatic
trait RoutablePageTrait {

	@CompileStatic(TypeCheckingMode.SKIP)
	RouteResult route(HttpServletRequest request, Map params, List<String> pathComponents) {
		if(!this.published) return null
		AntPathMatcher matcher = new AntPathMatcher()

		String path = "/" + pathComponents.join("/")
		Method matchedMethod = findRouteMethod(path, matcher)

		if(matchedMethod) {
			return routeToMethod(path, matchedMethod, matcher, params)
		} else {
			return super.route(request, params, pathComponents)
		}
	}

	private Method findRouteMethod(String path, AntPathMatcher matcher) {
		List<Method> routableMethods = BeanUtils.findAllMethodsWithAnnotation(getClass(), Route)

		Method matchedMethod = routableMethods.find({
			Route route = it.getAnnotation(Route)
			return matcher.match(route.value(), path)
		})

		return matchedMethod
	}


	private RouteResult routeToMethod(String path, Method method, AntPathMatcher matcher, Map params) {
		Route route = method.getAnnotation(Route)
		assert route != null
		params.putAll(matcher.extractUriTemplateVariables(route.value(), path))
		return (RouteResult)method.invoke(this, params)
	}
}

