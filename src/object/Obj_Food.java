package object;

import Main.GamePanel;
import entity.Entity;

import javax.imageio.ImageIO;
import java.io.IOException;

public class Obj_Food extends Entity {

    public Obj_Food(GamePanel gp){
        super(gp);
        name = "Food";
        try{
            image = ImageIO.read(getClass().getResourceAsStream("/objects/food_full.png"));
            image2 = ImageIO.read(getClass().getResourceAsStream("/objects/food_half.png"));
            image3 = ImageIO.read(getClass().getResourceAsStream("/objects/food_blank.png"));
        } catch(IOException e) {
            e.printStackTrace();
        }
        collision = true;
    }
}
