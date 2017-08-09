<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">

	<title><g:layoutTitle/></title>

	<asset:link rel="shortcut icon" href="lapsi/favicon.ico" type="image/x-icon"/>
	<asset:stylesheet src="bootstrap/css/bootstrap.css"/>
	<asset:stylesheet src="font-awsome/css/font-awesome.min.css"/>

	<link href="https://fonts.googleapis.com/css?family=Open+Sans:400,700|Source+Sans+Pro:400,700" rel="stylesheet">

	<asset:stylesheet src="prism/prism.css"/>
	<asset:stylesheet src="blog.css"/>

	<g:layoutHead/>
	<style>
	.top-bar {
		padding:5px 0 0 0;
	}

	.top-bar ul {
		padding-left: 0;
		list-style: none;
		color: #fff;
	}

	.top-bar ul li {
		display: inline-block;
		padding-right:20px;
	}


	.top-bar a {
		color: #333333;
		font-weight: bold;
	}

	.top-bar a:hover {
		color: #222222;
	}
	</style>
</head>
<body data-context="${request.contextPath}">
<div class="top-bar">
	<div class="container">
		<div class="row">
			<div class="col-md-12 text-right">
				<ul class="">
					<li><a href="/" title="nimavat.me">Home</a></li>
					<li><a href="/blog" title="Blog">Blog</a></li>
					<li><a href="/code-snippets" title="code snippets">Snippets</a></li>
				</ul>
			</div>
		</div>
	</div>
</div>
<div class="container main-content">
	<!--
	<div class="blog-header">
		<h1 class="blog-title">The Grails Playground</h1>
		<p class="lead blog-description">The official example template of creating a blog with Bootstrap.</p>
	</div>
	-->
	<div class="row" style="margin-top: 30px;">

		<div class="col-sm-12 blog-main" style="padding-left: 1.5em; padding-top: 1.5em">
			<g:layoutBody/>
		</div>
	</div>
</div>

<asset:javascript src="prism/prism.js" async="true"/>

<g:if env="production">
	<g:render template="/common/templates/mixpanel"/>
</g:if>

<footer class="footer">
	<div class="container">
		<div class="row">
			<div class="col-sm-12">
				<div class="text-center">
					<p class="text-muted">Blog developed with <a href="https://github.com/snimavat/simply">simply</a> Grails CMS</p>
					<p class="text-muted">Looking for a Grails developer ! <a href="/">Check with me.</a></p>
				</div>
			</div>
		</div>
	</div>
</footer>

</body>
</html>
