import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainWindow extends JFrame {

    public MainWindow() {
        setTitle("Sudoku Solver");
        setSize(500, 350);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(new Color(0x03346E));
        add(panel);

        JLabel welcome = new JLabel("Welcome to Sudoku Solver !!!");
        welcome.setFont(new Font("Georgia Italic", Font.BOLD, 25));
        welcome.setForeground(new Color(0xE2E2B6));
        welcome.setBorder(BorderFactory.createEmptyBorder(50, 0, 20, 0)); // Added bottom padding
        welcome.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(welcome);

        JTextArea textArea = new JTextArea("Easily crack any Sudoku puzzle with our quick and powerful solver.");
        textArea.setWrapStyleWord(true);
        textArea.setLineWrap(true);
        textArea.setEditable(false);
        textArea.setFont(new Font("Georgia italic", Font.BOLD, 15));
        textArea.setMaximumSize(new Dimension(450, 30));
        textArea.setBorder(BorderFactory.createEmptyBorder(10, 10, 0, 10));
        textArea.setBackground(new Color(0x03346E));
        textArea.setForeground(new Color(0xE2E2B6));
        panel.add(textArea);

        JTextArea textArea1 = new JTextArea("Just enter your grid and let the magic happen!");
        textArea1.setWrapStyleWord(true);
        textArea1.setLineWrap(true);
        textArea1.setEditable(false);
        textArea1.setFont(new Font("Georgia italic", Font.BOLD, 15));
        textArea1.setMaximumSize(new Dimension(450, 30));
        textArea1.setBorder(BorderFactory.createEmptyBorder(5, 10, 0, 10));
        textArea1.setBackground(new Color(0x03346E));
        textArea1.setForeground(new Color(0xE2E2B6));
        panel.add(textArea1);

        JPanel gridPanel = new JPanel();
        gridPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        gridPanel.setBackground(new Color(0x03346E));
        gridPanel.setBorder(BorderFactory.createEmptyBorder(40,10,0,10));

        JLabel gridLabel = new JLabel("Enter the number of grids: ");
        gridLabel.setFont(new Font("Georgia italic", Font.BOLD, 15));
        gridLabel.setForeground(new Color(0xE2E2B6));
        gridPanel.add(gridLabel);

        JTextField gridTextField = new JTextField(10);
        gridTextField.setBackground(new Color(0xE2E2B6));
        gridPanel.add(gridTextField);

        panel.add(gridPanel);

        gridTextField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String input = gridTextField.getText();
                try {
                    int num = Integer.parseInt(input);
                    int sqrt = (int) Math.sqrt(num);
                    if (sqrt * sqrt != num) {
                        JOptionPane.showMessageDialog(null, "Please enter a perfect square number.", "Invalid Input", JOptionPane.ERROR_MESSAGE);
                    }
                    else{
                        new SudokuSolver(num);
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Please enter a valid number.", "Invalid Input", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public static void main(String args[]) {
        new MainWindow();
    }
}
