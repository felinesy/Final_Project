package environment;

import Main.GamePanel;

import java.awt.*;

public class EnvironmentManager {
    GamePanel gp;
    public Lighting lighting;
    Fog fog;

    public EnvironmentManager(GamePanel gp) {
        this.gp = gp;
        lighting = null; // Always initialize
        fog = new Fog(gp);
    }

    public void setup() {
        if (gp.player != null) {
            int circleSize = 670;

            if (gp.currentMapIndex == 0) {
                fog = new Fog(gp);
            } else if (gp.currentMapIndex == 1) {

            } else if (gp.currentMapIndex == 2) {
                lighting = new Lighting(gp);
            }
        }
    }

    public void update() {
        lighting.update();
    }

    public void draw(Graphics2D g2) {
        // Draw the lighting and fog effects
        if (lighting != null) {
            lighting.draw(g2);
        }
        //if (fog != null) {
        //fog.draw(g2);
        //}
    }
}
