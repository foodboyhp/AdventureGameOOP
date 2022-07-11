package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_MANA extends Entity {
	GamePanel gp;
	public OBJ_MANA(GamePanel gp) {
		super(gp);
		this.gp = gp;
		name = "Mana";
		image = setup("/objects/manacrystal_full", gp.tileSize, gp.tileSize);
		image2 = setup("/objects/manacrystal_blank", gp.tileSize, gp.tileSize);
	}
}
