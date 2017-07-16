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

<nav>
	<ul class="pager">
		<g:if test="${currentPage > 1}">
			<li class="previous"><a href="${request.forwardURI}?page=${currentPage - 1}"><span aria-hidden="true">&larr;</span> Previous</a></li>
		</g:if>
		<g:if test="${currentPage < totalPageCount}">
			<li class="next"><a href="${request.forwardURI}?page=${currentPage + 1}">Next <span aria-hidden="true">&rarr;</span></a></li>
		</g:if>
	</ul>
</nav>

</body>
</html>

