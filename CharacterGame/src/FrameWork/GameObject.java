package FrameWork;

import java.awt.Graphics;
import java.awt.Rectangle;

public abstract class GameObject {

	protected float x,y;
	protected float velX, velY;
	protected ID id;
	protected boolean falling = true;
	public boolean jumping = false;
	
	public GameObject(float x, float y, ID id) {
		 this.x = x;
		 this.y = y;
		 this.id = id;
	 }
	 
	 public abstract void tick();
	 public abstract void render(Graphics g);
	 public abstract Rectangle getBounds();

	public float getX() {
		return x;
	}

	public void setX(float x) {
		this.x = x;
	}

	public float getY() {
		return y;
	}

	public void setY(float y) {
		this.y = y;
	}

	public float getVelX() {
		return velX;
	}

	public void setVelX(float velX) {
		this.velX = velX;
	}

	public float getVelY() {
		return velY;
	}

	public void setVelY(float velY) {
		this.velY = velY;
	}
	
	public ID getId() {
		return id;
	}

	public boolean isFalling() {
		return falling;
	}

	public void setFalling(boolean falling) {
		this.falling = falling;
	}

	public boolean isJumping() {
		return jumping;
	}

	public void setJumping(boolean jumping) {
		this.jumping = jumping;
	}
	
	 
 
}
