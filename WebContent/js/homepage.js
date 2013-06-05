(function() {

    var wine_search_text = document.getElementById("wine_search_text");
    var wine_search_submit = document.getElementById("wine_search_submit");

    function execute_wine_search()
    {
        var url = "/wine";

        var data = {
            "action": "wineSearch",
            "textWineSearch": $(wine_search_text).val()
        };

        $.ajax({
            url: url,
            data: data
        }).success(function (data, textStatus, jqXHR) {
            $("#quick_search_results").append(data);
            $("#quick_search_results").on("click", function() {
                $("#quick_search_results>*").remove();
            });
        });
    }

    $(wine_search_submit).on("click", execute_wine_search);
})();