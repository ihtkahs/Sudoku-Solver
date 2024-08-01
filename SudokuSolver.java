import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SudokuSolver extends JFrame {
    private int size;
    private JTextField[][] cells;

    public SudokuSolver(int size) {
        this.size = size;
        cells = new JTextField[size][size];
        setTitle("Sudoku Solver");
        setLayout(new BorderLayout());
        add(createMainPanel(), BorderLayout.CENTER);
        setSize(600 , 500);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private JPanel createMainPanel() {
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        mainPanel.setBackground(new Color(0x03346E));
        mainPanel.add(createGridPanel());
        mainPanel.add(createButtonPanel());
        return mainPanel;
    }

    private JPanel createGridPanel() {
        JPanel gridPanel = new JPanel(new GridLayout(size, size));
        gridPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        gridPanel.setBackground(new Color(0x03346E)); // Set background color
        Font cellFont = new Font("Georgia", Font.BOLD, 15);
        Border cellBorder = BorderFactory.createLineBorder(Color.GRAY, 1);

        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
                cells[row][col] = new JTextField(3);
                cells[row][col].setHorizontalAlignment(JTextField.CENTER);
                cells[row][col].setFont(cellFont);
                cells[row][col].setBorder(cellBorder);
                cells[row][col].setBackground(new Color(0xE2E2B6));
                cells[row][col].setForeground(Color.BLACK);
                gridPanel.add(cells[row][col]);
            }
        }
        return gridPanel;
    }

    private JPanel createButtonPanel() {
        JButton solveButton = new JButton("Solve");
        solveButton.setFont(new Font("Georgia", Font.BOLD, 18));
        solveButton.setBackground(new Color(100, 200, 100));
        solveButton.setForeground(Color.WHITE);
        solveButton.setFocusPainted(false);
        solveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                solveSudoku();
            }
        });

        JButton resetButton = new JButton("Reset");
        resetButton.setFont(new Font("Georgia", Font.BOLD, 18));
        resetButton.setBackground(new Color(200, 100, 100));
        resetButton.setForeground(Color.WHITE);
        resetButton.setFocusPainted(false);
        resetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                resetGrid();
            }
        });

        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(new Color(0x03346E));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(30, 0, 20, 0));
        buttonPanel.add(solveButton);
        buttonPanel.add(resetButton);
        return buttonPanel;
    }

    private void solveSudoku() {
        int[][] board = new int[size][size];
        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
                String text = cells[row][col].getText();
                if (!text.isEmpty()) {
                    board[row][col] = Integer.parseInt(text);
                }
            }
        }

        if (solve(board)) {
            for (int row = 0; row < size; row++) {
                for (int col = 0; col < size; col++) {
                    cells[row][col].setText(Integer.toString(board[row][col]));
                }
            }
        } else {
            JOptionPane.showMessageDialog(this, "No solution exists!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void resetGrid() {
        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
                cells[row][col].setText("");
            }
        }
    }

    private boolean solve(int[][] board) {
        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
                if (board[row][col] == 0) {
                    for (int num = 1; num <= size; num++) {
                        if (isValid(board, row, col, num)) {
                            board[row][col] = num;
                            if (solve(board)) {
                                return true;
                            }
                            board[row][col] = 0;
                        }
                    }
                    return false;
                }
            }
        }
        return true;
    }

    private boolean isValid(int[][] board, int row, int col, int num) {
        int sqrt = (int) Math.sqrt(size);
        for (int i = 0; i < size; i++) {
            if (board[row][i] == num || board[i][col] == num ||
                board[row - row % sqrt + i / sqrt][col - col % sqrt + i % sqrt] == num) {
                return false;
            }
        }
        return true;
    }

}
