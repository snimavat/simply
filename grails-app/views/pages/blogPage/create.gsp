<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
	<meta name="layout" content="admin">
	<title></title>
</head>

<body>
	<div class="row">
		<div class="col-md-12">
			<cms:breadcrumb page="${instance}"/>
			<div class="panel panel-default">
				<div class="panel-heading">
					Create Page
					<p class="pull-right">
						<admin:link action="create" class="btn btn-primary btn-xs">Create new</admin:link>
					</p>
				</div>
				<div class="panel-body">
					<g:render template="/pages/blogPage/form"/>
				</div>
			</div>
		</div>
	<div>
	</div>
	</div>
</body>