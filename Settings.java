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
    private JSlider depthSlider;
    private JLabel depthLabel;
    private JLabel ratioLabel;
    private JSlider ratioSlider;
    private JLabel angleLabel;
    private JSlider angleSlider;
    private JLabel currentDepth;
    private JLabel currentRatio;
    private JLabel currentAngle;
    private JButton rootFill;
    private JButton rootOutline;
    private JButton leafFill;
    private JButton leafOutline;
    private JCheckBox useOutline;
    private JCheckBox useGradient;

    // slider settings 
    private int MAX_DEPTH = 40;
    private int INIT_DEPTH = MAX_DEPTH / 2;
    private int MAX_RATIO = 100;
    private int INIT_RATIO = MAX_RATIO / 2; 
    private int MAX_ANGLE = 90;
    private int INIT_ANGLE = MAX_ANGLE / 2;

    // color settings
    private Color INIT_ROOT_FILL = Color.white;
    private Color INIT_ROOT_LINE = Color.black;
    private Color INIT_LEAF_FILL = Color.black;
    private Color INIT_LEAF_LINE = Color.white;

    public Settings() {
        setPreferredSize(new Dimension(180, 390));
        setLayout(null);

        // depth settings
        depthLabel = new JLabel("Depth", JLabel.CENTER);
        depthLabel.setBounds(0, 10, 60, 20);
        currentDepth = new JLabel(String.valueOf(INIT_DEPTH), JLabel.CENTER);
        currentDepth.setBounds(0, 210, 60, 20);
        depthSlider = new JSlider(JSlider.VERTICAL, 0, MAX_DEPTH, MAX_DEPTH / 2);
        depthSlider.setBounds(0, 30, 60, 180);
        depthSlider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                currentDepth.setText(String.valueOf(depthSlider.getValue()));
            }
        });
        add(depthLabel);
        add(currentDepth);
        add(depthSlider);

        // ratio settings
        ratioLabel = new JLabel("Ratio", JLabel.CENTER);
        ratioLabel.setBounds(60, 10, 60, 20);
        currentRatio = new JLabel(String.valueOf(INIT_RATIO), JLabel.CENTER);
        currentRatio.setBounds(60, 210, 60, 20);
        ratioSlider = new JSlider(JSlider.VERTICAL, 0, MAX_RATIO, MAX_RATIO / 2);
        ratioSlider.setBounds(60, 30, 60, 180);
        ratioSlider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                currentRatio.setText(String.valueOf(ratioSlider.getValue()));
            }
        });
        add(ratioLabel);
        add(ratioSlider);
        add(currentRatio);

        // angle settings
        angleLabel = new JLabel("Angle", JLabel.CENTER);
        angleLabel.setBounds(120, 10, 60, 20);
        currentAngle = new JLabel(String.valueOf(INIT_ANGLE), JLabel.CENTER);
        currentAngle.setBounds(120, 210, 60, 20);
        angleSlider = new JSlider(JSlider.VERTICAL, 0, MAX_ANGLE, MAX_ANGLE / 2);
        angleSlider.setBounds(120, 30, 60, 180);
        angleSlider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                currentAngle.setText(String.valueOf(angleSlider.getValue()));
            }
        });
        add(angleLabel);
        add(angleSlider);
        add(currentAngle);

        // root color settings
        rootFill = new JButton("");
        rootFill.setBounds(5, 240, 80, 60);
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
            }
        });
        rootOutline = new JButton("");
        rootOutline.setBounds(5, 300, 80, 20);
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
        add(rootFill);
        add(rootOutline);

        // leaf color settings
        leafFill = new JButton("");
        leafFill.setBounds(95, 240, 80, 60);;
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
        leafOutline.setBounds(95, 300, 80, 20);
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
        add(leafFill);
        add(leafOutline);

        // Checkbox settings
        useOutline = new JCheckBox("Outline");
        useOutline.setBounds(5, 325, 100, 25);
        useGradient = new JCheckBox("Gradient");
        useGradient.setBounds(5, 355, 100, 25);
        add(useOutline);
        add(useGradient);        
    }

    public static void main (String[] args) {
        JFrame frame = new JFrame("Settings");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(new Settings());
        frame.pack();
        frame.setVisible (true);
        frame.setResizable(false);
    }
}
