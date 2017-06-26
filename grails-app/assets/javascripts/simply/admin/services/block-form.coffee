app.service "blockFormServ", ["$log", "pathWithContext"], ($log, pathWithContext) ->

  submit = (dialog, params) ->
    form = dialog.find("form")
    $.publish('block.form.beforeSave', bootbox);
    $.post(pathWithContext("admin/block/save", params), form.serialize())


  (pageId, blockIndex, blockName, action = 'create') ->
    deferred = $.Deferred()
    dialog = bootbox.dialog {
      title: 'Select'
      message: '<p><i class="fa fa-spin fa-spinner"></i> Loading...</p>'
      size: 'large'
      buttons:
        cancel:
          label: 'Cancel',
          className: 'btn-danger'
          callback: () ->
            deferred.reject()

        select:
          label: 'Save',
          className: 'btn-success',
          callback: () ->
            data = undefined
            promise = submit(dialog, {"page.id":pageId, index:blockIndex, blockType:blockName})
            promise.done (data, textStatus, jqXHR) ->
              deferred.resolve(data) if data?
              dialog.modal("hide")

            return false
    }

    dialog.on "hidden.bs.modal", () ->
      $.publish('block.form.hide', bootbox);
      dialog.remove()

    dialog.init ()->
      promise = $.get(pathWithContext("admin/block/#{action}", {pageId:pageId, index:blockIndex, blockType:blockName}))
      promise.done (data) ->
        dialog.find('.bootbox-body').html(data)


    deferred.promise()
