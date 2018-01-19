package angia.problems;

import java.awt.Point;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Stack;

public class GrahamScan {
	
	Stack<Point> stack;
	Point[] points;
	
	public GrahamScan(Point[] points) {
		this.stack = new Stack<Point>();
		this.points = points;
	}
	
	private int distSq(Point p1, Point p2){
	    return (p1.x - p2.x)*(p1.x - p2.x) +
	          (p1.y - p2.y)*(p1.y - p2.y);
	}
	
	private int findLowestYCoordinatePosition(){
		int lowestYPoint = 0;
		for(int i=1;i<this.points.length;i++){
			if(this.points[i].y < this.points[lowestYPoint].y){
				this.points[lowestYPoint] = this.points[i];
				break;
			}else if(this.points[i].y == this.points[lowestYPoint].y){
				if (this.points[i].x < this.points[lowestYPoint].x){
					this.points[lowestYPoint] = this.points[i];
					break;
				}
			}
		}
		return lowestYPoint;
	}
	
	private Point[] sortBasedOnPolarAngle(){
		Point[] clonedPoint = this.points.clone();
		final Point p0 = clonedPoint[0];
		Arrays.sort(clonedPoint, 1, this.points.length-1, new Comparator<Point>() {
			public int compare(Point o1, Point o2) {
				int orientation = findOrientation(p0, o1, o2);
				if (orientation == 0) {
					return distSq(p0, o1) > distSq(p0, o2) ? 1 : -1; 
				}
				return orientation == 2 ? -1 : 1;
			}
		});
		return clonedPoint;
	}
	
	private int cmpre(Point o1, Point o2){
		return 0;
	}
	
	public int findOrientation(Point origin,Point source,Point dest){
		int val = (source.y - origin.y) * (dest.x - source.x) - 
					(source.x - origin.x) * (dest.y - source.y);
		if(val==0) return 0;
		return (val>0?1:2);
	}
	
	private void swap(int p1, int p2)
	{
	    Point temp = this.points[p1];
	    this.points[p1] = this.points[p2];
	    this.points[p2] = temp;
	}
	
	public Point[] getConvexHull(){
		int ymin = findLowestYCoordinatePosition();
		this.swap(0, ymin);
		Point[] sortedPoints = this.sortBasedOnPolarAngle();
		
		this.stack.push(startingPoint);
		return points;
	}
	

}
