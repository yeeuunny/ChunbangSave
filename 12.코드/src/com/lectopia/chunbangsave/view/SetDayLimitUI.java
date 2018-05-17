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
		currentBalance = new JLabel(QuarterManager.getCurrentAmount());
		currentBalance.setFont(new Font("�������",Font.BOLD,20));
		
		dayLimitTf = new JTextField(10);
		
		askLabel = new JLabel("");
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
	    cancelBtn.addActionListener(new CancelBtnListener(this,dayLimitTf));
		super.setBackground(Color.WHITE);
		super.setResizable(false);
		super.setLocation(800, 600); // ��ġ
		super.setPreferredSize(new Dimension(500, 400)); // ũ��cx000000000000000000
		super.pack();
		//super.setVisible(true);
	}
	public void setData(GregorianCalendar calendar ,int code,JLabel dayLimit,JButton addAccountBtn,JButton dayLimitBtn){
		this.calendar=calendar;
		askLabel.setText(calendar.get(Calendar.MONTH)+1+"��"+calendar.get(Calendar.DATE)+ "�Ͽ� ����� �ݾ�!");
		this.code=code;
		this.dayLimit = dayLimit;
		this.addAccountBtn = addAccountBtn;
		this.dayLimitBtn = dayLimitBtn;
		System.out.println(QuarterManager.getCurrentAmount());
		this.currentBalance.setText(QuarterManager.getCurrentAmount());
	}
	/**
	 * 
	 * @author ����
	 *  �����ѵ� ����ȭ�鿡�� �Ϸ� ��ư Ŭ��
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
	         
	         System.out.println(QuarterAdminManager.getTotManager()[month/3].getDayInfo().get("p1").get(month%3).get(calendar.get(Calendar.DATE)));//Ȯ��
	         /*
	          * ���� �ܾ� ���� �� �����ѵ� ���� �� ���� �� ����� �߰� ��ư Ȱ��ȭ �� ����� ���� �ѵ� ���� ��ư ��Ȱ��ȭ
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
