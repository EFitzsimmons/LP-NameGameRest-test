var sharedDataService = angular.module('sharedDataService', []);

sharedDataService.factory('sharedDS', function () {
	
	var currentPlayer = {
		id : -1,
		name : ''
	};

	var getPlayer = function() {
		return this.currentPlayer;
	}
	var setPlayer = function(player) {
		this.currentPlayer = player;
	}
	
	
	var currentGame = {
		id : -1,
		playersInGame : []
	};
	var getGame = function() {
		return this.currentGame;
	}
	var setGame = function(game) {
		console.log(game);
		this.currentGame = game;
	}
	
	
	return {
		getPlayer: getPlayer,
		setPlayer: setPlayer,
		
		getGame: getGame,
		setGame: setGame
	};
});