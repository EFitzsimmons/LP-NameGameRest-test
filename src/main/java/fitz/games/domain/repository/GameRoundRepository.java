package fitz.games.domain.repository;

import org.springframework.data.repository.CrudRepository;

import fitz.games.domain.GameRound;


public interface GameRoundRepository extends CrudRepository<GameRound, Long> {	
	
}
