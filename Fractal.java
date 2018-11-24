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
    
    private ArrayList<Circle> elements;
    private ArrayList<Observer> observers;

    public Fractal() {
        setData(0, 0, 0);
        observers = new ArrayList<Observer>();
    }

    private ArrayList<Circle> generateFractal(ArrayList<Circle> elements, double x, double y, 
    double radius, double angle, double ratio, int depth) {
        if ((int) radius > 0 && depth > 0) {
            // Add the current element to the array
            elements.add(new Circle(x, y, radius));
            // TODO - remove this line
            System.out.println(x + ", " + y + ", " + radius + ", " + angle);
            
            
            double newRadius = radius * ratio * 0.01;
            double angleInRads = angle * Math.PI / 180;
            double newLeftX = (radius + newRadius) * Math.sin(-angleInRads) + x;
            double newRightX = (radius + newRadius) * Math.sin(angleInRads) + x;
            double newY = (radius + newRadius) * Math.cos(angleInRads) + y;
            double leftAngle = angle + angle;
            double rightAngle = angle - angle;
  
            generateFractal(elements, newLeftX, newY, newRadius, leftAngle, ratio, depth - 1);
            generateFractal(elements, newRightX, newY, newRadius, rightAngle, ratio, depth - 1);
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

    public void setData(int depth, int ratio, int angle) {
        elements = new ArrayList<Circle>();
        this.depth = depth;
        this.ratio = ratio;
        this.angle = angle;
        notifyObservers();
    }

    public ArrayList<Circle> getData() {
        return generateFractal(elements, 100, 100, 100, angle, ratio, depth);
    }
}
