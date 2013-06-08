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

        $("#logo").on("click", hide_quick_search_container);
    }

    function hide_quick_search_container()
    {
        var results = document.getElementById("quick_search_results");

        _500bottles.anim.animate_out({element: results});
        _500bottles.anim.animate_in({element: home_subview, parent: home});

        setTimeout(function() {
            $("#quick_search_results").remove();
        }, 1000);

        $("#logo").off("click", hide_quick_search_container);
    }

    $(wine_search_submit).on("click", execute_wine_search);
})();