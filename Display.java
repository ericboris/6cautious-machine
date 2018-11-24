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
    private Subject subject;
    private ArrayList<Circle> elements;
    
    public Display(int width, int height, Subject subject) {
        if (subject == null) {
            throw new IllegalArgumentException("Argument must not be null");
        }
        this.subject = subject;
        
        tk = Toolkit.getDefaultToolkit();
        
        dp = new JPanel();
        dp.setPreferredSize(new Dimension(width, height));
        dp.setLayout(null);
        
        df = new JFrame("Fractal");
        df.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        df.getContentPane().add(dp);
        df.pack();
        df.setVisible(true);
        df.setLocation(tk.getScreenSize().width - width, 0);
        //df.setResizable(false);
    }
    
    public void update() {
        elements = subject.getData();
    }
    
    @Override 
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (Drawable element : elements) {
            element.draw(g);
        }
    }
}
