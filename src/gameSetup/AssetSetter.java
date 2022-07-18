package gameSetup;

import java.util.Random;

import entity.NPC_Guide;
import main.GamePanel;
import monster.*;
import object.*;

public class AssetSetter {
	
	GamePanel gp;
	public int numOfCurrentMonster[] = {0,0,0};
	public int spawnCounter = 0;
	public AssetSetter(GamePanel gp) {
		this.gp = gp;
	}
	
	public void setObject() {
		int mapNum = 0;
		int i = 0;
		
		gp.obj[mapNum][i] = new OBJ_Potion_Red(gp);
		gp.obj[mapNum][i].worldX = gp.tileSize*21;
		gp.obj[mapNum][i].worldY = gp.tileSize*25;
		i++;
		
		gp.obj[mapNum][i] = new OBJ_Potion_Red(gp);
		gp.obj[mapNum][i].worldX = gp.tileSize*26;
		gp.obj[mapNum][i].worldY = gp.tileSize*21;
		i++;
		
		mapNum = 2;
		gp.obj[mapNum][i] = new OBJ_Axe(gp);
		gp.obj[mapNum][i].worldX = gp.tileSize*22;
		gp.obj[mapNum][i].worldY = gp.tileSize*38;
		i++;
		
		gp.obj[mapNum][i] = new OBJ_Shield_Blue(gp);
		gp.obj[mapNum][i].worldX = gp.tileSize*26;
		gp.obj[mapNum][i].worldY = gp.tileSize*38;
		i++;
		
		gp.obj[mapNum][i] = new OBJ_Potion_Red(gp);
		gp.obj[mapNum][i].worldX = gp.tileSize*22;
		gp.obj[mapNum][i].worldY = gp.tileSize*27;
		i++;
	}
	public void setNPC() {
		int mapNum = 0;
		int i = 0;
		gp.npc[mapNum][i] = new NPC_Guide(gp);
		gp.npc[mapNum][i].worldX = gp.tileSize*21;
		gp.npc[mapNum][i].worldY = gp.tileSize*21;
		i++;
		
	}
	
	public void setMonster() {
		/*if(mapNum==0||mapNum==1) {
			while(numOfCurrentMonster[mapNum]<5) {
				spawnCounter++;
				if(spawnCounter==60) {
					setRandomMonster(mapNum);					
				}
				spawnCounter = 0;
			}
		}
		else if(mapNum==2) {
			if(numOfCurrentMonster[mapNum]<1) {
				gp.monster[mapNum][numOfCurrentMonster[mapNum]] = new MON_Boss(gp);
				gp.monster[mapNum][numOfCurrentMonster[mapNum]].worldX = gp.tileSize*24;
				gp.monster[mapNum][numOfCurrentMonster[mapNum]].worldY = gp.tileSize*24;
				numOfCurrentMonster[mapNum]++;				
			}
		}*/
		int mapNum = 0; 
		int i = 0;
		gp.monster[mapNum][i] = new MON_Bear(gp);
		gp.monster[mapNum][i].worldX = gp.tileSize*21;
		gp.monster[mapNum][i].worldY = gp.tileSize*32;
		i++;
		
		gp.monster[mapNum][i] = new MON_Bear(gp);
		gp.monster[mapNum][i].worldX = gp.tileSize*23;
		gp.monster[mapNum][i].worldY = gp.tileSize*34;
		i++;
		
		gp.monster[mapNum][i] = new MON_GreenSlime(gp);
		gp.monster[mapNum][i].worldX = gp.tileSize*24;
		gp.monster[mapNum][i].worldY = gp.tileSize*37;
		i++;
		
		gp.monster[mapNum][i] = new MON_GreenSlime(gp);
		gp.monster[mapNum][i].worldX = gp.tileSize*38;
		gp.monster[mapNum][i].worldY = gp.tileSize*42;
		i++;
		
		mapNum = 1;
		gp.monster[mapNum][i] = new MON_Bear(gp);
		gp.monster[mapNum][i].worldX = gp.tileSize*24;
		gp.monster[mapNum][i].worldY = gp.tileSize*38;
		i++;
		
		gp.monster[mapNum][i] = new MON_Bear(gp);
		gp.monster[mapNum][i].worldX = gp.tileSize*18;
		gp.monster[mapNum][i].worldY = gp.tileSize*37;
		i++;
		
		gp.monster[mapNum][i] = new MON_GreenSlime(gp);
		gp.monster[mapNum][i].worldX = gp.tileSize*22;
		gp.monster[mapNum][i].worldY = gp.tileSize*37;
		i++;
		
		gp.monster[mapNum][i] = new MON_GreenSlime(gp);
		gp.monster[mapNum][i].worldX = gp.tileSize*38;
		gp.monster[mapNum][i].worldY = gp.tileSize*42;
		i++;
		
		gp.monster[mapNum][i] = new MON_Dragon(gp);
		gp.monster[mapNum][i].worldX = gp.tileSize*38;
		gp.monster[mapNum][i].worldY = gp.tileSize*43;
		i++;
		
		mapNum = 2;
		gp.monster[mapNum][i] = new MON_Boss(gp);
		gp.monster[mapNum][i].worldX = gp.tileSize*21;
		gp.monster[mapNum][i].worldY = gp.tileSize*24;
		i++;
	}
	public void setRandomMonster(int mapNum) {
		
		Random rand = new Random();
		int i;
		
		if(mapNum==0) {
			i = rand.nextInt(100)+1;
			if(i<=50) {
				gp.monster[mapNum][numOfCurrentMonster[mapNum]] = new MON_GreenSlime(gp);
				gp.monster[mapNum][numOfCurrentMonster[mapNum]].worldX = 0;
				gp.monster[mapNum][numOfCurrentMonster[mapNum]].worldY = 2;
				numOfCurrentMonster[mapNum]++;
			}
			else {
				gp.monster[mapNum][numOfCurrentMonster[mapNum]] = new MON_Snake(gp);
				gp.monster[mapNum][numOfCurrentMonster[mapNum]].worldX = 0;
				gp.monster[mapNum][numOfCurrentMonster[mapNum]].worldY = 0;
				numOfCurrentMonster[mapNum]++;
			}
		}
		else if(mapNum==1) {
			i = rand.nextInt(100)+1;
			if(i<=50) {
				gp.monster[mapNum][numOfCurrentMonster[mapNum]] = new MON_Dragon(gp);
				gp.monster[mapNum][numOfCurrentMonster[mapNum]].worldX = 1;
				gp.monster[mapNum][numOfCurrentMonster[mapNum]].worldY = 2;
				numOfCurrentMonster[mapNum]++;
			}
			else {
				gp.monster[mapNum][numOfCurrentMonster[mapNum]] = new MON_Bear(gp);
				gp.monster[mapNum][numOfCurrentMonster[mapNum]].worldX = 0;
				gp.monster[mapNum][numOfCurrentMonster[mapNum]].worldY = 0;
				numOfCurrentMonster[mapNum]++;
			}
		}
	}
}
