<%@ page contentType="text/html;charset=UTF-8" defaultCodec="none" %>
<html>
<head>
	<meta name="layout" content="blog">
	<title>Grails blog</title>
</head>

<body>
<g:each in="${childs}" var="p">
	<div class="blog-post">
		<h2 class="blog-post-title">
			<cms:pageLink page="${p}">${p.title}</cms:pageLink>
		</h2>
		<p class="blog-intro">${p.intro}</p>
	</div>
</g:each>
</body>
</html>

