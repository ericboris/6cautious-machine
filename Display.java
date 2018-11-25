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
    /** width                   the width of the display */
    private int width;
    /** height                  the height of the display */
    private int height;
    
    /** subject                 this display's subject */
    private Subject subject;
    /** elements                the subject's elements */
    private ArrayList<Circle> elements;
    
    /**
     * construct a display
     * 
     * @param   subject         the subject to register this display with
     * @param   width           the width of the display
     * @param   height          the height of the display
     */
    public Display(Subject subject, int width, int height) {
        if (subject == null) {
            throw new IllegalArgumentException("Argument must not be null");
        }
        this.subject = subject;
        this.subject.register(this);

        this.width = width;
        this.height = height;
        
        setLayout(null);
        
        JFrame df = new JFrame("Fractal");
        df.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        df.getContentPane().add(this);
        df.pack();
        df.setLocation(Toolkit.getDefaultToolkit().getScreenSize().width - width, 0);
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
        return new Dimension(width, height);
    }
}
