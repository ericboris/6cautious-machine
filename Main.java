import java.awt.Toolkit;
import java.awt.Dimension;

/**
 * Run and display the GUI and fractals
 *
 * @author Eric Boris
 * @version 11/23/18
 */
public class Main {
    /**
     * run and display the fractal and its settings
     * 
     * @param   args            the command line arguments
     */
    public static void main(String[] args) {
        Fractal fractal = new Fractal();
        Settings settings = new Settings(fractal);
        Toolkit tk = Toolkit.getDefaultToolkit();
        Dimension disp_dim = new Dimension(tk.getScreenSize().width - settings.getPreferredSize().width,
                                           tk.getScreenSize().height);
        Display display = new Display(fractal, disp_dim);
    }
}
