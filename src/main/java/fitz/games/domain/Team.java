package fitz.games.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;

@Entity
public class Team {
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column
	private String name;
	
	@ManyToMany
	private List<Player> players = new ArrayList<Player>();
	
	@OneToOne
	private Player currentPlayersTurn;
	
//	//The current/most recent game of this team
//	@ManyToOne(cascade = CascadeType.ALL)
//	private Game currentGame;
//	
	//@OneToMany
	//private List<Game> gameHistory;


	// Add one player to the team
	public void addPlayer(Player player) {
		players.add(player);
	}
	
	
	@Override
	public String toString() {
		String playerNames = new String();
		
		if(players.size() > 0) {
			for (Player player : players) {
				playerNames += player.getName() + "  ";
			}
		} else { 
			playerNames = "No Players";
		}
		
		return "Team [id=" + id + ", name=" + name + ", players=" + playerNames
				+ "]";
	}




	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Player> getPlayers() {
		return players;
	}

	public void setPlayers(List<Player> players) {
		this.players = players;
	}

	public Player getCurrentPlayersTurn() {
		return currentPlayersTurn;
	}

	public void setCurrentPlayersTurn(Player currentPlayersTurn) {
		this.currentPlayersTurn = currentPlayersTurn;
	}
}
