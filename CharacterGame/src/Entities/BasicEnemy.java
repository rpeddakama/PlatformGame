package Entities;

import Main.Handler;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import FrameWork.GameObject;
import FrameWork.ID;
import FrameWork.Texture;
import Main.Camera;
import Main.Game;
import Main.HUD;
import Main.Handler;

public class BasicEnemy extends GameObject{
	
	private Handler handler;
	private int width = 32, height = 32;
	private GameObject player;
	private Camera cam;
		
	Texture tex = Game.getInstance();
	
	public BasicEnemy(float x, float y, ID id, Handler handler) {
		super(x, y, id);
		this.handler = handler;
		velX = 10;
	}

	public void tick() {
		x+=velX;
		y+=velY;
		
		collision();
	}

	public void render(Graphics g) {
		
		g.setColor(Color.red);
		g.fillRect((int)x, (int)y, width, height);
		
		Graphics2D g2d = (Graphics2D) g;		 	
	}
	
	public Rectangle getBounds() {
		return new Rectangle((int)x+10, (int)y+height/2, width-20, height/2);
	}
	
	public Rectangle getBoundsLeft() {
		return new Rectangle((int)x, (int)y+10, 5, height-20);
	}
	
	public Rectangle getBoundsRight() {
		return new Rectangle((int)x+width-5, (int)y+10, 5, height-20);
	}
	
	public Rectangle getBoundsTop() {
		return new Rectangle((int)x+10, (int)y, width-20, height/2);
	}
	
	public void collision() {
		for(int i = 0; i < handler.object.size(); i++) {
			GameObject tempObject = handler.object.get(i);
			
			if(tempObject.getId() == ID.Player) {
				if(getBoundsLeft().intersects(tempObject.getBounds())) {
					HUD.HEALTH-=20;
				}
			}
			
			if(tempObject.getId() == ID.Block){
				
				if(getBoundsLeft().intersects(tempObject.getBounds())) {
					x = tempObject.getX()+32;
					velX = -velX;
				}
				
				if(getBoundsRight().intersects(tempObject.getBounds())) {
					x = tempObject.getX()-32;
					velX = -velX;
				}
				
			}
			
			
			}
		}
}
	


