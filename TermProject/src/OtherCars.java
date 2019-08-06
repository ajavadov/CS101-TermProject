import acm.graphics.GImage;
import acm.util.RandomGenerator;

/*a separate class for the other cars that come above.*/
public class OtherCars extends GImage implements Runnable {

	public OtherCars() {
		super("car2.png");

	}

	@Override
	public void run() {
		
		/*
		 * ensuring a smooth ride from negative Y coordinate to the
		 * positive(display screen)
		 */
		for (int j = 0; j < 20; j++) {
			
			move(0, 4);
			/* defining random direction that other cars would choose */
			direction = rgen.nextDouble(-20, 20);
			/*
			 * assuring that other cars will stay on the road depending on the
			 * direction they choose 2 distinct assignments are given. *
			 */
			if (direction < 0)
				direction = direction + 10;
			else
				direction = direction - 10;
			pause(30);
		}
		/* the act of changing line on the road */
		for (int i = 0; i < 10; i++) {
			move(direction, 0);
			pause(70);
		}
		pause(50);
		/* then they start to leave the display as our car passes them */
		while (true) {
			move(0, 8);
			pause(PAUSE_TIME);
			direction = rgen.nextDouble(getWidth() / 8, getWidth() * 7 / 20);

		}

	}

	private static final int PAUSE_TIME = 90;

	public RandomGenerator rgen = RandomGenerator.getInstance();
	public double direction;
	public Gcar car;
}
