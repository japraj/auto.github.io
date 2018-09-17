package auto.logic;

import java.awt.MouseInfo;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.util.Random;

import org.jnativehook.keyboard.NativeKeyEvent;

public class Clicker implements Runnable {
	
	private  Thread thread;
	private boolean running;
	
	private Robot clicker;
	
	private int cursorX, cursorY;
	
	private int screenWidth, screenHeight;
	
	private int shortcutKeyCode;
	
	private boolean followCursor;
	private boolean randomLocation;
	private int randomX, randomY, randomWidth, randomHeight;
	
	private int clickKeyCode;
	private boolean doubleClick;
	private int doubleClickDelay;
	private int doubleClickDelayMultiplier;
	
	private boolean clickUntilStopped;
	private boolean stopAfterXClicks;
	private int clicks;
	private int x;
	
	private boolean fixedDelay;
	private int fixedDelayBetweenClicks;
	private int fixedDelayMultiplier;
	private boolean randomDelay;
	private int minimumRandomDelay;
	private int maximumRandomDelay;
	private int randomDelayMultiplier;
	
	public Clicker() throws Exception {
		running = false;
		
		clicker = new Robot();
		
		cursorX = 0;
		cursorY = 0;
		
		screenWidth = (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth();
		screenHeight = (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight();
		
		shortcutKeyCode = NativeKeyEvent.VC_F6;
		
		followCursor = false;
		randomLocation = false;
		randomX = 1;
		randomY = 1;
		randomWidth = screenWidth-1;
		randomHeight = screenHeight-1;
		
		clickKeyCode = MouseEvent.BUTTON1_MASK;
		doubleClick = false;
		doubleClickDelay = 1;
		doubleClickDelayMultiplier = 1;
		
		clickUntilStopped = false;
		stopAfterXClicks = true;
		clicks = 1;
		x = 0;
		
		fixedDelay = false;
		fixedDelayBetweenClicks = 1;
		fixedDelayMultiplier = 1;
		randomDelay = false;
		minimumRandomDelay = 1;
		maximumRandomDelay = 1;
		randomDelayMultiplier = 1;
	}
	
	public void run() {
		Random random = new Random();
		x = clicks;
		while (running) {
			if (!running) {
				break;
			}
			if (stopAfterXClicks) {
				if (x <= 0) {
					break;
				}
				x--;
			} 
			if (!clickUntilStopped && !stopAfterXClicks) {
				break;
			}
			if (followCursor || !randomLocation) {
				updateCursorPosition();
				clicker.mouseMove(cursorX, cursorY);
			} else if (randomLocation) {
				int rX = random.nextInt(randomWidth) + randomX;
				int rY = random.nextInt(randomHeight) + randomY;
				if (rX <= 0 || rX >= screenWidth) {
					updateCursorPosition();
					rX = cursorX;
				} 
				if (rY <= 0 || rY >=  screenHeight) {
					updateCursorPosition();
					rY = cursorY;
				}
				clicker.mouseMove(rX, rY);
			}
			clicker.mousePress(clickKeyCode);
			clicker.mouseRelease(clickKeyCode);
			if (doubleClick) {
				clicker.delay(doubleClickDelay * doubleClickDelayMultiplier);
				clicker.mousePress(clickKeyCode);
				clicker.mouseRelease(clickKeyCode);
			}
			if (fixedDelay) {
				clicker.delay(fixedDelayBetweenClicks * fixedDelayMultiplier);
			} else if (randomDelay) {
				clicker.delay(random.nextInt((maximumRandomDelay + 1) - minimumRandomDelay) + minimumRandomDelay * randomDelayMultiplier);
			} else {
				clicker.delay(1000);
			}
		}
		System.out.print("C loop end");
		stop();
	}
	
	public synchronized void start() {
		if (running) {
			return;
		}
		running = true;
		thread = new Thread(this);
		thread.start();
	}
	
	public synchronized void stop() {
		if (!running) {
			return;
		}
		running = false;
	}
	
	public void toggle() {
		if (running) {
			stop();
		} else {
			start();
		}
	}
	
	public void updateCursorPosition() {
		cursorX = MouseInfo.getPointerInfo().getLocation().x;
		cursorY = MouseInfo.getPointerInfo().getLocation().y;
	}
		
	public int getCursorX() {
		return cursorX;
	}
	
	public int getCursorY() {
		return cursorY;
	}
	
	public int getScreenWidth() {
		return screenWidth;
	}
	
	public int getScreenHeight() {
		return screenHeight;
	}
	
	public void setShortcutKeyCode(int shortcutKeyCode) {
		this.shortcutKeyCode = shortcutKeyCode;
	}

	public int getShortcutKeyCode() {
		return shortcutKeyCode;
	}
	
	public void setFollowCursor(boolean followCursor) {
		this.followCursor = followCursor;
	}
	
	public void setRandomLocation(boolean randomLocation) {
		this.randomLocation = randomLocation;
	}
	
	public void setRandomLocationX(int x) {
		if (x < 0) {
			x = 1;
		}
		if (x >= screenWidth) {
			x = screenWidth - 1;
		}
		randomX = x;
	}
	
	public void setRandomLocationY(int y) {
		if (y < 0) {
			y = 1;
		}
		if (y >= screenHeight) {
			y = screenHeight - 1;
		}
		randomY = y;
	}
	
	public void setRandomLocationWidth(int width) {
		if (width < 0 || width >= screenWidth) {
			width = screenWidth;
		}
		randomWidth = width;
	}
	
	public void setRandomLocationHeight(int height) {
		if (height < 0 || height >= screenHeight) {
			height = screenHeight;
		}
		randomHeight = height;
	}
	
	public int getRandomX() {
		return randomX;
	}
	
	public int getRandomY() {
		return randomY;
	}
	
	public int getRandomWidth() {
		return randomWidth;
	}
	
	public int getRandomHeight() {
		return randomHeight;
	}
	
	public void setClickKeyCode(int clickType) {
		clickKeyCode = clickType;
	} 
	
	public void setDoubleClick (boolean doubleClick) {
		this.doubleClick = doubleClick;
	}
	
	public boolean getDoubleClick() {
		return doubleClick;
	}
	
	public void setDoubleClickDelay(int doubleClickDelay) {
		this.doubleClickDelay = doubleClickDelay;
	}
	
	public int getDoubleClickDelay() {
		return doubleClickDelay;
	}
	
	public void setDoubleClickMultiplier(int index) {
		doubleClickDelayMultiplier = getMultiplier(index);
	}
	
	public void setClickUntilStopped(boolean clickUntilStopped) {
		this.clickUntilStopped = clickUntilStopped;
	}
	
	public void setStopAfterXClicks(boolean stopAfterXClicks) {
		this.stopAfterXClicks = stopAfterXClicks;
	}
	
	public void setClicks(int x) {
		if (x < 0 || x > Integer.MAX_VALUE) {
			x = 0;
		}
		clicks = x;
	}
	
	public int getClicks() {
		return clicks;
	}
	
	public int getX() {
		return clicks;
	}
	
	public void setFixedDelay(boolean fixedDelay) {
		this.fixedDelay = fixedDelay;
	}
	
	public void setRandomDelay(boolean randomDelay) {
		this.randomDelay = randomDelay;
	}
	
	public void setFixedDelayBetweenClicks(int delay) {
		if (delay <= 0) {
			delay = 1;
		}
		fixedDelayBetweenClicks = delay;
	}
	
	public int getFixedDelayBetweenClicks() {
		return fixedDelayBetweenClicks;
	}
	
	public void setFixedDelayMultipler(int index) {
		fixedDelayMultiplier = getMultiplier(index);
	}
	
	public void setMinimumRandomDelay(int minimumRandomDelay) {
		if (minimumRandomDelay <= 0) {
			minimumRandomDelay = 1;
		}
		if (minimumRandomDelay >= Integer.MAX_VALUE) {
			minimumRandomDelay = Integer.MAX_VALUE - 1;
		}
		this.minimumRandomDelay = minimumRandomDelay;
	}
	
	public int getMinimumRandomDelay() {
		return minimumRandomDelay;
	}
	
	public void setMaximumRandomDelay(int maximumRandomDelay) {
		if (maximumRandomDelay <= 0) {
			maximumRandomDelay = 1;
		}
		if (maximumRandomDelay >= Integer.MAX_VALUE) {
			maximumRandomDelay = Integer.MAX_VALUE - 1;
		}
		this.maximumRandomDelay = maximumRandomDelay;
	}
	
	public int getMaximumRandomDelay() {
		return maximumRandomDelay;
	}
	
	public void setRandomDelayMultiplier(int index) {
		randomDelayMultiplier = getMultiplier(index);
	}
	
	private int getMultiplier(int index) {
		int multiplier = 1000;
		if (index == 0) {
			multiplier = 1;
		} else if (index == 1) {
			multiplier = 1000;
		} else if (index == 2) {
			multiplier = 60000;
		}
		return multiplier;
	}
	
	public boolean getState() {
		return running;
	}

	public Robot getClicker() {
		return clicker;
	}	
	
}

