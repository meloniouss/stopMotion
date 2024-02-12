import com.github.sarxos.webcam.Webcam;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import com.github.sarxos.webcam.WebcamPanel;
import com.github.sarxos.webcam.WebcamResolution;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
public class mainClass {

    static Webcam webcam;

    public static void main(String[] args)
    {
        initializeWebcam();
        displayGUI();
        displayWebcamFeed();
    }

    private static void initializeWebcam()
    {
        try {
            webcam = Webcam.getDefault();
            if (webcam != null) {
                webcam.setViewSize(WebcamResolution.VGA.getSize());
            } else {
                System.out.println("No webcam found");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void displayGUI()
    {
        //initializing the actual window
        JFrame windowFrame = new JFrame("OPEN-MOTION");
        windowFrame.setSize(1920, 1440);
        windowFrame.setLocationRelativeTo(null);
        windowFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //simply adding labels and stuff here
        JLabel directoryLabel = new JLabel("DIRECTORY");
        JLabel cameraSettingsLabel = new JLabel("CAMERA SETTINGS");
        directoryLabel.setHorizontalAlignment(0);
        cameraSettingsLabel.setHorizontalAlignment(4);

        //webcam panel stuff
            WebcamPanel webcamPanel = new WebcamPanel(webcam);
            webcamPanel.setFPSDisplayed(true);
            webcamPanel.setDisplayDebugInfo(true);
            webcamPanel.setImageSizeDisplayed(true);
            webcamPanel.setMirrored(true);




        JPanel dirPanel = createPanel("DIRECTORY", "", Color.BLUE);

        JPanel settPanel = createPanel("Section 2", "CAMERA SETTINGS", Color.RED);

        JPanel mainPanel = createPanel("","",Color.DARK_GRAY);
        mainPanel.add(webcamPanel);


        //SWITCH THIS TO webcamPanel using more types from the github library

        JPanel footer = createPanel("Footer", "Footer Settings", Color.GREEN);

        dirPanel.setMinimumSize(new Dimension(100,300));
        settPanel.setMinimumSize(new Dimension(100,300));

        JSplitPane rightSplitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, mainPanel, settPanel);
        rightSplitPane.setResizeWeight(0.3);

        JSplitPane mainSplitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, dirPanel, rightSplitPane);
        mainSplitPane.setResizeWeight(0.3);




        JSplitPane mainWithFooterSplitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT, mainSplitPane, footer);
        mainWithFooterSplitPane.setResizeWeight(0.9);




        windowFrame.add(mainWithFooterSplitPane);
        windowFrame.setVisible(true);

        isActive(webcam, windowFrame);


        windowFrame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                if (webcam != null && webcam.isOpen()) {
                    webcam.close();
                }
            }
        });

    }


    private static JPanel createPanel(String label1, String label2, Color backgroundColor) {    //eventually the arguments will be removed (maybe not the colour)
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel,BoxLayout.PAGE_AXIS));
        panel.setBackground(backgroundColor);

        JLabel label1Component = new JLabel(label1);    //these two can be overriden eventually probably
        label1Component.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel label2Component = new JLabel(label2);
        label2Component.setAlignmentX(Component.CENTER_ALIGNMENT);

        panel.add(label1Component);
        panel.add(label2Component);

        return panel;
    }

    private static void displayWebcamFeed() {   // unsure if this method will be necessary once we add more packages from the github
        Timer timer = new Timer(100, e -> {
            BufferedImage image = webcam.getImage();
            ImageIcon icon = new ImageIcon(image.getScaledInstance(640, 480, Image.SCALE_SMOOTH));

        });
        timer.start();
    }

    private static void isActive(Webcam cam, JFrame windowFrame)
    {
        if (webcam == null)
        {
            JOptionPane.showMessageDialog(windowFrame, "WEBCAM NOT FOUND");
        }

    }

    //add a method which takes the previous image taken from an arraylist of captured images, and displays it on top of the live-view with a lower alpha value (lower opacity)

}