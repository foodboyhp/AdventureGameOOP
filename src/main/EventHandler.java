package main;

import java.awt.Rectangle;

public class EventHandler {

	GamePanel gp;
	EventRect eventRect[][][];
	
	int previousX, previousY;
	boolean canTouchEvent = true;
	
	public EventHandler(GamePanel gp) {
		this.gp = gp;
		
		eventRect = new EventRect[gp.maxMap][gp.maxWorldCol][gp.maxWorldRow];
		int col = 0;
		int row = 0;
		int map = 0;
		
		while(map< gp.maxMap && col < gp.maxWorldCol && row < gp.maxWorldRow) {
			eventRect[map][col][row] = new EventRect();
			eventRect[map][col][row].x = 23;
			eventRect[map][col][row].y = 23;
			eventRect[map][col][row].width = 2;
			eventRect[map][col][row].height = 2;
			eventRect[map][col][row].eventRectDefaultX = eventRect[map][col][row].x;
			eventRect[map][col][row].eventRectDefaultY = eventRect[map][col][row].y;
			
			col++;
			if(col == gp.maxWorldCol) {
				col = 0;
				row++;
				if(row==gp.maxWorldRow) {
					row = 0;
					map++;
				}
			}
		}
		
				
	}
	
	public void checkEvent() {
		
		//Check if the player character is more than 1 tile from last event
		int xDistance = Math.abs(gp.player.worldX - previousX);
		int yDistance = Math.abs(gp.player.worldY - previousY);
		int distance = Math.max(xDistance, yDistance);
		if(distance>gp.tileSize) {
			canTouchEvent = true;
		}
		if(canTouchEvent== true) {
			//if(hit(0,23,12,"up")==true) healingPool(gp.gameState);
			if(hit(0,27,16,"right") == true) damagePit(gp.dialogueState);	
			else if(hit(0,23,12, "any") == true) teleportPortal(1,12,13);
			else if(hit(1,12,13, "any") == true) teleportPortal(0,23,12);
		}
		
	}
	
	public boolean hit(int map ,int col, int row, String regDirection) {
		boolean hit = false;
		
		if(map == gp.currentMap) {
			gp.player.solidArea.x = gp.player.worldX + gp.player.solidArea.x;
			gp.player.solidArea.y = gp.player.worldY + gp.player.solidArea.y;
			eventRect[map][col][row].x = col*gp.tileSize+eventRect[map][col][row].x;
			eventRect[map][col][row].y = row*gp.tileSize+eventRect[map][col][row].y;
			
			if(gp.player.solidArea.intersects(eventRect[map][col][row])&&eventRect[map][col][row].eventDone == false) {
				if(gp.player.direction.contentEquals(regDirection)||regDirection.contentEquals("any"))
					hit = true;
				
					previousX = gp.player.worldX;
					previousY = gp.player.worldY;
			}
			
			gp.player.solidArea.x = gp.player.solidAreaDefaultX;
			gp.player.solidArea.y = gp.player.solidAreaDefaultY;
			eventRect[map][col][row].x = eventRect[map][col][row].eventRectDefaultX;
			eventRect[map][col][row].y = eventRect[map][col][row].eventRectDefaultY;
		}
		return hit;
	}
	public void damagePit( int gameState) {
		gp.gameState = gameState;
		gp.playSE(6);
		gp.ui.currentDialogue = "You fall into a pit";
		gp.player.life -= 1;
		canTouchEvent = false;
	}
	public void healingPool(int gameState) {
		if(gp.keyH.enterPressed == true) {
			gp.player.attackCanceled = true;
			gp.gameState = gameState;
			gp.player.life = gp.player.maxLife;
			gp.player.mana = gp.player.maxMana;
			gp.playSE(2);
			gp.aSetter.setMonster();
		}
	}
	public void teleportPortal(int map, int col, int row) {
		if(gp.keyH.enterPressed == true) {
			gp.currentMap = map;
			gp.player.worldX = gp.tileSize*col;
			gp.player.worldY = gp.tileSize*row;
			previousX = gp.player.worldX;
			previousY = gp.player.worldY;
			canTouchEvent = false;
			gp.playSE(12);
		}
	}
}
