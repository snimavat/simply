<g:form name="page-form" method="POST" id="${instance.id}" namespace="admin" action="${actionName}" id="${params.id}">
	<g:hiddenField name="parent.id" value="${instance.parent.id}"/>
	<g:hiddenField name="type" value="${grails.util.GrailsNameUtils.getPropertyName(instance.class)}"/>
<f:all bean="instance" order="${props}"/>
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

<script src="https://cloud.tinymce.com/stable/tinymce.min.js"></script>
<script>tinymce.init({
	selector:'textarea',
    toolbar: 'undo redo | styleselect | bold italic | link image | codesample',
    menubar:false,
    plugins : 'advlist autolink link image lists preview codesample',
    file_picker_callback: function(callback, value, meta) {
	    console.log(arguments);
	    alert('called');
    }
});</script>