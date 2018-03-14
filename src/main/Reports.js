function Reports(){
}

Reports.prototype={
    showChart: function(totalReport){
    var reportPage = this;
    var reportData =[];
    for(var i=0;i<totalReport.length;i++){
        reportData.push({
        y: totalReport[i].total_balance,
        label: "Date: "+totalReport[i].date+" TotalBalance: "
        });
    }
        var chart = new CanvasJS.Chart("pieChartContainer", {
        	animationEnabled: true,
        	title: {
        		text: "Report of an Employee"
        	},
        	data: [{
        		type: "pie",
        		startAngle: 240,
        		/*yValueFormatString: "##0.00\"%\"",*/
        		indexLabel: "{label} {y}",
        		click: function(e){
                   /* reportPage.showBarChart(totalReport[i]);*/
        		},
        		dataPoints: reportData
        	}]
        });
        chart.render();
         reportPage.showBarChart(totalReport);
    },

    getChartDetails: function(empName){

        var reportPage = this;
        $.ajax({
                                       type: "GET",
                                       url: "api/getTotalReportForSpecifiedEmployee/"+empName,
                                       async: false,
                                       contentType: "application/json",
                                       success: function(data) {
                                           alert("Report"+data);
                                             reportPage.showChart(data);
                                           return true;
                                       },
                                       error: function(xhr) {
                                           console.log(xhr);

                                       }
                                   });

        },

        showBarChart: function(totalReport){
        alert("click event triggered");
		 var reportOfEarning =[];
            for(var i=0;i<totalReport.length;i++){
                reportOfEarning.push({
                y: totalReport[i].total_earnings,
                label: "Date: "+totalReport[i].date+" TotalBalance: "
                });
            }

             var reportOfWithdrawal =[];
                        for(var i=0;i<totalReport.length;i++){
                            reportOfWithdrawal.push({
                            y: totalReport[i].total_withdrawal,
                            label: "Date: "+totalReport[i].date
                            });
                        }
        var chart = new CanvasJS.Chart("barChartContainer", {
                	animationEnabled: true,
                		axisY: {
                    		title: "Total Earnings",
                    		titleFontColor: "#4F81BC",
                    		lineColor: "#4F81BC",
                    		labelFontColor: "#4F81BC",
                    		tickColor: "#4F81BC"
                    	},
                    	axisY2: {
                    		title: "Total Withdrawal",
                    		titleFontColor: "#C0504E",
                    		lineColor: "#C0504E",
                    		labelFontColor: "#C0504E",
                    		tickColor: "#C0504E"
                    	},
                    	toolTip: {
                    		shared: true
                    	},
                    	legend: {
                    		cursor:"pointer",
                    		/*itemclick: toggleDataSeries*/
                    	},
                    	data: [{
                    		type: "column",
                    		name: "Total Earning",
                    		legendText: "Total Earning",
                    		showInLegend: true,
                    		dataPoints:reportOfEarning
                    	},
                    	{
                    		type: "column",
                    		name: "Total Withdrawal",
                    		legendText: "Total Withdrawal",
                    		axisYType: "secondary",
                    		showInLegend: true,
                    		dataPoints:reportOfWithdrawal
                    	}]
                    });
                    chart.render();
        }




};