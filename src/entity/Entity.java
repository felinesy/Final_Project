package entity;

import Main.GamePanel;
import Main.UtilityTool;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

public class Entity {
    //entity properties
    public int worldX, worldY;
    public int speed;
    public int defaultSpeed;
    GamePanel gp;
    public String name;
    public String description = "No description available";
    public int  entityType;
    public int type;
    public Entity currentLight;
    public int lightRadius;
    public final int type_light = 9;

    //sprite animation
    public BufferedImage up1, up2, down1, down2, left1, left2, right1, right2, fall;
    public String direction = "down";
    public int spriteCounter = 0;
    public int spriteNum = 1;
    public BufferedImage image, image2, image3;

    //inventory item
    public boolean stackable = false;
    public int amount = 1;

    //collision
    public boolean collision = false;
    public Rectangle solidArea = new Rectangle(8, 16, 30, 30);
    public int solidAreaDefaultX;
    public int solidAreaDefaultY;
    public boolean collisionOn = false;
    public double detectionRange = 100000.0;

    //combat
    public Rectangle attackArea = new Rectangle(0, 0, 0, 0);
    public boolean  knockBack = false;
    int knockBackCounter = 0;
    public boolean invincible = false;
    public int invincibleCounter = 0;

    //misc
    public int maxLife;
    public int life;
    public int actionLockCounter = 0;

    public Entity(GamePanel gp) {
        this.gp = gp;
    }

    public void setAction() { }

    public void update() {

        if (knockBack == true) {

            if (collisionOn == true) {
                knockBackCounter = 0;
                knockBack = false;
                speed = defaultSpeed;
            } else if (collisionOn == false) {

                switch (gp.player.direction) {
                    case "up": worldY -= speed; break;
                    case "down": worldY += speed; break;
                    case "left": worldX -= speed; break;
                    case "right": worldX += speed; break;
                }
            }
            knockBackCounter++;
            if (knockBackCounter == 3) {
                knockBackCounter = 0;
                knockBack = false;
                speed = defaultSpeed;
            }
        } else {
            if (isPlayerInRange()) {
                followPlayer();
            } else {

                setAction();  // Random movement
                collisionOn = false;

                gp.ch.checkTile(this);
                gp.ch.checkObject(this, false);
                gp.ch.checkEntity(this, gp.animals);
                gp.ch.checkEntity(this, gp.npc);

                if (!collisionOn) {
                    switch (direction) {
                        case "up": worldY -= speed; break;
                        case "down": worldY += speed; break;
                        case "left": worldX -= speed; break;
                        case "right": worldX += speed; break;
                    }
                } else {

                    Random random = new Random();
                    int i = random.nextInt(100) + 1;

                    if (i <= 25) direction = "up";
                    else if (i <= 50) direction = "down";
                    else if (i <= 75) direction = "left";
                    else direction = "right";
                }
            }

            boolean contactPlayer = gp.ch.checkPlayer(this);
            if (this.entityType == 2 && contactPlayer == true) {
                if (!gp.player.invincible) {
                    gp.player.life -= 1;
                    gp.ui.addMessage("Update(entity): Player took damage from animal");
                    gp.player.invincible = true;
                    
                    // Trigger player knockback - determine direction away from this entity
                    int dx = gp.player.worldX - worldX;
                    int dy = gp.player.worldY - worldY;
                    if (Math.abs(dx) > Math.abs(dy)) {
                        gp.player.direction = (dx > 0) ? "right" : "left";
                    } else {
                        gp.player.direction = (dy > 0) ? "down" : "up";
                    }
                    gp.player.knockBack = true;
                    gp.player.knockBackCounter = 0;
                }
            }
        }

        // Sprite animation
        spriteCounter++;
        if (spriteCounter > 12) {
            spriteNum = (spriteNum == 1) ? 2 : 1;
            spriteCounter = 0;
        }

        // Invincibility timer
        if (invincible == true) {
            invincibleCounter++;
            if (invincibleCounter > 40) {
                invincible = false;
                invincibleCounter = 0;
            }
        }
    }

    public boolean isPlayerInRange() {
        int dx = gp.player.worldX - worldX;
        int dy = gp.player.worldY - worldY;
        double distanceSquared = dx * dx + dy * dy;


        return distanceSquared < (detectionRange * detectionRange);
    }

    public void followPlayer() {
        actionLockCounter++;

        if (actionLockCounter >= 20) {
            int dx = gp.player.worldX - worldX;
            int dy = gp.player.worldY - worldY;

            if (Math.abs(dx) > Math.abs(dy)) {
                direction = (dx > 0) ? "right" : "left";
            } else {
                direction = (dy > 0) ? "down" : "up";
            }

            actionLockCounter = 0;
        }

        collisionOn = false;
        gp.ch.checkTile(this);
        int objIndex = gp.ch.checkObject(this, true);

        if (!collisionOn && objIndex == 999) {
            switch (direction) {
                case "up": worldY -= speed; break;
                case "down": worldY += speed; break;
                case "left": worldX -= speed; break;
                case "right": worldX += speed; break;
            }
        }
    }

    public void draw(Graphics2D g2) {
        int screenX = worldX - gp.player.worldX + gp.player.screenX;
        int screenY = worldY - gp.player.worldY + gp.player.screenY;
        BufferedImage image = null;

        if (worldX + gp.tileSize > gp.player.worldX - gp.player.screenX &&
                worldX - gp.tileSize < gp.player.worldX + gp.player.screenX &&
                worldY + gp.tileSize > gp.player.worldY - gp.player.screenY &&
                worldY - gp.tileSize < gp.player.worldY + gp.player.screenY) {

            switch (direction) {
                case "up": image = (spriteNum == 1) ? up1 : up2; break;
                case "down": image = (spriteNum == 1) ? down1 : down2; break;
                case "left": image = (spriteNum == 1) ? left1 : left2; break;
                case "right": image = (spriteNum == 1) ? right1 : right2; break;
            }

            if(entityType == 2 && maxLife > 0){
                g2.setColor(new Color(35, 35, 35));
                g2.fillRect(screenX, screenY - 10, gp.tileSize, 5);

                float healthPercentage = (float)life / maxLife;
                int healthWidth = (int)(gp.tileSize * healthPercentage);
                g2.setColor(new Color(255, 0, 30));
                g2.fillRect(screenX, screenY - 10, healthWidth, 5);

                g2.setColor(Color.WHITE);
                g2.drawRect(screenX, screenY - 10, gp.tileSize, 5);
            }

            if(invincible == true){
                g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.4f));
            }

            g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);

            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
        }
    }

    protected void takeDamage(int i, int i1){ }

    public BufferedImage setup(String imagePath, int width ,int height){
        UtilityTool uTool = new UtilityTool();
        BufferedImage image = null;

        try{
            image = ImageIO.read(getClass().getResourceAsStream(imagePath + ".png"));
            image = uTool.scaleImage(image, width, height);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return image;
    }

}
