package artcreator.gui;

import java.awt.*;
import java.io.Serial;
import java.util.TooManyListenersException;

import javax.swing.*;

import artcreator.creator.CreatorFactory;
import artcreator.creator.port.Creator;
import artcreator.statemachine.StateMachineFactory;
import artcreator.statemachine.port.Observer;
import artcreator.statemachine.port.State;
import artcreator.statemachine.port.Subject;

public class CreatorFrame extends JFrame implements Observer {

    @Serial
    private static final long serialVersionUID = 1L;

    private transient Creator creator = CreatorFactory.FACTORY.creator();
    private transient Subject subject = StateMachineFactory.FACTORY.subject();
    private transient Controller controller;

    private static final int WIDTH = 600;
    private static final int HEIGHT = 500;

    private final JPanel panel = new JPanel();
    private final JTextField textField = new JTextField();
    private final JLabel imageLabel = new JLabel();
    private ImageIcon imageIcon;

    public CreatorFrame() throws TooManyListenersException {
        super("ArtCreator");
        this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        this.setSize(WIDTH, HEIGHT);
        this.setLocationRelativeTo(null);
        this.subject.attach(this);
        this.controller = new Controller(this, subject, creator);
        panel.setLayout(new BorderLayout());
        JPanel topPanel = new JPanel();
        topPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        JButton btn = new JButton("Import Image");
        btn.setPreferredSize(new Dimension(300, 20));
        btn.addActionListener(this.controller);
        topPanel.add(btn);
        this.textField.setPreferredSize(new Dimension(300, 20));
        topPanel.add(this.textField);
        panel.add(topPanel, BorderLayout.NORTH);
        imageLabel.setHorizontalAlignment(SwingConstants.CENTER);
        panel.add(imageLabel, BorderLayout.CENTER);
        this.getContentPane().add(panel);
    }

    public void update(State newState) {
        if (newState.isSubStateOf(State.S.IMAGE_IMPORTED)) {
            imageIcon = new ImageIcon(this.getTextFieldContents());
            System.out.println(this.getTextFieldContents());
            int originalWidth = imageIcon.getIconWidth();
            int originalHeight = imageIcon.getIconHeight();
            int scaledWidth = 1250;
            int scaledHeight = scaledWidth * originalHeight / originalWidth;
            Image scaledImage = imageIcon.getImage().getScaledInstance(scaledWidth, scaledHeight, Image.SCALE_SMOOTH);
            ImageIcon scaledIcon = new ImageIcon(scaledImage);
            this.imageLabel.setIcon(scaledIcon);
            this.imageLabel.setText("");
            JButton btn = new JButton("Start generation");
            btn.setPreferredSize(new Dimension(300, 20));
            btn.addActionListener(this.controller);
            panel.add(btn, BorderLayout.SOUTH);
            this.panel.revalidate();
            this.panel.repaint();
        }

        if (newState.isSubStateOf(State.S.TEMPLATE_GENERATED)) {
            imageIcon = new ImageIcon("/home/schasch/Documents/HKA/05Semester/SWE/Labor/swelab/outFile.png");
            int originalWidth = imageIcon.getIconWidth();
            int originalHeight = imageIcon.getIconHeight();
            int scaledWidth = 1250;
            int scaledHeight = scaledWidth * originalHeight / originalWidth;
            Image scaledImage = imageIcon.getImage().getScaledInstance(scaledWidth, scaledHeight, Image.SCALE_SMOOTH);
            ImageIcon scaledIcon = new ImageIcon(scaledImage);
            this.imageLabel.setIcon(scaledIcon);
            this.imageLabel.setText(""););
            this.panel.revalidate();
            this.panel.repaint();
        }
    }

    public String getTextFieldContents() {
        return textField.getText();
    }
}
