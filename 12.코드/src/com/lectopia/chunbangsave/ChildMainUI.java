package com.lectopia.chunbangsave.view;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.border.TitledBorder;


public class ChildMainUI  extends JDialog
{
	private JLabel monthLabel;
	private JLabel leftArrowLabel;
	private JLabel rightArrowLabel;
	
	private JButton monthBtn; 
	private JButton yearBtn;
	private JButton closeBtn;
	private JButton diaryBtn;
	
	private JLabel dayLabel[];
	
	private GregorianCalendar cal;
	private int year;
	private int month;
	private int day;
	
	private JPanel[] moneyPanels;
	//?‚ ì§? ?Œ¨?„ 
	private JPanel[] datePanels;
	private JLabel[] dateLabels;
	private JPanel calendarPanel;
	
	private JProgressBar goalBar;
	private JLabel balanceLabel;
	private JLabel realBalanceLabel;
	
	//UI
	private MonthHistoryUI monthUI;
	private YearHistoryUI yearUI;
	private ShowChildDayAccountUI dayUI;
	private DiaryUI diaryUI;
	private CommunicationWithParent closeUI;
	private ChildManage toChildUI;
	private int code;

	public ChildMainUI (JDialog dialog)
	{
		super(dialog,true);
		setTitle("ì²œë°©??ì¶?");

		//?´ë¯¸ì? ?‹´?„ ?Œ¨?„
		JPanel imagePanel = new JPanel();
		
		// ?›” & ëª©í‘œ & ?´ë¯¸ì? ?‹´?„ ?Œ¨?„ 
		JPanel totalPanel = new JPanel();
		totalPanel.setLayout(new BoxLayout(totalPanel, BoxLayout.Y_AXIS));
		//
		JPanel topPanel = new JPanel();
		topPanel.setLayout(new GridLayout(1, 3, 20, 20));
		
		//?›” & ?™”?‚´?‘œ 
		//?˜¤?Š˜ ?‚ ì§? ê³„ì‚°  
		cal = (GregorianCalendar) Calendar.getInstance();
		year = cal.get(Calendar.YEAR);
		month = cal.get(Calendar.MONTH);
		day = cal.get(Calendar.DATE);
		
		//cal.set(Calendar.DATE, 1);
/*
 * UI?ƒ?„±
 * */
		
		//code = 2; //?„?‹œ ?“±ë¡ì½”?“œ
		monthUI = new MonthHistoryUI(null, cal,1);
		yearUI = new YearHistoryUI(null,cal,1);
		monthUI.setVisible(false);
		yearUI.setVisible(false);
		diaryUI = new DiaryUI(null,cal,1);
		diaryUI.setVisible(false);
		/*
		 * ?“±ë¡ì½”?“œ ì¤˜ì•¼
		 */
		
		dayUI = new ShowChildDayAccountUI(null, cal, 1);		
		dayUI.setVisible(false);
//?—¬ê¸°ê¹Œì§?
		
		
		Container contentPane = getContentPane();
		
		//?›” ? ˆ?´ë¸? 
		monthLabel = new JLabel(Integer.toString(month + 1) + "?›”");
		monthLabel.setFont(new Font("Courier New", Font.BOLD, 50));
		
		//?™”?‚´?‘œ ? ˆ?´ë¸? 
		leftArrowLabel = new JLabel("< ");		
		leftArrowLabel.setFont(new Font("Courier New", Font.BOLD, 30));
		rightArrowLabel = new JLabel(" >");
		rightArrowLabel.setFont(new Font("Courier New", Font.BOLD, 30));
		leftArrowLabel.addMouseListener(new PreviousCalendarListener());
		rightArrowLabel.addMouseListener(new NextCalendarListener());
		
		//?Œ¨?„?— ?›”, ?™”?‚´?‘œ?“¤ ë¶™ì„ 
		JPanel monthArrowPanel = new JPanel();
		monthArrowPanel.add(leftArrowLabel);
		monthArrowPanel.add(monthLabel);
		
		// ëª©í‘œ ?Œ¨?„, ëª©í‘œ ë°?
		JPanel goalBalancePanel = new JPanel();

		JPanel goalPanel = new JPanel();
		goalBar = new JProgressBar();
		goalPanel.add(goalBar);

		// ë²”ìœ„ 0 ~ 100 ?œ¼ë¡? ?„¤? •
		goalBar.setMinimum(0);
		goalBar.setMaximum(100);
		// ë³´ì´?„ë¡? ?„¸?Œ…
		goalBar.setStringPainted(true);
		goalBar.setValue(70);// ?˜„?¬ ?¼?„¼?Š¸ ë³´ì´ê²? 70?•˜ë©? 70%ê°? ë³´ì„
		goalBar.setForeground(Color.orange);// ?”„ë¡œê·¸? ˆ?Š¤ë°? ?ƒ‰ê¹?
		// goalBar.setStringPainted(false);//?”„ë¡œê·¸? ˆ?Š¤ë°”ì— 70?´ ?“¤?–´ê°?ë©? ê·¸ë˜?”„?— 70%?¼ê³? ?‘œ?‹œ?˜?Š”?°
		// ê·¸ê±° ì§??š°?Š” ì½”ë“œ

		// ?”?•¡ ?‘œ?‹œ ? ˆ?´ë¸?
		balanceLabel = new JLabel("?‚¨?? ?š©?ˆ ");
		realBalanceLabel = new JLabel("1000?›"); // ////*******?”?•¡ ë°›ì•„?„œ ?‘œ?‹œ?•  ë¶?ë¶?

		if (code == 2) // ë¶?ëª¨ì´ë©?
			balanceLabel.setText("?”?•¡ ");
		JPanel balancePanel = new JPanel();
		balancePanel.add(balanceLabel);
		balancePanel.add(realBalanceLabel);

		goalBalancePanel.add(goalPanel);
		goalBalancePanel.add(balancePanel);

		/************ ?‚˜ì¤‘ì— ?°?´?„° ?ˆ˜? • **************/
	
		goalBalancePanel.setBorder(new TitledBorder("ë°•ë™?µ ?–´ë¦°ì´!"));
		
		/**
		 * ?—¬ê¸°ë??„° ?ˆ˜? •
		 */
		topPanel.add(imagePanel);
		topPanel.add(monthArrowPanel); 
		topPanel.add(goalPanel);

		// ?‹¬? ¥?„ ?‹´?„ ?‹¬? ¥ ?Œ¨?„ 
		calendarPanel = new JPanel();
		calendarPanel.setLayout(new GridLayout(6, 7, 5, 5));
		//?‚¨?? ê¸ˆì•¡ & ?¼?¼?•œ?„ & ì¹?ì°¬í•˜ê¸? ?“¤?–´ê°? ?Œ¨?„ 
		//ìµœë? ?¼ ?ˆ˜ 31 + 6(?‹œ?‘?š”?¼ ëª¨ë¥´?‹ˆê¹? ê°??¥ ?° ê°’ìœ¼ë¡? ?„¤? •)
		moneyPanels = new JPanel[37];
		//?‚ ì§? ?Œ¨?„ 
		datePanels = new JPanel[37];
		dateLabels = new JLabel[37];
			
		//?´ ?Œ¨?„?“¤?„ ?ƒ?„±?•´ì£¼ê³  
		for(int i = 0; i < 37; i++)
		{
			datePanels[i] = new JPanel();
			moneyPanels[i] = new JPanel();
			dateLabels[i] = new JLabel();
			
			dateLabels[i].addMouseListener(new DayAccountLabelListener());
		}
		int j;
		//1?¼ ? „?— ?ˆ?Š” ?Œ¨?„?“¤?? ?ƒ?„±ê³? ?„¸?Œ…?? ?•´ì¤? (ì¶”í›„ ?‚¬?š©?•  ?˜ˆ? •?´ê¸? ?•Œë¬?)
		//?—¬ê¸°ì„œ?Š” ë³´ì—¬ì£¼ì? ?•Š?Œ 
		for(j = 0; j < cal.get(Calendar.DAY_OF_WEEK)- 1 ; j++)
		{	
			dateLabels[j].setText("");
			datePanels[j].add(dateLabels[j]);
			moneyPanels[j].setLayout(new BorderLayout());
			moneyPanels[j].add(datePanels[j], BorderLayout.SOUTH);
			
			datePanels[j].setBackground(new Color(15, 146, 2));
			datePanels[j].setBorder(BorderFactory.createLineBorder(Color.black));

			moneyPanels[j].setBackground(Color.WHITE);
			moneyPanels[j].setBorder(BorderFactory.createLineBorder(Color.black, 2));
			
			calendarPanel.add(moneyPanels[j]);
			moneyPanels[j].setVisible(false);
		}
		//ê·? ?›”?˜ ?¼?ˆ˜?— ?•´?‹¹?•˜?Š” ?Œ¨?„ ?„¸?Œ… 
		for(int i = 0; j < 37; i++, j++)
		{
			moneyPanels[j].setLayout(new BorderLayout());
			moneyPanels[j].add(datePanels[j], BorderLayout.SOUTH);
			dateLabels[j].setText(Integer.toString(i + 1));
			datePanels[j].add(dateLabels[j]);
			
			datePanels[j].setBackground(new Color(15, 146, 2));
			datePanels[j].setBorder(BorderFactory.createLineBorder(Color.black));

			moneyPanels[j].setBackground(Color.WHITE);
			moneyPanels[j].setBorder(BorderFactory.createLineBorder(Color.black, 2));
			calendarPanel.add(moneyPanels[j]);
		}		
		
		//ê·? ?›”?˜ ?¼ ?ˆ˜ë¥? ë²—ì–´?‚œ ?Œ¨?„?“¤?? ?•ˆ ë³´ì´ê²? 
		for(int i = 36; i >  cal.getActualMaximum(Calendar.DATE); i--)
			moneyPanels[i].setVisible(false);
		
		//datePanels[cal.get(Calendar.DAY_OF_WEEK) + day - 1].setBackground(Color.ORANGE);

		//?š”?¼ ?Œ¨?„ 
		JPanel dayPanel = new JPanel();
		dayPanel.setLayout(new GridLayout(2, 7, 10, 10));
		dayLabel = new JLabel[7];
		
		for(int i = 0; i < 7; i++)
		{
			dayLabel[i] = new JLabel();
			dayPanel.add(new JPanel());
		}
		
		
		dayLabel[0].setText("?¼");
		dayLabel[1].setText("?›”");
		dayLabel[2].setText("?™”");
		dayLabel[3].setText("?ˆ˜");
		dayLabel[4].setText("ëª?");
		dayLabel[5].setText("ê¸?");
		dayLabel[6].setText("?† ");
		
		for(int i = 0; i < 7; i++)
		{
			dayLabel[i].setFont(new Font("Courier New", Font.BOLD, 15));
			dayLabel[i].setHorizontalAlignment(JLabel.CENTER);
			dayPanel.add(dayLabel[i]);
		}
		totalPanel.add(new JPanel());
		totalPanel.add(topPanel);
		totalPanel.add(dayPanel);
		
		//ë²„íŠ¼?— ?“¤?–´ê°? ?´ë¯¸ì? ?•„?´ì½? ?ƒ?„± ë°? ?‚¬?´ì¦? ì¡°ì ˆ 
		ImageIcon diaryIcon = new ImageIcon("Pencil.png");
		Image image = diaryIcon.getImage(); // transform it 
		Image newimg = image.getScaledInstance(50, 50,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
		diaryIcon = new ImageIcon(newimg);  // transform it back
		
		ImageIcon yearIcon = new ImageIcon("chart1.png");
		image = yearIcon.getImage(); // transform it 
		newimg = image.getScaledInstance(50, 50,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
		yearIcon = new ImageIcon(newimg);  // transform it back
		
		ImageIcon monthIcon = new ImageIcon("chart2.png");
		image = monthIcon.getImage(); // transform it 
		image = image.getScaledInstance(50, 50,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
		monthIcon = new ImageIcon(newimg);  // transform it back
		
		ImageIcon closeIcon = new ImageIcon("heart.png");
		image = closeIcon.getImage(); // transform it 
		newimg = image.getScaledInstance(50, 50,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
		closeIcon = new ImageIcon(newimg);  // transform it back
		//ë²„íŠ¼?“¤ ?ƒ?„± 
/**
 * 	
 */
		diaryBtn = new JButton(" ?¼ê¸°ì¥", diaryIcon);
		diaryBtn.setBackground(new Color(255, 135, 0));
		yearBtn = new JButton(" ?—°?„ë³„ë³´ê¸?", yearIcon);
		yearBtn.setBackground(new Color(255, 135, 0));
		yearBtn.addActionListener(new YearAccountBtnListener());
		monthBtn = new JButton(" ?›”ë³„ë³´ê¸?", monthIcon);
		monthBtn.setBackground(new Color(255, 135, 0));
		monthBtn.addActionListener(new MonthAccountBtnListener());
		
		closeBtn = new JButton("?‹«ê¸?", closeIcon);
		closeBtn.setBackground(new Color(255, 135, 0));
		//closeBtn.addActionListener(new closeBtnListener());
		closeBtn.addActionListener(new CancelBtnListener(this));
		diaryBtn.addActionListener(new DiaryBtnListener());
		
		//ë²„íŠ¼?“¤ ?‹´?„ ë²„íŠ¼ ?Œ¨?„ ?ƒ?„± 
		JPanel buttonsPanel = new JPanel();
		buttonsPanel.add(monthBtn);
		buttonsPanel.add(yearBtn);
		buttonsPanel.add(diaryBtn);
		buttonsPanel.add(closeBtn);
		
		contentPane.add(totalPanel, BorderLayout.NORTH);
		//contentPane.add(dayPanel);
		contentPane.add(calendarPanel);
		contentPane.add(buttonsPanel, BorderLayout.SOUTH);
		
		
		setPreferredSize(new Dimension(700, 700));
		//setResizable(false);
		setLocation(200,100);
//		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		pack();
		//setAlwaysOnTop(true);
		//setVisible(true);
	}
	/**
	 * ë§ˆìš°?Š¤ë¦¬ìŠ¤?„ˆë¥? ë§ˆìš°?Š¤ ?–´?Œ‘?„°ë¡? ?‹¤ ë°”ê¿ˆ 
	 */
		//?™¼ìª? ?™”?‚´?‘œ ?´? „ ?‹¬ë¡? ê°?ê¸? 
		private class PreviousCalendarListener extends MouseAdapter
		{
			public void mouseClicked(MouseEvent e)
			{
				if(month != 0)
				{
					leftArrowLabel.setEnabled(true);
					rightArrowLabel.setEnabled(true);
					
				//?Œ¨?„ ëª¨ë‘ ?•ˆë³´ì´ê²? ë¨¼ì? ?•´?†“ê³? 
				for(int j = 0; j < 37; j++)
					moneyPanels[j].setVisible(false);
				//ì§??‚œ ?‹¬ë¡? ?‹¬? ¥?„ ë§ì¶°?†“ê³? 
				cal.set(year, --month, 1);
				//? ˆ?´ë¸”ë„ ì§??‚œ ?›”ë¡? ?„¸?Œ… 
				monthLabel.setText(Integer.toString(month + 1) + "?›”");
				
				//ê·? ?›”?˜ ?š”?¼?˜ ?‹œ?‘?„ êµ¬í•´?†“ê³? (ë°°ì—´?— ?‚¬?š©?œ„?•´ -1)
				int j = cal.get(Calendar.DAY_OF_WEEK)-1;
				//ê·? ?›”?˜ ?¼ ?ˆ˜ ë§Œí¼ ?¬ë¬? ?Œë¦? 
				for(int i = 0; i < cal.getActualMaximum(Calendar.DATE); i++, j++)
				{
					//ê·? ?›”?˜ ?š”?¼?˜ ?‹œ?‘ë¶??„° ?¼ ?ˆ˜ ë§Œí¼ ë³´ì´ê²? ?•˜ê³? ? ˆ?´ë¸? ?„¸?Œ… 
					moneyPanels[j].setVisible(true);
					dateLabels[j].setText(Integer.toString(i + 1));
				}
				}

				//1?›” ?´? „?œ¼ë¡? ê°?ì§?ëª»í•˜ê²? ? ˆ?´ë¸? ë¹„í™œ?„±?™” 
				else
					leftArrowLabel.setEnabled(false);
				
			}
		}

	// ?˜¤ë¥¸ìª½ ?™”?‚´?‘œ ?‹¤?Œ?‹¬ë¡? ê°?ê¸?
	private class NextCalendarListener extends MouseAdapter {
		public void mouseClicked(MouseEvent e) {
			if (month != 11) {
				leftArrowLabel.setEnabled(true);
				rightArrowLabel.setEnabled(true);

				for (int j = 0; j < 37; j++)
					moneyPanels[j].setVisible(false);

				cal.set(year, ++month, 1);
				monthLabel.setText(Integer.toString(month + 1) + "?›”");
				int j = cal.get(Calendar.DAY_OF_WEEK) - 1;

				for (int i = 0; i < cal.getActualMaximum(Calendar.DATE); i++, j++) {
					moneyPanels[j].setVisible(true);
					dateLabels[j].setText(Integer.toString(i + 1));
				}
			}
			// 12?›” ?´?›„ë¡? ê°?ì§?ëª»í•˜ê²? ? ˆ?´ë¸? ë¹„í™œ?„±?™”
			else
				rightArrowLabel.setEnabled(false);
		}
	}

	// ?‚ ì§? ?´ë¦? ?‹œ ?¼?¼ ê²°ì‚° ë³´ê¸°
	private class DayAccountLabelListener extends MouseAdapter {
		public void mouseClicked(MouseEvent e) {
			System.out.println(((JLabel) e.getSource()).getText());
			GregorianCalendar calendar = new GregorianCalendar();
			calendar.set(year, month,
					Integer.parseInt(((JLabel) e.getSource()).getText()));
			dayUI.getCalendar(calendar);
			dayUI.setVisible(true);
		}
	}

	// ?›” ë³? ê²°ì‚°
	private class MonthAccountBtnListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			monthUI.setVisible(true);
		}
	}

	// ?—°?„ ë³? ê²°ì‚°
	private class YearAccountBtnListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			yearUI.setVisible(true);
		}
	}// ?—„ë§?! ?•„ë¹?!

	/*private class closeBtnListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			setVisible(false);
		}
	}// ?¼ê¸°ì¥*/

	private class DiaryBtnListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			diaryUI.setVisible(true);
		}
	}
			
}
