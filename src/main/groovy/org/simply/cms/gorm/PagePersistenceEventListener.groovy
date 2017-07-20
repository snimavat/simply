package org.simply.cms.gorm

import groovy.transform.CompileStatic
import groovy.util.logging.Slf4j
import org.grails.datastore.mapping.core.Datastore
import org.grails.datastore.mapping.engine.event.*
import org.simply.cms.cache.VarnishCacheInvalidator
import org.simply.cms.pages.Page
import org.springframework.context.ApplicationEvent

@CompileStatic
@Slf4j
class PagePersistenceEventListener extends AbstractPersistenceEventListener {
	VarnishCacheInvalidator varnishCachInvalidator

	PagePersistenceEventListener(final Datastore datastore) {
		super(datastore)
	}

	@Override
	protected void onPersistenceEvent(AbstractPersistenceEvent event) {
		if(event.entity == null || event.entityObject == null) return
		if(!event.entityObject instanceof Page) return

		if(event.eventType == EventType.PreInsert) {
			onPageCreate((Page)event.entityObject)
		}
		if(event.eventType == EventType.PreUpdate) {
			//onPageUpdate((Page)event.entityObject)
		}
	}

	void onPageCreate(Page page) {

	}

	@Override
	boolean supportsEventType(Class<? extends ApplicationEvent> eventType) {
		return eventType == PreUpdateEvent || eventType == PreInsertEvent
	}

}
