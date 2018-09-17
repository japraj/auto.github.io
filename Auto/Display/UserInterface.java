package auto.display;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Desktop;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSeparator;
import javax.swing.JSpinner;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;

import org.jnativehook.keyboard.NativeKeyEvent;

import auto.logic.Clicker;
import auto.logic.DialougeUpdater;
import auto.logic.Typer;

public class UserInterface{

	private JFrame frame;
	private Clicker c;
	private Typer t;
		
	private int latestKeyPress;
	
	private List<Component> clickerComponents = new ArrayList<Component>();
	private List<Component> typerComponents = new ArrayList<Component>();

	private String[] delay;

	private ButtonGroup setClickPositionGroup;
	private ButtonGroup amountOfClicksGroup;
	private ButtonGroup clickTypeGroup;
	private ButtonGroup clickerDelayGroup;
	private ButtonGroup typeUntilGroup;
	private ButtonGroup capitalizationGroup;
	private ButtonGroup typerDelayGroup;
	private ButtonGroup repitition;

	private JPanel sideBar;
	private JPanel autoTyperMenuButton;
	private JPanel autoClickerMenuButton;
	private JLabel lblAutomouse;
	private JLabel lblAutotyper;
	private JPanel clicker;
	private JPanel cursorPositionPanel;
	private JLabel cursorPositionLabel;
	private JPanel screenSizePanel;
	private JLabel screenSizeLabel;
	private JPanel setClickPosition;
	private JRadioButton followCursor;
	private JRadioButton randomLocationWithinSpecifiedArea;
	private JLabel labelX;
	private JSpinner setClickX;
	private JLabel labelY;
	private JSpinner setClickY;
	private JLabel labelWidth;
	private JSpinner setWidth;
	private JLabel labelHeight;
	private JSpinner setHeight;
	private JPanel amountOfClicks;
	private JRadioButton clickUntilStopped;
	private JRadioButton stopAfter;
	private JLabel clicksLabel;
	private JSpinner amountOfClicksUntilStopped;
	private JPanel clickType;
	private JRadioButton leftClick;
	private JRadioButton rightClick;
	private JRadioButton middleClick;
	private JCheckBox doubleClick;
	private JSeparator clickTypeSeparator;
	private JSpinner doubleClickdelay;
	private JLabel lblDelay;
	private JComboBox<?> doubleClickDelayMultiplier;
	private JPanel delayBetweenClicks;
	private JRadioButton fixedDelay;
	private JComboBox<?> randomDelayMultiplier;
	private JSpinner maximumRandomDelay;
	private JRadioButton randomDelay;
	private JLabel betweenLabel;
	private JSpinner minimumRandomDelay;
	private JLabel lblAnd;
	private JSpinner fixedDelaySpinner;
	private JComboBox<?> fixedDelayMultiplier;
	private JPanel shortcut;
	private JTextField shortcutTextField;
	private JButton assignShortcut;
	private JPanel misc;
	private JButton startButton;
	private JButton feedbackButton;
	private JPanel typer;
	private JPanel messagePanel;
	private JTextArea messageBox;
	private JPanel delayPanel;
	private JRadioButton typerFixedDelay;
	private JComboBox<?> typerFixedDelayMultiplier;
	private JSpinner typerDelay;
	private JRadioButton typerRandomDelay;
	private JLabel labelBetween;
	private JSpinner typerRandomMin;
	private JLabel labelAnd;
	private JSpinner typerRandomMax;
	private JComboBox<?> typerRandomMultiplier;
	private JSeparator typerDelaySeparator;
	private JPanel capitalization;
	private JRadioButton typeAsItIs;
	private JRadioButton typeAllCaps;
	private JRadioButton typeAllLower;
	private JPanel enterShiftAlt;
	private JCheckBox enter;
	private JCheckBox shift;
	private JCheckBox alt;
	private JPanel typerMisc;
	private JButton typerStartStop;
	private JButton typerFeedBack;
	private JPanel typerShortcut;
	private JTextField typerShortcutText;
	private JButton typerAssign;
	private JPanel repititionsPanel;
	private JRadioButton typeTilStopped;
	private JRadioButton stopAfterTyping;
	private JSpinner stopAfterXTimes;
	private JLabel timesLabel;

		
	public UserInterface(Clicker c, Typer t) {
		this.c = c;
		this.t= t;
		
		latestKeyPress = 0;
		
		delay = new String[] { "Milliseconds", "Seconds", "Minutes"};
		
		setClickPositionGroup = new ButtonGroup ();
		amountOfClicksGroup = new ButtonGroup();
		clickTypeGroup = new ButtonGroup();
		clickerDelayGroup = new ButtonGroup();
		typeUntilGroup = new ButtonGroup();
		capitalizationGroup = new ButtonGroup();
		typerDelayGroup = new ButtonGroup();
		repitition = new ButtonGroup();
		
		sideBar = new JPanel();
		autoTyperMenuButton = new JPanel();
		autoClickerMenuButton = new JPanel();
		lblAutomouse = new JLabel("");
		lblAutotyper = new JLabel("");
		clicker = new JPanel();
		cursorPositionPanel = new JPanel();
		c.updateCursorPosition();
		cursorPositionLabel = new JLabel("X = " + c.getCursorX() +", Y = " + c.getCursorY());
		screenSizePanel = new JPanel();
		screenSizeLabel = new JLabel("Width = " + c.getScreenWidth() + ", Height = " + c.getScreenHeight());
		setClickPosition = new JPanel();
		followCursor = new JRadioButton("Follow Cursor");
		randomLocationWithinSpecifiedArea = new JRadioButton("Random Location Within Specified Area:");
		labelX = new JLabel("X:");
		setClickX = new JSpinner(new SpinnerNumberModel(0, 0, Integer.MAX_VALUE, 1));
		labelY = new JLabel("Y:");
		setClickY = new JSpinner(new SpinnerNumberModel(0, 0, Integer.MAX_VALUE, 1));
		labelWidth = new JLabel("Width:");
		setWidth = new JSpinner(new SpinnerNumberModel(0, 0, Integer.MAX_VALUE, 1));
		labelHeight = new JLabel("Height:");
		setHeight = new JSpinner(new SpinnerNumberModel(0, 0, Integer.MAX_VALUE, 1));
		amountOfClicks = new JPanel();
		clickUntilStopped = new JRadioButton("Click Until Stopped");
		stopAfter = new JRadioButton("Stop After ");
		clicksLabel = new JLabel("Clicks");
		amountOfClicksUntilStopped = new JSpinner(new SpinnerNumberModel(0, 0, Integer.MAX_VALUE, 1));
		clickType = new JPanel();
		leftClick = new JRadioButton("Left Click");
		rightClick = new JRadioButton("Right Click");
		middleClick = new JRadioButton("Middle Click");
		doubleClick = new JCheckBox("Double Click");
		clickTypeSeparator = new JSeparator();
		doubleClickdelay = new JSpinner(new SpinnerNumberModel(0, 0, Integer.MAX_VALUE, 1));
		lblDelay = new JLabel("Delay");
		doubleClickDelayMultiplier = new JComboBox<Object>(delay);
		delayBetweenClicks = new JPanel();
		fixedDelay = new JRadioButton("Fixed Delay");
		randomDelayMultiplier = new JComboBox<Object>(delay);
		maximumRandomDelay = new JSpinner(new SpinnerNumberModel(0, 0, Integer.MAX_VALUE, 1));
		randomDelay = new JRadioButton("Random Delay");
		betweenLabel = new JLabel("Between");
		minimumRandomDelay = new JSpinner(new SpinnerNumberModel(0, 0, Integer.MAX_VALUE, 1));
		lblAnd = new JLabel("and");
		fixedDelaySpinner = new JSpinner(new SpinnerNumberModel(0, 0, Integer.MAX_VALUE, 1));
		fixedDelayMultiplier = new JComboBox<Object>(delay);
		shortcut = new JPanel();
		shortcutTextField = new JTextField();
		assignShortcut = new JButton("Assign");
		misc = new JPanel();
		startButton = new JButton("Start/Stop");
		feedbackButton = new JButton("Feedback");
		typer = new JPanel();
		messagePanel = new JPanel();
		messageBox = new JTextArea();
		delayPanel = new JPanel();
		typerFixedDelay = new JRadioButton("Fixed Delay");
		typerFixedDelayMultiplier = new JComboBox<Object>(delay);
		typerDelay = new JSpinner(new SpinnerNumberModel(0, 0, Integer.MAX_VALUE, 1));
		typerRandomDelay = new JRadioButton("Random Delay");
		labelBetween = new JLabel("Between");
		typerRandomMin = new JSpinner(new SpinnerNumberModel(0, 0, Integer.MAX_VALUE, 1));
		labelAnd = new JLabel("and");		
		typerRandomMax = new JSpinner(new SpinnerNumberModel(0, 0, Integer.MAX_VALUE, 1));		
		typerRandomMultiplier = new JComboBox<Object>(delay);		
		typerDelaySeparator = new JSeparator();
		capitalization = new JPanel();		
		typeAsItIs = new JRadioButton("Type As It Is");
		typeAllCaps = new JRadioButton("Type All Capital");		
		typeAllLower = new JRadioButton("Type All Lower Case");
		enterShiftAlt = new JPanel();
		enter = new JCheckBox("Enter");		
		shift = new JCheckBox("Shift");		
		alt = new JCheckBox("Alt");
		typerMisc = new JPanel();		
		typerStartStop = new JButton("Start/Stop");		
		typerFeedBack = new JButton("Feedback");
		typerShortcut = new JPanel();		
		typerShortcutText = new JTextField();		
		repititionsPanel = new JPanel();
		typeTilStopped = new JRadioButton("Type Until Stopped");
		stopAfterTyping = new JRadioButton("Stop After Typing\r\n");
		stopAfterXTimes =  new JSpinner(new SpinnerNumberModel(0, 0, Integer.MAX_VALUE, 1));
		timesLabel = new JLabel("Times");
		
		setClickPositionGroup.add(followCursor);
		setClickPositionGroup.add(randomLocationWithinSpecifiedArea);
		amountOfClicksGroup.add(clickUntilStopped);
		amountOfClicksGroup.add(stopAfter);
		typeUntilGroup.add(typeTilStopped);
		typeUntilGroup.add(stopAfterTyping);
		clickTypeGroup.add(leftClick);
		clickTypeGroup.add(rightClick);
		clickTypeGroup.add(middleClick);
		capitalizationGroup.add(typeAsItIs);
		capitalizationGroup.add(typeAllCaps);
		capitalizationGroup.add(typeAllLower);
		typerDelayGroup.add(typerFixedDelay);
		typerDelayGroup.add(typerRandomDelay);
		repitition.add(stopAfterTyping);
		repitition.add(typeTilStopped);
		
		frame = new JFrame("Auto");
		frame.setBounds(100, 100, 690, 370);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setFocusable(true);
		//frame.setAlwaysOnTop(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		try {
			frame.setIconImage(ImageIO.read(UserInterface.class.getResource("/images/Mouse.png")));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		createComponents(frame.getContentPane());
	}
	
	private void createComponents(Container container) {
		container.setLayout(null);
		
		// Clicker Listeners
		clickUntilStopped.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				c.setClickUntilStopped(true);
				c.setStopAfterXClicks(false);
				amountOfClicksUntilStopped.setEnabled(false);
			}
		});
		
		doubleClick.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!c.getDoubleClick()) {
					c.setDoubleClick(true);
				} else if (c.getDoubleClick()) {
					c.setDoubleClick(false);
				}
			}
		});
		
		middleClick.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				c.setClickKeyCode(MouseEvent.BUTTON2_MASK);
			}
		});
		
		stopAfter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				c.setClickUntilStopped(false);
				c.setStopAfterXClicks(true);
				amountOfClicksUntilStopped.setEnabled(true);
			}
		});
		
		leftClick.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				c.setClickKeyCode(MouseEvent.BUTTON1_MASK);
			}
		});
		
		rightClick.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				c.setClickKeyCode(MouseEvent.BUTTON3_MASK);
			}
		});
		
		randomDelay.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				c.setRandomDelay(true);
				c.setFixedDelay(false);
				fixedDelaySpinner.setEnabled(false);
				fixedDelayMultiplier.setEnabled(false);
				minimumRandomDelay.setEnabled(true);
				maximumRandomDelay.setEnabled(true);
				randomDelayMultiplier.setEnabled(true);
			}
		});
		
		fixedDelay.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				c.setRandomDelay(false);
				c.setFixedDelay(true);
				fixedDelaySpinner.setEnabled(true);
				fixedDelayMultiplier.setEnabled(true);
				minimumRandomDelay.setEnabled(false);
				maximumRandomDelay.setEnabled(false);
				randomDelayMultiplier.setEnabled(false);	
			}
		});
		
		UserInterface buffer = this;
		assignShortcut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DialougeBox dialougeBox = new DialougeBox(buffer);
				DialougeUpdater dialougeUpdater = new DialougeUpdater(c, dialougeBox, shortcutTextField, 1);
				dialougeUpdater.start();
				for (Component component : clickerComponents) {
					component.setFocusable(false);
				}
				for (Component component : clickerComponents) {
					component.setFocusable(true);
				}
			}
		});		
		
		followCursor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				c.setFollowCursor(true);
				c.setRandomLocation(false);
				setClickX.setEnabled(false);
				setClickY.setEnabled(false);
				setWidth.setEnabled(false);
				setHeight.setEnabled(false);
			}
		});		
		
		randomLocationWithinSpecifiedArea.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				c.setFollowCursor(false);
				c.setRandomLocation(true);
				setClickX.setEnabled(true);
				setClickY.setEnabled(true);
				setWidth.setEnabled(true);
				setHeight.setEnabled(true);
			}
		});
		
		startButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				toggleClicker();
			}
		});

		// Typer Listeners
		typeAsItIs.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				t.setTypeAsIs(true);
				t.setAllCaps(false);
				t.setAllLower(false);
			}
		});
		
		typeAllCaps.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				t.setTypeAsIs(true);
				t.setAllCaps(false);
				t.setAllLower(false);
			}
		});
		
		typeAllLower.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				t.setTypeAsIs(true);
				t.setAllCaps(false);
				t.setAllLower(false);
			}
		});
		
		typerFixedDelay.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				t.setFixedDelay(true);
				t.setRandomDelay(false);
				typerFixedDelayMultiplier.setEnabled(true);
				typerDelay.setEnabled(true);
				typerRandomMin.setEnabled(false);
				typerRandomMax.setEnabled(false);
				typerRandomMultiplier.setEnabled(false);
			}
		});
		
		typerRandomDelay.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				t.setFixedDelay(false);
				t.setRandomDelay(true);
				typerFixedDelayMultiplier.setEnabled(false);
				typerDelay.setEnabled(false);
				typerRandomMin.setEnabled(true);
				typerRandomMax.setEnabled(true);
				typerRandomMultiplier.setEnabled(true);
			}
		});
		
		typeTilStopped.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				t.setTypeUntilStopped(true);
				t.setStopAfterXTypes(false);
				stopAfterXTimes.setEnabled(false);
			}
		});
		
		stopAfterTyping.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				t.setTypeUntilStopped(false);
				t.setStopAfterXTypes(true);
				stopAfterXTimes.setEnabled(true);
			}
		});
		
		UserInterface buffer2 = this;
		
		typerStartStop.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				toggleTyper();
			}
		});
		
		// Feedback Action Listeners
		String url = "https://autoclicker-typer.github.io/";
		
		feedbackButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			    try {
			        Desktop.getDesktop().browse(new URL(url).toURI());
			    } catch (Exception ex) {
			        ex.printStackTrace();
			    }
			}
		});
		
		typerFeedBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			    try {
			        Desktop.getDesktop().browse(new URL(url).toURI());
			    } catch (Exception ex) {
			        ex.printStackTrace();
			    }
			}
		});
		
		// SideMenu Panel
		sideBar.setBackground(Color.DARK_GRAY);
		sideBar.setBounds(0, 0, 74, 343);
		frame.getContentPane().add(sideBar);
		sideBar.setLayout(null);
	
		// AutoClicker SideMenu Button
		autoClickerMenuButton.setBorder(null);
		autoClickerMenuButton.setBackground(Color.LIGHT_GRAY);
		autoTyperMenuButton.setBackground(Color.GRAY);
		autoClickerMenuButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				autoClickerMenuButton.setBackground(Color.LIGHT_GRAY);
				autoTyperMenuButton.setBackground(Color.GRAY);
				disableComponents(typerComponents);
				enableComponents(clickerComponents);
			}
		});
		autoClickerMenuButton.setBounds(10, 11, 54, 32);
		sideBar.add(autoClickerMenuButton);
		autoClickerMenuButton.add(lblAutomouse);
		lblAutomouse.setBackground(Color.LIGHT_GRAY);
		try {
			ImageIcon mouseIcon2 = new ImageIcon(ImageIO.read(UserInterface.class.getResource("/images/Mouse2.png")).getScaledInstance(15, 23, java.awt.Image.SCALE_SMOOTH));
			lblAutomouse.setIcon(mouseIcon2);
		} catch (Exception e) {
			lblAutomouse.setText("AutoClicker");
		}
		lblAutomouse.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblAutomouse.setForeground(Color.BLACK);		
		
		// AutoTyper SideMenu Button
		autoTyperMenuButton.setBorder(null);
		autoTyperMenuButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				autoClickerMenuButton.setBackground(Color.gray);
				autoTyperMenuButton.setBackground(Color.LIGHT_GRAY);
				disableComponents(clickerComponents);
				enableComponents(typerComponents);
			}
		});
		autoTyperMenuButton.setBounds(10, 54, 54, 32);
		sideBar.add(autoTyperMenuButton);
		lblAutotyper.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblAutomouse.setBackground(Color.LIGHT_GRAY);
		try {
			ImageIcon keyboardIcon2 = new ImageIcon(ImageIO.read(UserInterface.class.getResource("/images/Keyboard.png")).getScaledInstance(42, 20, java.awt.Image.SCALE_SMOOTH));
			lblAutotyper.setIcon(keyboardIcon2);
		} catch (Exception e) {
			lblAutotyper.setText("AutoTyper");
		}
		autoTyperMenuButton.add(lblAutotyper);
		lblAutotyper.setForeground(Color.BLACK);
		
		// AutoClicker Panel
		clicker.setBackground(Color.LIGHT_GRAY);
		clicker.setBounds(74, 0, 613, 343);
		frame.getContentPane().add(clicker);
		clicker.setLayout(null);
		clickerComponents.add(clicker);
		
		// CursorPosition Panel
		cursorPositionPanel.setBorder(new TitledBorder(null, "Cursor Position", TitledBorder.LEADING, TitledBorder.TOP, new Font("Tahoma", Font.PLAIN, 14)));
		cursorPositionPanel.setBackground(Color.LIGHT_GRAY);
		cursorPositionPanel.setBounds(10, 11, 143, 52);
		clicker.add(cursorPositionPanel);
		cursorPositionPanel.setLayout(new BorderLayout(0, 0));
		clickerComponents.add(cursorPositionPanel);
		
		cursorPositionPanel.add(cursorPositionLabel);
		cursorPositionLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		clickerComponents.add(cursorPositionLabel);
		
		// ScreenSize Panel
		screenSizePanel.setBorder(new TitledBorder(null, "Screen Size", TitledBorder.LEADING, TitledBorder.TOP, new Font("Tahoma", Font.PLAIN, 14)));
		screenSizePanel.setBackground(Color.LIGHT_GRAY);
		screenSizePanel.setBounds(404, 11, 199, 52);
		clicker.add(screenSizePanel);
		screenSizePanel.setLayout(new BorderLayout(0, 0));
		clickerComponents.add(screenSizePanel);
		
		screenSizePanel.add(screenSizeLabel);
		screenSizeLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		clickerComponents.add(screenSizeLabel);
		
		// SetClickPositions JPanel
		setClickPosition.setBackground(Color.LIGHT_GRAY);
		setClickPosition.setBounds(10, 82, 261, 136);
		setClickPosition.setBorder(new TitledBorder(null, "Set Click Position",TitledBorder.LEADING, TitledBorder.TOP, new Font("Tahoma", Font.PLAIN, 14)));
		clicker.add(setClickPosition);
		setClickPosition.setLayout(null);
		clickerComponents.add(setClickPosition);

		followCursor.setFont(new Font("Tahoma", Font.PLAIN, 12));
		followCursor.setBackground(Color.LIGHT_GRAY);
		followCursor.setBounds(6, 18, 109, 23);
		setClickPosition.add(followCursor);
		followCursor.setFocusPainted(false);
		clickerComponents.add(followCursor);

		randomLocationWithinSpecifiedArea.setFont(new Font("Tahoma", Font.PLAIN, 12));
		randomLocationWithinSpecifiedArea.setBackground(Color.LIGHT_GRAY);
		randomLocationWithinSpecifiedArea.setBounds(6, 44, 244, 23);
		setClickPosition.add(randomLocationWithinSpecifiedArea);
		randomLocationWithinSpecifiedArea.setFocusPainted(false);
		clickerComponents.add(randomLocationWithinSpecifiedArea);
		
		labelX.setFont(new Font("Tahoma", Font.PLAIN, 12));
		labelX.setBounds(26, 69, 13, 14);
		setClickPosition.add(labelX);
		clickerComponents.add(labelX);
		
		setClickX.setForeground(Color.LIGHT_GRAY);
		setClickX.setBounds(49, 66, 70, 23);
		setClickPosition.add(setClickX);
		clickerComponents.add(setClickX);
		
		labelY.setFont(new Font("Tahoma", Font.PLAIN, 12));
		labelY.setBounds(152, 69, 13, 14);
		setClickPosition.add(labelY);
		clickerComponents.add(labelY);
		
		setClickY.setForeground(Color.LIGHT_GRAY);
		setClickY.setBounds(175, 66, 70, 23);
		setClickPosition.add(setClickY);
		clickerComponents.add(setClickY);
		
		labelWidth.setFont(new Font("Tahoma", Font.PLAIN, 12));
		labelWidth.setBounds(6, 103, 70, 14);
		setClickPosition.add(labelWidth);
		clickerComponents.add(labelWidth);
		
		setWidth.setForeground(Color.LIGHT_GRAY);
		setWidth.setBounds(49, 100, 70, 23);
		setClickPosition.add(setWidth);
		clickerComponents.add(setWidth);
		
		labelHeight.setFont(new Font("Tahoma", Font.PLAIN, 12));
		labelHeight.setBounds(124, 104, 45, 14);
		setClickPosition.add(labelHeight);
		clickerComponents.add(labelHeight);
		
		setHeight.setForeground(Color.LIGHT_GRAY);
		setHeight.setBounds(175, 101, 70, 23);
		setClickPosition.add(setHeight);
		clickerComponents.add(setHeight);
		
		// AmountOfClicks Panel
		amountOfClicks.setBorder(new TitledBorder(null, "Amount of Clicks", TitledBorder.LEADING, TitledBorder.TOP, new Font("Tahoma", Font.PLAIN, 14)));
		amountOfClicks.setBackground(Color.LIGHT_GRAY);
		amountOfClicks.setBounds(257, 237, 179, 94);
		clicker.add(amountOfClicks);
		amountOfClicks.setLayout(null);
		clickerComponents.add(amountOfClicks);
		
		clickUntilStopped.setFont(new Font("Tahoma", Font.PLAIN, 12));
		clickUntilStopped.setBounds(6, 23, 127, 23);
		clickUntilStopped.setBackground(Color.LIGHT_GRAY);
		clickUntilStopped.setFocusPainted(false);
		amountOfClicks.add(clickUntilStopped);
		clickerComponents.add(clickUntilStopped);
		
		stopAfter.setBackground(Color.LIGHT_GRAY);
		stopAfter.setFont(new Font("Tahoma", Font.PLAIN, 12));
		stopAfter.setBounds(6, 53, 87, 23);
		stopAfter.setFocusPainted(false);
		amountOfClicks.add(stopAfter);
		clickerComponents.add(stopAfter);

		clicksLabel.setBackground(Color.LIGHT_GRAY);
		clicksLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
		clicksLabel.setBounds(143, 57, 36, 14);
		amountOfClicks.add(clicksLabel);
		clickerComponents.add(clicksLabel);
		
		amountOfClicksUntilStopped.setBounds(94, 53, 45, 23);
		amountOfClicks.add(amountOfClicksUntilStopped);
		amountOfClicksUntilStopped.setForeground(Color.LIGHT_GRAY);
		clickerComponents.add(amountOfClicksUntilStopped);
		
		// ClickType Panel
		clickType.setBorder(new TitledBorder(null, "Click Type",TitledBorder.LEADING, TitledBorder.TOP, new Font("Tahoma", Font.PLAIN, 14)));
		clickType.setBackground(Color.LIGHT_GRAY);
		clickType.setBounds(10, 237, 222, 94);
		clicker.add(clickType);
		clickType.setLayout(null);
		clickerComponents.add(clickType);
		
		leftClick.setBackground(Color.LIGHT_GRAY);
		leftClick.setFont(new Font("Tahoma", Font.PLAIN, 12));
		leftClick.setBounds(6, 19, 75, 23);
		clickType.add(leftClick);
		leftClick.setFocusPainted(false);
		clickerComponents.add(leftClick);
		
		rightClick.setBounds(6, 40, 81, 23);
		clickType.add(rightClick);
		rightClick.setBackground(Color.LIGHT_GRAY);
		rightClick.setFont(new Font("Tahoma", Font.PLAIN, 12));
		rightClick.setFocusPainted(false);
		clickerComponents.add(rightClick);
		
		middleClick.setBounds(6, 61, 87, 23);
		clickType.add(middleClick);
		middleClick.setBackground(Color.LIGHT_GRAY);
		middleClick.setFont(new Font("Tahoma", Font.PLAIN, 12));		
		middleClick.setFocusPainted(false);
		clickerComponents.add(middleClick);
		
		doubleClick.setBackground(Color.LIGHT_GRAY);
		doubleClick.setFont(new Font("Tahoma", Font.PLAIN, 12));
		doubleClick.setBounds(107, 19, 97, 23);
		clickType.add(doubleClick);
		doubleClick.setFocusPainted(false);
		clickerComponents.add(doubleClick);
		
		clickTypeSeparator.setOrientation(SwingConstants.VERTICAL);
		clickTypeSeparator.setBounds(96, 8, 22, 82);
		clickType.add(clickTypeSeparator);
		clickerComponents.add(clickTypeSeparator);
		
		doubleClickdelay.setForeground(Color.LIGHT_GRAY);
		doubleClickdelay.setBounds(140, 43, 72, 18);
		clickType.add(doubleClickdelay);
		clickerComponents.add(doubleClickdelay);
		
		lblDelay.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblDelay.setBounds(107, 44, 37, 14);
		clickType.add(lblDelay);
		clickerComponents.add(lblDelay);

		doubleClickDelayMultiplier.setFont(new Font("Tahoma", Font.PLAIN, 12));
		doubleClickDelayMultiplier.setEditable(false);
		doubleClickDelayMultiplier.setBorder(null);
		doubleClickDelayMultiplier.setBackground(Color.WHITE);
		doubleClickDelayMultiplier.setBounds(107, 65, 105, 19);
		clickType.add(doubleClickDelayMultiplier);
		clickerComponents.add(doubleClickDelayMultiplier);
		
		// DelayBetwenClicks Panel
		delayBetweenClicks.setBorder(new TitledBorder(null, "Delay Between Clicks", TitledBorder.LEADING, TitledBorder.TOP, new Font("Tahoma", Font.PLAIN, 14)));
		delayBetweenClicks.setBackground(Color.LIGHT_GRAY);
		delayBetweenClicks.setBounds(295, 82, 306, 136);
		clicker.add(delayBetweenClicks);
		delayBetweenClicks.setLayout(null);
		clickerComponents.add(delayBetweenClicks);
		
		fixedDelay.setFocusPainted(false);
		fixedDelay.setBounds(6, 73, 87, 23);
		fixedDelay.setBackground(Color.LIGHT_GRAY);
		fixedDelay.setFont(new Font("Tahoma", Font.PLAIN, 12));
		delayBetweenClicks.add(fixedDelay);
		fixedDelay.setFocusPainted(false);
		clickerComponents.add(fixedDelay);
		clickerDelayGroup.add(fixedDelay);
		
		randomDelayMultiplier.setBackground(Color.WHITE);
		randomDelayMultiplier.setFont(new Font("Tahoma", Font.PLAIN, 12));
		randomDelayMultiplier.setBounds(191, 48, 105, 23);
		randomDelayMultiplier.setEditable(false);
		randomDelayMultiplier.setBorder(null);
		delayBetweenClicks.add(randomDelayMultiplier);
		clickerComponents.add(randomDelayMultiplier);
		
		maximumRandomDelay.setForeground(Color.LIGHT_GRAY);
		maximumRandomDelay.setBounds(140, 49, 46, 23);
		delayBetweenClicks.add(maximumRandomDelay);
		clickerComponents.add(maximumRandomDelay);
		
		randomDelay.setBackground(Color.LIGHT_GRAY);
		randomDelay.setFont(new Font("Tahoma", Font.PLAIN, 12));
		randomDelay.setBounds(6, 22, 105, 23);
		delayBetweenClicks.add(randomDelay);
		randomDelay.setFocusPainted(false);
		clickerComponents.add(randomDelay);
		clickerDelayGroup.add(randomDelay);
		
		betweenLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
		betweenLabel.setBounds(16, 52, 56, 14);
		delayBetweenClicks.add(betweenLabel);
		clickerComponents.add(betweenLabel);
		
		minimumRandomDelay.setForeground(Color.LIGHT_GRAY);
		minimumRandomDelay.setBounds(67, 49, 46, 23);
		delayBetweenClicks.add(minimumRandomDelay);
		clickerComponents.add(minimumRandomDelay);
		
		lblAnd.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblAnd.setBounds(117, 52, 26, 14);
		delayBetweenClicks.add(lblAnd);
		clickerComponents.add(lblAnd);

		fixedDelaySpinner.setForeground(Color.LIGHT_GRAY);
		fixedDelaySpinner.setBounds(16, 103, 46, 23);
		delayBetweenClicks.add(fixedDelaySpinner);
		clickerComponents.add(fixedDelaySpinner);
		
		fixedDelayMultiplier.setFont(new Font("Tahoma", Font.PLAIN, 12));
		fixedDelayMultiplier.setEditable(false);
		fixedDelayMultiplier.setBorder(null);
		fixedDelayMultiplier.setBackground(Color.WHITE);
		fixedDelayMultiplier.setBounds(67, 103, 105, 23);
		delayBetweenClicks.add(fixedDelayMultiplier);
		clickerComponents.add(fixedDelayMultiplier);
		
		// Shortcut JPanel
		shortcut.setBorder(new TitledBorder(null, "Shortcut Key to Start/Stop", TitledBorder.LEADING, TitledBorder.TOP, new Font("Tahoma", Font.PLAIN, 14)));
		shortcut.setBackground(Color.LIGHT_GRAY);
		shortcut.setBounds(178, 11, 199, 52);
		clicker.add(shortcut);
		shortcut.setLayout(null);	
		clickerComponents.add(shortcut);
		
		shortcutTextField.setFont(new Font("Tahoma", Font.PLAIN, 12));
		shortcutTextField.setBounds(10, 21, 104, 20);
		shortcut.add(shortcutTextField);
		shortcutTextField.setColumns(10);
		shortcutTextField.setEditable(false);
		shortcutTextField.setText(NativeKeyEvent.getKeyText(c.getShortcutKeyCode()));
		clickerComponents.add(shortcutTextField);
		
		assignShortcut.setFont(new Font("Tahoma", Font.PLAIN, 12));
		assignShortcut.setBounds(124, 20, 66, 23);
		shortcut.add(assignShortcut);
		assignShortcut.setFocusPainted(false);
		clickerComponents.add(assignShortcut);
		
		// Miscellaneous Panel
		misc.setBorder(new TitledBorder(null, "Miscellaneous", TitledBorder.LEADING, TitledBorder.TOP, new Font("Tahoma", Font.PLAIN, 14)));
		misc.setBackground(Color.LIGHT_GRAY);
		misc.setBounds(456, 237, 145, 94);
		clicker.add(misc);
		misc.setLayout(null);
		clickerComponents.add(misc);	

		startButton.setFont(new Font("Tahoma", Font.PLAIN, 12));
		startButton.setBounds(10, 21, 125, 30);
		misc.add(startButton);
		startButton.setFocusPainted(false);
		clickerComponents.add(startButton);
		
		feedbackButton.setFont(new Font("Tahoma", Font.PLAIN, 12));
		feedbackButton.setBounds(10, 55, 125, 28);
		misc.add(feedbackButton);
		feedbackButton.setFocusPainted(false);
		clickerComponents.add(feedbackButton);
		//*/
		
		// Typer Panel
		typer.setBackground(Color.LIGHT_GRAY);
		typer.setBounds(74, 0, 612, 343);
		frame.getContentPane().add(typer);
		typerComponents.add(typer);
		typer.setLayout(null);
		typerComponents.add(typer);

		// Message Panel
		messagePanel.setBorder(new TitledBorder(null, "Message", TitledBorder.LEADING, TitledBorder.TOP, new Font("Tahoma", Font.PLAIN, 14)));
		messagePanel.setBackground(Color.LIGHT_GRAY);
		messagePanel.setBounds(10, 11, 592, 104);
		typer.add(messagePanel);
		messagePanel.setLayout(null);
		typerComponents.add(messagePanel);
		
		messageBox.setBounds(10, 22, 572, 65);
		messageBox.setLineWrap(true);
		messageBox.setFont(new Font("Tahoma", Font.PLAIN, 14));
		messagePanel.add(messageBox);
		typerComponents.add(messageBox);
		
		// Delay Panel
		delayPanel.setBorder(new TitledBorder(null, "Delay Between Messages", TitledBorder.LEADING, TitledBorder.TOP, new Font("Tahoma", Font.PLAIN, 14)));
		delayPanel.setBackground(Color.LIGHT_GRAY);
		delayPanel.setBounds(10, 238, 478, 94);
		typer.add(delayPanel);
		typerComponents.add(delayPanel);
		delayPanel.setLayout(null);
		
		typerFixedDelay.setFocusPainted(false);
		typerFixedDelay.setBounds(6, 22, 86, 23);
		typerFixedDelay.setBackground(Color.LIGHT_GRAY);
		typerFixedDelay.setFont(new Font("Tahoma", Font.PLAIN, 12));
		delayPanel.add(typerFixedDelay);
		typerFixedDelay.setFocusPainted(false);
		typerComponents.add(typerFixedDelay);

		typerFixedDelayMultiplier.setBackground(Color.WHITE);
		typerFixedDelayMultiplier.setFont(new Font("Tahoma", Font.PLAIN, 12));
		typerFixedDelayMultiplier.setBounds(99, 52, 85, 19);
		typerFixedDelayMultiplier.setEditable(false);
		typerFixedDelayMultiplier.setBorder(null);
		delayPanel.add(typerFixedDelayMultiplier);
		typerComponents.add(typerFixedDelayMultiplier);
		
		typerDelay.setForeground(Color.LIGHT_GRAY);
		typerDelay.setBounds(16, 52, 70, 20);
		delayPanel.add(typerDelay);
		typerComponents.add(typerDelay);
		
		typerRandomDelay.setBackground(Color.LIGHT_GRAY);
		typerRandomDelay.setFont(new Font("Tahoma", Font.PLAIN, 12));
		typerRandomDelay.setBounds(197, 22, 102, 23);
		delayPanel.add(typerRandomDelay);
		typerRandomDelay.setFocusPainted(false);
		typerComponents.add(typerRandomDelay);

		labelBetween.setFont(new Font("Tahoma", Font.PLAIN, 12));
		labelBetween.setBounds(207, 54, 50, 15);
		delayPanel.add(labelBetween);
		typerComponents.add(labelBetween);

		typerRandomMin.setForeground(Color.LIGHT_GRAY);
		typerRandomMin.setBounds(262, 52, 40, 20);
		delayPanel.add(typerRandomMin);
		typerComponents.add(typerRandomMin);

		labelAnd.setFont(new Font("Tahoma", Font.PLAIN, 12));
		labelAnd.setBounds(311, 54, 20, 15);
		delayPanel.add(labelAnd);
		typerComponents.add(labelAnd);

		typerRandomMax.setForeground(Color.LIGHT_GRAY);
		typerRandomMax.setBounds(339, 52, 40, 20);
		delayPanel.add(typerRandomMax);
		typerComponents.add(typerRandomMax);

		typerRandomMultiplier.setFont(new Font("Tahoma", Font.PLAIN, 12));
		typerRandomMultiplier.setEditable(false);
		typerRandomMultiplier.setBorder(null);
		typerRandomMultiplier.setBackground(Color.WHITE);
		typerRandomMultiplier.setBounds(386, 52, 85, 19);
		delayPanel.add(typerRandomMultiplier);
		typerComponents.add(typerRandomMultiplier);

		typerDelaySeparator.setOrientation(SwingConstants.VERTICAL);
		typerDelaySeparator.setBounds(189, 8, 13, 82);
		delayPanel.add(typerDelaySeparator);
		
		// Capitalization Panel
		capitalization.setBorder(new TitledBorder(null, "Capitalization", TitledBorder.LEADING, TitledBorder.TOP, new Font("Tahoma", Font.PLAIN, 14)));
		capitalization.setBackground(Color.LIGHT_GRAY);
		capitalization.setBounds(10, 126, 147, 104);
		typer.add(capitalization);
		typerComponents.add(capitalization);
		capitalization.setLayout(null);

		typeAsItIs.setBackground(Color.LIGHT_GRAY);
		typeAsItIs.setFont(new Font("Tahoma", Font.PLAIN, 12));
		typeAsItIs.setBounds(6, 19, 109, 23);
		capitalization.add(typeAsItIs);
		typeAsItIs.setFocusPainted(false);
		typerComponents.add(typeAsItIs);

		typeAllCaps.setBackground(Color.LIGHT_GRAY);
		typeAllCaps.setFont(new Font("Tahoma", Font.PLAIN, 12));
		typeAllCaps.setBounds(6, 46, 109, 23);
		capitalization.add(typeAllCaps);
		typeAllCaps.setFocusPainted(false);
		typerComponents.add(typeAllCaps);

		typeAllLower.setBackground(Color.LIGHT_GRAY);
		typeAllLower.setFont(new Font("Tahoma", Font.PLAIN, 12));
		typeAllLower.setBounds(6, 73, 137, 23);
		capitalization.add(typeAllLower);
		typeAllLower.setFocusPainted(false);
		typerComponents.add(typeAllLower);
		
		// Keys pressed while typing panel
		enterShiftAlt.setBorder(new TitledBorder(null, "Press Keys While Typing", TitledBorder.LEADING, TitledBorder.TOP, new Font("Tahoma", Font.PLAIN, 14)));
		enterShiftAlt.setBackground(Color.LIGHT_GRAY);
		enterShiftAlt.setBounds(167, 177, 189, 53);
		typer.add(enterShiftAlt);
		typerComponents.add(enterShiftAlt);
		enterShiftAlt.setLayout(null);
		
		enter.setFont(new Font("Tahoma", Font.PLAIN, 12));
		enter.setBackground(Color.LIGHT_GRAY);
		enter.setBounds(6, 20, 64, 23);
		enterShiftAlt.add(enter);
		enter.setFocusPainted(false);
		typerComponents.add(enter);

		shift.setFont(new Font("Tahoma", Font.PLAIN, 12));
		shift.setBackground(Color.LIGHT_GRAY);
		shift.setBounds(131, 20, 52, 23);
		enterShiftAlt.add(shift);
		shift.setFocusPainted(false);
		typerComponents.add(shift);

		alt.setFont(new Font("Tahoma", Font.PLAIN, 12));
		alt.setBackground(Color.LIGHT_GRAY);
		alt.setBounds(72, 20, 52, 23);
		enterShiftAlt.add(alt);
		alt.setFocusPainted(false);
		typerComponents.add(alt);
		
		// Miscellaneous Panel
		typerMisc.setBorder(new TitledBorder(null, "Miscellaneous", TitledBorder.LEADING, TitledBorder.TOP, new Font("Tahoma", Font.PLAIN, 14)));
		typerMisc.setBackground(Color.LIGHT_GRAY);
		typerMisc.setBounds(493, 238, 109, 94);
		typer.add(typerMisc);
		typerMisc.setLayout(null);
		typerComponents.add(typerMisc);

		typerStartStop.setFont(new Font("Tahoma", Font.PLAIN, 12));
		typerStartStop.setBounds(10, 21, 92, 30);
		typerMisc.add(typerStartStop);
		typerStartStop.setFocusPainted(false);
		typerComponents.add(typerStartStop);

		typerFeedBack.setFont(new Font("Tahoma", Font.PLAIN, 12));
		typerFeedBack.setBounds(10, 55, 92, 28);
		typerMisc.add(typerFeedBack);
		typerFeedBack.setFocusPainted(false);
		typerComponents.add(typerFeedBack);
		
		// Shortcut JPanel
		typerShortcut.setBounds(167, 126, 189, 53);
		typer.add(typerShortcut);
		typerShortcut.setBorder(new TitledBorder(null, "Shortcut Key to Start/Stop", TitledBorder.LEADING, TitledBorder.TOP, new Font("Tahoma", Font.PLAIN, 14)));
		typerShortcut.setBackground(Color.LIGHT_GRAY);
		typerShortcut.setLayout(null);	
		typerComponents.add(typerShortcut);

		typerShortcutText.setFont(new Font("Tahoma", Font.PLAIN, 12));
		typerShortcutText.setBounds(10, 21, 86, 20);
		typerShortcut.add(typerShortcutText);
		typerShortcutText.setColumns(10);
		typerShortcutText.setEditable(false);
		typerComponents.add(typerShortcutText);
		typerShortcutText.setText(NativeKeyEvent.getKeyText(t.getShortcutKeyCode()));
		typerAssign = new JButton("Assign");
		typerAssign.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DialougeBox dialougeBox = new DialougeBox(buffer2);
				DialougeUpdater dialougeUpdater = new DialougeUpdater(t, dialougeBox, typerShortcutText, 2);
				dialougeUpdater.start();
				for (Component component : typerComponents) {
					component.setFocusable(false);
				}
				for (Component component : typerComponents) {
					component.setFocusable(true);
				}
			}
		});
		
		typerAssign.setFont(new Font("Tahoma", Font.PLAIN, 12));
		typerAssign.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		typerAssign.setBounds(106, 20, 73, 23);
		typerShortcut.add(typerAssign);
		typerAssign.setFocusPainted(false);
		typerComponents.add(typerAssign);
		
		// Repititions
		repititionsPanel.setBackground(Color.LIGHT_GRAY);
		repititionsPanel.setBorder(new TitledBorder(null, "Repititions", TitledBorder.LEADING, TitledBorder.TOP, new Font("Tahoma", Font.PLAIN, 14)));
		repititionsPanel.setBounds(366, 126, 236, 104);
		typer.add(repititionsPanel);
		repititionsPanel.setLayout(null);
		
		typeTilStopped.setFont(new Font("Tahoma", Font.PLAIN, 12));
		typeTilStopped.setBackground(Color.LIGHT_GRAY);
		typeTilStopped.setBounds(6, 26, 144, 23);
		repititionsPanel.add(typeTilStopped);
		typeTilStopped.setFocusPainted(false);
		typerComponents.add(typeTilStopped);
		
		stopAfterTyping.setFont(new Font("Tahoma", Font.PLAIN, 12));
		stopAfterTyping.setBackground(Color.LIGHT_GRAY);
		stopAfterTyping.setBounds(6, 57, 125, 23);
		repititionsPanel.add(stopAfterTyping);
		stopAfterTyping.setFocusPainted(false);
		typerComponents.add(stopAfterTyping);
		
		stopAfterXTimes.setForeground(Color.LIGHT_GRAY);
		stopAfterXTimes.setBounds(130, 58, 46, 21);
		repititionsPanel.add(stopAfterXTimes);
		typerComponents.add(stopAfterXTimes);
		
		timesLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
		timesLabel.setBounds(180, 61, 46, 14);
		repititionsPanel.add(timesLabel);
		typerComponents.add(timesLabel);
		//*/
		enableComponents(clickerComponents);
		disableComponents(typerComponents);	
		
	}
	
	private void disableComponents(List <Component> list) {
		for (Component c : list) {
			c.setEnabled(false);
			c.setVisible(false);
			c.setFocusable(false);
		}
	}
	
	private void enableComponents(List <Component> list) {
		for (Component c : list) {
			c.setEnabled(true);
			c.setVisible(true);
			c.setFocusable(true);
		}
	}
	
	public JFrame getFrame() {
		return frame;
	}
	
	public Clicker getClicker() {
		return c;
	}
	
	public Typer getTyper() {
		return t;
	}
	
	public void toggleClicker(int keyCode) {
		if (c.getState() && keyCode == c.getShortcutKeyCode()) {
			c.toggle();
		} else if (keyCode == c.getShortcutKeyCode()) {
			c.setRandomLocationX(Integer.parseInt(setClickX.getValue().toString()));
			c.setRandomLocationY(Integer.parseInt(setClickY.getValue().toString()));
			c.setRandomLocationWidth(Integer.parseInt(setWidth.getValue().toString()));
			c.setRandomLocationHeight(Integer.parseInt(setHeight.getValue().toString()));
			c.setDoubleClickDelay(Integer.parseInt(doubleClickdelay.getValue().toString()));
			c.setDoubleClickMultiplier(doubleClickDelayMultiplier.getSelectedIndex());					
			c.setClicks(Integer.parseInt(amountOfClicksUntilStopped.getValue().toString()));
			c.setMinimumRandomDelay(Integer.parseInt(minimumRandomDelay.getValue().toString()));
			c.setMaximumRandomDelay(Integer.parseInt(maximumRandomDelay.getValue().toString()));
			c.setRandomDelayMultiplier(randomDelayMultiplier.getSelectedIndex());				
			c.setFixedDelayBetweenClicks(Integer.parseInt(fixedDelaySpinner.getValue().toString()));					
			c.setFixedDelayMultipler(fixedDelayMultiplier.getSelectedIndex());
			c.toggle();
		}
	}
	
	public void toggleClicker() {
		if (c.getState()) {
			c.toggle();
		} else {
			c.setRandomLocationX(Integer.parseInt(setClickX.getValue().toString()));
			c.setRandomLocationY(Integer.parseInt(setClickY.getValue().toString()));
			c.setRandomLocationWidth(Integer.parseInt(setWidth.getValue().toString()));
			c.setRandomLocationHeight(Integer.parseInt(setHeight.getValue().toString()));
			c.setDoubleClickDelay(Integer.parseInt(doubleClickdelay.getValue().toString()));
			c.setDoubleClickMultiplier(doubleClickDelayMultiplier.getSelectedIndex());					
			c.setClicks(Integer.parseInt(amountOfClicksUntilStopped.getValue().toString()));
			c.setMinimumRandomDelay(Integer.parseInt(minimumRandomDelay.getValue().toString()));
			c.setMaximumRandomDelay(Integer.parseInt(maximumRandomDelay.getValue().toString()));
			c.setRandomDelayMultiplier(randomDelayMultiplier.getSelectedIndex());				
			c.setFixedDelayBetweenClicks(Integer.parseInt(fixedDelaySpinner.getValue().toString()));					
			c.setFixedDelayMultipler(fixedDelayMultiplier.getSelectedIndex());
			c.toggle();
		}
	}
	
	public void toggleTyper(int keyCode) {
		if (t.getState() && keyCode == t.getShortcutKeyCode()) {
			t.toggle();
		} else if (keyCode == t.getShortcutKeyCode()) {
			t.setMessage(messageBox.getText());
			t.setFixedDelayMultiplier(typerFixedDelayMultiplier.getSelectedIndex());
			t.setDelay(Integer.parseInt(typerDelay.getValue().toString()));
			t.setRandomDelayMultiplier(typerRandomMultiplier.getSelectedIndex());
			t.setMinimumRandomDelay(Integer.parseInt(typerRandomMin.getValue().toString()));
			t.setMaximumRandomDelay(Integer.parseInt(typerRandomMax.getValue().toString()));
			t.setEnter(enter.isSelected());
			t.setAlt(alt.isSelected());
			t.setShift(shift.isSelected());
			t.setTypes(Integer.parseInt(stopAfterXTimes.getValue().toString()));
			t.toggle();
		}
	}
	
	public void toggleTyper() {
		if (t.getState()) {
			t.toggle();
		} else {
			t.setMessage(messageBox.getText());
			t.setFixedDelayMultiplier(typerFixedDelayMultiplier.getSelectedIndex());
			t.setDelay(Integer.parseInt(typerDelay.getValue().toString()));
			t.setRandomDelayMultiplier(typerRandomMultiplier.getSelectedIndex());
			t.setMinimumRandomDelay(Integer.parseInt(typerRandomMin.getValue().toString()));
			t.setMaximumRandomDelay(Integer.parseInt(typerRandomMax.getValue().toString()));
			t.setEnter(enter.isSelected());
			t.setAlt(alt.isSelected());
			t.setShift(shift.isSelected());
			t.setTypes(Integer.parseInt(stopAfterXTimes.getValue().toString()));
			t.toggle();
		}
	}
	
	public JLabel getCursorLabel() {
		return cursorPositionLabel;
	}
	
	public int getLatestKeyPress() {
		return latestKeyPress;
	}
	
	public void setLatestKeyPress(int newKeyPress) {
		latestKeyPress = newKeyPress;
	}
	
	public void dispose() {
		frame.dispose();
	}
	
	public void setSettings() {
		
	}
}
