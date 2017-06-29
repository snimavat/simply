package org.simply.cms.gorm

import groovy.transform.CompileStatic
import groovy.util.logging.Slf4j
import me.nimavat.shortid.ShortId
import org.grails.datastore.mapping.core.Datastore
import org.grails.datastore.mapping.engine.EntityAccess
import org.grails.datastore.mapping.engine.event.*
import org.grails.datastore.mapping.model.PersistentEntity
import org.springframework.context.ApplicationEvent

@CompileStatic
@Slf4j
class ShortidGeneratorEventListener extends AbstractPersistenceEventListener {
	private static final String SHORTID_PROPERTY = "shortid"

	ShortidGeneratorEventListener(final Datastore datastore) {
		super(datastore)
	}

	@Override
	protected void onPersistenceEvent(AbstractPersistenceEvent event) {
		if(event.entity == null) return
		if(event.eventType == EventType.PreInsert) {
			beforeInsert(event.entity, event.entityAccess)
		}
	}

	@Override
	boolean supportsEventType(Class<? extends ApplicationEvent> eventType) {
		return eventType == PreUpdateEvent || eventType == PreInsertEvent
	}

	private void beforeInsert(PersistentEntity entity, EntityAccess ea) {
		Class<?> type =  ea.getPropertyType(SHORTID_PROPERTY)
		if(type && String.isAssignableFrom(type)) {
			ea.setProperty(SHORTID_PROPERTY, ShortId.generate())
		}
	}
}
