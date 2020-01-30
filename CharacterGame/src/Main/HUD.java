package Main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import FrameWork.GameObject;

public class HUD {
	
	private int x, y;
	
	public static int HEALTH = 200;
	Font font = new Font("Arial", Font.BOLD, 36);
	Camera cam;
	
	public HUD(Camera cam) {
		this.cam = cam;
	}
	
	public void tick(GameObject player) {
		x = (int)player.getX() + 170;
		
		if(HEALTH <= 0) {
			HEALTH = 0;
			Game.currentState = Game.STATE.End;
		}
	}
	
	public void render(Graphics g) {
		g.setColor(Color.white);
		g.setFont(font);
		g.drawRect(x, 40, 200, 30);
		g.drawString("Level: " + (Game.LEVEL-1), x-550, 50);
		
		g.setColor(Color.green);
		g.fillRect(x, 40, HEALTH, 30);
	}
}
