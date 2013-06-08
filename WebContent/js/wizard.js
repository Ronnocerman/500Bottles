(function() {

    /*
     * Suggestion form selectors for the various options.
     */
    var type_radio_selector = "#wizard_form input:radio[name=type]:checked";
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
        data.type = $(type_radio_selector).val();
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

        console.log("getting suggestion...");

        $.ajax({
            url: url,
            data: data
        }).success(show_suggestion_results);

        _500bottles.anim.animate_out({element: home_subview});
        _500bottles.views.show_loader();
    }

    function show_suggestion_results(data, textStatus, jqXHR)
    {
        console.log(data);
    }

    function hide_suggestion_results()
    {

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