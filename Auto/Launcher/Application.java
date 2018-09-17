package auto.launcher;

import java.awt.EventQueue;

import javax.swing.UIManager;

import auto.logic.Handler;

public class Application {

	public static void main (String [] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
					new Handler();
				} catch (Exception e) {
					e.printStackTrace();
					System.exit(1); 
				}
			}
		});
	}
}