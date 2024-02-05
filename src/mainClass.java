import com.github.sarxos.webcam.Webcam;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;

public class mainClass {

    public static void main(String[] args)
    {
        displayGUI();
        initializeWebcam();



    }

    private static void initializeWebcam()
    {
        Webcam webcam = Webcam.getDefault();
        if (webcam == null)
        {
            System.out.println("no webcam found");
        }
    }

    private static void displayGUI()
    {
        JFrame windowFrame = new JFrame("OPEN-MOTION");
        windowFrame.setSize(1920, 1440);
        windowFrame.setVisible(true);
        JLabel directoryLabel = new JLabel();
        JLabel cameraSettingsLabel = new JLabel();
        directoryLabel.setText("DIRECTORY");
        cameraSettingsLabel.setText("CAMERA SETTINGS");
       // directoryLabel.setHorizontalAlignment(-1);
       // cameraSettingsLabel.setHorizontalAlignment(1);
        windowFrame.add(directoryLabel);
        windowFrame.add(cameraSettingsLabel);

    }

}