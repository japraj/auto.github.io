package auto.logic;

import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.util.Random;

import org.jnativehook.keyboard.NativeKeyEvent;

public class Typer implements Runnable {

	private Thread thread;
	private boolean running;
	
	private Clipboard clipboard;
	
	private String message;
	
	private int shortcutKeyCode;
	
	private boolean typeAsIs;
	private boolean typeAllUpperCase;
	private boolean typeAllLowerCase;
	
	private boolean enter;
	private boolean alt;
	private boolean shift;
	
	private boolean typeUntilStopped;
	private boolean stopAfterXTypes;
	private int x;
	private int types;
	
	private boolean fixedDelay;
	private int delay;
	private int fixedDelayMultiplier;
	
	private boolean randomDelay;
	private int minimumRandomDelay;
	private int maximumRandomDelay;
	private int randomDelayMultiplier;
	
	private Robot typer;
	
	public Typer() throws Exception {
		clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
		
		running = false;
		
		typer = new Robot();
		
		message = "";
		
		shortcutKeyCode = NativeKeyEvent.VC_F7;
		
		typeAsIs = true;
		typeAllUpperCase = false;
		typeAllLowerCase = false;
		
		enter = false;
		alt = false;
		shift = false;
		
		typeUntilStopped = false;
		stopAfterXTypes = true;
		types = 1;
		x = 0;
		
		fixedDelay = false;
		delay = 1;
		fixedDelayMultiplier = 1;
		randomDelay = false;
		minimumRandomDelay = 1;
		maximumRandomDelay = 1;
		randomDelayMultiplier = 1;
	}
	 
	public void run() {
		Random random = new Random();
		x = types;
		setClipboard();
		while(running) {
			if (!running) {
				break;
			}
			if (!typeUntilStopped && !stopAfterXTypes) {
				break;
			}
			if (stopAfterXTypes) {
				if (x <= 0) {
					break;
				}
				x--;
			}
			if (alt) {
				//typer.keyPress(KeyEvent.VK_ALT);
			}
			if (shift) {
				//typer.keyPress(KeyEvent.VK_SHIFT);
			}
			sendMessage();
			if (alt) {
				//typer.keyRelease(KeyEvent.VK_ALT);
			}
			if (shift) {
				//typer.keyRelease(KeyEvent.VK_SHIFT);
			}
			
			if (fixedDelay) {
				typer.delay(delay * fixedDelayMultiplier);
			} else if (randomDelay) {
				typer.delay((random.nextInt((maximumRandomDelay + 1) - minimumRandomDelay) + minimumRandomDelay) * randomDelayMultiplier);
			}
		}
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
	
	private void setClipboard() {
		String message = this.message;
		if (typeAsIs) {
			
		} else if (typeAllUpperCase) {
			message.toUpperCase();
		} else if (typeAllLowerCase) {
			message.toLowerCase();
		}
		clipboard.setContents(new StringSelection(message), null);
	}
	
	private void sendMessage() {
		typer.keyPress(KeyEvent.VK_CONTROL);
		typer.keyPress(KeyEvent.VK_V);
		typer.keyRelease(KeyEvent.VK_V);
		typer.keyRelease(KeyEvent.VK_CONTROL);
		if (enter) {
			typer.keyPress(KeyEvent.VK_ENTER);
			typer.keyRelease(KeyEvent.VK_ENTER);
		}
	}
	
	public void setMessage(String message) {
		this.message = message;
	}
	
	public String getMessage() {
		return message;
	}

	public void setShortcutKeyCode(int shortcutKeyCode) {
		this.shortcutKeyCode = shortcutKeyCode;
	}
	
	public int getShortcutKeyCode() {
		return shortcutKeyCode;
	}
	
	public void setTypeAsIs(boolean typeAsIs) {
		this.typeAsIs = typeAsIs;
	}
	
	public void setAllCaps(boolean allCaps) {
		this.typeAllUpperCase = allCaps;
	}
	
	public void setAllLower(boolean allLower) {
		this.typeAllLowerCase = allLower;
	}
	
	public void setEnter(boolean enter) {
		this.enter = enter;
	}
	
	public void setAlt(boolean alt) {
		this.alt = alt;
	}
	
	public void setShift(boolean shift) {
		this.shift = shift;
	}
	
	public void setTypeUntilStopped(boolean typeUntilStopped) {
		this.typeUntilStopped = typeUntilStopped;
	}
	
	public void setStopAfterXTypes(boolean stopAfterXTypes) {
		this.stopAfterXTypes = stopAfterXTypes;
	}
	
	public void setTypes(int types) {
		if (types < 0) {
			types = 0;
		} else if (types >= Integer.MAX_VALUE) {
			types = Integer.MAX_VALUE - 1;
		}
		this.types = types;
	}
	
	public void setFixedDelay(boolean fixedDelay) {
		this.fixedDelay = fixedDelay;
	}
	
	public int getFixedDelay() {
		return delay;
	}
	 
	public void setDelay(int delay) {
		if (delay <= 0) {
			delay = 1;
		} if (delay >= Integer.MAX_VALUE) {
			delay = Integer.MAX_VALUE - 1;
		}
		this.delay = delay;
	}
	
	public void setFixedDelayMultiplier(int index) {
		fixedDelayMultiplier = getMultiplier(index);
	}
		
	public void setRandomDelay(boolean randomDelay) {
		this.randomDelay = randomDelay;
	}
	
	public void setMinimumRandomDelay(int minimumRandomDelay) {
		if (minimumRandomDelay <= 0) {
			minimumRandomDelay = 1;
		} else if (minimumRandomDelay >= Integer.MAX_VALUE) {
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
		} else if (maximumRandomDelay >= Integer.MAX_VALUE) {
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
	
	public Robot getTyper() {
		return typer;
	}
	
} 
