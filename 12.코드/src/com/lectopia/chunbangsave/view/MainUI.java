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
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.LinkedList;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;

import com.lectopia.chunbangsave.vo.CashBookDetailVO;
import com.lectopia.chunbangsave.vo.MonthCashBookDetailVO;
import com.lectopia.chunbangsave.vo.MonthCashBookVO;
import com.lectopia.chunbangsave.vo.QuarterAdminManager;
import com.lectopia.chunbangsave.vo.QuarterManager;
import com.lectopia.chunbangsave.vo.QuarterNo;
import com.lectopia.chunbangsave.vo.SavingGoalVO;


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

	private static JProgressBar goalBar;
	private JLabel balanceLabel;
	/**
	 * 현재 잔액 label
	 */
	private static JLabel realBalanceLabel;
	
	private JLabel[] amountLabels;
	private int quarter; //분기정보 
	public MainUI (MonthCashBookVO monthCashBookVO,SavingGoalVO savingGoal)
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
/**************/
		//분기계산
		quarter = (int)Math.ceil( (month + 1) / 3.0 );
/**************/
		
		addWindowListener(new UserWindowAdapter());
/*
 * UI생성
 * */
		code = 2; //임시 등록코드
		int childCode = 2;

		monthUI = new MonthHistoryUI(this,cal,code);
		yearUI = new YearHistoryUI(this,cal,code);
		/*
		 * 등록코드 줘야
		 */
		dayUI = new DayAccountUI(this, cal, code);
		diaryUI = new DiaryUI(this, cal,code);
		toChildUI = new ChildManage(this,cal, childCode);
		toParentUI = new CommunicationWithParent(this, code,cal);
		
		monthUI.setVisible(false);
		yearUI.setVisible(false);
		dayUI.setVisible(false);
		diaryUI.setVisible(false);
		toChildUI.setVisible(false);
		toParentUI.setVisible(false);
//여기까지
		
		Container contentPane = getContentPane();
		cal.set(Calendar.DATE, 1);
		
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
  
		JPanel monthArrowChildPanel = new JPanel(new BorderLayout());

		selectChildComboBox = new JComboBox<String>(new String[]{"------", "박동익","박성일","김예은"} ); //자녀들 정보를 받아와야한다 
		JPanel selectChildPanel = new JPanel();
		childLabel = new JLabel(" ");
		JLabel childLabel2 = new JLabel(" ");

		// 목표 패널, 목표 바
		JPanel goalBalancePanel = new JPanel();
		if(code == 2)
		{
			childLabel.setText("우리아이는 ");
			selectChildPanel.add(childLabel);
			selectChildPanel.add(selectChildComboBox);
			selectChildComboBox.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					goalBalancePanel.setBorder(new TitledBorder(selectChildComboBox.getSelectedItem().toString()+" 부모님!"));
					
				}
			});
		}
		else
		{
			selectChildPanel.add(childLabel);
			selectChildPanel.add(childLabel2);
		}
		monthArrowChildPanel.add(monthArrowPanel);
		monthArrowChildPanel.add(selectChildPanel, BorderLayout.SOUTH);

		
/**
 * 여기까지 추가 		
 */

		JPanel goalPanel = new JPanel();
		JLabel goalTitleLabel = new JLabel(savingGoal.getGoalTitle());
		goalBar = new JProgressBar();
		goalPanel.add(goalBar);
		goalPanel.add(goalTitleLabel);
		
		// 범위 0 ~ 100 으로 설정
		goalBar.setMinimum(0);
		goalBar.setMaximum(100);
		// 보이도록 세팅
		goalBar.setStringPainted(true);
		int goalPercent =(int)(Double.parseDouble(QuarterManager.getCurrentAmount())*100/savingGoal.getGoalAmount());
		goalBar.setValue(goalPercent);// 현재 퍼센트 보이게 70하면 70%가 보임
		goalBar.setForeground(Color.orange);// 프로그레스바 색깔
		// goalBar.setStringPainted(false);//프로그레스바에 70이 들어가면 그래프에 70%라고 표시되는데
		// 그거 지우는 코드

		// 잔액 표시 레이블
		balanceLabel = new JLabel("남은 용돈 ");
		realBalanceLabel = new JLabel(QuarterManager.getCurrentAmount()); // ////*******잔액 받아서 표시할 부분
/***************/
		realBalanceLabel.setText(QuarterManager.getCurrentAmount());

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
			goalBalancePanel.setBorder(new TitledBorder("---부모님!"));
/**
 * 여기부터 수정 		
 */
		topPanel.add(imagePanel);
		//자녀일 경우 
		//if(code == 1)
		//	topPanel.add(monthArrowPanel); 
		 //부모일 경우 
		//else
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
		amountLabels = new JLabel[37];
			
		//이 패널들을 생성해주고 
		for(int i = 0; i < 37; i++)
		{
			datePanels[i] = new JPanel();
			moneyPanels[i] = new JPanel();
			dateLabels[i] = new JLabel("");
/*****************11/15*********/			
			amountLabels[i] = new JLabel("");
			amountLabels[i].setFont(new Font("맑은고딕",Font.PLAIN,8));
			dateLabels[i].addMouseListener(new DayAccountLabelListener());
		}
		int j;
		//1일 전에 있는 패널들은 생성과 세팅은 해줌 (추후 사용할 예정이기 때문)
		//여기서는 보여주지 않음 
		for(j = 0; j < cal.get(Calendar.DAY_OF_WEEK)- 1 ; j++)
		{	
			datePanels[j].add(dateLabels[j]);
			moneyPanels[j].setLayout(new BorderLayout());
			moneyPanels[j].add(datePanels[j], BorderLayout.SOUTH);
			moneyPanels[j].add(amountLabels[j]);
			amountLabels[j].setHorizontalAlignment(SwingConstants.CENTER);
			
			datePanels[j].setBackground(new Color(15, 146, 2));
			datePanels[j].setBorder(BorderFactory.createLineBorder(Color.black));
			
			moneyPanels[j].setBackground(Color.WHITE);
			moneyPanels[j].setBorder(BorderFactory.createLineBorder(Color.black, 2));
			

			calendarPanel.add(moneyPanels[j]);
			moneyPanels[j].setVisible(false);
		}
		/*****************11/15*********/
		
		//그 월의 일수에 해당하는 패널 세팅
		for(int i = 0; j < 37; i++, j++)
		{
			moneyPanels[j].setLayout(new BorderLayout());
			moneyPanels[j].add(datePanels[j], BorderLayout.SOUTH);
			moneyPanels[j].add(amountLabels[j]);
			amountLabels[j].setHorizontalAlignment(SwingConstants.CENTER);


/*****************11/15*********일일한도&칭찬하기 추가*/
		//	System.out.println("** i = " + monthCashBookVOMap.get("r1").get(0).get(i + 1));
			if(monthCashBookVO.get(i+1) != null)
			{
				if(code == 1){
					/* 칭찬하기 = 1 , 응원하기 = 2, 아무것도 아니면 = 0 */
					if(monthCashBookVO.get(i + 1).getCompliment().equals("1")){
						ImageIcon iconLogo = new ImageIcon("thumbs.png");
						Image temp = iconLogo.getImage();
						Image iconLogo2 = temp.getScaledInstance(30, 30, java.awt.Image.SCALE_SMOOTH);
						ImageIcon tem = new ImageIcon(iconLogo2);
						amountLabels[j].setIcon(tem);
					}
					else if(monthCashBookVO.get(i + 1).getCompliment().equals("2"))
					{	
						ImageIcon iconLogo = new ImageIcon("x.png");
						Image temp = iconLogo.getImage();
						Image iconLogo2 = temp.getScaledInstance(30, 30, java.awt.Image.SCALE_SMOOTH);
						ImageIcon tem = new ImageIcon(iconLogo2);
						amountLabels[j].setIcon(tem);
					}
					else
						amountLabels[j].setDisabledIcon(new ImageIcon("thumbs.png"));
				}
				else
					amountLabels[j].setFont(new Font("맑은고딕",Font.BOLD,10));
				amountLabels[j].setText(monthCashBookVO.get(i + 1).getDayLimit());
			}
				//moneyPanels[j].add(new JLabel(monthCashBookVO.get(i+1).getDayLimit()));
			
/***************************/
			dateLabels[j].setText(Integer.toString(i + 1));
			datePanels[j].add(dateLabels[j]);
			
			datePanels[j].setBackground(new Color(15, 146, 2));
			datePanels[j].setBorder(BorderFactory.createLineBorder(Color.black));

			moneyPanels[j].setBackground(Color.WHITE);
			moneyPanels[j].setBorder(BorderFactory.createLineBorder(Color.black, 2));
			calendarPanel.add(moneyPanels[j]);
		}		
		
		//그 월의 일 수를 벗어난 패널들은 안 보이게 
		for(int i = 36; i >  cal.getActualMaximum(Calendar.DATE) + 1; i--)
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
	/////////////////////////////////////////////////////////////////////////////////////////////////
	/*	dayPanel.setBackground(Color.yellow);
		topPanel.setBackground(Color.yellow);
		totalPanel.setBackground(Color.yellow);
		calendarPanel.setBackground(Color.yellow);
		buttonsPanel.setBackground(Color.yellow);*/
		
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
/******************* 지난 달의 일일한도 & 칭찬하기 로딩 **********/
			
			//분기가 같은지 확인하여 달라졌으면 분기를 바꿈 
			if(quarter != (int)Math.ceil((month + 1) / 3.0))
				quarter = (int)Math.ceil((month + 1) / 3.0);
			int[] quarterMonth = new int[3];
			QuarterNo num = null;
			
			switch(quarter)
			{
			case 1: 
				quarterMonth[0] = 1; quarterMonth[1] = 2; quarterMonth[2] = 3;
				num = QuarterNo.FIRST_QUARTER;
				break;
			case 2: 
				quarterMonth[0] = 4; quarterMonth[1] = 5; quarterMonth[2] = 6;
				num = QuarterNo.SECOND_QUARTER;
				break;
			case 3: 
				quarterMonth[0] = 7; quarterMonth[1] = 8; quarterMonth[2] = 9;
				num = QuarterNo.THIRD_QUARTER;
				break;
			case 4: 
				quarterMonth[0] = 10; quarterMonth[1] = 11; quarterMonth[2] = 12;
				num = QuarterNo.FOURTH_QUARTER;
				break;	
			}
			//해당 분기의 쿼터 매니저 있는지 확인하여 없으면 생성, 로딩 
			if(QuarterAdminManager.getTotManager()[quarter - 1] == null)
			{
				System.out.println("test");
				QuarterAdminManager.getTotManager()[quarter - 1] = 
						new QuarterManager(num);
						QuarterAdminManager.getTotManager()[quarter - 1].putDayInfo( "p1",
								QuarterManager.getDataLoadManager().loadThreeMonthCBUnique("f0001", "p1", quarterMonth));
			}
			//해당 월의 일일 정보 가져옴 
			MonthCashBookVO monthCashBookVO = null;
			System.out.println("%%%" + QuarterAdminManager.getTotManager()[quarter - 1].getDayInfo());
			if(QuarterAdminManager.getTotManager()[quarter - 1].getDayInfo().get("p1") != null)
			{
				monthCashBookVO = 
					QuarterAdminManager.getTotManager()[quarter - 1].getDayInfo().get("p1").get(month+3 - 3*quarter);
			}
			
			//그 월의 요일의 시작을 구해놓고 (배열에 사용위해 -1)
			int j = cal.get(Calendar.DAY_OF_WEEK)-1;
			//그 월의 일 수 만큼 포문 돌림 
			for(int i = 0; i < cal.getActualMaximum(Calendar.DATE); i++, j++)
			{
				amountLabels[j].setText("");

				//그 월의 요일의 시작부터 일 수 만큼 보이게 하고 레이블 세팅 
				dateLabels[j].setText(Integer.toString(i + 1));
				//해당 일의 가계부 일일정보가 존재할 시, 일일한도 금액도 바꿔줌
				if(monthCashBookVO != null)
				{
					if(monthCashBookVO.get(i+1) != null)
					{
						if(code == 1){
							/* 칭찬하기 = 1 , 응원하기 = 2, 아무것도 아니면 = 0 */
							if(monthCashBookVO.get(i + 1).getCompliment().equals("1")){
								System.out.println("MainUI-칭찬하기_608line");
								ImageIcon iconLogo = new ImageIcon("thumbs.png");
								Image temp = iconLogo.getImage();
								Image iconLogo2 = temp.getScaledInstance(30, 30, java.awt.Image.SCALE_SMOOTH);
								ImageIcon tem = new ImageIcon(iconLogo2);
								amountLabels[j].setIcon(tem);
							}
							else if(monthCashBookVO.get(i + 1).getCompliment().equals("2"))
							{	
								System.out.println("MainUI-응원하기_617line");
								ImageIcon iconLogo = new ImageIcon("x.png");
								Image temp = iconLogo.getImage();
								Image iconLogo2 = temp.getScaledInstance(30, 30, java.awt.Image.SCALE_SMOOTH);
								ImageIcon tem = new ImageIcon(iconLogo2);
								amountLabels[j].setIcon(tem);
							}
							else
								amountLabels[j].setDisabledIcon(new ImageIcon("thumbs.png"));
						}
						amountLabels[j].setText(monthCashBookVO.get(i + 1).getDayLimit());
					}
				}
			
				moneyPanels[j].setVisible(true);
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
/******************* 지난 달의 일일한도 & 칭찬하기 로딩 **********/
			
			//분기가 같은지 확인하여 달라졌으면 분기를 바꿈 
			if(quarter != (int)Math.ceil((month + 1) / 3.0))
				quarter = (int)Math.ceil((month + 1) / 3.0);
			int[] quarterMonth = new int[3];
			QuarterNo num = null;
			
			switch(quarter)
			{
			case 1: 
				quarterMonth[0] = 1; quarterMonth[1] = 2; quarterMonth[2] = 3;
				num = QuarterNo.FIRST_QUARTER;
				break;
			case 2: 
				quarterMonth[0] = 4; quarterMonth[1] = 5; quarterMonth[2] = 6;
				num = QuarterNo.SECOND_QUARTER;
				break;
			case 3: 
				quarterMonth[0] = 7; quarterMonth[1] = 8; quarterMonth[2] = 9;
				num = QuarterNo.THIRD_QUARTER;
				break;
			case 4: 
				quarterMonth[0] = 10; quarterMonth[1] = 11; quarterMonth[2] = 12;
				num = QuarterNo.FOURTH_QUARTER;
				break;	
			}
			//해당 분기의 쿼터 매니저 있는지 확인하여 없으면 생성, 로딩 
			if(QuarterAdminManager.getTotManager()[quarter - 1] == null)
			{
				System.out.println("test");
				QuarterAdminManager.getTotManager()[quarter - 1] = 
						new QuarterManager(num);
						QuarterAdminManager.getTotManager()[quarter - 1].putDayInfo( "p1",
								QuarterManager.getDataLoadManager().loadThreeMonthCBUnique("f0001", "p1", quarterMonth));
			}
			//해당 월의 일일 정보 가져옴 
			MonthCashBookVO monthCashBookVO = null;
			System.out.println("%%%" + QuarterAdminManager.getTotManager()[quarter - 1].getDayInfo());
			if(QuarterAdminManager.getTotManager()[quarter - 1].getDayInfo().get("p1") != null)
			{
				monthCashBookVO = 
					QuarterAdminManager.getTotManager()[quarter - 1].getDayInfo().get("p1").get(month+3 - 3*quarter);
			}
			
			
			int j = cal.get(Calendar.DAY_OF_WEEK)-1;
			//달력에 다시 세팅 
			for(int i = 0; i < cal.getActualMaximum(Calendar.DATE); i++, j++)
			{
				amountLabels[j].setText("");
				
				dateLabels[j].setText(Integer.toString(i + 1));
				if(monthCashBookVO != null)
				{
					if(monthCashBookVO.get(i+1) != null)
					{
						if(i==15){
							amountLabels[j].setIcon(null);
							revalidate();
						}
						if(code == 1){
							System.out.println(monthCashBookVO.get(i + 1).getRegisterDate()[2]+" "+monthCashBookVO.get(i + 1).getCompliment());
							/* 칭찬하기 = 1 , 응원하기 = 2, 아무것도 아니면 = 0 */
							if(monthCashBookVO.get(i + 1).getCompliment().equals("1")){
								System.out.println("MainUI-칭찬하기_608line");
								ImageIcon iconLogo = new ImageIcon("thumbs.png");
								Image temp = iconLogo.getImage();
								Image iconLogo2 = temp.getScaledInstance(30, 30, java.awt.Image.SCALE_SMOOTH);
								ImageIcon tem = new ImageIcon(iconLogo2);
								amountLabels[j].setIcon(tem);
							}
							else if(monthCashBookVO.get(i + 1).getCompliment().equals("2"))
							{	
								System.out.println("MainUI-응원하기_617line");
								ImageIcon iconLogo = new ImageIcon("x.png");
								Image temp = iconLogo.getImage();
								Image iconLogo2 = temp.getScaledInstance(30, 30, java.awt.Image.SCALE_SMOOTH);
								ImageIcon tem = new ImageIcon(iconLogo2);
								amountLabels[j].setIcon(tem);
							}
						}
		/////////////////////////////////////////////////////////////////
						amountLabels[j].setText(monthCashBookVO.get(i + 1).getDayLimit());
					}
					else{
						amountLabels[j].setIcon(null);
						revalidate();
					}
				}
				moneyPanels[j].setVisible(true);
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
			/**
			 * 4분기 manager
			 */
			private QuarterAdminManager totManager;
			public void mouseClicked(MouseEvent e)
			{
				totManager = new QuarterAdminManager();
				quarter = (int) Math.ceil( month / 3.0 );
				System.out.println("분기 정보 : "+quarter);
				QuarterManager addManager = QuarterAdminManager.getTotManager()[quarter-1];
				
				/*
				 * 현재 날짜( 분기 )의 QuarterManager가 없을 시 생성한다.
				 */
				int []tMonth = {1,1,1};
				if(addManager==null){
					if(1 == quarter)
						addManager = new QuarterManager(QuarterNo.FIRST_QUARTER);
					else if(2 == quarter)
						addManager = new QuarterManager(QuarterNo.SECOND_QUARTER);
					else if(3 == quarter)
						addManager = new QuarterManager(QuarterNo.THIRD_QUARTER);
					else if(4 == quarter)
						addManager = new QuarterManager(QuarterNo.FOURTH_QUARTER);
					/*
					 * 새 분기 Manager 만들어 참조 
					 */
					QuarterAdminManager.getTotManager()[quarter-1] = addManager;
					if(QuarterAdminManager.getTotManager()[quarter-1]!=null)
						System.out.println("MainUI-listener : 분기 정보가 잘 들어갔습니다.");

					if(QuarterNo.FIRST_QUARTER == addManager.getKey()){
						tMonth[0] = 1; tMonth[1] = 2; tMonth[2] = 3;
					}
					else if(QuarterNo.SECOND_QUARTER == addManager.getKey()){
						tMonth[0] = 4; tMonth[1] = 5; tMonth[2] = 6;
					}
					else if(QuarterNo.THIRD_QUARTER == addManager.getKey()){
						tMonth[0] = 7; tMonth[1] = 8; tMonth[2] = 9;
					}
					else if(QuarterNo.FOURTH_QUARTER == addManager.getKey()){
						tMonth[0] = 10; tMonth[1] = 11; tMonth[2] = 12;
					}
					ArrayList<MonthCashBookDetailVO> temp = QuarterManager.getDataLoadManager().loadThreeMonthCB("F0001", "p1", tMonth);
					System.out.println(addManager.getKey()+"분기 객체 생성.."+addManager.putDetailInfoList("p1", temp));
				}
				/*
				 * 현재 일 정보 가져오기
				 */
				int clickMonth = cal.get(Calendar.MONTH)+3 - 3*quarter;
				int clickDay = Integer.parseInt(((JLabel)e.getSource()).getText());
				System.out.println("clickMonth = "+clickMonth+" clickDay = "+clickDay);
				LinkedList<CashBookDetailVO> clickDayList = 
						addManager.getDetailInfoList().get("p1").get(clickMonth).get(clickDay);
				if(clickDayList!=null){
					JLabel[][] cashBookList = new JLabel[clickDayList.size()][]; 
					CashBookDetailVO insertInfo = null;
					for(int i = 0 ; i < clickDayList.size(); ++i){
						insertInfo = clickDayList.get(i);
						///////////////////
						System.out.println("MainUI-Listener:"+insertInfo);
						cashBookList[i] = new JLabel[4];
						cashBookList[i][0] = new JLabel(insertInfo.getRegisterCategory().value());
						 cashBookList[i][0].setFont(new Font("휴먼중간팸체",Font.BOLD,20));
						 if(insertInfo.getRegisterCategory()==RegisterCategory.IMPORT)	
							 cashBookList[i][0].setForeground(Color.blue);
						 else if(insertInfo.getRegisterCategory()==RegisterCategory.EXPORT)
							 cashBookList[i][0].setForeground(Color.orange);
						 else if(insertInfo.getRegisterCategory()==RegisterCategory.SAVE)
							 cashBookList[i][0].setForeground(Color.green);
						 else if(insertInfo.getRegisterCategory()==RegisterCategory.DONATION)
							 cashBookList[i][0].setForeground(Color.yellow);
						 
						cashBookList[i][0].setFont(new Font("휴먼중간팸체",Font.BOLD,20));
	
						cashBookList[i][1] = new JLabel(insertInfo.getDetailCategory());
						cashBookList[i][2] = new JLabel(Integer.toString(i));
						cashBookList[i][2].setForeground(Color.white);
						cashBookList[i][3] = new JLabel(insertInfo.getAmount());
					}
				}
				GregorianCalendar calendar = new GregorianCalendar();
				calendar.set(year, month, Integer.parseInt(((JLabel)e.getSource()).getText()));
				GregorianCalendar calendar2 = new GregorianCalendar();
				calendar2.set(year, month,1);
				int dayLimitNum = calendar2.get(Calendar.DAY_OF_WEEK)+Integer.parseInt(((JLabel)e.getSource()).getText())-2;
				System.out.println(calendar2.get(Calendar.DAY_OF_WEEK)+" "+Integer.parseInt(((JLabel)e.getSource()).getText()));
				System.out.println("MainUI- listener 선택한 현재날짜 index번호 : "+ dayLimitNum);
				/*
				 * 선택 날짜와 선택 날짜의 일일한도 출력
				 */
				dayUI.getTodayInfo(calendar,amountLabels[dayLimitNum],MainUI.this);
				/*
				 * 일 정보에 따라 목록 업데이트 
				 */
				dayUI.panelUpdate(clickDayList);
					//amountLabels[dayLimitNum].setText(dayUI.getDayLimit().getText());
				clickDayList = null;
				dayUI.setVisible(true);
			}
		}
		//월 별 결산 
		private class MonthAccountBtnListener implements ActionListener
		{
			public void actionPerformed(ActionEvent e) 
			{
				ArrayList<MonthCashBookDetailVO> temp = QuarterAdminManager.getTotManager()[quarter-1].getDetailInfoList().get("p1");
				monthUI.setData(temp.get(month+3 - 3*quarter), month);
				monthUI.setTable();
				monthUI.setBar();
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
		public JLabel[] getAmountLabels() {
			return amountLabels;
		}
		public void setAmountLabels(JLabel[] amountLabels) {
			this.amountLabels = amountLabels;
		}
		/**
		 * 
		 * @return 현재 잔액 라벨
		 */
		public static JLabel getRealBalanceLabel() {
			return realBalanceLabel;
		}
		/**
		 * 
		 * @param realBalanceLabel 새로운 현재 잔액 라벨
		 */
		public static void setRealBalanceLabel(JLabel realBalanceLabel) {
			MainUI.realBalanceLabel = realBalanceLabel;
		}
		/**
		 * 
		 * @return 현재 저축 목표정보  progressBar
		 */
		public static JProgressBar getGoalBar() {
			return goalBar;
		}
		/**
		 * 
		 * @param goalBar 새로운 저축 목표 정보 progressBar
		 */
		public static void setGoalBar(JProgressBar goalBar) {
			MainUI.goalBar = goalBar;
		}
		/**
		 * 
		 * @author 동익
		 *	메인 화면 끄기 버튼 Event Action Listener
		 */
		private class UserWindowAdapter extends WindowAdapter{
			/**
			 * model과 데이터를 주고 받기 위한 controller 
			 */
			private final QuarterAdminManager totManager;
			/**
			 * default생성자
			 */
			public UserWindowAdapter() {
				this.totManager = new QuarterAdminManager();
			}
			@Override
	        public void windowClosing(WindowEvent e) {//윈도우 종료(GUI에 항상 들어옴)
	        //창 닫는거 클릭하는지 모니터링
				totManager.getTotManager()[quarter-1].getDataSaveManager().setCashBookUniqueList(totManager.getTotManager()[quarter-1].getDataLoadManager().getCashBookUniqueList());
				System.out.println("closing : "+totManager.getTotManager()[quarter-1].getDayInfo().get("p1").get(month+3-3*quarter).get(15));
				System.out.println("저장 분기 : "+quarter+" 저장 월 : "+month+1+" 저장 순서 : "+(month+3-3*quarter));
				if(JOptionPane.showConfirmDialog(null, "변경내용을 저장할까요?")==0){
					totManager.getTotManager()[quarter-1].getDataSaveManager().saveOneMonthCBUnique("F0001", "p1", month+1,
							totManager.getTotManager()[quarter-1].getDayInfo().get("p1"));
				}					
	         }
		}
}
