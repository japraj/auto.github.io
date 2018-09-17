package auto.logic;

import javax.swing.JTextField;

import org.jnativehook.keyboard.NativeKeyEvent;

import auto.display.DialougeBox;

public class DialougeUpdater implements Runnable {
	
	private Clicker clicker;
	private Typer typer;
	private DialougeBox dialougeBox;
	private JTextField textField;
	private boolean running;
	private Thread thread;
	
	private int type;
	
	public DialougeUpdater(Clicker clicker, DialougeBox dialougeBox, JTextField textField, int type) {
		this.clicker = clicker;
		this.dialougeBox = dialougeBox;
		this.textField = textField;
		running = false;
		
		this.type = type;
	}
	
	public DialougeUpdater(Typer typer, DialougeBox dialougeBox, JTextField textField, int type) {
		this.typer = typer;
		this.dialougeBox = dialougeBox;
		this.textField = textField;
		running = false;
		
		this.type = type;
	}
	
	public synchronized void start () {
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
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void run() {
		while (running) {
			if (dialougeBox.isActive()) {
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			} else {
				if (type == 1) {
					clicker.setShortcutKeyCode(dialougeBox.getKeyCode());
					textField.setText(NativeKeyEvent.getKeyText(clicker.getShortcutKeyCode()));
					clicker.stop();
					stop();
				} else if (type == 2) {
					typer.setShortcutKeyCode(dialougeBox.getKeyCode());
					textField.setText(NativeKeyEvent.getKeyText(typer.getShortcutKeyCode()));
					typer.stop();
					stop();
				}
			}
		}
		stop();
	}
}
