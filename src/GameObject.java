import java.awt.*;
import java.awt.image.BufferedImage;

public class GameObject extends Rectangle {
    private static final int scaledSize = 40;

    private Image view;

    public GameObject(BufferedImage view, int x, int y) {
        super(x, y, scaledSize, scaledSize);
        this.view = resizeImage(view);
    }

    public void setView(BufferedImage view) {
        this.view = resizeImage(view);
    }

    public int getRightX() {
        return x + width;
    }

    public int getLowerY() {
        return y + height;
    }

    private Image resizeImage(BufferedImage originalImage) {
        return originalImage.getScaledInstance(scaledSize, scaledSize, Image.SCALE_DEFAULT);
    }

    public Image getView() {
        return view;
    }
}
