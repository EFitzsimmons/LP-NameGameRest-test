var gameCtrl = angular.module('gameController', ['gameServices', 'sharedDataService']);


gameCtrl.controller('createGameCtrl', function($scope, $location, gameRepo, sharedDS) {
//	$scope.player = sharedDS.getPlayer();
//	player = $scope.player;
//	
//	if(!player) {
//		$location.path("/");
//	}
//	
//	//create game here
//	gameRepo.createGame(player.id)
//	.then(function(response) {
//		//$scope.game = response.data;
//		sharedDS.setGame(response.data);
//		
//		//redirect to manage newly created game
//		$location.path("/ManageGame");
//	}).catch(function(reason) {
//		//TODO
//	    console.error("Catch error in createGameController: " + reason);
//		//error getting game
//		$location.path("/");
//	});	
});


gameCtrl.controller('manageGameCtrl', function($scope, $location, gameRepo, sharedDS) {
//	$scope.player = sharedDS.getPlayer();
//	player = $scope.player;
//	$scope.game = sharedDS.getGame();
//	game = $scope.game;
//	
//	// if player or game not found go back to main page
//	if(! player || ! game) {
//		$location.path("/");
//	}
//	
//	
//	// adds a player to the game
//	$scope.addPlayer = function(playerName) {
//		var tempPlayer = {};
//		tempPlayer.name = playerName;
//		game.playersInGame.push(tempPlayer);
//		$scope.playerName = "";
//	}
//	
//	
//	//TODO move this into a directive??
//	//randomize all players into 2 teams
//	$scope.randomizeTeams = function() {		
//		// default add to team 2 first
//		var isTeam1Turn = false;
//
//		// Clear team1 and team2
//		game.team1.players.splice(0);
//		game.team2.players.splice(0);
//		
//		//Randomize players in game
//		shuffleArray(game.playersInGame);
//		
//		angular.forEach(game.playersInGame, function(aPlayer, key) {
//			if(isTeam1Turn) {
//				game.team1.players.push(aPlayer);
//				isTeam1Turn = false;
//			} else {
//				game.team2.players.push(aPlayer);
//				isTeam1Turn = true;
//			}
//		});		
//	}
//	
//	
//	$scope.startGame = function() {
//		//TODO save game to server with players and set first round in progress
//		$location.path("/PlayGame");
//	}
//	
//	
//
////TODO make this a generic service?
//// Credit -> Fisher–Yates shuffle algorithm
//var shuffleArray = function(array) {
//	var m = array.length, t, i;
//	
//	// While there remain elements to shuffle
//	while (m) {
//		// Pick a remaining element…
//		i = Math.floor(Math.random() * m--);
//		
//		// And swap it with the current element.
//		t = array[m];
//		array[m] = array[i];
//		array[i] = t;
//	}
//	
//	return array;
//}
//	
});

/*public class Game {
	private Long id;
	private Player owner;
	private List<Player> playersInGame;
	private Team team1;
	private Team team2;
	private List<GameRound> rounds;
	private GameRound currentRound;
	private List<GameName> namesInTheGame;
	private List<GameName> namesLeftInCurrentRound;
	private Team currentTeamsTurn;
	private int team1Score = 0;
	private int team2Score = 0;
*/