package org.simply.cms.utils

import groovy.transform.CompileStatic

import java.lang.annotation.Annotation
import java.lang.reflect.Method

@CompileStatic
class BeanUtils {

	static List<Method> findAllMethodsWithAnnotation(Class clazz, Class<? extends Annotation> annotation) {
		return clazz.methods.findAll({ Method m -> m.isAnnotationPresent(annotation)}) as List
	}
}
