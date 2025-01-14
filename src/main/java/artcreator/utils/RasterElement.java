package artcreator.utils;

import java.awt.*;

public class RasterElement {
    private float size;
    private int x;
    private int y;
    private Color color;

    public RasterElement(float size, int x, int y) {
        this.size = size;
        this.x = x;
        this.y = y;
    }

    public float getSize() {
        return size;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    @Override
    public String toString() {
        return "RasterElement{" +
                "size=" + size +
                ", x=" + x +
                ", y=" + y +
                '}';
    }
}

