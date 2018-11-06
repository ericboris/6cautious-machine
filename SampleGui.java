import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class SampleGui extends JFrame {
    // can also make a tool kit
    // provides interface between frame and os
    private Toolkit tk;
    
    public SampleGui() {
        // first create jframe
        setTitle("title");
        setSize(300, 200);
        
        // toolkit
        tk = getToolkit();
        Dimension size = tk.getScreenSize();
        setLocation((size.width - getWidth()) / 2, (size.height - getHeight()) / 2);
        
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        
        // then put jpanel inside jframe
        // supports jpanel inside jpanel too
        JPanel p = new JPanel();
        getContentPane().add(p);
        p.setLayout(null); // this turns off layout manager. prevents interference
        
        // now add buttons
        JButton b = new JButton("Bees!");
        b.setBounds(150, 60, 80, 30);
        // an anonomyous class inside actionListener
        b.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                tk.beep();
            }
        });
        p.add(b);

        JButton c = new JButton("See?");
        c.setBounds(150, 30, 80, 30);
        c.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // this is a rare case where this should be used
                System.exit(0);
            }
        });
        p.add(c);
        
        setVisible(true);
    }
    
    public static void main(String[] args) {
        SampleGui sg = new SampleGui();
    }
}