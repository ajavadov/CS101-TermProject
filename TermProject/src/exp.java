

	import java.awt.Color;
	import java.awt.event.ActionEvent;
	import java.awt.event.KeyEvent;

	import javax.swing.JButton;

	import acm.graphics.GCompound;
	import acm.graphics.GLabel;
	import acm.graphics.GPoint;
	import acm.graphics.GRect;
	import acm.program.GraphicsProgram;

	public class exp extends GraphicsProgram {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		private GPoint last; /* The last mouse position */

		private double leftBorder = (getWidth() / 4 + getWidth() / 15) - 10;
		private double rightBorder = (getWidth() / 4 + getWidth() / 15 + 22 * getWidth() / 60) - 30;

		GCompound roadinstance;

		Gcar car;

		OtherCars Car2;

		int manualmove;

		int score = 0;

		int speed;

		public void init() {

			addMouseListeners(); // ok
			addKeyListeners(); // ok

			add(new JButton("Insane"), SOUTH); // ok
			add(new JButton("Hard"), SOUTH); // ok
			add(new JButton("Easy"), SOUTH); // ok

			addActionListeners(); // ok

			Road roadMain = new Road(getWidth(), getHeight());
			add(roadMain, 0, 0);

			Road roadHelper = new Road(getWidth(), getHeight());
			roadinstance = roadHelper;
			add(roadHelper, 0, -getHeight());

			car = new Gcar();
			;
			car.scale(.12);

			double carHeight = car.getHeight();
			double carWidth = car.getWidth();

			// adds car to center of screen
			add(car, getWidth() / 2 - carWidth / 2, getHeight() - 3 * carHeight / 2);

			double leftBorder = (getWidth() / 4 + getWidth() / 15) - 10;
			double rightBorder = (getWidth() / 4 + getWidth() / 15 + 22 * getWidth() / 60) - 30;
			speed = 1;
			int a = (int) (car.getY());
			System.out.println(a);
			int b = 1;
			int c = (int) (car.getX());
			// int d=1;
			int e = 0;
			int f = 0;
			int g = 0;
			int l = 0;
			int j = 0;
			int k = 0;
			int m = 0;
			int d = c;

			while (true) {

				d = (int) (car.getX());

				GRect hidepreviousscore = new GRect(73, 0, 120, 60);
				hidepreviousscore.setFilled(true);
				hidepreviousscore.setFillColor(Color.GRAY);
				hidepreviousscore.setColor(Color.GRAY);
				add(hidepreviousscore);

				GLabel scoreboard = new GLabel("Score:" + score, 0, 30);
				scoreboard.setFont("Arial-ITALIC-25");
				scoreboard.setColor(Color.YELLOW);
				add(scoreboard);
				score = (int) (score + car.getY());

				if (car.getX() < leftBorder || car.getX() > rightBorder)
					break;

				GRect backg = new GRect(getWidth(), getHeight());
				if (car.getX() < leftBorder || car.getX() > rightBorder)
					break;

				backg.sendBackward();
				if (car.getX() < leftBorder || car.getX() > rightBorder)
					break;

				backg.setFilled(true);

				backg.setFillColor(Color.GRAY);

				add(backg);
				backg.sendToBack();

				OtherCars car2 = new OtherCars();
				car2.scale(.03);
				Car2 = car2;
				d = (int) Car2.getX();
				add(Car2, getWidth() / 2 - carWidth / 2, -getHeight() / 6);
				Thread redSquareThread = new Thread(car2);
				redSquareThread.start();
				double width = Car2.getWidth();
				double height = Car2.getHeight();

				// if(car.getX()> b && car2.getX()<b+width &&
				// car.getY()<a&&car.getY()>a-height )
				// break;
				// burda bezi ifler var ki onlari yoxlamaq ucun yazmisdim meselen
				// alttaki , ise yaramirlar.
				// aydindir, prosta baxiram ki ne vaxt break ler verilir,yaxsi
				if ((int) car2.getY() == 361) {
					break;
				}
				for (int i = 0; i < getHeight() / speed; i++) {
					roadMain.move(0, speed);
					b = b + 8;
					System.out.println(b);
					if ((a == b))
						break;
					if (car.getX() < leftBorder || car.getX() > rightBorder)
						break;
					roadHelper.move(0, speed);
					if (car.getX() < leftBorder || car.getX() > rightBorder)
						break;
					pause(100);
					if (car.getX() < leftBorder || car.getX() > rightBorder)
						break;
				}
				if ((int) Car2.getY() == 361)
					break;
				b = 0;
				if (car.getX() < leftBorder || car.getX() > rightBorder)
					break;
				roadMain.setLocation(0, -getHeight());
				if (car.getX() < leftBorder || car.getX() > rightBorder)
					break;
				roadHelper.setLocation(0, 0);
				if (car.getX() < leftBorder || car.getX() > rightBorder)
					break;
				int o = (int) (car.getX());
			}

			GRect finalScreen = new GRect(getWidth(), getHeight());
			finalScreen.setFilled(true);
			finalScreen.setFillColor(Color.BLACK);
			add(finalScreen);
			
			GLabel gameover = new GLabel("Game Over", getWidth() / 5, getHeight() / 2);
			gameover.setFont("Arial-ITALIC-82");
			gameover.setColor(Color.RED);
			add(gameover);
			
			GLabel finalscore = new GLabel("Your Score is:" + score, getWidth() / 5, getHeight() / 10);
			finalscore.setFont("Times New Roman-boldITALIC-35");
			finalscore.setColor(Color.RED);
			add(finalscore);

		}

		public void actionPerformed(ActionEvent e) {

			if (e.getActionCommand().equals("Hard")) {
				speed = 30;
				manualmove = 12;
			} else if (e.getActionCommand().equals("Easy")) {
				speed = 5;
				manualmove = 4;
			} else if (e.getActionCommand().equals("Insane")) {
				speed = 45;
				manualmove = 17;
			}
		}

		public void keyPressed(KeyEvent e) {

			if (roadinstance != null || car != null) {
				switch (e.getKeyCode()) {
				case KeyEvent.VK_UP:
					roadinstance.move(0, +manualmove);
					break;
				case KeyEvent.VK_DOWN:
					roadinstance.move(0, -manualmove);
					break;
				case KeyEvent.VK_LEFT:
					car.move(-3, 0);
					break;
				case KeyEvent.VK_RIGHT:
					car.move(+3, 0);
					break;
				}
			}
		}

		public static void main(String[] args) {
			new Gamepad().start();
		}

	

}
