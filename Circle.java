import java.awt.Color;
import java.awt.Graphics;

/**
 * Write a description of class Circle here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Circle implements Drawable{
    /** x                   the x coordinate of the circle */
    private double x;
    /** y                   the y coordinate of the circle */
    private double y;
    /** radius              the radius of the circle */
    private double radius;
    /** fillColor           the fill color of the circle */
    private Color fillColor;
    /** outlineColor        the outline color of the circle */ 
    private Color outlineColor;

    /**
     * a circle constructor
     * 
     * @param   x               the x coordinate of the circle
     * @param   y               the y coordinate of the circle
     * @param   radius          the radius of the circle
     * @param   fillColor       the fill color of the circle
     */
    public Circle(double x, double y, double radius, Color fillColor) {
        this(x, y, radius, fillColor, null);
    }
    
    /**
     * a circle constructor with a defined fill color and outline color
     * 
     * @param   x               the x coordinate of the circle
     * @param   y               the y coordinate of the circle
     * @param   radius          the radius of the circle
     * @param   fillColor       the fill color of the circle
     * @param   outlineColor    the outline color of the circle
     */
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
    
    /**
     * get the graphics draw origin of the circle
     * 
     * @param   val             the coordinates current value
     * @param   radius          the radius of the circle
     * @return                  the corner point draw origin of the circle
     */
    private double cornerPt(double val, double radius) {
        return val - radius;        
    }
    
    /**
     * draw the circle
     * 
     * @param   g               the graphics object to draw onto
     */
    public void draw(Graphics g) {
        g.setColor(fillColor);
        g.fillOval(Math.round((long) cornerPt(x, radius)), Math.round((long) cornerPt(y, radius)), 
                   Math.round((long) radius * 2), Math.round((long) radius * 2));
        // only draw an outline if an outline color is assigned
        if (outlineColor != null) {
            g.setColor(outlineColor);
            g.drawOval(Math.round((long) cornerPt(x, radius)), Math.round((long) cornerPt(y, radius)), 
                   Math.round((long) radius * 2), Math.round((long) radius * 2));
        }
    }
}   
