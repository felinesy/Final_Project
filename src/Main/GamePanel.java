package Main;

import data.SaveLoad;
import entity.Entity;
import entity.Player;
import environment.EnvironmentManager;
import object.Obj_Book;
import object.Obj_SpecialBook;
import object.Obj_SpecialKey;
import tile.TileManager;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;

public class GamePanel extends JPanel implements Runnable{

    //display settings
    final int originalTileSize = 16; //16x16
    final int scale = 3;
    public final int tileSize = originalTileSize * scale;

    //screen dimensions
    public final int screenWidth = tileSize * 18;
    public final int screenHeight = tileSize * 14;

    //world settings
    public final int maxWorldCol = 100;
    public final int maxWorldRow = 100;
    public final int maxMap= 5;
    public int currentMap = 0;
    public int currentMapIndex = 0;

    //game state control
    public int gameState;
    public final int titleState = 0;
    public int playState = 1;
    public final int pauseState = 2;
    public final int dialogueState = 3;
    public final int  characterState = 4;
    public final int winState = 5;
    public final int creditsState = 6;
    public final int gameOverState = 7;

    //managers
    public UI ui = new UI(this);
    public TileManager tileM = new TileManager(this);
    public KeyHandler keyH = new KeyHandler(this);
    public MouseHandler mouseH = new MouseHandler(this);
    EnvironmentManager eManager;
    Thread gameThread;
    public Player player = new Player(this, keyH);
    public CollisionChecker ch = new CollisionChecker(this);
    public AssetSetter aSetter = new AssetSetter(this);
    public QuizManager qm = new QuizManager (this);
    public EventHandler eHandler = new EventHandler(this);
    SaveLoad saveLoad = new SaveLoad(this);

    //entity
    public Entity npc[][] = new Entity[maxMap][10];
    public Entity animals[][] = new Entity[maxMap][100];
    public Entity obj[][] = new Entity[maxMap][100];
    public  int chosenCharacter = 0;
    ArrayList<Entity> entityList = new ArrayList<>();
    public Obj_SpecialBook currentSpecialBook = null;
    public HashMap<String, Obj_SpecialKey> specialBookKeyMap = new HashMap<>();
    public boolean hasGuardianKey = false;
    public boolean hasSpecialKey = false;

    //misc
    int FPS = 60;
    public long lastInteractionTime = 0;
    public Obj_Book currentBook = null;
    private Image titleGif;
    private Sound titleMusic;
    private Sound gameMusic;


    public GamePanel(){
        titleGif = new ImageIcon(getClass().getResource("/tile_assets/bgTitle8_gif.gif")).getImage();
        this.setPreferredSize(new Dimension(screenWidth, screenHeight)); // game size
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.addMouseListener(mouseH);
        this.setFocusable(true);

        setMapIndex(currentMapIndex);
        specialBookKeyMap = new HashMap<>();
        specialBookKeyMap.put("Sbook1", new Obj_SpecialKey("Skey1", this));
        specialBookKeyMap.put("Sbook2", new Obj_SpecialKey("Skey2", this));
        specialBookKeyMap.put("Sbook3", new Obj_SpecialKey("Skey3", this));
    }

    public void startGameThread(){
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {
        double drawInterval = 1000000000/FPS;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;

        while(gameThread != null){
            currentTime = System.nanoTime();
            delta += (currentTime-lastTime) / drawInterval;
            lastTime = currentTime;

            if(delta >= 1) {
                update();
                repaint();
                delta--;
            }
        }

    }

