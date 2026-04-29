package object;

import Main.GamePanel;
import entity.Entity;

import javax.imageio.ImageIO;
import java.io.IOException;

public class Obj_Book extends Entity {
    private GamePanel gp;

    public Obj_Book(String type, GamePanel gp) {
        super(gp);
        this.gp = gp;
        name = "Book";

        try {
            image = ImageIO.read(getClass().getResourceAsStream("/objects/book" +
                    ".png"));
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error loading book image: " + type);
        }

        collision = true;
    }
}
