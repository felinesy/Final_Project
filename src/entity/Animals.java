package entity;

import Main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

public class Animals extends Entity {
    private String type;

    public Animals(GamePanel gp, String type) {
        super(gp);
        this.type = type;
        entityType = 2;
        defaultSpeed = 1;
        speed = defaultSpeed;
        direction = "down";
        collisionOn = true;
        maxLife = 5;
        life = maxLife;

        solidArea.x = 18;
        solidArea.y = 18;
        solidArea.width = 42;
        solidArea.height = 30;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;

        getImage();
    }

    public void takeDamage(int damage, int i) {
        life -= damage;
        gp.ui.addMessage("Animal took " + damage + " damage!");

        if (life <= 0) {
            gp.ui.addMessage("Animal defeated!");
            gp.animals[gp.currentMap][i] = null;
        }
    }

    public void getImage() {
        try {
            up1 = ImageIO.read(getClass().getResourceAsStream("/animals/" + type + "_up1.png"));
            up2 = ImageIO.read(getClass().getResourceAsStream("/animals/" + type + "_up2.png"));
            down1 = ImageIO.read(getClass().getResourceAsStream("/animals/" + type + "_down1.png"));
            down2 = ImageIO.read(getClass().getResourceAsStream("/animals/" + type + "_down2.png"));
            left1 = ImageIO.read(getClass().getResourceAsStream("/animals/" + type + "_left1.png"));
            left2 = ImageIO.read(getClass().getResourceAsStream("/animals/" + type + "_left2.png"));
            right1 = ImageIO.read(getClass().getResourceAsStream("/animals/" + type + "_right1.png"));
            right2 = ImageIO.read(getClass().getResourceAsStream("/animals/" + type + "_right2.png"));
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error loading images for " + type);
        }
    }

    public void setAction() {
        actionLockCounter++;
        if (actionLockCounter == 120) {
            Random random = new Random();
            int i = random.nextInt(100) + 1;
            String newDirection;
            if (i <= 25) newDirection = "up";
            else if (i <= 50) newDirection = "down";
            else if (i <= 75) newDirection = "left";
            else newDirection = "right";

            collisionOn = false;
            gp.ch.checkTile(this);
            int objIndex = gp.ch.checkObject(this, true);

            if (!collisionOn && objIndex == 999) {
                direction = newDirection;
            }
            actionLockCounter = 0;
        }
    }

    @Override
    public void draw(Graphics2D g2) {
        int screenX = worldX - gp.player.worldX + gp.player.screenX;
        int screenY = worldY - gp.player.worldY + gp.player.screenY;
        BufferedImage image = null;

        if (worldX + gp.tileSize > gp.player.worldX - gp.player.screenX &&
                worldX - gp.tileSize < gp.player.worldX + gp.player.screenX &&
                worldY + gp.tileSize > gp.player.worldY - gp.player.screenY &&
                worldY - gp.tileSize < gp.player.worldY + gp.player.screenY) {

            if(entityType == 2 && maxLife > 0){
                g2.setColor(Color.lightGray);
                g2.fillRect(screenX, screenY - 10, gp.tileSize, 10);

                float healthPercentage = (float)life / maxLife;
                int healthWidth = (int)(gp.tileSize * healthPercentage);
                g2.setColor(new Color(255, 0, 30));
                g2.fillRect(screenX, screenY - 10, healthWidth, 10);

                g2.setColor(Color.darkGray);
                g2.drawRect(screenX, screenY - 10, gp.tileSize, 10);
            }

            switch (direction) {
                case "up": image = (spriteNum == 1) ? up1 : up2; break;
                case "down": image = (spriteNum == 1) ? down1 : down2; break;
                case "left": image = (spriteNum == 1) ? left1 : left2; break;
                case "right": image = (spriteNum == 1) ? right1 : right2; break;
            }

            if(invincible) {
                g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.4f));
            }

            g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);
            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
        }
    }
}
