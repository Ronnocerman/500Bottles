(function() {

    var home = document.getElementById("home");
    var home_subview = document.getElementById("home_subview");
    var wine_search_text = document.getElementById("wine_search_text");
    var wine_search_submit = document.getElementById("wine_search_submit");
    var wine_grid = document.getElementById("cellar_preview_wine_grid");
    var cellarupdated = false;
    
    _500bottles.views.fix_height(home, home_subview);

    setTimeout(function() {
        _500bottles.views.fix_height(home, home_subview);
    }, 1000);

    /**
     *
     */
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

        _500bottles.anim.animate_out({element: home_subview});
        _500bottles.views.show_loader();
    }

    /**
     *
     * @param data
     * @param textStatus
     * @param jqXHR
     */
    function show_search_results(data, textStatus, jqXHR)
    {
        _500bottles.views.hide_loader();
        _500bottles.views.add_subview(home, data, "quick_search_results");

        var results = document.getElementById("quick_search_results");

        $("#logo").on("click", hide_quick_search_container);
    }

    /**
     *
     */
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

    function bind_quick_search_enter_key()
    {
        $(document).on("keypress", on_quick_search_enter_key);
    }

    function unbind_quick_search_enter_key()
    {
        $(document).off("keypress", on_quick_search_enter_key);
    }

    function on_quick_search_enter_key(e)
    {
        if (e.which == 13) {
            e.preventDefault();
            execute_wine_search();
        }
    }
    
    function refresh_your_cellar()
    {
    	
    	console.log("in ref cellar");
    	
        var url = "/";

        var data = {
            "action": "getView",
            "view": "wine_grid/wine_grid"
        };
        
        var type = "GET";

        $.ajax({
            url: url,
            data: data,
            type:type,
            crossDomain: true
        }).success(function(data, textStatus, jqXHR){
        	console.log("refreshed");
        	$(wine_grid).append(data);
        	
        
        });

        
    }

    $(wine_search_submit).on("click", execute_wine_search);
    $(wine_search_text).on("focus", bind_quick_search_enter_key);
    $(wine_search_text).on("blur", unbind_quick_search_enter_key);
    $(document).bind("cellarupdate", function() { refresh_your_cellar(); cellarupdated = true; console.log("refresh cellar now");});
})();