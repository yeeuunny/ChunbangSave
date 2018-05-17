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
	//?���? ?��?�� 
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
		setTitle("천방??�?");

		//?��미�? ?��?�� ?��?��
		JPanel imagePanel = new JPanel();
		
		// ?�� & 목표 & ?��미�? ?��?�� ?��?�� 
		JPanel totalPanel = new JPanel();
		totalPanel.setLayout(new BoxLayout(totalPanel, BoxLayout.Y_AXIS));
		//
		JPanel topPanel = new JPanel();
		topPanel.setLayout(new GridLayout(1, 3, 20, 20));
		
		//?�� & ?��?��?�� 
		//?��?�� ?���? 계산  
		cal = (GregorianCalendar) Calendar.getInstance();
		year = cal.get(Calendar.YEAR);
		month = cal.get(Calendar.MONTH);
		day = cal.get(Calendar.DATE);
		
		//cal.set(Calendar.DATE, 1);
/*
 * UI?��?��
 * */
		
		//code = 2; //?��?�� ?��록코?��
		monthUI = new MonthHistoryUI(null, cal,1);
		yearUI = new YearHistoryUI(null,cal,1);
		monthUI.setVisible(false);
		yearUI.setVisible(false);
		diaryUI = new DiaryUI(null,cal,1);
		diaryUI.setVisible(false);
		/*
		 * ?��록코?�� 줘야
		 */
		
		dayUI = new ShowChildDayAccountUI(null, cal, 1);		
		dayUI.setVisible(false);
//?��기까�?
		
		
		Container contentPane = getContentPane();
		
		//?�� ?��?���? 
		monthLabel = new JLabel(Integer.toString(month + 1) + "?��");
		monthLabel.setFont(new Font("Courier New", Font.BOLD, 50));
		
		//?��?��?�� ?��?���? 
		leftArrowLabel = new JLabel("< ");		
		leftArrowLabel.setFont(new Font("Courier New", Font.BOLD, 30));
		rightArrowLabel = new JLabel(" >");
		rightArrowLabel.setFont(new Font("Courier New", Font.BOLD, 30));
		leftArrowLabel.addMouseListener(new PreviousCalendarListener());
		rightArrowLabel.addMouseListener(new NextCalendarListener());
		
		//?��?��?�� ?��, ?��?��?��?�� 붙임 
		JPanel monthArrowPanel = new JPanel();
		monthArrowPanel.add(leftArrowLabel);
		monthArrowPanel.add(monthLabel);
		
		// 목표 ?��?��, 목표 �?
		JPanel goalBalancePanel = new JPanel();

		JPanel goalPanel = new JPanel();
		goalBar = new JProgressBar();
		goalPanel.add(goalBar);

		// 범위 0 ~ 100 ?���? ?��?��
		goalBar.setMinimum(0);
		goalBar.setMaximum(100);
		// 보이?���? ?��?��
		goalBar.setStringPainted(true);
		goalBar.setValue(70);// ?��?�� ?��?��?�� 보이�? 70?���? 70%�? 보임
		goalBar.setForeground(Color.orange);// ?��로그?��?���? ?���?
		// goalBar.setStringPainted(false);//?��로그?��?��바에 70?�� ?��?���?�? 그래?��?�� 70%?���? ?��?��?��?��?��
		// 그거 �??��?�� 코드

		// ?��?�� ?��?�� ?��?���?
		balanceLabel = new JLabel("?��?? ?��?�� ");
		realBalanceLabel = new JLabel("1000?��"); // ////*******?��?�� 받아?�� ?��?��?�� �?�?

		if (code == 2) // �?모이�?
			balanceLabel.setText("?��?�� ");
		JPanel balancePanel = new JPanel();
		balancePanel.add(balanceLabel);
		balancePanel.add(realBalanceLabel);

		goalBalancePanel.add(goalPanel);
		goalBalancePanel.add(balancePanel);

		/************ ?��중에 ?��?��?�� ?��?�� **************/
	
		goalBalancePanel.setBorder(new TitledBorder("박동?�� ?��린이!"));
		
		/**
		 * ?��기�??�� ?��?��
		 */
		topPanel.add(imagePanel);
		topPanel.add(monthArrowPanel); 
		topPanel.add(goalPanel);

		// ?��?��?�� ?��?�� ?��?�� ?��?�� 
		calendarPanel = new JPanel();
		calendarPanel.setLayout(new GridLayout(6, 7, 5, 5));
		//?��?? 금액 & ?��?��?��?�� & �?찬하�? ?��?���? ?��?�� 
		//최�? ?�� ?�� 31 + 6(?��?��?��?�� 모르?���? �??�� ?�� 값으�? ?��?��)
		moneyPanels = new JPanel[37];
		//?���? ?��?�� 
		datePanels = new JPanel[37];
		dateLabels = new JLabel[37];
			
		//?�� ?��?��?��?�� ?��?��?��주고 
		for(int i = 0; i < 37; i++)
		{
			datePanels[i] = new JPanel();
			moneyPanels[i] = new JPanel();
			dateLabels[i] = new JLabel();
			
			dateLabels[i].addMouseListener(new DayAccountLabelListener());
		}
		int j;
		//1?�� ?��?�� ?��?�� ?��?��?��?? ?��?���? ?��?��?? ?���? (추후 ?��?��?�� ?��?��?���? ?���?)
		//?��기서?�� 보여주�? ?��?�� 
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
		//�? ?��?�� ?��?��?�� ?��?��?��?�� ?��?�� ?��?�� 
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
		
		//�? ?��?�� ?�� ?���? 벗어?�� ?��?��?��?? ?�� 보이�? 
		for(int i = 36; i >  cal.getActualMaximum(Calendar.DATE); i--)
			moneyPanels[i].setVisible(false);
		
		//datePanels[cal.get(Calendar.DAY_OF_WEEK) + day - 1].setBackground(Color.ORANGE);

		//?��?�� ?��?�� 
		JPanel dayPanel = new JPanel();
		dayPanel.setLayout(new GridLayout(2, 7, 10, 10));
		dayLabel = new JLabel[7];
		
		for(int i = 0; i < 7; i++)
		{
			dayLabel[i] = new JLabel();
			dayPanel.add(new JPanel());
		}
		
		
		dayLabel[0].setText("?��");
		dayLabel[1].setText("?��");
		dayLabel[2].setText("?��");
		dayLabel[3].setText("?��");
		dayLabel[4].setText("�?");
		dayLabel[5].setText("�?");
		dayLabel[6].setText("?��");
		
		for(int i = 0; i < 7; i++)
		{
			dayLabel[i].setFont(new Font("Courier New", Font.BOLD, 15));
			dayLabel[i].setHorizontalAlignment(JLabel.CENTER);
			dayPanel.add(dayLabel[i]);
		}
		totalPanel.add(new JPanel());
		totalPanel.add(topPanel);
		totalPanel.add(dayPanel);
		
		//버튼?�� ?��?���? ?��미�? ?��?���? ?��?�� �? ?��?���? 조절 
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
		//버튼?�� ?��?�� 
/**
 * 	
 */
		diaryBtn = new JButton(" ?��기장", diaryIcon);
		diaryBtn.setBackground(new Color(255, 135, 0));
		yearBtn = new JButton(" ?��?��별보�?", yearIcon);
		yearBtn.setBackground(new Color(255, 135, 0));
		yearBtn.addActionListener(new YearAccountBtnListener());
		monthBtn = new JButton(" ?��별보�?", monthIcon);
		monthBtn.setBackground(new Color(255, 135, 0));
		monthBtn.addActionListener(new MonthAccountBtnListener());
		
		closeBtn = new JButton("?���?", closeIcon);
		closeBtn.setBackground(new Color(255, 135, 0));
		//closeBtn.addActionListener(new closeBtnListener());
		closeBtn.addActionListener(new CancelBtnListener(this));
		diaryBtn.addActionListener(new DiaryBtnListener());
		
		//버튼?�� ?��?�� 버튼 ?��?�� ?��?�� 
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
	 * 마우?��리스?���? 마우?�� ?��?��?���? ?�� 바꿈 
	 */
		//?���? ?��?��?�� ?��?�� ?���? �?�? 
		private class PreviousCalendarListener extends MouseAdapter
		{
			public void mouseClicked(MouseEvent e)
			{
				if(month != 0)
				{
					leftArrowLabel.setEnabled(true);
					rightArrowLabel.setEnabled(true);
					
				//?��?�� 모두 ?��보이�? 먼�? ?��?���? 
				for(int j = 0; j < 37; j++)
					moneyPanels[j].setVisible(false);
				//�??�� ?���? ?��?��?�� 맞춰?���? 
				cal.set(year, --month, 1);
				//?��?��블도 �??�� ?���? ?��?�� 
				monthLabel.setText(Integer.toString(month + 1) + "?��");
				
				//�? ?��?�� ?��?��?�� ?��?��?�� 구해?���? (배열?�� ?��?��?��?�� -1)
				int j = cal.get(Calendar.DAY_OF_WEEK)-1;
				//�? ?��?�� ?�� ?�� 만큼 ?���? ?���? 
				for(int i = 0; i < cal.getActualMaximum(Calendar.DATE); i++, j++)
				{
					//�? ?��?�� ?��?��?�� ?��?���??�� ?�� ?�� 만큼 보이�? ?���? ?��?���? ?��?�� 
					moneyPanels[j].setVisible(true);
					dateLabels[j].setText(Integer.toString(i + 1));
				}
				}

				//1?�� ?��?��?���? �?�?못하�? ?��?���? 비활?��?�� 
				else
					leftArrowLabel.setEnabled(false);
				
			}
		}

	// ?��른쪽 ?��?��?�� ?��?��?���? �?�?
	private class NextCalendarListener extends MouseAdapter {
		public void mouseClicked(MouseEvent e) {
			if (month != 11) {
				leftArrowLabel.setEnabled(true);
				rightArrowLabel.setEnabled(true);

				for (int j = 0; j < 37; j++)
					moneyPanels[j].setVisible(false);

				cal.set(year, ++month, 1);
				monthLabel.setText(Integer.toString(month + 1) + "?��");
				int j = cal.get(Calendar.DAY_OF_WEEK) - 1;

				for (int i = 0; i < cal.getActualMaximum(Calendar.DATE); i++, j++) {
					moneyPanels[j].setVisible(true);
					dateLabels[j].setText(Integer.toString(i + 1));
				}
			}
			// 12?�� ?��?���? �?�?못하�? ?��?���? 비활?��?��
			else
				rightArrowLabel.setEnabled(false);
		}
	}

	// ?���? ?���? ?�� ?��?�� 결산 보기
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

	// ?�� �? 결산
	private class MonthAccountBtnListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			monthUI.setVisible(true);
		}
	}

	// ?��?�� �? 결산
	private class YearAccountBtnListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			yearUI.setVisible(true);
		}
	}// ?���?! ?���?!

	/*private class closeBtnListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			setVisible(false);
		}
	}// ?��기장*/

	private class DiaryBtnListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			diaryUI.setVisible(true);
		}
	}
			
}
