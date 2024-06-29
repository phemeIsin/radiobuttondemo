import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.IOException;

public class RadioButtonDemo extends JFrame {

    private JRadioButton birdButton;
    private JRadioButton catButton;
    private JRadioButton dogButton;
    private JRadioButton rabbitButton;
    private JRadioButton pigButton;
    private ButtonGroup group;
    private JLabel imageLabel;
    private JMenuBar menuBar;
    private JMenu petMenu;
    private JMenuItem birdMenuItem;
    private JMenuItem catMenuItem;
    private JMenuItem dogMenuItem;
    private JMenuItem rabbitMenuItem;
    private JMenuItem pigMenuItem;

    public RadioButtonDemo() {
        // Initialize components
        birdButton = new JRadioButton("Bird");
        catButton = new JRadioButton("Cat");
        dogButton = new JRadioButton("Dog");
        rabbitButton = new JRadioButton("Rabbit");
        pigButton = new JRadioButton("Pig");
        group = new ButtonGroup();
        imageLabel = new JLabel();
        menuBar = new JMenuBar();
        petMenu = new JMenu("Pets");
        birdMenuItem = new JMenuItem("Bird");
        catMenuItem = new JMenuItem("Cat");
        dogMenuItem = new JMenuItem("Dog");
        rabbitMenuItem = new JMenuItem("Rabbit");
        pigMenuItem = new JMenuItem("Pig");

        // Add the selection buttons to group
        group.add(birdButton);
        group.add(catButton);
        group.add(dogButton);
        group.add(rabbitButton);
        group.add(pigButton);

        // Add action listeners for radio buttons
        birdButton.addActionListener(this::showImage);
        catButton.addActionListener(this::showImage);
        dogButton.addActionListener(this::showImage);
        rabbitButton.addActionListener(this::showImage);
        pigButton.addActionListener(this::showImage);

        // Add action listeners for menu items
        birdMenuItem.addActionListener(this::showImageFromMenu);
        catMenuItem.addActionListener(this::showImageFromMenu);
        dogMenuItem.addActionListener(this::showImageFromMenu);
        rabbitMenuItem.addActionListener(this::showImageFromMenu);
        pigMenuItem.addActionListener(this::showImageFromMenu);

        // Add menu items to menu
        petMenu.add(birdMenuItem);
        petMenu.add(catMenuItem);
        petMenu.add(dogMenuItem);
        petMenu.add(rabbitMenuItem);
        petMenu.add(pigMenuItem);

        // Add menu to menu bar
        menuBar.add(petMenu);

        // Set layout and add components
        JPanel radioPanel = new JPanel(new GridLayout(5, 1));
        radioPanel.add(birdButton);
        radioPanel.add(catButton);
        radioPanel.add(dogButton);
        radioPanel.add(rabbitButton);
        radioPanel.add(pigButton);

        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, radioPanel, imageLabel);
        splitPane.setDividerLocation(150);

        setLayout(new BorderLayout());
        add(menuBar, BorderLayout.WEST);
        add(splitPane, BorderLayout.CENTER);

        // Set frame properties 
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 400);
        setVisible(true);
    }

    private void showImage(ActionEvent evt) {
        JRadioButton source = (JRadioButton) evt.getSource();
        String selectedPet = source.getText();
        String imagePath = "/images/" + selectedPet.toLowerCase() + ".jpg";
        displayImage(imagePath, selectedPet);
    }

    private void showImageFromMenu(ActionEvent evt) {
        JMenuItem source = (JMenuItem) evt.getSource();
        String selectedPet = source.getText();
        String imagePath = "/images/" + selectedPet.toLowerCase() + ".jpg";
        displayImage(imagePath, selectedPet);
    }

    private void displayImage(String imagePath, String selectedPet) {
        try {
            BufferedImage img = ImageIO.read(getClass().getResource(imagePath));
            if (img != null) {
                Image scaledImage = img.getScaledInstance(imageLabel.getWidth(), imageLabel.getHeight(), Image.SCALE_SMOOTH);
                ImageIcon icon = new ImageIcon(scaledImage);
                imageLabel.setIcon(icon);
                JOptionPane.showMessageDialog(this, "You selected: " + selectedPet);
            } else {
               JOptionPane.showMessageDialog(this, "Image not found: " + imagePath);
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error loading image: " + imagePath);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(RadioButtonDemo::new);
    }
}