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
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.border.TitledBorder;


public class MainUI  extends JFrame
{
	private JLabel monthLabel;
	private JLabel leftArrowLabel;
	private JLabel rightArrowLabel;
	
	private JLabel childLabel;
	private JComboBox<String> selectChildComboBox;
	
	private JButton monthBtn; 
	private JButton yearBtn;
	private JButton toParentBtn;
	private JButton diaryBtn;
	
	private JLabel dayLabel[];
	
	private GregorianCalendar cal;
	private int year;
	private int month;
	private int day;
	
	private JPanel[] moneyPanels;
	//날짜 패널 
	private JPanel[] datePanels;
	private JLabel[] dateLabels;
	private JPanel calendarPanel;
	
	//UI
	private MonthHistoryUI monthUI;
	private YearHistoryUI yearUI;
	private DayAccountUI dayUI;
	private DiaryUI diaryUI;
	private CommunicationWithParent toParentUI;
	private ChildManage toChildUI;
	private int code;

	private JProgressBar goalBar;
	private JLabel balanceLabel;
	private JLabel realBalanceLabel;
	public MainUI ()
	{
		super("천방저축");

		//이미지 담을 패널
		JPanel imagePanel = new JPanel();
		
		// 월 & 목표 & 이미지 담을 패널 
		JPanel totalPanel = new JPanel();
		totalPanel.setLayout(new BoxLayout(totalPanel, BoxLayout.Y_AXIS));
		JPanel topPanel = new JPanel();
		topPanel.setLayout(new GridLayout(1, 3, 20, 20));
		
		//월 & 화살표 
		//오늘 날짜 계산  
		cal = (GregorianCalendar) Calendar.getInstance();
		year = cal.get(Calendar.YEAR);
		month = cal.get(Calendar.MONTH);
		day = cal.get(Calendar.DATE);
		
		//cal.set(Calendar.DATE, 1);
/*
 * UI생성
 * */
		code = 2; //임시 등록코드
		int childCode = 1;

		monthUI = new MonthHistoryUI(this,cal,1);
		yearUI = new YearHistoryUI(this,cal,1);
		/*
		 * 등록코드 줘야
		 */
		dayUI = new DayAccountUI(this, cal, 1);
		diaryUI = new DiaryUI(this, cal,1);
		toChildUI = new ChildManage(this,cal, childCode);
		toParentUI = new CommunicationWithParent(this, code);
		
		monthUI.setVisible(false);
		yearUI.setVisible(false);
		dayUI.setVisible(false);
		diaryUI.setVisible(false);
		toChildUI.setVisible(false);
		toParentUI.setVisible(false);
//여기까지
		
		Container contentPane = getContentPane();
		
		//월 레이블 
		monthLabel = new JLabel(Integer.toString(month + 1) + "월");
		monthLabel.setFont(new Font("Courier New", Font.BOLD, 50));
		
		//화살표 레이블 
		leftArrowLabel = new JLabel("< ");		
		leftArrowLabel.setFont(new Font("Courier New", Font.BOLD, 30));
		rightArrowLabel = new JLabel(" >");
		rightArrowLabel.setFont(new Font("Courier New", Font.BOLD, 30));
		leftArrowLabel.addMouseListener(new PreviousCalendarListener());
		rightArrowLabel.addMouseListener(new NextCalendarListener());
		//패널에 월, 화살표들 붙임 
		JPanel monthArrowPanel = new JPanel();
		monthArrowPanel.add(leftArrowLabel);
		monthArrowPanel.add(monthLabel);
		monthArrowPanel.add(rightArrowLabel);
/**
 * 추가부분 
 */   
		childLabel = new JLabel("우리 아이는");
		selectChildComboBox = new JComboBox<String>(new String[]{"------"} ); //자녀들 정보를 받아와야한다 
		JPanel selectChildPanel = new JPanel();
		selectChildPanel.add(childLabel);
		selectChildPanel.add(selectChildComboBox);
	
		JPanel monthArrowChildPanel = new JPanel(new BorderLayout());
		monthArrowChildPanel.add(monthArrowPanel);
		monthArrowChildPanel.add(selectChildPanel, BorderLayout.SOUTH);
/**
 * 여기까지 추가 		
 */
		
		// 목표 패널, 목표 바
		JPanel goalBalancePanel = new JPanel();

		JPanel goalPanel = new JPanel();
		goalBar = new JProgressBar();
		goalPanel.add(goalBar);

		// 범위 0 ~ 100 으로 설정
		goalBar.setMinimum(0);
		goalBar.setMaximum(100);
		// 보이도록 세팅
		goalBar.setStringPainted(true);
		goalBar.setValue(70);// 현재 퍼센트 보이게 70하면 70%가 보임
		goalBar.setForeground(Color.orange);// 프로그레스바 색깔
		// goalBar.setStringPainted(false);//프로그레스바에 70이 들어가면 그래프에 70%라고 표시되는데
		// 그거 지우는 코드

		// 잔액 표시 레이블
		balanceLabel = new JLabel("남은 용돈 ");
		realBalanceLabel = new JLabel("1000원"); // ////*******잔액 받아서 표시할 부분

		if (code == 2) // 부모이면
			balanceLabel.setText("잔액 ");
		JPanel balancePanel = new JPanel();
		balancePanel.add(balanceLabel);
		balancePanel.add(realBalanceLabel);

		goalBalancePanel.add(goalPanel);
		goalBalancePanel.add(balancePanel);
		
		/************나중에 데이터 수정**************/
		if(code == 1) //어린이이면 
			goalBalancePanel.setBorder(new TitledBorder("박동익 어린이!"));
		else //부모이면 
			goalBalancePanel.setBorder(new TitledBorder("박동익 부모님!"));
/**
 * 여기부터 수정 		
 */
		topPanel.add(imagePanel);
		//부모일 경우 
		if(code == 1)  
			topPanel.add(monthArrowPanel); 
		 //자녀일 경우 
		else
			topPanel.add(monthArrowChildPanel);
		topPanel.add(goalBalancePanel);
/**
 * 여기까지 
 */
		// 달력을 담을 달력 패널 
		calendarPanel = new JPanel();
		calendarPanel.setLayout(new GridLayout(6, 7, 5, 5));
		//남은 금액 & 일일한도 & 칭찬하기 들어갈 패널 
		//최대 일 수 31 + 6(시작요일 모르니까 가장 큰 값으로 설정)
		moneyPanels = new JPanel[37];
		//날짜 패널 
		datePanels = new JPanel[37];
		dateLabels = new JLabel[37];
			
		//이 패널들을 생성해주고 
		for(int i = 0; i < 37; i++)
		{
			datePanels[i] = new JPanel();
			moneyPanels[i] = new JPanel();
			dateLabels[i] = new JLabel();
			
			dateLabels[i].addMouseListener(new DayAccountLabelListener());
		}
		int j;
		//1일 전에 있는 패널들은 생성과 세팅은 해줌 (추후 사용할 예정이기 때문)
		//여기서는 보여주지 않음 
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
		//그 월의 일수에 해당하는 패널 세팅 
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
		
		//그 월의 일 수를 벗어난 패널들은 안 보이게 
		for(int i = 36; i >  cal.getActualMaximum(Calendar.DATE); i--)
			moneyPanels[i].setVisible(false);
		
		//datePanels[cal.get(Calendar.DAY_OF_WEEK) + day - 1].setBackground(Color.ORANGE);

		//요일 패널 
		JPanel dayPanel = new JPanel();
		dayPanel.setLayout(new GridLayout(2, 7, 10, 10));
		dayLabel = new JLabel[7];
		
		for(int i = 0; i < 7; i++)
		{
			dayLabel[i] = new JLabel();
			dayPanel.add(new JPanel());
		}
		
		dayLabel[0].setText("일");
		dayLabel[1].setText("월");
		dayLabel[2].setText("화");
		dayLabel[3].setText("수");
		dayLabel[4].setText("목");
		dayLabel[5].setText("금");
		dayLabel[6].setText("토");
		
		for(int i = 0; i < 7; i++)
		{
			dayLabel[i].setFont(new Font("Courier New", Font.BOLD, 15));
			dayLabel[i].setHorizontalAlignment(JLabel.CENTER);
			dayPanel.add(dayLabel[i]);
		}
		totalPanel.add(new JPanel());
		totalPanel.add(topPanel);
		totalPanel.add(dayPanel);
		
		//버튼에 들어갈 이미지 아이콘 생성 및 사이즈 조절 
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
		
		ImageIcon toParentIcon = new ImageIcon("heart.png");
		image = toParentIcon.getImage(); // transform it 
		newimg = image.getScaledInstance(50, 50,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
		toParentIcon = new ImageIcon(newimg);  // transform it back
		//버튼들 생성 
/**
 * 	
 */
		diaryBtn = new JButton(" 일기장", diaryIcon);
		diaryBtn.setBackground(new Color(255, 135, 0));
		diaryBtn.addActionListener(new DiaryBtnListener());
		yearBtn = new JButton(" 연도별보기", yearIcon);
		yearBtn.setBackground(new Color(255, 135, 0));
		yearBtn.addActionListener(new YearAccountBtnListener());
		monthBtn = new JButton(" 월별보기", monthIcon);
		monthBtn.setBackground(new Color(255, 135, 0));
		monthBtn.addActionListener(new MonthAccountBtnListener());
		
		toParentBtn = new JButton(" 엄마! 아빠!", toParentIcon);
		toParentBtn.setBackground(new Color(255, 135, 0));
		toParentBtn.addActionListener(new ToParentBtnListener());
		
		//부모일 경우 
		if(code == 2)
			toParentBtn.setText("자녀 관리");
		
		//버튼들 담을 버튼 패널 생성 
		JPanel buttonsPanel = new JPanel();
		buttonsPanel.add(monthBtn);
		buttonsPanel.add(yearBtn);
		buttonsPanel.add(toParentBtn);
		buttonsPanel.add(diaryBtn);
		
		contentPane.add(totalPanel, BorderLayout.NORTH);
		//contentPane.add(dayPanel);
		contentPane.add(calendarPanel);
		contentPane.add(buttonsPanel, BorderLayout.SOUTH);
		
		setPreferredSize(new Dimension(700, 700));
		setResizable(false);
		setLocation(200,100);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		pack();
		setVisible(true);
	}
	/**
	 * 마우스리스너를 마우스 어댑터로 다 바꿈 
	 */
		//왼쪽 화살표 이전 달로 가기 
		private class PreviousCalendarListener extends MouseAdapter
		{
			public void mouseClicked(MouseEvent e)
			{
				if(month != 0)
				{
					leftArrowLabel.setEnabled(true);
					rightArrowLabel.setEnabled(true);
					
				//패널 모두 안보이게 먼저 해놓고 
				for(int j = 0; j < 37; j++)
					moneyPanels[j].setVisible(false);
				//지난 달로 달력을 맞춰놓고 
				cal.set(year, --month, 1);
				//레이블도 지난 월로 세팅 
				monthLabel.setText(Integer.toString(month + 1) + "월");
				
				//그 월의 요일의 시작을 구해놓고 (배열에 사용위해 -1)
				int j = cal.get(Calendar.DAY_OF_WEEK)-1;
				//그 월의 일 수 만큼 포문 돌림 
				for(int i = 0; i < cal.getActualMaximum(Calendar.DATE); i++, j++)
				{
					//그 월의 요일의 시작부터 일 수 만큼 보이게 하고 레이블 세팅 
					moneyPanels[j].setVisible(true);
					dateLabels[j].setText(Integer.toString(i + 1));
				}
				}

				//1월 이전으로 가지못하게 레이블 비활성화 
				else
					leftArrowLabel.setEnabled(false);
				
			}
		}
		//오른쪽 화살표 다음달로 가기 
		private class NextCalendarListener extends MouseAdapter
		{
			public void mouseClicked(MouseEvent e)
			{
				if(month != 11)
				{
					leftArrowLabel.setEnabled(true);
					rightArrowLabel.setEnabled(true);
					
				for(int j = 0; j < 37; j++)
					moneyPanels[j].setVisible(false);
				
				cal.set(year, ++month, 1);
				monthLabel.setText(Integer.toString(month + 1) + "월");
				int j = cal.get(Calendar.DAY_OF_WEEK)-1;
			
				for(int i = 0; i < cal.getActualMaximum(Calendar.DATE); i++, j++)
				{
					moneyPanels[j].setVisible(true);
					dateLabels[j].setText(Integer.toString(i + 1));
				}
				}
				//12월 이후로 가지못하게 레이블 비활성화 
				else
					rightArrowLabel.setEnabled(false);
			}
		}
		//날짜 클릭 시 일일 결산 보기 
		private class DayAccountLabelListener extends MouseAdapter
		{
			public void mouseClicked(MouseEvent e)
			{
				System.out.println(((JLabel)e.getSource()).getText());
				GregorianCalendar calendar = new GregorianCalendar();
				calendar.set(year, month, Integer.parseInt(((JLabel)e.getSource()).getText()));
				dayUI.getCalendar(calendar);
				dayUI.setVisible(true);
			}
		}
		//월 별 결산 
		private class MonthAccountBtnListener implements ActionListener
		{
			public void actionPerformed(ActionEvent e) 
			{
				monthUI.setVisible(true);
			}
		}
		//연도 별 결산 
		private class YearAccountBtnListener implements ActionListener
		{
			public void actionPerformed(ActionEvent e) 
			{
				yearUI.setVisible(true);
			}
		}//엄마! 아빠! 
		private class ToParentBtnListener implements ActionListener
		{
			public void actionPerformed(ActionEvent e) 
			{
				//부모자녀구분해야
				if(code != 1){
					toChildUI.setVisible(true);
				}
				else
					toParentUI.setVisible(true);
			}
		}//일기장
		private class DiaryBtnListener implements ActionListener
		{
			public void actionPerformed(ActionEvent e) 
			{
				diaryUI.setVisible(true);
			}
		}
		
}
