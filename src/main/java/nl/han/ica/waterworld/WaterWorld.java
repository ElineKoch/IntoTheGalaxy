package nl.han.ica.waterworld;

import com.sun.prism.image.ViewPort;
import nl.han.ica.OOPDProcessingEngineHAN.Dashboard.Dashboard;
import nl.han.ica.OOPDProcessingEngineHAN.Engine.GameEngine;
import nl.han.ica.OOPDProcessingEngineHAN.Objects.Sprite;
import nl.han.ica.OOPDProcessingEngineHAN.Persistence.FilePersistence;
import nl.han.ica.OOPDProcessingEngineHAN.Persistence.IPersistence;
import nl.han.ica.OOPDProcessingEngineHAN.Sound.Sound;
import nl.han.ica.OOPDProcessingEngineHAN.Tile.TileMap;
import nl.han.ica.OOPDProcessingEngineHAN.Tile.TileType;
import nl.han.ica.OOPDProcessingEngineHAN.View.EdgeFollowingViewport;
import nl.han.ica.OOPDProcessingEngineHAN.View.View;
import nl.han.ica.waterworld.tiles.BoardsTile;
import processing.core.PApplet;

/**
 * @author Ralph Niels
 */
@SuppressWarnings("serial")
public class WaterWorld extends GameEngine {

    private Sound backgroundSound;
    private Sound bubblePopSound;
    private TextObject dashboardText;
    private BubbleSpawner bubbleSpawner;
    private int bubblesPopped;
    private IPersistence persistence;
    private Player player;


    public static void main(String[] args) {
        PApplet.main(new String[]{"nl.han.ica.waterworld.WaterWorld"});
    }

    /**
     * In deze methode worden de voor het spel
     * noodzakelijke zaken geïnitialiseerd
     */
    @Override
    public void setupGame() {

        int worldWidth=1204;
        int worldHeight=903;

        initializeSound();
        createDashboard(worldWidth, 100);
        initializeTileMap();
        initializePersistence();

        createObjects();
        createBubbleSpawner();

        createViewWithoutViewport(worldWidth, worldHeight);
        //createViewWithViewport(worldWidth, worldHeight, 800, 800, 1.1f);

    }

    /**
     * Creeërt de view zonder viewport
     * @param screenWidth Breedte van het scherm
     * @param screenHeight Hoogte van het scherm
     */
    private void createViewWithoutViewport(int screenWidth, int screenHeight) {
        View view = new View(screenWidth,screenHeight);
        view.setBackground(loadImage("src/main/java/nl/han/ica/waterworld/media/background.jpg"));

        setView(view);
        size(screenWidth, screenHeight);
    }

    /**
     * Creeërt de view met viewport
     * @param worldWidth Totale breedte van de wereld
     * @param worldHeight Totale hoogte van de wereld
     * @param screenWidth Breedte van het scherm
     * @param screenHeight Hoogte van het scherm
     * @param zoomFactor Factor waarmee wordt ingezoomd
     */
    private void createViewWithViewport(int worldWidth,int worldHeight,int screenWidth,int screenHeight,float zoomFactor) {
        EdgeFollowingViewport viewPort = new EdgeFollowingViewport(player, (int)Math.ceil(screenWidth/zoomFactor),(int)Math.ceil(screenHeight/zoomFactor),0,0);
        viewPort.setTolerance(50, 50, 50, 50);
        View view = new View(viewPort, worldWidth,worldHeight);
        setView(view);
        size(screenWidth, screenHeight);
        view.setBackground(loadImage("src/main/java/nl/han/ica/waterworld/media/background.jpg"));
    }

    /**
     * Initialiseert geluid
     */
    private void initializeSound() {
        backgroundSound = new Sound(this, "src/main/java/nl/han/ica/waterworld/media/Waterworld.mp3");
        backgroundSound.loop(-1);
        bubblePopSound = new Sound(this, "src/main/java/nl/han/ica/waterworld/media/pop.mp3");
    }


    /**
     * Maakt de spelobjecten aan
     */
    private void createObjects() {
        player = new Player(this);
        addGameObject(player, 100, 100);
        Swordfish sf=new Swordfish(this);
        addGameObject(sf,200,200);
    }

    /**
     * Maakt de spawner voor de bellen aan
     */
    public void createBubbleSpawner() {
        bubbleSpawner=new BubbleSpawner(this,bubblePopSound,2);
    }

    /**
     * Maakt het dashboard aan
     * @param dashboardWidth Gewenste breedte van dashboard
     * @param dashboardHeight Gewenste hoogte van dashboard
     */
    private void createDashboard(int dashboardWidth,int dashboardHeight) {
        Dashboard dashboard = new Dashboard(0,0, dashboardWidth, dashboardHeight);
        dashboardText=new TextObject("");
        dashboard.addGameObject(dashboardText);
        addDashboard(dashboard);
    }

    /**
     * Initialiseert de opslag van de bellenteller
     * en laadt indien mogelijk de eerder opgeslagen
     * waarde
     */
    private void initializePersistence() {
        persistence = new FilePersistence("main/java/nl/han/ica/waterworld/media/bubblesPopped.txt");
        if (persistence.fileExists()) {
            bubblesPopped = Integer.parseInt(persistence.loadDataString());
            refreshDasboardText();
        }
    }

    /** 
     * Initialiseert de tilemap
     */
    private void initializeTileMap() {
        /* TILES */
        Sprite boardsSprite = new Sprite("src/main/java/nl/han/ica/waterworld/media/boards.jpg");
        TileType<BoardsTile> boardTileType = new TileType<>(BoardsTile.class, boardsSprite);

        TileType[] tileTypes = { boardTileType };
        int tileSize=50;
        int tilesMap[][]={
                {-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},
                {-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},
                {-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},
                {-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},
                {-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},
                {-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},
                {-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},
                {-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},
                {-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},
                {-1,-1,-1, 0, 0, 0, 0,-1,0 , 0},
                {-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},
                {-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},
                {-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},
                {-1,-1,-1,-1,-1,-1,-1,-1,-1,-1}
        };
        tileMap = new TileMap(tileSize, tileTypes, tilesMap);
    }

    @Override
    public void update() {
    }

    /**
     * Vernieuwt het dashboard
     */
    private void refreshDasboardText() {
        dashboardText.setText("Bubbles popped: "+bubblesPopped);
    }

    /**
     * Verhoogt de teller voor het aantal
     * geknapte bellen met 1
     */
    public void increaseBubblesPopped() {
        bubblesPopped++;
        persistence.saveData(Integer.toString(bubblesPopped));
        refreshDasboardText();
    }
}
