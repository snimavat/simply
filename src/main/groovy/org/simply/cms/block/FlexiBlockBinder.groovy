package org.simply.cms.block

import grails.core.GrailsApplication
import grails.databinding.DataBindingSource
import grails.databinding.SimpleMapDataBindingSource
import grails.databinding.events.DataBindingListener
import grails.web.databinding.GrailsWebDataBinder
import org.grails.databinding.IndexedPropertyReferenceDescriptor

/**
 * Created by sudhir on 04/06/17.
 */
class FlexiBlockBinder extends GrailsWebDataBinder {

	FlexiBlockBinder(GrailsApplication grailsApplication) {
		super(grailsApplication)
	}

	@Override
	protected processIndexedProperty(obj, MetaProperty metaProperty, IndexedPropertyReferenceDescriptor indexedPropertyReferenceDescriptor,
									 val, DataBindingSource source, DataBindingListener listener, errors) {

		def propName = indexedPropertyReferenceDescriptor.propertyName
		def propertyType = metaProperty.type
		if (propertyType.isArray()) {
			def index = Integer.parseInt(indexedPropertyReferenceDescriptor.index)
			def array = initializeArray(obj, propName, propertyType.componentType, index)
			if (array != null) {
				addElementToArrayAt array, index, val
			}
		} else if (Collection.isAssignableFrom(propertyType)) {
			int index = Integer.parseInt(indexedPropertyReferenceDescriptor.index)
			Collection collectionInstance = initializeCollection obj, propName, propertyType
			def indexedInstance = null
			if(!(Set.isAssignableFrom(propertyType))) {
				indexedInstance = collectionInstance[index]
			}
			if (indexedInstance == null) {
				Class genericType = getReferencedTypeForCollection(propName, obj, val)
				if (genericType) {
					if (genericType.isAssignableFrom(val?.getClass())) {
						addElementToCollectionAt obj, propName, collectionInstance, index, val
					} else if (isBasicType(genericType)) {
						addElementToCollectionAt obj, propName, collectionInstance, index, convert(genericType, val)
					} else if (val instanceof Map){
						indexedInstance = genericType.newInstance()
						bind indexedInstance, new SimpleMapDataBindingSource(val), listener
						addElementToCollectionAt obj, propName, collectionInstance, index, indexedInstance
					} else if (val instanceof DataBindingSource) {
						indexedInstance = genericType.newInstance()
						bind indexedInstance, val, listener
						addElementToCollectionAt obj, propName, collectionInstance, index, indexedInstance
					} else if(genericType.isEnum() && val instanceof CharSequence) {
						def enumValue = convertStringToEnum(genericType, val.toString())
						addElementToCollectionAt obj, propName, collectionInstance, index, enumValue
					}
				} else {
					addElementToCollectionAt obj, propName, collectionInstance, index, val
				}
			} else {
				if (val instanceof Map) {
					bind indexedInstance, new SimpleMapDataBindingSource(val), listener
				} else if (val instanceof DataBindingSource) {
					bind indexedInstance, val, listener
				} else if (val == null && indexedInstance != null) {
					addElementToCollectionAt obj, propName, collectionInstance, index, null
				}
			}
		} else if (Map.isAssignableFrom(propertyType)) {
			Map mapInstance = initializeMap obj, propName
			if (mapInstance.size() < autoGrowCollectionLimit || mapInstance.containsKey(indexedPropertyReferenceDescriptor.index)) {
				def referencedType = getReferencedTypeForCollection propName, obj
				if (referencedType != null) {
					if(val instanceof Map) {
						mapInstance[indexedPropertyReferenceDescriptor.index] = referencedType.newInstance(val)
					} else {
						mapInstance[indexedPropertyReferenceDescriptor.index] = convert(referencedType, val)
					}
				} else {
					mapInstance[indexedPropertyReferenceDescriptor.index] = val
				}
			}
		}
	}

	protected Class<?> getReferencedTypeForCollection(String propertyName, Object obj, def value) {
		if(value instanceof Map && value.containsKey('type')) {
			String type = value['type']
			FlexiBlock b = (FlexiBlock)obj
			return FlexiBlock.getBlockType(value['type'])
		} else {
			return super.getReferencedTypeForCollection(propertyName, obj)
		}
	}

}
