(function() {

    /*
     * Suggestion form selectors for the various options.
     */
    var wizard = document.getElementById("wizard");
    var wizard_subview = document.getElementById("wizard_subview");
    var type_selector = "#wizard_form #type option:selected";
    var varietal_selector = "#wizard_form #varietalSelect";
    var vineyard_selector = "#wizard_form input[name=vineyard]";
    var max_year_selector = "#wizard_form input[name=max_year]";
    var min_year_selector = "#wizard_form input[name=min_year]";
    var min_price_selector = "#wizard_form input[name=min_year]";
    var max_price_selector = "#wizard_form input[name=max_price]";
    var submit = "#wizard_form input[type=button]";

    function get_suggestion_options()
    {
        var data = {};
        data.type = $(type_selector).val();
        data.varietal = $(varietal_selector).val();
        data.vineyard = $(vineyard_selector).val();
        data.maxYear = $(max_year_selector).val();
        data.minYear = $(min_year_selector).val();
        data.minPrice = $(min_price_selector).val();
        data.maxPrice = $(max_price_selector).val();

        for(item in data)
            data[item] = encodeURIComponent(data[item]);

        return data;
    }

    function get_suggestion()
    {
        var data = get_suggestion_options();
        var url = "/winewizard";
        var action = "getSuggestion";

        data.action = action;

        $.ajax({
            url: url,
            data: data
        }).success(show_suggestion_results);

        _500bottles.anim.animate_out({element: wizard_subview});
        _500bottles.views.show_loader();
    }

    function show_suggestion_results(data, textStatus, jqXHR)
    {
        _500bottles.views.hide_loader();
        _500bottles.views.add_subview(wizard, data, "suggestion_results");
    }

    function hide_suggestion_results()
    {
        var results = document.getElementById("suggestion_results");

        _500bottles.anim.animate_out({element: results});
        _500bottles.anim.animate_in({element: wizard_subview, parent: wizard});

        setTimeout(function() {
            $("#suggestion_results").remove();
        }, 1000);
    }

    function add_suggestion_results()
    {

    }

    function remove_suggestion_results()
    {

    }

    function bind_events()
    {
        $(submit).on("click", get_suggestion);
    }

    bind_events();
})();