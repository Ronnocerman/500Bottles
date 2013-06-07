(function() {

    var home = document.getElementById("home");
    var home_subview = document.getElementById("home_subview");
    var wine_search_text = document.getElementById("wine_search_text");
    var wine_search_submit = document.getElementById("wine_search_submit");

    _500bottles.views.fix_height(home, home_subview);
    setTimeout(function() {
        _500bottles.views.fix_height(home, home_subview);
    }, 1000);

    function execute_wine_search()
    {
        var url = "/wine";

        // Add the container for the quick-search if it doesn't already exist.
        //display_quick_search_container();

        var data = {
            "action": "wineSearch",
            "textWineSearch": encodeURIComponent($(wine_search_text).val())
        };

        $.ajax({
            url: url,
            data: data
        }).success(show_search_results);
    }

    function show_search_results(data, textStatus, jqXHR)
    {
        _500bottles.anim.animate_out({element: home_subview});
        _500bottles.views.add_subview(home, data, "quick_search_results");

        var results = document.getElementById("quick_search_results");

//        $(results).on("click", function() {
//            _500bottles.anim.animate_out({element: results});
//            _500bottles.anim.animate_in({element: home_subview, parent: home});
//
//            setTimeout(function() {
//                $("#quick_search_results").remove();
//            }, 1000);
//        });
    }

    function display_quick_search_container()
    {
        var container = document.getElementById("quick_search_results");

        if (container == null)
            $("body").prepend($("<div/>").attr("id", "quick_search_results"));

        _500bottles.anim.animate_out({element: home});
    }

    $(wine_search_submit).on("click", execute_wine_search);
})();