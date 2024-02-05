import com.github.sarxos.webcam.Webcam;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class mainClass {

    public static void main(String[] args)
    {
        Webcam webcam = Webcam.getDefault();
        webcam.open();

    }


}
