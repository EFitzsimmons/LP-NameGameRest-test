package fitz.games.domain;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
public class GameRound {
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@JsonIgnore
	@ManyToOne(cascade = CascadeType.ALL)
	private Game game;
	
	//Cascade not necessary.  No changing Round from GameRound
	@OneToOne//(cascade = CascadeType.ALL)
	private Round round;
	
	//How long people have to guess in this round
	@Column
	private int secondsInRound = 60;
	
	@Override
	public String toString() {
		//TODO
		String gameId = new String();
		String roundName = new String();
		if(game != null) {
			gameId += game.getId();
		} else {
			gameId = " No Game Set";
		}
		if(round != null) {
			roundName = round.getName();
		} else {
			roundName = "No Round Set";
		}
		return "GameRound [id=" + id + ", gameId=" + gameId + ", round=" + roundName
				+ ", secondsInRound=" + secondsInRound + "]";
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Game getGame() {
		return game;
	}

	public void setGame(Game game) {
		this.game = game;
	}

	public int getSecondsInRound() {
		return secondsInRound;
	}

	public void setSecondsInRound(int secondsInRound) {
		this.secondsInRound = secondsInRound;
	}

	public Round getRound() {
		return round;
	}

	public void setRound(Round round) {
		this.round = round;
	}
	
}
