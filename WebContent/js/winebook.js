(function() {

    var add_winebook_entry = document.getElementById("add_winebook_entry");
    var entry_form = null;

    function get_entry_form()
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

            entry_form = document.getElementById("winebook_entry_form");

            $(entry_form).children(".close_icon").on("click", close_entry_form);

            open_entry_form();
        });
    }

    function open_entry_form()
    {
        entry_form = document.getElementById("winebook_entry_form");

        // If the form is not already loaded, get it from the server.
        if (entry_form == null) {
            get_entry_form();

            return;
        }

        _500bottles.views.show_floating_view(entry_form);
    }

    function close_entry_form()
    {
        _500bottles.views.hide_floating_view(entry_form);
    }

    function bind_events()
    {
        $(add_winebook_entry).on("click", open_entry_form);
    }

    bind_events();

})();