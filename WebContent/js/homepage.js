(function() {

    var home = document.getElementById("home");
    var wine_search_text = document.getElementById("wine_search_text");
    var wine_search_submit = document.getElementById("wine_search_submit");

    var HOME_ANIM_OUT = "fadeOutLeftBig";
    var HOME_ANIM_IN = "fadeInLeftBig";

    function execute_wine_search()
    {
        var url = "/wine";

        // Add the container for the quick-search if it doesn't already exist.
        //display_quick_search_container();

        var data = {
            "action": "wineSearch",
            "textWineSearch": $(wine_search_text).val()
        };

        $.ajax({
            url: url,
            data: data
        }).success(show_search_results);
    }

    function show_search_results(data, textStatus, jqXHR)
    {
        _500bottles.animate_out({element: home});
        _500bottles.add_view(data);

        var results = document.getElementById("quick_search_results");

        $(results).on("click", function() {
            _500bottles.animate_out({element: results});
            _500bottles.animate_in({element: home});

            setTimeout(function() {
                $("#quick_search_results").remove();
            }, 1000);
        });
    }

    function display_quick_search_container()
    {
        var container = document.getElementById("quick_search_results");

        if (container == null)
            $("body").prepend($("<div/>").attr("id", "quick_search_results"));

        _500bottles.animate_out({element: home});
    }

    $(wine_search_submit).on("click", execute_wine_search);
})();