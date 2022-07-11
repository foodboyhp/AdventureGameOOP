package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_SHIELD_1 extends Entity {
	public OBJ_SHIELD_1(GamePanel gp) {
		super(gp);
		name = "Shield 1";
		down1 = setup("/objects/shield_1", gp.tileSize, gp.tileSize);
		defenseValue = 1;
	}
}
