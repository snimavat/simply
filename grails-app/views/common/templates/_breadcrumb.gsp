<ol class="breadcrumb">
	<g:each in="${pages}" var="page">
		<li><g:link controller="${controller}" action="${action}" id="${page.id}">${page.title}</g:link> </li>
	</g:each>
</ol>