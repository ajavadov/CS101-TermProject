import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.JButton;

import acm.graphics.GCompound;
import acm.graphics.GLabel;
import acm.graphics.GRect;
import acm.program.GraphicsProgram;
import acm.util.RandomGenerator;

public class Gamepad extends GraphicsProgram {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
    
	/*instance variables*/
	GCompound roadinstance; //for first road
	GCompound roadinstance1;//for the second road which is identical to first,but is used for infinite road.
    
	Gcar car;//for the car
  
	OtherCars carHelper;//for the other cars
	OtherCars carHelper2;//for the other cars

	int manualmove; //used in keylisteners

	int score = 0;//score initializing
    String message="";//initializing the messages that come during the game.
	int speed;//speed.

	public void init() {
       /*adds listeners*/
		addMouseListeners(); 
		addKeyListeners(); 

		/* adds to buttons*/
		add(new JButton("Hard"), SOUTH); 
		add(new JButton("Easy"), SOUTH); 
		addActionListeners(); 
        /* the first road*/
		Road roadMain = new Road(getWidth(), getHeight());
		roadinstance=roadMain;
		add(roadMain, 0, 0);
		/*the second road*/
		Road roadHelper = new Road(getWidth(), getHeight());
		roadinstance1 = roadHelper;
		add(roadHelper, 0, -getHeight());
        /* the Car*/
		car = new Gcar();
		/*scales the car*/
		car.scale(.12);
        /* gets the sizes of car*/  
		double carHeight = car.getHeight();
		double carWidth = car.getWidth();

		// adds car to center of screen
		add(car, getWidth() / 2 - carWidth / 2, getHeight() - 3 * carHeight / 2);
        /*the coordinates of the left sidewalk*/
		double leftBorder = (getWidth() / 4 + getWidth() / 15) - 10;
		double rightBorder = (getWidth() / 4 + getWidth() / 15 + 22 * getWidth() / 60) - 30;
		double carX = 0;
		double carY = 0;
		speed = 1;
        int a=6;//it is 6 , because the game is designed to give the cars from above less frequently than the moving rate of the road in terms of pixels per loop
        while (true) {
			double carHelperX = 0;
			double carHelperWidth = 0;
			double carHelperY= 0;
			double carHelperHeight =0;
						
			
			
			a=a+3;
			carX = car.getX();
			carY = car.getY();
			//hides the previous scores by drawing a filled rectangle on it, so that the written scores do not collide.
			GRect hidePreviousScore = new GRect(0, 0, 180, 60);
			hidePreviousScore.setFilled(true);
			hidePreviousScore.setFillColor(Color.CYAN);
			hidePreviousScore.setColor(Color.CYAN);
			add(hidePreviousScore);
			
			message="Score: "+score;
            if(score>1000&&score<2000)
			message="You are GR8!";
            if(score>3500 && score<4200)
            	message="FANTASTIC!";
            if(score>7000 && score<8500)
            	message="Wow!Insane!!!";
			//the message place
            GLabel scoreBoard = new GLabel(message, 0, 30);
			scoreBoard.setFont("Arial-ITALIC-25");
			scoreBoard.setColor(Color.RED);
			add(scoreBoard);
			//updates of score
			score = (int) (score + carY);
            /* sets the background*/
			GRect seaBackground = new GRect(getWidth(), getHeight());
			seaBackground.setFilled(true);
			seaBackground.setFillColor(RandomGenerator.getInstance().nextColor());
			add(seaBackground);
			//sends back ,so that road is still visible.
			seaBackground.sendToBack();
			//creates the collision detect
			if((carHelperX + carHelperWidth) > car.getX() && car.getX()>carHelperX 
					&& car.getY()<(carHelperY+carHelperHeight)/3 ) 
				break;
			//works for every 3 times of loop
			if(a%9==0){
			/*other cars are created*/
				carHelper= new OtherCars();
			carHelper.scale(.03);
			add(carHelper, getWidth() / 2 - carWidth / 2, -getHeight() / 6);
			//thread is used , so each of them can move independently.
			Thread redSquareThread = new Thread(carHelper);
			Thread ccc = new Thread(carHelper2);
			redSquareThread.start();
			ccc.start();
			}
			
			
			//collision detect
			if((carHelperX + carHelperWidth)+10 > car.getX() && car.getX()>carHelperX-10 
					&& car.getY()<(carHelperY+carHelperHeight)/3 ) 
				{
				break;}
			//this loop creates the infinite road sequence
			for (int i = 0; i < getHeight() / speed; i++) {
				roadMain.move(0, speed);
				
				roadHelper.move(0, speed);
				
				 carHelperX = carHelper.getX();
				 carHelperWidth = carHelper.getWidth();
			     carHelperY= carHelper.getY();
			     carHelperHeight=carHelper.getHeight();
				 
				pause(100);
			}
			roadMain.setLocation(0,0);
			roadHelper.setLocation(0,-getHeight());
			
			System.out.println("" + leftBorder);
			if (car.getX() < leftBorder || car.getX() > rightBorder){
				break;
			}
			
			if((carHelperX + carHelperWidth)+10 > car.getX() && car.getX()>carHelperX-10 
					&& car.getY()<carHelperY+carHelperHeight &&car.getY()>carHelperY) 
				{
				break;
			}
			
		}
//final screen
		GRect finalScreen = new GRect(getWidth(), getHeight());
		finalScreen.setFilled(true);
		finalScreen.setFillColor(Color.BLACK);
		add(finalScreen);
		//writes game over 
		GLabel gameover = new GLabel("Game Over", getWidth() / 5, getHeight() / 2);
		gameover.setFont("Arial-ITALIC-82");
		gameover.setColor(Color.RED);
		add(gameover);
		//provides with finalscore
		GLabel finalscore = new GLabel("Your Score is:" + score, getWidth() / 5, getHeight() / 10);
		finalscore.setFont("Times New Roman-boldITALIC-35");
		finalscore.setColor(Color.RED);
		add(finalscore);

	}
//button actions
	public void actionPerformed(ActionEvent e) {

		if (e.getActionCommand().equals("Hard")) {
			speed = 30;
			manualmove =10;//keyboard action ,actually
		} else if (e.getActionCommand().equals("Easy")) {
			speed = 18;
			manualmove =7;//keyboard action,actually.
		}
	}
//keylisteners
	public void keyPressed(KeyEvent e) {

		if (roadinstance != null || car != null) {
			switch (e.getKeyCode()) {
			case KeyEvent.VK_UP:
				roadinstance.move(0, +manualmove);
				roadinstance1.move(0, +manualmove);
                carHelper.move(0,+manualmove);
				carHelper2.move(0,+manualmove);
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
			case KeyEvent.VK_ESCAPE:
				break;
			}
		}
	}

	public static void main(String[] args) {
		new Gamepad().start();
	}

}
