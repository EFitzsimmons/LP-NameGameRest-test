package fitz.games.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import fitz.games.domain.Player;
import fitz.games.domain.repository.PlayerRepository;
import fitz.games.utils.Utils;

@RestController
@RequestMapping("/player")
public class PlayerController {
	
	@Autowired
	private PlayerRepository playerRepository;

	/**
	 * Creates a player with name, email, and phone number given
	 * Ignores current team and ID 
	 * 
	 * @param player
	 * @return
	 */
    @PostMapping()
    //public Player createPlayer(@RequestParam(value="player", required=true)
    public Player createPlayer(@RequestBody Player player) {
    	player.setId(null);
    	
    	playerRepository.save(player);
    	
    	//TODO temp temp
    	System.out.println(player.toString());
    	
    	return player;
    }
    
    
    //TODO check for valid player for ID??
    @PutMapping()
    public String updatePlayer(@RequestParam(value="player", required=true)
			Player player) {
    	//TODO check player for valid mapping
    	
    	Player validPlayer = playerRepository.findOne(player.getId());
    	
    	if(validPlayer != null & validPlayer.getId() > 0) {
    		playerRepository.save(player);
    		return Utils.SUCCESS;
    	} else {
    		return "Invalid Player";
    	}
    }

}