var playGameCtrl = angular.module('playGameController', ['gameServices', 'sharedDataService']);


playGameCtrl.controller('playGameCtrl', function($scope, $location, gameRepo, sharedDS) {
	//var _this = this;
	//_this.sharedDS = sharedDS;
	
	//console.log(sharedDS.getGame().currentRound.secondsInRound);
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



