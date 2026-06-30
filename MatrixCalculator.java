import javax.swing.*;
import java.awt.*;

public class MatrixCalculator {

    private JFrame calculatorFrame;

    public MatrixCalculator() {

        // Create calculator window frame
        calculatorFrame = new JFrame("Matrix Calculator");
        calculatorFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        calculatorFrame.setSize(1000, 800);
        calculatorFrame.setLocationRelativeTo(null); // Centre window

        calculatorFrame.setLayout(new BorderLayout());

        calculatorFrame.getContentPane().setBackground(Color.PINK);

        calculatorFrame.add(createInputPanel(), BorderLayout.WEST);
        calculatorFrame.add(createOutputPanel(), BorderLayout.CENTER);

        calculatorFrame.setVisible(true);
    }

    private JPanel createInputPanel() {

        JPanel panel = new JPanel();
        panel.setPreferredSize(new Dimension(250, 800));
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        panel.setOpaque(false);

        // Matrix 1
        JLabel matrixLabel = new JLabel("Matrix 1");
        matrixLabel.setFont(new Font("Monospaced", Font.BOLD, 18));

        JLabel rowLabel = new JLabel("Rows");
        JSpinner rowSpinner = new JSpinner (new SpinnerNumberModel(2, 1, 10, 1));
        
        JLabel colLabel = new JLabel("Columns");
        JSpinner colSpinner = new JSpinner (new SpinnerNumberModel(2, 1, 10, 1));
        
        JButton createMatrixButton = new JButton("Create Matrix");

        JPanel matrixPanel = new JPanel();
        matrixPanel.setPreferredSize(new Dimension(200, 150));
        matrixPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        // Operations
        JButton addButton = new JButton("Addition");
        JButton subtractButton = new JButton("Subtraction");
        JButton multiplyButton = new JButton("Multiply");
        JButton transposeButton = new JButton("Transpose");
        JButton determinantButton = new JButton("Determinant");
        JButton gaussianButton = new JButton("Guassian Elimination");

        JButton backButton = new JButton("Back");

        backButton.addActionListener(e -> {
            calculatorFrame.dispose();
            new MatrixHome();
        });

        panel.add(matrixLabel);
        panel.add(Box.createVerticalStrut(10));

        panel.add(rowLabel);
        panel.add(rowSpinner);

        panel.add(Box.createVerticalStrut(10));

        panel.add(colLabel);
        panel.add(colSpinner);

        panel.add(Box.createVerticalStrut(10));

        panel.add(createMatrixButton);

        panel.add(Box.createVerticalStrut(10));

        panel.add(matrixPanel);

        panel.add(Box.createVerticalStrut(10));

        panel.add(addButton);
        panel.add(subtractButton);
        panel.add(multiplyButton);
        panel.add(transposeButton);
        panel.add(determinantButton);
        panel.add(gaussianButton);

        panel.add(Box.createVerticalGlue());

        panel.add(backButton);

        return panel;
    }

    private JPanel createOutputPanel() {

        JPanel panel = new JPanel(new BorderLayout());
        panel.setOpaque(false);

        JLabel title = new JLabel("Calculation", SwingConstants.CENTER);
        title.setFont(new Font("Monospaced", Font.BOLD, 30));

        JTextArea outputArea = new JTextArea();
        outputArea.setEditable(false);
        outputArea.setFont(new Font("Monospaced", Font.PLAIN, 18));

        panel.add(title, BorderLayout.NORTH);
        panel.add(new JScrollPane(outputArea), BorderLayout.CENTER);

        return panel;
    }
}
