import java.awt.*;
import java.awt.image.ImageObserver;
import java.util.Set;

public class ScreenMovement {
    private Player player;
    private ObjectsHolder objectsHolder;

    private static final int PLAYER_WIDTH = 10;
    public static final int PLAYER_X = ScreenConstants.SCREEN_WIDTH / 2 - PLAYER_WIDTH / 2;
    private static final int PLAYER_HEIGHT = 100;
    private static final int MAX_Y = ScreenConstants.SCREEN_HEIGHT - PLAYER_HEIGHT;
    private static final int MAX_JUMP_HEIGHT = 200;
    private static final int JUMP_SPEED = 10;
    private static final Rectangle playerHitbox = new Rectangle(PLAYER_X, MAX_Y, PLAYER_WIDTH, PLAYER_HEIGHT);

    private static int screenY = 0;
    private static int screenX = 0;
    private int speed = 5;
    private boolean isMovingUp;
    private Set<GameObject> gameObjects;

    //TODO перенести в этот класс управление отображением ВСЕХ объектов на экране, так же как и пересечением игрока и объектов.

    public ScreenMovement(Player player, ObjectsHolder objectsHolder) {
        this.player = player;
        this.objectsHolder = objectsHolder;
    }

    public void goLeft() {
        if (isAbleToMoveLeft()) {
            screenX += speed;
        }
    }

    public void goRight() {
        if (isAbleToMoveRight()) {
            screenX -= speed;
        }
    }

    public void draw(Graphics g, ImageObserver observer) {
        objectsHolder.drawObjects(g, observer);
        player.draw(g);
    }

    // update player, update objects, perform all loop logics
    public void update() {
        gameObjects = objectsHolder.getActiveObjects();
        performYLogics();
    }

    public static Rectangle getPlayerHitbox() {
        return playerHitbox;
    }

    public static int getScreenX() {
        return screenX;
    }

    public static int getScreenY() {
        return screenY;
    }

    public static int getPlayerY() {
        return playerHitbox.y;
    }

    public void jump() {
        if (!isMovingUp && playerHitbox.y <= playerHitbox.height) {
            return;
        }
        isMovingUp = true;
    }

    private void performYLogics() {
        if (isMovingUp) {
            if (playerHitbox.y < MAX_Y - MAX_JUMP_HEIGHT) {
                isMovingUp = false;
            } else {
                updateHitbox(-JUMP_SPEED);
            }
        } else if (canMoveDown()) {
            updateHitbox(JUMP_SPEED);
        }
    }

    private boolean canMoveDown() {
        return playerHitbox.y + JUMP_SPEED < MAX_Y
                && gameObjects
                .stream()
                .noneMatch(gameObject -> gameObject.contains(
                        playerHitbox.x + getScreenX(), playerHitbox.y + playerHitbox.height + JUMP_SPEED));
    }

    private void updateHitbox(int delta) {
        playerHitbox.y += delta;
    }

    private int getXOnScreen(int x) {
        return x - screenX;
    }

    private int getYOnScreen(int y) {
        return y + screenY;
    }

    private boolean isAbleToMoveRight() {
        return isAbleToMoveX(speed);
    }

    private boolean isAbleToMoveLeft() {
        return isAbleToMoveX(-speed);
    }

    private boolean isAbleToMoveX(int delta) {
        return gameObjects.stream()
                .noneMatch(object -> playerHitbox.contains(getXOnScreen(object.x + delta), getYOnScreen(object.y))
                        || playerHitbox.contains(getXOnScreen(object.x + object.width + delta), getYOnScreen(object.y)));
    }
}
