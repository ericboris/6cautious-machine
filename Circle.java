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
    private Color fillColor;
    private Color outlineColor;
    
    public Circle(double x, double y, double radius) {
        this(x, y, radius, Color.green);
    }
    
    public Circle(double x, double y, double radius, Color fillColor) {
        this(x, y, radius, fillColor, null);
    }
    
    public Circle(double x, double y, double radius, Color fillColor, Color outlineColor) {
        if (radius < 0) {
            throw new IllegalArgumentException("Argument must not be less than zero");
        }
        this.x = x;
        this.y = y;
        this.radius = radius;
        this.fillColor = fillColor;
        this.outlineColor = outlineColor;
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
    
    public Color getColor1() {
        return fillColor;
    }
    
    public Color getColor2() {
        return outlineColor;
    }
    
    public double cornerPt(double val, double radius) {
        return val - radius;        
    }
    
    public void draw(Graphics g) {
        g.setColor(fillColor);
        g.fillOval(Math.round((long) cornerPt(x, radius)), Math.round((long) cornerPt(y, radius)), 
                   Math.round((long) radius * 2), Math.round((long) radius * 2));
        if (outlineColor != null) {
            g.setColor(outlineColor);
            g.drawOval(Math.round((long) cornerPt(x, radius)), Math.round((long) cornerPt(y, radius)), 
                   Math.round((long) radius * 2), Math.round((long) radius * 2));
        }
    }
}   
