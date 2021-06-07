import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.ImageObserver;
import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class ObjectsHolder {
    private Set<GameObject> allObjects = new HashSet<>();
    private Set<GameObject> activeObjects = new HashSet<>();
    private static final int MIN_X = 0;
    private static final int MAX_X = ScreenConstants.SCREEN_WIDTH;

    public ObjectsHolder() {
        try {
            GameObject box = new GameObject(ImageIO.read(new File("src/res/cross.png")), 600, 760);
            allObjects.add(box);
            GameObject box1 = new GameObject(ImageIO.read(new File("src/res/cross.png")), 900, 760);
            allObjects.add(box1);
        } catch (IOException e) {}
    }

    public void drawObjects(Graphics g, ImageObserver observer) {
        activeObjects.forEach(object -> {
            g.drawImage(object.getView(), object.x - ScreenMovement.getScreenX(), object.y + ScreenMovement.getScreenY(), observer);
        });
    }

    public Set<GameObject> getActiveObjects() {
        updateObjectsInScreen();
        return activeObjects;
    }

    public void updateObjectsInScreen() {
        activeObjects.clear();
        activeObjects.addAll(
                allObjects.stream()
                        .filter(gameObject ->
                                this.xInScreen(gameObject) &&
                                this.yInScreen(gameObject))
                        .collect(Collectors.toSet()));
    }

    private boolean yInScreen(GameObject gameObject) {
        return gameObject.y + gameObject.height > 0
                || gameObject.y < ScreenConstants.SCREEN_HEIGHT;
    }

    private boolean xInScreen(GameObject gameObject) {
        return gameObject.x + gameObject.getWidth() > MIN_X + ScreenMovement.getScreenX()
                || gameObject.x < MAX_X + ScreenMovement.getScreenY();
    }
}
