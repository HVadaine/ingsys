package entity;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class Environment {
	private HashMap<Integer,Line> lineList;
	private HashMap<Integer,Cross> crossList;
	
	public Environment()
	{
		lineList =new HashMap<>();
		crossList = new HashMap<>();
	}
	
	public void addLine(Line line)
	{
		lineList.put(line.getId(), line);
	}
	
	public void addCross(Cross cross)
	{
		crossList.put(cross.getId, cross);
	}
	
	public Line getLine(int id)
	{
		return lineList.get(id);
	}
	
	public Cross getCross(int id)
	{
		return crossList.get(id);
	}
	
}
