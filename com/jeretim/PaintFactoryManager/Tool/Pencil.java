package com.jeretim.PaintFactoryManager.Tool;

import java.awt.*;
import java.awt.image.*;
import java.awt.geom.Line2D;

public class Pencil extends Tool
{
	private Color color;
	public Pencil(int radius) {
		this.radius = radius;
		color = Color.BLACK;
	}

	public Pencil(Pencil p) {
		this.radius = p.radius;
		this.color = p.color;
	}

	public Tool clone() {
		return new Pencil(this);
	}
			
	public void setColor(Color color) {
		this.color = color;
	}
	
	public void trace(BufferedImage img, Point src, Point dest) {
		Graphics2D drawable = img.createGraphics();
		drawable.setPaint(color);
		drawable.setStroke(new BasicStroke(radius, BasicStroke.CAP_ROUND,
          BasicStroke.JOIN_ROUND));
		drawable.draw(new Line2D.Float(src.x, src.y, dest.x, dest.y));
	}
}
