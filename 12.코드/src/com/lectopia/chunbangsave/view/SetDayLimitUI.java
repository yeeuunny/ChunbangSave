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

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.lectopia.chunbangsave.vo.CashBookDayVO;
import com.lectopia.chunbangsave.vo.QuarterAdminManager;
import com.lectopia.chunbangsave.vo.QuarterManager;

public class SetDayLimitUI extends JDialog{
	private JLabel balanceName;
	private JLabel currentBalance;
	private JLabel dayLimit;
	private JLabel askLabel;
	private JTextField dayLimitTf;
	private int code;
	private GregorianCalendar calendar;
	private JButton confirmBtn;
	private JButton cancelBtn;
	private JButton addAccountBtn;
	private JButton dayLimitBtn;
	public SetDayLimitUI(JDialog dialog,GregorianCalendar calendar, int code){
		super(dialog,true);
		this.calendar = calendar;
		this.code = code;
		if(code==2){
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
		currentBalance = new JLabel(QuarterManager.getCurrentAmount());
		currentBalance.setFont(new Font("맑은고딕",Font.BOLD,20));
		
		dayLimitTf = new JTextField(10);
		
		askLabel = new JLabel("");
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
	    cancelBtn.addActionListener(new CancelBtnListener(this,dayLimitTf));
		super.setBackground(Color.WHITE);
		super.setResizable(false);
		super.setLocation(800, 600); // 위치
		super.setPreferredSize(new Dimension(500, 400)); // 크기cx000000000000000000
		super.pack();
		//super.setVisible(true);
	}
	public void setData(GregorianCalendar calendar ,int code,JLabel dayLimit,JButton addAccountBtn,JButton dayLimitBtn){
		this.calendar=calendar;
		askLabel.setText(calendar.get(Calendar.MONTH)+1+"월"+calendar.get(Calendar.DATE)+ "일에 사용할 금액!");
		this.code=code;
		this.dayLimit = dayLimit;
		this.addAccountBtn = addAccountBtn;
		this.dayLimitBtn = dayLimitBtn;
		System.out.println(QuarterManager.getCurrentAmount());
		this.currentBalance.setText(QuarterManager.getCurrentAmount());
	}
	/**
	 * 
	 * @author 동익
	 *  일일한도 설정화면에서 완료 버튼 클릭
	 */
	private class RegisterLimitBtnListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {   
			if(Integer.parseInt(QuarterManager.getCurrentAmount())-Integer.parseInt(dayLimitTf.getText())<0){
 				dayLimitTf.setText("");
				return;
			}
					
			 int month=calendar.get(Calendar.MONTH);
			 CashBookDayVO vo=new CashBookDayVO(new int[]{calendar.get(Calendar.YEAR),month+1,calendar.get(Calendar.DATE)},dayLimitTf.getText(),"yet");
	         QuarterAdminManager.getTotManager()[month/3].getDayInfo().get("p1").get(month%3).put(calendar.get(Calendar.DATE),vo);
	         
	         System.out.println(QuarterAdminManager.getTotManager()[month/3].getDayInfo().get("p1").get(month%3).get(calendar.get(Calendar.DATE)));//확인
	         /*
	          * 현재 잔액 변경 및 일일한도 설정 및 설정 시 가계부 추가 버튼 활성화 및 가계부 일일 한도 설정 버튼 비활성화
	          */
	         QuarterManager.setCurrentAmount(
	        		 Integer.toString(Integer.parseInt(QuarterManager.getCurrentAmount())-Integer.parseInt(dayLimitTf.getText())));
	         dayLimit.setText(dayLimitTf.getText());
	         dayLimitTf.setText("");
	         addAccountBtn.setEnabled(true);
	         dayLimitBtn.setEnabled(false);
	         setVisible(false);
		}
	}
}
