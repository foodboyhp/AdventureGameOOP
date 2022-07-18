
package entity;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.awt.image.ShortLookupTable;

import javax.imageio.ImageIO;

import gameSetup.UtilityTool;
import main.GamePanel;

public class Entity {
	GamePanel gp;
    
    public BufferedImage up1, up2, down1, down2, left1, left2, right1, right2;
    public BufferedImage attackUp1, attackUp2, attackDown1, attackDown2, 
    attackLeft1, attackLeft2, attackRight1, attackRight2;
    public BufferedImage image, image2, image3;
    public Rectangle solidArea = new Rectangle(0,0,48,48);
    public Rectangle attackArea = new Rectangle(0,0,0,0);
    public int solidAreaDefaultX, solidAreaDefaultY;
    public boolean collision = false;
    String dialouges[] = new String[20];
    int dialogueIndex = 0;
    //State
    public int worldX,worldY;
    public String direction = "down";	
    public int spriteNum = 1;
    public boolean collisionOn = false;
    public boolean invincible = false;
    public boolean attacking = false;
    public boolean alive = true;
    public boolean died = false;
    boolean hpBarOn = false;
    public boolean onPath = false;
    
    
    //Counter
    public int spriteCounter = 0;
    public int actionLockCounter = 0;
    public int invicibleCounter = 0;
    public int isDyingCounter = 0;
    public int shootAvailableCounter = 0;
    int hpBarCounter = 0;
    
    //Character Status
	public String name;
    public int maxLife;
    public int life;
    public int maxMana;
    public int mana;
    public int speed;
    public int level;
    public int strength;
    public int dexterity;
    public int attack;
    public int defense;
    public int exp;
    public int nextLevelExp;
    public int coin;
    public Entity currentWeapon;
    public Entity currentShield;
    public Projectile projectile;

    //item Attributes 
    public int value;
    public int attackValue;
    public int defenseValue;
    public String description = "";
    public int useCost;
    public Entity user;
    public boolean stackable = false;
    public int amount = 1;
    
    //Type
	public int type;//player = 0; npc = 1; monster = 2;
    public final int type_player = 0;
    public final int type_npc = 1;
    public final int type_monster = 2;
    public final int type_sword = 3;
    public final int type_axe = 4;
    public final int type_shield = 5;
    public final int type_consumable = 6;
    public final int type_pickupOnly = 7;
    public final int type_obstacble = 8;
    
    public Entity(GamePanel gp) {
    	this.gp = gp;
    }  
    public int getLeftX() {
    	return worldX+solidArea.x;
    }
    public int getRightX() {
    	return worldX+solidArea.x+solidArea.width;
    }
    public int getTopY() {
    	return worldY + solidArea.y;
    }
    public int getBottomY() {
    	return worldY+solidArea.x+solidArea.height;
    }
    public int getCol() {
    	return (worldX + solidArea.x)/gp.tileSize;
    }
    public int getRow() {
    	return (worldY+solidArea.y)/gp.tileSize;
    }
    public void setAction() {
    	
    }
    public void damageReact() {
    	
    }
    public void getStatsOnDifficulty() {
    	
    }
    public void speak() {

		if(dialouges[dialogueIndex] == null) {
			dialogueIndex = 0;
		}
		gp.ui.currentDialogue = dialouges[dialogueIndex];
		dialogueIndex++;
		
		switch(gp.player.direction) {
		case "up": direction = "down"; break;
		case "down" : direction = "up"; break;
		case "left" : direction = "right"; break;
		case "right" : direction = "left"; break;
			
		}
    }
    public void interact() {
    	
    }
    public boolean use(Entity entity) {
    	return false;
    }
    public void checkDrop() {
    	
    }
    public void dropItem(Entity droppedItem) {
    	for(int i = 0; i<gp.obj[gp.currentMap].length; i++) {
    		if(gp.obj[gp.currentMap][i]==null) {
    			gp.obj[gp.currentMap][i]=droppedItem;
    			gp.obj[gp.currentMap][i].worldX = worldX;
    			gp.obj[gp.currentMap][i].worldY = worldY;
    			break; 
    		}
    	}
    }
    public void checkCollision() {
    	
    	collisionOn = false;
    	gp.cChecker.checkTile(this);
    	gp.cChecker.checkObject(this, false);
    	gp.cChecker.checkEntity(this,gp.npc);
    	gp.cChecker.checkEntity(this, gp.monster);
    	boolean contactPlayer = gp.cChecker.checkPlayer(this);
    	
    	if(type == type_monster && contactPlayer == true) {
    		damagePlayer(attack);
    	}
    }
    public void update() {
    	setAction();
    	checkCollision();
    	//if collision is false, can move
    	if(collisionOn== false) {
			switch(direction) {
			case "up": worldY -= speed; break;
			case "down": worldY += speed; break;
			case "right": worldX += speed; break;
			case "left": worldX -= speed; break;
			}
		}
        
        spriteCounter++;
        if(spriteCounter > 10) {
        	if(spriteNum == 1) 
        		spriteNum = 2;
        	else if(spriteNum == 2)
        		spriteNum = 1;
        	spriteCounter = 0;
        }
        
        if(invincible == true) {
    		invicibleCounter++;
    		if(invicibleCounter>60) {
    			invincible = false;
    			invicibleCounter = 0;
    		}
    	}
        if(shootAvailableCounter<30) {
        	shootAvailableCounter++;
        }
    }
    public void damagePlayer(int atk) {
    	if(gp.player.invincible==false) {
			gp.playSE(6);
			int damage = attack - gp.player.defense;
			if(damage < 0) {
				damage = 0;
			}
			gp.player.life-=damage;
			gp.player.invincible = true;
		}
    }
    public void draw(Graphics2D g2) {
    	
    	BufferedImage image = null;
    	
    	int screenX = worldX - gp.player.worldX + gp.player.screenX; //players position is always on the center on the screen -> 0-0 tile is really in different place since it's outside of our game window, so we need to do some offsetting.
		int screenY = worldY - gp.player.worldY + gp.player.screenY;
		
		if(worldX + gp.tileSize>gp.player.worldX-gp.player.screenX&&
		   worldX - gp.tileSize<gp.player.worldX+gp.player.screenX&&
		   worldY + gp.tileSize>gp.player.worldY-gp.player.screenY&&
	       worldY - gp.tileSize<gp.player.worldY+gp.player.screenY) {
			switch (direction) {
            case "up":
            	if(spriteNum == 1){
            		image = up1;
            	}
            	if(spriteNum == 2){
            		image = up2;
            	}
                break;
            case "down":
            	if(spriteNum == 1){
            		image = down1;
            	}
            	if(spriteNum == 2){
            		image = down2;
            	}
                break;
            case "left":
            	if(spriteNum == 1){
            		image = left1;
            	}
            	if(spriteNum == 2){
            		image = left2;
            	}
                break;
            case "right":
            	if(spriteNum == 1){
            		image = right1;
            	}
            	if(spriteNum == 2){
            		image = right2;
            	}
                break;
			}
			
			//Monster HP bar
			if(type==2 && hpBarOn == true) {
				double oneScale = (double)gp.tileSize/maxLife;
				double hpBarValue = oneScale*life;
				
				
				g2.setColor(new Color(35,35,35));
				g2.fillRect(screenX-1, screenY-16, gp.tileSize+2, 12);
				
				g2.setColor(new Color(255,0,30));
				g2.fillRect(screenX, screenY - 15, (int)hpBarValue, 10);
			
				hpBarCounter++;
				
				if(hpBarCounter > 600) {
					hpBarCounter = 0;
					hpBarOn = false;
				}
			}
			
			
			if(invincible == true) {
				hpBarOn = true;
				hpBarCounter = 0;
				changeAlpha(g2, 0.4f);
			}
			if(died == true) {
				dyingAnimation(g2);
				
			}
			
			g2.drawImage(image, screenX, screenY, null);	
			
			changeAlpha(g2, 1f);
		}
    }
    
    public void dyingAnimation(Graphics2D g2) {
    	isDyingCounter++;
    	
    	int i = 5;
    	
    	if(isDyingCounter<=i) {
    		changeAlpha(g2, 0f);
    	}
    	if(isDyingCounter>i&&isDyingCounter<=2*i) {
    		changeAlpha(g2, 1f);
    	}
    	if(isDyingCounter>2*i&&isDyingCounter<=3*i) {
    		changeAlpha(g2, 0f);
    	}
    	if(isDyingCounter>3*i&&isDyingCounter<=4*i) {
    		changeAlpha(g2, 1f);
    	}
    	if(isDyingCounter>4*i&&isDyingCounter<=5*i) {
    		changeAlpha(g2, 0f);
    	}
    	if(isDyingCounter>5*i&&isDyingCounter<=6*i) {
    		changeAlpha(g2, 1f);
    	}
    	if(isDyingCounter>6*i&&isDyingCounter<=7*i) {
    		changeAlpha(g2, 0f);
    	}
    	if(isDyingCounter>7*i&&isDyingCounter<=8*i) {
    		changeAlpha(g2, 1f);
    	}
    	if(isDyingCounter>8*i) {
    		died = true;
    		alive = false;
    	}
    }
    
    public void changeAlpha(Graphics2D g2, float alphaValue) {
    	g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alphaValue));
    }
    
    public BufferedImage setup(String imgPath, int width, int height) {
    	UtilityTool uTool = new UtilityTool();
    	BufferedImage image = null;
    	
    	try {
    		image = ImageIO.read(getClass().getResourceAsStream(imgPath+".png"));
    		image = uTool.scaleImage(image, width, height);
    	} catch (Exception e) {
			e.printStackTrace();// TODO: handle exception
		}
    	return image;
    }
    public void searchPath(int goalCol, int goalRow) {
    	int startCol = (worldX+solidArea.x)/gp.tileSize;
    	int startRow = (worldY+solidArea.y)/gp.tileSize;
    	gp.pFinder.setNodes(startCol, startRow, goalCol, goalRow, this);
    	
    	if(gp.pFinder.search()==true) {     
    		//next worldX & worldY
    		int nextX = gp.pFinder.pathList.get(0).col * gp.tileSize;
    		int nextY = gp.pFinder.pathList.get(0).row * gp.tileSize;
    		
    		//Entity's solidArea position
    		int enLeftX = worldX+solidArea.x;
    		int enRightX = worldX+solidArea.x+solidArea.width;
    		int enTopY = worldY+solidArea.y;
    		int enBottomY = worldY+solidArea.y+solidArea.height;
    		
    		if(enTopY> nextY&&enLeftX>=nextX&&enRightX< nextX+gp.tileSize) {
    			direction="up";
    		}
    		else if(enTopY< nextY&&enLeftX>=nextX&&enRightX<nextX+gp.tileSize) {
    			direction="down";
    		}
    		else if(enTopY>=nextY&&enBottomY<nextY+gp.tileSize) {
    			if(enLeftX>nextX) {
    				direction="left";
    			}
    			if(enLeftX<nextX) {
    				direction="right";
    			}
    		}
    		else if(enTopY>nextY&&enLeftX>nextX) {
    			direction="up";
    			checkCollision();
    			if(collisionOn==true) {
    				direction="left";
    			}
    		}
    		else if(enTopY>nextY&&enLeftX<nextX) {
    			direction="up";
    			checkCollision();
    			if(collisionOn==true) {
    				direction="right";
    			}
    		}
    		else if(enTopY<nextY&&enLeftX>nextX) {
    			direction="down";
    			checkCollision();
    			if(collisionOn==true) {
    				direction="left";
    			}
    		}
    		else if(enTopY<nextY&&enLeftX<nextX) {
    			direction="down";
    			checkCollision();
    			if(collisionOn==true) {
    				direction="right";
    			}
    		}
    		
    		//if reaches goal, stop search
    		/*int nextCol = gp.pFinder.pathList.get(0).col;
    		int nextRow = gp.pFinder.pathList.get(0).row;
    		if(nextCol==goalCol&&nextRow==goalRow) {
    			onPath=false; 
    		}*/
    	}
    }
    public int getDetected(Entity entity, Entity target[][], String targetName) {
    	int index = 999;
    	//Check surrounding object
    	int nextWorldX = user.getLeftX();
    	int nextWorldY = user.getTopY();
    	
    	switch(user.direction) {
    	case "up":	nextWorldY = user.getTopY()-1; break;
    	case "down": nextWorldY = user.getBottomY()+1; break;
    	case "left": nextWorldX = user.getLeftX()-1;break;
    	case "right": nextWorldY = user.getRightX()+1;break;
    	}
    	int col = nextWorldX/gp.tileSize;
    	int row = nextWorldY/gp.tileSize;
    	
    	for(int i = 0; i < target[1].length; i++) {
    		if(target[gp.currentMap][i]!=null) {
    			if(target[gp.currentMap][i].getCol()==col&&
    					target[gp.currentMap][i].getRow()==row&&
    					target[gp.currentMap][i].name.equals(targetName)) {
    				index = i;
    				break;
    			}
    		}
    	}
    	return index;
    }
}	
