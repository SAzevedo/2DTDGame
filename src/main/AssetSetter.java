package main;

import object.BootsGear;
import object.ChestObject;
import object.DoorObject;
import object.KeyObject;

public class AssetSetter {
	
	GamePanel gp;
	
	public AssetSetter(GamePanel gp) {
		this.gp = gp;
	}
	
	public void setObject() {
		gp.obj[0] = new KeyObject();
		gp.obj[0].worldX = 24 * gp.tileSize;
		gp.obj[0].worldY = 9 * gp.tileSize;
		
		gp.obj[1] = new KeyObject();
		gp.obj[1].worldX = 23 * gp.tileSize;
		gp.obj[1].worldY = 9 * gp.tileSize;
		
		gp.obj[2] = new KeyObject();
		gp.obj[2].worldX = 9 * gp.tileSize;
		gp.obj[2].worldY = 4 * gp.tileSize;
		
		gp.obj[3] = new DoorObject();
		gp.obj[3].worldX = 24 * gp.tileSize;
		gp.obj[3].worldY = 7 * gp.tileSize;
		
		gp.obj[4] = new DoorObject();
		gp.obj[4].worldX = 23 * gp.tileSize;
		gp.obj[4].worldY = 7 * gp.tileSize;
		
		gp.obj[5] = new DoorObject();
		gp.obj[5].worldX = 23 * gp.tileSize;
		gp.obj[5].worldY = 4 * gp.tileSize;
		
		gp.obj[6] = new ChestObject();
		gp.obj[6].worldX = 23 * gp.tileSize;
		gp.obj[6].worldY = 2 * gp.tileSize;
		
		gp.obj[7] = new BootsGear();
		gp.obj[7].worldX = 23 * gp.tileSize;
		gp.obj[7].worldY = 3 * gp.tileSize;
	}

}
