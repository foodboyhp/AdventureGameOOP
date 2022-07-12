package main;

import entity.NPC_Oldman;
import monster.MON_Bear;
import monster.MON_Dragon;
import monster.MON_GreenSlime;
import monster.MON_Snake;
import object.OBJ_BOOTS;
import object.OBJ_KEY;

public class AssetSetter {
	
	GamePanel gp;
	public AssetSetter(GamePanel gp) {
		this.gp = gp;
	}
	
	public void setObject() {
		int mapNum = 0;
		int i = 0;
		
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
		gp.monster[mapNum][i] = new MON_GreenSlime(gp);
		gp.monster[mapNum][i].worldX = gp.tileSize*21;
		gp.monster[mapNum][i].worldY = gp.tileSize*38;
		i++;
		
		gp.monster[mapNum][i] = new MON_Snake(gp);
		gp.monster[mapNum][i].worldX = gp.tileSize*23;
		gp.monster[mapNum][i].worldY = gp.tileSize*37;
		i++;
		
		gp.monster[mapNum][i] = new MON_Bear(gp);
		gp.monster[mapNum][i].worldX = gp.tileSize*24;
		gp.monster[mapNum][i].worldY = gp.tileSize*37;
		i++;
		
		gp.monster[mapNum][i] = new MON_GreenSlime(gp);
		gp.monster[mapNum][i].worldX = gp.tileSize*38;
		gp.monster[mapNum][i].worldY = gp.tileSize*42;
		i++;
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
