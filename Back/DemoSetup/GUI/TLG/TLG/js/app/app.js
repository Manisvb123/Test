	
// create the module and name it
var tlgApp = angular.module('tlgApp', ['ngRoute','LocalStorageModule','ui.bootstrap']);
tlgApp.config(['$httpProvider', function($httpProvider) {
        $httpProvider.defaults.useXDomain = true;
        delete $httpProvider.defaults.headers.common['X-Requested-With'];
  }]);
// routes
tlgApp .config(function ($routeProvider,$locationProvider) {
    $locationProvider.hashPrefix('');
    $routeProvider
        // route for the about page
           .when('/login', {
            templateUrl : 'pages/login/login.html',
            controller  : 'loginController'
        })
        .when('/sst', {
            templateUrl : 'pages/tools/sst.html',
            controller  : 'sstController'
        })
         .when('/sstsecond', {
            templateUrl : 'pages/tools/sstsecond.html',
            controller  : 'sstsecondController'
        })
         .when('/brexit', {
            templateUrl : 'pages/tools/brexit.html',
            controller  : 'brexitController'
        })
          .when('/dashboard', {
            templateUrl : 'pages/tools/dashboard.html',
            controller  : 'dashboardController'
        })
           .when('/index', {
            templateUrl : 'pages/dashboard/SST/sstIndex.html',
            controller  : 'sstIndexController'
        })
 
        // route for the service page
        .when('/service', {
            templateUrl : 'services.html',
            controller  : 'serviceController'
        });
 
});
tlgApp.run(['$timeout', '$rootScope', '$window', function($timeout, $rootScope, $window,) { 
    
        var eventRegistrationMethod = window.attachEvent || window.addEventListener;
        //In Firefox and IE, attachEvent identifies only event name onbeforeunload, 
        //while chrome and safari has addEventListener which identifies event beforeunlod
        //window.attachEvent => onbeforeunload; window.addEventListener => beforeunload
        var beforeUnlodEventName = window.attachEvent ? 'onbeforeunload' : 'beforeunload'; /// make IE7, IE8 compitable

        eventRegistrationMethod(beforeUnlodEventName, function(e) { // For >=IE7, Chrome, Firefox
              if( $rootScope.formDirtyCheck ){
                 (e || window.event).returnValue = 'Are you sure to leave the page?';
                 return;
              } 
            
        });     
        
        // register listener to watch route changes
    $rootScope.$on( "$locationChangeStart", function(event, next, current) {
        var urlPaths = [];
        urlPaths = next.split('#');
        if(urlPaths[1] != '/login'){
            $rootScope.isLoginPage = true;
        }else{
             $rootScope.isLoginPage = false;
        }
// alert("ascascasclocationchange");

    });
    }])
tlgApp.controller('serviceController', function($scope) {
    $scope.info = 'Service';
});

tlgApp.controller('aboutController', function($scope) {
    $scope.info = 'Service';
});