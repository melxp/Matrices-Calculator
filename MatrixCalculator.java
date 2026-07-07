import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;

public class MatrixCalculator {

    private JFrame frame;

    // Input
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

        JPanel mainPanel = new JPanel(new BorderLayout(10, 0));
        mainPanel.setOpaque(false);
        mainPanel.setBorder(new EmptyBorder(5, 5, 5, 5));

        JPanel inputWrapper = createInputPanel();
        inputWrapper.setPreferredSize(new Dimension(300, 0));
        mainPanel.add(inputWrapper, BorderLayout.WEST);

        JPanel workspacePanel = new JPanel(new GridBagLayout());
        workspacePanel.setOpaque(false);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(5, 5, 5, 5);

        // Top row 
        gbc.gridy = 0;
        gbc.gridheight = 1;
        gbc.weighty = 0.55;

        // Matrix 1 panel
        gbc.gridx = 0;
        gbc.weightx = 1.0;
        workspacePanel.add(createMatrix1Panel(), gbc);

        // Matrix 2 panel
        gbc.gridx = 1;
        gbc.weightx = 1.0;
        workspacePanel.add(createMatrix2Panel(), gbc);

        // Both panel
        gbc.gridx = 2;
        gbc.weightx = 1.0;
        workspacePanel.add(createBothPanel(), gbc);

        // Bottom row
        gbc.gridy = 1;
        gbc.gridx = 0;
        gbc.gridwidth = 3;
        gbc.weightx = 3.0;
        gbc.weighty = 0.45;

        workspacePanel.add(createOutputPanel(), gbc);

        mainPanel.add(workspacePanel, BorderLayout.CENTER);

        return mainPanel;
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

        // Matrix 1 parameters
        JLabel matrix1Label = new JLabel("Matrix 1");
        matrix1Label.setForeground(Color.WHITE);
        matrix1Label.setFont(new Font("Monospaced", Font.BOLD, 18));
        matrix1Label.setAlignmentX(Component.LEFT_ALIGNMENT);

        JPanel row1Panel = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 0));
        row1Panel.setOpaque(false);
        row1Panel.setAlignmentX(Component.LEFT_ALIGNMENT);

        JLabel rows1Label = new JLabel("Rows:      ");
        rows1Label.setForeground(Color.WHITE);
        
        JSpinner rows1Spinner = new JSpinner(new SpinnerNumberModel(2, 1, 10, 1));
        ((JSpinner.DefaultEditor) rows1Spinner.getEditor()).getTextField().setColumns(3); 
        row1Panel.add(rows1Label);
        row1Panel.add(rows1Spinner);


        JPanel col1Panel = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 0));
        col1Panel.setOpaque(false);
        col1Panel.setAlignmentX(Component.LEFT_ALIGNMENT);

        JLabel cols1Label = new JLabel("Columns:");
        cols1Label.setForeground(Color.WHITE);

        JSpinner cols1Spinner = new JSpinner(new SpinnerNumberModel(2, 1, 10, 1));
        ((JSpinner.DefaultEditor) cols1Spinner.getEditor()).getTextField().setColumns(3);
        col1Panel.add(cols1Label);
        col1Panel.add(cols1Spinner);

        JPanel buttonWrapper1 = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        buttonWrapper1.setOpaque(false);
        buttonWrapper1.setAlignmentX(Component.LEFT_ALIGNMENT);

        RoundedButton createMatrix1Button = new RoundedButton(
            "Generate Matrix Grid", Color.BLACK, Color.WHITE,
            new Color(240, 240, 245), new Color(220, 220, 225)
        );
        createMatrix1Button.setPreferredSize(new Dimension(250, 30));
        Font smallerFont = new Font(createMatrix1Button.getFont().getName(), createMatrix1Button.getFont().getStyle(), 15);
        createMatrix1Button.setFont(smallerFont);
        buttonWrapper1.add(createMatrix1Button);

        matrix1Panel = new JPanel();
        matrix1Panel.setOpaque(false);
        matrix1Panel.setBorder(BorderFactory.createLineBorder(Color.WHITE));

        createMatrix1Button.addActionListener(e -> {
            int rows = (Integer) rows1Spinner.getValue();
            int cols = (Integer) cols1Spinner.getValue();
            matrix1Fields = createMatrixGrid(matrix1Panel, rows, cols);
        });

        // Wrap scroll panes
        JScrollPane scrollMatrix1 = new JScrollPane(matrix1Panel);
        scrollMatrix1.setOpaque(false);
        scrollMatrix1.getViewport().setOpaque(false);
        scrollMatrix1.setBorder(BorderFactory.createLineBorder(Color.WHITE));
        scrollMatrix1.setAlignmentX(Component.LEFT_ALIGNMENT);
        scrollMatrix1.setMaximumSize(new Dimension(260, 160));
        scrollMatrix1.setPreferredSize(new Dimension(260, 160));

        // Matrix 2 parameters
        JLabel matrix2Label = new JLabel("Matrix 2");
        matrix2Label.setForeground(Color.WHITE);
        matrix2Label.setFont(new Font("Monospaced", Font.BOLD, 18));
        matrix2Label.setAlignmentX(Component.LEFT_ALIGNMENT);

        JPanel row2Panel = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 0));
        row2Panel.setOpaque(false);
        row2Panel.setAlignmentX(Component.LEFT_ALIGNMENT);

        JLabel rows2Label = new JLabel("Rows:      ");
        rows2Label.setForeground(Color.WHITE);

        JSpinner rows2Spinner = new JSpinner(new SpinnerNumberModel(2, 1, 10, 1));
        ((JSpinner.DefaultEditor) rows2Spinner.getEditor()).getTextField().setColumns(3);
        row2Panel.add(rows2Label);
        row2Panel.add(rows2Spinner);
        
        JPanel col2Panel = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 0));
        col2Panel.setOpaque(false);
        col2Panel.setAlignmentX(Component.LEFT_ALIGNMENT);

        JLabel cols2Label = new JLabel("Columns:");
        cols2Label.setForeground(Color.WHITE);

        JSpinner cols2Spinner = new JSpinner(new SpinnerNumberModel(2, 1, 10, 1));
        ((JSpinner.DefaultEditor) cols2Spinner.getEditor()).getTextField().setColumns(3);
        col2Panel.add(cols2Label);
        col2Panel.add(cols2Spinner);

        JPanel buttonWrapper2 = new JPanel(new FlowLayout(FlowLayout.LEFT, 0,0));
        buttonWrapper2.setOpaque(false);
        buttonWrapper2.setAlignmentX(Component.LEFT_ALIGNMENT);
        
        RoundedButton createMatrix2Button = new RoundedButton(
            "Generate Matrix Grid", Color.BLACK, Color.WHITE,
            new Color(240, 240, 245), new Color(220, 220, 225)
        );
        createMatrix2Button.setPreferredSize(new Dimension(250, 30));
        createMatrix2Button.setFont(smallerFont);
        buttonWrapper2.add(createMatrix2Button);

        matrix2Panel = new JPanel();
        matrix2Panel.setOpaque(false);
        matrix2Panel.setBorder(BorderFactory.createLineBorder(Color.WHITE));

        createMatrix2Button.addActionListener(e -> {
            int rows = (Integer) rows2Spinner.getValue();
            int cols = (Integer) cols2Spinner.getValue();
            matrix2Fields = createMatrixGrid(matrix2Panel, rows, cols);
        });

        JScrollPane scrollMatrix2 = new JScrollPane(matrix2Panel);
        scrollMatrix2.setOpaque(false);
        scrollMatrix2.getViewport().setOpaque(false);
        scrollMatrix2.setBorder(BorderFactory.createLineBorder(Color.WHITE));
        scrollMatrix2.setAlignmentX(Component.LEFT_ALIGNMENT);
        scrollMatrix2.setMaximumSize(new Dimension(260, 160));
        scrollMatrix2.setPreferredSize(new Dimension(260, 160));
    
       
        panel.add(matrix1Label);
        panel.add(Box.createVerticalStrut(8));
        panel.add(row1Panel);
        panel.add(Box.createVerticalStrut(4));
        panel.add(col1Panel);
        panel.add(Box.createVerticalStrut(10));
        panel.add(buttonWrapper1);
        panel.add(Box.createVerticalStrut(10));
        panel.add(scrollMatrix1);

        panel.add(Box.createVerticalStrut(25));

        panel.add(matrix2Label);
        panel.add(Box.createVerticalStrut(8));
        panel.add(row2Panel);
        panel.add(Box.createVerticalStrut(4));
        panel.add(col2Panel);
        panel.add(Box.createVerticalStrut(10));
        panel.add(buttonWrapper2);
        panel.add(Box.createVerticalStrut(10));
        panel.add(scrollMatrix2);

        panel.add(Box.createVerticalGlue());
        outer.add(panel, BorderLayout.CENTER);

        return outer;
    }

    private JPanel createMatrix1Panel() {
        JPanel outer = createSection("Matrix 1");

        JPanel panel = new JPanel();
        panel.setOpaque(false);
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(new EmptyBorder(10, 15, 15, 15));

        JLabel scalarLabel = new JLabel("Scalar:");
        scalarLabel.setForeground(Color.WHITE);
        scalarLabel.setFont(new Font("SansSerif", Font.PLAIN, 14));
        scalarLabel.setAlignmentX(Component.LEFT_ALIGNMENT);

        scalar1Field = new JTextField();
        scalar1Field.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));
        scalar1Field.setAlignmentX(Component.LEFT_ALIGNMENT);

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

        scalarMultiply1Button.addActionListener(e -> {
            try {
                if (matrix1Fields == null) { JOptionPane.showMessageDialog(frame, "Create Matrix 1 first."); return; }
                double[][] m1 = getMatrix(matrix1Fields);
                if (m1 == null) return;
                double scalar = Double.parseDouble(scalar1Field.getText());
                Calculations outcome = Matrix.scalarMultiplication(m1, scalar);
                displayResultWithSteps(outcome);
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
                Calculations outcome = Matrix.scalarDivision(m1, scalar);
                displayResultWithSteps(outcome);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(frame, "Please enter a valid scalar numeric value.");
            }
        });

        transpose1Button.addActionListener(e -> {
            if (matrix1Fields == null) { JOptionPane.showMessageDialog(frame, "Create Matrix 1 first."); return; }
            double[][] m1 = getMatrix(matrix1Fields);
            if (m1 != null) {
                Calculations outcome = Matrix.transpose(m1);
                displayResultWithSteps(outcome);
            }
        });

        determinant1Button.addActionListener(e -> {
            if (matrix1Fields == null) { JOptionPane.showMessageDialog(frame, "Create Matrix 1 first."); return; }
            double[][] m1 = getMatrix(matrix1Fields);
            if (m1 != null) {
                try {
                    Calculations outcome = Matrix.determinant(m1);
                    displayResultWithSteps(outcome);
                } catch (IllegalArgumentException ex) {
                    JOptionPane.showMessageDialog(frame, ex.getMessage());
                }
            }
        });
 
        inverse1Button.addActionListener(e -> {
            if (matrix1Fields == null) { JOptionPane.showMessageDialog(frame, "Create Matrix 1 first."); return; }
            double[][] m1 = getMatrix(matrix1Fields);
            if (m1 != null) {
                try {
                    Calculations outcome = Matrix.inverse(m1);
                    displayResultWithSteps(outcome);
                } catch(IllegalArgumentException ex) {
                    JOptionPane.showMessageDialog(frame, ex.getMessage());
                }
            }
        });

        rref1Button.addActionListener(e -> {
            if (matrix1Fields == null) { JOptionPane.showMessageDialog(frame, "Create Matrix 1 first."); return; }
            double[][] m1 = getMatrix(matrix1Fields);
            if (m1 != null) {
                Calculations outcome = Matrix.reducedRowEchelonForm(m1);
                displayResultWithSteps(outcome);
            }
        });

        panel.add(scalarMultiply1Button); panel.add(Box.createVerticalStrut(8));
        panel.add(scalarDivide1Button);    panel.add(Box.createVerticalStrut(8));
        panel.add(transpose1Button);       panel.add(Box.createVerticalStrut(8));
        panel.add(determinant1Button);     panel.add(Box.createVerticalStrut(8));
        panel.add(inverse1Button);         panel.add(Box.createVerticalStrut(8));
        panel.add(rref1Button);
        
        panel.add(Box.createVerticalGlue());
        outer.add(panel, BorderLayout.CENTER);
        return outer;
    }

    private JPanel createMatrix2Panel() {
        JPanel outer = createSection("Matrix 2");

        JPanel panel = new JPanel();
        panel.setOpaque(false);
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(new EmptyBorder(10, 15, 15, 15));

        JLabel scalarLabel = new JLabel("Scalar:");
        scalarLabel.setForeground(Color.WHITE);
        scalarLabel.setFont(new Font("SansSerif", Font.PLAIN, 14));
        scalarLabel.setAlignmentX(Component.LEFT_ALIGNMENT);

        scalar2Field = new JTextField();
        scalar2Field.setMaximumSize(new Dimension(180, 30));
        scalar2Field.setAlignmentX(Component.LEFT_ALIGNMENT);

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

        scalarMultiply2Button.addActionListener(e -> {
            try {
                if (matrix2Fields == null) { JOptionPane.showMessageDialog(frame, "Create Matrix 2 first."); return; }
                double[][] m2 = getMatrix(matrix2Fields);
                if (m2 == null) return;
                double scalar = Double.parseDouble(scalar2Field.getText());
                Calculations outcome = Matrix.scalarMultiplication(m2, scalar);
                displayResultWithSteps(outcome);
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
                Calculations outcome = Matrix.scalarDivision(m2, scalar);
                displayResultWithSteps(outcome);;
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(frame, "Please enter a valid scalar numeric value.");
            }
        });

        transpose2Button.addActionListener(e -> {
            if (matrix2Fields == null) { JOptionPane.showMessageDialog(frame, "Create Matrix 2 first."); return; }
            double[][] m2 = getMatrix(matrix2Fields);
            if (m2 != null) {
                Calculations outcome = Matrix.transpose(m2);
                displayResultWithSteps(outcome);
            }
        });

        determinant2Button.addActionListener(e -> {
            if (matrix2Fields == null) { JOptionPane.showMessageDialog(frame, "Create Matrix 2 first."); return; }
            double[][] m2 = getMatrix(matrix2Fields);
            if (m2 != null) {
                try {
                    Calculations outcome = Matrix.determinant(m2);
                    displayResultWithSteps(outcome);
                } catch (IllegalArgumentException ex) {
                    JOptionPane.showMessageDialog(frame, ex.getMessage());
                }
            }
        });

        inverse2Button.addActionListener(e -> {
            if (matrix2Fields == null) { JOptionPane.showMessageDialog(frame, "Create Matrix 2 first."); return; }
            double[][] m2 = getMatrix(matrix2Fields);
            if (m2 != null) {
                try {
                    Calculations outcome = Matrix.inverse(m2);
                    displayResultWithSteps(outcome);
                } catch(IllegalArgumentException ex) {
                    JOptionPane.showMessageDialog(frame, ex.getMessage());
                }
            }
        });

        rref2Button.addActionListener(e -> {
            if (matrix2Fields == null) { JOptionPane.showMessageDialog(frame, "Create Matrix 2 first."); return; }
            double[][] m2 = getMatrix(matrix2Fields);
            if (m2 != null) {
                Calculations outcome = Matrix.reducedRowEchelonForm(m2);
                displayResultWithSteps(outcome);
            }
        });

        panel.add(scalarMultiply2Button); panel.add(Box.createVerticalStrut(8));
        panel.add(scalarDivide2Button);    panel.add(Box.createVerticalStrut(8));
        panel.add(transpose2Button);       panel.add(Box.createVerticalStrut(8));
        panel.add(determinant2Button);     panel.add(Box.createVerticalStrut(8));
        panel.add(inverse2Button);         panel.add(Box.createVerticalStrut(8));
        panel.add(rref2Button);
        
        panel.add(Box.createVerticalGlue());
        outer.add(panel, BorderLayout.CENTER);
        return outer;
    }

    private JPanel createBothPanel() {
        JPanel outer = createSection("Both");

        JPanel panel = new JPanel();
        panel.setOpaque(false);
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(new EmptyBorder(10, 15, 15, 15));

        JLabel formatLabel = new JLabel("Format:");
        formatLabel.setForeground(Color.WHITE);
        formatLabel.setAlignmentX(Component.LEFT_ALIGNMENT);

        JLabel format1Label = new JLabel("Matrix 1 + Matrix 2");
        format1Label.setForeground(Color.WHITE);
        format1Label.setAlignmentX(Component.LEFT_ALIGNMENT);

        JLabel format2Label = new JLabel("Matrix 1 - Matrix 2");
        format2Label.setForeground(Color.WHITE);
        format2Label.setAlignmentX(Component.LEFT_ALIGNMENT);

        JLabel format3Label = new JLabel("Matrix 1 * Matrix 2");
        format3Label.setForeground(Color.WHITE);
        format3Label.setAlignmentX(Component.LEFT_ALIGNMENT);

        panel.add(formatLabel);
        panel.add(format1Label);
        panel.add(format2Label);
        panel.add(format3Label);
        panel.add(Box.createVerticalStrut(20));

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

                Calculations outcome = Matrix.addition(m1, m2);
                displayResultWithSteps(outcome);

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

                Calculations outcome = Matrix.subtraction(m1, m2);
                displayResultWithSteps(outcome);

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

                Calculations outcome = Matrix.matrixMultiplication(m1, m2);
                displayResultWithSteps(outcome);

            } catch(Exception ex) {
                JOptionPane.showMessageDialog(frame, ex.getMessage());
            }
        });

        panel.add(addButton);
        panel.add(Box.createVerticalStrut(8));
        panel.add(subtractButton);
        panel.add(Box.createVerticalStrut(8));
        panel.add(multiplyButton);
        
        panel.add(Box.createVerticalGlue());
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
        button.setMaximumSize(new Dimension(Integer.MAX_VALUE, 35));
        button.setAlignmentX(Component.LEFT_ALIGNMENT);
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

    private void displayResultWithSteps(Calculations calc) {
        StringBuilder formatBuilder = new StringBuilder();
        formatBuilder.append("====================================================================\n");
        formatBuilder.append(" CALCULATION STEPS LOGS\n");
        formatBuilder.append("====================================================================\n");
        formatBuilder.append(calc.steps).append("\n");
        formatBuilder.append("====================================================================\n");

        if (calc.matrix.length == 1 && calc.matrix[0].length == 1) {
            formatBuilder.append(" FINAL OUTPUT ANSWER\n");
            formatBuilder.append("====================================================================\n");
            formatBuilder.append(String.format(" Determinant = %.0f\n", calc.matrix[0][0]));
        } else {
            formatBuilder.append(" FINAL OUTPUT ANSWER GRID\n");
            formatBuilder.append("====================================================================\n");
            formatBuilder.append(matrixToString(calc.matrix));
        }

        formatBuilder.append("====================================================================\n");
        
        outputArea.setText(formatBuilder.toString());
        outputArea.setCaretPosition(0); // Automatically snaps the view scroll bar back to the top
    }
}