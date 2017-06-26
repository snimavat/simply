<%@ page import="grails.util.GrailsNameUtils" contentType="text/html;charset=UTF-8" %>
<html>
<head>
	<meta name="layout" content="admin">
	<title></title>
</head>

<body>
<div class="subhead">
	<div class="subhead-heading">Add child page</div>

</div>

<p>Choose which type of page you'd like to create.</p>

<div class="list-group">
	<g:each in="${pageTypes}" var="p">
		<g:link action="create" params="[id: parent.id, type: grails.util.GrailsNameUtils.getPropertyName(p)]"
				class="list-group-item">${p.simpleName}</g:link>
	</g:each>
</div>

</body>
</html>

