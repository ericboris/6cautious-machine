import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

/**
 * generate a fractal
 *
 * @author Eric Boris
 * @version 11/18/2018
 */
public class Fractal extends JPanel implements Subject{
    private int depth;
    private int ratio;
    private int angle;
    private ArrayList<Circle> circles;
    JPanel p;
    
    public Fractal() {
        ArrayList<Circle> circles = new ArrayList<Circle>();
        createCircleArray(circles, 600, 400, 100, Math.PI / 4, 0.5);
    }
    
    private ArrayList<Circle> createCircleArray(ArrayList<Circle> circles, double x, double y, 
                                         double radius, double angle, double ratio) {
        if ((int) radius > 0) {
            double newX = (radius + radius / ratio) * Math.sin(angle) + x;
            double newY = (radius + radius / ratio) * Math.cos(angle) + y;
            double newRadius = radius * ratio;
            double leftA = angle + angle;
            double rightA = angle - angle;
            // create a left branch
            circles = createCircleArray(circles, newX, newY, newRadius, leftA, ratio);
            // add a circle at current x and y with current radius
            circles.add(new Circle(x, y, radius));
            System.out.println(x + ", " + y + ", " + radius + ", " + angle);
            // create a right branch
            circles = createCircleArray(circles, newX, newY, newRadius, rightA, ratio);
        }
        return circles;
    }
    
    public ArrayList<Circle> getCircles() {
        return circles;
    }
        
    public double[] cornerPoint(double x, double y, double r) {
        double[] points = new double[2];
        points[0] = x - r;
        points[1] = y - r;
        return points;        
    }
    
    public void register(Observer observer) {}
    
    public void remove(Observer observer) {}
    
    public void notifyObservers() {}
    
    public void drawC(ArrayList<Circle> circles) {
        for (Circle c : circles) {
            double[] cp = cornerPoint(c.getX(), c.getY(), c.getRadius());
            // drawCircle(cp[0], cp[1], c.getRadius() * 2, c.getRadius() * 2);
            // draw a fillOval(x, y, r * 2, r * 2);  
        }
    }
    
    private static class Circle {
        private double x;
        private double y;
        private double radius;
        
        public Circle(double x, double y, double r) {
            if (x < 0 || y < 0 || r < 0) {
                throw new IllegalArgumentException("Argument must not be less than zero");
            }
            this.x = x;
            this.y = y;
            this.radius = r;
        }
        
        public double getX() {
            return x;
        }
        
        public double getY() {
            return y;
        }
        
        public double getRadius() {
            return radius;
        }
    }
    
    public static void main(String[] args) {
        Fractal f = new Fractal();
        ArrayList<Circle> circles = f.getCircles();
        
        //p = new JPanel();


        
        JFrame frame = new JFrame("Settings");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(new JPanel());
        frame.pack();
        frame.setVisible (true);
        frame.setSize(1200, 800);
        
        
    }
    
    
}
