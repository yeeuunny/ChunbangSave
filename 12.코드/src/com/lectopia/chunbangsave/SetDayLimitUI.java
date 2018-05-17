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
			setTitle("�����ѵ� ����");
			balanceName = new JLabel("���� �ܾ� : ");
			balanceName.setFont(new Font("�������",Font.BOLD,20));
		}
		else{
			setTitle("������ �̸�ŭ�� ���Կ�!");
			balanceName = new JLabel("���� �뵷 : ");
			balanceName.setFont(new Font("�������",Font.BOLD,20));
		}
		/*
		 * ���� �ܾ� �ý��ۿ��� ���;��ϴºκ�
		 */
		currentBalance = new JLabel("50000");
		currentBalance.setFont(new Font("�������",Font.BOLD,20));
		
		dayLimitTf = new JTextField(10);
		
		JLabel askLabel = new JLabel(calendar.get(Calendar.MONTH)+1+"��"+calendar.get(Calendar.DATE)+ "�Ͽ� ����� �ݾ� : ");
		askLabel.setFont(new Font("�������",Font.BOLD,20));
		JPanel currentBalancePanel = new JPanel();
	    currentBalancePanel.setLayout(new FlowLayout());
	    currentBalancePanel.add(balanceName,BorderLayout.CENTER);
	    currentBalancePanel.add(currentBalance,BorderLayout.CENTER);
	    JPanel temp2 = new JPanel();
	    JPanel balancePanel = new JPanel();
	    balancePanel.setLayout(new BoxLayout(balancePanel, BoxLayout.Y_AXIS));
	    balancePanel.add(temp2);
	    balancePanel.add(currentBalancePanel);
	    
	    //�� �ݾ� �Է� â
	    JPanel totAskPanel = new JPanel();
	    JLabel wonLabel = new JLabel("��");
	    wonLabel.setFont(new Font("�������",Font.BOLD,20));
	    totAskPanel.setLayout(new FlowLayout());
	    totAskPanel.add(askLabel,BorderLayout.CENTER);
	    totAskPanel.add(dayLimitTf,BorderLayout.CENTER);
	    totAskPanel.add(wonLabel,BorderLayout.CENTER);
	
		JPanel emptyPanel = new JPanel();
		emptyPanel.setLayout(new FlowLayout());
		JLabel temp = new JLabel("          ");
		emptyPanel.add(temp);
	
	    confirmBtn = new JButton("�Ϸ�");
	    confirmBtn.addActionListener(new RegisterLimitBtnListener());
	    cancelBtn = new JButton("���");
	    
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
		super.setLocation(800, 600); // ��ġ
		super.setPreferredSize(new Dimension(500, 400)); // ũ��cx000000000000000000
		super.pack();
		//super.setVisible(true);
	}
	public void setData(GregorianCalendar calendar ,int code){
		this.calendar=calendar;
		this.code=code;
	}
	/**
	 * 
	 * @author ����
	 *  �����ѵ� ����ȭ�鿡�� �Ϸ� ��ư Ŭ��
	 */
	private class RegisterLimitBtnListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			System.out.print("\n�ܾ� : "+dayLimitTf.getText());
		}
	}
}
