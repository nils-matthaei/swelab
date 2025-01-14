package artcreator.utils;

import java.util.ArrayList;
import java.util.List;

public class Template {
    private int horizontalStickCount; // Number of horizontal sticks
    private int verticalStickCount;   // Number of vertical sticks
    private List<Stick> sticks;       // List of sticks

    public Template(int horizontalStickCount, int verticalStickCount) {
        this.horizontalStickCount = horizontalStickCount;
        this.verticalStickCount = verticalStickCount;
        this.sticks = new ArrayList<>(); // Initialize the list of sticks
    }

    public int getHorizontalStickCount() {
        return horizontalStickCount;
    }

    public int getVerticalStickCount() {
        return verticalStickCount;
    }

    public List<Stick> getSticks() {
        return sticks;
    }

    public void addStick(Stick stick) {
        sticks.add(stick); // Add a stick to the list
    }

    @Override
    public String toString() {
        return "Template{" +
                "horizontalStickCount=" + horizontalStickCount +
                ", verticalStickCount=" + verticalStickCount +
                ", sticks=" + sticks +
                '}';
    }

    public void calcDimensions(ImportedImage image, Configuration configuration) {

    }
}
