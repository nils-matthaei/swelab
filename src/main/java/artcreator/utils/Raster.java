package artcreator.utils;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Raster {
    private RasterElement[][] elements; // 2D array of RasterElements

    public Raster(int horizontalCount, int verticalCount) {
        this.elements = new RasterElement[verticalCount][horizontalCount]; // Initialize the 2D array
    }

    public void setElement(int x, int y, RasterElement element) {
        elements[y][x] = element; // Set the RasterElement at the specified position
    }

    public RasterElement getElement(int x, int y) {
        return elements[y][x]; // Get the RasterElement at the specified position
    }

    public RasterElement[][] getElements() {
        return elements; // Return the 2D array of RasterElements
    }

    public void binaryRaster(Configuration config) {
        double binaryThreshold = config.getBinaryThreshold();

        for (RasterElement[] row : elements) {
            for (RasterElement element : row) {
                Color color = element.getColor(); // Get the current color of the RasterElement
                int grayValue = (int) (0.299 * color.getRed() + 0.587 * color.getGreen() + 0.114 * color.getBlue()); // Calculate gray value

                if (grayValue < binaryThreshold) {
                    element.setColor(Color.BLACK); // Set to black
                } else {
                    element.setColor(Color.WHITE); // Set to white
                }
            }
        }
    }
}

