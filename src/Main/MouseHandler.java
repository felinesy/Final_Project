package Main;
import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MouseHandler extends MouseAdapter {
    GamePanel gp;

    public MouseHandler(GamePanel gp) {
        this.gp = gp;
    }

    @Override
    public void mousePressed(MouseEvent e) {
        int mouseX = e.getX();
        int mouseY = e.getY();

        long currentTime = System.currentTimeMillis();
        if (currentTime - gp.lastInteractionTime < 1000) {
            return;
        }
        gp.lastInteractionTime = currentTime;

        if (gp.qm.isQuestionActive) {
            if (gp.qm.closeButtonBounds != null && gp.qm.closeButtonBounds.contains(mouseX, mouseY)) {
                System.out.println("Close button clicked!");

                if (!gp.qm.isQuestionActive) {
                    return;
                }

                int choice = JOptionPane.showConfirmDialog(
                        null,
                        "Are you sure you want to exit the question?",
                        "Exit Confirmation",
                        JOptionPane.YES_NO_OPTION
                );

                if (choice == JOptionPane.YES_OPTION) {
                    gp.qm.isQuestionActive = false;
                    gp.qm.selectedAnswerIndex = -1;
                    gp.gameState = gp.playState;
                    gp.repaint();
                    System.out.println("Returning to gameplay...");

                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException ex) {
                        ex.printStackTrace();
                    }
                } else {
                    System.out.println("Player chose to continue answering.");
                }

                return;
            }
            checkAnswer(mouseX, mouseY);
        }
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        int mouseX = e.getX();
        int mouseY = e.getY();

        if (gp.qm.isQuestionActive && gp.qm.closeButtonBounds != null) {
            boolean wasHovered = gp.qm.isCloseButtonHovered;
            gp.qm.isCloseButtonHovered = gp.qm.closeButtonBounds.contains(mouseX, mouseY);

            if (wasHovered != gp.qm.isCloseButtonHovered) {
                gp.repaint();
            }
        }
    }

    private void checkAnswer(int mouseX, int mouseY) {
        int circleX = 186;
        int baseY = 163;
        int circleSpacing = 59;
        int circleRadius = 20;

        for (int i = 0; i < gp.qm.choices.length; i++) {
            int centerY = baseY + (i * circleSpacing);

            int dx = mouseX - circleX;
            int dy = mouseY - centerY;
            boolean inCircle = (dx * dx + dy * dy) <= (circleRadius * circleRadius);

            if (inCircle) {
                gp.qm.selectedAnswerIndex = i;
                gp.repaint();
                gp.qm.processAnswer(gp.qm.choices[i]);
                return;
            }
        }
        gp.ui.addMessage("No choice detected!");
    }


}
