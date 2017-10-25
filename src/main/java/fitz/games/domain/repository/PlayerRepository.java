package fitz.games.domain.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import fitz.games.domain.Player;


public interface PlayerRepository extends CrudRepository<Player, Long> {
	
	
	List<Player> findByName(String name);

}
