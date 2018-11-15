// create the controller and inject Angular's $scope
tlgApp.controller('sstIndexController',['$scope','internationalizationService','Constant.labels','$http','$timeout', function($scope,internationalizationService,Constantlabels,$http,$timeout) {
        var gauges = [];
      
      function createGauge(name, label, min, max,value,greenZonesMin,greenZonesMax,redZonesMin,redZonesMax)
      {
        var config = 
        {
          size: 250,
          // label: label,
          min: undefined != min ? min : 0,
          max: undefined != max ? max : 100,
          minorTicks: 5
        }
        
        // var range = config.max - config.min;
        config.greenZones = [{ from: greenZonesMin, to: greenZonesMax}];
        config.redZones = [{ from: redZonesMin, to: redZonesMax}];
        // config.yellowZones = [{ from: yellowZonesMin, to: yellowZonesMax}];
        gauges[name] = new Gauge(name, config);
        gauges[name].render();
        gauges[name].redraw(value);

      }

      $scope.getdata=function(){
  $http.get('pages/mockdata/KPIIndex.json').then(function(response) {
     $scope.sstIndexData=response.data;
     $scope.title=response.data.title;
     $scope.gaugeIds=[];
      angular.forEach($scope.sstIndexData.kpiData, function(options, optionsindex){
        var data={
          "id":options.kpiName.replace(" ",""),
           "name":options.kpiName
        }
        $scope.gaugeIds.push(data);
    });
         $timeout( function(){  
             angular.forEach($scope.sstIndexData.kpiData, function(options, optionsindex){
        createGauge(options.kpiName.replace(" ",""),options.kpiName,0,100,options.kpiValue,options.kpiBenchmark,options.region2Threshold,0,options.region1Threshold)
    });
           }, 100);

 });
}

$scope.getdata();
// var gauges = [];
      
//       function createGauge(name, label, min, max)
//       {
//         var config = 
//         {
//           size: 120,
//           label: label,
//           min: undefined != min ? min : 0,
//           max: undefined != max ? max : 100,
//           minorTicks: 5
//         }
        
//         var range = config.max - config.min;
//         config.yellowZones = [{ from: config.min + range*0.75, to: config.min + range*0.9 }];
//         config.redZones = [{ from: config.min + range*0.9, to: config.max }];
        
//         gauges[name] = new Gauge(name + "GaugeContainer", config);
//         gauges[name].render();
//       }
      
//       function createGauges()
//       {
//         createGauge("memory", "Memory");
//         createGauge("cpu", "CPU");
//         createGauge("network", "Network");
//         //createGauge("test", "Test", -50, 50 );
//       }
      
//       function updateGauges()
//       {
//         for (var key in gauges)
//         {
//           var value = getRandomValue(gauges[key])
//           gauges[key].redraw(value);
//         }
//       }
      
//       function getRandomValue(gauge)
//       {
//         var overflow = 0; //10;
//         return gauge.config.min - overflow + (gauge.config.max - gauge.config.min + overflow*2) *  Math.random();
//       }
      
//       $scope.initialize=function()
//       {
//         createGauges();
//         setInterval(updateGauges, 5000);
//       }
//       $scope.initialize();
}]);

