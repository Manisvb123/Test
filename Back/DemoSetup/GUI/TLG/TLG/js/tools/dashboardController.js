// create the controller and inject Angular's $scope
tlgApp.controller('dashboardController',['$scope','internationalizationService','Constant.labels','$http', function($scope,internationalizationService,Constantlabels,$http) {

// var chart = c3.generate({
//     point: {
//         focus: {
//             expand: {
//                 enabled: false
//             }
//         },
//         r: 16
//     },
//     data: {
//         xs: {
//             setosa: 'setosa_x',
//             versicolor: 'versicolor_x',
//         },
//         // iris data from R
//         columns: [
//             ["setosa_x", 3.5, 3.0, 3.2, 3.1, 3.6, 3.9, 3.4, 3.4],
//             ["versicolor_x", 3.2, 3.2, 3.1, 2.3, 2.8, 2.8, 3.3, 2.4],
//             ["setosa", 0.2, 0.2, 0.2, 0.2, 0.2, 0.4, 0.3, 0.2],
//             ["versicolor", 1.4, 1.5, 1.5, 1.3, 1.5, 1.3, 1.6, 1.0],
//         ],
//         type: 'scatter'
//     },
// });


// var labels = [
//     ['AA', 'BB', 'CC', 'DD', 'EE', 'FF', 'GG', 'HH'],
//     ['ZA', 'ZB', 'ZC', 'ZD', 'ZE', 'ZF', 'ZG', 'ZH']
// ];
// // series
// var series = chart.internal.main
//                 .selectAll('.' + c3.chart.internal.fn.CLASS.circles)._parents;;
// // text layers
// var texts = chart.internal.main
//                 .selectAll('.' + c3.chart.internal.fn.CLASS.chartTexts)
//                 .selectAll('.' + c3.chart.internal.fn.CLASS.chartText)._parents;
// series.forEach(function (series, i) {
//     var points = d3.select(series).selectAll('.' + c3.chart.internal.fn.CLASS.circle)._groups[0];
//     points.forEach(function (point, j) {
//         d3.select(texts[i])
//             .append('text')
//             .attr('text-anchor', 'middle')
//             .attr('dy', '0.3em')
//             .attr('x', d3.select(point).attr('cx'))
//             .attr('y', d3.select(point).attr('cy'))
//             .text(labels[i][j])
//     })
// });

/*  d3.select("#chart svg g")
      .append("text")
      .text("label in the green region").style("fill", "green")
      .attr("transform","translate(" + (chart.internal.x(4))  + ",20)")
      .style("text-anchor","middle"); */
      
//  d3.select("#chart svg g")
//   .append("text")
//   .text("Greatest Concerns").style("fill", "red")
//   .attr("transform","translate(" + (chart.internal.x(2.4)) + ",20)")
//   .style("text-anchor","middle");

// /*  chart.regions.add([{axis: 'y', start: 0.2, end: 1.3, class: 'c3-region-r'},{axis: 'x', start: 3, class: 'c3-region-gr'}]); */

// d3.select("#chart svg g").append("polygon")       
//     .style("fill", "orange")    
//     .style("fill-opacity", "0.1") 
//     .attr("points", "0,0, 0,150, 1250,0");  // x,y points 


// d3.select("#chart svg g").append("polygon")
//     .style("fill", "none")  
//     .style("fill", "blue")    
//     .style("fill-opacity", "0.1") 
//     .attr("points", "0,150, 0,267, 200,267, 1250,150, 1250,0");  // x,y points 

// $scope.getdata=function(){
//   $http.get('pages/mockdata/graph_data.json').then(function(response) {
//     $scope.datalist = response.data;
//     $scope.robust=["robust"];
//     $scope.x_axisdata=["x"];
//     $scope.resilient=["resilient"];
//     $scope.y_axisdata=["y"];
//     $scope.rooted=["rooted"];
//     $scope.z_axisdata=["z"];
//     $scope.labels=[];
//     $scope.labeldata=[];
//     $scope.columnsdata=[];
//     angular.forEach($scope.datalist.robust, function(options, optionsindex){
//       $scope.robust.push(options[0]);
//       $scope.x_axisdata.push(options[1]);
//       $scope.labeldata.push(options[2])
//     });
//        angular.forEach($scope.datalist.resilient, function(options, optionsindex){
//         $scope.resilient.push(options[0]);
//       $scope.y_axisdata.push(options[1]);
//       $scope.labeldata.push(options[2])
//     });
//           angular.forEach($scope.datalist.rooted, function(options, optionsindex){
//         $scope.rooted.push(options[0]);
//       $scope.z_axisdata.push(options[1]);
//       $scope.labeldata.push(options[2])
//     });
//     $scope.columnsdata.push($scope.robust);
//     $scope.columnsdata.push($scope.x_axisdata);
//        $scope.columnsdata.push($scope.resilient);
//     $scope.columnsdata.push($scope.y_axisdata);
//        $scope.columnsdata.push($scope.rooted);
//     $scope.columnsdata.push($scope.z_axisdata);
//     $scope.labels.push($scope.labeldata);
//     var chart = c3.generate({
//        point: {
//         focus: {
//             expand: {
//                 enabled: false
//             }
//         },
//         r: 10
//     },
//       data: {
//         xs: {
//           robust: 'x',
//           resilient: 'y',
//           rooted: 'z'
//         },
//         columns:$scope.columnsdata,
//         type: 'scatter'
//       },
//       axis: {
//         x: {
//           label: 'Strategy is robust, resilient, and rooted',
//           tick: {
//             fit: false
//           }
//         },
//         y: {
//           label: 'Lack of alignment amongst respondents'
//         }
//       }
//     });
//     // series
// var series = chart.internal.main
//                 .selectAll('.' + c3.chart.internal.fn.CLASS.circles)._parents;;
// // text layers
// var texts = chart.internal.main
//                 .selectAll('.' + c3.chart.internal.fn.CLASS.chartTexts)
//                 .selectAll('.' + c3.chart.internal.fn.CLASS.chartText)._parents;
// series.forEach(function (series, i) {
//     var points = d3.select(series).selectAll('.' + c3.chart.internal.fn.CLASS.circle)._groups[0];
//     points.forEach(function (point, j) {
//         d3.select(texts[i])
//             .append('text')
//             .attr('text-anchor', 'middle')
//             .attr('dy', '0.3em')
//             .attr('x', d3.select(point).attr('cx'))
//             .attr('y', d3.select(point).attr('cy'))
//             .text($scope.labels[i][j])
//     })
//   });
//  });



// }

$scope.getdata=function(){
  $http.get('pages/mockdata/SSTInsight.json').then(function(response) {
    $scope.xs={};
    $scope.x_axislabel=response.data.axes.xAxisLabel
    $scope.title=response.data.title;
    $scope.y_axislabel=response.data.axes.yAxisLabel
    $scope.datalist = response.data;
    $scope.indicators=[]
    angular.forEach($scope.datalist.dataPoints, function(point, pointindex){
      if($scope.indicators.length===0){
        $scope.indicators.push(point.indicator)
      }
      $scope.isIndicatorFound=false;
      angular.forEach( $scope.indicators, function(indicator, indicatorIndex){
        if(indicator===point.indicator){
          $scope.isIndicatorFound=true;
        }
      })
      if($scope.isIndicatorFound===false){
        $scope.indicators.push(point.indicator)
      }
    });
       angular.forEach( $scope.indicators, function(indicator, indicatorIndex){
       $scope.xs[indicator]=indicator+'_y'
      })
       $scope.columnsdata=[];
       $scope.labeldata=[];
       angular.forEach( $scope.indicators, function(indicator, indicatorIndex){
        $scope.x_axisdata=[];
        $scope.y_axisdata=[];
        angular.forEach($scope.datalist.dataPoints, function(point, pointindex){
         if(indicator===point.indicator){
          if($scope.x_axisdata.length===0){
            var label=indicator+'_y'
            $scope.x_axisdata.push(indicator);
            $scope.y_axisdata.push(label);
            $scope.y_axisdata.push(point.mean)
            $scope.x_axisdata.push(point.stdDev)
            $scope.labeldata.push(point.questionId)
          }else{
           $scope.y_axisdata.push(point.mean)
            $scope.x_axisdata.push(point.stdDev)
            $scope.labeldata.push(point.questionId)
          }
        }
      });
        $scope.columnsdata.push($scope.x_axisdata)
        $scope.columnsdata.push($scope.y_axisdata)
      });
    // $scope.robust=["robust"];
//     $scope.x_axisdata=["x"];
//     $scope.resilient=["resilient"];
//     $scope.y_axisdata=["y"];
//     $scope.rooted=["rooted"];
//     $scope.z_axisdata=["z"];
    $scope.labels=[];
//     $scope.labeldata=[];
//     $scope.columnsdata=[];
//     angular.forEach($scope.datalist.robust, function(options, optionsindex){
//       $scope.robust.push(options[0]);
//       $scope.x_axisdata.push(options[1]);
//       $scope.labeldata.push(options[2])
//     });
//        angular.forEach($scope.datalist.resilient, function(options, optionsindex){
//         $scope.resilient.push(options[0]);
//       $scope.y_axisdata.push(options[1]);
//       $scope.labeldata.push(options[2])
//     });
//           angular.forEach($scope.datalist.rooted, function(options, optionsindex){
//         $scope.rooted.push(options[0]);
//       $scope.z_axisdata.push(options[1]);
//       $scope.labeldata.push(options[2])
//     });
//     $scope.columnsdata.push($scope.robust);
//     $scope.columnsdata.push($scope.x_axisdata);
//        $scope.columnsdata.push($scope.resilient);
//     $scope.columnsdata.push($scope.y_axisdata);
//        $scope.columnsdata.push($scope.rooted);
//     $scope.columnsdata.push($scope.z_axisdata);
    $scope.labels.push($scope.labeldata);
    var chart = c3.generate({
      size: {
        height: 520
    },
       point: {
        focus: {
            expand: {
                enabled: false
            }
        },
        r: 13
    },
      data: {
        xs: $scope.xs,
        columns:$scope.columnsdata,
        type: 'scatter'
      },
      axis: {
        x: {
          label:$scope.x_axislabel,
          tick: {
            fit: false
          }
        },
        y: {
          label:$scope.y_axislabel
        }
      },
      onresize: function () {
              $('.c3-shapes.c3-circles text').remove();
           }
    });
    // series
var series = chart.internal.main
                .selectAll('.' + c3.chart.internal.fn.CLASS.circles)._parents;;
// text layers
var texts = chart.internal.main
                .selectAll('.' + c3.chart.internal.fn.CLASS.chartTexts)
                .selectAll('.' + c3.chart.internal.fn.CLASS.chartText)._parents;
series.forEach(function (series, i) {
    var points = d3.select(series).selectAll('.' + c3.chart.internal.fn.CLASS.circle)._groups[0];
    points.forEach(function (point, j) {
        d3.select(texts[i])
            .append('text')
            .attr('text-anchor', 'middle')
            .attr('dy', '0.3em')
            .attr('x', d3.select(point).attr('cx'))
            .attr('y', d3.select(point).attr('cy'))
            .text($scope.labels[i][j])
    })
  });
 });



}
$scope.getdata();
        var gauges = [];
      
      function createGauge(name, label, min, max)
      {
        var config = 
        {
          size: 120,
          label: label,
          min: undefined != min ? min : 0,
          max: undefined != max ? max : 100,
          minorTicks: 5
        }
        
        var range = config.max - config.min;
        config.yellowZones = [{ from: config.min + range*0.75, to: config.min + range*0.9 }];
        config.redZones = [{ from: config.min + range*0.9, to: config.max }];
        
        gauges[name] = new Gauge(name + "GaugeContainer", config);
        gauges[name].render();
      }
      
      function createGauges()
      {
        createGauge("memory", "Memory");
        createGauge("cpu", "CPU");
        createGauge("network", "Network");
        //createGauge("test", "Test", -50, 50 );
      }
      
      function updateGauges()
      {
        for (var key in gauges)
        {
          var value = getRandomValue(gauges[key])
          gauges[key].redraw(value);
        }
      }
      
      function getRandomValue(gauge)
      {
        var overflow = 0; //10;
        return gauge.config.min - overflow + (gauge.config.max - gauge.config.min + overflow*2) *  Math.random();
      }
      
      $scope.initialize=function()
      {
        createGauges();
        setInterval(updateGauges, 5000);
      }
      $scope.initialize();
}]);


// var chart = c3.generate({
//     point: {
//         focus: {
//             expand: {
//                 enabled: false
//             }
//         },
//         r: 16
//     },
//     data: {
//         xs: {
//             setosa: 'setosa_x',
//             versicolor: 'versicolor_x',
//         },
//         // iris data from R
//         columns: [
//             ["setosa_x", 3.5, 3.0, 3.2, 3.1, 3.6, 3.9, 3.4, 3.4],
//             ["versicolor_x", 3.2, 3.2, 3.1, 2.3, 2.8, 2.8, 3.3, 2.4],
//             ["setosa", 0.2, 0.2, 0.2, 0.2, 0.2, 0.4, 0.3, 0.2],
//             ["versicolor", 1.4, 1.5, 1.5, 1.3, 1.5, 1.3, 1.6, 1.0],
//         ],
//         type: 'scatter'
//     },
// });


// var labels = [
//     ['AA', 'BB', 'CC', 'DD', 'EE', 'FF', 'GG', 'HH'],
//     ['ZA', 'ZB', 'ZC', 'ZD', 'ZE', 'ZF', 'ZG', 'ZH']
// ];
// // series
// var series = chart.internal.main
//                 .selectAll('.' + c3.chart.internal.fn.CLASS.circles)[0];
// // text layers
// var texts = chart.internal.main
//                 .selectAll('.' + c3.chart.internal.fn.CLASS.chartTexts)
//                 .selectAll('.' + c3.chart.internal.fn.CLASS.chartText)[0]
// series.forEach(function (series, i) {
//     var points = d3.select(series).selectAll('.' + c3.chart.internal.fn.CLASS.circle)[0]
//     points.forEach(function (point, j) {
//         d3.select(texts[i])
//             .append('text')
//             .attr('text-anchor', 'middle')
//             .attr('dy', '0.3em')
//             .attr('x', d3.select(point).attr('cx'))
//             .attr('y', d3.select(point).attr('cy'))
//             .text(labels[i][j])
//     })
// });
