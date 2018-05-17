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
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.LinkedList;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

import com.lectopia.chunbangsave.vo.CashBookDayVO;
import com.lectopia.chunbangsave.vo.CashBookDetailVO;
import com.lectopia.chunbangsave.vo.MonthCashBookVO;
import com.lectopia.chunbangsave.vo.QuarterAdminManager;

public class ShowChildDayAccountUI extends JDialog{
	private GregorianCalendar today;
	private int code;
	private JLabel dayLabel;
	private JLabel allowanceTitle;
	private JLabel dayLimit;
	private JLabel useTotal;
	private JButton complimentBtn;
	private JButton supportBtn;
	private JLabel remainCost;
	private JPanel panel;
	private ChildMainUI mainUI;
	private LinkedList<CashBookDetailVO> bookList;
	private ShowChildDetailAccountUI showChildDayAccountUI;
	public void panelUpdate(LinkedList<CashBookDetailVO> bookList){
		/*
		 * 일일한도 미설정 시 
		 */
		if(dayLimit.getText().equals(""))return;
		/*
		 * 해당 날의 가계부 기록 없을 시
		 */
		if(bookList==null){
			System.out.println("DetailAccountUI-panelUpdate : 일일한도는 존재하는데 일일가계부 내역이없네요?");
			useTotal.setText("");
			remainCost.setText("");
			panel.removeAll();
			getContentPane().validate();	
			return;
		}
		panel.removeAll();
		//panel.validate();
		/*
		 * 상세보기 리스너
		 */
		int total = 0;
		this.bookList = bookList;
		DetailViewMouseBtnListener tempListener = new DetailViewMouseBtnListener();
		for(int i=0;i<bookList.size();++i){
			JPanel tempPanel=new JPanel(new GridLayout(1,4));
			tempPanel.setPreferredSize(new Dimension(10,30));
			tempPanel.setBackground(Color.white);
			JLabel registerLabel = new JLabel(bookList.get(i).getRegisterCategory().value());
			registerLabel.setFont(new Font("맑은고딕",Font.BOLD,22));
			if(bookList.get(i).getRegisterCategory()==RegisterCategory.IMPORT)	
				registerLabel.setForeground(Color.blue);
			 else if(bookList.get(i).getRegisterCategory()==RegisterCategory.EXPORT)
				 registerLabel.setForeground(Color.red);
			 else if(bookList.get(i).getRegisterCategory()==RegisterCategory.SAVE)
				 registerLabel.setForeground(Color.green);
			 else if(bookList.get(i).getRegisterCategory()==RegisterCategory.DONATION)
				 registerLabel.setForeground(Color.yellow);
			tempPanel.add(registerLabel);
			JLabel detailCategory = new JLabel(bookList.get(i).getDetailCategory());
			detailCategory.setFont(new Font("맑은고딕",Font.BOLD,13));
			tempPanel.add(detailCategory);
			JLabel indexLabel = new JLabel(Integer.toString(i));
			indexLabel.setForeground(Color.white);
			indexLabel.addMouseListener(tempListener);
			tempPanel.add(indexLabel);
			System.out.println("DayAccountUI-금액 : "+bookList.get(i).getAmount());
			//지출합계 업데이트
			if(!bookList.get(i).getRegisterCategory().value().equals("+"))
				total += Integer.parseInt(bookList.get(i).getAmount());
			else
				total -= Integer.parseInt(bookList.get(i).getAmount());
			JLabel amountLabel = new JLabel(bookList.get(i).getAmount());
			amountLabel.setFont(new Font("맑은고딕",Font.BOLD,13));
			tempPanel.add(amountLabel);
			panel.add(tempPanel);
			panel.setBackground(Color.white);			
		}
		this.useTotal.setText(Integer.toString(total));
		this.remainCost.setText(Integer.toString(Integer.parseInt(dayLimit.getText())-total));
		getContentPane().validate();
	}
	public void getTodayInfo(GregorianCalendar today,JLabel dayLimit,ChildMainUI mainUI){
		this.today = today;	
		this.mainUI = mainUI;
		dayLabel.setText(this.today.get(Calendar.YEAR)+"년 "+(this.today.get(Calendar.MONTH)+1)+"월 "+this.today.get(Calendar.DATE)+"일");
		/*
		 * 선택한 일에서 받아온 일일한도 대입
		 */
		this.dayLimit.setText(dayLimit.getText());
		/*
		 * 일일한도 존재하지 않으면
		 */
		if(this.dayLimit.getText().equals(""))
			return;/*
		 * 선택한 일에서 받아온 일일한도 대입
		 */
		// 지출 총액
		int total=0;
		if(this.bookList!=null){
			for(int i = 0 ; i<this.bookList.size();++i)
				total += Integer.parseInt(bookList.get(i).getAmount());
			this.useTotal.setText(Integer.toString(total));
		}
		this.remainCost.setText(Integer.toString(Integer.parseInt(dayLimit.getText())-total));
	}
	public void getCalendar(GregorianCalendar today){
		this.today = today;
		//System.out.println(this.today+"  dasdsd");	
		dayLabel.setText(this.today.get(Calendar.YEAR)+"년 "
		+(this.today.get(Calendar.MONTH)+1)+"월 "
		+this.today.get(Calendar.DATE)+"일");

	}
	public ShowChildDayAccountUI(JDialog frame,GregorianCalendar today, int code){
		super(frame,true);
		setTitle("일일결산");
		this.today = today;
		this.code = code;
		Container contentpane = getContentPane();
		
		showChildDayAccountUI=new ShowChildDetailAccountUI(ShowChildDayAccountUI.this,code,today);
		showChildDayAccountUI.setVisible(false);
		System.out.println(this.today.get(Calendar.YEAR));
		System.out.println(this.today.get(Calendar.MONTH)+1);
		System.out.println(this.today.get(Calendar.DATE));
		dayLabel = new JLabel(this.today.get(Calendar.YEAR)+"년 "
								+(this.today.get(Calendar.MONTH)+1)+"월 "
									+this.today.get(Calendar.DATE)+"일",SwingConstants.CENTER);
		
		dayLabel.setFont(new Font("휴먼중간팸체",Font.BOLD,30));
	
		allowanceTitle = new JLabel("오늘의 용돈",SwingConstants.CENTER);
		allowanceTitle.setFont(new Font("굵은안상수체",Font.BOLD,40));
		complimentBtn = new JButton("칭찬해요");
		supportBtn = new JButton("응원해요");
	
		/*
		 * 일일한도 가져와야하는부분
		 */
		dayLimit = new JLabel("5000"+"원",SwingConstants.CENTER);
		useTotal = new JLabel("3500"+"원",SwingConstants.CENTER);
		dayLimit.setFont(new Font("휴먼중간팸체",Font.BOLD,20));
		dayLimit.setForeground(Color.BLUE);
		useTotal.setFont(new Font("휴먼중간팸체",Font.BOLD,20));
		useTotal.setForeground(Color.RED);
		
		/*
		 * 오늘은 이만큼 ! nested class
		 */
		complimentBtn.addActionListener(new ComplimentBtnListener());
		supportBtn.addActionListener(new SupportBtnListener());
		
		
		JPanel temp = new JPanel();
		JPanel temp2 = new JPanel();
		temp2.add(new JLabel(" "));	
		temp2.setBackground(Color.white);
		JPanel temp4 = new JPanel();
		temp4.add(new JLabel("========================",SwingConstants.CENTER));
		temp4.setBackground(Color.white);
	
		
		JPanel titlePanel1 = new JPanel();
		titlePanel1.setLayout(new GridLayout(3,1));
		titlePanel1.add(temp2);
		titlePanel1.add(dayLabel);
		titlePanel1.add(allowanceTitle);
		titlePanel1.setBackground(Color.white);
		
		JPanel titlePanel3 = new JPanel();
		titlePanel3.setLayout(new GridLayout(4,1));
		titlePanel3.add(dayLimit);
		titlePanel3.add(useTotal);
		titlePanel3.add(temp4);
		/*
		 * 일일한도 - 사용금액
		 */
		remainCost = new JLabel("1500"+"원",SwingConstants.CENTER);
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
		JLabel[][] list = new JLabel[listCnt][];
		for(int i = 0; i <listCnt;++i){
			//JLabel[][]list = new JLabel[listCnt][];
			
			if(category == 1){
				list[i] = new JLabel[4];
				list[i][0]=new JLabel();
				list[i][1]=new JLabel();
				list[i][2]=new JLabel();
				list[i][3]=new JLabel();
				//사칙연산
				//list[i][0] = new JLabel("┼");
				//list[i][0].setFont(new Font("휴먼중간팸체",Font.BOLD,20));
				//list[i][0].setForeground(Color.green);
				//카테고리
				//list[i][1] = new JLabel("용돈");
				//list[i][1].setFont(new Font("휴먼중간팸체",Font.BOLD,20));
				/*
				 * 지금 여백이 마우스 클릭이벤트가 발생한다! 여기에다가 index번호를 주거나 unique한 데이터를 주자
				 *  - index번호를 줘서 listener에서 그 index를 가지고 회원 가계부 내역을 가져와야할듯
				 */
				//list[i][2] = new JLabel(Integer.toString(i));
				list[i][2].addMouseListener(tempListener);
				//list[i][2].setForeground(Color.white);
				//지출금액
				//list[i][3] = new JLabel("10000");
				JPanel tempPanel = new JPanel();
				tempPanel.setLayout(new GridLayout(1, 4));
				tempPanel.setBackground(Color.white);
				
				tempPanel.add(list[i][0]);
				tempPanel.add(list[i][1]);
				tempPanel.add(list[i][2]);
				tempPanel.add(list[i][3]);
				listPanel[i][1].add(tempPanel);
			}
		}	
		/*
		 * 화면 완성
		 */
		panel = new JPanel();
		JScrollPane scrollpane = new JScrollPane();
		scrollpane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);//수평 스크롤 안쓰게함.
		scrollpane.setBorder(new LineBorder(Color.WHITE));
		//////////
		panel.setLayout(new GridLayout(0,1));
		panel.setBackground(Color.white);
		for(int i = 0 ; i < listCnt;++i)
				panel.add(listPanel[i][1]);
		scrollpane.setViewportView(panel);
		JPanel cashBookListPanel = new JPanel();
		cashBookListPanel.setLayout(new GridLayout(1,3));
		cashBookListPanel.add(listPanel[0][0]);
		cashBookListPanel.add(scrollpane);
		cashBookListPanel.add(listPanel[0][2]);
		
		setLayout(new GridLayout(4, 1));
		add(titlePanel1);
		add(titlePanel3);
		add(cashBookListPanel);
		
		JPanel btnPanel = new JPanel();
		//btnPanel.setLayout(new BoxLayout(btnPanel, BoxLayout.X_AXIS));
		btnPanel.setLayout(new FlowLayout());
		btnPanel.setBackground(Color.white);
		btnPanel.add(complimentBtn,BorderLayout.CENTER);
		btnPanel.add(supportBtn,BorderLayout.CENTER);
		JPanel totBtnPanel = new JPanel();
		JPanel emptyPanel = new JPanel();
		emptyPanel.setBackground(Color.white);
		totBtnPanel.setLayout(new GridLayout(2,1));
		totBtnPanel.add(emptyPanel);
		totBtnPanel.add(btnPanel);
		
		add(totBtnPanel);
		super.setBackground(Color.WHITE);
		super.setResizable(false);
		super.setLocation(1000, 1000); // 위치
		super.setPreferredSize(new Dimension(500, 700)); // 크기cx000000000000000000
		//super.setDefaultCloseOperation(JDialog.EXIT_ON_CLOSE);
		//frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		super.pack();
	//	super.setVisible(true);
	}
	/**
	 * 
	 * @author 동익
	 *  응원하기 버튼 listener
	 */
	private class SupportBtnListener implements ActionListener{
		private QuarterAdminManager totManager;
		@Override
		public void actionPerformed(ActionEvent e) {
			int quarter = (int)Math.ceil((today.get(Calendar.MONTH) + 1) / 3.0);
			ArrayList<MonthCashBookVO> tempVo = totManager.getTotManager()[quarter-1].getDayInfo().get("p1");
			tempVo.get(today.get(Calendar.MONTH)+3 - 3*quarter).put(today.get(Calendar.DATE), 
					new CashBookDayVO(new int[]{today.get(Calendar.YEAR),today.get(Calendar.MONTH)+1,today.get(Calendar.DATE)}, dayLimit.getText(), "2"));
			totManager = new QuarterAdminManager();
			totManager.getTotManager()[quarter-1].putDayInfo("p1", tempVo);
			JOptionPane.showMessageDialog(null, "응원하기를 클릭하셨습니다!");
			
		}
	}
	/**
	 * 
	 * @author 동익
	 *  칭찬하기 버튼 listener
	 */
	private class ComplimentBtnListener implements ActionListener{
		private QuarterAdminManager totManager;
		@Override
		public void actionPerformed(ActionEvent e) {
			int quarter = (int)Math.ceil((today.get(Calendar.MONTH) + 1) / 3.0);
			ArrayList<MonthCashBookVO> tempVo = totManager.getTotManager()[quarter-1].getDayInfo().get("p1");
			tempVo.get(today.get(Calendar.MONTH)+3 - 3*quarter).put(today.get(Calendar.DATE), 
					new CashBookDayVO(new int[]{today.get(Calendar.YEAR),today.get(Calendar.MONTH)+1,today.get(Calendar.DATE)}, dayLimit.getText(), "1"));
			totManager = new QuarterAdminManager();
			totManager.getTotManager()[quarter-1].putDayInfo("p1", tempVo);
			System.out.println("ComplimentBtnListener-저장한 데이터 정보  : 순서("+(today.get(Calendar.MONTH)+3 - 3*quarter)+") 일 ("+today.get(Calendar.DATE)+")"+tempVo.get(today.get(Calendar.MONTH)+3 - 3*quarter));
			System.out.println("쿼터 index"  +(quarter-1));
			JOptionPane.showMessageDialog(null, "칭찬하기를 클릭하셨습니다!");
			//totManager.getTotManager()[]
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
			//여기에 showChildDayAccountUI에게 레이블 번호에 해당하는 정보들을 받아 넘겨주는 set메소드를 만들어서 정보 넘겨 주어야함
			
			showChildDayAccountUI.setVisible(true);
		//	new DetailAccountUI(DayAccountUI.this,code, today, Integer.parseInt(tempLabel.getText()));
		}
	}
}
