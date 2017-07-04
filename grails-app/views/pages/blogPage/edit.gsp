<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
	<meta name="layout" content="admin">
	<title>Edit page</title>

	<asset:stylesheet src="lapsi.css"/>
</head>

<body>
	<div class="row" controller="EditPageCtrl" page-id="${instance.id}">
		<div class="col-md-12">
			<div class="panel panel-default">
				<div class="panel-heading">
					<admin:link action="create" class="btn btn-primary btn-xs">Back</admin:link>
					Edit Page
					<span class="pull-right clickable panel-collapsed"><i class="glyphicon glyphicon-chevron-down"></i></span>
				</div>
				<div class="panel-body" style="display: none;">
					<g:render template="/pages/blogPage/form"/>
				</div>
			</div>

			<div class="panel panel-default">
				<div class="panel-heading">Body
					<div class="btn-group">
						<button type="button" class="btn btn-default btn-xs dropdown-toggle" data-toggle="dropdown">
							Add Block <span class="caret"></span>
						</button>
						<g:set bean="blockService" var="blockService"/>
						<g:set var="blockTypes" value="${blockService.listAvailableBlocks()}"/>
						<ul class="dropdown-menu">
							<g:each in="${blockTypes}" var="blockType">
								<li><a href="javascript:" class="add-block" data-field="body" data-block-type="${blockType.simpleName}">${blockType.simpleName}</a></li>
							</g:each>
						</ul>
					</div>

				</div>
				<div class="panel-body" style="padding-top: 25px;">
					<f:widget bean="instance" property="body"/>
				</div>
			</div>
		</div>
	<div>
	</div>
	</div>
	<asset:javascript src="simply/admin/simply.js"/>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/tinymce/4.6.4/tinymce.min.js"></script>
	<asset:stylesheet src="prism/prism.css"/>
	<asset:javascript src="prism/prism.js"/>
</body>