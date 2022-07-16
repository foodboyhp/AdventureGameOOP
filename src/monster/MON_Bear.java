package monster;

import java.util.Random;

import entity.Entity;
import main.GamePanel;

public class MON_Bear extends Entity{
    GamePanel gp;
	public MON_Bear(GamePanel gp) {
		super(gp);
		this.gp = gp;
		
		type = 2;
		name = "Snake";
		speed = 2;
		maxLife = 20;
		life = maxLife;
		
		solidArea.x = 3;
		solidArea.y = 18;
		solidArea.width = 42;
		solidArea.height = 30;
		solidAreaDefaultX = solidArea.x;
		solidAreaDefaultY = solidArea.y;
		
		getImage();
	}
    public void getImage() {
		down1 = setup("/monster/Bear/BEARDOWN1", gp.tileSize, gp.tileSize);
		down2 = setup("/monster/Bear/BEARDOWN2", gp.tileSize, gp.tileSize);
		up1 = setup("/monster/Bear/BEARUP1", gp.tileSize, gp.tileSize);
		up2 = setup("/monster/Bear/BEARUP2", gp.tileSize, gp.tileSize);
		left1 = setup("/monster/Bear/BEARLEFT1", gp.tileSize, gp.tileSize);
		left2 = setup("/monster/Bear/BEARLEFT2", gp.tileSize, gp.tileSize);
		right1 = setup("/monster/Bear/BEARRIGHT1", gp.tileSize, gp.tileSize);
		right2 = setup("/monster/Bear/BEARRIGHT2", gp.tileSize, gp.tileSize);
	}
    public void setAction() {
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
    public void damageReact() {
		actionLockCounter = 0;
		direction = gp.player.direction;
	}
}