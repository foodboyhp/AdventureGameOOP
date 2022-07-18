package monster;

import java.util.Random;

import entity.Entity;
import main.GamePanel;
import object.OBJ_Coin_Bronze;
import object.OBJ_DragonFire;
import object.OBJ_Heart;
import object.OBJ_Mana;

public class MON_Dragon extends Entity{
    GamePanel gp;
	public MON_Dragon(GamePanel gp) {
		super(gp);
		this.gp = gp;
		
		type = type_monster;
		name = "Dragon";
		getStatsOnDifficulty();
		projectile = new OBJ_DragonFire(gp);
		projectile.attack = 10;
		
		solidArea.x = 3;
		solidArea.y = 18;
		solidArea.width = 42;
		solidArea.height = 30;
		solidAreaDefaultX = solidArea.x;
		solidAreaDefaultY = solidArea.y;
		
		getImage();
	}
	public void getStatsOnDifficulty() {
		speed = 2;
		maxLife = 20;
		life = maxLife;
		attack = 2;
		defense = 3;
		exp = 7;
		switch(gp.difficulty) {
		case 0:
			break;
		case 1:
			speed = (int)(1.5*speed);
			maxLife = (int)(1.5*maxLife);
			life = maxLife;
			attack = (int)(1.5*attack);
			defense = (int)(1.5*defense);
			exp = (int)(1.5*exp);
			break;
		case 2:
			speed = (int)(2*speed);
			maxLife = (int)(2*maxLife);
			life = maxLife;
			attack = (int)(2*attack);
			defense = (int)(2*defense);
			exp = (int)(2*exp);
			break;
		default: 
			break;
		}
	}
    public void getImage() {
		down1 = setup("/monster/Drago/DRAGON_DOWN1", gp.tileSize, gp.tileSize);
		down2 = setup("/monster/Drago/DRAGON_DOWN2", gp.tileSize, gp.tileSize);
		up1 = setup("/monster/Drago/DRAGON_UP1", gp.tileSize, gp.tileSize);
		up2 = setup("/monster/Drago/DRAGON_UP2", gp.tileSize, gp.tileSize);
		left1 = setup("/monster/Drago/DRAGON_LEFT1", gp.tileSize, gp.tileSize);
		left2 = setup("/monster/Drago/DRAGON_LEFT2", gp.tileSize, gp.tileSize);
		right1 = setup("/monster/Drago/DRAGON_RIGHT1", gp.tileSize, gp.tileSize);
		right2 = setup("/monster/Drago/DRAGON_RIGHT2", gp.tileSize, gp.tileSize);
	}
    public void update() {
		super.update();
		
		int xDistance = Math.abs(worldX-gp.player.worldX);
		int yDistance = Math.abs(worldY-gp.player.worldY);
		int tileDistance = (xDistance+yDistance)/gp.tileSize;
		
		if(onPath==false&&tileDistance<5) {
			int i = new Random().nextInt(100)+1;
			if(i>50) {
				onPath=true;
			}
		}
		if(onPath==true&&tileDistance>=10) {
			onPath=false;
		}
	}
    public void setAction() {
    	if(onPath==true) {
			int goalCol = (gp.player.worldX+gp.player.solidArea.x)/gp.tileSize;
			int goalRow = (gp.player.worldY+gp.player.solidArea.y)/gp.tileSize;
			searchPath(goalCol,goalRow);

			int i = new Random().nextInt(200)+1;
			if(i>197&&projectile.alive==false&&shootAvailableCounter==30) {
				projectile.set(worldX,worldY,direction,true,this);
				gp.projectiles.add(projectile);
				shootAvailableCounter=0;
			}
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
    public void damageReact() {
		actionLockCounter = 0;
		//direction = gp.player.direction;
		onPath = true;
	}
	public void checkDrop() {
		//cast a die
		int i = new Random().nextInt(100)+1;
		
		//set monster drop
		if(i<50) {
			dropItem(new OBJ_Coin_Bronze(gp));
		}
		if(i>=50&&i<75) {
			dropItem(new OBJ_Heart(gp));
		}
		if(i>=75&&i<100) {
			dropItem(new OBJ_Mana(gp));
		}
	}
}