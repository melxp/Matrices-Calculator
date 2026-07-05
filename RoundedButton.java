import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class RoundedButton extends JButton {

    // Colours
    private final Color base = Color.WHITE;
    private final Color hover = new Color(240, 240,245);
    private final Color pressed = new Color(220, 220, 225);

    private Color current = base;

    public RoundedButton(String text) {
        super(text);

        // Turn off default Swing painting
        setFocusPainted(false);
        setContentAreaFilled(false);
        setBorderPainted(false);
        setOpaque(false);

        setBorder(BorderFactory.createEmptyBorder());

        setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        setForeground(Color.BLACK);
        setFont(new Font("Monospaced", Font.PLAIN, 20));

        // Listeners for mouse activity
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                current = hover;
                repaint();
            }

            @Override
            public void mouseExited(MouseEvent e) {
                current = base;
                repaint();
            }

            @Override
            public void mousePressed(MouseEvent e) {
                current = pressed;
                repaint();
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                if (getBounds().contains(e.getComponent().getParent().getMousePosition())) {
                    current = hover;
                } else {
                    current = base;
                }
                repaint();
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {

        Graphics2D g2 = (Graphics2D) g.create();

        // Enable anti-aliasing for rounded edges
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                            RenderingHints.VALUE_ANTIALIAS_ON);
                            

        int radius = 40;
        int width = getWidth();
        int height = getHeight();

        // Fill the rounded rectangle
        g2.setColor(current);
        g2.fillRoundRect(0, 0, width, height, radius, radius);

        g2.dispose();

        // Paint text on top
        super.paintComponent(g);
    
    }
    
}