import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class calculator extends JFrame implements ActionListener {

    private JTextField display;
    private double result = 0;
    private String operation = "=";
    private boolean start = true;

    public calculator() {
        display = new JTextField("0", 20);
        display.setEditable(false);
        display.setHorizontalAlignment(JTextField.RIGHT);
        
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 4, 5, 5));
        
        String[] buttonLabels = {
            "7", "8", "9", "/",
            "4", "5", "6", "*",
            "1", "2", "3", "-",
            "0", ".", "=", "+"
        };
        
        for (String label : buttonLabels) {
            JButton button = new JButton(label);
            button.addActionListener(this);
            panel.add(button);
        }

        this.add(display, BorderLayout.NORTH);
        this.add(panel, BorderLayout.CENTER);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
        this.setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        
        if (start) {
            if (command.equals(".")) {
                display.setText("0.");
                start = false;
            } else if (!command.equals("=")) {
                display.setText(command);
                start = false;
            }
        } else {
            if (command.equals(".")) {
                if (!display.getText().contains("."))
                    display.setText(display.getText() + ".");
            } else if ("+-*/".contains(command)) {
                calculate(Double.parseDouble(display.getText()));
                operation = command;
                start = true;
            } else if (command.equals("=")) {
                calculate(Double.parseDouble(display.getText()));
                start = true;
            } else {
                display.setText(display.getText() + command);
            }
        }
    }

    private void calculate(double x) {
        if (operation.equals("+")) result += x;
        else if (operation.equals("-")) result -= x;
        else if (operation.equals("*")) result *= x;
        else if (operation.equals("/")) {
            if (x != 0) result /= x;
            else display.setText("Error: Division by zero");
        }
        else result = x;
        display.setText("" + result);
    }

    public static void main(String[] args) {
        new calculator();
    }
}