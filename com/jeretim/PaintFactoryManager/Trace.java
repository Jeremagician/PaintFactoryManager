package com.jeretim.PaintFactoryManager;
import java.util.*;
import java.awt.*;
import com.jeretim.PaintFactoryManager.Tool.*;

public class Trace extends ArrayList<Point>
{
	// Tool est en privé, comme ça on ne peux le change qu'en début de trace.
	private Tool tool;
	public Tool getTool()
	{
		return tool;
	}
	
	public Trace(Tool tool)
	{
		this.tool = tool;
	}
}
