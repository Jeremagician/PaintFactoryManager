package com.jeretim.PaintFactoryManager.Tool;

import java.awt.*;
import java.awt.image.*;
import java.awt.geom.Line2D;

public class Eraser extends Tool
{
	public Eraser(int radius) {
		this.radius = radius;
	}
	
	public void trace(BufferedImage img, Point src, Point dest) {
		Graphics2D drawable = img.createGraphics();
		drawable.setPaint(Color.white);
		drawable.setStroke(new BasicStroke(radius));
		drawable.draw(new Line2D.Float(src.x, src.y, dest.x, dest.y));
	}

	public Tool clone() {
		return new Eraser(radius);
	}
}
