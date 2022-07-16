package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Sword_normal extends Entity {
	public OBJ_Sword_normal(GamePanel gp) {
		super(gp);
		
		type = type_sword;
		name = "Sword 1";
		down1 = setup("/objects/sword_1", gp.tileSize, gp.tileSize);
		description = "[" + name + "]";
		attackValue = 4;
		attackArea.width = 36;
        attackArea.height = 36;
	}
}
