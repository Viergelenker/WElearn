$(document).foundation();

$(document).ready(function () {

    $(".buttonPost").attr("disabled", "true");
    $(".buttonComment").attr("disabled", "true");

    tinymce.init({
        setup: function (editor) {
            editor.on('keyup', function (e) {
                if (editor.getContent().length > 0) {
                    $('.buttonPost').removeAttr("disabled");
                } else {
                    $('.buttonPost').attr("disabled", "true");
                }
            });
        },
        selector: '#mytextareaforpost',
        statusbar: false,
        toolbar: "undo redo | styleselect | bold italic | alignleft aligncenter alignright alignjustify | bullist numlist | "
    });

    tinymce.init({
        selector: '.mytextarea',
        statusbar: false,
        toolbar: "undo redo | styleselect | bold italic | alignleft aligncenter alignright alignjustify | bullist numlist | "
    });

    $('.area').on("keyup", action);
    function action() {
        if ($('.area').val().length > 0) {
            $('.buttonComment').removeAttr("disabled");
        } else {
            $('.buttonComment').attr("disabled", "true");
        }
    }
});