package artcreator.gui;

import java.util.TooManyListenersException;

import javax.swing.*;

import artcreator.creator.CreatorFactory;
import artcreator.creator.port.Creator;
import artcreator.statemachine.StateMachineFactory;
import artcreator.statemachine.port.Observer;
import artcreator.statemachine.port.State;
import artcreator.statemachine.port.Subject;


public class CreatorFrame extends JFrame implements Observer {

	private static final long serialVersionUID = 1L;

	private transient Creator creator = CreatorFactory.FACTORY.creator();
	private transient Subject subject = StateMachineFactory.FACTORY.subject();
	private transient Controller controller;

	private static final int WIDTH = 600;
	private static final int HEIGHT = 500;

	private JButton btn = new JButton("Import Image");
	private JPanel panel = new JPanel();
	private JTextField textField = new JTextField();

	public CreatorFrame() throws TooManyListenersException {
		super("ArtCreator");
		this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		this.setSize(WIDTH, HEIGHT);
		this.setLocationRelativeTo(null);
		this.subject.attach(this);
		this.controller = new Controller(this, subject, creator);

		/* build view */
		this.btn.addActionListener(this.controller);
		this.panel.add(this.btn);

		textField.setBounds(50, 30, 200, 300);
		this.panel.add(textField);

		this.getContentPane().add(this.panel);

	}

	public void update(State newState) {
		/* modify view if necessary */}

	public String getTextFieldContents() {
		return textField.getText(); // Method to get the text field contents
	}
}
