import java.awt.Color;
import java.awt.Polygon;

public class ColoredPolygon {
	Polygon polygon;
	Color color;
	
	public ColoredPolygon(int[] x, int y[], Color color){
		this.polygon=new Polygon(x, y, x.length);
		this.color=color;
	}
}
