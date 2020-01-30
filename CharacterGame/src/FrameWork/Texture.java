package FrameWork;

import java.awt.image.BufferedImage;

public class Texture {

	SpriteSheet bs, ps;
	private BufferedImage blockSheet = null;
	private BufferedImage playerSheet = null;
	
	public BufferedImage[] block = new BufferedImage[3];
	public BufferedImage[] player = new BufferedImage[1];
	
	public Texture() {
		
		BufferedImageLoader loader = new BufferedImageLoader();
		
		blockSheet = loader.loadImage("/test.png");
		playerSheet = loader.loadImage("/player_sheet.png");
		
		bs = new SpriteSheet(blockSheet);
		ps = new SpriteSheet(playerSheet);
		
		getTextures();
		
	}
	
	private void getTextures() {
		block[0] = bs.grabImage(1, 1, 32, 32); //dirt block
		block[1] = bs.grabImage(2, 1, 32, 32); //grass block
		
		player[0] = ps.grabImage(1, 1, 32, 64);
	}
	
}
