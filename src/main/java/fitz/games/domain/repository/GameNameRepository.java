package fitz.games.domain.repository;

import org.springframework.data.repository.CrudRepository;

import fitz.games.domain.GameName;


public interface GameNameRepository extends CrudRepository<GameName, Long> {	
	
}
