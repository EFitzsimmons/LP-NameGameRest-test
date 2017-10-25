package fitz.games.web.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import fitz.games.domain.Game;
import fitz.games.domain.Player;
import fitz.games.domain.repository.GameRepository;
import fitz.games.domain.repository.PlayerRepository;
import fitz.games.domain.services.GameService;
import fitz.games.utils.Utils;

@RestController
@RequestMapping("/game")
public class GameController {

	@Autowired
	private GameService gameService;
	
	@Autowired
	private PlayerRepository playerRepository;

	@Autowired
	private GameRepository gameRepository;

	/** An owner uses this to create a game on the server, and get a reference back to it
	 * 
	 * @param ownerId the id of the player who wishes to create the game
	 * @return
	 */
	@PostMapping(value="/create")
	public Game createGame(@RequestParam(value="ownerId", required=true)
			Long ownerId) {
		
		Player owner = playerRepository.findOne(ownerId);
		if(owner != null) {
			return gameService.createStandardGame(owner);
		} else {
			System.out.println("INVALID OWNER ID: " + ownerId);
			return null;
		}
	}
	
	
	/**
	 * Gets the game for a given GameId
	 * 
	 * @param gameId game ID of the desired game
	 * @return instance of the game if it exists, or nothing if it doesn't
	 */
    @RequestMapping(value="")
	public Game getGameById(@RequestParam(value="gameId", required=true)
			Long gameId) {
		
		Game game = gameService.getGameById(gameId);
		
		if(game == null) {
			//TODO send error?
			System.out.println("No Game found with ID: " + gameId);
			//game = new Game();
		}
		
		return game;
	}
    
    
    /**
     * Updates a game's info
     * 
     * TODO some authentication that it is from the owner???
     * @param game
     * @return SUCCESS when it saved properly
     */
    @PutMapping(value="/")
	public String updateGame(@RequestParam(value="game", required=true)
			Game game) {
		
    	//TODO jax already checking valid game???
		Game tempGame = gameService.getGameById(game.getId());
		
		if(tempGame != null) {
			gameRepository.save(game);
			return Utils.SUCCESS;
		} else {
			//TODO send error?
			System.out.println("Invalid game to update with ID: " + game.getId());
			return "FAILED with invalid game ID";
		}
	}
    
    
    /**
     * Adds the Player with @playerId to the Game with @gameId
     * 
     * @param requestParams
     * @return "Success" on Success
     * @throws Exception
     */
    @PutMapping(value="/join")
    public String joinGame(@RequestParam Map<String,String> requestParams) {
		String playerId = requestParams.get("playerId");
		String gameId = requestParams.get("gameId");

		
		if (Utils.isValidPosNumber(playerId) && 
				Utils.isValidPosNumber(gameId)) {
			Player player = playerRepository.findOne(Long.parseLong(playerId));
			//TODO REMOVE
			System.out.println("Joining game with player: " + player.getName());
			
			if (player != null && player.getId() > 0) {
				gameService.joinGame(Long.parseLong(gameId), player);
				return Utils.SUCCESS;
			} else {
				return "Invalid player id";
			}
		} else {
			return "Invalid values for gameId or playerId.  Must be numbers  Received "
					+ playerId + " and " + gameId;
		}
    }
    
    
    
    
	/**
	 * TODO Don't think we use this in rest...
	 * TODO if we do, should I return the game?
	 * 
	 * @param gameId
	 * @return
	 */
	@PutMapping(value="/randomizeTeams")
    public String randomizeTeams(@RequestParam("gameId") String gameId) {

    	if(Utils.isValidPosNumber(gameId)) {
	    	Game game = gameService.getGameById(new Long(gameId));
	    	if(game != null & game.getId() > 0) {
	    		gameService.randomizeTeams(game);
	    		
	    		return Utils.SUCCESS;
	    	} else {
	    		return "Game not found with id: " + gameId;
	    	}
    	} else {
    		return "Invalid game id format: " + gameId;
    	}
    }
    
}