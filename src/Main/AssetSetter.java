package Main;
import object.*;
import entity.Animals;

public class AssetSetter {
    GamePanel gp;

    public AssetSetter(GamePanel gp){
        this.gp = gp;
    }

    public void setObject(){
        int mapNum = 0;
        //KEY
        int i = 0;
        gp.obj[mapNum][i] = new Obj_Key(gp);
        gp.obj[mapNum][i].worldX = 36*gp.tileSize; //
        gp.obj[mapNum][i].worldY = 66*gp.tileSize;
        i++;

        gp.obj[mapNum][i] = new Obj_Key(gp);
        gp.obj[mapNum][i].worldX = 90*gp.tileSize;
        gp.obj[mapNum][i].worldY = 43*gp.tileSize;//
        i++;

        gp.obj[mapNum][i] = new Obj_Key(gp);
        gp.obj[mapNum][i].worldX = 22*gp.tileSize;
        gp.obj[mapNum][i].worldY = 11*gp.tileSize;//
        i++;

        gp.obj[mapNum][i] = new Obj_Key(gp);
        gp.obj[mapNum][i].worldX = 48*gp.tileSize;//
        gp.obj[mapNum][i].worldY = 84*gp.tileSize;
        i++;

        gp.obj[mapNum][i] = new Obj_Key(gp);
        gp.obj[mapNum][i].worldX = 45*gp.tileSize;
        gp.obj[mapNum][i].worldY = 82*gp.tileSize;//
        i++;

        gp.obj[mapNum][i] = new Obj_Key(gp);
        gp.obj[mapNum][i].worldX = 30*gp.tileSize;
        gp.obj[mapNum][i].worldY = 80*gp.tileSize;//
        i++;

        gp.obj[mapNum][i] = new Obj_Key(gp);
        gp.obj[mapNum][i].worldX = 76*gp.tileSize;
        gp.obj[mapNum][i].worldY = 88*gp.tileSize;
        i++;

        gp.obj[mapNum][i] = new Obj_Lantern(gp);
        gp.obj[mapNum][i].worldX = 88*gp.tileSize;
        gp.obj[mapNum][i].worldY = 45*gp.tileSize;
        i++;

        gp.obj[mapNum][i] = new Obj_Key(gp);
        gp.obj[mapNum][i].worldX = 21*gp.tileSize;
        gp.obj[mapNum][i].worldY = 54*gp.tileSize;
        i++;

        gp.obj[mapNum][i] = new Obj_Book("book", gp);
        gp.obj[mapNum][i].worldX = 64 * gp.tileSize;
        gp.obj[mapNum][i].worldY = 59 * gp.tileSize;
        i++;

        gp.obj[mapNum][i] = new Obj_Book("book", gp);
        gp.obj[mapNum][i].worldX = 53 * gp.tileSize;
        gp.obj[mapNum][i].worldY = 82 * gp.tileSize;//
        i++;

        gp.obj[mapNum][i] = new Obj_Book("book", gp);
        gp.obj[mapNum][i].worldX = 64 * gp.tileSize;
        gp.obj[mapNum][i].worldY = 88 * gp.tileSize;//
        i++;
        gp.obj[mapNum][i] = new Obj_Key(gp);
        gp.obj[mapNum][i].worldX = 18*gp.tileSize;
        gp.obj[mapNum][i].worldY = 60*gp.tileSize;
        i++;

        gp.obj[mapNum][i] = new Obj_Book("book", gp);
        gp.obj[mapNum][i].worldX = 49 * gp.tileSize;
        gp.obj[mapNum][i].worldY = 73 * gp.tileSize;
        i++;

        gp.obj[mapNum][i] = new Obj_Book("book", gp);
        gp.obj[mapNum][i].worldX = 14 * gp.tileSize;
        gp.obj[mapNum][i].worldY = 77 * gp.tileSize;
        i++;

        gp.obj[mapNum][i] = new Obj_Book("book", gp);
        gp.obj[mapNum][i].worldX = 46 * gp.tileSize;
        gp.obj[mapNum][i].worldY = 55 * gp.tileSize;//47,53
        i++;

        gp.obj[mapNum][i] = new Obj_BluePotion(gp);
        gp.obj[mapNum][i].worldX = 32 * gp.tileSize;
        gp.obj[mapNum][i].worldY = 33 * gp.tileSize;
        i++;

        gp.obj[mapNum][i] = new Obj_BluePotion(gp);
        gp.obj[mapNum][i].worldX = 78 * gp.tileSize;
        gp.obj[mapNum][i].worldY = 40 * gp.tileSize;
        i++;

        gp.obj[mapNum][i] = new Obj_BluePotion(gp);
        gp.obj[mapNum][i].worldX = 55 * gp.tileSize;
        gp.obj[mapNum][i].worldY = 52 * gp.tileSize;
        i++;

        gp.obj[mapNum][i] = new Obj_BluePotion(gp);
        gp.obj[mapNum][i].worldX = 35 * gp.tileSize;
        gp.obj[mapNum][i].worldY = 54 * gp.tileSize;
        i++;

        gp.obj[mapNum][i] = new Obj_RedPotion(gp);
        gp.obj[mapNum][i].worldX = 20 * gp.tileSize;
        gp.obj[mapNum][i].worldY = 64 * gp.tileSize;
        i++;

        //OBJECTS
        gp.obj[mapNum][i] = new Obj_Generic("basket_of_bread", "Bread", gp);
        gp.obj[mapNum][i].worldX = 47 * gp.tileSize;
        gp.obj[mapNum][i].worldY = 23 * gp.tileSize;
        i++;

        gp.obj[mapNum][i] = new Obj_Generic("basket_of_bread", "Bread", gp);
        gp.obj[mapNum][i].worldX = 36 * gp.tileSize;
        gp.obj[mapNum][i].worldY = 65 * gp.tileSize;
        i++;

        gp.obj[mapNum][i] = new Obj_Generic("basket_of_bread", "Bread", gp);
        gp.obj[mapNum][i].worldX = 42 * gp.tileSize;
        gp.obj[mapNum][i].worldY = 63 * gp.tileSize;
        i++;

        gp.obj[mapNum][i] = new Obj_Generic("basket_of_bread", "Bread", gp);
        gp.obj[mapNum][i].worldX = 14 * gp.tileSize;
        gp.obj[mapNum][i].worldY = 62 * gp.tileSize;
        i++;

        gp.obj[mapNum][i] = new Obj_Generic("basket_of_bread", "Bread", gp);
        gp.obj[mapNum][i].worldX = 20 * gp.tileSize;
        gp.obj[mapNum][i].worldY = 43 * gp.tileSize;//
        i++;

        gp.obj[mapNum][i] = new Obj_Book("book", gp);
        gp.obj[mapNum][i].worldX = 87 * gp.tileSize;
        gp.obj[mapNum][i].worldY = 43 * gp.tileSize;//
        i++;

        gp.obj[mapNum][i] = new Obj_Book("book", gp);
        gp.obj[mapNum][i].worldX = 69 * gp.tileSize;
        gp.obj[mapNum][i].worldY = 51 * gp.tileSize;//
        i++;

        gp.obj[mapNum][i] = new Obj_Book("book", gp);
        gp.obj[mapNum][i].worldX = 46 * gp.tileSize;
        gp.obj[mapNum][i].worldY = 55 * gp.tileSize;//
        i++;

        gp.obj[mapNum][i] = new Obj_Book("book", gp);
        gp.obj[mapNum][i].worldX = 17 * gp.tileSize;
        gp.obj[mapNum][i].worldY = 81 * gp.tileSize;//
        i++;

        gp.obj[mapNum][i] = new Obj_Book("book", gp);
        gp.obj[mapNum][i].worldX = 80 * gp.tileSize;
        gp.obj[mapNum][i].worldY = 15 * gp.tileSize;//
        i++;

        gp.obj[mapNum][i] = new Obj_Book("book", gp);
        gp.obj[mapNum][i].worldX = 46 * gp.tileSize;
        gp.obj[mapNum][i].worldY = 46 * gp.tileSize;
        i++;

        gp.obj[mapNum][i] = new Obj_Book("book", gp);
        gp.obj[mapNum][i].worldX = 77 * gp.tileSize;//
        gp.obj[mapNum][i].worldY = 83 * gp.tileSize;
        i++;

        gp.obj[mapNum][i] = new Obj_SpecialBook("Sbook1", gp);
        gp.obj[mapNum][i].worldX = 17 * gp.tileSize;
        gp.obj[mapNum][i].worldY = 19 * gp.tileSize;
        i++;

        gp.obj[mapNum][i] = new Obj_MapDoor("map_door", gp);
        gp.obj[mapNum][i].worldX = 11 * gp.tileSize;
        gp.obj[mapNum][i].worldY = 14 * gp.tileSize;
        i++;

        mapNum = 1; //(for other map) map 2

        gp.obj[mapNum][i] = new Obj_Door("door1", gp);
        gp.obj[mapNum][i].worldX = 87 * gp.tileSize;
        gp.obj[mapNum][i].worldY = 44 * gp.tileSize;
        i++;

        gp.obj[mapNum][i] = new Obj_Door("door1", gp);
        gp.obj[mapNum][i].worldX = 82 * gp.tileSize;
        gp.obj[mapNum][i].worldY = 36 * gp.tileSize;
        i++;

        gp.obj[mapNum][i] = new Obj_Door("door1", gp);
        gp.obj[mapNum][i].worldX = 32 * gp.tileSize;
        gp.obj[mapNum][i].worldY = 72 * gp.tileSize;
        i++;

        gp.obj[mapNum][i] = new Obj_Door("door1", gp);
        gp.obj[mapNum][i].worldX = 83 * gp.tileSize;
        gp.obj[mapNum][i].worldY = 36 * gp.tileSize;
        i++;

        gp.obj[mapNum][i] = new Obj_MapDoor("map_door", gp);
        gp.obj[mapNum][i].worldX = 44 * gp.tileSize;
        gp.obj[mapNum][i].worldY = 84 * gp.tileSize;
        i++;

        gp.obj[mapNum][i] = new Obj_Door("door1", gp);
        gp.obj[mapNum][i].worldX = 19 * gp.tileSize;
        gp.obj[mapNum][i].worldY = 78 * gp.tileSize;
        i++;

        gp.obj[mapNum][i] = new Obj_Door("door1", gp);
        gp.obj[mapNum][i].worldX = 20 * gp.tileSize;
        gp.obj[mapNum][i].worldY = 85 * gp.tileSize;
        i++;

        gp.obj[mapNum][i] = new Obj_Door("door1", gp);
        gp.obj[mapNum][i].worldX = 20 * gp.tileSize;
        gp.obj[mapNum][i].worldY = 84 * gp.tileSize;
        i++;

        gp.obj[mapNum][i] = new Obj_Book("book", gp);
        gp.obj[mapNum][i].worldX = 25 * gp.tileSize;
        gp.obj[mapNum][i].worldY = 84 * gp.tileSize;
        i++;


        gp.obj[mapNum][i] = new Obj_Door("door1", gp);
        gp.obj[mapNum][i].worldX = 52 * gp.tileSize;
        gp.obj[mapNum][i].worldY = 27 * gp.tileSize;
        i++;

        gp.obj[mapNum][i] = new Obj_Door("door1", gp);
        gp.obj[mapNum][i].worldX = 25 * gp.tileSize;
        gp.obj[mapNum][i].worldY = 11 * gp.tileSize;
        i++;

        gp.obj[mapNum][i] = new Obj_Door("door1", gp);
        gp.obj[mapNum][i].worldX = 26 * gp.tileSize;
        gp.obj[mapNum][i].worldY = 45 * gp.tileSize;
        i++;

        gp.obj[mapNum][i] = new Obj_Door("door1", gp);
        gp.obj[mapNum][i].worldX = 25 * gp.tileSize;
        gp.obj[mapNum][i].worldY = 40 * gp.tileSize;
        i++;

        gp.obj[mapNum][i] = new Obj_Door("door1", gp);
        gp.obj[mapNum][i].worldX = 16 * gp.tileSize;
        gp.obj[mapNum][i].worldY = 19 * gp.tileSize;
        i++;

        gp.obj[mapNum][i] = new Obj_Door("door1", gp);
        gp.obj[mapNum][i].worldX = 60 * gp.tileSize;
        gp.obj[mapNum][i].worldY = 84 * gp.tileSize;
        i++;

        gp.obj[mapNum][i] = new Obj_Door("door1", gp);
        gp.obj[mapNum][i].worldX = 80 * gp.tileSize;
        gp.obj[mapNum][i].worldY = 86 * gp.tileSize;
        i++;

        gp.obj[mapNum][i] = new Obj_Book("book", gp);
        gp.obj[mapNum][i].worldX = 69 * gp.tileSize;
        gp.obj[mapNum][i].worldY = 20 * gp.tileSize;
        i++;

        gp.obj[mapNum][i] = new Obj_SpecialBook("Sbook2", gp);
        gp.obj[mapNum][i].worldX = 48 * gp.tileSize;
        gp.obj[mapNum][i].worldY = 84 * gp.tileSize;
        i++;

        gp.obj[mapNum][i] = new Obj_Book("book", gp);
        gp.obj[mapNum][i].worldX = 30 * gp.tileSize;
        gp.obj[mapNum][i].worldY = 12 * gp.tileSize;
        i++;

        gp.obj[mapNum][i] = new Obj_Book("book", gp);
        gp.obj[mapNum][i].worldX = 18 * gp.tileSize;
        gp.obj[mapNum][i].worldY = 22 * gp.tileSize;
        i++;

        gp.obj[mapNum][i] = new Obj_Book("book", gp);
        gp.obj[mapNum][i].worldX = 27 * gp.tileSize;
        gp.obj[mapNum][i].worldY = 76 * gp.tileSize;
        i++;

        gp.obj[mapNum][i] = new Obj_RedPotion(gp);
        gp.obj[mapNum][i].worldX = 62 * gp.tileSize;
        gp.obj[mapNum][i].worldY = 11 * gp.tileSize;
        i++;

        gp.obj[mapNum][i] = new Obj_RedPotion(gp);
        gp.obj[mapNum][i].worldX = 33 * gp.tileSize;
        gp.obj[mapNum][i].worldY = 51 * gp.tileSize;
        i++;

        gp.obj[mapNum][i] = new Obj_BluePotion(gp);
        gp.obj[mapNum][i].worldX = 74 * gp.tileSize;
        gp.obj[mapNum][i].worldY = 26 * gp.tileSize;
        i++;

        gp.obj[mapNum][i] = new Obj_BluePotion(gp);
        gp.obj[mapNum][i].worldX = 81 * gp.tileSize;
        gp.obj[mapNum][i].worldY = 79 * gp.tileSize;
        i++;

        gp.obj[mapNum][i] = new Obj_BluePotion(gp);
        gp.obj[mapNum][i].worldX = 77 * gp.tileSize;
        gp.obj[mapNum][i].worldY = 78 * gp.tileSize;
        i++;

        gp.obj[mapNum][i] = new Obj_BluePotion(gp);
        gp.obj[mapNum][i].worldX = 61 * gp.tileSize;
        gp.obj[mapNum][i].worldY = 33 * gp.tileSize;
        i++;

        gp.obj[mapNum][i] = new Obj_BluePotion(gp);
        gp.obj[mapNum][i].worldX = 47 * gp.tileSize;
        gp.obj[mapNum][i].worldY = 35 * gp.tileSize;
        i++;

        gp.obj[mapNum][i] = new Obj_BluePotion(gp);
        gp.obj[mapNum][i].worldX = 32 * gp.tileSize;
        gp.obj[mapNum][i].worldY = 33 * gp.tileSize;
        i++;

        gp.obj[mapNum][i] = new Obj_Generic("basket_of_bread", "Bread", gp);
        gp.obj[mapNum][i].worldX = 30 * gp.tileSize;
        gp.obj[mapNum][i].worldY = 42 * gp.tileSize;
        i++;

        gp.obj[mapNum][i] = new Obj_Generic("basket_of_bread", "Bread", gp);
        gp.obj[mapNum][i].worldX = 53 * gp.tileSize;
        gp.obj[mapNum][i].worldY = 45 * gp.tileSize;
        i++;

        gp.obj[mapNum][i] = new Obj_Key(gp);
        gp.obj[mapNum][i].worldX = 44*gp.tileSize;
        gp.obj[mapNum][i].worldY = 37*gp.tileSize;
        i++;

        mapNum=2; //(for other map) map 3                       MAP 3

        gp.obj[mapNum][i] = new Obj_Door("door1", gp);
        gp.obj[mapNum][i].worldX = 87 * gp.tileSize;
        gp.obj[mapNum][i].worldY = 44 * gp.tileSize;
        i++;

        gp.obj[mapNum][i] = new Obj_Door("door1", gp);
        gp.obj[mapNum][i].worldX = 82 * gp.tileSize;
        gp.obj[mapNum][i].worldY = 36 * gp.tileSize;
        i++;

        gp.obj[mapNum][i] = new Obj_Door("door1", gp);
        gp.obj[mapNum][i].worldX = 32 * gp.tileSize;
        gp.obj[mapNum][i].worldY = 72 * gp.tileSize;
        i++;

        gp.obj[mapNum][i] = new Obj_Door("door1", gp);
        gp.obj[mapNum][i].worldX = 83 * gp.tileSize;
        gp.obj[mapNum][i].worldY = 36 * gp.tileSize;
        i++;

        gp.obj[mapNum][i] = new Obj_MapDoor("map_door", gp);
        gp.obj[mapNum][i].worldX = 39 * gp.tileSize;
        gp.obj[mapNum][i].worldY = 30 * gp.tileSize;
        i++;

        gp.obj[mapNum][i] = new Obj_Guardian (gp);
        gp.obj[mapNum][i].worldX = 39 * gp.tileSize;
        gp.obj[mapNum][i].worldY = 37 * gp.tileSize;
        i++;

        gp.obj[mapNum][i] = new Obj_Door("door1", gp);
        gp.obj[mapNum][i].worldX = 19 * gp.tileSize;
        gp.obj[mapNum][i].worldY = 78 * gp.tileSize;
        i++;

        gp.obj[mapNum][i] = new Obj_Door("door1", gp);
        gp.obj[mapNum][i].worldX = 20 * gp.tileSize;
        gp.obj[mapNum][i].worldY = 85 * gp.tileSize;
        i++;

        gp.obj[mapNum][i] = new Obj_Door("door1", gp);
        gp.obj[mapNum][i].worldX = 20 * gp.tileSize;
        gp.obj[mapNum][i].worldY = 84 * gp.tileSize;
        i++;

        gp.obj[mapNum][i] = new Obj_Book("book", gp);
        gp.obj[mapNum][i].worldX = 25 * gp.tileSize;
        gp.obj[mapNum][i].worldY = 84 * gp.tileSize;
        i++;


        gp.obj[mapNum][i] = new Obj_Door("door1", gp);
        gp.obj[mapNum][i].worldX = 52 * gp.tileSize;
        gp.obj[mapNum][i].worldY = 27 * gp.tileSize;
        i++;

        gp.obj[mapNum][i] = new Obj_Door("door1", gp);
        gp.obj[mapNum][i].worldX = 25 * gp.tileSize;
        gp.obj[mapNum][i].worldY = 11 * gp.tileSize;
        i++;

        gp.obj[mapNum][i] = new Obj_Door("door1", gp);
        gp.obj[mapNum][i].worldX = 26 * gp.tileSize;
        gp.obj[mapNum][i].worldY = 45 * gp.tileSize;
        i++;

        gp.obj[mapNum][i] = new Obj_Door("door1", gp);
        gp.obj[mapNum][i].worldX = 25 * gp.tileSize;
        gp.obj[mapNum][i].worldY = 40 * gp.tileSize;
        i++;

        gp.obj[mapNum][i] = new Obj_Door("door1", gp);
        gp.obj[mapNum][i].worldX = 16 * gp.tileSize;
        gp.obj[mapNum][i].worldY = 19 * gp.tileSize;
        i++;

        gp.obj[mapNum][i] = new Obj_Door("door1", gp);
        gp.obj[mapNum][i].worldX = 60 * gp.tileSize;
        gp.obj[mapNum][i].worldY = 84 * gp.tileSize;
        i++;

        gp.obj[mapNum][i] = new Obj_Door("door1", gp);
        gp.obj[mapNum][i].worldX = 80 * gp.tileSize;
        gp.obj[mapNum][i].worldY = 86 * gp.tileSize;
        i++;

        gp.obj[mapNum][i] = new Obj_Book("book", gp);
        gp.obj[mapNum][i].worldX = 69 * gp.tileSize;
        gp.obj[mapNum][i].worldY = 20 * gp.tileSize;
        i++;

        gp.obj[mapNum][i] = new Obj_SpecialBook("Sbook3", gp);
        gp.obj[mapNum][i].worldX = 39 * gp.tileSize;
        gp.obj[mapNum][i].worldY = 38 * gp.tileSize;
        i++;

        gp.obj[mapNum][i] = new Obj_Book("book", gp);
        gp.obj[mapNum][i].worldX = 30 * gp.tileSize;
        gp.obj[mapNum][i].worldY = 12 * gp.tileSize;
        i++;

        gp.obj[mapNum][i] = new Obj_Book("book", gp);
        gp.obj[mapNum][i].worldX = 18 * gp.tileSize;
        gp.obj[mapNum][i].worldY = 22 * gp.tileSize;
        i++;

        gp.obj[mapNum][i] = new Obj_Book("book", gp);
        gp.obj[mapNum][i].worldX = 27 * gp.tileSize;
        gp.obj[mapNum][i].worldY = 76 * gp.tileSize;
        i++;

        gp.obj[mapNum][i] = new Obj_RedPotion(gp);
        gp.obj[mapNum][i].worldX = 62 * gp.tileSize;
        gp.obj[mapNum][i].worldY = 11 * gp.tileSize;
        i++;

        gp.obj[mapNum][i] = new Obj_RedPotion(gp);
        gp.obj[mapNum][i].worldX = 33 * gp.tileSize;
        gp.obj[mapNum][i].worldY = 51 * gp.tileSize;
        i++;

        gp.obj[mapNum][i] = new Obj_BluePotion(gp);
        gp.obj[mapNum][i].worldX = 74 * gp.tileSize;
        gp.obj[mapNum][i].worldY = 26 * gp.tileSize;
        i++;

        gp.obj[mapNum][i] = new Obj_BluePotion(gp);
        gp.obj[mapNum][i].worldX = 81 * gp.tileSize;
        gp.obj[mapNum][i].worldY = 79 * gp.tileSize;
        i++;

        gp.obj[mapNum][i] = new Obj_BluePotion(gp);
        gp.obj[mapNum][i].worldX = 77 * gp.tileSize;
        gp.obj[mapNum][i].worldY = 78 * gp.tileSize;
        i++;

        gp.obj[mapNum][i] = new Obj_BluePotion(gp);
        gp.obj[mapNum][i].worldX = 61 * gp.tileSize;
        gp.obj[mapNum][i].worldY = 33 * gp.tileSize;
        i++;

        gp.obj[mapNum][i] = new Obj_BluePotion(gp);
        gp.obj[mapNum][i].worldX = 47 * gp.tileSize;
        gp.obj[mapNum][i].worldY = 35 * gp.tileSize;
        i++;

        gp.obj[mapNum][i] = new Obj_BluePotion(gp);
        gp.obj[mapNum][i].worldX = 32 * gp.tileSize;
        gp.obj[mapNum][i].worldY = 33 * gp.tileSize;
        i++;

        gp.obj[mapNum][i] = new Obj_Generic("basket_of_bread", "Bread", gp);
        gp.obj[mapNum][i].worldX = 30 * gp.tileSize;
        gp.obj[mapNum][i].worldY = 42 * gp.tileSize;
        i++;

        gp.obj[mapNum][i] = new Obj_Generic("basket_of_bread", "Bread", gp);
        gp.obj[mapNum][i].worldX = 53 * gp.tileSize;
        gp.obj[mapNum][i].worldY = 45 * gp.tileSize;
        i++;

        gp.obj[mapNum][i] = new Obj_Key(gp);
        gp.obj[mapNum][i].worldX = 44*gp.tileSize;
        gp.obj[mapNum][i].worldY = 37*gp.tileSize;

    }

    public void setAnimals(){
        int mapNum = 0;
        int i=0;

        gp.animals[mapNum][i] = new Animals(gp, "animal1"); //
        gp.animals[mapNum][i].worldX = gp.tileSize * 85;
        gp.animals[mapNum][i].worldY = gp.tileSize * 46;
        i++;

        gp.animals[mapNum][i] = new Animals(gp, "animal1"); //
        gp.animals[mapNum][i].worldX = gp.tileSize * 51;
        gp.animals[mapNum][i].worldY = gp.tileSize * 22;
        i++;

        gp.animals[mapNum][i] = new Animals(gp, "animal1"); //
        gp.animals[mapNum][i].worldX = gp.tileSize * 73;
        gp.animals[mapNum][i].worldY = gp.tileSize * 62;
        i++;

        gp.animals[mapNum][i] = new Animals(gp, "animal1"); //
        gp.animals[mapNum][i].worldX = gp.tileSize * 80;
        gp.animals[mapNum][i].worldY = gp.tileSize * 66;
        i++;

        gp.animals[mapNum][i] = new Animals(gp, "animal2");
        gp.animals[mapNum][i].worldX = gp.tileSize * 78;
        gp.animals[mapNum][i].worldY = gp.tileSize * 11;
        i++;

        gp.animals[mapNum][i] = new Animals(gp, "animal3");
        gp.animals[mapNum][i].worldX = gp.tileSize * 74;
        gp.animals[mapNum][i].worldY = gp.tileSize * 20;
        i++;

        gp.animals[mapNum][i] = new Animals(gp, "animal1");
        gp.animals[mapNum][i].worldX = gp.tileSize * 65;
        gp.animals[mapNum][i].worldY = gp.tileSize * 43;
        i++;

        gp.animals[mapNum][i] = new Animals(gp, "animal1");
        gp.animals[mapNum][i].worldX = gp.tileSize * 55;
        gp.animals[mapNum][i].worldY = gp.tileSize * 22;
        i++;

        gp.animals[mapNum][i] = new Animals(gp, "animal2");
        gp.animals[mapNum][i].worldX = gp.tileSize * 52;
        gp.animals[mapNum][i].worldY = gp.tileSize * 48;
        i++;

        gp.animals[mapNum][i] = new Animals(gp, "animal3");
        gp.animals[mapNum][i].worldX = gp.tileSize * 18;
        gp.animals[mapNum][i].worldY = gp.tileSize * 64;
        i++;

        gp.animals[mapNum][i] = new Animals(gp, "animal1");
        gp.animals[mapNum][i].worldX = gp.tileSize * 37;
        gp.animals[mapNum][i].worldY = gp.tileSize * 67;
        i++;

        gp.animals[mapNum][i] = new Animals(gp, "animal2");
        gp.animals[mapNum][i].worldX = gp.tileSize * 42;
        gp.animals[mapNum][i].worldY = gp.tileSize * 65;
        i++;

        gp.animals[mapNum][i] = new Animals(gp, "animal3");
        gp.animals[mapNum][i].worldX = gp.tileSize * 21;
        gp.animals[mapNum][i].worldY = gp.tileSize * 14;
        i++;

        gp.animals[mapNum][i] = new Animals(gp, "animal1");
        gp.animals[mapNum][i].worldX = gp.tileSize * 14;
        gp.animals[mapNum][i].worldY = gp.tileSize * 20;
        i++;

        mapNum=1;                                                    // MAP 2

        gp.animals[mapNum][i] = new Animals(gp, "animal1");
        gp.animals[mapNum][i].worldX = gp.tileSize * 55;
        gp.animals[mapNum][i].worldY = gp.tileSize * 52;
        i++;

        gp.animals[mapNum][i] = new Animals(gp, "animal1");
        gp.animals[mapNum][i].worldX = gp.tileSize * 84;
        gp.animals[mapNum][i].worldY = gp.tileSize * 21;
        i++;

        gp.animals[mapNum][i] = new Animals(gp, "animal2");
        gp.animals[mapNum][i].worldX = gp.tileSize * 64;
        gp.animals[mapNum][i].worldY = gp.tileSize * 25;
        i++;

        gp.animals[mapNum][i] = new Animals(gp, "animal1");
        gp.animals[mapNum][i].worldX = gp.tileSize * 76;
        gp.animals[mapNum][i].worldY = gp.tileSize * 12;
        i++;

        gp.animals[mapNum][i] = new Animals(gp, "animal1");
        gp.animals[mapNum][i].worldX = gp.tileSize * 81;
        gp.animals[mapNum][i].worldY = gp.tileSize * 73;
        i++;

        gp.animals[mapNum][i] = new Animals(gp, "animal1");
        gp.animals[mapNum][i].worldX = gp.tileSize * 71;
        gp.animals[mapNum][i].worldY = gp.tileSize * 63;
        i++;

        gp.animals[mapNum][i] = new Animals(gp, "animal1");
        gp.animals[mapNum][i].worldX = gp.tileSize * 84;
        gp.animals[mapNum][i].worldY = gp.tileSize * 60;
        i++;


        gp.animals[mapNum][i] = new Animals(gp, "animal1");
        gp.animals[mapNum][i].worldX = gp.tileSize * 30;
        gp.animals[mapNum][i].worldY = gp.tileSize * 56;
        i++;

        gp.animals[mapNum][i] = new Animals(gp, "animal1");
        gp.animals[mapNum][i].worldX = gp.tileSize * 41;
        gp.animals[mapNum][i].worldY = gp.tileSize * 46;
        i++;

        gp.animals[mapNum][i] = new Animals(gp, "animal1");
        gp.animals[mapNum][i].worldX = gp.tileSize * 50;
        gp.animals[mapNum][i].worldY = gp.tileSize * 78;
        i++;

        gp.animals[mapNum][i] = new Animals(gp, "animal2");
        gp.animals[mapNum][i].worldX = gp.tileSize * 90;
        gp.animals[mapNum][i].worldY = gp.tileSize * 46;
        i++;

        gp.animals[mapNum][i] = new Animals(gp, "animal1");
        gp.animals[mapNum][i].worldX = gp.tileSize * 78;
        gp.animals[mapNum][i].worldY = gp.tileSize * 49;
        i++;

        gp.animals[mapNum][i] = new Animals(gp, "animal2");
        gp.animals[mapNum][i].worldX = gp.tileSize * 34;
        gp.animals[mapNum][i].worldY = gp.tileSize * 25;
        i++;

        gp.animals[mapNum][i] = new Animals(gp, "animal3");
        gp.animals[mapNum][i].worldX = gp.tileSize * 78;
        gp.animals[mapNum][i].worldY = gp.tileSize * 81;
        i++;

        gp.animals[mapNum][i] = new Animals(gp, "animal2");
        gp.animals[mapNum][i].worldX = gp.tileSize * 82;
        gp.animals[mapNum][i].worldY = gp.tileSize * 76;
        i++;

        gp.animals[mapNum][i] = new Animals(gp, "animal3");
        gp.animals[mapNum][i].worldX = gp.tileSize * 59;
        gp.animals[mapNum][i].worldY = gp.tileSize * 81;
        i++;

        gp.animals[mapNum][i] = new Animals(gp, "animal1");
        gp.animals[mapNum][i].worldX = gp.tileSize * 32;
        gp.animals[mapNum][i].worldY = gp.tileSize * 79;
        i++;


        gp.animals[mapNum][i] = new Animals(gp, "animal1");
        gp.animals[mapNum][i].worldX = gp.tileSize * 45;
        gp.animals[mapNum][i].worldY = gp.tileSize * 51;
        i++;

        gp.animals[mapNum][i] = new Animals(gp, "animal1");
        gp.animals[mapNum][i].worldX = gp.tileSize * 14;
        gp.animals[mapNum][i].worldY = gp.tileSize * 55;
        i++;

        gp.animals[mapNum][i] = new Animals(gp, "animal1");
        gp.animals[mapNum][i].worldX = gp.tileSize * 39;
        gp.animals[mapNum][i].worldY = gp.tileSize * 45;
        i++;

        mapNum=2;                                              // MAP 3

        gp.animals[mapNum][i] = new Animals(gp, "animal1");
        gp.animals[mapNum][i].worldX = gp.tileSize * 55;
        gp.animals[mapNum][i].worldY = gp.tileSize * 52;
        i++;

        gp.animals[mapNum][i] = new Animals(gp, "animal1");
        gp.animals[mapNum][i].worldX = gp.tileSize * 84;
        gp.animals[mapNum][i].worldY = gp.tileSize * 21;
        i++;

        gp.animals[mapNum][i] = new Animals(gp, "animal2");
        gp.animals[mapNum][i].worldX = gp.tileSize * 64;
        gp.animals[mapNum][i].worldY = gp.tileSize * 25;
        i++;

        gp.animals[mapNum][i] = new Animals(gp, "animal1");
        gp.animals[mapNum][i].worldX = gp.tileSize * 76;
        gp.animals[mapNum][i].worldY = gp.tileSize * 12;
        i++;

        gp.animals[mapNum][i] = new Animals(gp, "animal1");
        gp.animals[mapNum][i].worldX = gp.tileSize * 81;
        gp.animals[mapNum][i].worldY = gp.tileSize * 73;
        i++;

        gp.animals[mapNum][i] = new Animals(gp, "animal1");
        gp.animals[mapNum][i].worldX = gp.tileSize * 71;
        gp.animals[mapNum][i].worldY = gp.tileSize * 63;
        i++;

        gp.animals[mapNum][i] = new Animals(gp, "animal1");
        gp.animals[mapNum][i].worldX = gp.tileSize * 84;
        gp.animals[mapNum][i].worldY = gp.tileSize * 60;
        i++;

        gp.animals[mapNum][i] = new Animals(gp, "animal1");
        gp.animals[mapNum][i].worldX = gp.tileSize * 30;
        gp.animals[mapNum][i].worldY = gp.tileSize * 56;
        i++;

        gp.animals[mapNum][i] = new Animals(gp, "animal1");
        gp.animals[mapNum][i].worldX = gp.tileSize * 41;
        gp.animals[mapNum][i].worldY = gp.tileSize * 46;
        i++;

        gp.animals[mapNum][i] = new Animals(gp, "animal1");
        gp.animals[mapNum][i].worldX = gp.tileSize * 50;
        gp.animals[mapNum][i].worldY = gp.tileSize * 78;

    }
}
