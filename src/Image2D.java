import java.awt.*;
import java.util.Collections;
import java.util.LinkedList;
import javax.swing.JComponent;
import javax.swing.JFrame;

// Manipulation for images
public class Image2D {
	private int width; // width of the image
	private int height; // height of the image
	private java.util.List<ColoredCases> coloredCases; // colored polygons in the image
	private java.util.List<Edge> edges; // edges to add to separate polygons

	// Constructor that instantiates an image of a specified width and height
	public Image2D(int width, int height) {
		this.width = width;
		this.height = height;
		coloredCases = Collections.synchronizedList(new LinkedList<ColoredCases>());
		edges = Collections.synchronizedList(new LinkedList<Edge>());
	}

	// Return the width of the image
	public int getWidth() {
		return width;
	}

	// Return the height of the image
	public int getHeight() {
		return height;
	}

	// Return the colored polygons of the image
	public java.util.List<ColoredCases> getColoredCases() {
		return coloredCases;
	}

	// Return the edges of the image
	public java.util.List<Edge> getEdges() {
		return edges;
	}


	public void addCase(int x, int y, Color color, int size) {
		coloredCases.add(new ColoredCases(x, y, color,size));
	}
	
	// Create the edge with coordinates x1, y1, x2, y2
	public void addEdge(int x1, int y1, int x2, int y2, int width, Color color) {
		edges.add(new Edge(x1, y1, x2, y2, width, color));
	}
	
	public void addEdge(Edge e) {
		edges.add(e);
	}
	
	// Clear the picture
	public void clear() {
		coloredCases = Collections.synchronizedList(new LinkedList<ColoredCases>());
		edges = Collections.synchronizedList(new LinkedList<Edge>());		
	}
}

// Image2d component
class Image2dComponent extends JComponent {

	private static final long serialVersionUID = -7710437354239150390L;
	private Image2D img;

	public Image2dComponent(Image2D img) {
		this.img = img;
		setPreferredSize(new Dimension(img.getWidth(), img.getHeight()));
	}
	
	public void paint(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;

		// set the background color
		Dimension d = getSize();
        g2.setBackground(Color.white);
        g2.clearRect(0,0,d.width,d.height);
        
        // draw the polygons
		synchronized (img.getColoredCases()) {
			for (ColoredCases coloredCase : img.getColoredCases()) {
				g2.setColor(coloredCase.color);
				g2.fill(coloredCase.rectangle);
			}
		}
		
		// draw the edges
		//g2.setColor(Color.white);
		synchronized (img.getEdges()) {
			for (Edge edge : img.getEdges()) {
				g2.setColor(edge.color);
                g2.setStroke(new BasicStroke(edge.width));
                g2.drawLine(edge.x1, edge.y1, edge.x2, edge.y2);
			}
		}
	}
}

// Frame for the vizualization
class Image2dViewer extends JFrame {

	private static final long serialVersionUID = -7498525833438154949L;
	static int xLocation = 0;
	Image2D img;
	Image2dComponent img2d;

	public Image2dViewer(Image2D img) {
		this.img = img;
		this.setLocation(xLocation, 0);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		img2d = new Image2dComponent(img);
		add(img2d);
		pack();
		setVisible(true);
		xLocation += img.getWidth();
	}
	

}