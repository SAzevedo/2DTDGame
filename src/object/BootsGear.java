package object;

import java.io.IOException;

import javax.imageio.ImageIO;

public class BootsGear extends SuperObject {
	
	public BootsGear() {
		name = "Boots";
		try {
			image = ImageIO.read(getClass().getResourceAsStream("/objects/Boots.png"));
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
	
}
