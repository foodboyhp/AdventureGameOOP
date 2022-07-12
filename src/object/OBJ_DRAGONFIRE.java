package object;

import entity.Projectile;
import main.GamePanel;

public class OBJ_DRAGONFIRE extends Projectile {
    GamePanel gp;
	
	public OBJ_DRAGONFIRE(GamePanel gp) {
		super(gp);
		this.gp = gp;
		
		name = "Fireball";
		speed = 6;
		maxLife = 80;
		life = maxLife;
		getImage();

	}
	public void getImage()
	{
		down1 = setup("/Projectile/fireball_down_1", gp.tileSize, gp.tileSize);
		down2 = setup("/Projectile/fireball_down_2", gp.tileSize, gp.tileSize);
		up1 = setup("/Projectile/fireball_up_1", gp.tileSize, gp.tileSize);
		up2 = setup("/Projectile/fireball_up_2", gp.tileSize, gp.tileSize);
		left1 = setup("/Projectile/fireball_left_1", gp.tileSize, gp.tileSize);
		left2 = setup("/Projectile/fireball_left_2", gp.tileSize, gp.tileSize);
		right1 = setup("/Projectile/fireball_right_1", gp.tileSize, gp.tileSize);
		right2 = setup("/Projectile/fireball_right_2", gp.tileSize, gp.tileSize);
	}
}