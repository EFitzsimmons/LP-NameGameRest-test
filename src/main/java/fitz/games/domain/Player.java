package fitz.games.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Player {

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(nullable = false)
	private String name;
	
	@Column(unique = true)
	private String email;
	
	@Column(unique = true)
	private Long phoneNumber;
	
	//@ManyToOne(fetch=FetchType.LAZY)
	@OneToOne
	private Team currentTeam;
	
	//@OneToMany
	//private List<Game> gamesHistory;
	
	
	@Override
	public String toString() {
		return "Player [id=" + id + ", name=" + name + ", email=" + email + ", phoneNumber=" + phoneNumber
				+ ", currentTeam=" + currentTeam + "]";
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Long getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(Long phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public Team getCurrentTeam() {
		return currentTeam;
	}

	public void setCurrentTeam(Team currentTeam) {
		this.currentTeam = currentTeam;
	}

//	public List<Game> getGamesHistory() {
//		return gamesHistory;
//	}
//
//	public void setGamesHistory(List<Game> gamesHistory) {
//		this.gamesHistory = gamesHistory;
//	}
}
