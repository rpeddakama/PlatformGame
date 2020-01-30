package FrameWork;

import java.awt.image.BufferedImage;

public class SpriteSheet {

	private BufferedImage image;
	
	public SpriteSheet(BufferedImage image) {
		this.image = image;
	}
	
	public BufferedImage grabImage(int column, int row, int w, int h) {
		BufferedImage img = image.getSubimage(column*w-w, row*h-h, w, h);
		return img;
	}
}
