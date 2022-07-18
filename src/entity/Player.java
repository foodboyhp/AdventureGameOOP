
package entity;

import entity.Entity;
import gameSetup.KeyHandler;
import gameSetup.UtilityTool;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.print.attribute.standard.JobPriority;

import main.GamePanel;
import object.OBJ_Fireball;
import object.OBJ_Key;
import object.OBJ_Shield_Wood;
import object.OBJ_Sword_normal;

public class Player extends Entity{
    KeyHandler keyH;
    
    public final int screenX;
    public final int screenY;
    public int hasKey = 0;
    public boolean attackCanceled = false;
    int standCounter = 0;
    public ArrayList<Entity> inventory = new ArrayList<>();
    public final int inventorySize = 20;
    public int currentPlayer;
    public int currentMapAvailable;
    public int numOfMonKilled[] = {45,50,0};
    public final int map1Level = 5;
    public final int map2Level = 10;
   
    
    public Player(GamePanel gp, KeyHandler keyH, int currentPlayer){
        super(gp);
        this.keyH = keyH;
        this.currentPlayer = currentPlayer;
        this.currentMapAvailable = 0;
        screenX = gp.screenWidth/2-(gp.tileSize/2);
        screenY = gp.screenHeight/2-(gp.tileSize/2);
        
        solidArea = new Rectangle(8,16,32,32);
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        
        
        
        setDefaultValues();
        getPlayerImage();
        getPlayerAttackImage();
        setItem();
    }    
    public void setDefaultValues(){
        worldX = gp.tileSize*23;
        worldY = gp.tileSize*21;
    	//worldX = gp.tileSize*12;
    	//worldY = gp.tileSize*13;
        direction = "down";
        
        //Player Status
        speed = 4;
        level = 4;
        maxLife = 6;
        life = maxLife;
        maxMana = 4;
        mana = maxMana;
        strength = 20;
        dexterity = 999;
        exp = 0;
        nextLevelExp = 5;
        coin = 0;
        currentWeapon = new OBJ_Sword_normal(gp);
        currentShield = new OBJ_Shield_Wood(gp);
        projectile = new OBJ_Fireball(gp);
        attack = getAttack();
        defense = getDefense();
        
        
    }
    public void setDefaultPositions() {
    	worldX = gp.tileSize*23;
        worldY = gp.tileSize*21;
        direction = "down";
    }
    public void restoreLifeAndMana() {
    	life = maxLife;
    	mana = maxMana;
    	invincible = false;
    }
    public void setItem() {
    	inventory.add(currentWeapon);
    	inventory.add(currentShield);
    }
    public int getAttack() {
    	attackArea = currentWeapon.attackArea;
    	return attack = strength * currentWeapon.attackValue;
    }
    public int getDefense() {
    	return defense = dexterity * currentShield.defenseValue;
    }
    public void getPlayerImage(){
    	switch(currentPlayer) {
    	case 0:
            up1 = setup("/player/up1", gp.tileSize, gp.tileSize);
            up2 = setup("/player/up2", gp.tileSize, gp.tileSize);
            down1 = setup("/player/down1", gp.tileSize, gp.tileSize);
            down2 = setup("/player/down2", gp.tileSize, gp.tileSize);
            left1 = setup("/player/left1", gp.tileSize, gp.tileSize);
            left2 = setup("/player/left2", gp.tileSize, gp.tileSize);
            right1 = setup("/player/right1", gp.tileSize, gp.tileSize);
            right2 = setup("/player/right2", gp.tileSize, gp.tileSize);
            break;
    	case 1:
    		up1 = setup("/player/boy2/tile015", gp.tileSize, gp.tileSize);
            up2 = setup("/player/boy2/tile013", gp.tileSize, gp.tileSize);
            down1 = setup("/player/boy2/tile003", gp.tileSize, gp.tileSize);
            down2 = setup("/player/boy2/tile001", gp.tileSize, gp.tileSize);
            left1 = setup("/player/boy2/tile004", gp.tileSize, gp.tileSize);
            left2 = setup("/player/boy2/tile005", gp.tileSize, gp.tileSize);
            right1 = setup("/player/boy2/tile008", gp.tileSize, gp.tileSize);
            right2 = setup("/player/boy2/tile011", gp.tileSize, gp.tileSize);
    		break;
    	case 2:
    		up1 = setup("/player/boy3/up2", gp.tileSize, gp.tileSize);
            up2 = setup("/player/boy3/up4", gp.tileSize, gp.tileSize);
            down1 = setup("/player/boy3/down2", gp.tileSize, gp.tileSize);
            down2 = setup("/player/boy3/down4", gp.tileSize, gp.tileSize);
            left1 = setup("/player/boy3/left2", gp.tileSize, gp.tileSize);
            left2 = setup("/player/boy3/left4", gp.tileSize, gp.tileSize);
            right1 = setup("/player/boy3/right2", gp.tileSize, gp.tileSize);
            right2 = setup("/player/boy3/right4", gp.tileSize, gp.tileSize);
    		break;
    	default:
    		up1 = setup("/player/up1", gp.tileSize, gp.tileSize);
            up2 = setup("/player/up2", gp.tileSize, gp.tileSize);
            down1 = setup("/player/down1", gp.tileSize, gp.tileSize);
            down2 = setup("/player/down2", gp.tileSize, gp.tileSize);
            left1 = setup("/player/left1", gp.tileSize, gp.tileSize);
            left2 = setup("/player/left2", gp.tileSize, gp.tileSize);
            right1 = setup("/player/right1", gp.tileSize, gp.tileSize);
            right2 = setup("/player/right2", gp.tileSize, gp.tileSize);
    		break;
    	}
    }
    public void getPlayerAttackImage() {
    	
    	/*if(currentWeapon.type==type_axe) {
    		
    		attackUp1 = setup("/player/Attack/boy_axe_up_1", gp.tileSize, gp.tileSize*2);
    		attackUp2 = setup("/player/Attack/boy_axe_up_2", gp.tileSize, gp.tileSize*2);
    		attackDown1 = setup("/player/Attack/boy_axe_down_1", gp.tileSize, gp.tileSize*2);
    		attackDown2 = setup("/player/Attack/boy_axe_down_2", gp.tileSize, gp.tileSize*2);
    		attackLeft1 = setup("/player/Attack/boy_axe_left_1", gp.tileSize*2, gp.tileSize);
    		attackLeft2 = setup("/player/Attack/boy_axe_left_2", gp.tileSize*2, gp.tileSize);
    		attackRight1 = setup("/player/Attack/boy_axe_right_1", gp.tileSize*2, gp.tileSize);
    		attackRight2 = setup("/player/Attack/boy_axe_right_2", gp.tileSize*2, gp.tileSize);    
    	}
    	else*/
    	if(currentWeapon.type == type_sword) {
    		switch(currentPlayer) {
    		case 0:
    			attackUp1 = setup("/player/Attack/boy_attack_up_1", gp.tileSize, gp.tileSize*2);
        		attackUp2 = setup("/player/Attack/boy_attack_up_2", gp.tileSize, gp.tileSize*2);
        		attackDown1 = setup("/player/Attack/boy_attack_down_1", gp.tileSize, gp.tileSize*2);
        		attackDown2 = setup("/player/Attack/boy_attack_down_2", gp.tileSize, gp.tileSize*2);
        		attackLeft1 = setup("/player/Attack/boy_attack_left_1", gp.tileSize*2, gp.tileSize);
        		attackLeft2 = setup("/player/Attack/boy_attack_left_2", gp.tileSize*2, gp.tileSize);
        		attackRight1 = setup("/player/Attack/boy_attack_right_1", gp.tileSize*2, gp.tileSize);
        		attackRight2 = setup("/player/Attack/boy_attack_right_2", gp.tileSize*2, gp.tileSize); 
    			break;
    		case 1:
    			attackUp1 = setup("/player/boy2/Attack/attack_up1", gp.tileSize, gp.tileSize*2);
    	    	attackUp2 = setup("/player/boy2/Attack/attack_up2", gp.tileSize, gp.tileSize*2);
    	    	attackDown1 = setup("/player/boy2/Attack/attack_down1", gp.tileSize, gp.tileSize*2);
    	    	attackDown2 = setup("/player/boy2/Attack/attack_down2", gp.tileSize, gp.tileSize*2);
    	    	attackLeft1 = setup("/player/boy2/Attack/attack_left2", gp.tileSize*2, gp.tileSize);
    	    	attackLeft2 = setup("/player/boy2/Attack/attack_left3", gp.tileSize*2, gp.tileSize);
    	    	attackRight1 = setup("/player/boy2/Attack/attack_right2", gp.tileSize*2, gp.tileSize);
    	    	attackRight2 = setup("/player/boy2/Attack/attack_right3", gp.tileSize*2, gp.tileSize);
    			break;
    		case 2:
    			attackUp1 = setup("/player/boy3/Attack/up1", gp.tileSize, gp.tileSize*2);
        		attackUp2 = setup("/player/boy3/Attack/up2", gp.tileSize, gp.tileSize*2);
        		attackDown1 = setup("/player/boy3/Attack/down1", gp.tileSize, gp.tileSize*2);
        		attackDown2 = setup("/player/boy3/Attack/down2", gp.tileSize, gp.tileSize*2);
        		attackLeft1 = setup("/player/boy3/Attack/left1", gp.tileSize*2, gp.tileSize);
        		attackLeft2 = setup("/player/boy3/Attack/left2", gp.tileSize*2, gp.tileSize);
        		attackRight1 = setup("/player/boy3/Attack/right1", gp.tileSize*2, gp.tileSize);
        		attackRight2 = setup("/player/boy3/Attack/right2", gp.tileSize*2, gp.tileSize);
    			break;
    		default:
    			attackUp1 = setup("/player/Attack/boy_attack_up_1", gp.tileSize, gp.tileSize*2);
        		attackUp2 = setup("/player/Attack/boy_attack_up_2", gp.tileSize, gp.tileSize*2);
        		attackDown1 = setup("/player/Attack/boy_attack_down_1", gp.tileSize, gp.tileSize*2);
        		attackDown2 = setup("/player/Attack/boy_attack_down_2", gp.tileSize, gp.tileSize*2);
        		attackLeft1 = setup("/player/Attack/boy_attack_left_1", gp.tileSize*2, gp.tileSize);
        		attackLeft2 = setup("/player/Attack/boy_attack_left_2", gp.tileSize*2, gp.tileSize);
        		attackRight1 = setup("/player/Attack/boy_attack_right_1", gp.tileSize*2, gp.tileSize);
        		attackRight2 = setup("/player/Attack/boy_attack_right_2", gp.tileSize*2, gp.tileSize);
    			break;
    		}	   		
    	}
    	
    }   
    public void update(){
    	
    	if(attacking == true) {
    		attacking(); 
    	}
    	
    	else if(keyH.upPressed == true || keyH.downPressed == true 
    		|| keyH.leftPressed == true || keyH.rightPressed == true||keyH.enterPressed==true
    		|| keyH.Jpressed == true) {
    		if(keyH.upPressed == true){
                direction = "up";
                
            }
            else if(keyH.downPressed == true){
                direction = "down";
                
            }
            else if(keyH.leftPressed == true){
                direction = "left";
            }
            else if(keyH.rightPressed == true){
                direction = "right";
            }
    		
    		//Check tile collsion
    		collisionOn = false;
    		gp.cChecker.checkTile(this);
    		//Check obj collision
    		int objIndex = gp.cChecker.checkObject(this, true);
    		pickUpObject(objIndex);
    		//Check npc collision
    		int npcIndex = gp.cChecker.checkEntity(this, gp.npc);
    		interactNPC(npcIndex);
    		//check monster collision
    		int monsterIndex = gp.cChecker.checkEntity(this, gp.monster);
    		contactMonster(monsterIndex);
    		
    		//Check event
    		gp.eHandler.checkEvent();
    		gp.keyH.enterPressed = false;
    		//If collision = false, player can move
    		if(collisionOn == false && keyH.enterPressed== false && keyH.Jpressed == false) {
    			switch(direction) {
	    			case "up": worldY -= speed; break;
	    			case "down": worldY += speed; break;
	    			case "right": worldX += speed; break;
	    			case "left": worldX -= speed; break;
    			}
    		}
    		
    		if(keyH.Jpressed == true && attackCanceled == false) {
    			attacking = true;
    			spriteCounter = 0;
    		}

    		gp.keyH.enterPressed = false;
            gp.keyH.Jpressed = false;
            
            spriteCounter++;
            if(spriteCounter > 12) {
            	if(spriteNum == 1) 
            		spriteNum = 2;
            	else if(spriteNum == 2)
            		spriteNum = 1;
            	spriteCounter = 0;
            }
    	} 
    	else {
    		standCounter++;
    		if(standCounter==20) {
    			spriteNum = 1;
    			standCounter = 0;
    		}
    	}
    	
    	if(gp.keyH.shootKeyPressed == true && projectile.alive == false && shootAvailableCounter == 30
    			&& projectile.haveResource(this) == true) {
    		projectile.set(worldX, worldY, direction, true, this);
    		projectile.subtractResource(this);
    		gp.projectiles.add(projectile);
    		shootAvailableCounter = 0;
    		gp.playSE(10);
    	}
    	

    	if(shootAvailableCounter < 30) {
    		shootAvailableCounter++;
    	}
    	//Invincible time
    	if(invincible == true) {
    		invicibleCounter++;
    		if(invicibleCounter>60) {
    			invincible = false;
    			invicibleCounter = 0;
    		}
    	}
    	
    	if(life>maxLife) {
    		life = maxLife;
    	}
    	if(mana>maxMana) {
    		mana = maxMana;
    	}
    	if(life <= 0) {
    		gp.gameState = gp.gameOverState;
    		gp.ui.commandNum = -1;
    		gp.stopMusic();
    		gp.playSE(11);
    	}
    	currentMapAvailable = currentMapAvailable();
    }   
    public void attacking() {
    	spriteCounter++;
    	
    	if(spriteCounter<= 5) {
    		spriteNum = 1;
    	}
    	if(spriteCounter > 5&&spriteCounter<=25) {
    		spriteNum =2;
    		
    		int currentWorldX = worldX;
    		int currentWorldY = worldY;
    		int solidAreaWidth = solidArea.width;
    		int solidAreaHeight = solidArea.height;
    		
    		switch(direction) {
    		case "up": worldY-=attackArea.height; break;
    		case "down": worldY+=attackArea.height;break;
    		case "left": worldX-=attackArea.width;break;
    		case "right": worldX+=attackArea.width; break;
    		}
    		
    		solidArea.width = attackArea.width;
    		solidArea.height = attackArea.height;
    		
    		int monsterIndex = gp.cChecker.checkEntity(this, gp.monster);
    		damageMonster(monsterIndex, attack);
    		
    		worldX = currentWorldX;
    		worldY = currentWorldY;
    		solidArea.width = solidAreaWidth;
    		solidArea.height = solidAreaHeight;
    	
    	}
    	if(spriteCounter>25) {
    		spriteNum = 1;
    		spriteCounter = 0;
    		attacking = false;
    	}
    }  
    public void pickUpObject(int i) {
    	
    	
    	
    	if(gp.keyH.enterPressed==true) {
    		if(i!=999) {
    			
    			//pickup only items
    			if(gp.obj[gp.currentMap][i].type==type_pickupOnly) {
    				gp.obj[gp.currentMap][i].use(this);
    				gp.obj[gp.currentMap][i] = null;
    			}
    			//obstacle
    			else if(gp.obj[gp.currentMap][i].type==type_obstacble) {
    				if(keyH.enterPressed==true) {
    					gp.obj[gp.currentMap][i].interact();
    				}
    			}
    			
    			//inventory items
    			else {
            		String text;
            		if(canObtain(gp.obj[gp.currentMap][i])==true) {
            			gp.playSE(1);
            			text = "Got a " + gp.obj[gp.currentMap][i].name + "!" ; 
            		}
            		else {
            			text = "You can't carry any more"; 
            		}
            		gp.ui.addMessage(text);
            		gp.obj[gp.currentMap][i] = null;	
    			}
            }
    	}
    }
    public void interactNPC(int i) {
    	
    	if(gp.keyH.Jpressed == true) {
    		if(i!=999) {
    			attackCanceled = true;
    		}else {
    			gp.playSE(7);
    			attacking = true;
    		}
    	}
    	gp.keyH.Jpressed = false;
    	if(gp.keyH.enterPressed == true) {
    		if(i!=999) {
    			attackCanceled = true;
    			gp.gameState = gp.dialogueState;
    			gp.npc[gp.currentMap][i].speak();
    		}
    	}
    	
    }
    public void contactMonster(int i) {
    	if(i!=999) {
    		if(invincible == false) {
    			gp.playSE(6);
    			int damage = gp.monster[gp.currentMap][i].attack - defense;
    			if(damage < 0) {
    				damage = 0;
    			}
    			life-=damage;
    			invincible = true;
    		}
    	}
    }
    public void damageMonster(int i, int attack) {
    	if(i!=999) {
    		if(gp.monster[gp.currentMap][i].invincible == false) {
    			
    			gp.playSE(5);
    			
    			int damage = attack - gp.monster[gp.currentMap][i].defense;
    			if(damage < 0) {
    				damage = 0;
    			}
    			gp.ui.addMessage(damage + " damage!");
    			gp.monster[gp.currentMap][i].life -= damage;
    			gp.monster[gp.currentMap][i].invincible = true;
    			gp.monster[gp.currentMap][i].damageReact();
    			
    			if(gp.monster[gp.currentMap][i].life<=0) { 
    				gp.monster[gp.currentMap][i].died = true;
    				numOfMonKilled[gp.currentMap]++;
    				gp.aSetter.numOfCurrentMonster[gp.currentMap]--;
    				gp.ui.addMessage("Killed the " + gp.monster[gp.currentMap][i].name + "!");
    				exp+=gp.monster[gp.currentMap][i].exp;
    				gp.ui.addMessage("Exp + " + gp.monster[gp.currentMap][i].exp);
    				checkLevelUp();
    			}
    		}
    	}
    	else {
		}
    }  
    public void checkLevelUp() {
    	if(exp>=nextLevelExp) {
    		level++;
    		nextLevelExp = nextLevelExp*2;
    		maxLife+=2;
    		strength++;
    		dexterity++;
    		attack = getAttack();
    		defense = getDefense();
    		
    		gp.playSE(8);
    		gp.gameState = gp.dialogueState;
    		gp.ui.currentDialogue = "You are level " + level + " now!";
    	}
    }
    public void selectItem() {
    	int itemIndex = gp.ui.getItemIndexOnSlot();
    	if(itemIndex < inventory.size()) {
    		Entity selectedItem = inventory.get(itemIndex);
    		if(selectedItem.type == type_sword||selectedItem.type == type_axe) {
    			currentWeapon = selectedItem;
    			attack = getAttack();
    			getPlayerAttackImage();
    		}
    		if(selectedItem.type == type_shield) {
    			currentShield = selectedItem;
    			defense = getDefense();
    		}
    		if(selectedItem.type == type_consumable) {
    			
    			if(selectedItem.use(this)==true) {
    				if(selectedItem.amount>1) {
    					selectedItem.amount--;
    				}
    				else {
    					inventory.remove(itemIndex);      				    					
    				}
    			}
    		}
    	}
    }
    public int searchItemInInventory(String itemName) {
    	int itemIndex = 999;
    	for(int i = 0; i< inventory.size();i++) {
    		if(inventory.get(i).name.equals(itemName)) {
    			itemIndex = i;
    			break;
    		}
    	}
    	return itemIndex;
    	
    }
    public boolean canObtain(Entity item) {
    	boolean canObtain = false;
    	
    	//check if stackable
    	if(item.stackable==true) {
    		int index = searchItemInInventory(item.name);
    		if(index!=999) {
    			inventory.get(index).amount++;
    			canObtain=true;
    		}
    		else {//if new item, check vacancy
    			if(inventory.size()!=inventorySize) {
    				inventory.add(item);
    				canObtain=true;
    			}
    		}
    	}
    	else {//not stackable, check vacancy
    		if(inventory.size()!=inventorySize) {
				inventory.add(item);
				canObtain=true;
			}
    	}
    	return canObtain;
    }
    public void draw(Graphics2D g2){
        BufferedImage image = null;
        
        int tempScreenX = screenX;
        int tempScreenY = screenY;
        
        switch (direction) {
            case "up":
            	if(attacking == false) {
            		if(spriteNum == 1){image = up1;}
            		if(spriteNum == 2){image = up2;}
            	}
            	if(attacking ==true) {
            		tempScreenY = screenY - gp.tileSize;
            		if(spriteNum == 1){image = attackUp1;}
            		if(spriteNum == 2){image = attackUp2;}
            	}
            	break;
            case "down":
            	if(attacking == false) {
            		if(spriteNum == 1){image = down1;}
            		if(spriteNum == 2){image = down2;}
            	}
            	if(attacking ==true) {
            		if(spriteNum == 1){image = attackDown1;}
            		if(spriteNum == 2){image = attackDown2;}
            	}
                break;
            case "left":
            	if(attacking == false) {
            		if(spriteNum == 1){image = left1;}
            		if(spriteNum == 2){image = left2;}
            	}
            	if(attacking ==true) {
            		tempScreenX = screenX - gp.tileSize;
            		if(spriteNum == 1){image = attackLeft1;}
            		if(spriteNum == 2){image = attackLeft2;}
            	}
                break;
            case "right":
            	if(attacking == false) {
            		if(spriteNum == 1){image = right1;}
            		if(spriteNum == 2){image = right2;}
            	}
            	if(attacking ==true) {
            		if(spriteNum == 1){image = attackRight1;}
            		if(spriteNum == 2){image = attackRight2;}
            	}
                break;
        }
        /*
        int x = screenX;
        int y = screenY;
        
        if(screenX>worldX) {
        	x = worldX;
        }
        if(screenY>worldY) {
        	y = worldY;
        }
        int rightOffset = gp.screenWidth-screenX;
		if(rightOffset > gp.worldWidth-worldX) {
			x = gp.screenWidth-(gp.worldWidth-worldX);
		}
		int bottomOffset = gp.screenHeight-screenY;
		if(bottomOffset>gp.worldHeight-worldY) {
			y = gp.screenHeight-(gp.worldHeight-worldY);
		}
		*/
		if(invincible == true) {
			g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,0.3f));
		}
        
        g2.drawImage(image, tempScreenX, tempScreenY, null);
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,1F));
    }
    public int currentMapAvailable() {
    	if(currentMapAvailable == 0) {
    		if(numOfMonKilled[0]>=50&&level>=5) {
    			currentMapAvailable=1;
    		}
    	}
    	else if(currentMapAvailable == 1) {
    		if(numOfMonKilled[1]>=50&&level>=10) {
    			currentMapAvailable=2;
    		}
    	}
    	else if(currentMapAvailable == 2) {
    		if(numOfMonKilled[2]>=1) {
    			currentMapAvailable = 3;
    		}
    	}
    	return currentMapAvailable;
    }
}
