package fitz.games.domain.services;

import java.util.List;

import fitz.games.domain.Game;
import fitz.games.domain.Player;

public interface GameService {
	
	List<Game> findByOwner(Player player);
	
	public Game createStandardGame(Player owner);

	public Game getGameById(Long gameId);
	public Game getGameById(String gameId);
	
	public Game joinGame(Long gameId, Player player);
	
	public void randomizeTeams(Game game);
	
	public void startGame(Game game);
}

