package environment;

import Main.GamePanel;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Fog {
    GamePanel gp;
    BufferedImage fogFilter;
    float alpha = 0.3f; // 0.0f = invisible, 1.0f = fully opaque

    public Fog(GamePanel gp) {
        this.gp = gp;
        fogFilter = new BufferedImage(gp.screenWidth, gp.screenHeight, BufferedImage.TYPE_INT_ARGB);

        Graphics2D gFog = fogFilter.createGraphics();
        gFog.setColor(new Color(220, 220, 220));
        gFog.fillRect(0, 0, gp.screenWidth, gp.screenHeight);
        gFog.dispose();
    }

    public void draw(Graphics2D g2) {
        Composite originalComposite = g2.getComposite();
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha));

        g2.drawImage(fogFilter, 0, 0, null);

        g2.setComposite(originalComposite);
    }
}
