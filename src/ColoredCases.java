import java.awt.Color;
import java.awt.Polygon;
import java.awt.Rectangle;

public class ColoredCases { // Dans la représentation graphique, chaque case est
							// un rectangle

	Rectangle rectangle;
	Color color;

	public ColoredCases(int x, int y, Color color, int size) {
		this.rectangle = new Rectangle(x, y, size, size);
		this.color = color;
	}
}
