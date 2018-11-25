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
    private int size;
    private int depth;
    private int ratio;
    private double angle;
    private Color color1;
    private Color color2;
    
    private ArrayList<Circle> elements;
    private ArrayList<Observer> observers;

    public Fractal() {
        elements = new ArrayList<Circle>();
        observers = new ArrayList<Observer>();
    }

    private ArrayList<Circle> generateFractal(double x, double y, 
    double radius, double a1, double a2, int depth, double ratio) {
        if ((int) radius > 1 && depth > 0) {
            // Add the current element to the array
            elements.add(new Circle(x, y, radius, setColor()));

            double newRadius = radius * ratio * 0.01;
            
            // the left branch
            generateFractal(x + (radius + newRadius) * Math.sin(a1 - a2), 
                            y - (radius + newRadius) * Math.cos(a1 - a2), 
                            newRadius, 
                            a1 - a2,
                            a2,
                            depth - 1, 
                            ratio);            
                            
            // the right branch
            generateFractal(x + (radius + newRadius) * Math.sin(a1 + a2), 
                            y - (radius + newRadius) * Math.cos(a1 + a2), 
                            newRadius, 
                            a1 + a2,
                            a2,
                            depth - 1,
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

    public void setData(int x, int y, int size, int depth, int ratio, int angle, Color color1, Color color2) {
        this.x = x;
        this.y = y;
        this.size = size;
        this.depth = depth;
        this.ratio = ratio;
        this.angle = Math.toRadians(angle);
        this.color1 = color1;
        this.color2 = color2;
        notifyObservers();
    }

    public ArrayList<Circle> getData() {
        elements.clear();
        System.out.println("\n");
        return generateFractal(x, y, size, 0.0, angle, depth, ratio);
    }
    
    private Color setColor() {
        return color1;
    }
}
