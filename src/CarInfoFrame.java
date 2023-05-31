import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class CarInfoFrame extends JFrame implements ActionListener {
    private JComboBox<String> brandComboBox;
    private JRadioButton manualRadioButton;
    private JRadioButton automaticRadioButton;
    private JTextField modelTextField;
    private JTextField yearTextField;
    private JTextField maxSpeedTextField;
    private JTextField colorTextField;
    private JButton addCarButton;
    private JButton searchFormButton;

    public CarInfoFrame() {
        setTitle("Car Info");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        JLabel infoLabel = new JLabel("Enter the car's information");
        infoLabel.setFont(new Font("Arial", Font.BOLD, 18));
        infoLabel.setBounds(70, 20, 350, 35);
        infoLabel.setForeground(Color.blue);

        JLabel brandLabel = new JLabel("Choose the brand:");
        brandLabel.setBounds(30, 70, 120, 20);
        brandComboBox = new JComboBox<>(new String[]{"choose","Mercedes", "BMW", "Honda", "Mazda", "Suzuki", "Hyundai"});
        brandComboBox.setBounds(180, 70, 150, 20);

        JLabel gearboxLabel = new JLabel("Choose the Gearbox:");
        gearboxLabel.setBounds(30, 100, 120, 20);
        manualRadioButton = new JRadioButton("Manual");
        manualRadioButton.setBounds(180, 100, 70, 20);
        automaticRadioButton = new JRadioButton("Automatic");
        automaticRadioButton.setBounds(250, 100, 90, 20);
        ButtonGroup gearboxButtonGroup = new ButtonGroup();
        gearboxButtonGroup.add(manualRadioButton);
        gearboxButtonGroup.add(automaticRadioButton);

        JLabel modelLabel = new JLabel("Enter the model:");
        modelLabel.setBounds(30, 130, 120, 20);
        modelTextField = new JTextField(20);
        modelTextField.setBounds(180, 130, 150, 20);

        JLabel yearLabel = new JLabel("Enter the year:");
        yearLabel.setBounds(30, 160, 120, 20);
        yearTextField = new JTextField(20);
        yearTextField.setBounds(180, 160, 150, 20);

        JLabel maxSpeedLabel = new JLabel("Enter the max speed:");
        maxSpeedLabel.setBounds(30, 190, 120, 20);
        maxSpeedTextField = new JTextField(20);
        maxSpeedTextField.setBounds(180, 190, 150, 20);

        JLabel colorLabel = new JLabel("Enter the color:");
        colorLabel.setBounds(30, 220, 120, 20);
        colorTextField = new JTextField(20);
        colorTextField.setBounds(180, 220, 150, 20);

        add(infoLabel);
        add(brandLabel);
        add(brandComboBox);
        add(gearboxLabel);
        add(manualRadioButton);
        add(automaticRadioButton);
        add(modelLabel);
        add(modelTextField);
        add(yearLabel);
        add(yearTextField);
        add(maxSpeedLabel);
        add(maxSpeedTextField);
        add(colorLabel);
        add(colorTextField);

        addCarButton = new JButton("Add car to the file");
        addCarButton.setBounds(30, 260, 300, 25);
        addCarButton.setForeground(Color.red);
        searchFormButton = new JButton("Open the search form");
        searchFormButton.setBounds(30, 300, 300, 25);
        searchFormButton.setForeground(Color.red);
        add(addCarButton);
        add(searchFormButton);

        addCarButton.addActionListener(this);
        searchFormButton.addActionListener(this);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == addCarButton) {
             saveCarInfoToFile();
        } else if (e.getSource() == searchFormButton) {
            openSearchForm();
        }
    }
    private void saveCarInfoToFile() {
        try {
            File file = new File("C:\\Users\\abdul\\Desktop\\car_info.txt");
            FileWriter writer = new FileWriter(file, true);
            String brand = brandComboBox.getSelectedItem().toString();
            String gearbox = manualRadioButton.isSelected() ? "Manual" : "Automatic";
            String model = modelTextField.getText();
            String year = yearTextField.getText();
            String maxSpeed = maxSpeedTextField.getText();
            String color = colorTextField.getText();

            writer.write(brand + "\t");
            writer.write( gearbox + "\t");
            writer.write( model + "\t");
            writer.write( year + "\t");
            writer.write( maxSpeed + "\t");
            writer.write(color + "\n");
            writer.close();

            JOptionPane.showMessageDialog(this, "Car information saved to file.");
        } catch (IOException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error saving car information to file.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    private void openSearchForm() {
        JFrame searchFrame = new JFrame("Search Form");
        searchFrame.setSize(500, 200);
        searchFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        searchFrame.setLayout(new FlowLayout(FlowLayout.LEFT));

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        JPanel labelsPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JPanel textFieldsPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));

        String[] labels = {"Brand", "Gearbox", "Model", "Year", "Max Speed","Color"};
        JLabel[] labelComponents = new JLabel[labels.length];
        JTextField[] textFields = new JTextField[labels.length];

        for (int i = 0; i < labels.length; i++) {
            JLabel label = new JLabel(labels[i]);
            JTextField textField = new JTextField(6);

            labelComponents[i] = label;
            label.setBorder(BorderFactory.createEmptyBorder(0, 13, 0, 17));
            textFields[i] = textField;

            labelsPanel.add(label);
            textFieldsPanel.add(textField);
        }

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));
        JButton findButton = new JButton("Find the Fastest car");
        JButton clearButton = new JButton("Clear");

        findButton.setMaximumSize(new Dimension(Integer.MAX_VALUE, findButton.getPreferredSize().height));

        buttonPanel.add(findButton);
        buttonPanel.add(Box.createRigidArea(new Dimension(50, 0))); // Add some space between buttons
        buttonPanel.add(clearButton);

        panel.add(labelsPanel);
        panel.add(textFieldsPanel);
        panel.add(buttonPanel);

        searchFrame.add(panel);

        searchFrame.setVisible(true);
        findButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String[] values = new String[textFields.length];
                for (int i = 0; i < textFields.length; i++) {
                    values[i] = textFields[i].getText();
                }

                // Perform search based on the entered criteria
                // Display the results or take appropriate action
            }
        });

        clearButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                for (JTextField textField : textFields) {
                    textField.setText("");
                }
            }
        });
    }
}



