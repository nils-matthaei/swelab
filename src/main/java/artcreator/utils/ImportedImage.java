package artcreator.utils;

public class ImportedImage {
    private int[][] pixelArray; // 2D array to represent pixel values
    private int width;
    private int height;
    private Raster raster;

    public ImportedImage(int width, int height) {
        this.width = width;
        this.height = height;
        this.pixelArray = new int[height][width]; // Initialize pixel array
        this.raster = new Raster(); // Initialize raster
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
}
