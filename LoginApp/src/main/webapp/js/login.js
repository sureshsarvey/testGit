  var module = angular.module('loginApp', ['ngRoute']);

    module.config(['$routeProvider',
        function($routeProvider) {
            $routeProvider.
                    when('/login', {
                        templateUrl: 'login.html'
                    }).
                    when('/forgot', {
                        templateUrl: 'forgot.html'
                    }).
                    when('/register', {
                        templateUrl: 'register.html',
                        controller: "registerController"
                    }).
                    otherwise({
                        redirectTo: '/',
                        templateUrl: 'login.html'
                    });
        }]);
    module.controller('registerController',function($scope,$http,$rootScope){
    	$scope.registerValues = function()
    	{
    		 $scope.registervalues = {
    				   'username' : $scope.register.username,
    				   'password' : $scope.register.password,
    				   'email' : $scope.register.email
    			
    		   }
    		 var response = $http.post('loginController/registerUser',$scope.registervalues);
    		 response.success(function(data,status){
    			
    			 if(data)
    				 {
    				 $scope.register_status_success = true;
    				 $scope.register_status_failed = false;
    				 $scope.loginstatus = "Registration Success....";
    				 
    				 //clear the fields
    				 $scope.emptyValues();
    				   // location.href = '#/login';
    				 }
    			 else
    				 {
    				 $scope.register_status_failed = true;
    				 $scope.register_status_success = false;
 				     $scope.loginstatus = "Registration Failed try again.....";
    				 }
    		 });
    		 response.error(function(data, status) {
                 console.log("error");
             });
    	};
    	$scope.emptyValues = function()
    	{
    		 $scope.register.username = "";
			 $scope.register.password = "";
			 $scope.register.email = "";
			 $scope.register.confirmPassword = "";
    	};
    	  
    });
    module.controller('loginController',function($scope,$http,$rootScope){
    	$scope.loginvalidate = function()
    	{
    		if ($scope.login.username && $scope.login.password) {
    			$scope.UserCredentials = {
    					'username' : $scope.login.username,
    					'password' : $scope.login.password
    			}
    			var response = $http.post('loginController/loginUser',$scope.UserCredentials);
    			response.success(function(data,status){
    				if(data)
    				{
    					console.log("user logged in successfully");
    				}
    			});
    			response.error(function(data, status) {
    				console.log("error");
    			});
    		}
    	}
    	  
    });
  