package object;

import Main.GamePanel;
import entity.Entity;

import javax.imageio.ImageIO;
import java.io.IOException;

public class Obj_Guardian extends Entity {

    public Obj_Guardian(GamePanel gp) {
        super(gp);
        name = "Guardian";
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/objects/guardian.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        collision = true;
        description = "Guardian";
    }


}
