/**
 * Created by Julien on 30.06.16.
 */

$(function () {
    $('#container').highcharts({
        data: {
            table: 'datatable'
        },
        chart: {
            type: 'column'
        },
        title: {
            text: 'Overall quiz statistics'
        },
        yAxis: {
            allowDecimals: true,
            title: {
                text: 'Points'
            }
        },
        tooltip: {
            formatter: function () {
                return '<b>' + this.series.name + '</b><br/>' +
                    this.point.y + ' ' + this.point.name.toLowerCase();
            }
        }
    });
});