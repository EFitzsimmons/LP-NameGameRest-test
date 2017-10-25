package fitz.games;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

import fitz.games.domain.Game;
import fitz.games.domain.GameName;
import fitz.games.domain.Player;
import fitz.games.domain.Round;
import fitz.games.domain.repository.GameNameRepository;
import fitz.games.domain.repository.GameRepository;
import fitz.games.domain.repository.PlayerRepository;
import fitz.games.domain.repository.RoundRepository;
import fitz.games.domain.services.GameService;
import fitz.games.domain.services.impl.GameServiceImpl;



@SpringBootApplication
public class LPTNameGame {
	

    public static void main(String[] args) {
    	
    	
        ApplicationContext ctx = SpringApplication.run(LPTNameGame.class, args);

//        System.out.println("Let's inspect the beans provided by Spring Boot:");
//
//        String[] beanNames = ctx.getBeanDefinitionNames();
//        Arrays.sort(beanNames);
//        for (String beanName : beanNames) {
//            System.out.println(beanName);
//        }
//        
//		System.out.println("HERE main 2");
    }
    
    
    @Autowired
    private GameRepository gameRepository;
    
    @Bean
    public CommandLineRunner commandLineRunner(PlayerRepository playerRepo,
    		RoundRepository roundRepo, GameService gameService,
    		GameNameRepository gameNameRepo) {
    	return args -> {

    		System.out.println("\n\nSTART -- commandLineRunner -- START\n");
    		
    		
    		//TODO pregame setup
    		//TODO create rounds
    		createRounds(roundRepo);
    		
    		//TODO TEMP REMOVE AFTER UPDATED DB
    		GameServiceImpl gsi = (GameServiceImpl) gameService;
    		gsi.initializeDefaultGameRounds();
    		//TODO create names
    		createGameNames(gameNameRepo);
    		//TODO TEMP REMOVE AFTER UPDATED DB
    		
    		Player player1 = new Player();
    		player1.setName("Eric");
    		player1.setEmail("eric@LPTNameGame.com");
    		
//    		System.out.println("Find Player");
//    		System.out.println("Total Players: " + playerRepo.count());
//    		System.out.println(playerRepo.findByName("Eric").toString());
//    		System.out.println("Find Player done");
    		
    		
    		Game testGame = gameService.createStandardGame(player1);
    		
    		addPlayer(testGame, playerRepo, "Roger");
    		addPlayer(testGame, playerRepo, "Gina");
    		addPlayer(testGame, playerRepo, "James");
    		addPlayer(testGame, playerRepo, "Rachel");
    		addPlayer(testGame, playerRepo, "Jennifer");
    		addPlayer(testGame, playerRepo, "Tony");
    		

//    		System.out.println("\n\n\n ---------" 
//    				+ playerRepo.findAll().toString() + "\n\n");
    		
    		
    		//Make Random teams
    		gameService.randomizeTeams(testGame);
    		
    		//Sets the current Round, names left in the game, and team turn
    		gameService.startGame(testGame);
    		
    		    		
//    		System.out.println("\n " + testGame.toString());
    		System.out.println("\nEND --- commandLineRunner -- End \n\n");
    	};
    }

    private void addPlayer(Game game, PlayerRepository playerRepo, String name) {
		Player p = new Player();
		p.setName(name);
		game.addPlayerToGame(p);
	}

	//TODO
    // THIS WILL ALREADY EXIST IN THE DB
    // TEST ONLY
	private void createRounds(RoundRepository roundRepo) {
		//create and set Rounds
		Round anyClues = new Round();
		anyClues.setName("All Clues");
		anyClues.setDescription("You can use all clues and skip as much as you want in this round");
		Round oneWordClues = new Round();
		oneWordClues.setName("One Word Clues");
		oneWordClues.setDescription("You can only use one word and any skipped names are given to the other team");
		Round charades = new Round();
		charades.setName("Charades");
		charades.setDescription("You can not use sound in this round, only acting it out.  All skipped names are given to the other team");
		
		ArrayList<Round> rounds = new ArrayList<Round>();
		rounds.add(anyClues);    		
		rounds.add(oneWordClues);
		rounds.add(charades);
		
//		System.out.println(anyClues.toString());
		roundRepo.save(rounds);
//		System.out.println(anyClues.toString());
//		System.out.println(oneWordClues.toString());
//		System.out.println(charades.toString());
	}
	//END TODO
	
	
	//TODO Test only create sample names for games to use
	private void createGameNames(GameNameRepository gameNameRepo) {
		
		ArrayList<String> nameList = new ArrayList<String>();
		nameList.add("James Vanderbeek");
		nameList.add("Rachel Weiss");
		nameList.add("Anna Kendrick");
		
		nameList.add("Chris Farley");
		nameList.add("Jim Jeffries");

		nameList.add("Mike Tyson");
		nameList.add("Floyd Mayweather");

		nameList.add("Martina Hingis");
		nameList.add("Maria Sherapova");
		nameList.add("Serena Williams");
		
		nameList.add("Nolan Ryan");
		nameList.add("Ryan Sanberg");
		nameList.add("Mariano Rivera");
		nameList.add("Nomar Garciapara");
		
		nameList.add("Barak Obama");
		nameList.add("Donald Trump");
		nameList.add("Abe Lincoln");
		nameList.add("George Washington");
		
		for (String name: nameList) {
			GameName gn = new GameName();
			gn.setName(name);
			gameNameRepo.save(gn);
		}
	}
	//END TODO
}