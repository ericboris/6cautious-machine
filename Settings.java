import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

/**
 * a settings panel
 *
 * @author Eric Boris
 * @version 11/18/2018
 */
public class Settings extends JPanel {
    private Fractal fractal;
    
    private JFrame sf;
    private JPanel sp;

    private JSlider xSlider;
    private JLabel xCurrent;
    private JLabel xLabel;
    private JSlider ySlider;
    private JLabel yCurrent;
    private JLabel yLabel;
    private JSlider sizeSlider;
    private JLabel sizeCurrent;
    private JLabel sizeLabel;
    private JSlider depthSlider;
    private JLabel depthCurrent;
    private JLabel depthLabel;
    private JLabel ratioLabel;
    private JLabel ratioCurrent;
    private JSlider ratioSlider;
    private JLabel angleLabel;
    private JLabel angleCurrent;
    private JSlider angleSlider;
    private JLabel rootLabel;
    private JButton rootFill;
    private JButton rootOutline;
    private JLabel leafLabel;
    private JButton leafFill;
    private JButton leafOutline;
    private JCheckBox useOutline;
    private JCheckBox useGradient;

    // window settings
    private Dimension INIT_DIM = new Dimension(180, 670);
    
    // slider settings 
    private int MAX_X = Toolkit.getDefaultToolkit().getScreenSize().width - INIT_DIM.width;
    private int INIT_X = MAX_X / 2;
    private int MAX_Y = Toolkit.getDefaultToolkit().getScreenSize().height;
    private int INIT_Y = MAX_Y / 2;
    private int MAX_SIZE = INIT_DIM.width * 4;
    private int INIT_SIZE = MAX_SIZE / 4;
    private int MAX_DEPTH = 20;
    private int INIT_DEPTH = MAX_DEPTH / 2;
    private int MAX_RATIO = 99;
    private int INIT_RATIO = MAX_RATIO / 2; 
    private int MAX_ANGLE = 180;
    private int INIT_ANGLE = MAX_ANGLE / 4;

    // color settings
    private Color INIT_ROOT_FILL = Color.white;
    private Color INIT_ROOT_LINE = Color.black;
    private Color INIT_LEAF_FILL = Color.black;
    private Color INIT_LEAF_LINE = Color.white;

    public Settings(Fractal fractal) {
        if (fractal == null) {
            throw new IllegalArgumentException("Argument must not be null");
        }
        this.fractal = fractal;
        
        // create a settings jpanel
        //sp = new JPanel();
        //setPreferredSize(INIT_DIM);
        //setLayout(new FlowLayout());
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
        yCurrent = new JLabel("0." + String.valueOf(INIT_Y), JLabel.CENTER);
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
                }
            });
        add(leafLabel);
        add(leafFill);
        add(leafOutline);

        // Checkbox settings
        useOutline = new JCheckBox("Outline");
        useOutline.setBounds(5, 585, 100, 25);
        useGradient = new JCheckBox("Gradient");
        useGradient.setBounds(5, 610, 100, 25);
        add(useOutline);
        add(useGradient); 
        
        // create a settings jframe and add the settings panel to it
        sf = new JFrame("Settings");
        sf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        sf.getContentPane().add(this);
        sf.pack();
        sf.setVisible(true);
        sf.setResizable(false);
    }

    private void update() {
        fractal.setData(xSlider.getValue(),
                        ySlider.getValue(),
                        sizeSlider.getValue(),
                        depthSlider.getValue(),
                        ratioSlider.getValue(), 
                        angleSlider.getValue(),
                        rootFill.getBackground(),
                        leafFill.getBackground());
    } 
    
    @Override
    public Dimension getPreferredSize() {
        return INIT_DIM;
    }
}
