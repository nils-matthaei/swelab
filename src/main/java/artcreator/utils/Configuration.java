package artcreator.utils;

public class Configuration {
    // Attributes
    private int templateWidth;
    private int templateHeight;
    private float binaryThreshold;
    private int stickCount;

    // Default constructor
    public Configuration() {
        this.templateWidth = 1250;      // Default value for templateWidth
        this.templateHeight = 2000;     // Default value for templateHeight
        this.binaryThreshold = 0.6f;    // Default value for binaryThreshold
        this.stickCount = 1000;           // Default value for stickCount
    }

    // Getters and Setters
    public int getTemplateWidth() {
        return templateWidth;
    }

    public void setTemplateWidth(int templateWidth) {
        this.templateWidth = templateWidth;
    }

    public int getTemplateHeight() {
        return templateHeight;
    }

    public void setTemplateHeight(int templateHeight) {
        this.templateHeight = templateHeight;
    }

    public float getBinaryThreshold() {
        return binaryThreshold;
    }

    public void setBinaryThreshold(float binaryThreshold) {
        this.binaryThreshold = binaryThreshold;
    }

    public int getStickCount() {
        return stickCount;
    }

    public void setStickCount(int stickCount) {
        this.stickCount = stickCount;
    }

    // Optional: toString method for easy representation
    @Override
    public String toString() {
        return "Configuration{" +
                "templateWidth=" + templateWidth +
                ", templateHeight=" + templateHeight +
                ", binaryThreshold=" + binaryThreshold +
                ", stickCount=" + stickCount +
                '}';
    }
}
