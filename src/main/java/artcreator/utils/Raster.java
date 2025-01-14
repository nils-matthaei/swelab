package artcreator.utils;

import java.util.ArrayList;
import java.util.List;

public class Raster {
    private List<RasterElement> elements;

    public Raster() {
        this.elements = new ArrayList<>();
    }

    public void addElement(RasterElement element) {
        elements.add(element);
    }

    public List<RasterElement> getElements() {
        return elements;
    }

    @Override
    public String toString() {
        return "Raster{" +
                "elements=" + elements +
                '}';
    }
}

