var gameServices = angular.module('gameServices', ['ngResource']);

gameServices.factory('gameRepo', function($http) {
	
	var game = {
		id : 0,
		players : []
	};
	
	var getGameById = function (id) {
		var url = 'http://localhost:8888/game/' + id;
		return $http.get(url);
	}
	
	// creates a player with the given name and returns the new player
	var createGame = function(playerId) {
		var url = 'http://localhost:8888/game/create/?ownerId=' + playerId;
		
		return $http.post(url);
	}
	
	return {
		getGameById:     getGameById,
		createGame:     createGame
	};
});