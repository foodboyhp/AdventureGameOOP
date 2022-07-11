package main;

import entity.Entity;

public class CollisionCheck {

	GamePanel gp;
	public CollisionCheck(GamePanel gp) {
		this.gp=gp;
	}
	public void checkTile(Entity entity) {
		int entityLeftWorldX = entity.worldX + entity.solidArea.x;
		int entityRightWorldX = entity.worldX + entity.solidArea.x + entity.solidArea.width;
		int entityTopWorldY = entity.worldY + entity.solidArea.y;
		int entityBotWorldY = entity.worldY + entity.solidArea.y + entity.solidArea.height; //TODO: check bottom collision to make moving more smooth.. Now when movin right or left when collidin with bottom, we rly cant move..
		
		
		
		int entityLeftCol = entityLeftWorldX/gp.tileSize;
		int entityRightCol = entityRightWorldX/gp.tileSize;
		int entityTopRow = entityTopWorldY/gp.tileSize;
		int entityBotRow = entityBotWorldY/gp.tileSize;
		
		int tileNumber1, tileNumber2;
		
		switch(entity.direction) {
			
		case "up":
			entityTopRow = (entityTopWorldY - entity.speed)/gp.tileSize;
			tileNumber1 = gp.tileM.mapTileNum[gp.currentMap][entityLeftCol][entityTopRow]; //left side
			tileNumber2 = gp.tileM.mapTileNum[gp.currentMap][entityRightCol][entityTopRow]; //right side
				if(gp.tileM.tile[tileNumber1].collision == true || gp.tileM.tile[tileNumber2].collision == true) {
					entity.collisionOn=true;
				}
			break;
			
		case "down":
			entityBotRow = (entityBotWorldY + entity.speed)/gp.tileSize;
			tileNumber1 = gp.tileM.mapTileNum[gp.currentMap][entityLeftCol][entityBotRow]; //left side
			tileNumber2 = gp.tileM.mapTileNum[gp.currentMap][entityRightCol][entityBotRow]; //right side
				if(gp.tileM.tile[tileNumber1].collision == true || gp.tileM.tile[tileNumber2].collision == true) {
					entity.collisionOn=true;
				}
			
			break;
		case "left":
			
			entityLeftCol = (entityLeftWorldX - entity.speed)/gp.tileSize;
			tileNumber1 = gp.tileM.mapTileNum[gp.currentMap][entityLeftCol][entityTopRow]; //left side
			tileNumber2 = gp.tileM.mapTileNum[gp.currentMap][entityLeftCol][entityBotRow]; //right side
				if(gp.tileM.tile[tileNumber1].collision == true || gp.tileM.tile[tileNumber2].collision == true) {
					entity.collisionOn=true;
				}
			
			break;
		case "right":
			entityRightCol = (entityRightWorldX + entity.speed)/gp.tileSize;
			tileNumber1 = gp.tileM.mapTileNum[gp.currentMap][entityRightCol][entityTopRow]; //left side
			tileNumber2 = gp.tileM.mapTileNum[gp.currentMap][entityRightCol][entityBotRow]; //right side
				if(gp.tileM.tile[tileNumber1].collision == true || gp.tileM.tile[tileNumber2].collision == true) {
					entity.collisionOn=true;
				}
			break;
		}
	}
	public int checkObject(Entity entity, boolean player) {
		int index = 999;
		
		for(int i = 0; i < gp.obj[gp.currentMap].length; i++) {
			//scan obj array
			
			if(gp.obj[gp.currentMap][i] != null) {
				//get entity's solid area position
				entity.solidArea.x = entity.worldX + entity.solidArea.x;
				entity.solidArea.y = entity.worldY + entity.solidArea.y;
				
				//Get the obj solid area position
				gp.obj[gp.currentMap][i].solidArea.x = gp.obj[gp.currentMap][i].worldX + gp.obj[gp.currentMap][i].solidArea.x;
				gp.obj[gp.currentMap][i].solidArea.y = gp.obj[gp.currentMap][i].worldY + gp.obj[gp.currentMap][i].solidArea.y;
				
				switch(entity.direction) {
				
				case "up":
					entity.solidArea.y -= entity.speed;
					break;
				case "down":
					entity.solidArea.y += entity.speed;
					break;
				case "left":
					entity.solidArea.x -= entity.speed;
					break;
				case "right":
					entity.solidArea.x += entity.speed;
					break;
				}

				if(entity.solidArea.intersects(gp.obj[gp.currentMap][i].solidArea)) {
					if(gp.obj[gp.currentMap][i].collision == true) {
						entity.collisionOn = true;
					}
					if(player == true) {
						index = i;
					}
				}
				
				entity.solidArea.x = entity.solidAreaDefaultX;
				entity.solidArea.y = entity.solidAreaDefaultY;
				gp.obj[gp.currentMap][i].solidArea.x = gp.obj[gp.currentMap][i].solidAreaDefaultX;
				gp.obj[gp.currentMap][i].solidArea.y = gp.obj[gp.currentMap][i].solidAreaDefaultY;
			}
			
		}
		
		return index;
	}
	//NPC or monster collision
	public int checkEntity(Entity entity, Entity[][] target) {
		int index = 999;
		
		for(int i = 0; i < target[gp.currentMap].length; i++) {
			//scan target array
			
			if(target[gp.currentMap][i] != null) {
				//get entity's solid area position
				entity.solidArea.x = entity.worldX + entity.solidArea.x;
				entity.solidArea.y = entity.worldY + entity.solidArea.y;
				
				//Get the target solid area position
				target[gp.currentMap][i].solidArea.x = target[gp.currentMap][i].worldX + target[gp.currentMap][i].solidArea.x;
				target[gp.currentMap][i].solidArea.y = target[gp.currentMap][i].worldY + target[gp.currentMap][i].solidArea.y;
				
				switch(entity.direction) {
				
				case "up":
					entity.solidArea.y -= entity.speed;
					break;
				case "down":
					entity.solidArea.y += entity.speed;
					break;
				case "left":
					entity.solidArea.x -= entity.speed;
					break;
				case "right":
					entity.solidArea.x += entity.speed;
					break;
				}
				
				if(entity.solidArea.intersects(target[gp.currentMap][i].solidArea)) {
					if(target[gp.currentMap][i]!=entity) {
						entity.collisionOn = true;
						index = i;
					}
				}
				
				entity.solidArea.x = entity.solidAreaDefaultX;
				entity.solidArea.y = entity.solidAreaDefaultY;
				target[gp.currentMap][i].solidArea.x = target[gp.currentMap][i].solidAreaDefaultX;
				target[gp.currentMap][i].solidArea.y = target[gp.currentMap][i].solidAreaDefaultY;
			}
			
		}
		
		return index;
	}
	public boolean checkPlayer(Entity entity) {
		
		boolean contactPlayer = false;
		
		if(gp.player != null) {
			//get entity's solid area position
			entity.solidArea.x = entity.worldX + entity.solidArea.x;
			entity.solidArea.y = entity.worldY + entity.solidArea.y;
			
			//Get the target solid area position
			gp.player.solidArea.x = gp.player.worldX + gp.player.solidArea.x;
			gp.player.solidArea.y = gp.player.worldY + gp.player.solidArea.y;
			
			switch(entity.direction) {
			
			case "up":
				entity.solidArea.y -= entity.speed;
				break;
			case "down":
				entity.solidArea.y += entity.speed;
				break;
			case "left":
				entity.solidArea.x -= entity.speed;
				break;
			case "right":
				entity.solidArea.x += entity.speed;
				break;
			
			}

			if(entity.solidArea.intersects(gp.player.solidArea)) {
				entity.collisionOn = true;
				contactPlayer = true;
			}
			entity.solidArea.x = entity.solidAreaDefaultX;
			entity.solidArea.y = entity.solidAreaDefaultY;
			gp.player.solidArea.x = gp.player.solidAreaDefaultX;
			gp.player.solidArea.y = gp.player.solidAreaDefaultY;
		}
		return contactPlayer;
	}
}
