<%@ page contentType="text/html;charset=UTF-8" defaultCodec="none" %>
<html>
<head>
	<meta name="layout" content="blog">
	<title>${page.title}</title>
	<meta name="keywords" content="${page.keywords}"/>
	<meta name="description" content="${page.metaDescription}"/>
</head>

<body>
<div class="blog-post">
	<h2 class="blog-post-title">
		${page.title}
		<sec:ifAllGranted roles="ROLE_ADMIN">
			<g:link controller="page" action="edit" id="${page.id}" namespace="admin">
				<i class="fa fa-edit" style="color: #2b669a"></i>
			</g:link>
		</sec:ifAllGranted>
	</h2>
	<div class="blog-post-meta">
		<span class="date">Published: <g:formatDate date="${page.dateCreated}" format="MMM dd yyyy"/></span>
	</div>
	<g:if test="${page.body != null}">
		<g:each in="${page.body}" var="block">
			<cms:renderBlock block="${block}"/>
		</g:each>
	</g:if>

	<g:if test="${page.commentsEnabled}">
		<disqus:comments id="test" identifier="${page.shortid}"/>
	</g:if>
</div>
</body>
</html>

