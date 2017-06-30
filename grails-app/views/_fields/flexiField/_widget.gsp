<%@ page defaultCodec="none" %>

<div id="flexi-field-${property}" data-field="${property}" class="flexi-field">
	<g:if test="${value}">
		<g:each in="${value}" var="block" status="blockIndex">
			<div class="block block-${block.class.simpleName} block-wrapper" data-index="${blockIndex}" data-type="${block.class.simpleName}">
				<div class="pull-right">
					<a href="javascript:" class="block-action"><i class="fa fa-pencil-square-o"></i> </a>
					<a href="javascript:" class="block-action block-delete"><i class="fa fa-trash-o"></i> </a>
				</div>
				${block.value}
			</div>
		</g:each>
	</g:if>
</div>