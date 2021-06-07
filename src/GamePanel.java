import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable {
    private final Player player = new Player();
    private Thread gameThread;
    private final ObjectsHolder objectsHolder = new ObjectsHolder();
    private ScreenMovement screenMovement;

    public GamePanel() {
        this.setPreferredSize(new Dimension(ScreenConstants.SCREEN_WIDTH, ScreenConstants.SCREEN_HEIGHT));
        this.setFocusable(true);
        screenMovement = new ScreenMovement(player, objectsHolder);
        this.addKeyListener(new CustomKeyListener(screenMovement,  this::repaint));

        gameThread = new Thread(this, "Mad bob");
        gameThread.start();
    }

    public void paint(Graphics g) {
        draw(g);
    }

    private void draw(Graphics g) {
        screenMovement.draw(g, this);
    }

    private void update() {
        screenMovement.update();
    }

    @Override
    public void run() {
        while (true) {
            try {
                gameThread.sleep(17);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            update();
            repaint();
        }
    }
}
