package entity;

import Main.GamePanel;
import Main.KeyHandler;

import Main.EldenSkills;
import Main.Sound;
import object.*;

import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Player extends Entity {
    public BufferedImage attackUp1, attackUp2, attackDown1, attackDown2, attackLeft1, attackLeft2, attackRight1, attackRight2;
    private boolean isAttacking = false;
    private int attackFrame = 0;
    KeyHandler keyH;
    public final int screenX;
    public final int screenY;
    private int chosenCharacter;
    private boolean attacking = false;
    private int attackCooldown = 0;

    public boolean isFalling = false;
    public int fallCounter = 0;
    public boolean lightUpdate = false;

    private boolean trapActivated = false;
    private int fallDelayCounter = 0;
    private boolean guardianMessagePrinted = false;
    public boolean hasGuardianKey = false;

    public int mana;
    public int maxMana;
    public List<EldenSkills> skills = new ArrayList<>();
    public boolean isStealth = false;
    public float alpha = 1.0f;

    private long lastDamageTime = 0;
    private final long damageCooldown = 1000;

    public ArrayList<Entity> inventory = new ArrayList<>();
    public final int inventorySize = 20;

    int hasKey = 0;


    public Player(GamePanel gp, KeyHandler keyH) {
        super(gp);
        this.keyH = keyH;
        screenX = gp.screenWidth / 2 - (gp.tileSize / 2);
        screenY = gp.screenHeight / 2 - (gp.tileSize / 2);
        solidArea = new Rectangle(8, 16, 30, 32);

        solidAreaDefaultX = 8;
        solidAreaDefaultY = 16;

        attackArea.width = 48;
        attackArea.height = 30;

        defaultSpeed = 4;
        speed = defaultSpeed;

        setDefaultValues();
        getPlayerImage();
        hpAndMana();
        setItems();

        skills.add(new EldenSkills(1, gp));
        skills.add(new EldenSkills(2, gp));
        skills.add(new EldenSkills(3, gp));
    }

    public int getMaxLife(){ return maxLife; }

    public void useSkill(int index) {
        if (index >= 0 && index < skills.size()) {
            skills.get(index).use(this);
        } else {
            System.out.println("Invalid skill selection.");
        }
    }

    public void setItems(){
        //inventory.add(currentWeapon);
    }

    public void hpAndMana(){
        switch(chosenCharacter){
            case 0: maxLife = 10; maxMana = 50; break;
            case 1: maxLife = 10; maxMana = 60; break;
            case 2: maxLife = 10; maxMana = 50; break;
        }
        life = maxLife;
        mana = maxMana;
    }

    public void setDefaultValues() {
        worldX = gp.tileSize * 89;
        worldY = gp.tileSize * 43;
        speed = 7;
        direction = "right";
        maxLife = 10;
        life = maxLife;
        mana = maxMana;

    }

    public void getPlayerImage() {
        try {
            if (gp.chosenCharacter == 0) { // Elden
                up1 = setup("/player/Elden_up1", gp.tileSize, gp.tileSize);
                up2 = setup("/player/Elden_up2", gp.tileSize, gp.tileSize);
                down1 = setup("/player/Elden_down1", gp.tileSize, gp.tileSize);
                down2 = setup("/player/Elden_down2", gp.tileSize, gp.tileSize);
                left1 = setup("/player/Elden_left1", gp.tileSize, gp.tileSize);
                left2 = setup("/player/Elden_left2", gp.tileSize, gp.tileSize);
                right1 = setup("/player/Elden_right1", gp.tileSize, gp.tileSize);
                right2 = setup("/player/Elden_right2", gp.tileSize, gp.tileSize);

                attackLeft1 = ImageIO.read(getClass().getResourceAsStream("/player/Elden_attack_left1.png"));
                attackLeft2 = ImageIO.read(getClass().getResourceAsStream("/player/Elden_attack_left2.png"));
                attackRight1 = ImageIO.read(getClass().getResourceAsStream("/player/Elden_attack_right1.png"));
                attackRight2 = ImageIO.read(getClass().getResourceAsStream("/player/Elden_attack_right2.png"));
                attackDown1 = ImageIO.read(getClass().getResourceAsStream("/player/Elden_down1.png"));
                attackDown2 = ImageIO.read(getClass().getResourceAsStream("/player/Elden_down2.png"));
                attackUp1 = ImageIO.read(getClass().getResourceAsStream("/player/Elden_up1.png"));
                attackUp2 = ImageIO.read(getClass().getResourceAsStream("/player/Elden_up2.png"));
                fall = ImageIO.read(getClass().getResourceAsStream("/player/Elden_falling1.png"));

            } else if (gp.chosenCharacter == 1) { // Briana
                up1 = setup("/player/Briana_up1", gp.tileSize, gp.tileSize);
                up2 = setup("/player/Briana_up2", gp.tileSize, gp.tileSize);
                down1 = setup("/player/Briana_down1", gp.tileSize, gp.tileSize);
                down2 = setup("/player/Briana_down2", gp.tileSize, gp.tileSize);
                left1 = setup("/player/Briana_left1", gp.tileSize, gp.tileSize);
                left2 = setup("/player/Briana_left2", gp.tileSize, gp.tileSize);
                right1 = setup("/player/Briana_right1", gp.tileSize, gp.tileSize);
                right2 = setup("/player/Briana_right2", gp.tileSize, gp.tileSize);

                attackLeft1 = ImageIO.read(getClass().getResourceAsStream("/player/Briana_attack_left1.png"));
                attackLeft2 = ImageIO.read(getClass().getResourceAsStream("/player/Briana_attack_left2.png"));
                attackRight1 = ImageIO.read(getClass().getResourceAsStream("/player/Briana_attack_right1.png"));
                attackRight2 = ImageIO.read(getClass().getResourceAsStream("/player/Briana_attack_right2.png"));
                attackDown1 = ImageIO.read(getClass().getResourceAsStream("/player/Briana_down1.png"));
                attackDown2 = ImageIO.read(getClass().getResourceAsStream("/player/Briana_down2.png"));
                attackUp1 = ImageIO.read(getClass().getResourceAsStream("/player/Briana_up1.png"));
                attackUp2 = ImageIO.read(getClass().getResourceAsStream("/player/Briana_up2.png"));
                fall = ImageIO.read(getClass().getResourceAsStream("/player/Briana_falling1.png"));
            }
            else if (gp.chosenCharacter == 2) { // Orion
                up1 = setup("/player/Orion_up1", gp.tileSize, gp.tileSize);
                up2 = setup("/player/Orion_up2", gp.tileSize, gp.tileSize);
                down1 = setup("/player/Orion_down1", gp.tileSize, gp.tileSize);
                down2 = setup("/player/Orion_down2", gp.tileSize, gp.tileSize);
                left1 = setup("/player/Orion_left1", gp.tileSize, gp.tileSize);
                left2 = setup("/player/Orion_left2", gp.tileSize, gp.tileSize);
                right1 = setup("/player/Orion_right1", gp.tileSize, gp.tileSize);
                right2 = setup("/player/Orion_right2", gp.tileSize, gp.tileSize);

                attackLeft1 = ImageIO.read(getClass().getResourceAsStream("/player/Orion_attack_left1.png"));
                attackLeft2 = ImageIO.read(getClass().getResourceAsStream("/player/Orion_attack_left2.png"));
                attackRight1 = ImageIO.read(getClass().getResourceAsStream("/player/Orion_attack_right1.png"));
                attackRight2 = ImageIO.read(getClass().getResourceAsStream("/player/Orion_attack_right2.png"));
                attackDown1 = ImageIO.read(getClass().getResourceAsStream("/player/Orion_down1.png"));
                attackDown2 = ImageIO.read(getClass().getResourceAsStream("/player/Orion_down2.png"));
                attackUp1 = ImageIO.read(getClass().getResourceAsStream("/player/Orion_up1.png"));
                attackUp2 = ImageIO.read(getClass().getResourceAsStream("/player/Orion_up2.png"));
                fall = ImageIO.read(getClass().getResourceAsStream("/player/Orion_falling1.png"));
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("getPlayerImage() is not working");
        }
    }

    public void attack(String attackDirection) {
        Sound.getAttackSound().play();  // Play attack sound when attacking
        for (int i = 0; i < gp.npc[1].length; i++) {
            if (gp.npc[gp.currentMap][i] != null && isNpcInRange(gp.npc[gp.currentMap][i], attackDirection)) {
                gp.npc[gp.currentMap][i].takeDamage(1, i);
            }
        }
    }

    public boolean isNpcInRange(Entity npc, String attackDirection) {
        int attackRange = gp.tileSize / 2;

        int npcLeft = npc.worldX;
        int npcRight = npc.worldX + gp.tileSize;
        int npcTop = npc.worldY;
        int npcBottom = npc.worldY + gp.tileSize;

        int playerLeft = worldX;
        int playerRight = worldX + gp.tileSize;
        int playerTop = worldY;
        int playerBottom = worldY + gp.tileSize;

        boolean hit = false;

        if (attackDirection.equals("left")) {
            hit = (playerLeft - attackRange < npcRight && playerLeft > npcLeft);
        } else if (attackDirection.equals("right")) {
            hit = (playerRight + attackRange > npcLeft && playerRight < npcRight);
        }

        return hit;
    }

    @Override
    public void update() {

        if (gp.player.life <= 0 && gp.gameState != gp.gameOverState) {
            gp.gameState = gp.gameOverState;
            return;
        }
        if (hasGuardianKey && gp.gameState == gp.playState) {
            gp.gameState = gp.winState;

            hasGuardianKey = false;
        }
        if (isFalling) {
            fallAnimation();
            return;
        }
        if (gp.eHandler.checkPitTile()) {
            trapActivated = true;
        }
        if (trapActivated) {
            fallDelayCounter++;
            if (fallDelayCounter > 1) {
                startFalling();
                trapActivated = false;
                fallDelayCounter = 0;
            }
            return;
        }

        // Handle knockback
        if (knockBack == true) {
            // Apply knockback movement incrementally
            switch (direction) {
                case "up": worldY -= speed; break;
                case "down": worldY += speed; break;
                case "left": worldX -= speed; break;
                case "right": worldX += speed; break;
            }
            
            // Check for collisions after moving
            collisionOn = false;
            gp.ch.checkTile(this);
            gp.ch.checkObject(this, false);
            gp.ch.checkEntity(this, gp.npc);
            
            if (collisionOn == true) {
                // Hit an obstacle, stop knockback
                knockBackCounter = 0;
                knockBack = false;
                speed = defaultSpeed;
            }
            
            knockBackCounter++;
            if (knockBackCounter == 10) {
                knockBackCounter = 0;
                knockBack = false;
                speed = defaultSpeed;
            }
            return; // Return early during knockback to prevent normal movement
        }

        collisionOn = false;

        int objectIndex = gp.ch.checkEntity(this, gp.obj);
        if (objectIndex != 999) {
            interactWithObject(objectIndex);
            pickUpObject(objectIndex);
        }

        if (attackCooldown > 0) {
            attackCooldown--;
        }

        if (keyH.spacePressed && !isAttacking && attackCooldown == 0) {
            if (gp.chosenCharacter == 0 || gp.chosenCharacter == 1 || gp.chosenCharacter == 2) {
                if (direction.equals("left")) {
                    isAttacking = true;
                    attackFrame = 0;
                    attackCooldown = 20;
                    attack("left");
                } else if (direction.equals("right")) {
                    isAttacking = true;
                    attackFrame = 0;
                    attackCooldown = 20;
                    attack("right");
                } if (direction.equals("down")) {
                    isAttacking = true;
                    attackFrame = 0;
                    attackCooldown = 20;
                    attack("down");
                } else if (direction.equals("up")) {
                    isAttacking = true;
                    attackFrame = 0;
                    attackCooldown = 20;
                    attack("up");
                }
            }
        }

        if (isAttacking) {
            attacking();
            attackFrame++;
            if (attackFrame > 10) {
                isAttacking = false;
            }
        }

        else if (keyH.downPressed || keyH.leftPressed || keyH.upPressed || keyH.rightPressed) {
            if (keyH.upPressed) direction = "up";
            else if (keyH.downPressed) direction = "down";
            else if (keyH.leftPressed) direction = "left";
            else if (keyH.rightPressed) direction = "right";

            gp.ch.checkTile(this);
            int animalIndex = gp.ch.checkEntity(this, gp.animals);
            interactAnimals(animalIndex);

            if (!collisionOn) {
                switch (direction) {
                    case "up":
                        worldY -= speed;
                        break;
                    case "down":
                        worldY += speed;
                        break;
                    case "left":
                        worldX -= speed;
                        break;
                    case "right":
                        worldX += speed;
                        break;
                }
            }

            spriteCounter++;
            if (spriteCounter > 12) {
                spriteNum = (spriteNum == 1) ? 2 : 1;
                spriteCounter = 0;
            }
        }

        if (invincible == true) {
            invincibleCounter++;
            if (invincibleCounter > 40) {
                invincible = false;
                invincibleCounter = 0;
            }
        }
        int tileX = gp.player.worldX / gp.tileSize;
        int tileY = gp.player.worldY / gp.tileSize;

        if (tileX >= 0 && tileX < 100 && tileY >= 0 && tileY < 100) {
            int tileID = gp.tileM.mapTileNum[gp.currentMap][tileY][tileX];
        }
    }

    public void attacking(){
        spriteCounter++;

        if(spriteCounter <= 5){
            spriteNum = 1;
        } if(spriteCounter > 5 && spriteCounter <= 25){
            spriteNum = 2;

            int currentWorldX = worldX;
            int currentWorldY = worldY;
            int solidAreaWidth = solidArea.width;
            int solidAreaHeight = solidArea.height;

            switch(direction){
                case "up": worldY -= attackArea.height; break;
                case "down": worldY += attackArea.height; break;
                case "left": worldX -= attackArea.width; break;
                case "right": worldX += attackArea.width; break;
            }

            solidArea.width = attackArea.width;
            solidArea.height = attackArea.height;

            int animalIndex = gp.ch.checkEntity(this, gp.animals);
            damageAnimal(animalIndex);

            worldX = currentWorldX;
            worldY = currentWorldY;
            solidArea.width = solidAreaWidth;
            solidArea.height = solidAreaHeight;


        } if(spriteCounter > 25){
            spriteNum = 1;
            spriteCounter = 0;
            attacking = false;
        }
    }

    public void pickUpObject(int i) {
        if (i != 999) {
            String text = "";
            Entity currentObj = gp.obj[gp.currentMap][i];

            if (currentObj != null) {
                switch (currentObj.name) {
                    case "Book":
                    case "Guardian":
                    case "Special Book":
                    case "SDoor":
                    case "Open Door":
                        break;

                    case "Key":
                        boolean keyExists = false;
                        for (Entity item : inventory) {
                            if (item != null && item.name.equals("Key")) {
                                keyExists = true;
                                item.amount++;
                                hasKey++;
                                System.out.println("Key: " + item.amount);
                                break;
                            }
                        }

                        if (!keyExists) {
                            currentObj.amount = 1;
                            inventory.add(currentObj);
                            hasKey++;
                            System.out.println("Key: " + currentObj.amount);
                        }

                        gp.obj[gp.currentMap][i] = null;
                        gp.repaint();
                        text = "Got a Key!";
                        break;

                    case "Door":
                        if (hasKey > 0) {
                            boolean keyUsed = false;
                            for (Entity item : inventory) {
                                if (item != null && item.name.equals("Key") && item.amount > 0) {
                                    item.amount--;
                                    hasKey--;
                                    keyUsed = true;
                                    System.out.println("Door disappears. Keys remaining: " + hasKey);
                                    text = "Used a key to open the door!";
                                    break;
                                }
                            }

                            if (!keyUsed) {
                                System.out.println("You have no keys to open the door.");
                                text = "You have no keys to open the door!";
                            } else {
                                gp.obj[gp.currentMap][i] = null;
                                gp.repaint();
                            }
                        } else {
                            System.out.println("You need a key to open the door.");
                            text = "The door is locked!";
                        }
                        break;

                    case "Map Door":
                        gp.eHandler.checkEvent();
                        break;

                    default:
                        if (canObtainItem(currentObj)) {
                            Sound itemPickup = new Sound("/music/Item3.wav");
                            itemPickup.play();
                            text = "Got a " + currentObj.name + "!";
                            gp.obj[gp.currentMap][i] = null;
                        } else {
                            text = "You cannot carry anymore!";
                        }
                        break;
                }
                if (!text.isEmpty()) {
                    gp.ui.addMessage(text);
                }
            }
        }
    }

    public void useItem(int index) {
        if (index < 0 || index >= inventory.size()) return;

        Entity item = inventory.get(index);
        if (item == null) return;

        boolean itemUsed = false;
        String message = "";

        switch (item.name) {
            case "Health Potion":
                this.life = Math.min(this.life + 1, this.maxLife);
                message = "Used Health Potion! +1 HP";
                itemUsed = true;
                break;
            case "Mana Potion":
                this.mana = Math.min(this.mana + 15, this.maxMana);
                message = "Used Mana Potion! +15 MP";
                itemUsed = true;
                break;
            case "Key":
                boolean doorOpened = false;
                for (int i = 0; i < gp.obj[gp.currentMap].length; i++) {
                    if (gp.obj[gp.currentMap][i] != null &&
                            gp.obj[gp.currentMap][i].name.equals("Door") &&
                            isPlayerFacingObject(gp.obj[gp.currentMap][i])) {

                        if (!gp.obj[gp.currentMap][i].collision) {
                            message = "Door is already open";
                        } else {
                            gp.obj[gp.currentMap][i].collision = false;
                            gp.obj[gp.currentMap][i].image = gp.obj[gp.currentMap][6].image; // Open door image
                            message = "Used Key to open the door!";
                            itemUsed = true;
                            doorOpened = true;
                        }
                        break;
                    }
                }
                if (!doorOpened) {
                    message = "No door to use this on!";
                }
                break;
            case "Guardian Key":
                gp.playState = gp.winState;
                break;
            case "Lantern":
                if (currentLight == null) {
                    currentLight = item;
                    message = "Lantern light turned on!";
                } else if (currentLight == item) {
                    currentLight = null;
                    message = "Lantern light turned off!";
                }
                lightUpdate = true;
                break;
            default:
                //message = "Cannot use this item";
                System.out.println("Unknown item: " + item.name);
        }

        if (!message.isEmpty()) {
            gp.ui.addMessage(message);
        }
        if (itemUsed) {
            if (item.stackable && item.amount > 1) {
                item.amount--;
            } else {
                inventory.remove(index);
            }
        }
    }

    private boolean isPlayerFacingObject(Entity obj) {
        return Math.abs(obj.worldX - this.worldX) < gp.tileSize &&
                Math.abs(obj.worldY - this.worldY) < gp.tileSize;
    }

    public void setTrapActivated() {
        trapActivated = true;
        fallDelayCounter = 0;
    }

    public void startFalling() {
        isFalling = true;
        fallCounter = 0;

        int tileCenterX = ((worldX / gp.tileSize) * gp.tileSize) + (gp.tileSize / 2) - (solidArea.width / 2);
        int tileCenterY = ((worldY / gp.tileSize) * gp.tileSize) + (gp.tileSize / 2) - (solidArea.height / 2);

        worldX = tileCenterX;
        worldY = tileCenterY;

        if (gp.ui.currentDialogue == null) {
            gp.ui.currentDialogue = "You fell!";
        } else {
            gp.ui.currentDialogue = "You fell!";
        }

        gp.gameState = gp.dialogueState;
    }

    public void fallAnimation() {
        if (fallCounter < 30) {
            worldY += 2;
            fallCounter++;
        } else {
            respawnPlayer();
        }
    }

    public void respawnPlayer() {
        isFalling = false;
        worldX = gp.tileSize * 90;
        worldY = gp.tileSize * 43;
        gp.gameState = gp.playState;
    }

    public void draw(Graphics2D g2) {
        BufferedImage image = null;
        int tempScreenX = screenX;
        int tempScreenY = screenY;

        if (isFalling) {
            image = fall; // FALL SPRITE
            int fallOffset = (fallCounter * gp.tileSize) / 30;

            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha));
            g2.drawImage(image, screenX, screenY + fallOffset, gp.tileSize, gp.tileSize, null);

            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1.0f));
            return;
        }

        else if (isAttacking && (gp.chosenCharacter == 0|| gp.chosenCharacter == 1 || gp.chosenCharacter == 2)) {
            if (direction.equals("left")) {
                image = (attackFrame % 2 == 0) ? attackLeft1 : attackLeft2;
            } else if (direction.equals("right")) {
                image = (attackFrame % 2 == 0) ? attackRight1 : attackRight2;
            } else if (direction.equals("down")) {
                image = (attackFrame % 2 == 0) ? attackDown1 : attackDown2;
            } else if (direction.equals("up")) {
                image = (attackFrame % 2 == 0) ? attackUp1 : attackUp2;
            }
        }

        else {
            switch (direction) { //edit lang niya ni atong original
                case "up":
                    if(attacking == false){
                        if(spriteNum == 1) image = up1;
                        if(spriteNum == 2) image = up2;

                    } if(attacking == true) {
                    tempScreenY = screenY - gp.tileSize;
                    if(spriteNum == 1) image = up1;
                    if(spriteNum == 2) image = up2;
                } break;
                case "down":
                    if(attacking == false){
                        if(spriteNum == 1) image = down1;
                        if(spriteNum == 2) image = down2;
                    } if(attacking == true) {
                    if(spriteNum == 1) image = attackDown1;
                    if(spriteNum == 2) image = attackDown2;
                }
                    break;
                case "left":
                    if(attacking == false){
                        if(spriteNum == 1) image = left1;
                        if(spriteNum == 2) image = left2;
                    } if(attacking == true) {
                    tempScreenX = screenX - gp.tileSize;
                    if(spriteNum == 1) image = attackLeft1;
                    if(spriteNum == 2) image = attackLeft1;
                }
                    break;
                case "right":
                    if(attacking == false){
                        if(spriteNum == 1) image = right1;
                        if(spriteNum == 2) image = right2;
                    } if(attacking == true) {
                    if(spriteNum == 1) image = attackRight1;
                    if(spriteNum == 2) image = attackRight1;
                }
                    break;
            }
        }

        if(invincible == true){
            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.3f));
        }
        g2.drawImage(image, tempScreenX, tempScreenY, gp.tileSize, gp.tileSize, null);
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
        //drawInventory(g2);

    }

    public void interactAnimals(int i) {
        if (i != 999) {

            if (isCollidingWithAnimal(gp.animals[gp.currentMap][i])) {
                takeDamage(1);
                gp.gameState = gp.playState;
            }
            if(invincible == false){
                takeDamage(1);
                gp.ui.addMessage("Damage taken: " + 1);
                invincible = true;
            }
        }
    }

    public void damageAnimal(int i) {
        if (i != 999 && gp.animals[gp.currentMap][i] != null) {
            knockBack(gp.animals[gp.currentMap][i]);
            if (!gp.animals[gp.currentMap][i].invincible) {
                gp.animals[gp.currentMap][i].life -= 1;
                gp.ui.addMessage("HIT!! Animal HP: " + gp.animals[gp.currentMap][i].life);

                if (gp.animals[gp.currentMap][i].life <= 0) {
                    Obj_Key key = new Obj_Key(gp);
                    key.worldX = gp.animals[gp.currentMap][i].worldX;
                    key.worldY = gp.animals[gp.currentMap][i].worldY;

                    for (int j = 0; j < gp.obj[1].length; j++) {
                        if (gp.obj[gp.currentMap][j] == null) {
                            gp.obj[gp.currentMap][j] = key;
                            gp.ui.addMessage("Key dropped at: (" + key.worldX + ", " + key.worldY + ")");
                            break;
                        }
                    }
                    gp.animals[gp.currentMap][i] = null;
                } else {
                    gp.animals[gp.currentMap][i].invincible = true;
                }
            }
        }
    }

    public void knockBack(Entity entity){
        entity.direction = direction;
        entity.speed += 10;
        entity.knockBack = true;
    }

    private boolean isCollidingWithAnimal(Entity animal) {

        int dx = animal.worldX - worldX;
        int dy = animal.worldY - worldY;
        double distance = Math.sqrt(dx * dx + dy * dy);

        int collisionDistance = gp.tileSize / 2;
        return distance < collisionDistance;
    }

    public void takeDamage(int damage) {

        if (invincible || isStealth) {
            return; // Skip damage
        }

        if (life <= 0) return;  // Already dead

        long currentTime = System.currentTimeMillis();
        if (currentTime - lastDamageTime >= damageCooldown) {
            life -= damage;
            lastDamageTime = currentTime;
            gp.ui.drawPlayerLife();

            if (life <= 0) {
                gp.gameState = gp.gameOverState;
            } else {
                gp.ui.addMessage("Player took " + damage + " damage! HP: " + life);
                alpha = 0.3f;
            }
        }
    }

    public void interactWithObject(int i) {
        if (gp.obj[gp.currentMap][i] != null) {
            if (System.currentTimeMillis() - gp.lastInteractionTime < 5000) {
                return;
            }

            if (gp.obj[gp.currentMap][i] instanceof Obj_SpecialDoor) {
                if (gp.hasGuardianKey) {
                    System.out.println("Guardian Key used! Entering the door...");
                } else {
                    gp.ui.currentDialogue = "You need the Guardian Key to enter this portal.";
                    gp.gameState = gp.dialogueState;
                    gp.repaint();
                }
                return;
            }
            if (gp.obj[gp.currentMap][i] instanceof Obj_MapDoor) {
                if (gp.currentMap == 0) {
                    if (gp.qm.specialKeyCount >= 1) {
                        System.out.println("Special Key validated. Entering Map 2...");
                    } else {
                        gp.ui.currentDialogue = "This door requires 1 Special Key to enter.";
                        gp.gameState = gp.dialogueState;
                        gp.repaint();
                    }
                }
                else if (gp.currentMap == 1) {
                    if (gp.qm.specialKeyCount >= 2) {
                        System.out.println("Special Keys validated. Entering Map 3...");
                    } else {
                        gp.ui.currentDialogue = "This door requires 2 Special Keys to enter.";
                        gp.gameState = gp.dialogueState;
                        gp.repaint();
                    }
                }
                return;
            }

            if (gp.obj[gp.currentMap][i] instanceof Obj_SpecialBook) {
                System.out.println("Special book detected! Starting special quiz.");
                gp.currentSpecialBook = (Obj_SpecialBook) gp.obj[gp.currentMap][i];
                gp.qm.startSpecialQuiz(gp.currentSpecialBook);
                return;
            }
            if (gp.obj[gp.currentMap][i] instanceof Obj_Book) {
                System.out.println("Regular book detected! Starting normal quiz.");
                gp.currentBook = (Obj_Book) gp.obj[gp.currentMap][i];
                gp.qm.startQuiz(gp.currentBook);
                return;
            }
            if (gp.obj[gp.currentMap][i] instanceof Obj_Guardian) {
                if (gp.qm.specialKeyCount >= 3) {
                    gp.qm.startGuardianQuiz();
                    gp.qm.specialKeyCount -= 3;
                    guardianMessagePrinted = false;
                } else {
                    if (!guardianMessagePrinted) {
                        System.out.println("You need at least 3 special keys to start the Guardian Quiz!");
                        guardianMessagePrinted = true;
                    }
                }
                return;
            }

        }
    }

    public int searchItemInventory(String itemName){
        int itemIndex = 999;

        for(int i = 0; i < inventory.size(); i++) {
            if(inventory.get(i).name.equals(itemName)){
                itemIndex = i;
                break;
            }
        }
        return itemIndex;
    }

    public boolean canObtainItem(Entity item) {
        boolean canObtain = false;

        if (item.stackable) {
            int index = searchItemInventory(item.name);

            if (index != 999) {
                inventory.get(index).amount++;
                canObtain = true;
            } else {
                if (inventory.size() != inventorySize) {
                    inventory.add(item);
                    canObtain = true;
                }
            }
        } else {
            if (inventory.size() != inventorySize) {
                inventory.add(item);
                canObtain = true;
            }
        }

        return canObtain;
    }

}



