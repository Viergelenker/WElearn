$(function () {
    $('#statChart').highcharts({
        chart: {
            type: 'bar'
        },
        title: {
            text: 'Rating'
        },
        
        xAxis: {
            categories: usernames,
            title: {
                text: null
            }
        },
        yAxis: {
            min: 0,
            title: {
                text: 'Points',
                align: 'high'
            },
            labels: {
                overflow: 'justify'
            }
        },
        tooltip: {
            valueSuffix: ' points'
        },
        plotOptions: {
            bar: {
                dataLabels: {
                    enabled: true
                }
            }
        },
        legend: {
            enabled: true
        },
        credits: {
            enabled: false
        },
        series: [{
            name: 'Total',
            data: points
        }, {

            name: 'Average',
            data: averagePoints
        }]
    });
});