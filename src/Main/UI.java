package Main;

import entity.Entity;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

public class UI {
    GamePanel gp;
    Graphics2D g2;
    Font arial_40, arial_80B;
    Font customFont;
    public int commandNum = 0;
    public int titleScreenState = 0;
    public int tutorialState = 0;
    public  String currentDialogue = "";
    public int slotCol = 0;
    public int slotRow = 0;
    public boolean showTeleportGif = false;

    // Mana bar colors
    private final Color MANA_EMPTY = new Color(35, 35, 85);
    private final Color MANA_LOW = new Color(188, 16, 16);   // Crimson Red
    private final Color MANA_HIGH = new Color(9, 89, 179);   // Lime Green
    private final Color MANA_BORDER = Color.WHITE;
    private final Color MANA_TEXT_SHADOW = new Color(0, 0, 0, 180);

    // Mana thresholds
    private final int MANA_LOW_THRESHOLD = 25;
    private final int MANA_MAX_WARRIOR = 50;    // Elden
    private final int MANA_MAX_ROGUE = 50;      // Brianna
    private final int MANA_MAX_MAGE = 60;       // Orion

    ArrayList<String> message = new ArrayList<>();
    ArrayList<Integer> messageCounter = new ArrayList<>();

    private Image titleGif;
    private Image creditsGif;
    private  Image winGif;
    BufferedImage heart_full, heart_half, heart_blank;
    BufferedImage characterOrion1, characterOrion2, characterOrion3, characterOrion4,
            characterElden1, characterElden2, characterElden3, characterElden4,
            characterBriana1, characterBriana2, characterBriana3, characterBriana4;


    public UI(GamePanel gp){
        this.gp=gp;
        arial_40 = new Font("Arial", Font.PLAIN, 40);
        arial_80B = new Font("Arial", Font.BOLD, 80);
        titleGif = new ImageIcon(getClass().getResource("/tiles/bgTitle6_gif.gif")).getImage();
        creditsGif = new ImageIcon(getClass().getResource("/tiles/bgTitle5_gif.gif")).getImage();
        winGif = new ImageIcon(getClass().getResource("/tiles/modern_home.gif")).getImage();
        loadCustomFont();
        loadHeartImages();
    }

    private void loadCustomFont() {
        try {
            customFont = new Font("Arial", Font.PLAIN, 40); // Directly assigning Arial font
        } catch (Exception e) {
            e.printStackTrace();
            customFont = new Font("Arial", Font.PLAIN, 40); // Fallback font
        }
    }


    public void addMessage(String text){
        message.add(text);
        messageCounter.add(0);
    }

    private void loadHeartImages() {
        try {
            heart_full = ImageIO.read(getClass().getResourceAsStream("/objects/heart_full.png"));
            heart_half = ImageIO.read(getClass().getResourceAsStream("/objects/heart_half.png"));
            heart_blank = ImageIO.read(getClass().getResourceAsStream("/objects/heart_blank.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void draw(Graphics2D g2){
        this.g2 = g2;
        g2.setFont(arial_40);
        g2.setColor(Color.WHITE);

        if (gp.gameState == gp.titleState) {
            if (tutorialState == 1) {
                drawTutorialScreen();
            } else {
                drawTitleScreen();
            }
        } else if (gp.gameState == gp.playState) {
            drawPlayerLife();
            drawManaBar();
            drawMessage();
        } else if (gp.gameState == gp.pauseState) {
            drawPauseScreen();
            drawPlayerLife();
        } else if (gp.gameState == gp.dialogueState) {
            if (gp.qm.isQuestionActive) {
                gp.qm.drawQuestionScreen();
                gp.qm.drawMessageQuestion(g2);
            } else {
                drawDialogueScreen();
            }
            drawPlayerLife();
            drawManaBar();
        } else if (gp.gameState == gp.characterState) {
            drawCharacterScreen();
            drawInventory();
            drawPlayerLife();
            drawManaBar();
        }  else if (gp.gameState == gp.creditsState) {
            drawCreditsScreen();

        } if (gp.gameState == gp.winState) {
            drawWinScreen();
        }   else if (gp.gameState == gp.gameOverState) {
            drawGameOverScreen();
        }
    }

    private void drawGameOverScreen() {
        g2.setColor(new Color(0, 0, 0, 150));
        g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);

        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 80F));
        String text = "GAME OVER";
        int x = getXforCenteredText(text);
        int y = gp.screenHeight / 3;

        g2.setColor(Color.BLACK);
        g2.drawString(text, x + 4, y + 4);

        g2.setColor(Color.RED);
        g2.drawString(text, x, y);
    }

    private void drawManaBar() {
        int width = gp.tileSize * 6;
        int height = gp.tileSize/2;
        int x = gp.screenWidth - width - gp.tileSize/2;
        int y = gp.tileSize/2 + gp.tileSize/3;

        if (gp.player.maxMana == 0) {
            switch(gp.chosenCharacter) {
                case 0: // Elden
                    gp.player.maxMana = MANA_MAX_WARRIOR;
                    break;
                case 1: // Brianna
                    gp.player.maxMana = MANA_MAX_ROGUE;
                    break;
                case 2: // Orion
                    gp.player.maxMana = MANA_MAX_MAGE;
                    break;
            }
            gp.player.mana = gp.player.maxMana;
        }

        g2.setColor(MANA_EMPTY);
        g2.fillRect(x, y, width, height);

        double manaRatio = (double)gp.player.mana / gp.player.maxMana;
        int currentManaWidth = (int)(width * manaRatio);

        if (gp.player.mana < MANA_LOW_THRESHOLD) {
            g2.setColor(MANA_LOW);
        } else {
            g2.setColor(MANA_HIGH);
        }
        g2.fillRect(x, y, currentManaWidth, height);

        g2.setColor(MANA_BORDER);
        g2.setStroke(new BasicStroke(2));
        g2.drawRect(x, y, width, height);

        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 18F));
        String manaText = gp.player.mana + "/" + gp.player.maxMana;
        int textX = x + width/2 - g2.getFontMetrics().stringWidth(manaText)/2;
        int textY = y + height/2 + 6;

        g2.setColor(MANA_TEXT_SHADOW);
        g2.drawString(manaText, textX + 1, textY + 1);

        g2.setColor(MANA_BORDER);
        g2.drawString(manaText, textX, textY);
    }

    public void drawTutorialScreen() {
        GradientPaint gradient = new GradientPaint(
                0, 0, new Color(0, 0, 0, 230),
                0, gp.screenHeight, new Color(20, 15, 35, 230));
        Graphics2D g2d = (Graphics2D) g2;
        g2d.setPaint(gradient);
        g2d.fillRect(0, 0, gp.screenWidth, gp.screenHeight);

        g2.setFont(customFont.deriveFont(Font.BOLD, 40F));
        String characterName = gp.chosenCharacter == 0 ? "ELDEN" :
                gp.chosenCharacter == 1 ? "BRIANNA" : "ORION";
        String title = "WELCOME, " + characterName + "!";
        int x = getXforCenteredText(title);
        int y = gp.tileSize * 2;

        g2.setColor(new Color(255, 215, 0, 50));
        g2.drawString(title, x + 2, y + 2);
        g2.setColor(new Color(255, 215, 0, 30));
        g2.drawString(title, x + 1, y + 1);

        g2.setColor(new Color(255, 215, 0));
        g2.drawString(title, x, y);

        drawControlsSection("BASIC CONTROLS", new String[]{
                "[W][A][S][D] - Move character",
                "[P] - Pause game",
                "[C] - Toggle inventory",
                "[ENTER] - Interact/Talk",
                "[F5] - Save Load"
        }, gp.tileSize * 1, gp.tileSize * 4);

        String[] skills;
        if (gp.chosenCharacter == 0) { // Elden
            skills = new String[]{
                    "[SPACE] - Basic Attack ",
                    "[1] - Healing Bloom ",
                    "[2] - Silent Steps ",
                    "[3] - Teleport "
            };
        } else if (gp.chosenCharacter == 1) { // Brianna
            skills = new String[]{
                    "[SPACE] - Basic Attack",
                    "[1] - Hint Seeker ",
                    "[2] - Moonlit Shroud ️",
                    "[3] - Luminous Thread "
            };
        } else { // Orion
            skills = new String[]{
                    "[SPACE] - Basic Attack ",
                    "[1] - Phantom Howl ",
                    "[2] - Time Rewind ",
                    "[3] - Guardian’s Instinct "
            };
        }
        drawControlsSection("COMBAT SKILLS", skills,
                gp.screenWidth / 2 + gp.tileSize, gp.tileSize * 4);

        g2.setFont(customFont.deriveFont(Font.PLAIN, 25F));
        String hint = "Press [ENTER] to begin or [ESC] to change character";
        x = getXforCenteredText(hint);
        y = gp.screenHeight - gp.tileSize * 2;

        float alpha = (float) (0.5 + 0.5 * Math.sin(System.currentTimeMillis() * 0.003));
        g2.setColor(new Color(1f, 1f, 1f, alpha));
        g2.drawString(hint, x, y);
    }

    private void drawControlsSection(String title, String[] items, int x, int y) {
        g2.setFont(customFont.deriveFont(Font.BOLD, 40F));
        g2.setColor(new Color(255, 215, 0));
        g2.drawString(title, x, y);

        g2.setFont(customFont.deriveFont(Font.PLAIN, 30F));
        y += gp.tileSize;

        for (String item : items) {
            String[] parts = item.split(" - ");
            if (parts.length == 2) {
                g2.setColor(new Color(0, 255, 255));
                g2.drawString(parts[0], x, y);
                g2.setColor(Color.WHITE);
                int descX = x + g2.getFontMetrics().stringWidth(parts[0] + " ");
                g2.drawString("- " + parts[1], descX, y);
            } else {
                g2.setColor(Color.WHITE);
                g2.drawString(item, x, y);
            }
            y += 45;
        }
    }

    public void drawMessage(){
        int messageX = gp.tileSize;
        int messageY = gp.tileSize * 4;
        g2.setFont(g2.getFont().deriveFont(Font.TRUETYPE_FONT, 18f));

        for(int i=0; i<message.size(); i++){
            if(message.get(i) != null){
                g2.setColor(Color.white);
                g2.drawString(message.get(i), messageX, messageY);

                int counter = messageCounter.get(i) + 1;
                messageCounter.set(i, counter);
                messageY += 50;

                if(messageCounter.get(i)> 180){
                    message.remove(i);
                    messageCounter.remove(i);
                }
            }
        }
    }

    public void drawCharacterScreen(){
        final int frameX = gp.tileSize*11;
        final int frameY = gp.tileSize;
        final int frameWidth = gp.tileSize*6;
        final int frameHeight = gp.tileSize*5;

        drawSubWindow(frameX, frameY, frameWidth, frameHeight);
    }

    public void drawTitleScreen() {
        if (titleScreenState == 0) {
            if (titleGif != null) {
                g2.drawImage(titleGif, 0, 0, gp.screenWidth, gp.screenHeight, null);
            } else {
                g2.setColor(Color.BLACK);
                g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);
                System.out.println("Title GIF not loaded. Displaying black background.");
            }

            try {
                Font customFont = Font.createFont(Font.TRUETYPE_FONT, getClass().getResourceAsStream("/fonts/BuyanForest-Yz0jy.otf"));
                g2.setFont(customFont.deriveFont(Font.BOLD, 80));
            } catch (Exception e) {
                e.printStackTrace();
                g2.setFont(new Font("SansSerif", Font.BOLD, 100)); // Fallback font
            }

            String title = "The Three Trials";
            int x = getXforCenteredText(title);
            int y = gp.tileSize * 4;

            g2.setColor(Color.BLACK);
            g2.drawString(title, x + 8, y + 8);

            g2.setColor(new Color(50, 205, 50));
            g2.drawString(title, x, y);

            g2.setColor(new Color(34, 139, 34));
            g2.drawString(title, x, y);

            g2.setFont(new Font("Palatino Linotype", Font.BOLD, 36));
            String[] menuItems = {"START GAME", "LOAD GAME", "CREDITS", "QUIT"};
            y += gp.tileSize * 3;

            for (int i = 0; i < menuItems.length; i++) {
                String item = menuItems[i];
                x = getXforCenteredText(item);

                g2.setColor(Color.BLACK);
                g2.drawString(item, x + 3, y + 3);

                g2.setColor(commandNum == i ? Color.GREEN : Color.WHITE);
                g2.drawString(item, x, y);

                if (commandNum == i) {
                    g2.setColor(Color.WHITE);
                    g2.drawString("➤", x - gp.tileSize, y);
                }

                y += gp.tileSize * 2;
            }

        } else if (titleScreenState == 1) {
            selectCharacter();

        }
    }

    public void drawCreditsScreen() {
        if (creditsGif != null) {
            g2.drawImage(creditsGif, 0, 0, gp.screenWidth, gp.screenHeight, null);
        } else {
            g2.setColor(Color.BLACK);
            g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);
        }

        Font titleFont;
        try {
            Font customFont = Font.createFont(Font.TRUETYPE_FONT,
                    getClass().getResourceAsStream("/fonts/BuyanForest-Yz0jy.otf"));
            titleFont = customFont.deriveFont(Font.BOLD, 50);
        } catch (Exception e) {
            e.printStackTrace();
            titleFont = new Font("SansSerif", Font.BOLD, 50);
        }

        Font nameFont = new Font("Palatino Linotype", Font.BOLD, 28);

        String title = "CREDITS";
        int x = getXforCenteredText(title);
        int y = gp.tileSize * 2;

        g2.setFont(titleFont);
        g2.setColor(Color.DARK_GRAY);
        g2.drawString(title, x + 6, y + 6);
        g2.setColor(new Color(34, 139, 34));
        g2.drawString(title, x, y);

        y += gp.tileSize * 2;

        g2.setFont(nameFont);
        String manager = "Project Manager: Katrina S. Rubi";
        x = getXforCenteredText(manager);
        g2.setColor(Color.BLACK);
        g2.drawString(manager, x + 2, y + 2);
        g2.setColor(Color.WHITE);
        g2.drawString(manager, x, y);

        y += 60;

        String membersHeader = "Members:";
        x = getXforCenteredText(membersHeader);
        g2.setColor(Color.BLACK);
        g2.drawString(membersHeader, x + 2, y + 2);
        g2.setColor(Color.WHITE);
        g2.drawString(membersHeader, x, y);

        y += 50;

        String[] members = {
                "Zyril Wyne O. Sonsona",
                "Charles Brayden P. Sanchez",
        };

        for (String member : members) {
            x = getXforCenteredText(member);
            g2.setColor(Color.BLACK);
            g2.drawString(member, x + 2, y + 2);
            g2.setColor(Color.LIGHT_GRAY);
            g2.drawString(member, x, y);
            y += 45;
        }

        String backText = "Back";
        g2.setFont(nameFont);
        x = gp.tileSize * 2;
        y = gp.screenHeight - gp.tileSize * 2;

        g2.setColor(Color.WHITE);
        g2.drawString(backText, x + gp.tileSize, y);

        if (commandNum == 3) {
            g2.setColor(Color.GREEN);
            g2.drawString("➤", x, y);
            g2.drawString(backText, x + gp.tileSize, y);
        }
    }

    public void drawWinScreen(){
        if (titleGif != null) {
            g2.drawImage(winGif, 0, 0, gp.screenWidth, gp.screenHeight, null);
            gp.ui.addMessage("YOU HAVE YOUR WAY HOME.");
        } else {
            g2.setColor(Color.black);
            g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);
            System.out.println("Title GIF not loaded. Displaying black background.");
        }
    }

    public void drawPauseScreen() {
        g2.setColor(new Color(0, 0, 0, 150));
        g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);

        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 80F));
        String text = "PAUSED";
        int x = getXforCenteredText(text);
        int y = gp.screenHeight / 3;

        g2.setColor(Color.BLACK);
        g2.drawString(text, x + 4, y + 4);

        g2.setColor(Color.WHITE);
        g2.drawString(text, x, y);

        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 30F));
        String[] options = {"RESUME", "SAVE GAME", "QUIT GAME"};
        y = gp.screenHeight / 2;

        for (int i = 0; i < options.length; i++) {
            text = options[i];
            x = getXforCenteredText(text);

            g2.setColor(Color.white);
            if (commandNum == i) {
                g2.setColor(Color.GREEN);
                g2.drawString("➤", x - 40, y);
            }
            g2.drawString(text, x, y);
            y += 50;
        }
    }

    public void drawDialogueScreen() {
        if (currentDialogue == null) {
            currentDialogue = "";
        }

        int x = gp.tileSize * 2;
        int y = gp.tileSize * 4;
        int width = gp.screenWidth - (gp.tileSize * 4);
        int height = gp.tileSize * 3;

        drawSubWindow(x, y, width, height);

        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 20F));
        g2.setColor(Color.WHITE);
        x += gp.tileSize;
        y += gp.tileSize;

        for (String line : currentDialogue.split("\\n")) {
            g2.drawString(line, x, y);
            y += 40;
        }
    }

    public void drawSubWindow(int x, int y, int width, int height){
        Color c = new Color(0,0,0,200);
        g2.setColor(c);
        g2.fillRoundRect(x,y,width,height,35,35);

        c = new Color(255,255,255);
        g2.setColor(c);
        g2.setStroke(new BasicStroke(5));
        g2.drawRoundRect(x+5, y+5, width-10, height-10,25,25);
    }

    public int getXforCenteredText(String text){
        int length = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        int x = (gp.screenWidth - length) / 2;

        return x;
    }

    public void selectCharacter(){
        g2.setColor(Color.white);
        g2.setFont(g2.getFont().deriveFont(42f));
        String text = "Select Character";
        int x = getXforCenteredText(text);
        int y = gp.tileSize*3;
        g2.drawString(text, x, y);

        try{
            characterOrion1 = ImageIO.read(getClass().getResourceAsStream("/player/Orion_1.png"));
            characterOrion2 = ImageIO.read(getClass().getResourceAsStream("/player/Orion_2.png"));
            characterOrion3 = ImageIO.read(getClass().getResourceAsStream("/player/Orion_3.png"));
            characterOrion4 = ImageIO.read(getClass().getResourceAsStream("/player/Orion_4.png"));
            characterElden1 = ImageIO.read(getClass().getResourceAsStream("/player/Elden_1.png"));
            characterElden2 = ImageIO.read(getClass().getResourceAsStream("/player/Elden_2.png"));
            characterElden3 = ImageIO.read(getClass().getResourceAsStream("/player/Elden_3.png"));
            characterElden4 = ImageIO.read(getClass().getResourceAsStream("/player/Elden_4.png"));
            characterBriana1 = ImageIO.read(getClass().getResourceAsStream("/player/Briana_1.png"));
            characterBriana2 = ImageIO.read(getClass().getResourceAsStream("/player/Briana_2.png"));
            characterBriana3 = ImageIO.read(getClass().getResourceAsStream("/player/Briana_3.png"));
            characterBriana4 = ImageIO.read(getClass().getResourceAsStream("/player/Briana_4.png"));
        } catch(IOException e) {
            e.printStackTrace();
        }
        g2.drawImage(characterElden1, 4*gp.tileSize, 5*gp.tileSize, gp.tileSize, gp.tileSize, null);
        g2.drawImage(characterElden2, 5*gp.tileSize, 5*gp.tileSize, gp.tileSize, gp.tileSize, null);
        g2.drawImage(characterElden3, 4*gp.tileSize, 6*gp.tileSize, gp.tileSize, gp.tileSize, null);
        g2.drawImage(characterElden4, 5*gp.tileSize, 6*gp.tileSize, gp.tileSize, gp.tileSize, null);
        g2.drawImage(characterBriana1, 8*gp.tileSize, 5*gp.tileSize, gp.tileSize, gp.tileSize, null);
        g2.drawImage(characterBriana2, 9*gp.tileSize, 5*gp.tileSize, gp.tileSize, gp.tileSize, null);
        g2.drawImage(characterBriana3, 8*gp.tileSize, 6*gp.tileSize, gp.tileSize, gp.tileSize, null);
        g2.drawImage(characterBriana4, 9*gp.tileSize, 6*gp.tileSize, gp.tileSize, gp.tileSize, null);
        g2.drawImage(characterOrion1, 12*gp.tileSize, 5*gp.tileSize, gp.tileSize, gp.tileSize, null);
        g2.drawImage(characterOrion2, 13*gp.tileSize, 5*gp.tileSize, gp.tileSize, gp.tileSize, null);
        g2.drawImage(characterOrion3, 12*gp.tileSize, 6*gp.tileSize, gp.tileSize, gp.tileSize, null);
        g2.drawImage(characterOrion4, 13*gp.tileSize, 6*gp.tileSize, gp.tileSize, gp.tileSize, null);

        g2.setFont(new Font("SansSerif", Font.BOLD, 26)); // Set a clear font

        text = "E l d e n";
        g2.setColor(Color.white);
        if (commandNum == 0) {
            g2.setColor(Color.GREEN);
            g2.drawString("➤", 3 * gp.tileSize, 8 * gp.tileSize);
        }
        g2.drawString(text, 4 * gp.tileSize, 8 * gp.tileSize);


        text = "B r i a n a";
        g2.setColor(Color.white);
        if (commandNum == 1) {
            g2.setColor(Color.GREEN);
            g2.drawString("➤", 7 * gp.tileSize, 8 * gp.tileSize);
        }
        g2.drawString(text, 8 * gp.tileSize, 8 * gp.tileSize);

        text = "O r i o n";
        g2.setColor(Color.white);
        if (commandNum == 2) {
            g2.setColor(Color.GREEN);
            g2.drawString("➤", 11 * gp.tileSize, 8 * gp.tileSize);
        }
        g2.drawString(text, 12 * gp.tileSize, 8 * gp.tileSize);


        text = "Back";
        g2.setColor(Color.white);
        g2.drawString(text, 2 * gp.tileSize, 13 * gp.tileSize);
        if (commandNum == 3) {
            g2.setColor(Color.GREEN);
            g2.drawString("➤", 1 * gp.tileSize, 13 * gp.tileSize);
            g2.drawString(text, 2 * gp.tileSize, 13 * gp.tileSize);

        }
    }

    public void drawInventory() {
        final int frameX = gp.tileSize * 11;
        final int frameY = gp.tileSize;
        final int frameWidth = gp.tileSize * 6;
        final int frameHeight = gp.tileSize * 5;

        drawSubWindow(frameX, frameY, frameWidth, frameHeight);

        final int slotXstart = frameX + 20;
        final int slotYstart = frameY + 20;
        int slotX = slotXstart;
        int slotY = slotYstart;

        for(int i = 0; i < gp.player.inventory.size(); i++) {

            if(gp.player.inventory.get(i) != null) {
                g2.drawImage(gp.player.inventory.get(i).image, slotX, slotY, gp.tileSize, gp.tileSize, null);

                if(gp.player.inventory.get(i).amount > 1){
                    g2.setFont(g2.getFont().deriveFont(18f));
                    int amountX;
                    int amountY;

                    String s = "" + gp.player.inventory.get(i).amount;
                    amountX = getXforAlignToRightText(s, slotX + 44);
                    amountY = slotY + gp.tileSize;

                    g2.setColor(new Color(60,60,60));
                    g2.drawString(s, amountX, amountY);

                    g2.setColor(Color.white);
                    g2.drawString(s, amountX-3,amountY-3);
                }
            }

            slotX += gp.tileSize;

            if((i + 1) % 5 == 0) {
                slotX = slotXstart;
                slotY += gp.tileSize;
            }

            if(i >= 14) break;
        }

        int cursorX = slotXstart + (gp.tileSize * slotCol);
        int cursorY = slotYstart + (gp.tileSize * slotRow);

        g2.setColor(Color.white);
        g2.setStroke(new BasicStroke(3));
        g2.drawRoundRect(cursorX, cursorY, gp.tileSize, gp.tileSize, 10, 10);

        if(gp.player.inventory.size() > 0 &&
                slotCol + (slotRow * 5) < gp.player.inventory.size()) {
            int itemIndex = slotCol + (slotRow * 5);
            if(itemIndex < gp.player.inventory.size()) {
                Entity item = gp.player.inventory.get(itemIndex);

                int descX = frameX;
                int descY = frameY + frameHeight + 10;
                int descWidth = frameWidth;
                int descHeight = gp.tileSize * 2;

                drawSubWindow(descX, descY, descWidth, descHeight);

                g2.setColor(Color.white);
                g2.setFont(g2.getFont().deriveFont(Font.BOLD, 20F));
                g2.drawString(item.name, descX + 20, descY + 30);

                g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 16F));
                String description = item.description != null ? item.description : "No description";
                for(String line : description.split("\n")) {
                    g2.drawString(line, descX + 20, descY + 60);
                }
            }
        }
    }

    private int getXforAlignToRightText(String s, int i) {
        int length = getTextLength(s);
        return i - length;
    }

    private int getTextLength(String s) {
        return g2.getFontMetrics().stringWidth(s);
    }

    public void drawPlayerLife() {
        int x = gp.tileSize / 2;
        int y = gp.tileSize / 2;
        int i = 0;

        while (i < gp.player.maxLife / 2) {
            g2.drawImage(heart_blank, x, y, gp.tileSize, gp.tileSize, null);
            i++;
            x += gp.tileSize;
        }

        x = gp.tileSize / 2;
        i = 0;

        while (i < gp.player.life) {
            g2.drawImage(heart_half, x, y, gp.tileSize, gp.tileSize, null);
            i++;
            if (i < gp.player.life) {
                g2.drawImage(heart_full, x, y, gp.tileSize, gp.tileSize, null);
            }
            i++;
            x += gp.tileSize;
        }
    }
}

