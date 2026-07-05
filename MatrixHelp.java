import javax.swing.*;
import java.awt.*;

public class MatrixHelp {
    
    private JFrame helpFrame;

    public MatrixHelp() {

        // Create help window frame
        helpFrame = new JFrame("Matrix Help");
        helpFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        helpFrame.setSize(1000, 800);
        helpFrame.setLocationRelativeTo(null); // Centre window

        helpFrame.setLayout(new BorderLayout());

        helpFrame.getContentPane().setBackground(new Color(165, 197, 208));

        helpFrame.setVisible(true);

        // Create centre panel
        JPanel centrePanel = new JPanel();
        centrePanel.setLayout(new BoxLayout(centrePanel, BoxLayout.Y_AXIS));
        centrePanel.setOpaque(false);
        centrePanel.setBackground(Color.WHITE);

        // 
        JLabel titleLabel = new JLabel("Welcome");
        titleLabel.setFont(new Font("Monospaced", Font.BOLD, 56));
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        helpFrame.add(centrePanel);
        helpFrame.setVisible(true);

    }
}
