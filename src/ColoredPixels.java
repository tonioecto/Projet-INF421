import java.awt.Color;
import java.awt.Polygon;

public class ColoredPixels {
	int[] xCoords;
	int[] yCoords;
	int size;
	Color color;
	
	public ColoredPixels(int[] x, int y[], Color color){
		this.xCoords=x;
		this.yCoords=y;
		this.size=x.length;
		this.color=color;
	}
}
