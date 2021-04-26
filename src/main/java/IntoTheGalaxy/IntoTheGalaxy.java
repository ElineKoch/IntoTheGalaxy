package IntoTheGalaxy;

import java.util.*;

import nl.han.ica.oopg.dashboard.Dashboard;
import nl.han.ica.oopg.engine.GameEngine;
import nl.han.ica.oopg.view.View;
import nl.han.ica.oopg.objects.Sprite;
import nl.han.ica.oopg.tile.TileType;
import nl.han.ica.oopg.tile.TileMap;
import nl.han.ica.oopg.sound.Sound;
//import TutorialWorld.tiles.FloorTile;

@SuppressWarnings("serial")
public class IntoTheGalaxy extends GameEngine {
//    private Player player;
//    private Disc disc1;
//    private Disc disc2;
//    private DiscSpawner discSpawner1;
	 	Dashboard gameDashboard;
    private Sound bleep;
		Fighter fighter;
		ArrayList<Alien> alienList;
		FighterRocket rocketF;
		AlienRocket rocketA;
		
		int numButterflies;
		int numBees;
		int numBosses;
		int time;
    
    // Deze regel maakt het makkelijker om te refereren naar je plaatjes.
    public static String MEDIA_URL = "src/main/java/IntoTheGalaxy/media/";
    
    public static void main(String[] args) {
        IntoTheGalaxy tw = new IntoTheGalaxy();
        tw.runSketch();
    }

    @Override
    public void setupGame() {
        int worldWidth = 800;
        int worldHeight = 600;
        
       
        time = 0;
        bleep = new Sound(this, MEDIA_URL.concat("bleep.wav"));
        numButterflies = 20;
        numBees = 20;
        numBosses = 3;
        // uiteraard kan je het toevoegen van
        // nieuwe game objects misschien het beste
        // in een aparte methode doen
        // i.p.v. de update zo groot te maken.
//        player = new Player(this, jump);
//        addGameObject(player, 0, 0);
//        
//        disc1 = new Disc(this, jump);
//        addGameObject(disc1, 400, 400);
//        
//        disc2 = new Disc(this, jump);
//        addGameObject(disc2, 600, 400);
//        
//        discSpawner1 = new DiscSpawner(this, 1, jump);
        fighter = new Fighter(this);
        addGameObject(fighter, worldWidth / 2, worldHeight * 7 / 8);
        
        alienList = new ArrayList<Alien>();
        
        for (int i = 0; i < numBosses; i++) {
        	Alien boss = new Boss(this, bleep,  i + 1, i / 10);
        	alienList.add(boss);
        }
        
        for (int i = 0; i < numButterflies; i++) {
        	Alien butterfly = new Butterfly(this, bleep,  i % 10 + 2, i / 10 + 1);
        	alienList.add(butterfly);
        }
        
        for (int i = 0; i < numBees; i++) {
        	Alien bee = new Bee(this, bleep,  i % 10 + 2  , i / 10 + 3);
        	alienList.add(bee);
        }
        
        for (Alien alien : alienList) {
        	addGameObject(alien, alien.getX(), alien.getY());
        }
        
//        rocketF = new FighterRocket(worldWidth / 2, worldHeight / 2, 4, 16);
//        addGameObject(rocketF, rocketF.getX(), rocketF.getY());
//        rocketA = new AlienRocket(worldWidth / 4, worldHeight / 4, 4, 16);
//        addGameObject(rocketA, rocketA.getX(), rocketA.getY());
        
        gameDashboard = new Dashboard(worldWidth * 5 / 6, 0, worldWidth / 6 + 1, worldHeight);
        gameDashboard.setBackground(0, 0, 0);
        addDashboard(gameDashboard);
        
        View view = new View(worldWidth, worldHeight);

        setView(view);
        size(worldWidth, worldHeight);
        
        view.setBackground(loadImage(IntoTheGalaxy.MEDIA_URL.concat("SpaceBackground.png")));
//        initializeTileMap();
        
        
        
    }
    
//    private void initializeTileMap() {
//      // Load Sprites
//      Sprite floorSprite = new Sprite(TutorialWorld.MEDIA_URL.concat("platformPack_tile001.png"));
//      // Create tile types with the right Tile class and sprite
//      TileType<FloorTile> floorTileType = new TileType<>(FloorTile.class, floorSprite);
//
//      TileType[] tileTypes = {floorTileType};
//      int tileSize = 128;
//      int tilesMap[][] = {
//              {-1, -1, -1, -1, -1, -1, -1, },
//              {-1, -1, -1, -1, -1, -1, -1, },
//              {-1, -1, -1, -1, -1, -1, -1, },
//              {-1, -1, -1, -1, -1, -1, -1, },
//              {0, 0, 0, 0, 0, 0, 0, },
//              {-1, -1, -1, -1, -1, -1, -1, },
//              {-1, -1, -1, -1, -1, -1, -1, },
//      };
//      tileMap = new TileMap(tileSize, tileTypes, tilesMap);
//    }
//    
    @Override
    public void update() {
    	time++;
    	
    	for (Alien alien : alienList) {
    		alien.makeDive();
      }
    }
}