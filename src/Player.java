import java.awt.*;
//TODO think about making class static singleton to have ability of easy accessing to player's api
public class Player {
    private int y;
    private static final int PLAYER_HEIGHT = 100;
    private static final int MAX_Y = ScreenConstants.SCREEN_HEIGHT - PLAYER_HEIGHT;
    private static final int x = ScreenConstants.SCREEN_WIDTH / 2;
    private static final int MAX_JUMP_HEIGHT = 200;

    private int speed = 5;
    private boolean isMovingUp;
    private int xWithoutTouchingGround;

    public Player() {
        y = MAX_Y;
    }

    public void moveRight() {

    }

    public void moveLeft() {

    }

    public void jump() {
        isMovingUp = xWithoutTouchingGround < MAX_JUMP_HEIGHT || isMovingUp;
    }

    private void checkJumpHeight() {
        if (isMovingUp && xWithoutTouchingGround >= MAX_JUMP_HEIGHT) {
            isMovingUp = false;
        }
        if (y == MAX_Y && xWithoutTouchingGround != 0) {
            xWithoutTouchingGround = 0;
        }
    }

    private void performYLogics() {
        checkJumpHeight();
        if (isMovingUp) {
            y -= speed;
            xWithoutTouchingGround += speed;
        } else if (y <= MAX_Y) {
            y += speed;
        }
    }

    public void update() {
        performYLogics();
    }

    public void draw(Graphics g) {
        g.fillRect(x, y, 10, PLAYER_HEIGHT);
    }
}
