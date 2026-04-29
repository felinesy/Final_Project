package object;

import Main.GamePanel;

import javax.imageio.ImageIO;
import java.io.IOException;

public class Obj_SpecialKey extends Obj_Key {
    private String type;

    public Obj_SpecialKey(String type, GamePanel gp) {
        super(gp);
        this.type = type;
        name = "Special Key (" + type + ")";

        try {
            image = ImageIO.read(getClass().getResourceAsStream("/objects/" + type + ".png"));
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error loading special key image: " + type);
        }
    }

    public String getType() {
        return type;
    }
}
