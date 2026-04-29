package object;

import Main.GamePanel;
import entity.Entity;

import javax.imageio.ImageIO;
import java.io.IOException;

public class Obj_Door extends Entity {
    public Obj_Door(String type, GamePanel gp) {
        super(gp);
        name = "Door";
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/objects/" + type + ".png"));
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error loading door image: " + type);
        }
        collision = true;
    }
}
