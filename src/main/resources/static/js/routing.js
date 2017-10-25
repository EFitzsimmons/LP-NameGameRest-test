(function() {
	
app = angular.module("myAppRouter", ['ngRoute', 'playerController', 'gameController',
	'playGameController']);

/*
.when('/PlayGame', {
	templateUrl: 'divs/playGame.html',
	controller: 'playGameCtrl'
})*/

app.config(function($routeProvider){

    $routeProvider
    .when('/', {
        templateUrl: 'divs/welcome.html',
    	controller: 'playerCtrl'
    })
    .when('/CreateGame', {
    	templateUrl: 'divs/createGame.html',
    	controller: 'createGameCtrl'
    })
    .when('/ManageGame', {
    	templateUrl: 'divs/manageGame.html',
    	controller: 'manageGameCtrl'
    })
    .otherwise({ redirectTo: '/index.html' });
})

})();