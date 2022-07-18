package entity;

import java.awt.image.BufferedImage;
import java.util.Random;

import javax.imageio.ImageIO;

import gameSetup.UtilityTool;
import main.GamePanel;

public class NPC_Guide extends Entity {

	public NPC_Guide(GamePanel gp) {
		super(gp);
		direction = "down";
		speed = 1;
		type = type_npc;
		getImage();
		setDialogue();
	}
	public void getImage(){
        up1 = setup("/NPC/oldman_up_1", gp.tileSize, gp.tileSize);
        up2 = setup("/NPC/oldman_up_2", gp.tileSize, gp.tileSize);
        down1 = setup("/NPC/oldman_down_1", gp.tileSize, gp.tileSize);
        down2 = setup("/NPC/oldman_down_2", gp.tileSize, gp.tileSize);
        left1 = setup("/NPC/oldman_left_1", gp.tileSize, gp.tileSize);
        left2 = setup("/NPC/oldman_left_2", gp.tileSize, gp.tileSize);
        right1 = setup("/NPC/oldman_right_1", gp.tileSize, gp.tileSize);
        right2 = setup("/NPC/oldman_right_2", gp.tileSize, gp.tileSize);
    }
	
	public void setDialogue() {
		
		dialouges[0] = "Hello, welcome to God's challenge.\n"
				+ "If you need anything, I'm here to help.";
		dialouges[1] = "Collect the items needed to fight monster";
		dialouges[2] = "There're 3 stages you need to\n"+ 
		"complete before you fight the BOSS";
		dialouges[3] = "GOOD LUCK!!!";
		
	}
	
	public void setAction() {
		
		if(onPath==true) {
//			int goalCol = (gp.player.worldX+gp.player.solidArea.x)/gp.tileSize;
//			int goalRow = (gp.player.worldY+gp.player.solidArea.y)/gp.tileSize;
//			searchPath(goalCol,goalRow);
		}
		else {
			actionLockCounter++;
			
			if(actionLockCounter==120) {
				Random random = new Random();
				int i = random.nextInt(100)+1;
				
				if(i<=25) {
					direction = "up";
				}
				if(i>25&&i<=50) {
					direction = "down";
				}
				if(i>50&&i<=75) {
					direction = "left";
				}
				if(i>75 && i<=100) {
					direction = "right";
				}
				
				actionLockCounter = 0;
			}
		}
		
		
		
	}
	public void speak() {
		super.speak();
		//onPath = true;
	}
    
}
