import java.awt.*;
//TODO think about making class static singleton to have ability of easy accessing to player's api
public class Player {
    private static final int PLAYER_HEIGHT = 100;
    private static final int MAX_Y = ScreenConstants.SCREEN_HEIGHT - PLAYER_HEIGHT;
    private Image view;

    public Player() {

    }

    public void update() {

    }

    public void draw(Graphics g) {
        g.setColor(Color.RED);
        g.drawRect(ScreenMovement.PLAYER_X, ScreenMovement.getPlayerY(), ScreenMovement.getPlayerHitbox().width, ScreenMovement.getPlayerHitbox().height);
    }
}
