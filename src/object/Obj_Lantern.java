package object;

import Main.GamePanel;
import entity.Entity;

import javax.imageio.ImageIO;
import java.io.IOException;

public class Obj_Lantern extends Entity {

    public Obj_Lantern(GamePanel gp) {
        super(gp);

        name = "Lantern";
        stackable = false;
        lightRadius = 250;
        description = "[Lantern]";
        collision = true;

        try {
            image = ImageIO.read(getClass().getResourceAsStream("/objects/lantern.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
