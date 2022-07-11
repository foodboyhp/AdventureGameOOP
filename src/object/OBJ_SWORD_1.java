package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_SWORD_1 extends Entity {
	public OBJ_SWORD_1(GamePanel gp) {
		super(gp);
		
		name = "Sword 1";
		down1 = setup("/objects/sword_1", gp.tileSize, gp.tileSize);
		attackValue = 4;
	}
}
