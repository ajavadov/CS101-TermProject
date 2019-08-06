import java.awt.Color;

import acm.graphics.GCompound;
import acm.graphics.GImage;
import acm.graphics.GRect;

/* a separate class for the road */
public class Road extends GCompound {
	/**
	 * draws road
	 * 
	 * @param width
	 *            width of the main screen.
	 * @param height
	 *            height of the main screen.
	 */
	public Road(double width, double height) {

		/* left sidewalk */
		GRect roadSideWalkLeft = new GRect(width / 15, 2 * height);
		roadSideWalkLeft.setFilled(true);
		roadSideWalkLeft.setColor(Color.DARK_GRAY);
		add(roadSideWalkLeft, width / 4, -height);

		/* right sidewalk */
		GRect roadSideWalkRight = new GRect(width / 15, 2 * height);
		roadSideWalkRight.setFilled(true);
		roadSideWalkRight.setColor(Color.DARK_GRAY);
		add(roadSideWalkRight, width * 7 / 10, 2 - height);

		/* main road */
		GRect roadMain = new GRect(23 * width / 60, 2 * height); // a:4+a:15=19a:60.
														// 7a:10-19a:60=23a:60.(width
														// of grey rect.)
		roadMain.setFilled(true);
		roadMain.setColor(Color.GRAY);
		add(roadMain, 19 * width / 60, 2 - height);// a:4+a:15=19a:60.

		/* trees on the sidewalks */

		GImage tree = new GImage("tree.png");
		tree.scale(.17);
		add(tree, width / 4, 90);
		GImage tree1 = new GImage("tree1.png");
		tree1.scale(.17);
		add(tree1, width * 7 / 10, 90);
		GImage tree2 = new GImage("tree2.png");
		tree2.scale(.17);
		add(tree2, width / 4, 10);
		GImage tree3 = new GImage("tree.png");
		tree3.scale(.17);
		add(tree3, width * 7 / 10, 29);

	}

}
