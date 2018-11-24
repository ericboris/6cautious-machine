import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
/**
 * handle displaying a fractal window and a settings window
 *
 * @author Eric Boris
 * @version 11/7/18
 */
public class Display extends JPanel implements Observer {
    private JFrame df;
    private JPanel dp;
    private Toolkit tk;
    
    
    private int DISP_WIDTH;
    private int DISP_HEIGHT;
    
    private Subject subject;
    private ArrayList<Circle> elements;
    
    public Display(int width, int height, Subject subject) {
        if (subject == null) {
            throw new IllegalArgumentException("Argument must not be null");
        }
        this.subject = subject;
        this.subject.register(this);

        this.DISP_WIDTH = width;
        this.DISP_HEIGHT = height;
        
        
        //tk = Toolkit.getDefaultToolkit();
        
        // dp = new JPanel();
        // dp.setPreferredSize(new Dimension(width, height));
        // dp.setLayout(null);
        
        //new JPanel();
        //setPreferredSize(new Dimension(width, height));
        setLayout(null);
        
        df = new JFrame("Fractal");
        df.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        df.getContentPane().add(this);
        df.pack();
        df.setLocation(Toolkit.getDefaultToolkit().getScreenSize().width - width, 0);
        df.setVisible(true);
        //df.setResizable(false);
    }
    
    public void update() {
        elements = subject.getData();
        System.out.println("Display update called");
        repaint();
    }
    
    @Override 
    protected void paintComponent(Graphics g) {
        System.out.println("paintComponent Called");
        super.paintComponent(g);
        for (Drawable element : elements) {
            element.draw(g);
        }
    }
    
    @Override
    public Dimension getPreferredSize() {
        return new Dimension(DISP_WIDTH, DISP_HEIGHT);
    }
}
