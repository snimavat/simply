<g:form name="page-form" method="POST" id="${instance.id}" namespace="admin" action="${actionName}">
<f:all bean="instance"/>
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