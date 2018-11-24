import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

/**
 * Write a description of class Circle here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Circle implements Drawable{
    private double x;
    private double y;
    private double radius;
    private Color color;
    
    public Circle(double x, double y, double radius) {
        this(x, y, radius, Color.green);
    }
    
    public Circle(double x, double y, double radius, Color color) {
        if (radius < 0) {
            throw new IllegalArgumentException("Argument must not be less than zero");
        }
        this.x = x;
        this.y = y;
        this.radius = radius;
        this.color = color;
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
    
    public Color getColor() {
        return color;
    }
    
    public double cornerPt(double val, double r) {
        return val - r;        
    }
    
    public void draw(Graphics g) {
        g.setColor(Color.black);
        g.fillOval(Math.round((long) cornerPt(x, radius)), Math.round((long) cornerPt(y, radius)), 
                   Math.round((long) radius), Math.round((long) radius));
    }
    
    // public void paintComponent(Graphics g) {
        
        // g.drawOval(Math.round((long) cornerPt(x, radius)), Math.round((long) cornerPt(y, radius)), 
                   // Math.round((long) radius), Math.round((long) radius));
    // }
}   
