<table class="table table-striped">
	<thead>
	<tr>
		<g:sortableColumn property="id" title="id" params="[domainClass:params.domainClass]" namespace="admin"/>
		<g:each in="${domainProperties}" var="p" status="i">
			<g:set var="propTitle">${domainClass.propertyName}.${p.name}.label</g:set>
			<g:sortableColumn property="${p.name}" title="${message(code: propTitle, default: p.naturalName)}" params="[domainClass:params.domainClass]" namespace="admin"/>
		</g:each>
	</tr>
	</thead>
	<tbody>
	<g:each in="${collection}" var="bean" status="i">
		<tr data-row-id="${bean.id}" data-display="${bean?.name}">
			<td><admin:link action="edit" id="${bean.id}"><f:display bean="${bean}" property="id" displayStyle="${displayStyle?:'table'}" /></admin:link></td>
			<g:each in="${domainProperties}" var="p" status="j">
					<td><f:display bean="${bean}" property="${p.name}"  displayStyle="${displayStyle?:'table'}" /></td>
			</g:each>
		</tr>
	</g:each>
	</tbody>
</table>