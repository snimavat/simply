
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
	<meta name="layout" content="admin">
	<title></title>
</head>

<body>
		<div class="subhead">
			<div class="subhead-heading">Pages</div>
			<div class="subhead-actions">
				<g:link action="addSubpage" class="btn btn-primary btn-xs" id="${params.id}">Create child page</g:link>
			</div>
		</div>

		<cms:breadcrumb page="${page}"/>
		<g:render template="list"/>

</body>
</html>

