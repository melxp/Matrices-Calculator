import javax.swing.*;
import java.awt.*;

public class MatrixCalculator {

    private JFrame calculatorFrame;

    private JPanel matrixPanel1;
    private JPanel matrixPanel2;

    private JTextField[][] matrixFields1;
    private JTextField[][] matrixFields2;

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
        JLabel matrixLabel1 = new JLabel("Matrix 1");
        matrixLabel1.setFont(new Font("Monospaced", Font.BOLD, 18));

        JLabel rowLabel1 = new JLabel("Rows");
        JSpinner rowSpinner1 = new JSpinner (new SpinnerNumberModel(2, 1, 10, 1));
        
        JLabel colLabel1 = new JLabel("Columns");
        JSpinner colSpinner1 = new JSpinner (new SpinnerNumberModel(2, 1, 10, 1));
        
        JButton createMatrixButton1 = new JButton("Create Matrix");
        createMatrixButton1.addActionListener(e -> {
            int rows1 = (Integer) rowSpinner1.getValue();
            int cols1 = (Integer) colSpinner1.getValue();
            matrixFields1 = createMatrixGrid(matrixPanel1, rows1, cols1);
        });

        matrixPanel1 = new JPanel();
        matrixPanel1.setPreferredSize(new Dimension(200, 200));
        matrixPanel1.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        // Matrix 2
        JLabel matrixLabel2 = new JLabel("Matrix 2");
        matrixLabel2.setFont(new Font("Monospaced", Font.BOLD, 18));

        JLabel rowLabel2 = new JLabel("Rows");
        JSpinner rowSpinner2 = new JSpinner (new SpinnerNumberModel(2, 1, 10, 1));
        
        JLabel colLabel2 = new JLabel("Columns");
        JSpinner colSpinner2 = new JSpinner (new SpinnerNumberModel(2, 1, 10, 1));
        
        JButton createMatrixButton2 = new JButton("Create Matrix");
        createMatrixButton2.addActionListener(e -> {
            int rows2 = (Integer) rowSpinner2.getValue();
            int cols2 = (Integer) colSpinner2.getValue();
            matrixFields2 = createMatrixGrid(matrixPanel2, rows2, cols2);
        });

        matrixPanel2 = new JPanel();
        matrixPanel2.setPreferredSize(new Dimension(200, 200));
        matrixPanel2.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        JButton backButton = new JButton("Back");

        backButton.addActionListener(e -> {
            calculatorFrame.dispose();
            new MatrixHome();
        });

        panel.add(matrixLabel1);
        panel.add(Box.createVerticalStrut(10));

        panel.add(rowLabel1);
        panel.add(rowSpinner1);

        panel.add(Box.createVerticalStrut(10));

        panel.add(colLabel1);
        panel.add(colSpinner1);

        panel.add(Box.createVerticalStrut(10));

        panel.add(createMatrixButton1);

        panel.add(Box.createVerticalStrut(10));

        panel.add(matrixPanel1);

        panel.add(Box.createVerticalStrut(10));

        panel.add(matrixLabel2);
        panel.add(Box.createVerticalStrut(10));

        panel.add(rowLabel2);
        panel.add(rowSpinner2);

        panel.add(Box.createVerticalStrut(10));

        panel.add(colLabel2);
        panel.add(colSpinner2);

        panel.add(Box.createVerticalStrut(10));

        panel.add(createMatrixButton2);

        panel.add(Box.createVerticalStrut(10));

        panel.add(matrixPanel2);

        panel.add(Box.createVerticalStrut(10));

        panel.add(Box.createVerticalGlue());

        panel.add(backButton);

        return panel;
    }

    private JPanel createOutputPanel() {

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setOpaque(false);

        JLabel title = new JLabel("Calculation");
        title.setFont(new Font("Monospaced", Font.BOLD, 30));
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        JTextArea outputArea = new JTextArea();
        outputArea.setEditable(false);

        // Operations
        JButton addButton = new JButton("Addition");
        addButton.addActionListener(e -> {

            try {

                if (matrixFields1 == null || matrixFields2 == null) {
                    JOptionPane.showMessageDialog(calculatorFrame, "Please create both matrices first.");
                    return;
                }

                int[][] matrix1 = getMatrix(matrixFields1);
                int[][] matrix2 = getMatrix(matrixFields2);

                if (matrix1 == null || matrix2 == null) {
                    return;
                }

                int[][] result = Matrix.addition(matrix1, matrix2);

                outputArea.setText(matrixToString(result));

            } catch (IllegalArgumentException ex) {
                JOptionPane.showMessageDialog(calculatorFrame, ex.getMessage());
            }
            
        });

        JButton subtractButton = new JButton("Subtraction");
        subtractButton.addActionListener(e -> {

            try {

                if (matrixFields1 == null || matrixFields2 == null) {
                    JOptionPane.showMessageDialog(calculatorFrame, "Please create both matrices first.");
                    return;
                }

                int[][] matrix1 = getMatrix(matrixFields1);
                int[][] matrix2 = getMatrix(matrixFields2);

                if (matrix1 == null || matrix2 == null) {
                    return;
                }

                int[][] result = Matrix.subtraction(matrix1, matrix2);

                outputArea.setText(matrixToString(result));

            } catch (IllegalArgumentException ex) {
                JOptionPane.showMessageDialog(calculatorFrame, ex.getMessage());
            }

        });

        JButton scalarMultiplicationButton = new JButton("Scalar Multiplication");
        scalarMultiplicationButton.addActionListener(e -> {


        });

        JButton multiplyButton = new JButton("Multiply");
        multiplyButton.addActionListener(e -> {

        if (matrixFields1 == null || matrixFields2 == null) {
                JOptionPane.showMessageDialog(calculatorFrame, "Please create both matrices first.");
                return;
            }

            int[][] matrix1 = getMatrix(matrixFields1);
            int[][] matrix2 = getMatrix(matrixFields2);

            if (matrix1 == null || matrix2 == null) {
                return;
            }

            int[][] result = Matrix.matrixMultiplication(matrix1, matrix2);

            outputArea.setText(matrixToString(result));

        });

        JButton transposeButton = new JButton("Transpose");
        transposeButton.addActionListener(e -> {
            int[][] matrix = getMatrix(matrixFields1);
            int[][] result = Matrix.transpose(matrix, matrix.length, matrix[0].length);
        
            outputArea.setText(matrixToString(result));
        });

        JButton determinantButton = new JButton("Determinant");
        determinantButton.addActionListener(e -> {
            int[][] matrix = getMatrix(matrixFields1);
            int result = Matrix.determinant(matrix);
        
            outputArea.setText(String.valueOf(result));
        });

        JButton reducedRowEchelonFormButton = new JButton("Reduced Row Echelon Form");
        reducedRowEchelonFormButton.addActionListener(e -> {
            int[][] matrix = getMatrix(matrixFields1);
            double[][] result = Matrix.reducedRowEchelonForm(matrix);
        
            outputArea.setText(matrixToString(result));
        });

        panel.add(title);
        panel.add(Box.createVerticalStrut(20));

        panel.add(addButton);
        panel.add(subtractButton);
        panel.add(scalarMultiplicationButton);
        panel.add(multiplyButton);
        panel.add(transposeButton);
        panel.add(determinantButton);
        panel.add(reducedRowEchelonFormButton);

        panel.add(Box.createVerticalStrut(20));
        panel.add(new JScrollPane(outputArea));

        return panel;

    }

    private JTextField[][] createMatrixGrid(JPanel panel, int rows, int cols) {

        // Remove previous matrix (if necessary)
        panel.removeAll();

        // Create new layout
        panel.setLayout(new GridLayout(rows, cols, 5, 5));

        JTextField[][] fields = new JTextField[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                fields[i][j] = new JTextField(3);
                panel.add(fields[i][j]);
            }
        }

        // Refresh the panel
        panel.revalidate();
        panel.repaint();

        return fields;

    }

    // Method to convert text fields into int[][]
    private int[][] getMatrix(JTextField[][] fields) {

        int rows = fields.length;
        int cols = fields[0].length;

        int[][] matrix = new int[rows][cols];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                try {
                    matrix[i][j] = Integer.parseInt(fields[i][j].getText());
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(calculatorFrame, "Pelase enter valid integers in every matrix cell");
                    return null;
                }
            }
        }

        return matrix;
    }

    private String matrixToString(int[][] matrix) {

        StringBuilder sb = new StringBuilder();

        for (int[] row : matrix) {
            for (int value : row) {
                sb.append(String.format("%6d", value));
            }
            sb.append("\n ");
        }

        return sb.toString();
    }

    private String matrixToString(double[][] matrix) {

        StringBuilder sb = new StringBuilder();

        for (double[] row : matrix) {
            for (double value : row) {

                if (Math.abs(value - Math.round(value)) < 1e-9) {
                    sb.append(String.format("%6d", (int) Math.round(value)));
                } else {
                sb.append(String.format("%8.3f", value));
                }
            }
            sb.append("\n ");
        }

        return sb.toString();
    }
}
