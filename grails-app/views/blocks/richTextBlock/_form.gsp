<style>
textarea {
	min-height: 150px;
}

</style>

<form>
	<textarea id="value" name="block.value" class="form-control">${block.value}</textarea>
</form>

<script>
    function destroyTinyMce() {
        tinymce.editors = [];
        $.unsubscribe('block.form.hidden', destroyTinyMce);
    }

    function onModalShow() {
        //alert("init tinymce");
        $.unsubscribe('block.form.shown', onModalShow);

        $(document).on('focusin', function(e) {
            if (($(e.target).closest(".mce-window").length)) {
                return e.stopImmediatePropagation();
            }
        });

        tinymce.init({
            selector: '#value',
            toolbar: 'undo redo | styleselect | bold italic | numlist bullist | link image | codesample code',
            menubar: false,
            plugins: 'advlist autolink link image lists preview codesample code',


            branding: false,
            resize: true,


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

            content_css: ''

            /*
             file_picker_callback: function (callback, value, meta) {
             console.log(arguments);
             alert('called');
             }*/
        });

    }

    $.subscribe('block.form.shown', onModalShow);
    $.subscribe('block.form.hidden', destroyTinyMce);

    /*
    tinymce.PluginManager.add('stylebuttons', function(editor, url) {
        ['pre', 'p', 'code', 'h1', 'h2', 'h3', 'h4', 'h5', 'h6'].forEach(function(name){
            editor.addButton("style-" + name, {
                tooltip: "Toggle " + name,
                text: name.toUpperCase(),
                onClick: function() { editor.execCommand('mceToggleFormat', false, name, {skip_focus: true}); },
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
    */

</script>
