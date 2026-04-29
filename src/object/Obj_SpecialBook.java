package object;

import Main.GamePanel;

import javax.imageio.ImageIO;
import java.io.IOException;

public class Obj_SpecialBook extends Obj_Book {
    private String type;
    GamePanel gp;

    //
    public Obj_SpecialBook(String type, GamePanel gp) {
        super("SpecialBookName", gp);
        this.type = type;
        name = "Special Book";
        this.type = type;

        try {
            image = ImageIO.read(getClass().getResourceAsStream("/objects/" + type + ".png"));
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error loading book image: " + type);
        }
    }

    public String getType() {
        return  this.type;
    }
}
