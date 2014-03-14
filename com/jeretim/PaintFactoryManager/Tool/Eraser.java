package com.jeretim.PaintFactoryManager.Tool;

import java.awt.*;
import java.awt.image.*;

public class Eraser extends Tool
{
	public Eraser(int radius) {
		this.radius = radius;
	}
	
	public void trace(BufferedImage img, Point src, Point dest) {
		Graphics2D drawable = img.createGraphics();
		drawable.setPaint(Color.white);
		drawable.fillRect(src.x - radius/2, src.y - radius/2, radius, radius);
	}
}
