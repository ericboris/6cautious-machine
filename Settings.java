import java.awt.Toolkit;
import java.awt.Graphics;
import java.awt.Dimension;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.JSlider;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JColorChooser;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;


/**
 * a settings panel
 *
 * @author Eric Boris
 * @version 11/18/2018
 */
public class Settings extends JPanel {
    /** fractal                 the fractal object this panel modifies */
    private Fractal fractal;

    /** xSlider                 the slider for the x value */
    private JSlider xSlider;
    /** xCurrent                the label of the current x value */
    private JLabel xCurrent;
    /** xLabel                  the label of the x slider */
    private JLabel xLabel;
    /** ySlider                 the slider for the y value */
    private JSlider ySlider;
    /** yCurrent                the label of the current y value */
    private JLabel yCurrent;
    /** yLabel                  the label of the y slider */
    private JLabel yLabel;
    /** sizeSlider              the slider for the size */
    private JSlider sizeSlider;
    /** sizeCurrent             the label of the current size */
    private JLabel sizeCurrent;
    /** sizeLabel               the label for the size slider */
    private JLabel sizeLabel;
    /** depthSlider             the slider for the depth */
    private JSlider depthSlider;
    /** depthCurrent            the label for the current depth */
    private JLabel depthCurrent;
    /** depthLabel              the label for the depth slider */
    private JLabel depthLabel;
    /** ratioLabel              the label for the ratio slider */
    private JLabel ratioLabel;
    /** ratioCurrent            the label for the current ratio value */
    private JLabel ratioCurrent;
    /** ratioSlider             the slider for the ratio */
    private JSlider ratioSlider;
    /** angleLabel              the label for the angle slider */
    private JLabel angleLabel;
    /** angleCurrent            the label for the current angle value */
    private JLabel angleCurrent;
    /** angleSlider             the slider for the angle */
    private JSlider angleSlider;
    /** rootLabel               the label for the root color */
    private JLabel rootLabel;
    /** rootFill                the button to set the root color fill */
    private JButton rootFill;
    /** rootOutline             the button to set the root color outline */
    private JButton rootOutline;
    /** leafLabel               the label for the leaf color */
    private JLabel leafLabel;
    /** leafFill                the button to set the leaf color fill */
    private JButton leafFill;
    /** leafOutline             the button to set the leaf color outline */
    private JButton leafOutline;
    /** useOutline              the checkbox to turn on and off outline */
    private JCheckBox useOutline;
    /** useGradient             the checkbox to turn on and off gradient */
    private JCheckBox useGradient;

    // window settings
    /** INIT_DIM                the initial dimension of the window */
    private static final Dimension INIT_DIM = new Dimension(180, 670);
    
    // slider settings 
    /** MAX_X                   the maximum x slider value */
    private static final int MAX_X = Toolkit.getDefaultToolkit().getScreenSize().width - INIT_DIM.width;
    /** INIT_X                  the default x slider value */
    private static final int INIT_X = MAX_X / 2;
    /** MAX_Y                   the maximum y slider value */
    private static final int MAX_Y = Toolkit.getDefaultToolkit().getScreenSize().height;
    /** INIT_Y                  the default y slider value */
    private static final int INIT_Y = MAX_Y * 2/ 3;
    /** MAX_SIZE                the maximum size of the root element */
    private static final int MAX_SIZE = INIT_DIM.width * 8;
    /** INIT_SIZE               the default size slider value */
    private static final int INIT_SIZE = MAX_SIZE / 8;
    /** MAX_DEPTH               the maximum depth slider value */
    private static final int MAX_DEPTH = 20;
    /** INIT_DEPTH              the default depth slider value */
    private static final int INIT_DEPTH = MAX_DEPTH / 4;
    /** MAX_RATIO               the maximum ratio slider value */
    private static final int MAX_RATIO = 99;
    /** INIT_RATIO              the default ratio slider value */
    private static final int INIT_RATIO = (MAX_RATIO / 2) + 1; 
    /** MAX_ANGLE               the maximum angle slider value */
    private static final int MAX_ANGLE = 180;
    /** INIT_ANGLE              the default angle slider value */
    private static final int INIT_ANGLE = MAX_ANGLE / 4;

    // color settings
    /** INIT_ROOT_FILL          the default root fill color */
    private static final Color INIT_ROOT_FILL = Color.green;
    /** INIT_ROOT_LINE          the default root outline color */
    private static final Color INIT_ROOT_LINE = new Color(0, 153, 0);
    /** INIT_LEAF_FILL          the default leaf fill color */
    private static final Color INIT_LEAF_FILL = Color.magenta;
    /** INIT_LEAF_LINE          the default leaf outline color */
    private static final Color INIT_LEAF_LINE = new Color(153, 0, 153);

    /**
     * create a new settings dialog for the given fractal
     * 
     * @param   fractal          the fractal to modify
     */
    public Settings(Fractal fractal) {
        if (fractal == null) {
            throw new IllegalArgumentException("Argument must not be null");
        }
        this.fractal = fractal;

        setLayout(null);

        // x settings
        xLabel = new JLabel("X", JLabel.CENTER);
        xLabel.setBounds(0, 10, 60, 20);
        xCurrent = new JLabel(String.valueOf(INIT_X), JLabel.CENTER);
        xCurrent.setBounds(0, 210, 60, 20);
        xSlider = new JSlider(JSlider.VERTICAL, 0, MAX_X, INIT_X);
        xSlider.setBounds(0, 30, 60, 180);
        xSlider.addChangeListener(new ChangeListener() {
                @Override
                public void stateChanged(ChangeEvent e) {
                    update();
                    xCurrent.setText(String.valueOf(xSlider.getValue()));
                }
            });
        add(xLabel);
        add(xSlider);
        add(xCurrent);
        
        // y settings
        yLabel = new JLabel("Y", JLabel.CENTER);
        yLabel.setBounds(60, 10, 60, 20);
        yCurrent = new JLabel(String.valueOf(INIT_Y), JLabel.CENTER);
        yCurrent.setBounds(60, 210, 60, 20);
        ySlider = new JSlider(JSlider.VERTICAL, 0, MAX_Y, INIT_Y);
        ySlider.setBounds(60, 30, 60, 180);
        ySlider.addChangeListener(new ChangeListener() {
                @Override
                public void stateChanged(ChangeEvent e) {
                    update();
                    yCurrent.setText(String.valueOf(ySlider.getValue()));
                }
            });
        add(yLabel);
        add(ySlider);
        add(yCurrent);

        // size settings
        sizeLabel = new JLabel("Size", JLabel.CENTER);
        sizeLabel.setBounds(120, 10, 60, 20);
        sizeCurrent = new JLabel(String.valueOf(INIT_SIZE), JLabel.CENTER);
        sizeCurrent.setBounds(120, 210, 60, 20);
        sizeSlider = new JSlider(JSlider.VERTICAL, 0, MAX_SIZE, INIT_SIZE);
        sizeSlider.setBounds(120, 30, 60, 180);
        sizeSlider.addChangeListener(new ChangeListener() {
                @Override
                public void stateChanged(ChangeEvent e) {
                    update();
                    sizeCurrent.setText(String.valueOf(sizeSlider.getValue()));
                }
            });
        add(sizeLabel);
        add(sizeSlider);
        add(sizeCurrent);
        
        // depth settings
        depthLabel = new JLabel("Depth", JLabel.CENTER);
        depthLabel.setBounds(0, 240, 60, 20);
        depthCurrent = new JLabel(String.valueOf(INIT_DEPTH), JLabel.CENTER);
        depthCurrent.setBounds(0, 440, 60, 20);
        depthSlider = new JSlider(JSlider.VERTICAL, 0, MAX_DEPTH, INIT_DEPTH);
        depthSlider.setBounds(0, 260, 60, 180);
        depthSlider.addChangeListener(new ChangeListener() {
                @Override
                public void stateChanged(ChangeEvent e) {
                    update();
                    depthCurrent.setText(String.valueOf(depthSlider.getValue()));
                }
            });
        add(depthLabel);
        add(depthCurrent);
        add(depthSlider);

        // ratio settings
        ratioLabel = new JLabel("Ratio", JLabel.CENTER);
        ratioLabel.setBounds(60, 240, 60, 20);
        ratioCurrent = new JLabel("0." + String.valueOf(INIT_RATIO), JLabel.CENTER);
        ratioCurrent.setBounds(60, 440, 60, 20);
        ratioSlider = new JSlider(JSlider.VERTICAL, 0, MAX_RATIO, INIT_RATIO);
        ratioSlider.setBounds(60, 260, 60, 180);
        ratioSlider.addChangeListener(new ChangeListener() {
                @Override
                public void stateChanged(ChangeEvent e) {
                    update();
                    ratioCurrent.setText("0." + String.valueOf(ratioSlider.getValue()));
                }
            });
        add(ratioLabel);
        add(ratioSlider);
        add(ratioCurrent);

        // angle settings
        angleLabel = new JLabel("Angle", JLabel.CENTER);
        angleLabel.setBounds(120, 240, 60, 20);
        angleCurrent = new JLabel(String.valueOf(INIT_ANGLE), JLabel.CENTER);
        angleCurrent.setBounds(120, 440, 60, 20);
        angleSlider = new JSlider(JSlider.VERTICAL, 0, MAX_ANGLE, INIT_ANGLE);
        angleSlider.setBounds(120, 260, 60, 180);
        angleSlider.addChangeListener(new ChangeListener() {
                @Override
                public void stateChanged(ChangeEvent e) {
                    update();
                    angleCurrent.setText(String.valueOf(angleSlider.getValue()));
                }
            });
        add(angleLabel);
        add(angleSlider);
        add(angleCurrent);

        // root color settings
        rootLabel = new JLabel("Color 1", JLabel.CENTER);
        rootLabel.setBounds(0, 470, 80, 20);
        rootFill = new JButton("");
        rootFill.setBounds(5, 500, 80, 60);
        rootFill.setBackground(INIT_ROOT_FILL);
        rootFill.setOpaque(true);
        rootFill.setBorderPainted(false);
        rootFill.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    Color c = JColorChooser.showDialog(null, "Choose a Color",
                            rootFill.getForeground());
                    if (c != null) {
                        rootFill.setBackground(c);
                    }
                    update();
                }
            });
        rootOutline = new JButton("");
        rootOutline.setBounds(5, 560, 80, 20);
        rootOutline.setBackground(INIT_ROOT_LINE);
        rootOutline.setOpaque(true);
        rootOutline.setBorderPainted(false);
        rootOutline.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    Color c = JColorChooser.showDialog(null, "Choose a Color",
                            rootOutline.getForeground());
                    if (c != null) {
                        rootOutline.setBackground(c);
                    } 
                    update();
                }
            });
        add(rootLabel);
        add(rootFill);
        add(rootOutline);

        // leaf color settings
        leafLabel = new JLabel("Color 2", JLabel.CENTER);
        leafLabel.setBounds(80, 470, 80, 20);
        leafFill = new JButton("");
        leafFill.setBounds(95, 500, 80, 60);;
        leafFill.setBackground(INIT_LEAF_FILL);
        leafFill.setOpaque(true);
        leafFill.setBorderPainted(false);
        leafFill.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    Color c = JColorChooser.showDialog(null, "Choose a Color",
                            leafFill.getForeground());
                    if (c != null) {
                        leafFill.setBackground(c);
                    }
                    update();
                }
            });
        leafOutline = new JButton("");
        leafOutline.setBounds(95, 560, 80, 20);
        leafOutline.setBackground(INIT_LEAF_LINE);
        leafOutline.setOpaque(true);
        leafOutline.setBorderPainted(false);
        leafOutline.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    Color c = JColorChooser.showDialog(null, "Choose a Color",
                            leafOutline.getForeground());
                    if (c != null) {
                        leafOutline.setBackground(c);
                    } 
                    update();
                }
            });
        add(leafLabel);
        add(leafFill);
        add(leafOutline);

        // Checkbox settings
        useOutline = new JCheckBox("Outline");
        useOutline.setBounds(5, 585, 100, 25);
        useOutline.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                update();
            }
        });
        useGradient = new JCheckBox("Gradient");
        useGradient.setBounds(5, 610, 100, 25);
        useGradient.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                update();
            }
        });
        add(useOutline);
        add(useGradient); 
        
        // create a settings jframe and add the settings panel to it
        JFrame sf = new JFrame("Settings");
        sf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        sf.getContentPane().add(this);
        sf.pack();
        sf.setVisible(true);
        sf.setResizable(false);
    }

    /**
     * update the fractal with the current settings values
     */
    private void update() {
        fractal.setData(xSlider.getValue(),
                        ySlider.getValue(),
                        sizeSlider.getValue(),
                        depthSlider.getValue(),
                        ratioSlider.getValue(), 
                        angleSlider.getValue(),
                        rootFill.getBackground(),
                        leafFill.getBackground(),
                        rootOutline.getBackground(),
                        leafOutline.getBackground(),
                        useOutline.isSelected(),
                        useGradient.isSelected());
    } 
    
    /**
     * get the preferred window dimensions
     * 
     * @return              the prefered window dimensions
     */
    @Override
    public Dimension getPreferredSize() {
        return INIT_DIM;
    }
}
