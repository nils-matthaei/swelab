package artcreator.utils;

public class RasterElement {
    private float size;
    private int x;
    private int y;

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

    @Override
    public String toString() {
        return "RasterElement{" +
                "size=" + size +
                ", x=" + x +
                ", y=" + y +
                '}';
    }
}

