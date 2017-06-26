<g:if test="${block?.blocks}">
	<g:each in="${block.blocks}" var="b">
		<cms:renderBlock block="${b}"/>
	</g:each>
</g:if>