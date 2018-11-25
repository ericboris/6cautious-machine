import java.awt.Toolkit;
import java.awt.Graphics;
import java.awt.Dimension;
import javax.swing.JPanel;
import javax.swing.JFrame;

/**
 * handle displaying window
 * 
 * @author Eric Boris
 * @version 11/7/18
 */
public class Display extends JPanel implements Observer {
    /** dimension               this displays dimensions */
    private Dimension dimension;

    /** subject                 this display's subject */
    private Subject subject;
    /** elements                the subject's elements */
    private ArrayList<? extends Drawable> elements;

    /**
     * construct a display
     * 
     * @param   subject         the subject to register this display with
     * @param   dimension       the displays dimensions
     */
    public Display(Subject subject, Dimension dimension) {
        if (subject == null) {
            throw new IllegalArgumentException("Argument must not be null");
        }
        this.subject = subject;
        this.subject.register(this);

        this.dimension = dimension;

        setLayout(null);

        JFrame df = new JFrame("Fractal");
        df.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        df.getContentPane().add(this);
        df.pack();
        df.setLocation(Toolkit.getDefaultToolkit().getScreenSize().width - dimension.width, 0);
        df.setVisible(true);
    }

    /**
     * update the contents of the display
     */
    public void update() {
        elements = subject.getData();
        repaint();
    }

    /**
     * paint the elements to the display
     * 
     * @param   g               the graphics object to paint onto
     */
    @Override 
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (Drawable element : elements) {
            element.draw(g);
        }
    }

    /**
     * return the preffered size of the display
     * 
     * @return                  the preferred size of the display
     */
    @Override
    public Dimension getPreferredSize() {
        return dimension;
    }
}
