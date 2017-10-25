(function() {
	
var playerServices = angular.module('playerServices', ['ngResource']);

playerServices.factory('playerRepo', function($http) {
	
	var player = {
		id : 0,
		name : "",
		email : "",
		phoneNumber : 0
	};
	
	var getPlayer = function (pName) {
		var url = 'http://localhost:8888/?name=' + pName;
		return $http.get(url);
	}
	
	// creates a player with the given name and returns the new player
	var createPlayer = function(playerName) {
		player.id = 0;
		player.name = playerName;
		player.phoneNumber = null;
		player.email = null;

		var url = 'http://localhost:8888/player/';
		
		return $http.post(url, player);
	}
	
	return {
		//playerName: playerName,
		getPlayer:     getPlayer,
		createPlayer:     createPlayer
	};
});

})();
