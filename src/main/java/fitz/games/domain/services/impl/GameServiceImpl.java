package fitz.games.domain.services.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fitz.games.domain.Game;
import fitz.games.domain.GameRound;
import fitz.games.domain.Player;
import fitz.games.domain.Round;
import fitz.games.domain.repository.GameNameRepository;
import fitz.games.domain.repository.GameRepository;
import fitz.games.domain.repository.GameRoundRepository;
import fitz.games.domain.repository.RoundRepository;
import fitz.games.domain.services.GameService;
import fitz.games.utils.Utils;


@Service
public class GameServiceImpl implements GameService {
	
	@Autowired
	GameRepository gameRepository;
	
	@Autowired
	RoundRepository roundRepository;
	
	@Autowired
	GameRoundRepository gameRoundRepository;
	
/*	@Autowired
	TeamRepository teamRepository;
*/	
	@Autowired
	GameNameRepository gameNameRepository;
	
	//Default game rounds
	private ArrayList<Round> defaultGameRounds;
	
	
	//TODO after data is put in the database make postconstruct
	//TODO@PostConstruct
	public void initializeDefaultGameRounds() {
		defaultGameRounds = new ArrayList<Round>();
		
		//all clues round
		defaultGameRounds.add(roundRepository.findOne(new Long(1)));
		//One word clues round
		defaultGameRounds.add(roundRepository.findOne(new Long(2)));
		//Charades round
		defaultGameRounds.add(roundRepository.findOne(new Long(3)));
	}

	
	//TODO change to by owner name or phone?
	@Override
	public List<Game> findByOwner(Player player) {
		
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	@Transactional
	public Game createStandardGame(Player owner) {
		Game game = new Game();
		game.setOwner(owner);
		
		setDefaultGameRounds(game);
				
		//TODO temp use all game names
		game.addNamesToTheGame(gameNameRepository.findAll());
		
		return game;
	}
	
	@Override
	@Transactional
	public Game joinGame(Long gameId, Player player) {
		Game game = gameRepository.findOne(gameId);
    	if(game != null) {
    		game.addPlayerToGame(player);
    	} else {
    		//TODO Game not found exception??  what to return
    		System.out.println("Game not found in joinGame() with id: " + gameId);
    		return null;
    	}
    	
    	//gameRepository.save(game);
    	

    	//TODO remove?
//    	System.out.println("\n\n\n joinGame function \n"
//    			+ game.toString());
//
//    	System.out.println("\n should equal joinGame function \n"
//    			+ gameRepository.findOne(gameId).toString());
    	return game;
	}
	

	@Override
	public Game getGameById(String gameId) {
		if(Utils.isValidPosNumber(gameId)) {
			return getGameById(new Long(gameId));
		} else {
			return null;
		}
	}
	
	@Override
	public Game getGameById(Long gameId) {
		return gameRepository.findOne(gameId);
	}
	
	
	@Override
	@Transactional
	public void randomizeTeams(Game game) {
		List<Player> players = game.getPlayersInGame();
		
		if(players != null) {
			//go through players and randomize them
			Collections.shuffle(players);
			game.getTeam1().getPlayers().clear();
			game.getTeam2().getPlayers().clear();
			
			//Add team 2 first because they may be going second, so if there is
			//an uneven amount of people they will have more
			boolean team2Turn = true;
			for(Player p : players) {
				if(team2Turn) {
					game.getTeam2().addPlayer(p);
					team2Turn = false;
				} else {
					game.getTeam1().addPlayer(p);
					team2Turn = true;
				}
			}
			
			gameRepository.save(game);
		}
	}
	
	public void startGame(Game game) {
		game.initialize();
	}

	// Creates the game rounds for the given game and round for a default game
	// Sets the games current round to the first round
	private void setDefaultGameRounds(Game game) {
		GameRound allCluesRound = new GameRound();
		allCluesRound.setGame(game);
		allCluesRound.setRound(defaultGameRounds.get(0));
		gameRoundRepository.save(allCluesRound);
		
		GameRound oneWordCluesRound = new GameRound();
		oneWordCluesRound.setRound(defaultGameRounds.get(1));
		gameRoundRepository.save(oneWordCluesRound);
		
		GameRound charradesRound = new GameRound();
		charradesRound.setRound(defaultGameRounds.get(2));
		gameRoundRepository.save(charradesRound);
		

		// set game rounds to default rounds
		ArrayList<GameRound> gameRounds = new ArrayList<GameRound>();
		gameRounds.add(allCluesRound);
		gameRounds.add(oneWordCluesRound);
		gameRounds.add(charradesRound);
		game.setRounds(gameRounds);
	}

}