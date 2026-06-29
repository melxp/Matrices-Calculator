import javax.swing.*;
import java.awt.*;

public class MatrixHome {

    public static void main(String[] args) {
        // Create GUI on correct event thread
        SwingUtilities.invokeLater(() -> {

            // Create main window frame
            JFrame frame = new JFrame("Matrix Calculator");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(1000, 800);
            frame.setLocationRelativeTo(null); // Centre window

            frame.setLayout(new BorderLayout());

            frame.getContentPane().setBackground(new Color(188, 234, 243));

            // Create centre panel
            JPanel centrePanel = new JPanel();
            centrePanel.setLayout(new BoxLayout(centrePanel, BoxLayout.Y_AXIS));
            centrePanel.setOpaque(false);

            // Create title label
            JLabel titleLabel = new JLabel("Matrix Calculator 234");
            titleLabel.setFont(new Font("Monospaced", Font.BOLD, 48));
            titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

            // Create start button
            JButton startButton = new JButton("Start");
            startButton.setFont(new Font("Monospaced", Font.PLAIN, 20));
            startButton.setAlignmentX(Component.CENTER_ALIGNMENT);

            // Use 'Glue' and 'RigidArea' to create flexible spacing
            centrePanel.add(Box.createVerticalGlue());
            centrePanel.add(titleLabel);
            centrePanel.add(Box.createRigidArea(new Dimension(0, 25)));
            centrePanel.add(startButton);
            centrePanel.add(Box.createVerticalGlue());

            // Add centre panel to frame
            frame.add(centrePanel, BorderLayout.CENTER);

            // Help button
            JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));

            JButton helpButton = new JButton("?");
            helpButton.setFont(new Font("Monospaced", Font.BOLD, 16));

            bottomPanel.add(helpButton);

            frame.add(bottomPanel, BorderLayout.SOUTH);

            // Make window visible
            frame.setVisible(true);
        
        
        });
    }
}