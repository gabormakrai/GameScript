package gs.robot;

import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class ScreenCaptureService {
	
	public static boolean capture(String fileName) {
		
		String osName = System.getProperty("os.name");
		
		if (osName.toLowerCase().contains("windows")) {
			Rectangle screenRect = new Rectangle(Toolkit.getDefaultToolkit().getScreenSize());
			BufferedImage img = RobotService.getRobot().createScreenCapture(screenRect);
			try {
				ImageIO.write(img, "png", new File(fileName));
				return true;
			} catch (IOException e) {
				return false;
			}
		} else {
			return executeCommand("import -window root " + fileName); 
		}
	}
	
	private static boolean executeCommand(String command) {
		
		try {
			
			Process p = Runtime.getRuntime().exec(command);
			
			p.waitFor();
			
		} catch (Exception e) {
			
			return false;
			
		}

		return true;

	}	
	
}
