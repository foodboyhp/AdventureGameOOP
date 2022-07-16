package main;

import entity.NPC_Oldman;
import monster.*;
import object.*;

public class AssetSetter {
	
	GamePanel gp;
	public AssetSetter(GamePanel gp) {
		this.gp = gp;
	}
	
	public void setObject() {
		int mapNum = 0;
		int i = 0;
		gp.obj[mapNum][i] = new OBJ_Door(gp);
		gp.obj[mapNum][i].worldX = gp.tileSize*25;
		gp.obj[mapNum][i].worldY = gp.tileSize*23;
		i++;
		
		gp.obj[mapNum][i] = new OBJ_Potion_Red(gp);
		gp.obj[mapNum][i].worldX = gp.tileSize*21;
		gp.obj[mapNum][i].worldY = gp.tileSize*19;
		i++;
		
		gp.obj[mapNum][i] = new OBJ_Potion_Red(gp);
		gp.obj[mapNum][i].worldX = gp.tileSize*26;
		gp.obj[mapNum][i].worldY = gp.tileSize*21;
		i++;
		
		gp.obj[mapNum][i] = new OBJ_Axe(gp);
		gp.obj[mapNum][i].worldX = gp.tileSize*26;
		gp.obj[mapNum][i].worldY = gp.tileSize*20;
		i++;
		
		gp.obj[mapNum][i] = new OBJ_Shield_Blue(gp);
		gp.obj[mapNum][i].worldX = gp.tileSize*33;
		gp.obj[mapNum][i].worldY = gp.tileSize*21;
		i++;
		
		gp.obj[mapNum][i] = new OBJ_Potion_Red(gp);
		gp.obj[mapNum][i].worldX = gp.tileSize*22;
		gp.obj[mapNum][i].worldY = gp.tileSize*27;
		i++;
	}
	public void setNPC() {
		int mapNum = 0;
		int i = 0;
		gp.npc[mapNum][i] = new NPC_Oldman(gp);
		gp.npc[mapNum][i].worldX = gp.tileSize*21;
		gp.npc[mapNum][i].worldY = gp.tileSize*21;
		i++;
		
	}
	
	public void setMonster() {
		int mapNum = 0;
		int i = 0;
		/*gp.monster[mapNum][i] = new MON_Dragon(gp);
		gp.monster[mapNum][i].worldX = gp.tileSize*21;
		gp.monster[mapNum][i].worldY = gp.tileSize*38;
		i++;
		
		gp.monster[mapNum][i] = new MON_Dragon(gp);
		gp.monster[mapNum][i].worldX = gp.tileSize*23;
		gp.monster[mapNum][i].worldY = gp.tileSize*37;
		i++;
		
		gp.monster[mapNum][i] = new MON_Dragon(gp);
		gp.monster[mapNum][i].worldX = gp.tileSize*24;
		gp.monster[mapNum][i].worldY = gp.tileSize*37;
		i++;
		
		gp.monster[mapNum][i] = new MON_Dragon(gp);
		gp.monster[mapNum][i].worldX = gp.tileSize*38;
		gp.monster[mapNum][i].worldY = gp.tileSize*42;
		i++;*/
		for(int j = 0; j < i; j++) {
			if(gp.difficulty==1) {
				gp.monster[mapNum][j].speed = (int)(gp.monster[mapNum][j].speed*1.5);
				gp.monster[mapNum][j].maxLife = (int)(gp.monster[mapNum][j].maxLife*1.5);
				gp.monster[mapNum][j].life = gp.monster[mapNum][j].maxLife;
				gp.monster[mapNum][j].attack = (int)(gp.monster[mapNum][j].attack*1.5);
				gp.monster[mapNum][j].defense = (int)(gp.monster[mapNum][j].defense*1.5);
				gp.monster[mapNum][j].exp = (int)(gp.monster[mapNum][j].exp*1.5);
			}
			else if(gp.difficulty==2) {
				gp.monster[mapNum][j].speed*=2;
				gp.monster[mapNum][j].maxLife*=2;
				gp.monster[mapNum][j].life = gp.monster[mapNum][j].maxLife;
				gp.monster[mapNum][j].attack*=2;
				gp.monster[mapNum][j].defense*=2;
				gp.monster[mapNum][j].exp*=2;
			}
		}
	}
}
