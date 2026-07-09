import javax.swing.*;
import java.awt.*;

public class MatrixHelp {
    
    private JFrame frame;

    public MatrixHelp() {

        // Create help window frame
        frame = new JFrame("Matrix Calculator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1100, 850);
        frame.setLocationRelativeTo(null); // Centre window

        // Set background colour
        JPanel background = new JPanel(new BorderLayout());
        background.setBackground(new Color(165, 197, 208));

        frame.setContentPane(background);

        background.add(createMainPanel(), BorderLayout.CENTER);
        background.add(createFooter(), BorderLayout.SOUTH);

        frame.setVisible(true);
    }

}
