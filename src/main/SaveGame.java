package main;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;

public class SaveGame {
	
	GamePanel gp;
	
	public SaveGame(GamePanel gp) {
		this.gp = gp;
	}
	public void saveGame() {
		try {
			FileWriter fw = new FileWriter("savedGame2.txt");
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write(String.valueOf(gp.currentPlayer));
			bw.newLine();
			bw.write(String.valueOf(gp.difficulty));
			bw.newLine();
			bw.write(String.valueOf(gp.currentMap));
			bw.newLine();
			bw.write(String.valueOf(gp.player.worldX/gp.tileSize));
			bw.newLine();
			bw.write(String.valueOf(gp.player.worldY/gp.tileSize));
			bw.newLine();
			bw.write(String.valueOf(gp.player.level));
			bw.newLine();
			bw.write(String.valueOf(gp.player.maxLife));
			bw.newLine();
			bw.write(String.valueOf(gp.player.life));
			bw.newLine();
			bw.write(String.valueOf(gp.player.maxMana));
			bw.newLine();
			bw.write(String.valueOf(gp.player.mana));
			bw.newLine();
			bw.write(String.valueOf(gp.player.strength));
			bw.newLine();
			bw.write(String.valueOf(gp.player.dexterity));
			bw.newLine();
			bw.write(String.valueOf(gp.player.exp));
			bw.newLine();
			bw.write(String.valueOf(gp.player.nextLevelExp));
			bw.newLine();
			bw.close();
		}
		catch (Exception e) {
			e.printStackTrace();// TODO: handle exception
		}
	}
	public void loadSavedGame() {
		try {
			BufferedReader br = new BufferedReader(new FileReader("savedGame2.txt"));
			String s = br.readLine();
			gp.currentPlayer = Integer.parseInt(s);
			s = br.readLine();
			gp.difficulty = Integer.parseInt(s);
			s = br.readLine();
			gp.currentMap = Integer.parseInt(s);
			s = br.readLine();
			gp.player.worldX = Integer.parseInt(s)*gp.tileSize;
			s = br.readLine();
			gp.player.worldY = Integer.parseInt(s)*gp.tileSize;
			s = br.readLine();
			gp.player.level = Integer.parseInt(s);
			s = br.readLine();
			gp.player.maxLife = Integer.parseInt(s);
			s = br.readLine();
			gp.player.life = Integer.parseInt(s);
			s = br.readLine();
			gp.player.maxMana = Integer.parseInt(s);
			s = br.readLine();
			gp.player.mana = Integer.parseInt(s);
			s = br.readLine();
			gp.player.strength = Integer.parseInt(s);
			s = br.readLine();
			gp.player.dexterity = Integer.parseInt(s);
			s = br.readLine();
			gp.player.exp = Integer.parseInt(s);
			s = br.readLine();
			gp.player.nextLevelExp = Integer.parseInt(s);
			br.close();
		}catch (Exception e) {
			e.printStackTrace();// TODO: handle exception
		}
	}
}
