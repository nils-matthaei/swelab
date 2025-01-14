package artcreator.utils;

public class Stick {
    private String color; // Color of the stick
    private int x;        // X coordinate
    private int y;        // Y coordinate

    public Stick(String color, int x, int y) {
        this.color = color;
        this.x = x;
        this.y = y;
    }

    public String getColor() {
        return color;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    @Override
    public String toString() {
        return "Stick{" +
                "color='" + color + '\'' +
                ", x=" + x +
                ", y=" + y +
                '}';
    }
}

