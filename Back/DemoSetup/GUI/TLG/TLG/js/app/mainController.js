// create the controller and inject Angular's $scope
tlgApp.controller('mainController',['$scope','internationalizationService','Constant.labels','$rootScope', function($scope,internationalizationService,Constantlabels,$rootScope) {
    // alert("ascascascmain");
    var result = internationalizationService.checkAndSetStorage('English');
      result.then(function(resolve) {
        $scope.labels = {};
        $scope.sidebarlist = internationalizationService.get(Constantlabels.side_bar);
         // alert($scope.sidebarlist);
      }, function(reject) {
        alert('Label display failed');
      });
      $rootScope.logout = function () {
       alert("ascascascmainensd");
     }
     
}]);