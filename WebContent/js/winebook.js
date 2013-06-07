(function() {

    function display_entry_form()
    {
        var url = "/";

        var data = {
            "action": "getView",
            "view": "winebook/entry_form"
        };

        $.ajax({
            url: url,
            data: data
        }).success(function (data, textStatus, jqXHR) {
            _500bottles.views.add_floating_view({
                contents: data,
                id: "winebook_entry_form"
            });
        });
    }

    display_entry_form();

})();