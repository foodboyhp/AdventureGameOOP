package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Shield_Wood extends Entity {
	public OBJ_Shield_Wood(GamePanel gp) {
		super(gp);
		
		type = type_shield;
		name = "Shield 1";
		down1 = setup("/objects/shield_1", gp.tileSize, gp.tileSize);
		description = "[" + name + "]";
		defenseValue = 1;
	}
}
