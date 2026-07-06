import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;

public class MatrixCalculator {

    private JFrame frame;

    // Input Panels
    private JPanel matrix1Panel;
    private JPanel matrix2Panel;

    private JTextField[][] matrix1Fields;
    private JTextField[][] matrix2Fields;

    // Output
    private JTextArea outputArea;

    // Matrix 1 buttons
    private RoundedButton scalarMultiply1Button;
    private RoundedButton scalarDivide1Button;
    private RoundedButton transpose1Button;
    private RoundedButton determinant1Button;
    private RoundedButton inverse1Button;
    private RoundedButton rref1Button;

    // Matrix 2 buttons
    private RoundedButton scalarMultiply2Button;
    private RoundedButton scalarDivide2Button;
    private RoundedButton transpose2Button;
    private RoundedButton determinant2Button;
    private RoundedButton inverse2Button;
    private RoundedButton rref2Button;

    // Both matrices buttons
    private RoundedButton addButton;
    private RoundedButton subtractButton;
    private RoundedButton multiplyButton;

    // Scalar fields
    private JTextField scalar1Field;
    private JTextField scalar2Field;

    public MatrixCalculator() {
        
        // Create calculator window frame
        frame = new JFrame("Matrix Calculator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1100, 850);
        frame.setLocationRelativeTo(null); // Centre window

        JPanel background = new JPanel(new BorderLayout());
        background.setBackground(new Color(165, 197, 208));

        frame.setContentPane(background);

        background.add(createMainPanel(), BorderLayout.CENTER);
        background.add(createFooter(), BorderLayout.SOUTH);

        frame.setVisible(true);
    }

    private JPanel createMainPanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setOpaque(false);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(5, 5, 5, 5);

        // Top Row Panels: Input, Matrix 1, Matrix 2, Both
        gbc.gridy = 0;
        gbc.gridheight = 1;
        gbc.weighty = 0.6;

        // Input panel
        gbc.gridx = 0;
        gbc.weightx = 1.0;
        panel.add(createInputPanel(), gbc);

        // Matrix 1 panel
        gbc.gridx = 1;
        gbc.weightx = 0.8;
        panel.add(createMatrix1Panel(), gbc);

        // Matrix 2 panel
        gbc.gridx = 2;
        gbc.weightx = 0.8;
        panel.add(createMatrix2Panel(), gbc);

        // Both panel
        gbc.gridx = 3;
        gbc.weightx = 0.8;
        panel.add(createBothPanel(), gbc);

        // Bottom Row Panel: Output panel spans across columns under operational sections
        gbc.gridy = 1;
        gbc.gridx = 1;
        gbc.gridwidth = 3;
        gbc.weightx = 2.4;
        gbc.weighty = 0.4;

        panel.add(createOutputPanel(), gbc);

        return panel;
    }

    private JPanel createSection(String title) {
        JPanel panel = new JPanel();
        panel.setOpaque(false);
        panel.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Color.WHITE));
        panel.setLayout(new BorderLayout());

        JLabel heading = new JLabel(title);
        heading.setForeground(Color.WHITE);
        heading.setFont(new Font("Monospaced", Font.BOLD, 24));
        heading.setBorder(new EmptyBorder(8, 8, 8, 8));

        panel.add(heading, BorderLayout.NORTH);
        return panel;
    }

    private JPanel createFooter() {
        JPanel bottomPanel = new JPanel(new BorderLayout());
        bottomPanel.setBackground(Color.WHITE);
        bottomPanel.setBorder(new EmptyBorder(5, 8, 5, 8));

        JLabel copyright = new JLabel("@2026 Melanie Pritchard. All Rights Reserved.");
        copyright.setFont(new Font("SansSerif", Font.PLAIN, 12));

        // Create a wrapper for buttons to look neat on the right
        JPanel actionPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 0));
        actionPanel.setOpaque(false);

        RoundedButton backButton = new RoundedButton(
            "<-",
            Color.WHITE,
            Color.BLACK,
            new Color(240, 240, 245),
            new Color(220, 220, 225)
        );
        backButton.setPreferredSize(new Dimension(35, 30));
        backButton.addActionListener(e -> {
            frame.dispose();
            new MatrixHome();
        });

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
            frame.dispose(); 
            new MatrixHelp(); 
        });

        actionPanel.add(backButton);
        actionPanel.add(helpButton);

        bottomPanel.add(copyright, BorderLayout.WEST);
        bottomPanel.add(actionPanel, BorderLayout.EAST);

        return bottomPanel;
    }

    private JPanel createInputPanel() {
        JPanel outer = createSection("Input");

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(new EmptyBorder(10, 15, 15, 15));
        panel.setOpaque(false);

        // Matrix 1 Parameters
        JLabel matrix1Label = new JLabel("Matrix 1");
        matrix1Label.setForeground(Color.WHITE);
        matrix1Label.setFont(new Font("Monospaced", Font.BOLD, 16));

        JLabel rows1Label = new JLabel("Rows:");
        rows1Label.setForeground(Color.WHITE);
        JSpinner rows1Spinner = new JSpinner(new SpinnerNumberModel(2, 1, 10, 1));
        
        JLabel cols1Label = new JLabel("Columns:");
        cols1Label.setForeground(Color.WHITE);
        JSpinner cols1Spinner = new JSpinner(new SpinnerNumberModel(2, 1, 10, 1));
        
        RoundedButton createMatrix1Button = new RoundedButton(
            "Create Matrix", Color.BLACK, Color.WHITE,
            new Color(240, 240, 245), new Color(220, 220, 225)
        );

        matrix1Panel = new JPanel();
        matrix1Panel.setOpaque(false);
        matrix1Panel.setBorder(BorderFactory.createLineBorder(Color.WHITE));

        createMatrix1Button.addActionListener(e -> {
            int rows = (Integer) rows1Spinner.getValue();
            int cols = (Integer) cols1Spinner.getValue();
            matrix1Fields = createMatrixGrid(matrix1Panel, rows, cols);
        });

        // Matrix 2 Parameters
        JLabel matrix2Label = new JLabel("Matrix 2");
        matrix2Label.setForeground(Color.WHITE);
        matrix2Label.setFont(new Font("Monospaced", Font.BOLD, 16));

        JLabel rows2Label = new JLabel("Rows:");
        rows2Label.setForeground(Color.WHITE);
        JSpinner rows2Spinner = new JSpinner(new SpinnerNumberModel(2, 1, 10, 1));
        
        JLabel cols2Label = new JLabel("Columns:");
        cols2Label.setForeground(Color.WHITE);
        JSpinner cols2Spinner = new JSpinner(new SpinnerNumberModel(2, 1, 10, 1));
        
        RoundedButton createMatrix2Button = new RoundedButton(
            "Create Matrix", Color.BLACK, Color.WHITE,
            new Color(240, 240, 245), new Color(220, 220, 225)
        );

        matrix2Panel = new JPanel();
        matrix2Panel.setOpaque(false);
        matrix2Panel.setBorder(BorderFactory.createLineBorder(Color.WHITE));

        createMatrix2Button.addActionListener(e -> {
            int rows = (Integer) rows2Spinner.getValue();
            int cols = (Integer) cols2Spinner.getValue();
            matrix2Fields = createMatrixGrid(matrix2Panel, rows, cols);
        });

        // Layout Assembly
        panel.add(matrix1Label);
        panel.add(Box.createVerticalStrut(5));
        panel.add(rows1Label);
        panel.add(rows1Spinner);
        panel.add(Box.createVerticalStrut(3));
        panel.add(cols1Label);
        panel.add(cols1Spinner);
        panel.add(Box.createVerticalStrut(8));
        panel.add(createMatrix1Button);
        panel.add(Box.createVerticalStrut(8));
        matrix1Panel.setMaximumSize(new Dimension(250, 150));
        panel.add(matrix1Panel);

        panel.add(Box.createVerticalStrut(20));

        panel.add(matrix2Label);
        panel.add(Box.createVerticalStrut(5));
        panel.add(rows2Label);
        panel.add(rows2Spinner);
        panel.add(Box.createVerticalStrut(3));
        panel.add(cols2Label);
        panel.add(cols2Spinner);
        panel.add(Box.createVerticalStrut(8));
        panel.add(createMatrix2Button);
        panel.add(Box.createVerticalStrut(8));
        matrix2Panel.setMaximumSize(new Dimension(250, 150));
        panel.add(matrix2Panel);

        panel.add(Box.createVerticalGlue());
        outer.add(panel, BorderLayout.CENTER);

        return outer;
    }

    private JPanel createMatrix1Panel() {
        JPanel outer = createSection("Matrix 1");

        JPanel panel = new JPanel();
        panel.setOpaque(false);
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(new EmptyBorder(15, 15, 15, 15));

        JLabel scalarLabel = new JLabel("Scalar:");
        scalarLabel.setForeground(Color.WHITE);

        scalar1Field = new JTextField();
        scalar1Field.setMaximumSize(new Dimension(180, 30));

        panel.add(scalarLabel);
        panel.add(Box.createVerticalStrut(5));
        panel.add(scalar1Field);
        panel.add(Box.createVerticalStrut(15));

        scalarMultiply1Button = createOperationButton("Multiply");
        scalarDivide1Button = createOperationButton("Divide");
        transpose1Button = createOperationButton("Transpose");
        determinant1Button = createOperationButton("Determinant");
        inverse1Button = createOperationButton("Inverse");
        rref1Button = createOperationButton("RREF");

        // Set up individual Matrix 1 ActionListeners
        scalarMultiply1Button.addActionListener(e -> {
            try {
                if (matrix1Fields == null) { JOptionPane.showMessageDialog(frame, "Create Matrix 1 first."); return; }
                double[][] m1 = getMatrix(matrix1Fields);
                if (m1 == null) return;
                double scalar = Double.parseDouble(scalar1Field.getText());
                outputArea.setText(matrixToString(Matrix.scalarMultiplication(m1, scalar)));
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(frame, "Please enter a valid scalar numeric value.");
            }
        });

        scalarDivide1Button.addActionListener(e -> {
            try {
                if (matrix1Fields == null) { JOptionPane.showMessageDialog(frame, "Create Matrix 1 first."); return; }
                double[][] m1 = getMatrix(matrix1Fields);
                if (m1 == null) return;
                double scalar = Double.parseDouble(scalar1Field.getText());
                outputArea.setText(matrixToString(Matrix.scalarDivision(m1, scalar)));
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(frame, "Please enter a valid scalar numeric value.");
            }
        });

        transpose1Button.addActionListener(e -> {
            if (matrix1Fields == null) { JOptionPane.showMessageDialog(frame, "Create Matrix 1 first."); return; }
            double[][] m1 = getMatrix(matrix1Fields);
            if (m1 != null) outputArea.setText(matrixToString(Matrix.transpose(m1)));
        });

        determinant1Button.addActionListener(e -> {
            if (matrix1Fields == null) { JOptionPane.showMessageDialog(frame, "Create Matrix 1 first."); return; }
            double[][] m1 = getMatrix(matrix1Fields);
            if (m1 != null) outputArea.setText(String.valueOf(Matrix.determinant(m1)));
        });

        inverse1Button.addActionListener(e -> {
            if (matrix1Fields == null) { JOptionPane.showMessageDialog(frame, "Create Matrix 1 first."); return; }
            double[][] m1 = getMatrix(matrix1Fields);
            if (m1 != null) outputArea.setText(matrixToString(Matrix.inverse(m1)));
        });

        rref1Button.addActionListener(e -> {
            if (matrix1Fields == null) { JOptionPane.showMessageDialog(frame, "Create Matrix 1 first."); return; }
            double[][] m1 = getMatrix(matrix1Fields);
            if (m1 != null) outputArea.setText(matrixToString(Matrix.reducedRowEchelonForm(m1)));
        });

        panel.add(scalarMultiply1Button); panel.add(Box.createVerticalStrut(8));
        panel.add(scalarDivide1Button);    panel.add(Box.createVerticalStrut(8));
        panel.add(transpose1Button);       panel.add(Box.createVerticalStrut(8));
        panel.add(determinant1Button);     panel.add(Box.createVerticalStrut(8));
        panel.add(inverse1Button);         panel.add(Box.createVerticalStrut(8));
        panel.add(rref1Button);
        
        outer.add(panel, BorderLayout.CENTER);
        return outer;
    }

    private JPanel createMatrix2Panel() {
        JPanel outer = createSection("Matrix 2");

        JPanel panel = new JPanel();
        panel.setOpaque(false);
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(new EmptyBorder(15, 15, 15, 15));

        JLabel scalarLabel = new JLabel("Scalar:");
        scalarLabel.setForeground(Color.WHITE);

        scalar2Field = new JTextField();
        scalar2Field.setMaximumSize(new Dimension(180, 30));

        panel.add(scalarLabel);
        panel.add(Box.createVerticalStrut(5));
        panel.add(scalar2Field);
        panel.add(Box.createVerticalStrut(15));

        scalarMultiply2Button = createOperationButton("Multiply");
        scalarDivide2Button = createOperationButton("Divide");
        transpose2Button = createOperationButton("Transpose");
        determinant2Button = createOperationButton("Determinant");
        inverse2Button = createOperationButton("Inverse");
        rref2Button = createOperationButton("RREF");

        // Set up individual Matrix 2 ActionListeners
        scalarMultiply2Button.addActionListener(e -> {
            try {
                if (matrix2Fields == null) { JOptionPane.showMessageDialog(frame, "Create Matrix 2 first."); return; }
                double[][] m2 = getMatrix(matrix2Fields);
                if (m2 == null) return;
                double scalar = Double.parseDouble(scalar2Field.getText());
                outputArea.setText(matrixToString(Matrix.scalarMultiplication(m2, scalar)));
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(frame, "Please enter a valid scalar numeric value.");
            }
        });

        scalarDivide2Button.addActionListener(e -> {
            try {
                if (matrix2Fields == null) { JOptionPane.showMessageDialog(frame, "Create Matrix 2 first."); return; }
                double[][] m2 = getMatrix(matrix2Fields);
                if (m2 == null) return;
                double scalar = Double.parseDouble(scalar2Field.getText());
                outputArea.setText(matrixToString(Matrix.scalarDivision(m2, scalar)));
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(frame, "Please enter a valid scalar numeric value.");
            }
        });

        transpose2Button.addActionListener(e -> {
            if (matrix2Fields == null) { JOptionPane.showMessageDialog(frame, "Create Matrix 2 first."); return; }
            double[][] m2 = getMatrix(matrix2Fields);
            if (m2 != null) outputArea.setText(matrixToString(Matrix.transpose(m2)));
        });

        determinant2Button.addActionListener(e -> {
            if (matrix2Fields == null) { JOptionPane.showMessageDialog(frame, "Create Matrix 2 first."); return; }
            double[][] m2 = getMatrix(matrix2Fields);
            if (m2 != null) outputArea.setText(String.valueOf(Matrix.determinant(m2)));
        });

        inverse2Button.addActionListener(e -> {
            if (matrix2Fields == null) { JOptionPane.showMessageDialog(frame, "Create Matrix 2 first."); return; }
            double[][] m2 = getMatrix(matrix2Fields);
            if (m2 != null) outputArea.setText(matrixToString(Matrix.inverse(m2)));
        });

        rref2Button.addActionListener(e -> {
            if (matrix2Fields == null) { JOptionPane.showMessageDialog(frame, "Create Matrix 2 first."); return; }
            double[][] m2 = getMatrix(matrix2Fields);
            if (m2 != null) outputArea.setText(matrixToString(Matrix.reducedRowEchelonForm(m2)));
        });

        panel.add(scalarMultiply2Button); panel.add(Box.createVerticalStrut(8));
        panel.add(scalarDivide2Button);    panel.add(Box.createVerticalStrut(8));
        panel.add(transpose2Button);       panel.add(Box.createVerticalStrut(8));
        panel.add(determinant2Button);     panel.add(Box.createVerticalStrut(8));
        panel.add(inverse2Button);         panel.add(Box.createVerticalStrut(8));
        panel.add(rref2Button);
        
        outer.add(panel, BorderLayout.CENTER);
        return outer;
    }

    private JPanel createBothPanel() {
        JPanel outer = createSection("Both");

        JPanel panel = new JPanel();
        panel.setOpaque(false);
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(new EmptyBorder(15, 15, 15, 15));

        addButton = createOperationButton("Add");
        addButton.addActionListener(e -> {
            try {
                if (matrix1Fields == null || matrix2Fields == null) {
                    JOptionPane.showMessageDialog(frame, "Create both matrices first.");
                    return;
                }
                double[][] m1 = getMatrix(matrix1Fields);
                double[][] m2 = getMatrix(matrix2Fields);
                if (m1 == null || m2 == null) return;

                outputArea.setText(matrixToString(Matrix.addition(m1, m2)));
            } catch(Exception ex) {
                JOptionPane.showMessageDialog(frame, ex.getMessage());
            }
        });

        subtractButton = createOperationButton("Subtract");
        subtractButton.addActionListener(e -> {
            try {
                if (matrix1Fields == null || matrix2Fields == null) {
                    JOptionPane.showMessageDialog(frame, "Create both matrices first.");
                    return;
                }
                double[][] m1 = getMatrix(matrix1Fields);
                double[][] m2 = getMatrix(matrix2Fields);
                if (m1 == null || m2 == null) return;

                outputArea.setText(matrixToString(Matrix.subtraction(m1, m2)));
            } catch(Exception ex) {
                JOptionPane.showMessageDialog(frame, ex.getMessage());
            }
        });

        multiplyButton = createOperationButton("Multiply");
        multiplyButton.addActionListener(e -> {
            try {
                if (matrix1Fields == null || matrix2Fields == null) {
                    JOptionPane.showMessageDialog(frame, "Create both matrices first.");
                    return;
                }
                double[][] m1 = getMatrix(matrix1Fields);
                double[][] m2 = getMatrix(matrix2Fields);
                if (m1 == null || m2 == null) return;

                outputArea.setText(matrixToString(Matrix.matrixMultiplication(m1, m2)));
            } catch(Exception ex) {
                JOptionPane.showMessageDialog(frame, ex.getMessage());
            }
        });

        panel.add(Box.createVerticalStrut(10));
        panel.add(addButton);
        panel.add(Box.createVerticalStrut(12));
        panel.add(subtractButton);
        panel.add(Box.createVerticalStrut(12));
        panel.add(multiplyButton);
        
        outer.add(panel, BorderLayout.CENTER);
        return outer;
    }

    private RoundedButton createOperationButton(String text) {
        RoundedButton button = new RoundedButton( 
            text, 
            Color.BLACK,
            Color.WHITE,
            new Color(240, 240, 245),
            new Color(220, 220, 225)
        );
        button.setMaximumSize(new Dimension(180, 40));
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        return button;    
    }

    private JPanel createOutputPanel() {
        JPanel outer = createSection("Output");
        outer.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createMatteBorder(2, 2, 2, 2, Color.WHITE),
            BorderFactory.createEmptyBorder(10, 10, 10, 10)
        ));

        outputArea = new JTextArea();
        outputArea.setEditable(false);
        outputArea.setFont(new Font("Monospaced", Font.PLAIN, 18));
        outputArea.setMargin(new Insets(10, 10, 10, 10));

        JScrollPane scroll = new JScrollPane(outputArea);
        scroll.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        outer.add(scroll, BorderLayout.CENTER);
        return outer;
    }

    private JTextField[][] createMatrixGrid(JPanel panel, int rows, int cols) {
        panel.removeAll();
        panel.setLayout(new GridLayout(rows, cols, 5, 5));

        JTextField[][] fields = new JTextField[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                fields[i][j] = new JTextField(3);
                panel.add(fields[i][j]);
            }
        }

        panel.revalidate();
        panel.repaint();
        return fields;
    }

    private double[][] getMatrix(JTextField[][] fields) {
        int rows = fields.length;
        int cols = fields[0].length;
        double[][] matrix = new double[rows][cols];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                try {
                    matrix[i][j] = Double.parseDouble(fields[i][j].getText());
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(frame, "Please enter valid numbers in every matrix cell.");
                    return null;
                }
            }
        }
        return matrix;
    } 

    private String matrixToString(double[][] matrix) {
        if (matrix == null) return "";
        StringBuilder sb = new StringBuilder();

        for (double[] row : matrix) {
            for (double value : row) {
                if (Math.abs(value - Math.round(value)) < 1e-9) {
                    sb.append(String.format("%6d", (int) Math.round(value)));
                } else {
                    sb.append(String.format("%8.3f", value));
                }
            }
            sb.append("\n");
        }
        return sb.toString();
    }
}