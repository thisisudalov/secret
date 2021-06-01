import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class GamePanel extends JPanel implements Runnable {

    private final Player player = new Player();
    private Thread gameThread;

    public GamePanel() {
        this.setPreferredSize(new Dimension(ScreenConstants.SCREEN_WIDTH, ScreenConstants.SCREEN_HEIGHT));
        this.addKeyListener(new CustomKeyListener());
        this.setFocusable(true);

        gameThread = new Thread(this, "Mad bob");
        gameThread.start();
    }

    public void paint(Graphics g) {
        draw(g);
    }

    private void draw(Graphics g) {
        player.draw(g);
    }

    private void update() {
        player.update();
    }

    private class CustomKeyListener extends KeyAdapter {
        public void keyPressed(KeyEvent e) {
            switch (e.getKeyCode()) {
                case KeyEvent.VK_RIGHT:
                    player.moveRight();
                    break;
                case KeyEvent.VK_SPACE:
                case KeyEvent.VK_UP:
                    player.jump();
                    break;
                case KeyEvent.VK_LEFT:
                    player.moveLeft();
                    break;
            }
        }
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

    private class CustomMouseListener extends MouseAdapter {
        @Override
        public void mouseClicked(MouseEvent e) {
            super.mouseClicked(e);
        }
    }

}
