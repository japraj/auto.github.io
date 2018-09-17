package auto.display;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.border.TitledBorder;

public class DialougeBox {
	
	private UserInterface window;
	
	private JFrame frmAuto;
	private int keyCode;
	private boolean isActive;
	
	public DialougeBox(UserInterface window) {
		this.window = window;
		initialize();
	}
	
	private void initialize() {
		isActive = true;
		keyCode = 0;
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		frmAuto = new JFrame();
		frmAuto.setTitle("Auto");
		frmAuto.setAlwaysOnTop(true);
		frmAuto.setBounds(100, 100, 317, 92);
		frmAuto.setResizable(false);
		//frmAuto.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmAuto.getContentPane().setLayout(null);
		try {
			frmAuto.setIconImage(ImageIO.read(UserInterface.class.getResource("/images/Mouse.png")));
		} catch (Exception e) {
			
		}
		frmAuto.setFocusable(true);
		frmAuto.setLocationRelativeTo(null);
		frmAuto.setVisible(true);
		
		JPanel dialougeBox = new JPanel();
		dialougeBox.setBackground(Color.LIGHT_GRAY);
		dialougeBox.setBounds(0, 0, 317, 64);
		frmAuto.getContentPane().add(dialougeBox);
		dialougeBox.setLayout(null);
		
		JPanel dialougeBackground = new JPanel();
		dialougeBackground.setBackground(Color.LIGHT_GRAY);
		dialougeBackground.setBorder(new TitledBorder(null, "",TitledBorder.LEADING, TitledBorder.TOP, new Font("Tahoma", Font.PLAIN, 14)));
		dialougeBackground.setBounds(10, 11, 291, 42);
		dialougeBox.add(dialougeBackground);
		dialougeBackground.setLayout(null);
		
		JLabel dialougeLabel = new JLabel("Press Any Button To Set The Shortcut Key");
		dialougeLabel.setBounds(10, 11, 265, 17);
		dialougeBackground.add(dialougeLabel);
		dialougeLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		frmAuto.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				keyCode = window.getLatestKeyPress();
				isActive = false;
				frmAuto.dispose();
			}
		});
	}
	
	public int getKeyCode() {
		return keyCode;
	}
	
	public boolean isActive() {
		return isActive;
	}
	
}
