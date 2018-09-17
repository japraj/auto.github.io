package auto.logic;

import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;

import org.jnativehook.GlobalScreen;
import org.jnativehook.keyboard.NativeKeyEvent;
import org.jnativehook.keyboard.NativeKeyListener;
import org.jnativehook.mouse.NativeMouseEvent;
import org.jnativehook.mouse.NativeMouseMotionListener;

import auto.display.UserInterface;

public class Handler implements Runnable {
	
	private Clicker clicker;
	private Typer typer;
	private UserInterface window;
	
	private Thread thread;
	private boolean running;
	
	public Handler() throws Exception {
		running = false;
		clicker = new Clicker();
		typer = new Typer();
		window = new UserInterface(clicker, typer);
		window.getFrame().setVisible(true);
		
		GlobalScreen.registerNativeHook();
		LogManager.getLogManager().reset();
		Logger logger = Logger.getLogger(GlobalScreen.class.getPackage().getName());
		logger.setLevel(Level.OFF);
		GlobalScreen.addNativeKeyListener(new NativeKeyListener() {
			@Override
			public void nativeKeyPressed(NativeKeyEvent e) {
				window.toggleClicker(e.getKeyCode());
				window.toggleTyper(e.getKeyCode());
				window.setLatestKeyPress(e.getKeyCode());	
			}

			@Override
			public void nativeKeyReleased(NativeKeyEvent e) {
				
			}

			@Override
			public void nativeKeyTyped(NativeKeyEvent e) {
				
			}
		});
		
		GlobalScreen.addNativeMouseMotionListener(new NativeMouseMotionListener() {

			@Override
			public void nativeMouseDragged(NativeMouseEvent e) {
				
			}

			@Override
			public void nativeMouseMoved(NativeMouseEvent e) {
				clicker.updateCursorPosition();
				window.getCursorLabel().setText("X = " + clicker.getCursorX() + ", Y = " + clicker.getCursorY());
			}
			
		});
		
		start();
	}

	private synchronized void start () {
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
		try {
			dispose();
			thread.join();

		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public void dispose() {
		window.dispose();
	}
	
	@Override
	public void run() {
		while (running) {
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.exit(4);
	}
	
	public UserInterface getWindow() {
		return window;
	}
	
	public Clicker getClicker() {
		return clicker;
	}
	
	public Typer getTyper() {
		return typer;
	}
	
} 
