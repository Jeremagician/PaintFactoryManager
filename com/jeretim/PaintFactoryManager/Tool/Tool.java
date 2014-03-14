package com.jeretim.PaintFactoryManager.Tool;

import java.awt.*;
import java.awt.image.*;

public abstract class Tool
{
	public int radius;
	abstract public void trace(BufferedImage img, Point src, Point dest);
}
