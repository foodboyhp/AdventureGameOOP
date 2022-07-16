package object;

import org.w3c.dom.css.CSSValueList;

import entity.Entity;
import main.GamePanel;

public class OBJ_Potion_Red extends Entity {
	GamePanel gp;
	

	public OBJ_Potion_Red(GamePanel gp) {
		super(gp);
		this.gp = gp;
		
		type = type_consumable;
		name = "Red Potion";
		stackable = true;
		value = 5;
		down1 = setup("/objects/potion_red", gp.tileSize, gp.tileSize);
		description = "[" + name + "]";
		
	}
	public boolean use(Entity entity) {
		gp.gameState = gp.dialogueState;
		gp.ui.currentDialogue = "You drank life potion";
		entity.life+= value;
		if(entity.life>=entity.maxLife) {
			entity.life= entity.maxLife;
		}
		gp.playSE(2);
		return true;
	}
}
