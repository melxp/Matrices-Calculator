import javax.swing.*;
import javax.swing.border.EmptyBorder;

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

        calculatorFrame.getContentPane().setBackground(new Color(165, 197, 208));

        calculatorFrame.add(createInputPanel(), BorderLayout.WEST);
        calculatorFrame.add(createOutputPanel(), BorderLayout.CENTER);

        // Create bottom panel
        JPanel bottomPanel = new JPanel(new BorderLayout());
        bottomPanel.setBackground(Color.WHITE);
        bottomPanel.setBorder(new EmptyBorder(5, 8, 5, 8));

        JLabel copyright = new JLabel("@2026 Melanie Pritchard. All Rights Reserved.");
        copyright.setFont(new Font("SansSerif", Font.PLAIN, 12));

        RoundedButton helpButton = new RoundedButton(
            "?",
            Color.WHITE,
            Color.BLACK,
            new Color(40, 40, 45),
            new Color(60, 60, 65)     
        );
        helpButton.setPreferredSize(new Dimension(30, 30));
        helpButton.setBackground(Color.BLACK);
        helpButton.setForeground(Color.WHITE);

        helpButton.addActionListener(e -> {
            calculatorFrame.dispose(); // Close calculator
            new MatrixHelp(); // Open help page
        });

        bottomPanel.add(copyright, BorderLayout.WEST);
        bottomPanel.add(helpButton, BorderLayout.EAST);

        calculatorFrame.add(bottomPanel, BorderLayout.SOUTH);

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
        
        RoundedButton createMatrixButton1 = new RoundedButton(
            "Create Matrix",
            Color.BLACK,
            Color.WHITE,
            new Color(240, 240, 245),
            new Color(220, 220, 225)
        );

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
        
        RoundedButton createMatrixButton2 = new RoundedButton(
            "Create Matrix",
            Color.BLACK,
            Color.WHITE,
            new Color(240, 240, 245),
            new Color(220, 220, 225)
        );

        createMatrixButton2.addActionListener(e -> {
            int rows2 = (Integer) rowSpinner2.getValue();
            int cols2 = (Integer) colSpinner2.getValue();
            matrixFields2 = createMatrixGrid(matrixPanel2, rows2, cols2);
        });

        matrixPanel2 = new JPanel();
        matrixPanel2.setPreferredSize(new Dimension(200, 200));
        matrixPanel2.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        RoundedButton backButton = new RoundedButton(
            "Back",
            Color.BLACK,
            Color.WHITE,
            new Color(240, 240, 245),
            new Color(220, 220, 225)
        );

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
        RoundedButton addButton = new RoundedButton(
            "Addition",
            Color.BLACK,
            Color.WHITE,
            new Color(240, 240, 245),
            new Color(220, 220, 225)
        );

        addButton.addActionListener(e -> {

            try {

                if (matrixFields1 == null || matrixFields2 == null) {
                    JOptionPane.showMessageDialog(calculatorFrame, "Please create both matrices first.");
                    return;
                }

                double[][] matrix1 = getMatrix(matrixFields1);
                double[][] matrix2 = getMatrix(matrixFields2);

                if (matrix1 == null || matrix2 == null) {
                    return;
                }

                double[][] result = Matrix.addition(matrix1, matrix2);

                outputArea.setText(matrixToString(result));

            } catch (IllegalArgumentException ex) {
                JOptionPane.showMessageDialog(calculatorFrame, ex.getMessage());
            }
            
        });

        RoundedButton subtractButton = new RoundedButton(
            "Subtraction",
            Color.BLACK,
            Color.WHITE,
            new Color(240, 240, 245),
            new Color(220, 220, 225)
        );

        subtractButton.addActionListener(e -> {

            try {

                if (matrixFields1 == null || matrixFields2 == null) {
                    JOptionPane.showMessageDialog(calculatorFrame, "Please create both matrices first.");
                    return;
                }

                double[][] matrix1 = getMatrix(matrixFields1);
                double[][] matrix2 = getMatrix(matrixFields2);

                if (matrix1 == null || matrix2 == null) {
                    return;
                }

                double[][] result = Matrix.subtraction(matrix1, matrix2);

                outputArea.setText(matrixToString(result));

            } catch (IllegalArgumentException ex) {
                JOptionPane.showMessageDialog(calculatorFrame, ex.getMessage());
            }

        });

        RoundedButton scalarMultiplicationButton = new RoundedButton(
            "Scalar Multiplication",
            Color.BLACK,
            Color.WHITE,
            new Color(240, 240, 245),
            new Color(220, 220, 225)
        );

        scalarMultiplicationButton.addActionListener(e -> {


        });

        RoundedButton scalarDivisionButton = new RoundedButton(
            "Scalar Division",
            Color.BLACK,
            Color.WHITE,
            new Color(240, 240, 245),
            new Color(220, 220, 225)
        );

        scalarDivisionButton.addActionListener(e -> {


        });

        RoundedButton multiplyButton = new RoundedButton(
            "Multiply",
            Color.BLACK,
            Color.WHITE,
            new Color(240, 240, 245),
            new Color(220, 220, 225)
        );

        multiplyButton.addActionListener(e -> {

            try {

                if (matrixFields1 == null || matrixFields2 == null) {
                    JOptionPane.showMessageDialog(calculatorFrame, "Please create both matrices first.");
                    return;
                }

                double[][] matrix1 = getMatrix(matrixFields1);
                double[][] matrix2 = getMatrix(matrixFields2);

                if (matrix1 == null || matrix2 == null) {
                    return;
                }

                double[][] result = Matrix.matrixMultiplication(matrix1, matrix2);

                outputArea.setText(matrixToString(result));

            } catch (IllegalArgumentException ex) {
                JOptionPane.showMessageDialog(calculatorFrame, ex.getMessage());
            }

        });

        RoundedButton transposeButton = new RoundedButton(
            "Transpose",
            Color.BLACK,
            Color.WHITE,
            new Color(240, 240, 245),
            new Color(220, 220, 225)
        );

        transposeButton.addActionListener(e -> {
            double[][] matrix = getMatrix(matrixFields1);
            double[][] result = Matrix.transpose(matrix);
        
            outputArea.setText(matrixToString(result));
        });

        RoundedButton determinantButton = new RoundedButton(
            "Determinant",
            Color.BLACK,
            Color.WHITE,
            new Color(240, 240, 245),
            new Color(220, 220, 225)
        );

        determinantButton.addActionListener(e -> {
            double[][] matrix = getMatrix(matrixFields1);
            int result = Matrix.determinant(matrix);
        
            outputArea.setText(String.valueOf(result));
        });

        RoundedButton inverseButton = new RoundedButton(
            "Inverse",
            Color.BLACK,
            Color.WHITE,
            new Color(240, 240, 245),
            new Color(220, 220, 225)
        );

        inverseButton.addActionListener(e -> {
            double[][] matrix = getMatrix(matrixFields1);
            double[][] result = Matrix.inverse(matrix);
        
            outputArea.setText(matrixToString(result));
        });

        RoundedButton reducedRowEchelonFormButton = new RoundedButton(
            "Reduced Row Echelon Form",
            Color.BLACK,
            Color.WHITE,
            new Color(240, 240, 245),
            new Color(220, 220, 225)
        );

        reducedRowEchelonFormButton.addActionListener(e -> {
            double[][] matrix = getMatrix(matrixFields1);
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
        panel.add(inverseButton);
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

    // Method to convert text fields into double[][]
    private double[][] getMatrix(JTextField[][] fields) {

        int rows = fields.length;
        int cols = fields[0].length;

        double[][] matrix = new double[rows][cols];

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
