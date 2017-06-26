
class EditPageCtrl
  @$inject = ["$el", "$attrs", "$log", "blockFormServ", "$confirm", "pathWithContext"]

  constructor: ($el, $attrs, $log, blockFormServ, $confirm, $path) ->
    pageId = $attrs['page-id']

    blockTpl = _.template """
                  <div class="block block-{{type}} block-wrapper" data-index="{{index}}" data-type="{{type}}">
                    <div class="pull-right">
                        <a href="javascript:" class="block-action"><i class="fa fa-pencil-square-o"></i> </a>
                        <a href="javascript:" class="block-action block-delete"><i class="fa fa-trash-o"></i> </a>
                    </div>
                  </div>
                """

    addBlock = () ->
      fieldName = $(this).data('field')
      field = $("#flexi-field-#{fieldName}")
      index = $("#flexi-field-#{fieldName} .block:last").data("index")
      type = $(this).data('block-type')

      if index?
        index = index + 1
      else
        index = 0

      promise = blockFormServ(pageId, index, type)
      promise.then (blockContent) ->
        block = $(blockTpl({type: type, index: index})).append(blockContent)
        field.append(block)


    editBlock = () ->
      block = $(this)
      index = $(this).data("index")
      fieldName = block.parent().data("field")
      field = $("#flexi-field-#{fieldName}")
      type = $(this).data("type")

      promise = blockFormServ(pageId, index, type, "edit")
      promise.then (blockContent) ->
        updated = $(blockTpl({type: type, index: index})).append(blockContent)
        block.html(updated)


    deleteBlock = (e) ->
      e.stopPropagation()
      promise = $confirm("Are you sure you want to delete the block?")
      block = $(this).parents(".block")
      blockIndex = block.data("index")
      promise.then (result) ->
        p = $.post($path("admin/block/delete", {index: blockIndex, "page.id":pageId}))
        p.done () -> block.remove()

    $el.find(".flexi-field").on("click", ".block-wrapper", editBlock)
    $el.find(".flexi-field").on("click", ".block-delete", deleteBlock)
    $el.find("a.add-block").on("click", addBlock)


app.controller("EditPageCtrl", EditPageCtrl)


