(function() {
    var nav_wine = document.getElementById("nav_wines");
    var nav_wizard = document.getElementById("nav_wizard");
    var nav_cellar = document.getElementById("nav_cellar");
    var nav_winebook = document.getElementById("nav_winebook");

    var view_wine = null;
    var view_wizard = null;
    var view_cellar = null;
    var view_winebook = null;

    function on_nav_wine_click()
    {
        if (!view_wine)
            get_view("wine", function(data) {
                add_view(data);
                view_wine = document.getElementById("wine");
            });
    }

    function on_nav_wizard_click()
    {
        if (!view_wizard)
            get_view("wizard", function(data) {
                add_view(data);
                view_wizard = document.getElementById("wizard");
            });
    }

    function on_nav_cellar_click()
    {
        if (!view_cellar)
            get_view("cellar", function(data) {
                add_view(data);
                view_cellar = document.getElementById("cellar");
            });
    }

    function on_nav_winebook_click()
    {
        if (!view_winebook)
            get_view("winebook", function(data) {
                add_view(data);
                view_winebook = document.getElementById("winebook");
            });
    }

    /**
     * Appends the received HTML to the page.
     */
    function add_view(view_contents)
    {
        $("body").append(view_contents);
    }

    /**
     * Executes an AJAX request to retrieve a view.
     * @param view_name     The name of the view.
     * @param cb            Callback function.
     */
    function get_view(view_name, cb)
    {
        var url = "/";

        var data = {
            "action": "getView",
            "view": view_name
        };

        $.ajax({
            url: url,
            data: data
        }).success(function (data, textStatus, jqXHR) {
            cb.call(this, data);
        });
    }

    $(nav_wine).on("click", on_nav_wine_click);
    $(nav_wizard).on("click", on_nav_wizard_click);
    $(nav_cellar).on("click", on_nav_cellar_click);
    $(nav_winebook).on("click", on_nav_winebook_click);
})();