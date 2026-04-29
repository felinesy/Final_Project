package object;

import Main.GamePanel;
import entity.Entity;

import javax.imageio.ImageIO;
import java.io.IOException;

public class Obj_GuardianKey extends Entity {

    public Obj_GuardianKey(GamePanel gp){
        super(gp);
        name = "Guardian Key";
        try{
            image = ImageIO.read(getClass().getResourceAsStream("/objects/guardianKey.png"));
        } catch(IOException e) {
            e.printStackTrace();
        }
        collision = true;
    }
}
