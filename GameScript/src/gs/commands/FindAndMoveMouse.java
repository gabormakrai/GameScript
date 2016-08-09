package gs.commands;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import javax.imageio.ImageIO;

import gs.log.Logger;
import gs.main.Command;
import gs.robot.RobotService;
import gs.robot.ScreenCaptureService;

public class FindAndMoveMouse implements Command {
	
	final String screenFile;
	final String targetFile;
	
	final int moveX;
	final int moveY;
	final int x0;
	final int y0;
	final int x1;
	final int y1;
	
	final int retries;
	final int retrySleep;

	public FindAndMoveMouse(String screenFile, String targetFile, int moveX, int moveY, int x0, int y0, int x1, int y1, int retries, int retrySleep) {
		this.screenFile = screenFile;
		this.targetFile = targetFile;
		this.moveX = moveX;
		this.moveY = moveY;
		this.x0 = x0;
		this.y0 = y0;
		this.x1 = x1;
		this.y1 = y1;
		this.retries = retries;
		this.retrySleep = retrySleep;
	}
	
	@Override
	public void run(HashMap<String, String> variables, Logger logger) {
		
		for (int iteration = 1; iteration <= retries; ++iteration) {
			
			logger.log("FindAndMoveMouse", "Iteration: " + iteration + "/" + retries);
			
			logger.log("FindAndMoveMouse", "Capture screen to " + screenFile);
			
			if (!ScreenCaptureService.capture(screenFile)) {
				continue;
			}
			
			logger.log("FindAndMoveMouse", "Load image " + targetFile + "...");
			
			BufferedImage target = null;
			try {
				target = ImageIO.read(new File(targetFile));
			} catch (IOException e) {
				throw new RuntimeException("Problem reading the image", e);
			}
			BufferedImage target2 = target; //convert(target);
			
			logger.log("FindAndMoveMouse", "Load image " + screenFile + "...");
			
			BufferedImage screen = null;
			try {
				screen = ImageIO.read(new File(screenFile));
			} catch (IOException e) {
				throw new RuntimeException("Problem reading the image", e);
			}
			BufferedImage screen2 = screen; // convert(screen);
			
			boolean targetFound = false;
								
			for (int x = x0; x < x1; ++x) {
				for (int y = y0; y < y1; ++y) {
					
					int match = 0;
					
					for (int tx = 0; tx < target.getWidth(); ++tx) {
						for (int ty = 0; ty < target.getHeight(); ++ty) {
					
							int screenRGB = screen2.getRGB(x + tx, y + ty);
							Color screenColor = new Color(screenRGB);
							
							int targetRGB = target2.getRGB(tx, ty);
							Color targetColor = new Color(targetRGB);
							
							int diff = 0;
							diff += Math.abs(screenColor.getRed() - targetColor.getRed());
							diff += Math.abs(screenColor.getGreen() - targetColor.getGreen());
							diff += Math.abs(screenColor.getBlue() - targetColor.getBlue());

							if (diff < 20) {
								++match;
							}
						}
					}
					
					if (match > 0.9 * target.getWidth() * target.getHeight()) {
						
						logger.log("FindAndMoveMouse", "Target found at (" + x + "," + y + ")");
						
						logger.log("FindAndMoveMouse", "Move mouse to (" + (x+moveX) + "," + (y+moveY) + ")");
						
						RobotService.getRobot().mouseMove(x + moveX, y + moveY);
						
						targetFound = true;
						
						break;
					}
				}
				if (targetFound) {
					break;
				}
			}
			
			if (!targetFound) {
				
				logger.log("FindAndMoveMouse", "Target has not been found :( Sleep for " + retrySleep + "ms...");
				
				try {
					Thread.sleep(retrySleep);
				} catch (InterruptedException e) {
					// do nothing
				}
				
			} else {
				break;
			}
			
		}
		
	}		
}
