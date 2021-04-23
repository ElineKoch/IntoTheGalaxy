package IntoTheGalaxy;

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
//    private Sound jump;
    
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
        //jump = new Sound(this, MEDIA_URL.concat("jump_03.wav"));
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
    	
    }

}