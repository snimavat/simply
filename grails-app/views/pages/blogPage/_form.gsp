<g:form name="page-form" method="POST" id="${instance.id}" namespace="admin" action="${actionName}" id="${params.id}">
	<g:hiddenField name="parent.id" value="${instance.parent.id}"/>
	<g:hiddenField name="type" value="${grails.util.GrailsNameUtils.getPropertyName(instance.class)}"/>

	<f:with bean="instance">
		<f:field property="slug"/>
		<f:field property="title"/>
		<f:field property="category" widget-inline="${true}"/>
		<f:field property="intro"/>
		<f:field property="keywords"/>
		<f:field property="metaDescription"/>
		<f:field property="published"/>
	</f:with>

	<div class="form-actions">
		<button type="submit" class="btn btn-primary" value="save">
			<i class="icon-ok icon-white"></i>
			<g:if test="${actionName == 'edit'}">Update</g:if>
			<g:else>Save</g:else>
		</button>

		<admin:link action="list" class="btn btn-warning">
			<i class="icon-arrow-left icon-white"></i> Cancel
		</admin:link>
	</div>
</g:form>

