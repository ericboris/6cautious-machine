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
    private int x;
    private int y;
    private int radius;
    private int depth;
    private int ratio;
    private double angle;
    private int maxDepth;
    private Color color1;
    private Color color2;
    private boolean useGradient;
    
    private ArrayList<Circle> elements;
    private ArrayList<Observer> observers;

    public Fractal() {
        elements = new ArrayList<Circle>();
        observers = new ArrayList<Observer>();
    }

    private ArrayList<Circle> generateFractal(double x, double y, 
    double radius, double a1, double a2, int depth, double ratio) {
        //if ((int) radius > 1 && depth > 0) {
        if (depth <= maxDepth) {
            // Add the current element to the array
            elements.add(new Circle(x, y, radius, getColor(depth)));

            double newRadius = radius * ratio * 0.01;
            
            // the left branch
            generateFractal(x + (radius + newRadius) * Math.sin(a1 - a2), 
                            y - (radius + newRadius) * Math.cos(a1 - a2), 
                            newRadius, 
                            a1 - a2,
                            a2,
                            depth + 1, 
                            ratio);            
                            
            // the right branch
            generateFractal(x + (radius + newRadius) * Math.sin(a1 + a2), 
                            y - (radius + newRadius) * Math.cos(a1 + a2), 
                            newRadius, 
                            a1 + a2,
                            a2,
                            depth + 1,
                            ratio);
        }
        return elements;
    }

    public void register(Observer observer) {
        observers.add(observer);
    }

    public void remove(Observer observer) {
        observers.remove(observers.indexOf(observer));
    }

    public void notifyObservers() {
        if (observers != null) {
            for (Observer observer : observers) {
                observer.update();
            }
        }
    }

    public void setData(int x, int y, int radius, int depth, int ratio, int angle, 
                        Color color1, Color color2, boolean useGradient) {
        this.x = x;
        this.y = y;
        this.radius = radius;
        this.depth = depth;
        this.ratio = ratio;
        this.angle = Math.toRadians(angle);
        this.color1 = color1;
        this.color2 = color2;
        this.useGradient = useGradient;
        this.maxDepth = maxDepth(radius, depth, ratio);
        notifyObservers();
    }

    public ArrayList<Circle> getData() {
        elements.clear();
        return generateFractal(x, y, radius, 0.0, angle, 0, ratio);
    }
    
    private Color getColor(int depth) {
        if (!useGradient) {
            if (maxDepth != depth) {
                return color1;
            }
            return color2;
        } else {
            //double ratio = depth / maxDepth;
            //double ratio = 2/3;
            
            //System.out.println(ratio + " " + red + " " + green + " " + blue);
            return interpolateColors((double) depth / (double) maxDepth);
        }
    }
    
    private Color interpolateColors(double ratio) {
        int red = (int) Math.abs((ratio * color2.getRed()) + ((1 - ratio) * color1.getRed()));
        int green = (int) Math.abs((ratio * color2.getGreen()) + ((1 - ratio) * color1.getGreen()));
        int blue = (int) Math.abs((ratio * color2.getBlue()) + ((1 - ratio) * color1.getBlue()));
        return new Color(red, green, blue);
    }
    
    private int maxDepth(double radius, int depth, double ratio) {
        int count = 0;
        while ((int) radius > 1 && depth > 0) {
            radius = radius * ratio * 0.01;
            depth--;
            count++;
        }
        return count;
    }
}
