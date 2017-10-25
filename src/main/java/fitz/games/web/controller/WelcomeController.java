package fitz.games.web.controller;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import fitz.games.domain.Player;
import fitz.games.domain.repository.PlayerRepository;

@RestController
@RequestMapping("/")
public class WelcomeController {
    
    @Autowired
    private PlayerRepository playerRepository;
    
    //Do some auth here?  Should this do anything
    //public String greeting(@RequestParam(value="name", required=false, defaultValue="NoNameSet")
	//String name, Model model) {
    @GetMapping()
    public Player getRoot(@RequestParam(value="name", defaultValue="NoName")
    		String name)  {
    	
    	
    	//TODO get player?
    	List<Player> playersWithName = playerRepository.findByName(name);
    	Player player;
    
    	if(playersWithName.size() > 0) {
    		player = playersWithName.get(0);
    		//TODO Temp temp
    		System.out.println("Player Found: " + player.toString());
    		return player;
    	} else {
    		System.out.println("Player Not Found with name: " + name);
    		//player = new Player();
    		//player.setName(name);
    		return null;
    	}
    }
}