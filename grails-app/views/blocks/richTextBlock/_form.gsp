<style>
textarea {
	resize: none;
	min-height: 150px;
}

</style>

<form>
	<textarea id="value" name="block.value" class="form-control">${block.value}</textarea>
</form>

<script>
    function destroyTinyMce() {
        tinymce.editors = [];
        $.unsubscribe('block.form.hide', destroyTinyMce);
    }

    $.subscribe('block.form.hide', destroyTinyMce);

    $(document).on('focusin', function (e) {
        if ($(e.target).closest(".mce-window").length) {
            e.stopImmediatePropagation();
        }
    });


    tinymce.PluginManager.add('stylebuttons', function(editor, url) {
        ['pre', 'p', 'code', 'h1', 'h2', 'h3', 'h4', 'h5', 'h6'].forEach(function(name){
            editor.addButton("style-" + name, {
                tooltip: "Toggle " + name,
                text: name.toUpperCase(),
                onClick: function() { editor.execCommand('mceToggleFormat', false, name); },
                onPostRender: function() {
                    var self = this, setup = function() {
                        editor.formatter.formatChanged(name, function(state) {
                            self.active(state);
                        });
                    };
                    editor.formatter ? setup() : editor.on('init', setup);
                }
            })
        });
    });

    tinymce.init({
        selector: '#value',
        toolbar: 'undo redo | styleselect | style-h1 style-h2 style-h3 | bold italic | link image | codesample code',
        menubar: false,
        plugins: 'advlist autolink link image lists preview codesample code stylebuttons',


        branding: false,
        resize: false,
        theme: 'modern',

        codesample_languages: [
            {text: 'HTML/XML', value: 'markup'},
            {text: 'JavaScript', value: 'javascript'},
            {text: 'Java', value: 'java'},
            {text: 'Groovy', value: 'groovy'}
        ],

        setup: function (editor) {
            editor.on('change', function () {
                editor.save();
            });
        },

        file_picker_callback: function (callback, value, meta) {
            console.log(arguments);
            alert('called');
        }
    });




</script>
