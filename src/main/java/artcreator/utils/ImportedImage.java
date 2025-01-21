package artcreator.utils;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;

public class ImportedImage {
    private int[][] pixelArray; // 2D array to represent pixel values
    private int width;
    private int height;
    private Raster raster;

    public ImportedImage(int width, int height) {
        this.width = width;
        this.height = height;
        this.pixelArray = new int[height][width]; // Initialize pixel array
    }

    public int[][] getPixelArray() {
        return pixelArray;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public Raster getRaster() {
        return raster;
    }

    public void setPixel(int x, int y, int value) {
        if (x >= 0 && x < width && y >= 0 && y < height) {
            pixelArray[y][x] = value; // Set pixel value
        } else {
            throw new IndexOutOfBoundsException("Pixel coordinates out of bounds");
        }
    }

    public void createRaster(Template template) {
        int horizontalCount = template.getHorizontalStickCount();
        int verticalCount = template.getVerticalStickCount();

        // Create a new Raster with the specified dimensions
        this.raster = new Raster(horizontalCount, verticalCount);

        double elementWidth = (double) width / horizontalCount;
        double elementHeight = (double) height / verticalCount;

        for (int y = 0; y < verticalCount; y++) {
            for (int x = 0; x < horizontalCount; x++) {
                int startX = (int) (x * elementWidth);
                int startY = (int) (y * elementHeight);
                int endX = (int) Math.min((x + 1) * elementWidth, width);
                int endY = (int) Math.min((y + 1) * elementHeight, height);

                Color averageColor = calculateAverageColor(startX, startY, endX, endY);

                RasterElement rasterElement = new RasterElement((float) elementWidth, startX, startY);
                rasterElement.setColor(averageColor);
                raster.setElement(x, y, rasterElement); // Set the RasterElement in the 2D array
            }
        }
    }

    public ImportedImage rasterToImage() {
        // Create a new ImportedImage with the same dimensions
        ImportedImage newImage = new ImportedImage(this.width, this.height);

        // Get the number of horizontal and vertical sticks from the raster
        int horizontalCount = this.raster.getElements().length; // Assuming elements are added in a grid
        int verticalCount = this.raster.getElements()[0].length; // Assuming elements are added in a grid

        // Calculate the width and height of each RasterElement
        double elementWidth = (double) width / horizontalCount;
        double elementHeight = (double) height / verticalCount;

        // Fill the new image's pixel array based on the raster elements
        for (int y = 0; y < horizontalCount; y++) {
            for (int x = 0; x < verticalCount; x++) {
                // Get the RasterElement
                RasterElement element = raster.getElement(x,y);
                Color color = element.getColor();

                // Calculate the pixel range for this RasterElement
                int startX = (int) (y * elementWidth);
                int startY = (int) (x * elementHeight);
                int endX = (int) Math.min((y + 1) * elementWidth, width);
                int endY = (int) Math.min((x + 1) * elementHeight, height);

                // Set the color for all pixels covered by this RasterElement
                for (int py = startY; py < endY; py++) {
                    for (int px = startX; px < endX; px++) {
                        newImage.setPixel(px, py, color.getRGB()); // Set the pixel color
                    }
                }
            }
        }

        return newImage; // Return the new image
    }

    public void writeToFile(String filePath, String format) {
        // Create a BufferedImage to hold the pixel data
        BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

        // Populate the BufferedImage with pixel data
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                bufferedImage.setRGB(x, y, pixelArray[y][x]); // Set the pixel color
            }
        }

        // Write the BufferedImage to a file
        try {
            File outputFile = new File(filePath);
            ImageIO.write(bufferedImage, format, outputFile); // Save the image
        } catch (IOException e) {
            System.err.println("Error writing image to file: " + e.getMessage());
        }
    }


    private Color calculateAverageColor(int startX, int startY, int endX, int endY) {
        long totalRed = 0;
        long totalGreen = 0;
        long totalBlue = 0;
        int pixelCount = 0;

        for (int y = startY; y < endY; y++) {
            for (int x = startX; x < endX; x++) {
                int rgb = pixelArray[y][x];
                Color color = new Color(rgb);
                totalRed += color.getRed();
                totalGreen += color.getGreen();
                totalBlue += color.getBlue();
                pixelCount++;
            }
        }

        // Calculate average color
        if (pixelCount > 0) {
            int avgRed = (int) (totalRed / pixelCount);
            int avgGreen = (int) (totalGreen / pixelCount);
            int avgBlue = (int) (totalBlue / pixelCount);
            return new Color(avgRed, avgGreen, avgBlue);
        } else {
            return Color.BLACK; // Default color if no pixels are covered
        }
    }
}
