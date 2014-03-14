package com.jeretim.PaintFactoryManager;
import java.util.*;
import java.awt.*;
import com.jeretim.PaintFactoryManager.Tool.*;

public class TraceManager
{
	private Stack<Trace> done;
	private Stack<Trace> redo;
	private Trace current;

	private Tool left;
	private Tool right;

	public static enum ToolId {
		LEFT, RIGHT
	}
	
	AireDeDessin aire;
	public TraceManager(AireDeDessin aire, Tool left, Tool right) {
		this.aire = aire;
		done = new Stack<Trace>();
		redo = new Stack<Trace>();
		current = null;
		this.left = left;
		this.right = right;
	}

	public Tool getTool(ToolId id) {
		return id == ToolId.LEFT ? left : right;
	}		

	public void startDraw(ToolId id, int x, int y) {
		current = new Trace(id == ToolId.LEFT ? left : right);
		current.add(new Point(x, y));
		redo.clear();
	}

	public void stopDraw() {
		if(current != null)
		{
			done.push(current);
			current = null;
		}
	}

	public void trace(int x, int y) {
		if(current != null)
		{
			current.getTool().trace(aire.image, current.get(current.size()-1), new Point(x, y));
			current.add(new Point(x, y));
			aire.repaint();
		}
	}
	
	private void redraw() {
		aire.clear();
		for(Trace t : done)
		{
			Point last = null;
			for(Point p : t)
			{
				if(last != null)
				{
					t.getTool().trace(aire.image, last, p);
				}
				last = p;
			}
		}
		aire.repaint();
	}
	
	public void undo() {
		if(current != null)
		{
			stopDraw();
		}
		else
		{
			redo.push(done.pop());
			redraw();
		}
	}

	public void redo() {
		done.push(redo.pop());
		redraw();
	}
	
}
