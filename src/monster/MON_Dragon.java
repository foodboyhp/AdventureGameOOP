package monster;

import java.util.Random;

import entity.Entity;
import main.GamePanel;
import object.OBJ_DRAGONFIRE;

public class MON_Dragon extends Entity{
    GamePanel gp;
	public MON_Dragon(GamePanel gp) {
		super(gp);
		this.gp = gp;
		
		type = 2;
		name = "Dragon";
		speed = 1;
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
		down1 = setup("/monster/Dragon/DRAGON_DOWN1", gp.tileSize, gp.tileSize);
		down2 = setup("/monster/Dragon/DRAGON_DOWN2", gp.tileSize, gp.tileSize);
		down3 = setup("/monster/Dragon/DRAGON_DOWN1", gp.tileSize, gp.tileSize);
		down4 = setup("/monster/Dragon/DRAGON_DOWN2", gp.tileSize, gp.tileSize);
		up1 = setup("/monster/Dragon/DRAGON_UP1", gp.tileSize, gp.tileSize);
		up2 = setup("/monster/Dragon/DRAGON_UP2", gp.tileSize, gp.tileSize);
		up3 = setup("/monster/Dragon/DRAGON_UP1", gp.tileSize, gp.tileSize);
		up4 = setup("/monster/Dragon/DRAGON_UP2", gp.tileSize, gp.tileSize);
		left1 = setup("/monster/Dragon/DRAGON_LEFT1", gp.tileSize, gp.tileSize);
		left2 = setup("/monster/Dragon/DRAGON_LEFT2", gp.tileSize, gp.tileSize);
		left3 = setup("/monster/Dragon/DRAGON_LEFT1", gp.tileSize, gp.tileSize);
		left4 = setup("/monster/Dragon/DRAGON_LEFT2", gp.tileSize, gp.tileSize);
		right1 = setup("/monster/Dragon/DRAGON_RIGHT1", gp.tileSize, gp.tileSize);
		right2 = setup("/monster/Dragon/DRAGON_RIGHT2", gp.tileSize, gp.tileSize);
		right3 = setup("/monster/Dragon/DRAGON_RIGHT1", gp.tileSize, gp.tileSize);
		right4 = setup("/monster/Dragon/DRAGON_RIGHT2", gp.tileSize, gp.tileSize);
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
			int i=new Random().nextInt(100)+1;
			if (i>99 && projectile.alive==false )
			{
				projectile.set(worldX,worldY,direction,true,this);
				gp.projectiles.add(projectile);
				
			}
	} 
    public void damageReact() {
		actionLockCounter = 0;
		direction = gp.player.direction;
	}
}