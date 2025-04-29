import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Calculator extends JFrame implements ActionListener {
    private JTextField display;
    private StringBuilder currentInput;
    private double operand1;
    private double operand2;
    private String operator;
    
    public Calculator() {
        // Setting up the JFrame
        setTitle("Calculator");
        setSize(300, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        
        // Initializing the display
        display = new JTextField();
        display.setFont(new Font("Arial", Font.BOLD, 24));
        display.setEditable(false);
        display.setHorizontalAlignment(JTextField.RIGHT);
        
        // Initializing variables
        currentInput = new StringBuilder();
        operand1 = operand2 = 0;
        operator = "";

        // Creating buttons
        JPanel buttonPanel = new JPanel(new GridLayout(5, 4, 10, 10));
        String[] buttons = {
            "7", "8", "9", "/",
            "4", "5", "6", "*",
            "1", "2", "3", "-",
            "0", ".", "=", "+",
            "C"
        };
        
        for (String text : buttons) {
            JButton button = new JButton(text);
            button.setFont(new Font("Arial", Font.BOLD, 24));
            button.addActionListener(this);
            buttonPanel.add(button);
        }

        // Adding components to the frame
        setLayout(new BorderLayout(10, 10));
        add(display, BorderLayout.NORTH);
        add(buttonPanel, BorderLayout.CENTER);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        
        if (command.charAt(0) >= '0' && command.charAt(0) <= '9' || command.equals(".")) {
            currentInput.append(command);
            display.setText(currentInput.toString());
        } else if (command.equals("C")) {
            currentInput.setLength(0);
            operand1 = operand2 = 0;
            operator = "";
            display.setText("");
        } else if (command.equals("=")) {
            if (!operator.isEmpty()) {
                operand2 = Double.parseDouble(currentInput.toString());
                double result = performCalculation(operand1, operand2, operator);
                display.setText(String.valueOf(result));
                currentInput.setLength(0);
                currentInput.append(result);
                operator = "";
            }
        } else {
            if (currentInput.length() > 0) {
                operand1 = Double.parseDouble(currentInput.toString());
                operator = command;
                currentInput.setLength(0);
            }
        }
    }
    
    private double performCalculation(double op1, double op2, String op) {
        switch (op) {
            case "+": return op1 + op2;
            case "-": return op1 - op2;
            case "*": return op1 * op2;
            case "/": return op1 / op2;
            default: return 0;
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Calculator calc = new Calculator();
            calc.setVisible(true);
        });
    }
}
