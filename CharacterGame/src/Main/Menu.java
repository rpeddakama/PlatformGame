package Main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;

import FrameWork.GameObject;

public class Menu extends MouseAdapter {

	private Handler handler;
	GameObject tempObject;

	Color Top = Color.white;
	Color middle = Color.white;
	Color bottom = Color.white;
	Color helpColor = Color.white;

	Font titleFont = new Font("Arial", Font.BOLD, 60);
	Font fnt1 = new Font("Arial", Font.BOLD, 48);
	Font smallFont = new Font("Arial", Font.BOLD, 12);

	private int width = 400, height = 100;
	private int MX, MY;
	
	Random r;

	public Menu(Handler handler) {
		this.handler = handler;
		r = new Random();
		
	}
	
	public void tick() {
		
	}

	public void render(Graphics g) {
		if (Game.currentState == Game.STATE.Menu) {
			g.setColor(Color.white);
			// Menu Word
			g.setFont(titleFont);
			g.drawString("Menu", 320, 100);

			g.setFont(fnt1);
			
			// Top
			g.setColor(Top);
			g.drawRect(200, 200, width, height);
			g.drawString("Play", 340, 270);
			g.setColor(Color.white);

			// Middle
			g.setColor(middle);
			g.drawRect(200, 350, width, height);
			g.drawString("Help", 340, 420);
			g.setColor(Color.white);

			// Bottom
			g.setColor(bottom);
			g.drawRect(200, 500, width, height);
			g.drawString("Quit", 340, 570);
			g.setColor(Color.white);
			

		}

		if (Game.currentState == Game.STATE.End) {
			//GameOver
			g.setColor(Color.white);
			g.setFont(titleFont);
			g.drawString("Game Over", 247 , 130);
			
			//Play Again
			g.setFont(fnt1);
			g.setColor(Top);
			g.drawRect(200, 200, width, height);
			g.drawString("Play Again", 280, 270);
			g.setColor(Color.white);
		}
		
		if(Game.currentState == Game.STATE.Help) {
			//Help
			g.setColor(Color.white);
			g.setFont(titleFont);
			g.drawString("Help", 320 , 100);
			
			g.setColor(helpColor);
			g.drawString("Back", 320, 575);
			g.drawRect(200, 500, width, height);
			
			g.setColor(Color.white);
			g.setFont(smallFont);
			g.drawString("Use WASD to move and space to jump. Avoid red square enemies. Level ends when you reach yellow square at the end", 10, 300);
		}

	}

	public boolean mouseOver(int mx, int my, int x, int y, int width, int height) {
		if (mx >= x && mx <= x + width) {
			if (my >= y && my <= y + height)
				return true;
			else
				return false;
		} else
			return false;
	}

	public void mousePressed(MouseEvent e) {
		MX = e.getX();
		MY = e.getY();
		
		if(Game.currentState == Game.STATE.Menu) {
			if(mouseOver(MX, MY, 200, 200, width, height))Game.currentState = Game.STATE.Game;
			if(mouseOver(MX, MY, 200, 350, width, height))Game.currentState = Game.STATE.Help;
			if(mouseOver(MX, MY, 200, 500, width, height))System.exit(1);
		}
		
		if(Game.currentState == Game.STATE.End) {
			if(mouseOver(MX, MY, 200, 200, width, height)) {
				Game.currentState = Game.STATE.Game;
				Game.LEVEL = 1;
				HUD.HEALTH = 200;
			}
		}
		
		if(Game.currentState == Game.STATE.Help) {
			if(mouseOver(MX, MY, 200, 500, width, height))Game.currentState = Game.STATE.Menu;
		}

	
	}

	public void mouseMoved(MouseEvent e) {
		int mx = e.getX();
		int my = e.getY();
	
	if(Game.currentState == Game.STATE.Menu) {
		if (mouseOver(mx, my, 200, 200, width, height))
			Top = Color.blue;
		else
			Top = Color.white;

		if (mouseOver(mx, my, 200, 350, width, height))
			middle = Color.green;
		else
			middle = Color.white;

		if (mouseOver(mx, my, 200, 500, width, height))
			bottom = Color.red;
		else
			bottom = Color.white;
	}
	
	if(Game.currentState == Game.STATE.Help) {
		if(mouseOver(mx, my, 200, 500, width, height)) 
			helpColor = Color.blue;
		else
			helpColor = Color.white;
	}
		
	}

}
