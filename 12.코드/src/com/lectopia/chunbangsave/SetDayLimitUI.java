package com.lectopia.chunbangsave.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class SetDayLimitUI extends JDialog{
	private JLabel balanceName;
	private JLabel currentBalance;
	private JTextField dayLimitTf;
	private int code;
	private GregorianCalendar calendar;
	private JButton confirmBtn;
	private JButton cancelBtn;
	public SetDayLimitUI(JDialog dialog,GregorianCalendar calendar, int code){
		super(dialog,true);
		this.calendar = calendar;
		this.code = code;
		if(code==1){
			setTitle("일일한도 설정");
			balanceName = new JLabel("현재 잔액 : ");
			balanceName.setFont(new Font("맑은고딕",Font.BOLD,20));
		}
		else{
			setTitle("오늘은 이만큼만 쓸게요!");
			balanceName = new JLabel("남은 용돈 : ");
			balanceName.setFont(new Font("맑은고딕",Font.BOLD,20));
		}
		/*
		 * 현재 잔액 시스템에서 얻어와야하는부분
		 */
		currentBalance = new JLabel("50000");
		currentBalance.setFont(new Font("맑은고딕",Font.BOLD,20));
		
		dayLimitTf = new JTextField(10);
		
		JLabel askLabel = new JLabel(calendar.get(Calendar.MONTH)+1+"월"+calendar.get(Calendar.DATE)+ "일에 사용할 금액 : ");
		askLabel.setFont(new Font("맑은고딕",Font.BOLD,20));
		JPanel currentBalancePanel = new JPanel();
	    currentBalancePanel.setLayout(new FlowLayout());
	    currentBalancePanel.add(balanceName,BorderLayout.CENTER);
	    currentBalancePanel.add(currentBalance,BorderLayout.CENTER);
	    JPanel temp2 = new JPanel();
	    JPanel balancePanel = new JPanel();
	    balancePanel.setLayout(new BoxLayout(balancePanel, BoxLayout.Y_AXIS));
	    balancePanel.add(temp2);
	    balancePanel.add(currentBalancePanel);
	    
	    //쓸 금액 입력 창
	    JPanel totAskPanel = new JPanel();
	    JLabel wonLabel = new JLabel("원");
	    wonLabel.setFont(new Font("맑은고딕",Font.BOLD,20));
	    totAskPanel.setLayout(new FlowLayout());
	    totAskPanel.add(askLabel,BorderLayout.CENTER);
	    totAskPanel.add(dayLimitTf,BorderLayout.CENTER);
	    totAskPanel.add(wonLabel,BorderLayout.CENTER);
	
		JPanel emptyPanel = new JPanel();
		emptyPanel.setLayout(new FlowLayout());
		JLabel temp = new JLabel("          ");
		emptyPanel.add(temp);
	
	    confirmBtn = new JButton("완료");
	    confirmBtn.addActionListener(new RegisterLimitBtnListener());
	    cancelBtn = new JButton("취소");
	    
		JPanel btnPanel = new JPanel();
		//btnPanel.setLayout(new BoxLayout(btnPanel, BoxLayout.X_AXIS));
		btnPanel.setLayout(new FlowLayout());
		//btnPanel.setBackground(Color.white);
		btnPanel.add(confirmBtn,BorderLayout.CENTER);
		btnPanel.add(cancelBtn,BorderLayout.CENTER);
		JPanel totBtnPanel = new JPanel();
		//JPanel emptyPanel = new JPanel();
		//emptyPanel.setBackground(Color.white);
		totBtnPanel.setLayout(new GridLayout(2,1));
		totBtnPanel.add(emptyPanel);
		totBtnPanel.add(btnPanel);
		
	    JPanel totPanel = new JPanel();
	    totPanel.setLayout(new BoxLayout(totPanel, BoxLayout.Y_AXIS));
	   // totPanel.add(emptyPanel);
	    totPanel.add(balancePanel);
	    totPanel.add(totAskPanel);
	   // totPanel.add(emptyPanel);
	    totPanel.add(totBtnPanel);
	    add(totPanel);
	    
		super.setBackground(Color.WHITE);
		super.setResizable(false);
		super.setLocation(800, 600); // 위치
		super.setPreferredSize(new Dimension(500, 400)); // 크기cx000000000000000000
		super.pack();
		//super.setVisible(true);
	}
	public void setData(GregorianCalendar calendar ,int code){
		this.calendar=calendar;
		this.code=code;
	}
	/**
	 * 
	 * @author 동익
	 *  일일한도 설정화면에서 완료 버튼 클릭
	 */
	private class RegisterLimitBtnListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			System.out.print("\n잔액 : "+dayLimitTf.getText());
		}
	}
}
