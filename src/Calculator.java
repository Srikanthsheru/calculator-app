 import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Calculator {
    // Frame
    private JFrame frame;
    // Text field to display input and result
    private JTextField textField;
    // Variables for calculations
    private double num1, num2, result;
    private String operator;

    public Calculator() {
        // Initialize the frame
        frame = new JFrame("Calculator");
        frame.setSize(400, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        
        // Initialize the text field
        textField = new JTextField();
        textField.setEditable(false);
        frame.add(textField, BorderLayout.NORTH);
        
        // Create panel for buttons
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 4));
        frame.add(panel, BorderLayout.CENTER);
        
        // Button labels
        String[] buttonLabels = {
            "7", "8", "9", "/",
            "4", "5", "6", "*",
            "1", "2", "3", "-",
            "C", "0", "=", "+"
        };
        
        // Add buttons to the panel
        for (String label : buttonLabels) {
            JButton button = new JButton(label);
            button.addActionListener(new ButtonClickListener());
            panel.add(button);
        }
        
        // Initialize operator and operands
        operator = "";
        num1 = num2 = result = 0;

        // Set frame visibility
        frame.setVisible(true);
    }
    
    // ActionListener for button clicks
    private class ButtonClickListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            JButton source = (JButton) e.getSource();
            String command = source.getText();
            
            // Handle number buttons
            if (command.charAt(0) >= '0' && command.charAt(0) <= '9') {
                textField.setText(textField.getText() + command);
            } 
            // Handle clear button
            else if (command.equals("C")) {
                textField.setText("");
                operator = "";
                num1 = num2 = result = 0;
            } 
            // Handle equal button
            else if (command.equals("=")) {
                num2 = Double.parseDouble(textField.getText());
                switch (operator) {
                    case "+":
                        result = num1 + num2;
                        break;
                    case "-":
                        result = num1 - num2;
                        break;
                    case "*":
                        result = num1 * num2;
                        break;
                    case "/":
                        result = num1 / num2;
                        break;
                }
                textField.setText(String.valueOf(result));
                operator = "";
            } 
            // Handle operator buttons
            else {
                if (!operator.isEmpty()) {
                    num1 = Double.parseDouble(textField.getText());
                    textField.setText("");
                }
                operator = command;
                num1 = Double.parseDouble(textField.getText());
                textField.setText("");
            }
        }
    }

    public static void main(String[] args) {
        new Calculator();
    }
}
