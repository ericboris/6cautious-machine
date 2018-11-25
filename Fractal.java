import java.awt.Color;
import javax.swing.JPanel;
import java.awt.Graphics;

/**
 * generate a fractal
 *
 * @author Eric Boris
 * @version 11/18/2018
 */
public class Fractal extends JPanel implements Subject{
    /** x                       the starting x value */
    private int x;
    /** y                       the starting y value */
    private int y;
    /** radius                  the starting radius */
    private int radius;
    /** depth                   the draw depth */
    private int depth;
    /** ratio                   the draw ratio */
    private int ratio;
    /** angle                   the draw angle */
    private double angle;
    /** fillColor1              the largest elements fill color */
    private Color fillColor1;
    /** fillColor2              the smallest elements fill color */
    private Color fillColor2;
    /** lineColor1              the largest elements ouline color */
    private Color lineColor1;
    /** lineColor2              the smallest elements outline color */
    private Color lineColor2;
    /** useGradient             use a gradient on the colors */
    private boolean useGradient;
    /** useOutline              use an outline on the elements */
    private boolean useOutline;
    /** maxDepth                store the maximum draw depth */
    private int maxDepth;
    /** elements                the elements that comprise the fractal */
    private ArrayList<Circle> elements;
    /** observers               the observers of this subject */
    private ArrayList<Observer> observers;

    /**
     * construct a new fractal
     */
    public Fractal() {
        elements = new ArrayList<Circle>();
        observers = new ArrayList<Observer>();
    }

    /**
     * generate the fractal array
     * 
     * @param   x               the x position to draw this element
     * @param   y               the y position to draw this element
     * @param   radius          the radius of this element
     * @param   a1              the starting angle of this element
     * @param   a2              the angle modifier of this element
     * @param   depth           the current depth of this element
     * @param   ratio           the ratio between this element and the next
     * @return                  an array of fractal elements
     */
    private ArrayList<Circle> generateFractal(double x, double y, double radius, 
                                              double a1, double a2, int depth, 
                                              double ratio) {
        //if ((int) radius > 1 && depth > 0) {
        if (depth <= maxDepth) {
            // Add the current element to the array
            // if outline is being used add that color to the new circle too
            if (useOutline) {
                elements.add(new Circle(x, y, radius, 
                                        getColor(depth, fillColor1, fillColor2), 
                                        getColor(depth, lineColor1, lineColor2)));
            } else {
                elements.add(new Circle(x, y, radius, 
                                        getColor(depth, fillColor1, fillColor2)));
            }

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

    /**
     * add the given observer to this subjects list of observers
     * 
     * @param   observer            the observer to add
     */
    public void register(Observer observer) {
        observers.add(observer);
    }

    /**
     * remove the given observer from this subjects list of observers
     * 
     * @param   observer            the observer to remove
     */
    public void remove(Observer observer) {
        observers.remove(observers.indexOf(observer));
    }

    /**
     * notify this subjects list of observers of changes made to this subject
     */
    public void notifyObservers() {
        if (observers != null) {
            for (Observer observer : observers) {
                observer.update();
            }
        }
    }

    /**
     * set the data of this subject's fractal elements
     * 
     * @param   x               the x position of the first element
     * @param   y               the y position of the first element
     * @param   radius          the radius of the first element
     * @param   depth           the depth of elements to draw
     * @param   ratio           the ratio between elements
     * @param   angle           the angle between elements
     * @param   fillColor1      the fill color of the largest element
     * @param   fillColor2      the fill color of the smallest element
     * @param   lineColor1      the line color of the largest element
     * @param   lineColor2      the line color of the smallest element
     * @param   useOutline       draw an outline around the elements
     * @param   useGradient     iterpolate colors between each element
     */
    public void setData(int x, int y, int radius, int depth, int ratio, int angle, 
                        Color fillColor1, Color fillColor2, Color lineColor1, 
                        Color lineColor2, boolean useOutline, boolean useGradient) {
        this.x = x;
        this.y = y;
        this.radius = radius;
        this.depth = depth;
        this.ratio = ratio;
        this.angle = Math.toRadians(angle);
        this.fillColor1 = fillColor1;
        this.fillColor2 = fillColor2;
        this.lineColor1 = lineColor1;
        this.lineColor2 = lineColor2;
        this.useOutline = useOutline;
        this.useGradient = useGradient;
        this.maxDepth = maxDepth(radius, depth, ratio);
        notifyObservers();
    }

    /**
     * get the fractal data
     * 
     * @return                  an arraylist fractal data
     */
    public ArrayList<Circle> getData() {
        elements.clear();
        return generateFractal(x, y, radius, 0.0, angle, 0, ratio);
    }

    /**
     * get the current elements color
     * 
     * @param   depth           the elements current depth
     * @param   color1          the larger elements color
     * @param   color2          the smaller elements color
     * @return                  a color to apply to an element
     */
    private Color getColor(int depth, Color color1, Color color2) {
        // standard coloring
        if (!useGradient) {
            if (maxDepth != depth) {
                return color1;
            }
            return color2;
        } else { // fade between colors
            return interpolateColors((double) depth / (double) maxDepth, color1, color2);
        }
    }

    /**
     * return a color some way between the given colors
     * 
     * @param   ratio           the current ratio between colors
     * @param   color1          the first color to fade between
     * @param   color2          the second color to fade between
     * @return                  the new color part way between the given colors
     */
    private Color interpolateColors(double ratio, Color color1, Color color2) {
        int red = (int) Math.abs((ratio * color2.getRed()) + ((1 - ratio) * color1.getRed()));
        int green = (int) Math.abs((ratio * color2.getGreen()) + ((1 - ratio) * color1.getGreen()));
        int blue = (int) Math.abs((ratio * color2.getBlue()) + ((1 - ratio) * color1.getBlue()));
        return new Color(red, green, blue);
    }

    /**
     * the maximum depth that elements will be drawn at
     * 
     * @param   radius          the largest elements starting radius
     * @param   depth           the desired draw depth
     * @param   ratio           the ratio between elements
     * @return                  the maximum depth that elements will be drawn at
     */
    private int maxDepth(double radius, int depth, double ratio) {
        int count = 0;
        while ((int) radius > 1 && depth > 0) {
            radius = radius * ratio * 0.01;
            depth--;
            count++;
        }
        return count;
    }

    /**
     * create a circle and it's data
     */
    private class Circle implements Drawable{
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
}
