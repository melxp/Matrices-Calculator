import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.*;

public class MatrixHome {

    private JFrame frame;

    public MatrixHome() {

        // Create main window frame
        frame = new JFrame("Matrix Calculator Home");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000, 800);
        frame.setLocationRelativeTo(null); // Centre window

        // Create background panel
        JPanel background = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);

                Graphics g2 = (Graphics2D) g;

                // Set background color
                g2.setColor(new Color(165, 197, 208));
                g2.fillRect(0, 0, getWidth(), getHeight());

                // Create faded symbols
                g2.setColor(new Color(255, 255, 255, 45));

                // Symbols
                g2.setFont(new Font("Monospaced", Font.PLAIN, 120));
                g2.drawString("(", 20, 630);
                g2.drawString(")", 120, 630);

                g2.setFont(new Font("Monospaced", Font.BOLD, 100));
                g2.drawString("*", 70, 280);
                g2.drawString("λ", 323, 320);
                g2.drawString("+", 630, 250);
                g2.drawString("\\", 760, 140);
                g2.drawString("/", 250, 540);
                g2.drawString("-", 845, 520);

                // Text
                g2.setFont(new Font("SansSerif", Font.PLAIN, 65));
                g2.drawString("PDP", 130, 130);
                g2.drawString("A", 470, 180);
                g2.drawString("det(A)", 780, 280);
                g2.drawString("(A)", 280, 680);
                g2.drawString("Av=λv", 470, 580);
                g2.drawString("RREF", 700, 680);

                g2.setFont(new Font("SansSerif", Font.PLAIN, 50));
                g2.drawString("10", 78, 590);
                g2.drawString("01", 83, 640);

                // Subscripts
                g2.setFont(new Font("SansSerif", Font.PLAIN, 35));
                g2.drawString("-1", 280, 110);
                g2.drawString("T", 505, 130);
                g2.drawString("ij", 380, 690);

            }
        };

        background.setLayout(new BorderLayout());
        frame.setContentPane(background);

        // Create centre panel
        JPanel centrePanel = new JPanel();
        centrePanel.setLayout(new BoxLayout(centrePanel, BoxLayout.Y_AXIS));
        centrePanel.setOpaque(false);

        // Create title label
        JLabel titleLabel = new JLabel("Matrix Calculator");
        titleLabel.setFont(new Font("Monospaced", Font.BOLD, 56));
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Create start button
        RoundedButton startButton = new RoundedButton(
            "Start",
            Color.BLACK,
            Color.WHITE,
            new Color(240, 240, 245),
            new Color(220, 220, 225)
        );
        startButton.setPreferredSize(new Dimension(220, 60));
        startButton.setMaximumSize(new Dimension(220, 60));
        startButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        startButton.addActionListener(e -> {
            frame.dispose(); // Close home page
            new MatrixCalculator(); // Open calculator
        });

        // Create flexible spacing
        centrePanel.add(Box.createVerticalGlue());
        centrePanel.add(titleLabel);
        centrePanel.add(Box.createRigidArea(new Dimension(0, 40)));
        centrePanel.add(startButton);
        centrePanel.add(Box.createVerticalGlue());

        // Add centre panel to background
        background.add(centrePanel, BorderLayout.CENTER);

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

        bottomPanel.add(copyright, BorderLayout.WEST);
        bottomPanel.add(helpButton, BorderLayout.EAST);

        background.add(bottomPanel, BorderLayout.SOUTH);

        JPanel versionPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        versionPanel.setOpaque(false);

        JLabel version = new JLabel("Version 1.0");
        version.setForeground(Color.WHITE);
        version.setFont(new Font("Monospaced", Font.PLAIN, 24));

        versionPanel.add(version);

        background.add(versionPanel, BorderLayout.NORTH);

        // Make window visible
        frame.setVisible(true);
        
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(MatrixHome::new);
    }
}