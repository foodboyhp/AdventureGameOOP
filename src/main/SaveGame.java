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
			BufferedWriter bw = new BufferedWriter(new FileWriter("/savedGame/savedGame1.txt"));
			bw.close();
		}
		catch (Exception e) {
			e.printStackTrace();// TODO: handle exception
		}
	}
	public void loadGame() {
		try {
			BufferedReader br = new BufferedReader(new FileReader("/savedGame/savedGame1.txt"));
			String s = br.readLine();
			br.close();
		}catch (Exception e) {
			e.printStackTrace();// TODO: handle exception
		}
	}
}
