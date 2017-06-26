
<div class="list-group">
<g:each in="${list}" var="page" status="i">
	<div class="list-group-item clearfix">
		<div>
			<div class="col-md-10">
				<h4 class="list-group-item-heading"><g:link action="edit" id="${page.id}">${page.title}</g:link> </h4>
				<p class="text-gray d-block">Created on <g:formatDate date="${page.lastUpdated}"></g:formatDate> </p>

			</div>
			<div class="col-md-2">
				<div class="pull-right">
					<a href="${createLink(controller: 'page', action: 'list', id: page.id)}" style="font-size: 30px;">
						<i class="fa fa-chevron-right" aria-hidden="true"></i>
					</a>
				</div>
			</div>
		</div>

	</div>
</g:each>
</div>


<div id="paginate" controller="paginate" , data-url="admin/author/list" data-update=".container" data-total="2"></div>