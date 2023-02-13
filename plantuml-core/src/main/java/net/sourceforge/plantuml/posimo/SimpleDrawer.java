package net.sourceforge.plantuml.posimo;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.Collection;

import net.sourceforge.plantuml.awt.geom.XDimension2D;
import net.sourceforge.plantuml.awt.geom.XPoint2D;

public class SimpleDrawer {

	private final Cluster root;
	private final Collection<Path> paths;

	public SimpleDrawer(Cluster root, Collection<Path> paths) {
		this.root = root;
		this.paths = paths;
	}

	public void draw(Graphics2D g2d) {
		g2d.setColor(Color.BLACK);
		for (Clusterable cl : root.getContents()) {
			final Block b = (Block) cl;
			final XPoint2D pos = b.getPosition();
			final XDimension2D dim = b.getSize();
			// drawRectCentered(g2d, pos, dim);
			drawRect(g2d, pos, dim);
		}

		g2d.setColor(Color.GREEN);
		for (Path p : paths) {
			final Label label = p.getLabel();
			final XPoint2D labelPos = label.getPosition();
			final XDimension2D labelDim = label.getSize();
			// final double x1 = labelPos.getX();
			// final double y1 = labelPos.getY();
			// g2d.draw(new Ellipse2D.Double(x1 - 1, y1 - 1, 3, 3));
			// drawRectCentered(g2d, labelPos, labelDim);
			drawRect(g2d, labelPos, labelDim);
		}

		g2d.setColor(Color.RED);
		for (Path p : paths) {
			p.getDotPath().draw(g2d, 0, 0);
		}

		for (Cluster sub : root.getSubClusters()) {
			new SimpleDrawer(sub, new ArrayList<Path>()).draw(g2d);
		}

	}

	private void drawRectCentered(Graphics2D g2d, final XPoint2D pos, final XDimension2D dim) {
		final Rectangle2D rect = new Rectangle2D.Double(pos.getX() - dim.getWidth() / 2, pos.getY() - dim.getHeight()
				/ 2, dim.getWidth(), dim.getHeight());
		g2d.draw(rect);
	}

	private void drawRect(Graphics2D g2d, final XPoint2D pos, final XDimension2D dim) {
		final Rectangle2D rect = new Rectangle2D.Double(pos.getX(), pos.getY(), dim.getWidth(), dim.getHeight());
		g2d.draw(rect);
	}
}
