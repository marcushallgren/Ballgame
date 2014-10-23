package se.nackademin.projektbollspel;

import java.applet.Applet;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.VolatileImage;

public class Game extends Applet implements Runnable, KeyListener {

	int DEATH = 1;
	double hinder_x = 600, hinder_y = 325;
	int RADIEF = 40;
	int points = 0;
	double targetx = 200, targety = 200;
	VolatileImage bgi;
	int WALL_RIGHT = 1210;
	int HEIGHT = 760;
	int RADIE = 20;
	double myBall_x = 50, myBall_y = 100;
	double velocity_x = 0, velocity_y = 0;
	boolean leftKeyPress = false, rightKeyPress = false, upKeyPress = false,
			downKeyPress = false;

	public void init() {
		addKeyListener(this);
		requestFocus();
	}

	public void paint(Graphics g) {
		g.drawRect(10, 10, 1200, 750);
		g.setColor(Color.LIGHT_GRAY);
		g.fillRect(11, 11, 1199, 749);

		/*
		 * g.setColor(Color.ORANGE); g.fillRect(755, 17, 450, 200);
		 */
		g.setColor(Color.black);
		g.fillOval((int) hinder_x - RADIEF, (int) hinder_y - RADIEF,
				2 * RADIEF, 2 * RADIEF);
		g.setColor(Color.green);
		g.fillOval((int) targetx - RADIE, (int) targety - RADIE, 2 * RADIE,
				2 * RADIE);
		g.setColor(Color.blue);
		g.fillOval((int) (myBall_x - RADIE), (int) (myBall_y - RADIE),
				2 * RADIE, 2 * RADIE);
		if (DEATH == 1338) {
			g.setColor(Color.black);
			g.fillRect(10, 10, 1200, 750);
			g.setColor(Color.red);
			g.drawString("YOU FAIL, bara " + points
					+ " poäng. Better luck next time", 400, 300);
		}
	}

	public void update(Graphics g) {
		if (bgi == null) {
			bgi = createVolatileImage(1600, 1200);
			this.requestFocus();
		}
		bgi.getGraphics().clearRect(0, 0, 1600, 1200);
		paint(bgi.getGraphics());
		g.drawImage(bgi, 0, 0, this);
	}

	public void start() {
		Thread ny = new Thread(this);
		ny.start();
	}

	public void run() {

		while (true) {
			double dx = targetx - myBall_x;
			double dy = targety - myBall_y;
			double distSqr = dx * dx + dy * dy;
			if (distSqr < (RADIE + RADIE) * (RADIE + RADIE)) {
				points = points + 1;
				showStatus(points + " IS YOUR SCORE!!!");
				RADIEF = RADIEF + 5;
				do {
					hinder_x = RADIE + (1200 - 2 * RADIE) * Math.random();
					hinder_y = RADIE + (400 - 2 * RADIE) * Math.random();
				} while (Math.abs(hinder_x - myBall_x) < (RADIEF + 5) + RADIE
						&& Math.abs(hinder_y - myBall_y) < (RADIEF + 5) + RADIE);

				do {
					targetx = RADIE + (1200 - 2 * RADIE) * Math.random();
					targety = RADIE + (400 - 2 * RADIE) * Math.random();
				} while (Math.abs(hinder_x - targetx) < (RADIEF + 5) + RADIE
						&& Math.abs(hinder_y - targety) < (RADIEF + 5) + RADIE);

				if (myBall_x < hinder_x)
					if (myBall_x + 50 > hinder_x)
						if (myBall_y < hinder_y)
							if (myBall_y + 50 > hinder_y) {
								hinder_x = RADIE + (1200 - 2 * RADIE)
										* Math.random();
								hinder_y = RADIE + (400 - 2 * RADIE)
										* Math.random();
							}
				if (myBall_x < hinder_x)
					if (myBall_x + 50 > hinder_x)
						if (myBall_y > hinder_y)
							if (myBall_y - 50 < hinder_y) {
								hinder_x = RADIE + (1200 - 2 * RADIE)
										* Math.random();
								hinder_y = RADIE + (400 - 2 * RADIE)
										* Math.random();
							}
				if (myBall_x > hinder_x)
					if (myBall_x - 50 < hinder_x)
						if (myBall_y < hinder_y)
							if (myBall_y + 50 > hinder_y) {
								hinder_x = RADIE + (1200 - 2 * RADIE)
										* Math.random();
								hinder_y = RADIE + (400 - 2 * RADIE)
										* Math.random();
							}
				if (myBall_x > hinder_x + RADIEF)
					if (myBall_x - 50 < hinder_x + RADIEF)
						if (myBall_y > hinder_y + RADIEF)
							if (myBall_y - 50 < hinder_y + RADIEF) {
								hinder_x = RADIE + (1200 - 2 * RADIE)
										* Math.random();
								hinder_y = RADIE + (400 - 2 * RADIE)
										* Math.random();
							}
				// ------------------------------------------------------------------------------
				if (targetx < hinder_x)
					if (targetx + 50 > hinder_x)
						if (targety < hinder_y)
							if (targety + 50 > hinder_y) {
								hinder_x = RADIE + (1200 - 2 * RADIE)
										* Math.random();
								hinder_y = RADIE + (400 - 2 * RADIE)
										* Math.random();
							}
				if (targetx < hinder_x)
					if (targetx + 50 > hinder_x)
						if (targety > hinder_y)
							if (targety - 50 < hinder_y) {
								hinder_x = RADIE + (1200 - 2 * RADIE)
										* Math.random();
								hinder_y = RADIE + (400 - 2 * RADIE)
										* Math.random();
							}
				if (targetx > hinder_x)
					if (targetx - 50 < hinder_x)
						if (targety < hinder_y)
							if (targety + 50 > hinder_y) {
								hinder_x = RADIE + (1200 - 2 * RADIE)
										* Math.random();
								hinder_y = RADIE + (400 - 2 * RADIE)
										* Math.random();
							}
				if (targetx > hinder_x + RADIEF)
					if (targetx - 50 < hinder_x + RADIEF)
						if (targety > hinder_y + RADIEF)
							if (targety - 50 < hinder_y + RADIEF) {
								hinder_x = RADIE + (1200 - 2 * RADIE)
										* Math.random();
								hinder_y = RADIE + (400 - 2 * RADIE)
										* Math.random();
							}

			}
			double fx = hinder_x - myBall_x;
			double fy = hinder_y - myBall_y;
			double fdiag = (fx * fx) + (fy * fy);
			if (fdiag < (RADIEF + RADIE) * (RADIEF + RADIE)) {
				velocity_x = 0;
				velocity_y = 0;
				DEATH = 1338;
			}

			if (leftKeyPress)
				velocity_x = velocity_x - 0.05;
			if (rightKeyPress)
				velocity_x = velocity_x + 0.05;
			if (upKeyPress)
				velocity_y = velocity_y - 0.05;
			if (downKeyPress)
				velocity_y = velocity_y + 0.05;

			if (myBall_x + RADIE >= WALL_RIGHT)
				velocity_x = 0.5 * -velocity_x - 0.5; // om den slår i väggen
														// "wall_right eller HEIGHT"
														// så sätts den till en
														// viss hastighet
			if (myBall_x - RADIE <= 10)
				velocity_x = 0.5 * -velocity_x + 0.5;
			if (myBall_y + RADIE >= HEIGHT)
				velocity_y = 0.5 * -velocity_y - 0.5;
			if (myBall_y - RADIE <= 10)
				velocity_y = 0.5 * -velocity_y + 0.5;

			if (myBall_x + RADIE >= WALL_RIGHT)
				velocity_x = 0.5 * -velocity_x - 0.5;
			if (myBall_x + RADIE <= 10)
				velocity_x = 0.5 * -velocity_x + 0.5;
			if (myBall_y + RADIE >= HEIGHT)
				velocity_y = 0.5 * -velocity_y - 0.5;
			if (myBall_y - RADIE <= 10)
				velocity_y = 0.5 * -velocity_y + 0.5;

			myBall_x = myBall_x + velocity_x;
			myBall_y = myBall_y + velocity_y;
			repaint(); // anropar metoden repaint, som "ritar" om bollen på den
						// nya platsen den befinner sig på

			try {
				Thread.sleep(10);
			} catch (InterruptedException ie) {
			}
		}
	}

	public void keyPressed(KeyEvent key) {
		if (key.getKeyCode() == KeyEvent.VK_LEFT) // när någon knapp hålls in så
													// ska den öka med sig
													// själv, se raderna 165-185
			leftKeyPress = true;
		if (key.getKeyCode() == KeyEvent.VK_RIGHT)
			rightKeyPress = true;
		if (key.getKeyCode() == KeyEvent.VK_UP)
			upKeyPress = true;
		if (key.getKeyCode() == KeyEvent.VK_DOWN)
			downKeyPress = true;
	}

	public void keyReleased(KeyEvent key) {
		if (key.getKeyCode() == KeyEvent.VK_LEFT) // när någon knapp släpps så
													// ska inte längre farten
													// öka
			leftKeyPress = false;
		if (key.getKeyCode() == KeyEvent.VK_RIGHT)
			rightKeyPress = false;
		if (key.getKeyCode() == KeyEvent.VK_UP)
			upKeyPress = false;
		if (key.getKeyCode() == KeyEvent.VK_DOWN)
			downKeyPress = false;
	}

	@Override
	public void keyTyped(KeyEvent e) {

	}
}

