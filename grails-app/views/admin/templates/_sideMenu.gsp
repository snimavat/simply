<div class="sidebar-nav">
	<div class="navbar navbar-default" role="navigation">
		<div class="navbar-header">
			<button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".sidebar-navbar-collapse">
				<span class="sr-only">Toggle navigation</span>
				<span class="icon-bar"></span>
				<span class="icon-bar"></span>
				<span class="icon-bar"></span>
			</button>
			<span class="visible-xs navbar-brand">Sidebar menu</span>
		</div>
		<div class="navbar-collapse collapse sidebar-navbar-collapse">
			<ul class="nav navbar-nav">
				<li class="${'user' == params.domainClass ? 'active' : ''}">
					<admin:link controller="genericAdmin" namespace="admin" domain="user">Users</admin:link>
				</li>

				<li class="${'role' == params.domainClass ? 'active' : ''}">
					<admin:link controller="genericAdmin" namespace="admin" domain="role">Roles</admin:link>
				</li>

				<li class="${'site' == params.domainClass ? 'active' : ''}">
					<admin:link controller="genericAdmin" namespace="admin" domain="site">Sites</admin:link>
				</li>

				<li class="${controllerName == 'page' ? 'active' : ''}">
					<g:link controller="page" action="list" namespace="admin">Pages</g:link>
				</li>

				<%--
				<g:set bean="adminService" var="adminService"/>
				<g:each in="${adminService.adminControllers}" var="adminController">
					<li class="${adminController.controller == controllerName ? 'active' : ''}">
						<g:link controller="${adminController.controller}" namespace="admin">${adminController.name}</g:link>
					</li>
				</g:each>
				--%>

				<!--
				<li class="active"><g:link controller="page" namespace="admin">Pages</g:link> </li>
				<li><g:link controller="space" namespace="admin">Spaces</g:link></li>
				<li><g:link controller="block" namespace="admin">Blocks</g:link></li>
				<li><a href="#">Menu Item 4</a></li>
				<li><a href="#">Reviews <span class="badge">1,118</span></a></li>
				-->
			</ul>
		</div><!--/.nav-collapse -->
	</div>
</div>
