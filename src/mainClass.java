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
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Array;
import java.util.ArrayList;

public class mainClass {

    static Webcam webcam;
    static JComboBox<Dimension> camRes;
    static WebcamPanel webcamPanel;
    static JPanel mainPanel;
    static ArrayList<BufferedImage> picsTaken = new ArrayList<>();

    public static void main(String[] args)
    {
        camRes = new JComboBox<>();                             //refactor this, this shouldnt be in main
        camRes.addItem(new Dimension(640,480));
        camRes.addItem(new Dimension(176,144));
        camRes.addItem(new Dimension(320,240));
        initializeWebcam();
        displayGUI();

    }

    private static void initializeWebcam()
    {
        try {
        webcam = Webcam.getDefault();
        if (webcam != null) {
            Dimension selectedRes = (Dimension) camRes.getSelectedItem();
            webcam.setViewSize(selectedRes);
            openWebcam();
        }
        else
            {
                System.out.println("No webcam found");
            }
        }
    catch (Exception e)
    {
            e.printStackTrace();    //change this, this is horrible
    }
        Dimension[] nonStandardResolutions = new Dimension[] { //refactor this, this is horrible
                WebcamResolution.PAL.getSize(),
                WebcamResolution.HD.getSize(),
                new Dimension(2000, 1000),
                new Dimension(1000, 500),
        };
        webcam.setCustomViewSizes(nonStandardResolutions);
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
            webcamPanel = new WebcamPanel(webcam);
            webcamPanel.setFPSDisplayed(true);
            webcamPanel.setDisplayDebugInfo(true);
            webcamPanel.setImageSizeDisplayed(true);
            webcamPanel.setMirrored(true);
        // this combobox is used to select the user resolution
        JPanel resPanel = new JPanel();
        camRes.addItem(new Dimension(2000,1000));
        camRes.addItem(new Dimension(1152,648));
        camRes.addItem(new Dimension(1280,720));
        camRes.addItem(new Dimension(1366,768));
        camRes.addItem(new Dimension(1600,900));
        camRes.addItem(new Dimension(1920,1080));
        resPanel.add(new JLabel("Select Resolution:"));
        resPanel.add(camRes);

        JPanel dirPanel = createPanel("DIRECTORY", "", Color.DARK_GRAY);
        JPanel settPanel = createPanel("Section 2", "CAMERA SETTINGS", Color.DARK_GRAY);
        mainPanel = createPanel("","",Color.LIGHT_GRAY);
        mainPanel.add(webcamPanel);
        mainPanel.add(resPanel);

        camRes.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setResolution();
            }
        });

        //SWITCH THIS TO webcamPanel using more types from the GitHub library

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



    private static void isActive(Webcam cam, JFrame windowFrame) {      // this method is redundant?
        if (webcam == null) {
            JOptionPane.showMessageDialog(windowFrame, "WEBCAM NOT FOUND");
        }
    }

    private static void setResolution() {
        try {
            closeWebcam();
            Dimension selectedRes = (Dimension) camRes.getSelectedItem();
            webcam.setViewSize(selectedRes);
            System.out.println("Webcam resolution updated: " + selectedRes.width + "x" + selectedRes.height);
            openWebcam();

            updateWebcamPanel(mainPanel);
            System.out.println("panel updated");
        } catch (Exception e) { // CHANGE THIS, THIS IS HORRIBLE
            e.printStackTrace();
        }
    }

    private static void closeWebcam() {
        if (webcam != null && webcam.isOpen()) {
            webcam.close();
            System.out.println("webcam closed");
        }
    }

    private static void openWebcam() {
        if (webcam != null) {
            webcam.open();
            System.out.println("webcam opened"); // for debug purposes
        } else {
            System.out.println("No webcam found");
        }
    }
    private static void updateWebcamPanel(JPanel mainPanel) {
        // Remove the existing webcamPanel from the mainPanel
        mainPanel.remove(webcamPanel);
        // Create a new webcamPanel with the updated webcam resolution
        webcamPanel = new WebcamPanel(webcam);
        webcamPanel.setFPSDisplayed(true);
        webcamPanel.setDisplayDebugInfo(true);
        webcamPanel.setImageSizeDisplayed(true);
        webcamPanel.setMirrored(true);
        // Add the new webcamPanel to the mainPanel
        mainPanel.add(webcamPanel);
        // Repaint the mainPanel to reflect the changes
        mainPanel.revalidate();
        mainPanel.repaint();
    }

    private static void takePhoto() throws IOException {
        BufferedImage currentPic = webcam.getImage();
        picsTaken.add(currentPic);
        ImageIO.write(currentPic, "jpg", new File("C:\\Users\\fireh\\Desktop\\webcamTest")); //hard coded path for now, will add file selection
    }






}