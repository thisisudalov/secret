import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class CustomKeyListener extends KeyAdapter {
    ScreenMovement screenMovement;
    Runnable repainter;

    public CustomKeyListener(ScreenMovement screenMovement, Runnable repainter) {
        this.screenMovement = screenMovement;
        this.repainter = repainter;
    }

    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_RIGHT:
                screenMovement.goRight();
                repainter.run();
                break;
            case KeyEvent.VK_SPACE:
            case KeyEvent.VK_UP:
                screenMovement.jump();
                repainter.run();
                break;
            case KeyEvent.VK_LEFT:
                screenMovement.goLeft();
                repainter.run();
                break;
        }
    }
}