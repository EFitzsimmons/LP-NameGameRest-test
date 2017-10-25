var playerCtrl = angular.module('playerController', ['playerServices', 'sharedDataService'])


playerCtrl.controller('playerCtrl', function($scope, $location, playerRepo, sharedDS) {
	$scope.Player = sharedDS.getPlayer();
	
	
	$scope.createGame = function(playerName) {
		if(playerName) {
			playerRepo.getPlayer(playerName)
			.then(function(response) {
				gotPlayerResponse(response.data);
		    }).catch(function(reason) {
		    	console.error("Catch error in myCtrl: " + reason);
		    });//TODO get error here?
		} else {
			//TODO return error no name selected?
			alert('here');
		}
	}
	
	
	gotPlayerResponse = function(aPlayer) {
		$scope.Player = aPlayer;
    	
    	// If the player exist set the shared data current Player
    	if($scope.Player.name) {
	    	sharedDS.setPlayer($scope.Player);
	    	// Successfully got player redirect to create game
	    	// TODO change the site url??
	    	$location.path("/CreateGame");
    	} else {
    		playerRepo.createPlayer($scope.playerName)
    		.then(function(response) {
    			gotPlayerResponse(response.data);
    		}).catch(function(reason) {
    			//TODO should never hit this.  Error creating player after not getting a player.
		    	console.error("Catch error (couldn't find or create player): " + reason);
    		});
    	}
	}

});
