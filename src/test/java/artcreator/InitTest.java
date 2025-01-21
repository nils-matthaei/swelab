package artcreator;

import artcreator.utils.Configuration;
import artcreator.utils.ImportedImage;
import artcreator.utils.Stick;
import artcreator.utils.Template;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.awt.Color;

import artcreator.creator.CreatorFactory;
import artcreator.creator.port.Creator;
import artcreator.domain.DomainFactory;
import artcreator.domain.port.Domain;
import artcreator.statemachine.StateMachineFactory;
import artcreator.statemachine.port.StateMachine;
import artcreator.statemachine.port.Subject;
import artcreator.statemachine.port.Observer;
import artcreator.statemachine.port.State;
import artcreator.statemachine.port.State.S;

import java.awt.*;

class InitTest implements Observer {

	State s;

	@Test
	void testStateMachine() {
		StateMachine stateMachine = StateMachineFactory.FACTORY.stateMachine();
		Assertions.assertNotNull(stateMachine);
	}
	@Test
	void testStateMachineFactory() {
		StateMachine stateMachine = StateMachineFactory.FACTORY.stateMachine();
		Assertions.assertNotNull(stateMachine);
		Subject subject = StateMachineFactory.FACTORY.subject();
		Assertions.assertEquals(stateMachine, subject);
	}
	@Test
	void testDomainFactory() {
		Domain domain = DomainFactory.FACTORY.domain();
		Assertions.assertNotNull(domain);
	}
	@Test
	void testCreatorFactory() {
		Creator creator = CreatorFactory.FACTORY.creator();
		Assertions.assertNotNull(creator);
	}
	@Test
	void testCreatorFactoryLoadImage() {
		Subject subject = StateMachineFactory.FACTORY.subject();
		subject.attach(this);
		StateMachine stateMachine = StateMachineFactory.FACTORY.stateMachine();
		Creator creator = CreatorFactory.FACTORY.creator();
		Assertions.assertNotNull(creator);
		creator.loadImageFromPath("src/main/fuchs.jpg");
		Assertions.assertTrue(stateMachine.getState().isSubStateOf(S.IMAGE_IMPORTED));
		Assertions.assertEquals(S.IMAGE_IMPORTED, this.s);
		subject.detach(this);
	}
	@Test
	void testStartGeneration() {
		Subject subject = StateMachineFactory.FACTORY.subject();
		subject.attach(this);
		Creator creator = CreatorFactory.FACTORY.creator();
		creator.loadImageFromPath("src/main/fuchs.jpg");
		subject.detach(this);
		creator.startGeneration();
	}

//	@Test
//	void test() {
//		StateMachine stateMachine = StateMachineFactory.FACTORY.stateMachine();
//		Assertions.assertNotNull(stateMachine);
//		Subject subject = StateMachineFactory.FACTORY.subject();
//		Assertions.assertEquals(stateMachine, subject);
//		subject.attach(this);
//
//		Domain domain = DomainFactory.FACTORY.domain();
//		Assertions.assertNotNull(domain);
//
//		Creator creator = CreatorFactory.FACTORY.creator();
//		Assertions.assertNotNull(creator);
//
//		creator.loadImageFromPath("src/main/fuchs.jpg");
//
//		Assertions.assertTrue(stateMachine.getState().isSubStateOf(S.IMAGE_IMPORTED));
//		Assertions.assertEquals(S.IMAGE_IMPORTED, this.s);
//		subject.detach(this);
//
//	}

	private ImportedImage importedImage;

	@BeforeEach
	public void setUp() {
		importedImage = new ImportedImage(10, 10);
	}

	@Test
	public void testGetPixelArray() {
		int[][] pixelArray = importedImage.getPixelArray();
		Assertions.assertNotNull(pixelArray);
		Assertions.assertEquals(10, pixelArray.length);
		Assertions.assertEquals(10, pixelArray[0].length);
	}

	@Test
	public void testGetWidth() {
		Assertions.assertEquals(10, importedImage.getWidth());
	}

	@Test
	public void testGetHeight() {
		Assertions.assertEquals(10, importedImage.getHeight());
	}

	@Test
	public void testGetRaster() {
		Assertions.assertNull(importedImage.getRaster());
	}

	@Test
	public void testSetPixel() {
		importedImage.setPixel(5, 5, Color.RED.getRGB());
		Assertions.assertEquals(Color.RED.getRGB(), importedImage.getPixelArray()[5][5]);
	}

	@Test
	public void testSetPixelOutOfBounds() {
		Assertions.assertThrows(IndexOutOfBoundsException.class, () -> {
			importedImage.setPixel(10, 10, Color.RED.getRGB());
		});
	}

	@Test
	public void testCreateRaster() {
		Template template = new Template();
		template.addStick(new Stick("Black", 20, 5));
		template.addStick(new Stick("Red",30, 40));
		template.calcDimensions(importedImage, new Configuration());

		importedImage.createRaster(template);
		Assertions.assertNotNull(importedImage.getRaster());
	}

	@Test
	public void testRasterToImage() {
		Template template = new Template();
		template.addStick(new Stick("Black", 20, 5));
		template.addStick(new Stick("Red",30, 40));
		template.calcDimensions(importedImage, new Configuration());

		importedImage.createRaster(template);
		ImportedImage newImage = importedImage.rasterToImage();
		Assertions.assertNotNull(newImage);
		Assertions.assertEquals(importedImage.getWidth(), newImage.getWidth());
		Assertions.assertEquals(importedImage.getHeight(), newImage.getHeight());
	}

	@Test
	public void testWriteToFile() {
		// This test will be a bit tricky since it involves file I/O.
		// You can mock the file I/O or use a temporary file for testing.
	}

//	@Test
//	public void testCalculateAverageColor() {
//		importedImage.setPixel(0, 0, Color.RED.getRGB());
//		importedImage.setPixel(0, 1, Color.GREEN.getRGB());
//		importedImage.setPixel(1, 0, Color.BLUE.getRGB());
//		importedImage.setPixel(1, 1, Color.WHITE.getRGB());
//
//		Color avgColor = importedImage.calculateAverageColor(0, 0, 2, 2);
//		Assertions.assertNotNull(avgColor);
//	}

	@Override
	public void update(State currentState) {
		this.s = currentState;
	}

}
