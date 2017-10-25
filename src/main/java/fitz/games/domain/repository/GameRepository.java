package fitz.games.domain.repository;

import org.springframework.data.repository.CrudRepository;

import fitz.games.domain.Game;


public interface GameRepository extends CrudRepository<Game, Long> {	
	
}
