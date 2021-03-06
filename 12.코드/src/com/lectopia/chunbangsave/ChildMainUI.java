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
	//? μ§? ?¨? 
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
		setTitle("μ²λ°©??μΆ?");

		//?΄λ―Έμ? ?΄? ?¨?
		JPanel imagePanel = new JPanel();
		
		// ? & λͺ©ν & ?΄λ―Έμ? ?΄? ?¨? 
		JPanel totalPanel = new JPanel();
		totalPanel.setLayout(new BoxLayout(totalPanel, BoxLayout.Y_AXIS));
		//
		JPanel topPanel = new JPanel();
		topPanel.setLayout(new GridLayout(1, 3, 20, 20));
		
		//? & ??΄? 
		//?€? ? μ§? κ³μ°  
		cal = (GregorianCalendar) Calendar.getInstance();
		year = cal.get(Calendar.YEAR);
		month = cal.get(Calendar.MONTH);
		day = cal.get(Calendar.DATE);
		
		//cal.set(Calendar.DATE, 1);
/*
 * UI??±
 * */
		
		//code = 2; //?? ?±λ‘μ½?
		monthUI = new MonthHistoryUI(null, cal,1);
		yearUI = new YearHistoryUI(null,cal,1);
		monthUI.setVisible(false);
		yearUI.setVisible(false);
		diaryUI = new DiaryUI(null,cal,1);
		diaryUI.setVisible(false);
		/*
		 * ?±λ‘μ½? μ€μΌ
		 */
		
		dayUI = new ShowChildDayAccountUI(null, cal, 1);		
		dayUI.setVisible(false);
//?¬κΈ°κΉμ§?
		
		
		Container contentPane = getContentPane();
		
		//? ? ?΄λΈ? 
		monthLabel = new JLabel(Integer.toString(month + 1) + "?");
		monthLabel.setFont(new Font("Courier New", Font.BOLD, 50));
		
		//??΄? ? ?΄λΈ? 
		leftArrowLabel = new JLabel("< ");		
		leftArrowLabel.setFont(new Font("Courier New", Font.BOLD, 30));
		rightArrowLabel = new JLabel(" >");
		rightArrowLabel.setFont(new Font("Courier New", Font.BOLD, 30));
		leftArrowLabel.addMouseListener(new PreviousCalendarListener());
		rightArrowLabel.addMouseListener(new NextCalendarListener());
		
		//?¨?? ?, ??΄??€ λΆμ 
		JPanel monthArrowPanel = new JPanel();
		monthArrowPanel.add(leftArrowLabel);
		monthArrowPanel.add(monthLabel);
		
		// λͺ©ν ?¨?, λͺ©ν λ°?
		JPanel goalBalancePanel = new JPanel();

		JPanel goalPanel = new JPanel();
		goalBar = new JProgressBar();
		goalPanel.add(goalBar);

		// λ²μ 0 ~ 100 ?Όλ‘? ?€? 
		goalBar.setMinimum(0);
		goalBar.setMaximum(100);
		// λ³΄μ΄?λ‘? ?Έ?
		goalBar.setStringPainted(true);
		goalBar.setValue(70);// ??¬ ?Ό?Ό?Έ λ³΄μ΄κ²? 70?λ©? 70%κ°? λ³΄μ
		goalBar.setForeground(Color.orange);// ?λ‘κ·Έ? ?€λ°? ?κΉ?
		// goalBar.setStringPainted(false);//?λ‘κ·Έ? ?€λ°μ 70?΄ ?€?΄κ°?λ©? κ·Έλ?? 70%?Όκ³? ?????°
		// κ·Έκ±° μ§??°? μ½λ

		// ??‘ ?? ? ?΄λΈ?
		balanceLabel = new JLabel("?¨?? ?©? ");
		realBalanceLabel = new JLabel("1000?"); // ////*******??‘ λ°μ? ???  λΆ?λΆ?

		if (code == 2) // λΆ?λͺ¨μ΄λ©?
			balanceLabel.setText("??‘ ");
		JPanel balancePanel = new JPanel();
		balancePanel.add(balanceLabel);
		balancePanel.add(realBalanceLabel);

		goalBalancePanel.add(goalPanel);
		goalBalancePanel.add(balancePanel);

		/************ ?μ€μ ?°?΄?° ??  **************/
	
		goalBalancePanel.setBorder(new TitledBorder("λ°λ?΅ ?΄λ¦°μ΄!"));
		
		/**
		 * ?¬κΈ°λ??° ?? 
		 */
		topPanel.add(imagePanel);
		topPanel.add(monthArrowPanel); 
		topPanel.add(goalPanel);

		// ?¬? ₯? ?΄? ?¬? ₯ ?¨? 
		calendarPanel = new JPanel();
		calendarPanel.setLayout(new GridLayout(6, 7, 5, 5));
		//?¨?? κΈμ‘ & ?Ό?Ό?? & μΉ?μ°¬νκΈ? ?€?΄κ°? ?¨? 
		//μ΅λ? ?Ό ? 31 + 6(????Ό λͺ¨λ₯΄?κΉ? κ°??₯ ?° κ°μΌλ‘? ?€? )
		moneyPanels = new JPanel[37];
		//? μ§? ?¨? 
		datePanels = new JPanel[37];
		dateLabels = new JLabel[37];
			
		//?΄ ?¨??€? ??±?΄μ£Όκ³  
		for(int i = 0; i < 37; i++)
		{
			datePanels[i] = new JPanel();
			moneyPanels[i] = new JPanel();
			dateLabels[i] = new JLabel();
			
			dateLabels[i].addMouseListener(new DayAccountLabelListener());
		}
		int j;
		//1?Ό ? ? ?? ?¨??€?? ??±κ³? ?Έ??? ?΄μ€? (μΆν ?¬?©?  ?? ?΄κΈ? ?λ¬?)
		//?¬κΈ°μ? λ³΄μ¬μ£Όμ? ?? 
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
		//κ·? ?? ?Ό?? ?΄?Ή?? ?¨? ?Έ? 
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
		
		//κ·? ?? ?Ό ?λ₯? λ²μ΄? ?¨??€?? ? λ³΄μ΄κ²? 
		for(int i = 36; i >  cal.getActualMaximum(Calendar.DATE); i--)
			moneyPanels[i].setVisible(false);
		
		//datePanels[cal.get(Calendar.DAY_OF_WEEK) + day - 1].setBackground(Color.ORANGE);

		//??Ό ?¨? 
		JPanel dayPanel = new JPanel();
		dayPanel.setLayout(new GridLayout(2, 7, 10, 10));
		dayLabel = new JLabel[7];
		
		for(int i = 0; i < 7; i++)
		{
			dayLabel[i] = new JLabel();
			dayPanel.add(new JPanel());
		}
		
		
		dayLabel[0].setText("?Ό");
		dayLabel[1].setText("?");
		dayLabel[2].setText("?");
		dayLabel[3].setText("?");
		dayLabel[4].setText("λͺ?");
		dayLabel[5].setText("κΈ?");
		dayLabel[6].setText("? ");
		
		for(int i = 0; i < 7; i++)
		{
			dayLabel[i].setFont(new Font("Courier New", Font.BOLD, 15));
			dayLabel[i].setHorizontalAlignment(JLabel.CENTER);
			dayPanel.add(dayLabel[i]);
		}
		totalPanel.add(new JPanel());
		totalPanel.add(topPanel);
		totalPanel.add(dayPanel);
		
		//λ²νΌ? ?€?΄κ°? ?΄λ―Έμ? ??΄μ½? ??± λ°? ?¬?΄μ¦? μ‘°μ  
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
		//λ²νΌ?€ ??± 
/**
 * 	
 */
		diaryBtn = new JButton(" ?ΌκΈ°μ₯", diaryIcon);
		diaryBtn.setBackground(new Color(255, 135, 0));
		yearBtn = new JButton(" ?°?λ³λ³΄κΈ?", yearIcon);
		yearBtn.setBackground(new Color(255, 135, 0));
		yearBtn.addActionListener(new YearAccountBtnListener());
		monthBtn = new JButton(" ?λ³λ³΄κΈ?", monthIcon);
		monthBtn.setBackground(new Color(255, 135, 0));
		monthBtn.addActionListener(new MonthAccountBtnListener());
		
		closeBtn = new JButton("?«κΈ?", closeIcon);
		closeBtn.setBackground(new Color(255, 135, 0));
		//closeBtn.addActionListener(new closeBtnListener());
		closeBtn.addActionListener(new CancelBtnListener(this));
		diaryBtn.addActionListener(new DiaryBtnListener());
		
		//λ²νΌ?€ ?΄? λ²νΌ ?¨? ??± 
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
	 * λ§μ°?€λ¦¬μ€?λ₯? λ§μ°?€ ?΄??°λ‘? ?€ λ°κΏ 
	 */
		//?Όμͺ? ??΄? ?΄?  ?¬λ‘? κ°?κΈ? 
		private class PreviousCalendarListener extends MouseAdapter
		{
			public void mouseClicked(MouseEvent e)
			{
				if(month != 0)
				{
					leftArrowLabel.setEnabled(true);
					rightArrowLabel.setEnabled(true);
					
				//?¨? λͺ¨λ ?λ³΄μ΄κ²? λ¨Όμ? ?΄?κ³? 
				for(int j = 0; j < 37; j++)
					moneyPanels[j].setVisible(false);
				//μ§?? ?¬λ‘? ?¬? ₯? λ§μΆ°?κ³? 
				cal.set(year, --month, 1);
				//? ?΄λΈλ μ§?? ?λ‘? ?Έ? 
				monthLabel.setText(Integer.toString(month + 1) + "?");
				
				//κ·? ?? ??Ό? ??? κ΅¬ν΄?κ³? (λ°°μ΄? ?¬?©??΄ -1)
				int j = cal.get(Calendar.DAY_OF_WEEK)-1;
				//κ·? ?? ?Ό ? λ§νΌ ?¬λ¬? ?λ¦? 
				for(int i = 0; i < cal.getActualMaximum(Calendar.DATE); i++, j++)
				{
					//κ·? ?? ??Ό? ??λΆ??° ?Ό ? λ§νΌ λ³΄μ΄κ²? ?κ³? ? ?΄λΈ? ?Έ? 
					moneyPanels[j].setVisible(true);
					dateLabels[j].setText(Integer.toString(i + 1));
				}
				}

				//1? ?΄? ?Όλ‘? κ°?μ§?λͺ»νκ²? ? ?΄λΈ? λΉν?±? 
				else
					leftArrowLabel.setEnabled(false);
				
			}
		}

	// ?€λ₯Έμͺ½ ??΄? ?€??¬λ‘? κ°?κΈ?
	private class NextCalendarListener extends MouseAdapter {
		public void mouseClicked(MouseEvent e) {
			if (month != 11) {
				leftArrowLabel.setEnabled(true);
				rightArrowLabel.setEnabled(true);

				for (int j = 0; j < 37; j++)
					moneyPanels[j].setVisible(false);

				cal.set(year, ++month, 1);
				monthLabel.setText(Integer.toString(month + 1) + "?");
				int j = cal.get(Calendar.DAY_OF_WEEK) - 1;

				for (int i = 0; i < cal.getActualMaximum(Calendar.DATE); i++, j++) {
					moneyPanels[j].setVisible(true);
					dateLabels[j].setText(Integer.toString(i + 1));
				}
			}
			// 12? ?΄?λ‘? κ°?μ§?λͺ»νκ²? ? ?΄λΈ? λΉν?±?
			else
				rightArrowLabel.setEnabled(false);
		}
	}

	// ? μ§? ?΄λ¦? ? ?Ό?Ό κ²°μ° λ³΄κΈ°
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

	// ? λ³? κ²°μ°
	private class MonthAccountBtnListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			monthUI.setVisible(true);
		}
	}

	// ?°? λ³? κ²°μ°
	private class YearAccountBtnListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			yearUI.setVisible(true);
		}
	}// ?λ§?! ?λΉ?!

	/*private class closeBtnListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			setVisible(false);
		}
	}// ?ΌκΈ°μ₯*/

	private class DiaryBtnListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			diaryUI.setVisible(true);
		}
	}
			
}
