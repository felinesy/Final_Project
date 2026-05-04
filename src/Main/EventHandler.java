package Main;

import java.awt.*;

public class EventHandler {
    GamePanel gp;
    boolean canTouchEvent = true;
    int previousEventX, previousEventY;
    EventRect eventRect[][][];

    public EventHandler(GamePanel gp) {
        this.gp = gp;
        eventRect = new EventRect[gp.maxMap][gp.maxWorldCol][gp.maxWorldRow];
        int map = 0;
        int col = 0;
        int row = 0;
        while(map < gp.maxMap && col < gp.maxWorldCol && row < gp.maxWorldRow){
            eventRect[map][col][row] = new EventRect();
            eventRect[map][col][row].x = gp.tileSize/4;  // Center within tile
            eventRect[map][col][row].y = gp.tileSize/4;
            eventRect[map][col][row].width = gp.tileSize/2;  // Make it half tile size
            eventRect[map][col][row].height = gp.tileSize/2;
            eventRect[map][col][row].eventRectDefaultX = eventRect[map][col][row].x;
            eventRect[map][col][row].eventRectDefaultY = eventRect[map][col][row].y;

            col++;
            if(col == gp.maxWorldCol){
                col=0;
                row++;

                if(row == gp.maxWorldRow){
                    row = 0;
                    map++;
                }
            }
        }
    }

    public void checkEvent() {
        if (checkPitTile()) {
            damagePit();
        }

        int xDistance = Math.abs(gp.player.worldX - previousEventX);
        int yDistance = Math.abs(gp.player.worldY - previousEventY);
        int distance = Math.max(xDistance, yDistance);

        if (distance > gp.tileSize) {
            canTouchEvent = true;
        }

        if (canTouchEvent) {

            if ( hit(0, 11, 14, "any")) {
                if (gp.qm.specialKeyCount >= 1) {
                    teleport(1, 90, 43); // Map 1 → Map 2
                    System.out.println("You used 1 Special Key to enter Map 2.");
                } else {
                    System.out.println(" You need at least 1 Special Key to enter Map 2!");
                }
            }
            //From MAP 2 to MAP 3 (Needs 2 special keys)
            else if (hit(1, 44, 84, "any")) {
                if (gp.qm.specialKeyCount >= 2) {
                    teleport(2, 90, 43); // Map 2 → Map 3
                    System.out.println("🌀 You used 2 Special Keys to enter Map 3.");
                } else {
                    System.out.println(" You need at least 2 Special Keys to enter Map 3!");
                }
            }
            // From MAP 3 to ending
//            else if (hit(2, 41, 37, "any")) {
//                    teleport(2, 90, 43); // Map 3 → Ending
//                }
            }
    }

    public void teleport(int map, int col, int row){
        gp.currentMap = map;
        gp.player.worldX = gp.tileSize * col;
        gp.player.worldY = gp.tileSize * row;
        previousEventX = gp.player.worldX;
        previousEventY = gp.player.worldY;
        canTouchEvent = false;
    }

    public boolean checkPitTile() {
        int leftCol = (gp.player.worldX + gp.player.solidArea.x) / gp.tileSize;
        int rightCol = (gp.player.worldX + gp.player.solidArea.x + gp.player.solidArea.width) / gp.tileSize;
        int topRow = (gp.player.worldY + gp.player.solidArea.y) / gp.tileSize;
        int bottomRow = (gp.player.worldY + gp.player.solidArea.y + gp.player.solidArea.height) / gp.tileSize;

        int tileTopLeft = gp.tileM.mapTileNum[gp.currentMap][leftCol][topRow];
        int tileTopRight = gp.tileM.mapTileNum[gp.currentMap][rightCol][topRow];
        int tileBottomLeft = gp.tileM.mapTileNum[gp.currentMap][leftCol][bottomRow];
        int tileBottomRight = gp.tileM.mapTileNum[gp.currentMap][rightCol][bottomRow];

        if (tileTopLeft == 18 || tileTopRight == 18 || tileBottomLeft == 18 || tileBottomRight == 18) {
            gp.tileM.mapTileNum[gp.currentMap][leftCol][topRow] = 23;
            gp.tileM.mapTileNum[gp.currentMap][rightCol][topRow] = 23;
            gp.tileM.mapTileNum[gp.currentMap][leftCol][bottomRow] = 23;
            gp.tileM.mapTileNum[gp.currentMap][rightCol][bottomRow] = 23;
            gp.player.setTrapActivated();
            return false;
        }

        return (tileTopLeft == 23 && tileTopRight == 23 && tileBottomLeft == 23 && tileBottomRight == 23);
    }


    public boolean hit(int map, int col, int row, String reqDirection) {
        boolean hit = false;

        if(map == gp.currentMap){
            int eventX = col * gp.tileSize + eventRect[map][col][row].x;
            int eventY = row * gp.tileSize + eventRect[map][col][row].y;

            Rectangle eventArea = new Rectangle(eventX, eventY,
                    eventRect[map][col][row].width,
                    eventRect[map][col][row].height);

            Rectangle playerArea = new Rectangle(
                    gp.player.worldX + gp.player.solidArea.x,
                    gp.player.worldY + gp.player.solidArea.y,
                    gp.player.solidArea.width,
                    gp.player.solidArea.height
            );

            if (playerArea.intersects(eventArea) && canTouchEvent) {
                if (reqDirection.equals("any") || gp.player.direction.equals(reqDirection)) {
                    hit = true;
                    previousEventX = gp.player.worldX;
                    previousEventY = gp.player.worldY;
                    canTouchEvent = false;

                    System.out.println("Teleport triggered at map:" + map +
                            " col:" + col + " row:" + row);
                }
            }
        }
        return hit;
    }

    public void damagePit() {
        if (canTouchEvent) {
            gp.gameState = gp.dialogueState;
            gp.ui.currentDialogue = "You fell into a pit!";
            knockBackPlayer();
            canTouchEvent = false;
        }
    }

    public void knockBackPlayer() {
        // Determine knockback direction (opposite of current direction)
        String knockbackDir = gp.player.direction;
        
        // Check for obstacles before applying knockback
        gp.player.collisionOn = false;
        gp.ch.checkTile(gp.player);
        gp.ch.checkObject(gp.player, false);
        
        if (!gp.player.collisionOn) {
            // Apply knockback only if no obstacle in the way
            switch (knockbackDir) {
                case "up": gp.player.worldY += gp.player.speed * 2; break;
                case "down": gp.player.worldY -= gp.player.speed * 2; break;
                case "left": gp.player.worldX += gp.player.speed * 2; break;
                case "right": gp.player.worldX -= gp.player.speed * 2; break;
            }
        }
    }
}