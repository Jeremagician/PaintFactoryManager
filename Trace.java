
import java.util.*;
import java.awt.*;

public class Trace extends ArrayList<Point>
{
	public static enum DrawMode {
		NONE, DRAW, ERASE
	}
	
	DrawMode mode;
	public Trace(DrawMode mode)
	{
		this.mode = mode;
	}
}
