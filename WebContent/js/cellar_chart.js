(function() {
    // Load the Visualization API and the piechart package.
    google.load('visualization', '1.0', {'packages':['corechart']});

    // Callback that creates and populates a data table,
    // instantiates the pie chart, passes in the data and
    // draws it.
    function drawChart() {
        // Create the data table.
        var data = new google.visualization.DataTable();
        data.addColumn('string', 'Type');
        data.addColumn('number', 'Bottles');
        data.addRows([
            ['Red', 300],
            ['White', 100],
            ['Rose', 12],
            ['Dessert', 30],
            ['Other', 16]
        ]);

        // Set chart options
        var options = {
            'width': "100%",
            'height': 270,
            'backgroundColor': 'transparent',
            chartArea: {width:"90%",height:"70%", left:"15%"},
            colors: ['#c70000', '#dadada', '#e9b5b5', '#600404', '#454545']
        };

        // Instantiate and draw our chart, passing in some options.
        var chart = new google.visualization.PieChart(document.getElementById('chart_div'));
        chart.draw(data, options);
    }

    // Set a callback to run when the Google Visualization API is loaded.
    google.setOnLoadCallback(drawChart);
})();