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
        Toolkit tk = Toolkit.getDefaultToolkit();
        
        Fractal f = new Fractal();
        Settings s = new Settings(f);
        Display d = new Display(tk.getScreenSize().width - s.getSettingsDimension().width,
                                tk.getScreenSize().height, f);
    }
}
