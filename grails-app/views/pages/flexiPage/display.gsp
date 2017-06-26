<%@ page contentType="text/html;charset=UTF-8" defaultCodec="none" %>
<html>
<head>
	<meta name="layout" content="admin">
	<title>Flexi page</title>
</head>

<body>
<div class="panel panel-default">
	<div class="panel-heading">
		Page content
	</div>
	<div class="panel-body">
		<cms:renderBlock block="${page.body}"/>
	</div>
</div>
</body>
</html>

