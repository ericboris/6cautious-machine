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
    private ArrayList<Observer> observers;
    JPanel p;

    private Toolkit tk;

    public Fractal() {
        setData(0, 0, 0);
        observers = new ArrayList<Observer>();
    }

    private ArrayList<Circle> createCircleArray(ArrayList<Circle> circles, double x, double y, 
    double radius, double angle, double ratio, int depth) {
        if ((int) radius > 0 && depth > 0) {
            circles.add(new Circle(x, y, radius));
            System.out.println(x + ", " + y + ", " + radius + ", " + angle);
            // add a circle at current x and y with current radius
            
            double newRadius = radius * ratio * 0.01;
            double angleInRads = angle * Math.PI / 180;
            double newLeftX = (radius + newRadius) * Math.sin(-angleInRads) + x;
            double newRightX = (radius + newRadius) * Math.sin(angleInRads) + x;
            double newY = (radius + newRadius) * Math.cos(angleInRads) + y;
            double leftA = angle + angle;
            double rightA = angle - angle;
            // create a left branch
            // removed x change x
            createCircleArray(circles, newLeftX, newY, newRadius, leftA, ratio, depth - 1);
            
            
            
            // create a right branch
            // removed x change x
            createCircleArray(circles, newRightX, newY, newRadius, rightA, ratio, depth - 1);
        }
        return circles;
    }

    public void register(Observer observer) {
        observers.add(observer);
    }

    public void remove(Observer observer) {
        observers.remove(observers.indexOf(observer));
    }

    public void notifyObservers() {
        for (Observer o : observers) {
            o.update();
        }
    }

    public void setData(int depth, int ratio, int angle) {
        circles = new ArrayList<Circle>();
        this.depth = depth;
        this.ratio = ratio;
        this.angle = angle;
        //notifyObservers();
    }

    public ArrayList<Circle> getData() {
        return createCircleArray(circles, 100, 100, 100, angle, ratio, depth);
    }
}
