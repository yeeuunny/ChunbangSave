package com.lectopia.chunbangsave.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;

public class DayAccountUI extends JDialog{
	private GregorianCalendar today;
	private int code;
	private JLabel dayLabel;
	private JLabel allowanceTitle;
	private JLabel dayLimit;
	private JLabel useTotal;
	private JButton setDayLimitBtn;
	private JButton addAccountBtn;
	private AddMoneyUI addMoneyUI;
	private SetDayLimitUI setDayLimitUI;
	private DetailAccountUI detailAccountUI;
	public void getCalendar(GregorianCalendar today){
		this.today = today;
		System.out.println(this.today.get(Calendar.DATE)+"  dasdsd");	
		dayLabel.setText(this.today.get(Calendar.YEAR)+"년 "
		+(this.today.get(Calendar.MONTH)+1)+"월 "
		+this.today.get(Calendar.DATE)+"일");
	}
	public DayAccountUI(JFrame frame,GregorianCalendar today, int code){
		super(frame,true);
		setTitle("일일결산");

		this.today = today;
		this.code = code;
		
		addMoneyUI=new AddMoneyUI(DayAccountUI.this,today,code);
		addMoneyUI.setVisible(false);
		
		setDayLimitUI=new SetDayLimitUI(DayAccountUI.this,today,code);
		setDayLimitUI.setVisible(false);
		
		detailAccountUI=new DetailAccountUI(DayAccountUI.this,code, today);
		
		Container contentpane = getContentPane();
		System.out.println(this.today.get(Calendar.YEAR));
		System.out.println(this.today.get(Calendar.MONTH)+1);
		System.out.println(this.today.get(Calendar.DATE));
		dayLabel = new JLabel(this.today.get(Calendar.YEAR)+"년 "
								+(this.today.get(Calendar.MONTH)+1)+"월 "
									+this.today.get(Calendar.DATE)+"일",SwingConstants.CENTER);
		
		dayLabel.setFont(new Font("휴먼중간팸체",Font.BOLD,30));
		/*
		 * 부모 자녀 구분
		 */
		if(code == 1){
			allowanceTitle = new JLabel("오늘의 용돈",SwingConstants.CENTER);
			allowanceTitle.setFont(new Font("굵은안상수체",Font.BOLD,40));
			setDayLimitBtn = new JButton("오늘은 이만큼 쓸게요!");
			addAccountBtn = new JButton("돈을 관리해요!");
		}
		else if(code == 2){
			allowanceTitle = new JLabel("오늘의 내역",SwingConstants.CENTER);
			allowanceTitle.setFont(new Font("굵은안상수체",Font.BOLD,40));
			setDayLimitBtn = new JButton("가계부내역 추가");
			addAccountBtn = new JButton("생활비 설정");
		}

		JPanel titlePanel1 = new JPanel();
		JPanel temp2 = new JPanel();
		temp2.add(new JLabel(" "));	
		temp2.setBackground(Color.white);
		titlePanel1.setLayout(new GridLayout(3,1));
		titlePanel1.add(temp2);
		titlePanel1.add(dayLabel);
		titlePanel1.add(allowanceTitle);
		titlePanel1.setBackground(Color.white);
		/*
		 * 오늘은 이만큼 ! nested class 버튼 리스너!
		 */
		setDayLimitBtn.addActionListener(new TodayLimitBtnListener());
		addAccountBtn.addActionListener(new MoneyEditBtnListener());
		/*
		 * 일일한도 가져와야하는부분
		 */
		//if(일일한도 != 0){
			dayLimit = new JLabel("5000"+"원",SwingConstants.CENTER);
			useTotal = new JLabel("3500"+"원",SwingConstants.CENTER);
			dayLimit.setFont(new Font("휴먼중간팸체",Font.BOLD,20));
			dayLimit.setForeground(Color.BLUE);
			useTotal.setFont(new Font("휴먼중간팸체",Font.BOLD,20));
			useTotal.setForeground(Color.RED);
			
			
			JPanel temp = new JPanel();
			JPanel temp4 = new JPanel();
			temp4.add(new JLabel("========================",SwingConstants.CENTER));
			temp4.setBackground(Color.white);
	
			JPanel titlePanel3 = new JPanel();
			titlePanel3.setLayout(new GridLayout(4,1));
			titlePanel3.add(dayLimit);
			titlePanel3.add(useTotal);
			titlePanel3.add(temp4);
			/*
			 * 일일한도 - 사용금액
			 */
			JLabel remainCost = new JLabel("1500"+"원",SwingConstants.CENTER);
			remainCost.setFont(new Font("휴먼중간팸체",Font.BOLD,20));
			titlePanel3.add(remainCost);
			titlePanel3.setBackground(Color.white);
			
			//add(titlePanel1,BorderLayout.NORTH);
			//add(titlePanel2,BorderLayout.CENTER);
			//add(titlePanel3,BorderLayout.SOUTH);
			
			/*
			 * 가계부 목록 
			 */
			int listCnt = 5; // 목록 수
			int category = 1; // 임시 카테고리 번호
			JPanel listPanel[][] = new JPanel[listCnt][];
			for(int i = 0; i < listCnt; ++i){
				listPanel[i] = new JPanel[3];
				for(int j = 0 ; j < 3;++j){
					listPanel[i][j] = new JPanel();
					listPanel[i][j].setBackground(Color.white);
				}
			}
			/*
			 * 상세보기를 위한 마우스 버튼 리스너
			 */
			DetailViewMouseBtnListener tempListener = new DetailViewMouseBtnListener();
			
			for(int i = 0; i <listCnt;++i){
				JLabel[][]list = new JLabel[listCnt][];
				if(category == 1){
					list[i] = new JLabel[4];
					//사칙연산
					list[i][0] = new JLabel("┼");
					list[i][0].setFont(new Font("휴먼중간팸체",Font.BOLD,20));
					list[i][0].setForeground(Color.green);
					//카테고리
					list[i][1] = new JLabel("용돈");
					list[i][1].setFont(new Font("휴먼중간팸체",Font.BOLD,20));
					/*
					 * 지금 여백이 마우스 클릭이벤트가 발생한다! 여기에다가 index번호를 주거나 unique한 데이터를 주자
					 *  - index번호를 줘서 listener에서 그 index를 가지고 회원 가계부 내역을 가져와야할듯
					 */
					list[i][2] = new JLabel(Integer.toString(i));
					list[i][2].addMouseListener(tempListener);
					list[i][2].setForeground(Color.white);
					//지출금액
					list[i][3] = new JLabel("10000");
					JPanel tempPanel = new JPanel();
					tempPanel.setLayout(new GridLayout(1, 4));
					tempPanel.setBackground(Color.white);
					
					tempPanel.add(list[i][0]);
					tempPanel.add(list[i][1]);
					tempPanel.add(list[i][2]);
					tempPanel.add(list[i][3]);
					listPanel[i][1].add(tempPanel);
					//임시
					category =2;
				}
				else if(category == 2){
					list[i] = new JLabel[4];
					//사칙연산
					list[i][0] = new JLabel("X");
					list[i][0].setFont(new Font("Arial Unicode MS",Font.BOLD,20));
					list[i][0].setForeground(Color.YELLOW);
					//카테고리
					list[i][1] = new JLabel("저축");
					list[i][1].setFont(new Font("휴먼중간팸체",Font.BOLD,20));
					/*
					 * 지금 여백이 마우스 클릭이벤트가 발생한다! 여기에다가 index번호를 주거나 unique한 데이터를 주자
					 *  - index번호를 줘서 listener에서 그 index를 가지고 회원 가계부 내역을 가져와야할듯
					 */
					list[i][2] = new JLabel(Integer.toString(i));
					list[i][2].addMouseListener(tempListener);
					list[i][2].setForeground(Color.white);
					//지출금액
					list[i][3] = new JLabel("10000");
					JPanel tempPanel = new JPanel();
					tempPanel.setLayout(new GridLayout(1, 4));
					tempPanel.setBackground(Color.white);
					tempPanel.add(list[i][0]);
					tempPanel.add(list[i][1]);
					tempPanel.add(list[i][2]);
					tempPanel.add(list[i][3]);
					listPanel[i][1].add(tempPanel);
					//임시
					category =1;
				}
			}	
			/*
			 * 화면 완성
			 */
			JPanel panel = new JPanel();
			panel.setLayout(new GridLayout(listCnt,3));
			for(int i = 0 ; i < listCnt;++i)
				for(int j = 0 ; j < 3; ++j)
					panel.add(listPanel[i][j]);
			setLayout(new GridLayout(4, 1));
			add(titlePanel1);
			add(titlePanel3);
			add(panel);
/*일일한도존재시*/		//}
			/*else{//일일한도 존재하지 않을때
			    // 가계부 추가 버튼 비활성화
				addAccountBtn.setEnabled(false);
				// 일일한도와 사용금액 초기화
				if(code==1)
					dayLimit = new JLabel("오늘은 얼마 쓸 것인가요?",SwingConstants.CENTER);
				else
					dayLimit = new JLabel("일일한도를 먼저 설정해주세요!",SwingConstants.CENTER);
				//빈 패널
				useTotal = new JLabel(" ",SwingConstants.CENTER);
				dayLimit.setFont(new Font("휴먼중간팸체",Font.BOLD,20));
				dayLimit.setForeground(Color.BLUE);
				useTotal.setFont(new Font("휴먼중간팸체",Font.BOLD,20));
				useTotal.setForeground(Color.RED);
				// 패널 생성
				 JPanel temp = new JPanel();
				JPanel temp4 = new JPanel();
				temp4.add(new JLabel("========================",SwingConstants.CENTER));
				temp4.setBackground(Color.white);
				JPanel titlePanel3 = new JPanel();
				titlePanel3.setLayout(new GridLayout(4,1));
				titlePanel3.add(dayLimit);
				titlePanel3.add(useTotal);
				titlePanel3.add(temp4);
				setLayout(new GridLayout(4, 1));
				// 상단 알림 창
				add(titlePanel1);
				add(titlePanel3);
				// 가계부 일지 칸
				int listCnt = 5; // 목록 수
				int category = 1; // 임시 카테고리 번호
				JPanel listPanel[][] = new JPanel[listCnt][];
				for(int i = 0; i < listCnt; ++i){
					listPanel[i] = new JPanel[3];
					for(int j = 0 ; j < 3;++j){
						listPanel[i][j] = new JPanel();
						listPanel[i][j].setBackground(Color.white);
					}
				}
				add(panel);
			}*/
		JPanel btnPanel = new JPanel();
		//btnPanel.setLayout(new BoxLayout(btnPanel, BoxLayout.X_AXIS));
		btnPanel.setLayout(new FlowLayout());
		btnPanel.setBackground(Color.white);
		btnPanel.add(setDayLimitBtn,BorderLayout.CENTER);
		btnPanel.add(addAccountBtn,BorderLayout.CENTER);
		JPanel totBtnPanel = new JPanel();
		JPanel emptyPanel = new JPanel();
		emptyPanel.setBackground(Color.white);
		totBtnPanel.setLayout(new GridLayout(2,1));
		totBtnPanel.add(emptyPanel);
		totBtnPanel.add(btnPanel);
		
		add(totBtnPanel);
		
		super.setBackground(Color.WHITE);
		super.setResizable(false);
		super.setLocation(700, 500); // 위치
		super.setPreferredSize(new Dimension(500, 700)); // 크기cx000000000000000000
		//super.setDefaultCloseOperation(JDialog.EXIT_ON_CLOSE);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		super.pack();
	//	super.setVisible(true);
	}
	/**
	 * 
	 * @author 동익
	 *  가계부 내역 추가 버튼 listener
	 */
	private class MoneyEditBtnListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			addMoneyUI.setData(today, code);
			addMoneyUI.setVisible(true);
		}
	}
	/**
	 * 
	 * @author 동익
	 *  일일한도 설정 버튼 listener
	 */
	private class TodayLimitBtnListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			setDayLimitUI.setData(today, code);
			setDayLimitUI.setVisible(true);
		}
	}
	/**
	 * 
	 * @author 동익
	 *  상세보기를 위한 listener
	 */
	private class DetailViewMouseBtnListener extends MouseAdapter{
		@Override
		public void mouseClicked(MouseEvent e) {
			JLabel tempLabel = (JLabel)e.getSource();
			detailAccountUI.setData(today,  Integer.parseInt(tempLabel.getText()), code);
			detailAccountUI.setVisible(true);
		}
	}
}
