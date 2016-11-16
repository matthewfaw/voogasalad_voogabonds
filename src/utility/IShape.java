package utility;

public interface IShape {
	
	abstract public double getLength();
	abstract public double getArea();
	abstract public double getDistanceTo(Point p);
	abstract public double getDistanceTo(IShape s);
	abstract public boolean contains(Point p);
	abstract public boolean contains(IShape s);
	abstract public boolean overlaps(IShape s);

}
