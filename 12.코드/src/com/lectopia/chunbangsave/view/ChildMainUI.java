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
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.LinkedList;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
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
	private JLabel amountLabels[];
	
	private GregorianCalendar cal;
	private int year;
	private int month;
	private int day;
	
	private JPanel[] moneyPanels;
	//날짜 패널 
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

	private int quarter;
	public ChildMainUI(MonthCashBookVO monthCashBookVO,SavingGoalVO savingGoal)
	{

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
		
		cal.set(Calendar.DATE, 1);
/*
 * UI생성
 * */
		code = 2; //임시 등록코드
		int childCode = 2;

		//monthUI = new MonthHistoryUI(this,cal,code);
		//yearUI = new YearHistoryUI(this,cal,code);
		/*
		 * 등록코드 줘야
		 */
		dayUI = new ShowChildDayAccountUI(this, cal, code);
		//diaryUI = new DiaryUI(this, cal,code);
		//toChildUI = new ChildManage(this,cal, childCode);
		//toParentUI = new CommunicationWithParent(this, code,cal);
		
		//monthUI.setVisible(false);
		//yearUI.setVisible(false);
		dayUI.setVisible(false);
		//diaryUI.setVisible(false);
		//toChildUI.setVisible(false);
	//	toParentUI.setVisible(false);
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
  
		JPanel monthArrowChildPanel = new JPanel(new BorderLayout());

		monthArrowChildPanel.add(monthArrowPanel);
	
		
/**
 * 여기까지 추가 		
 */
		// 목표 패널, 목표 바
		JPanel goalBalancePanel = new JPanel();

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
		int goalPercent =(int)(Double.parseDouble(QuarterManager.getCurrentAmount())/savingGoal.getGoalAmount()*100);
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
			goalBalancePanel.setBorder(new TitledBorder("박동익 부모님!"));
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
				/* 칭찬하기 = 1 , 응원하기 = 2, 아무것도 아니면 = 0 */
				if(monthCashBookVO.get(i + 1).getCompliment().equals("1")){
					//System.out.println("ChildMainUI-칭찬하기_267line");
					ImageIcon iconLogo = new ImageIcon("thumbs.png");
					Image temp = iconLogo.getImage();
					Image iconLogo2 = temp.getScaledInstance(30, 30, java.awt.Image.SCALE_SMOOTH);
					ImageIcon tem = new ImageIcon(iconLogo2);
					amountLabels[j].setIcon(tem);
				}
				else if(monthCashBookVO.get(i + 1).getCompliment().equals("2"))
				{	
					//System.out.println("ChildMainUI-응원하기_276line");
					ImageIcon iconLogo = new ImageIcon("x.png");
					Image temp = iconLogo.getImage();
					Image iconLogo2 = temp.getScaledInstance(30, 30, java.awt.Image.SCALE_SMOOTH);
					ImageIcon tem = new ImageIcon(iconLogo2);
					amountLabels[j].setIcon(tem);
				}
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
		
		ImageIcon closeIcon = new ImageIcon("heart.png");
		image = closeIcon.getImage(); // transform it 
		newimg = image.getScaledInstance(50, 50,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
		closeIcon = new ImageIcon(newimg);  // transform it back
		//버튼들 생성 
/**
 * 	
 */
		diaryBtn = new JButton(" 일기장", diaryIcon);
		diaryBtn.setBackground(new Color(255, 135, 0));
		yearBtn = new JButton(" 연도별보기", yearIcon);
		yearBtn.setBackground(new Color(255, 135, 0));
		yearBtn.addActionListener(new YearAccountBtnListener());
		monthBtn = new JButton(" 월별보기", monthIcon);
		monthBtn.setBackground(new Color(255, 135, 0));
		monthBtn.addActionListener(new MonthAccountBtnListener());
		
		closeBtn = new JButton("닫기", closeIcon);
		closeBtn.setBackground(new Color(255, 135, 0));
		//closeBtn.addActionListener(new closeBtnListener());
		closeBtn.addActionListener(new CancelBtnListener(this));
		diaryBtn.addActionListener(new DiaryBtnListener());
		
		//버튼들 담을 버튼 패널 생성 
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

	// 오른쪽 화살표 다음달로 가기
	private class NextCalendarListener extends MouseAdapter {
		public void mouseClicked(MouseEvent e) {
			if (month != 11) {
				leftArrowLabel.setEnabled(true);
				rightArrowLabel.setEnabled(true);

				for (int j = 0; j < 37; j++)
					moneyPanels[j].setVisible(false);

				cal.set(year, ++month, 1);
				monthLabel.setText(Integer.toString(month + 1) + "월");
				int j = cal.get(Calendar.DAY_OF_WEEK) - 1;

				for (int i = 0; i < cal.getActualMaximum(Calendar.DATE); i++, j++) {
					moneyPanels[j].setVisible(true);
					dateLabels[j].setText(Integer.toString(i + 1));
				}
			}
			// 12월 이후로 가지못하게 레이블 비활성화
			else
				rightArrowLabel.setEnabled(false);
		}
	}

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
			dayUI.getTodayInfo(calendar,amountLabels[dayLimitNum],ChildMainUI.this);
			/*
			 * 일 정보에 따라 목록 업데이트 
			 */
			dayUI.panelUpdate(clickDayList);
				//amountLabels[dayLimitNum].setText(dayUI.getDayLimit().getText());
			clickDayList = null;
			dayUI.setVisible(true);
		}
	}

	// 월 별 결산
	private class MonthAccountBtnListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			monthUI.setVisible(true);
		}
	}

	// 연도 별 결산
	private class YearAccountBtnListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			yearUI.setVisible(true);
		}
	}// 엄마! 아빠!

	/*private class closeBtnListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			setVisible(false);
		}
	}// 일기장*/

	private class DiaryBtnListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			diaryUI.setVisible(true);
		}
	}
			
}
