package artcreator.creator.impl;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import artcreator.creator.port.Creator;
import artcreator.domain.port.Domain;
import artcreator.statemachine.port.StateMachine;
import artcreator.utils.Configuration;
import artcreator.utils.ImportedImage;
import artcreator.utils.Template;

public class CreatorImpl implements Creator {
	ImportedImage importedImage;
	Template template;
	Configuration configuration = new Configuration();

	public CreatorImpl(StateMachine stateMachine, Domain domain) {
		// TODO Auto-generated constructor stub
	}
	@Override
	public void startGeneration() {

	}
	@Override
	public void loadImageFromPath(String path) {
		ImportedImage importedImage = null;

		try {
			BufferedImage image = ImageIO.read(new File(path));
			int width = image.getWidth();
			int height = image.getHeight();
			importedImage = new ImportedImage(width, height);

			for (int y = 0; y < height; y++) {
				for (int x = 0; x < width; x++) {
					int rgb = image.getRGB(x, y);
					importedImage.setPixel(x, y, rgb);
				}
			}
		} catch (IOException e) {
			System.err.println("Error loading image: " + e.getMessage());
		}

		this.importedImage = importedImage;
	}
}
