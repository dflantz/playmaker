import java.awt.Color;
import java.awt.Point;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Queue;
import java.util.concurrent.ExecutionException;

import javax.swing.SwingWorker;

public class Character extends SwingWorker implements Serializable{
	private boolean position;
	private int xLoc;
	private int yLoc;
	private Color color;
	private ArrayList<Point> action;
	private boolean delete;
	public boolean done = false;
	//private Shape shape;
	public Character(){
		super();
	}
	
	public Character(boolean position, int xLoc, int yLoc, Color color){
		this.position = position;
		this.xLoc = xLoc;
		this.yLoc = yLoc;
		this.color = color;
		delete = false;
		action = new ArrayList<Point>();
	}

	public Character(int xLoc, int yLoc, ArrayList<Point> action, Color color) {
		this.xLoc = xLoc;
		this.yLoc = yLoc;
		this.action = action;
		this.color = color;
	}
	
	public Character(int xLoc, int yLoc, Color color) {
		this.xLoc = xLoc;
		this.yLoc = yLoc;
		action = new ArrayList<Point>();
		this.color = color;
	}

	public boolean isDelete() {
		return delete;
	}

	public void setDelete(boolean delete) {
		this.delete = delete;
	}

	public boolean isPosition() {
		return position;
	}

	public int getxLoc() {
		return xLoc;
	}

	public int getyLoc() {
		return yLoc;
	}

	public Color getColor() {
		return color;
	}

	public ArrayList<Point> getAction() {
		return action;
	}

	public void setPosition(boolean position) {
		this.position = position;
	}

	public void setxLoc(int xLoc) {
		this.xLoc = xLoc;
	}

	public void setyLoc(int yLoc) {
		this.yLoc = yLoc;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public void setAction(ArrayList<Point> action){
		this.action = action;
	}

	public Point getLast(){
		if(action.size()==0)
			return new Point(xLoc, yLoc);
		else
			return action.get(action.size()-1);
	}
	
	public Point getFirst(){
		if(action.size()==0)
			return new Point(xLoc, yLoc);
		else
			return action.get(0);
	}
	public void setPoint(Point point){
		this.xLoc = point.x;
		this.yLoc = point.y;
	}

	public boolean getDone(){
		return done;
	}

	@Override
	protected Object doInBackground() throws Exception {
		//System.out.println("\t\tEXECUTING");
		for(int i=0; i<action.size(); i++){	
				//System.out.println(action.get(i));
				setPoint(action.get(i));
				Thread.sleep(50);
		}
		done = true;
		
		return true;
	}

	@Override
	protected void done() {
		//System.out.println("Done");
		if (GamePlay.isSimulating) {
			GamePlay.sameStage();
		} else
			GamePlay.initNextStage(this);
		
	}

	public String toString(){
		return "("+this.xLoc+","+this.yLoc+")";
	}

}
