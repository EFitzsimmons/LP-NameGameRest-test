package fitz.games.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;


@Entity
public class Game {

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@ManyToOne(cascade = CascadeType.ALL)
	private Player owner;

	//@JoinColumn()
	@ManyToMany(cascade = CascadeType.ALL)
	private List<Player> playersInGame;

	
	@ManyToOne(cascade = CascadeType.ALL)
	private Team team1;
	
	@ManyToOne(cascade = CascadeType.ALL)
	private Team team2;
	
	@ManyToMany(cascade = CascadeType.ALL)
	private List<GameRound> rounds;

	@ManyToOne
	private GameRound currentRound;
	
	@ManyToMany(cascade = CascadeType.ALL)
	private List<GameName> namesInTheGame;
	
	@ManyToMany(cascade = CascadeType.ALL)
	private List<GameName> namesLeftInCurrentRound;
	
	@OneToOne
	private Team currentTeamsTurn;
	
	private int team1Score = 0;
	
	private int team2Score = 0;


	public Game() {
		this.rounds = new ArrayList<GameRound>();
		this.playersInGame = new ArrayList<Player>();
		
		//TODO change this?
		this.team1 = new Team();
		team1.setName("Team 1");
		this.team2 = new Team();
		team2.setName("Team 2");
	}
	
	
	public int getTeam1Score() {
		return team1Score;
	}


	public int getTeam2Score() {
		return team2Score;
	}


	public void teamGotNames(Team team, Iterable<GameName> gameNames) {
		int addToScore = 0;		
		
		for(GameName gn : gameNames) {
			addToScore++;
			this.getNamesLeftInCurrentRound().remove(gn);
		}
		
		if(team == team1) {
			team1Score += addToScore;
		} else {
			team2Score += addToScore;
		}

	}
	
	
	//TODO set current game round with Round
	//TODO set game rounds with Round.  create the Game Rounds here.
	
	public List<Player> getPlayersInGame() {
		return playersInGame;
	}

	public void setPlayersInGame(List<Player> playersInGame) {
		this.playersInGame = playersInGame;
	}
	
	/**Adds a player to the players in the game
	 * If teams exist, the player will be added to the smaller team.
	 * 
	 * @param player
	 */
	public void addPlayerToGame(Player player) {
		if(! playersInGame.contains(player)) {
			playersInGame.add(player);
			
			// If teams are created already
			if(team2.getPlayers().size() > 0) {
				if(team1.getPlayers().size() < team2.getPlayers().size()) {
					team1.addPlayer(player);
				} else {
					team2.addPlayer(player);
				}
			
			}
		}
	}

	public GameRound getCurrentRound() {
		return currentRound;
	}

	public void setCurrentRound(GameRound currentRound) {
		this.currentRound = currentRound;
	}

	public List<GameName> getNamesLeftInCurrentRound() {
		return namesLeftInCurrentRound;
	}

	public void setNamesLeftInCurrentRound(List<GameName> namesLeftInCurrentRound) {
		this.namesLeftInCurrentRound = namesLeftInCurrentRound;
	}

	public Player getOwner() {
		return owner;
	}

	public void setOwner(Player owner) {
		this.owner = owner;
		this.addPlayerToGame(owner);
	}

	public List<GameName> getNamesInTheGame() {
		return namesInTheGame;
	}

	public void setNamesInTheGame(List<GameName> namesInTheGame) {
		this.namesInTheGame = namesInTheGame;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Team getTeam1() {
		return team1;
	}

	public void setTeam1(Team team1) {
		this.team1 = team1;
	}

	public Team getTeam2() {
		return team2;
	}

	public void setTeam2(Team team2) {
		this.team2 = team2;
	}

	public List<GameRound> getRounds() {
		return rounds;
	}

	public void setRounds(List<GameRound> rounds) {
		this.rounds = rounds;
		this.currentRound = rounds.get(0);
		for(GameRound r : rounds) {
			r.setGame(this);
		}
	}

	public Team getCurrentTeamsTurn() {
		return currentTeamsTurn;
	}

	public void setCurrentTeamsTurn(Team currentTeamsTurn) {
		this.currentTeamsTurn = currentTeamsTurn;
	}

	public void addNamesToTheGame(Iterable<GameName> gameNames) {
		if (namesInTheGame == null) namesInTheGame = new ArrayList<GameName>();
		for(GameName gameName: gameNames) {
			namesInTheGame.add(gameName);
		}	
	}
	
	
	
	@Override
	public String toString() {
		String roundsString = new String();
		for(GameRound round : rounds) {
			roundsString += round.toString();
		}
		String pInGame = new String();
		for(Player p : this.playersInGame) {
			pInGame += p.getName() + "  ";
		}
		//return "Game [id=" + id + ", owner=" + owner.getName() + ", team1=" + team1 + ", team2=" + team2
		return "##  Game [id=" + id + ", owner=" + owner.getName()
			+ "\n##  Players in the game: " + pInGame
			+ "\n##  TEAMS: " + team1 + ", " + team2
			+ "\n#   Team1 score = " + team1Score + ",  Team2 score = " + team2Score
			+ "\n##  Rounds=" + roundsString
			+ "\n##  Current Round Name=" + currentRound.getRound().getName()
			+ "\n##  namesInTheGame=" + namesInTheGame
			+ "\n##  namesLeftInCurrentRound=" + namesLeftInCurrentRound 
			+ "\n##  currentTeamsTurn=" + currentTeamsTurn + "]";
	}


	public void initialize() {
		// TODO Auto-generated method stub
		this.currentTeamsTurn = team1;
		this.namesLeftInCurrentRound = this.namesInTheGame;
	}
	
}
