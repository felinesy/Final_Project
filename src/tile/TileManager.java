package tile;

import Main.GamePanel;
import Main.UtilityTool;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class TileManager {
    GamePanel gp;
    public Tile[] tile;
    public int mapTileNum[][][];
    int map;
    BufferedImage home;

    public TileManager(GamePanel gp) {
        this.gp = gp;

        try {
            home = ImageIO.read(getClass().getResourceAsStream("/tiles/home.jpg"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        tile = new Tile[150];
        mapTileNum = new int[gp.maxMap][gp.maxWorldCol][gp.maxWorldRow];
        getTileImage();
        loadMap("/maps/TheCursedForest.txt", 0);
        loadMap("/maps/TheForgottenRuins.txt", 1);
        loadMap("/maps/TheCrumblingCaverns.txt", 2);

    }


    public void getTileImage() {
        try {
            this.tile[0] = new Tile();
            this.tile[0].image = ImageIO.read(this.getClass().getResourceAsStream("/tiles/floorw.png"));
            this.tile[0].collision = false;
            this.tile[1] = new Tile();
            this.tile[1].image = ImageIO.read(this.getClass().getResourceAsStream("/tiles/bg.png"));
            this.tile[1].collision = true;
            this.tile[2] = new Tile();
            this.tile[2].image = ImageIO.read(this.getClass().getResourceAsStream("/tiles/walls2.png"));
            this.tile[2].collision = true;
            // this.tile[3] = new Tile();
            // this.tile[3].image = new
            // ImageIcon(this.getClass().getResource("/tiles/torch.gif"));
            this.tile[4] = new Tile();
            this.tile[4].image = ImageIO.read(this.getClass().getResourceAsStream("/tiles/statue.png"));
            this.tile[4].collision = true;
            this.tile[5] = new Tile();
            this.tile[5].image = ImageIO.read(this.getClass().getResourceAsStream("/tiles/bg.png"));
            this.tile[5].collision = true;
            this.tile[6] = new Tile();
            this.tile[6].image = ImageIO.read(this.getClass().getResourceAsStream("/tiles/walls3.png"));
            this.tile[6].collision = true;
            this.tile[7] = new Tile();
            this.tile[7].image = ImageIO.read(this.getClass().getResourceAsStream("/tiles/walls4.png"));
            this.tile[7].collision = true;
            this.tile[8] = new Tile();
            this.tile[8].image = ImageIO.read(this.getClass().getResourceAsStream("/tiles/walls5.png"));
            this.tile[8].collision = true;
            this.tile[9] = new Tile();
            this.tile[9].image = ImageIO.read(this.getClass().getResourceAsStream("/tiles/wall6.png"));
            this.tile[9].collision = true;
            this.tile[10] = new Tile();
            this.tile[10].image = ImageIO.read(this.getClass().getResourceAsStream("/tiles/wall8.png"));
            this.tile[10].collision = true;
            this.tile[11] = new Tile();
            this.tile[11].image = ImageIO.read(this.getClass().getResourceAsStream("/tiles/floor.png"));
            this.tile[11].collision = false;
            this.tile[12] = new Tile();
            this.tile[12].image = ImageIO.read(this.getClass().getResourceAsStream("/tiles/gate1.png"));
            this.tile[12].collision = true;
            this.tile[13] = new Tile();
            this.tile[13].image = ImageIO.read(this.getClass().getResourceAsStream("/tiles/grave.png"));
            this.tile[13].collision = true;
            this.tile[14] = new Tile();
            this.tile[14].image = ImageIO.read(this.getClass().getResourceAsStream("/tiles/t1.png"));
            this.tile[14].collision = true;
            this.tile[15] = new Tile();
            this.tile[15].image = ImageIO.read(this.getClass().getResourceAsStream("/tiles/t2.png"));
            this.tile[15].collision = true;
            this.tile[16] = new Tile();
            this.tile[16].image = ImageIO.read(this.getClass().getResourceAsStream("/tiles/t3.png"));
            this.tile[16].collision = true;
            this.tile[17] = new Tile();
            this.tile[17].image = ImageIO.read(this.getClass().getResourceAsStream("/tiles/t4.png"));
            this.tile[17].collision = true;
            this.tile[18] = new Tile();
            this.tile[18].image = ImageIO.read(this.getClass().getResourceAsStream("/tiles/barrel.png"));
            this.tile[18].collision = true;
            this.tile[19] = new Tile();
            this.tile[19].image = ImageIO.read(this.getClass().getResourceAsStream("/tiles/box.png"));
            this.tile[19].collision = true;
            this.tile[20] = new Tile();
            this.tile[20].image = ImageIO.read(this.getClass().getResourceAsStream("/tiles/box2.png"));
            this.tile[20].collision = true;
            this.tile[71] = new Tile();
            this.tile[71].image = ImageIO.read(this.getClass().getResourceAsStream("/tiles/box3.png"));
            this.tile[71].collision = true;
            this.tile[72] = new Tile();
            this.tile[72].image = ImageIO.read(this.getClass().getResourceAsStream("/tiles/box4.png"));
            this.tile[72].collision = true;
            this.tile[73] = new Tile();
            this.tile[73].image = ImageIO.read(this.getClass().getResourceAsStream("/tiles/in_wall.png"));
            this.tile[73].collision = true;
            this.tile[74] = new Tile();
            this.tile[74].image = ImageIO.read(this.getClass().getResourceAsStream("/tiles/chest.png"));
            this.tile[74].collision = true;
            this.tile[75] = new Tile();
            this.tile[75].image = ImageIO.read(this.getClass().getResourceAsStream("/tiles/chest2.png"));
            this.tile[75].collision = true;
            this.tile[76] = new Tile();
            this.tile[76].image = ImageIO.read(this.getClass().getResourceAsStream("/tiles/chest3.png"));
            this.tile[76].collision = true;
            this.tile[77] = new Tile();
            this.tile[77].image = ImageIO.read(this.getClass().getResourceAsStream("/tiles/trap.png"));
            this.tile[77].collision = true;
            this.tile[78] = new Tile();
            this.tile[78].image = ImageIO.read(this.getClass().getResourceAsStream("/tiles/trap2.png"));
            this.tile[78].collision = true;
            this.tile[79] = new Tile();
            this.tile[79].image = ImageIO.read(this.getClass().getResourceAsStream("/tiles/water.png"));
            this.tile[79].collision = true;
            this.tile[80] = new Tile();
            this.tile[80].image = ImageIO.read(this.getClass().getResourceAsStream("/tiles/plantz.png"));
            this.tile[80].collision = true;
            this.tile[81] = new Tile();
            this.tile[81].image = ImageIO.read(this.getClass().getResourceAsStream("/tiles/plants.png"));
            this.tile[81].collision = true;
            this.tile[82] = new Tile();
            this.tile[82].image = ImageIO.read(this.getClass().getResourceAsStream("/tiles/ps.png"));
            this.tile[82].collision = true;
            this.tile[83] = new Tile();
            this.tile[83].image = ImageIO.read(this.getClass().getResourceAsStream("/tiles/in_wall2.png"));
            this.tile[83].collision = true;
            this.tile[84] = new Tile();
            this.tile[84].image = ImageIO.read(this.getClass().getResourceAsStream("/tiles/spid.png"));
            this.tile[84].collision = true;
            // forest tiles
            this.tile[21] = new Tile();
            this.tile[21].image = ImageIO.read(this.getClass().getResourceAsStream("/tiles/fencecorner1.png"));
            this.tile[21].collision = true;
            this.tile[22] = new Tile();
            this.tile[22].image = ImageIO.read(this.getClass().getResourceAsStream("/tiles/fencecorner2.png"));
            this.tile[22].collision = true;
            this.tile[23] = new Tile();
            this.tile[23].image = ImageIO.read(this.getClass().getResourceAsStream("/tiles/fencecorner3.png"));
            this.tile[23].collision = true;
            this.tile[24] = new Tile();
            this.tile[24].image = ImageIO.read(this.getClass().getResourceAsStream("/tiles/fencecorner4.png"));
            this.tile[24].collision = true;
            this.tile[25] = new Tile();
            this.tile[25].image = ImageIO.read(this.getClass().getResourceAsStream("/tiles/fencehoriz.png"));
            this.tile[25].collision = true;
            this.tile[26] = new Tile();
            this.tile[26].image = ImageIO.read(this.getClass().getResourceAsStream("/tiles/fenceverti.png"));
            this.tile[26].collision = true;
            this.tile[27] = new Tile();
            this.tile[27].image = ImageIO.read(this.getClass().getResourceAsStream("/tiles/rock.png"));
            this.tile[27].collision = true;
            // this.tile[28] = new Tile();
            // this.tile[28].image =
            // ImageIO.read(this.getClass().getResourceAsStream("/tiles/dead_tree.png"));
            // this.tile[29] = new Tile();
            // this.tile[29].image =
            // ImageIO.read(this.getClass().getResourceAsStream("/tiles/log.png"));
            this.tile[30] = new Tile();
            this.tile[30].image = ImageIO.read(this.getClass().getResourceAsStream("/tiles/wall_forest.png"));
            this.tile[30].collision = true;
            this.tile[31] = new Tile();
            this.tile[31].image = ImageIO.read(this.getClass().getResourceAsStream("/tiles/grass_forest.jpg"));
            // this.tile[31].collision = true;
            this.tile[32] = new Tile();
            this.tile[32].image = ImageIO.read(this.getClass().getResourceAsStream("/tiles/path_forest.png"));
            this.tile[33] = new Tile();
            this.tile[33].image = ImageIO.read(this.getClass().getResourceAsStream("/tiles/ftree1.png"));
            this.tile[33].collision = true;
            this.tile[34] = new Tile();
            this.tile[34].image = ImageIO.read(this.getClass().getResourceAsStream("/tiles/ftree2.png"));
            this.tile[34].collision = true;
            this.tile[35] = new Tile();
            this.tile[35].image = ImageIO.read(this.getClass().getResourceAsStream("/tiles/ftree3.png"));
            this.tile[35].collision = true;
            this.tile[36] = new Tile();
            this.tile[36].image = ImageIO.read(this.getClass().getResourceAsStream("/tiles/ftree4.png"));
            this.tile[36].collision = true;
            // this.tile[37] = new Tile();
            // this.tile[37].image =
            // ImageIO.read(this.getClass().getResourceAsStream("/tiles/fog.png"));
            this.tile[38] = new Tile();
            this.tile[38].image = ImageIO.read(this.getClass().getResourceAsStream("/tiles/plant1.png"));
            this.tile[38].collision = true;
            this.tile[39] = new Tile();
            this.tile[39].image = ImageIO.read(this.getClass().getResourceAsStream("/tiles/plant2.png"));
            this.tile[39].collision = true;
            this.tile[40] = new Tile();
            this.tile[40].image = ImageIO.read(this.getClass().getResourceAsStream("/tiles/plant3.png"));
            this.tile[40].collision = true;
            this.tile[41] = new Tile();
            this.tile[41].image = ImageIO.read(this.getClass().getResourceAsStream("/tiles/plant4.png"));
            this.tile[41].collision = true;
            this.tile[42] = new Tile();
            this.tile[42].image = ImageIO.read(this.getClass().getResourceAsStream("/tiles/pond-1.png"));
            this.tile[42].collision = true;
            this.tile[43] = new Tile();
            this.tile[43].image = ImageIO.read(this.getClass().getResourceAsStream("/tiles/pond-2.png"));
            this.tile[43].collision = true;
            this.tile[44] = new Tile();
            this.tile[44].image = ImageIO.read(this.getClass().getResourceAsStream("/tiles/pond-3.png"));
            this.tile[44].collision = true;
            this.tile[45] = new Tile();
            this.tile[45].image = ImageIO.read(this.getClass().getResourceAsStream("/tiles/pond-4.png"));
            this.tile[45].collision = true;
            this.tile[46] = new Tile();
            this.tile[46].image = ImageIO.read(this.getClass().getResourceAsStream("/tiles/pond-5.png"));
            this.tile[46].collision = true;
            this.tile[47] = new Tile();
            this.tile[47].image = ImageIO.read(this.getClass().getResourceAsStream("/tiles/pond-6.png"));
            this.tile[47].collision = true;
            this.tile[48] = new Tile();
            this.tile[48].image = ImageIO.read(this.getClass().getResourceAsStream("/tiles/pond-7.png"));
            this.tile[48].collision = true;
            this.tile[49] = new Tile();
            this.tile[49].image = ImageIO.read(this.getClass().getResourceAsStream("/tiles/pond-8.png"));
            this.tile[49].collision = true;
            this.tile[50] = new Tile();
            this.tile[50].image = ImageIO.read(this.getClass().getResourceAsStream("/tiles/pond-9.png"));
            this.tile[50].collision = true;
            this.tile[51] = new Tile();
            this.tile[51].image = ImageIO.read(this.getClass().getResourceAsStream("/tiles/pond-10.png"));
            this.tile[51].collision = true;
            this.tile[52] = new Tile();
            this.tile[52].image = ImageIO.read(this.getClass().getResourceAsStream("/tiles/pond-11.png"));
            this.tile[52].collision = true;
            this.tile[53] = new Tile();
            this.tile[53].image = ImageIO.read(this.getClass().getResourceAsStream("/tiles/pond-12.png"));
            this.tile[53].collision = true;
            this.tile[54] = new Tile();
            this.tile[54].image = ImageIO.read(this.getClass().getResourceAsStream("/tiles/pond-13.png"));
            this.tile[54].collision = true;
            this.tile[55] = new Tile();
            this.tile[55].image = ImageIO.read(this.getClass().getResourceAsStream("/tiles/pond-14.png"));
            this.tile[55].collision = true;
            this.tile[56] = new Tile();
            this.tile[56].image = ImageIO.read(this.getClass().getResourceAsStream("/tiles/pond-15.png"));
            this.tile[56].collision = true;
            this.tile[57] = new Tile();
            this.tile[57].image = ImageIO.read(this.getClass().getResourceAsStream("/tiles/pond-16.png"));
            this.tile[57].collision = true;
            this.tile[58] = new Tile();
            this.tile[58].image = ImageIO.read(this.getClass().getResourceAsStream("/tiles/skull1.png"));
            this.tile[58].collision = true;
            this.tile[59] = new Tile();
            this.tile[59].image = ImageIO.read(this.getClass().getResourceAsStream("/tiles/skull2.png"));
            this.tile[59].collision = true;
            this.tile[60] = new Tile();
            this.tile[60].image = ImageIO.read(this.getClass().getResourceAsStream("/tiles/skull3.png"));
            this.tile[60].collision = true;
            this.tile[61] = new Tile();
            this.tile[61].image = ImageIO.read(this.getClass().getResourceAsStream("/tiles/skull4.png"));
            this.tile[61].collision = true;
            this.tile[62] = new Tile();
            this.tile[62].image = ImageIO.read(this.getClass().getResourceAsStream("/tiles/mushroom_1.png"));
            this.tile[62].collision = true;
            this.tile[63] = new Tile();
            this.tile[63].image = ImageIO.read(this.getClass().getResourceAsStream("/tiles/mushroom_2.png"));
            this.tile[63].collision = true;
            this.tile[64] = new Tile();
            this.tile[64].image = ImageIO.read(this.getClass().getResourceAsStream("/tiles/mushroom_3.png"));
            this.tile[64].collision = true;
            this.tile[65] = new Tile();
            this.tile[65].image = ImageIO.read(this.getClass().getResourceAsStream("/tiles/mushroom_4.png"));
            this.tile[65].collision = true;
            this.tile[66] = new Tile();
            this.tile[66].image = ImageIO.read(this.getClass().getResourceAsStream("/tiles/deadt1.png"));
            this.tile[66].collision = true;
            this.tile[67] = new Tile();
            this.tile[67].image = ImageIO.read(this.getClass().getResourceAsStream("/tiles/deadt2.png"));
            this.tile[67].collision = true;
            this.tile[68] = new Tile();
            this.tile[68].image = ImageIO.read(this.getClass().getResourceAsStream("/tiles/deadt3.png"));
            this.tile[68].collision = true;
            this.tile[69] = new Tile();
            this.tile[69].image = ImageIO.read(this.getClass().getResourceAsStream("/tiles/deadt4.png"));
            this.tile[69].collision = true;
            this.tile[70] = new Tile();
            this.tile[70].image = ImageIO.read(this.getClass().getResourceAsStream("/tiles/mush.png"));
            this.tile[70].collision = true;
            // crumbling caverns
            // floor
            this.tile[85] = new Tile();
            this.tile[85].image = ImageIO.read(this.getClass().getResourceAsStream("/tiles/cave_floor.png"));
            this.tile[85].collision = false;
            // black tile
            this.tile[86] = new Tile();
            this.tile[86].image = ImageIO.read(this.getClass().getResourceAsStream("/tiles/bg.png"));
            this.tile[86].collision = true;
            // wall2
            this.tile[87] = new Tile();
            this.tile[87].image = ImageIO.read(this.getClass().getResourceAsStream("/tiles/cave2.png"));
            this.tile[87].collision = true;
            // torch -> mushroom
            this.tile[88] = new Tile();
            this.tile[88].image = ImageIO.read(this.getClass().getResourceAsStream("/tiles/crystal.png"));
            this.tile[88].collision = true;
            // statue -> crystal
            this.tile[89] = new Tile();
            this.tile[89].image = ImageIO.read(this.getClass().getResourceAsStream("/tiles/shroom.png"));
            this.tile[89].collision = true;
            // black tile
            this.tile[90] = new Tile();
            this.tile[90].image = ImageIO.read(this.getClass().getResourceAsStream("/tiles/bg.png"));
            this.tile[90].collision = true;
            // wall3
            this.tile[91] = new Tile();
            this.tile[91].image = ImageIO.read(this.getClass().getResourceAsStream("/tiles/cave3.png"));
            this.tile[91].collision = true;
            // wall4
            this.tile[92] = new Tile();
            this.tile[92].image = ImageIO.read(this.getClass().getResourceAsStream("/tiles/cave4.png"));
            this.tile[92].collision = true;
            // wall5
            this.tile[93] = new Tile();
            this.tile[93].image = ImageIO.read(this.getClass().getResourceAsStream("/tiles/cave3.png"));
            this.tile[93].collision = true;
            // wall6
            this.tile[94] = new Tile();
            this.tile[94].image = ImageIO.read(this.getClass().getResourceAsStream("/tiles/cave8.png"));
            this.tile[94].collision = true;
            // wall8
            this.tile[95] = new Tile();
            this.tile[95].image = ImageIO.read(this.getClass().getResourceAsStream("/tiles/cave8.png"));
            this.tile[95].collision = true;
            // floor / checkered
            this.tile[96] = new Tile();
            this.tile[96].image = ImageIO.read(this.getClass().getResourceAsStream("/tiles/floorx.png"));
            this.tile[96].collision = false;
            // gate1
            this.tile[97] = new Tile();
            this.tile[97].image = ImageIO.read(this.getClass().getResourceAsStream("/tiles/gate1.png"));
            this.tile[97].collision = true;
            // grave
            this.tile[98] = new Tile();
            this.tile[98].image = ImageIO.read(this.getClass().getResourceAsStream("/tiles/crystal2.png"));
            this.tile[98].collision = true;
            // t1
            this.tile[99] = new Tile();
            this.tile[99].image = ImageIO.read(this.getClass().getResourceAsStream("/tiles/thing1.png"));
            this.tile[99].collision = true;
            // t2
            this.tile[100] = new Tile();
            this.tile[100].image = ImageIO.read(this.getClass().getResourceAsStream("/tiles/thing2.png"));
            this.tile[100].collision = true;
            // t3
            this.tile[101] = new Tile();
            this.tile[101].image = ImageIO.read(this.getClass().getResourceAsStream("/tiles/thing3.png"));
            this.tile[101].collision = true;
            // t4
            this.tile[102] = new Tile();
            this.tile[102].image = ImageIO.read(this.getClass().getResourceAsStream("/tiles/thing4.png"));
            this.tile[102].collision = true;
            // barrel -> leaves
            this.tile[103] = new Tile();
            this.tile[103].image = ImageIO.read(this.getClass().getResourceAsStream("/tiles/leaves.png"));
            this.tile[103].collision = true;
            // box
            this.tile[104] = new Tile();
            this.tile[104].image = ImageIO.read(this.getClass().getResourceAsStream("/tiles/thing1.png"));
            this.tile[104].collision = true;
            // box2
            this.tile[105] = new Tile();
            this.tile[105].image = ImageIO.read(this.getClass().getResourceAsStream("/tiles/thing2.png"));
            this.tile[105].collision = true;
            // box3
            this.tile[106] = new Tile();
            this.tile[106].image = ImageIO.read(this.getClass().getResourceAsStream("/tiles/thing3.png"));
            this.tile[106].collision = true;
            // box4
            this.tile[107] = new Tile();
            this.tile[107].image = ImageIO.read(this.getClass().getResourceAsStream("/tiles/thing4.png"));
            this.tile[107].collision = true;
            //
            this.tile[108] = new Tile();
            this.tile[108].image = ImageIO.read(this.getClass().getResourceAsStream("/tiles/crystal.png"));
            this.tile[108].collision = true;
            // chest
            this.tile[109] = new Tile();
            this.tile[109].image = ImageIO.read(this.getClass().getResourceAsStream("/tiles/chest.png"));
            this.tile[109].collision = true;
            // chest2
            this.tile[110] = new Tile();
            this.tile[110].image = ImageIO.read(this.getClass().getResourceAsStream("/tiles/chest2.png"));
            this.tile[110].collision = true;
            // chest3
            this.tile[111] = new Tile();
            this.tile[111].image = ImageIO.read(this.getClass().getResourceAsStream("/tiles/chest3.png"));
            this.tile[111].collision = true;
            // trap
            this.tile[112] = new Tile();
            this.tile[112].image = ImageIO.read(this.getClass().getResourceAsStream("/tiles/trap.png"));
            this.tile[112].collision = true;
            // trap2
            this.tile[113] = new Tile();
            this.tile[113].image = ImageIO.read(this.getClass().getResourceAsStream("/tiles/trap2.png"));
            this.tile[113].collision = true;
            // water
            this.tile[114] = new Tile();
            this.tile[114].image = ImageIO.read(this.getClass().getResourceAsStream("/tiles/water.png"));
            this.tile[114].collision = true;
            // plantz
            this.tile[115] = new Tile();
            this.tile[115].image = ImageIO.read(this.getClass().getResourceAsStream("/tiles/leaves.png"));
            this.tile[115].collision = true;
            // plants
            this.tile[116] = new Tile();
            this.tile[116].image = ImageIO.read(this.getClass().getResourceAsStream("/tiles/leaves2.png"));
            this.tile[116].collision = true;
            // ps
            this.tile[117] = new Tile();
            this.tile[117].image = ImageIO.read(this.getClass().getResourceAsStream("/tiles/ps.png"));
            this.tile[117].collision = true;
            // in_wall2
            this.tile[118] = new Tile();
            this.tile[118].image = ImageIO.read(this.getClass().getResourceAsStream("/tiles/crystal.png"));
            this.tile[118].collision = true;
            // spid
            this.tile[119] = new Tile();
            this.tile[119].image = ImageIO.read(this.getClass().getResourceAsStream("/tiles/firedemon.png"));
            this.tile[119].collision = true;

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setUp(int index, String imageName, boolean collision) {
        if (index < 0 || index >= tile.length) {
            System.out.println("Invalid tile index: " + index);
            return;
        }

        UtilityTool uTool = new UtilityTool();
        try {
            tile[index] = new Tile();
            tile[index].image = ImageIO.read(getClass().getResourceAsStream("/tiles/" + imageName + ".png"));
            tile[index].image = uTool.scaleImage(tile[index].image, gp.tileSize, gp.tileSize);
            tile[index].collision = collision;
        } catch (IOException | NullPointerException e) {
            System.out.println("Error loading tile: " + imageName);
            e.printStackTrace();
        }
    }

    public void loadMap(String filePath, int mapIndex) {
        this.map = mapIndex;
        try {
            InputStream is = getClass().getResourceAsStream(filePath);
            if (is == null) {
                System.out.println("Map file not found: " + filePath);
                return;
            }

            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            // Refactored to ensure proper loop structure
            int row = 0;
            String line;
            while ((line = br.readLine()) != null && row < gp.maxWorldRow) {
                String[] numbers = line.split(" ");
                int col = 0;
                for (col = 0; col < gp.maxWorldCol && col < numbers.length; col++) {
                    int num = Integer.parseInt(numbers[col]);

                    // Ensure tile number is within valid range
                    if (num < 0 || num >= tile.length) {
                        System.out.println("Invalid tile number at (" + row + ", " + col + "): " + num);
                        num = 0; // Default to a valid tile
                    }

                    mapTileNum[map][col][row] = num;
                }
                row++;
            }
            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        gp.setMapIndex(mapIndex);
        System.out.println(filePath);
    }

    public void draw(Graphics2D g2) {

        int worldCol = 0;
        int worldRow = 0;

        while (worldCol < gp.maxWorldCol && worldRow < gp.maxWorldRow) {
            int tileNum = mapTileNum[gp.currentMap][worldCol][worldRow];

            if (tileNum < 0 || tileNum >= tile.length || tile[tileNum] == null) {
                worldCol++;
                if (worldCol == gp.maxWorldCol) {
                    worldCol = 0;
                    worldRow++;
                }
                continue;
            }

            int worldX = worldCol * gp.tileSize;
            int worldY = worldRow * gp.tileSize;
            int screenX = worldX - gp.player.worldX + gp.player.screenX;
            int screenY = worldY - gp.player.worldY + gp.player.screenY;

            if (worldX + gp.tileSize > gp.player.worldX - gp.player.screenX &&
                    worldX - gp.tileSize < gp.player.worldX + gp.player.screenX &&
                    worldY + gp.tileSize > gp.player.worldY - gp.player.screenY &&
                    worldY - gp.tileSize < gp.player.worldY + gp.player.screenY) {

                g2.drawImage(tile[tileNum].image, screenX, screenY, gp.tileSize, gp.tileSize, null);
            }

            worldCol++;
            if (worldCol == gp.maxWorldCol) {
                worldCol = 0;
                worldRow++;
            }
        }
    }
}
