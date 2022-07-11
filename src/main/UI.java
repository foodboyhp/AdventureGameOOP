package main;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import entity.Entity;
import object.OBJ_HEART;
import object.OBJ_KEY;
import object.OBJ_MANA;

public class UI {

	GamePanel gp;
	Graphics2D g2;
	Font arial_40,arial_80B;
	BufferedImage heart_full, heart_half, heart_blank, mana_full, mana_blank;
	public boolean messageOn = false;
	public String message = "";
	int messageCounter = 0;
	public boolean gameFinished = false;
	public String currentDialogue = "";
	public int commandNum = 0;
	public int titleScreenState = 0;
	int subState = 0;
	
	public UI(GamePanel gp) {
		this.gp = gp;
		arial_40 = new Font("Arial", Font.PLAIN,40);
		arial_80B = new Font("Arial", Font.BOLD,80);
		
		Entity heart = new OBJ_HEART(gp);
		heart_full = heart.image;
		heart_half = heart.image2;
		heart_blank = heart.image3;
		Entity mana = new OBJ_MANA(gp);
		mana_full = mana.image;
		mana_blank = mana.image2;
	}
	
	public void showMessage(String text) {
		message = text;
		messageOn = true;
	}
	
	public void draw(Graphics2D g2) {
		
		this.g2 = g2;
		g2.setFont(arial_40);
		g2.setColor(Color.white);
		
		//Title State
		if(gp.gameState == gp.titleState) {
			drawTitleScreen();
		}
		//PlayState
		if(gp.gameState== gp.playState) {
			drawPlayerLife();
		}
		//Pause state
		if(gp.gameState == gp.pauseState) {
			drawPlayerLife();
			drawPauseScreen();
		}
		if(gp.gameState == gp.dialogueState) {
			drawDialogueScreen();
		}
		//Character State
		if(gp.gameState == gp.characterState) {
			drawCharacterScreen();
		}
		//Options state
		if(gp.gameState == gp.optionsState) {
			drawOptionScreen();
		}
		//Game over state
		if(gp.gameState == gp.gameOverState) {
			drawGameOverScreen();
		}
	}
	public void drawPlayerLife() {
		int x = gp.tileSize/2;
		int y = gp.tileSize/2;
		
		int i = 0;
		while(i<gp.player.maxLife/2) {
			g2.drawImage(heart_blank, x, y, null);
			i++;
			x+=gp.tileSize;
		}
		
		//Reset
		x = gp.tileSize/2;
		y = gp.tileSize/2;
		i = 0;
		
		//draw current life
		while(i< gp.player.life) {
			g2.drawImage(heart_half, x, y, null);
			i++;
			if(i< gp.player.life) {
				g2.drawImage(heart_full, x, y, null);
			}
			i++;
			x+=gp.tileSize;
		}
		
		//draw max mana
		x = gp.tileSize/2-5;
		y = (int)(gp.tileSize*1.5);
		i = 0;
		while(i<gp.player.maxMana) {
			g2.drawImage(mana_blank, x, y, null);
			i++;
			x+=35;
		}
		//draw current mana
		x = gp.tileSize/2-5;
		y = (int)(gp.tileSize*1.5);
		i = 0;
		while(i < gp.player.mana) {
			g2.drawImage(mana_full, x, y, null);
			i++;
			x+=35;
		}
	}
	
	public void drawTitleScreen() {
		
		if(titleScreenState == 0) {
			
			g2.setColor(new Color(70,120,80));
			g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);
			
			
			//title name
			g2.setFont(g2.getFont().deriveFont(Font.BOLD,90F));
			String text = "Adventure Game";
			int x = getXforCenteredText(text);
			int y = gp.tileSize*3;
			//shadow
			g2.setColor(Color.gray);
			g2.drawString(text, x+5, y+5);
			
			
			//main
			g2.setColor(Color.white);
			g2.drawString(text, x, y);
			//more image
			//blueboy
			x = gp.screenWidth/2-gp.tileSize;
			y+=gp.tileSize*2;
			g2.drawImage(gp.player.down1, x, y, gp.tileSize*2, gp.tileSize*2, null);		
			
			//menu
			g2.setFont(g2.getFont().deriveFont(Font.BOLD,48F));
			
			text = "NEW GAME";
			x = getXforCenteredText(text);
			y += gp.tileSize*4;
			g2.drawString(text,x,y);
			if(commandNum==0) {
				g2.drawString(">" ,x-gp.tileSize,y);
			}
			
			text = "LOAD GAME";
			x = getXforCenteredText(text);
			y += gp.tileSize;
			g2.drawString(text,x,y);
			if(commandNum==1) {
				g2.drawString(">" ,x-gp.tileSize,y);
			}
			
			text = "QUIT";
			x = getXforCenteredText(text);
			y += gp.tileSize;
			g2.drawString(text,x,y);
			if(commandNum==2) {
				g2.drawString(">" ,x-gp.tileSize,y);
			}
		}
		else if(titleScreenState==1) {
			//class selection screen
			g2.setColor(Color.white);
			g2.setFont(g2.getFont().deriveFont(42F));
			
			String text = "Select your Character!";
			int x = getXforCenteredText(text);
			int y = gp.tileSize*3;
			g2.drawString(text, x, y);
			
			text = "Hiep";
			x = getXforCenteredText(text);
			y+= gp.tileSize*2;
			g2.drawString(text, x, y);
			if(commandNum == 0) {
				g2.drawString(">", x-gp.tileSize, y);
			}
			
			text = "Hao";
			x = getXforCenteredText(text);
			y+= gp.tileSize;
			g2.drawString(text, x, y);
			if(commandNum == 1) {
				g2.drawString(">", x-gp.tileSize, y);
			}
			
			text = "Hoang";
			x = getXforCenteredText(text);
			y+= gp.tileSize;
			g2.drawString(text, x, y);
			if(commandNum == 2) {
				g2.drawString(">", x-gp.tileSize, y);
			}
			
			text = "Vinh";
			x = getXforCenteredText(text);
			y+= gp.tileSize;
			g2.drawString(text, x, y);
			if(commandNum == 3) {
				g2.drawString(">", x-gp.tileSize, y);
			}
			
			text = "Nghia";
			x = getXforCenteredText(text);
			y+= gp.tileSize;
			g2.drawString(text, x, y);
			if(commandNum == 4) {
				g2.drawString(">", x-gp.tileSize, y);
			}
			
			text = "Back";
			x = getXforCenteredText(text);
			y+= gp.tileSize*2;
			g2.drawString(text, x, y);
			if(commandNum == 5) {
				g2.drawString(">", x-gp.tileSize, y);
			}
		}
		else if(titleScreenState == 2) {
			g2.setColor(Color.white);
			g2.setFont(g2.getFont().deriveFont(42F));
			
			String text = "Choose difficulty";
			int x = getXforCenteredText(text);
			int y = gp.tileSize*3;
			g2.drawString(text, x, y);
			
			text = "Easy";
			x = getXforCenteredText(text);
			y+= gp.tileSize*2;
			g2.drawString(text, x, y);
			if(commandNum == 0) {
				g2.drawString(">", x-gp.tileSize, y);
			}
			text = "Medium";
			x = getXforCenteredText(text);
			y+= gp.tileSize;
			g2.drawString(text, x, y);
			if(commandNum == 1) {
				g2.drawString(">", x-gp.tileSize, y);
			}
			text = "Hard";
			x = getXforCenteredText(text);
			y+= gp.tileSize;
			g2.drawString(text, x, y);
			if(commandNum == 2) {
				g2.drawString(">", x-gp.tileSize, y);
			}
			
			text = "Back";
			x = getXforCenteredText(text);
			y+= gp.tileSize*2;
			g2.drawString(text, x, y);
			if(commandNum == 3) {
				g2.drawString(">", x-gp.tileSize, y);
			}
		}
		else if(titleScreenState == 3) {
			
		}
	}
	public void drawPauseScreen() {
		
		g2.setFont(g2.getFont().deriveFont(Font.PLAIN,80F));
		String text = "Paused";
		int x = getXforCenteredText(text);
		int y = gp.screenHeight/2;
		g2.drawString(text, x, y);
	}
	
	public void drawDialogueScreen() {

		//Window
		int x = gp.tileSize*2;
		int y = gp.tileSize/2;
		int width = gp.screenWidth - (gp.tileSize*4);
		int height = gp.tileSize*5;
		drawSubWindow(x, y, width, height);
		
		g2.setFont(g2.getFont().deriveFont(Font.PLAIN,32F));
		
		x+=gp.tileSize;
		y+=gp.tileSize;
		
		for(String line: currentDialogue.split("\n")) {
			g2.drawString(line, x, y);			
			y+=40;
		}
		
	}
	public void drawCharacterScreen() {

		//Create a frame
		final int frameX = gp.tileSize;
		final int frameY = gp.tileSize;
		final int frameWidth = gp.tileSize * 5;
		final int frameHeight = gp.tileSize * 10 ;
		drawSubWindow(frameX, frameY, frameWidth, frameHeight);
		g2.setColor(Color.white);
		g2.setFont(g2.getFont().deriveFont(32F));
		
		int textX = frameX + 20;
		int textY = frameY + gp.tileSize;
		final int lineHeight = 32;
		
		//names
		g2.drawString("Level", textX, textY);
		textY+=lineHeight;
		g2.drawString("Life", textX, textY);
		textY+=lineHeight;
		g2.drawString("Mana", textX, textY);
		textY+=lineHeight;
		g2.drawString("Strength", textX, textY);
		textY+=lineHeight;
		g2.drawString("Dexterity", textX, textY);
		textY+=lineHeight;
		g2.drawString("Attack", textX, textY);
		textY+=lineHeight;
		g2.drawString("Defense", textX, textY);
		textY+=lineHeight;
		g2.drawString("Exp", textX, textY);
		textY+=lineHeight;
		g2.drawString("Next Level", textX, textY);
		textY+=lineHeight;
		g2.drawString("Coin", textX, textY);
		textY+=lineHeight+20;
		g2.drawString("Weapon", textX, textY);
		textY+=lineHeight+15;
		g2.drawString("Shield", textX, textY);
		textY+=lineHeight;
		//values
		int tailX = (frameX + frameWidth) - 30;
		//reset textY
		textY = frameY + gp.tileSize;
		String value;
		
		value = String.valueOf(gp.player.level);
		textX = getXforAlignToRightText(value, tailX);
		g2.drawString(value, textX, textY);
		textY+=lineHeight;
		
		value = String.valueOf(gp.player.life + "/" + gp.player.maxLife);
		textX = getXforAlignToRightText(value, tailX);
		g2.drawString(value, textX, textY);
		textY+=lineHeight;
		
		value = String.valueOf(gp.player.mana + "/" + gp.player.maxMana);
		textX = getXforAlignToRightText(value, tailX);
		g2.drawString(value, textX, textY);
		textY+=lineHeight;
		
		value = String.valueOf(gp.player.strength);
		textX = getXforAlignToRightText(value, tailX);
		g2.drawString(value, textX, textY);
		textY+=lineHeight;
		
		value = String.valueOf(gp.player.dexterity);
		textX = getXforAlignToRightText(value, tailX);
		g2.drawString(value, textX, textY);
		textY+=lineHeight;
		
		value = String.valueOf(gp.player.attack);
		textX = getXforAlignToRightText(value, tailX);
		g2.drawString(value, textX, textY);
		textY+=lineHeight;
		
		value = String.valueOf(gp.player.defense);
		textX = getXforAlignToRightText(value, tailX);
		g2.drawString(value, textX, textY);
		textY+=lineHeight;
		
		value = String.valueOf(gp.player.exp);
		textX = getXforAlignToRightText(value, tailX);
		g2.drawString(value, textX, textY);
		textY+=lineHeight;
		
		value = String.valueOf(gp.player.nextLevelExp);
		textX = getXforAlignToRightText(value, tailX);
		g2.drawString(value, textX, textY);
		textY+=lineHeight;
		
		value = String.valueOf(gp.player.coin);
		textX = getXforAlignToRightText(value, tailX);
		g2.drawString(value, textX, textY);
		textY+=lineHeight;
		
		g2.drawImage(gp.player.currentWeapon.down1, tailX - gp.tileSize, textY-14, null);
		textY+=gp.tileSize;
		g2.drawImage(gp.player.currentShield.down1, tailX - gp.tileSize, textY-14, null);
		textY+=gp.tileSize;
	}
	public void drawGameOverScreen() {
		g2.setColor(new Color(0,0,0,150));
		g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);
		
		int x;
		int y;
		String text;
		g2.setFont(g2.getFont().deriveFont(Font.BOLD,110F));
		
		text = "Game Over";
		
		//shadow
		g2.setColor(Color.black);
		x = getXforCenteredText(text);
		y = gp.tileSize*4;
		g2.drawString(text, x, y);
		//main
		g2.setColor(Color.white);
		g2.drawString(text, x-4, y-4);
		//Retry
		g2.setFont(g2.getFont().deriveFont(50f));
		text = "Retry";
		x = getXforCenteredText(text);
		y += gp.tileSize*4;
		g2.drawString(text, x, y);
		if(commandNum==0) {
			g2.drawString(">", x-40, y);
		}
		//Back to the title screen
		text = "Quit";
		x = getXforCenteredText(text);
		y+=55;
		g2.drawString(text, x, y);
		if(commandNum==1) {
			g2.drawString(">", x-40, y);
		}
	}
	public void drawOptionScreen() {
		g2.setColor(Color.white);
		g2.setFont(g2.getFont().deriveFont(32f));
		
		//sub window
		final int frameX = gp.tileSize * 6;
		final int frameY = gp.tileSize;
		final int frameWidth = gp.tileSize * 8;
		final int frameHeight = gp.tileSize * 10 ;
		drawSubWindow(frameX, frameY, frameWidth, frameHeight);
		
		switch(subState) {
		case 0: options_top(frameX, frameY);break;
		case 1: break;
		case 2: break;
		}
		
	}
	public void options_top(int frameX, int frameY) {
		int textX;
		int textY;
		
		//Title
		String text = "Options";
		textX = getXforCenteredText(text);
		textY = frameY + gp.tileSize;
		g2.drawString(text, textX, textY);
		
		//Music
		textX = frameX + gp.tileSize;
		textY+=gp.tileSize*2;
		g2.drawString("Music", textX, textY);
		if(commandNum==0) {
			g2.drawString(">", textX-25, textY);
		}
		
		//SE
		textY+=gp.tileSize;
		g2.drawString("SE", textX, textY);
		if(commandNum==1) {
			g2.drawString(">", textX-25, textY);
		}
		//Control
		textY+=gp.tileSize;
		g2.drawString("Control", textX, textY);
		if(commandNum==2) {
			g2.drawString(">", textX-25, textY);
		}
		//Save game
		textY+=gp.tileSize;
		g2.drawString("Save Game", textX, textY);
		if(commandNum==3) {
			g2.drawString(">", textX-25, textY);
		}
		//End game
		textY+=gp.tileSize;
		g2.drawString("End Game", textX, textY);
		if(commandNum==4) {
			g2.drawString(">", textX-25, textY);
		}
		//back
		textY+=gp.tileSize*2;
		g2.drawString("Back", textX, textY);
		if(commandNum==5) {
			g2.drawString(">", textX-25, textY);
		}
		
		//music volume
		textX = frameX + (int)(gp.tileSize*4.5);
		textY = frameY + gp.tileSize*2+24;
		g2.drawRect(textX, textY, 120, 24);
		int volumeWidth = 24*gp.music.volumeScale;
		g2.fillRect(textX, textY, volumeWidth, 24);
		
		//se volume
		textY+=gp.tileSize;
		g2.drawRect(textX, textY, 120, 24);
		volumeWidth = 24*gp.se.volumeScale;
		g2.fillRect(textX, textY, volumeWidth, 24);
	}
	public void drawSubWindow(int x, int y, int width, int height) {
		
		Color c = new Color(0,0,0,200);
		g2.setColor(c);
		g2.fillRoundRect(x, y, width, height, 35, 35);
		
		c = new Color(255,255,255);
		g2.setColor(c);
		g2.setStroke(new BasicStroke(5));
		g2.drawRoundRect(x+5, y+5, width-10, height-10, 25, 25);
	}
	
	public int getXforCenteredText(String text) {

		int length = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
		int x = gp.screenWidth/2-length/2;
		return x;
	}
	public int getXforAlignToRightText(String text, int tailX) {
		int length = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
		int x = tailX - length;
		return x;
	}
}

