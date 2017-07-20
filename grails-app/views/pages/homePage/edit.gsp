<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
	<meta name="layout" content="admin">
	<title>Edit page</title>
	<asset:stylesheet src="boostrap-addons.css"/>
	<asset:stylesheet src="lapsi.css"/>
	<asset:javascript src="simply/admin/simply.js"/>
</head>

<body>
	<div class="row" controller="EditPageCtrl" page-id="${instance.id}">
		<div class="col-md-12">
			<cms:breadcrumb page="${instance}" />
			<div class="panel panel-default">
				<div class="panel-heading">
					<admin:link action="create" class="btn btn-primary btn-xs">Back</admin:link>
					Edit Page
					<span class="pull-right clickable panel-collapsed"><i class="glyphicon glyphicon-chevron-down"></i></span>
				</div>
				<div class="panel-body" style="display: none;">
					<g:render template="/pages/homePage/form"/>
				</div>
			</div>

		</div>
	<div>
	</div>
	</div>
</body>