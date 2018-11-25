import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
/**
 * Run and display the GUI and fractals
 *
 * @author Eric Boris
 * @version 11/23/18
 */
public class Main {
    public static void main(String[] args) {
        Fractal fractal = new Fractal();
        Settings settings = new Settings(fractal);
        Toolkit tk = Toolkit.getDefaultToolkit();
        Display d = new Display(tk.getScreenSize().width - settings.getPreferredSize().width,
                                tk.getScreenSize().height, fractal);
    }
}
