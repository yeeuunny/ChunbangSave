package com.lectopia.chunbangsave.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class MoneyInputUI extends JDialog{
	private MoneySendUI send;
	private MoneyRefuseUI refuse;
	private AllowanceListUI allowanceUI;
	
	private JTextField needText;
	private JTextField moneyNeedText;
	private JTextArea howNeedArea;

	private GregorianCalendar cal;
	private int code;
	public MoneyInputUI(JDialog frame,GregorianCalendar cal,int code){
		super(frame,true);
		setTitle("�ڳ� ��ȭ�ϱ�");
		this.code = code;
		Container contentPane=getContentPane();
		refuse=new MoneyRefuseUI(frame,code);
		refuse.setVisible(false);
		send=new MoneySendUI(frame,code);
		send.setVisible(false);
		
		allowanceUI = new AllowanceListUI(this, cal, this.code);
		allowanceUI.setVisible(false);
		
		System.out.println("�ڳ����ڵ� , year month day"+code+cal.get(Calendar.YEAR)+cal.get(Calendar.MONTH)+cal.get(Calendar.DATE));
		
		this.cal=cal;
		//��ܺκ�
		JLabel title=new JLabel("�뵷�ֱ�");
		Font titleFont=new Font("����",Font.PLAIN,25);
		title.setFont(titleFont);
		JPanel top=new JPanel(new FlowLayout(FlowLayout.CENTER,0,30));
		top.add(title);
		
		//�߰��κ�
		//new �� �κ� �۾� ���涧�� �ϵ��� �����ؾ���
		JLabel need=new JLabel("����! �ƺ�! ���⿡ �ʿ��ؿ�!");
		JLabel state=new JLabel("new");
		state.setForeground(Color.RED);
		//���� �ؾ��ϴºκ�
		needText=new JTextField(20);
		JPanel needPanel=new JPanel(new FlowLayout());
		needPanel.add(need);
		needPanel.add(state);
		needPanel.add(needText);
		//�����ƺ� ���� �ʿ��ؿ� ������ �� �̺�
		JLabel moneyNeed=new JLabel("             �󸶰� �ʿ��ؿ�!");
		moneyNeedText=new JTextField(20);
		JLabel moneyEmp=new JLabel("               ");
		JPanel moneyPanel=new JPanel(new FlowLayout());
		moneyPanel.add(moneyNeed);
		moneyPanel.add(moneyEmp);
		moneyPanel.add(moneyNeedText);
		JLabel howNeed=new JLabel("            �̷��� �ʿ��ؿ�!");
		howNeedArea=new JTextArea(2,20);
		JLabel howEmp=new JLabel("               ");
		JPanel howNeedPanel=new JPanel(new FlowLayout());
		howNeedPanel.add(howNeed);
		howNeedPanel.add(howEmp);
		howNeedPanel.add(howNeedArea);
		
		JButton responBtn=new JButton(" �����亯���� ");
		JButton acceptBtn=new JButton("����");
		JButton refuseBtn=new JButton("����");
		JPanel button=new JPanel(new FlowLayout());
		button.add(responBtn);
		button.add(acceptBtn);
		button.add(refuseBtn);
		
		JPanel mid=new JPanel();
		mid.setLayout(new BoxLayout(mid,BoxLayout.Y_AXIS));
		mid.add(needPanel);
		mid.add(moneyPanel);
		mid.add(howNeedPanel);
		mid.add(button);
		
		JButton out=new JButton("������");
		JPanel but=new JPanel(new FlowLayout(FlowLayout.CENTER));
		but.add(out);
		
		responBtn.addActionListener(new ShowResponseBtnListener());
		acceptBtn.addActionListener(new AcceptBtnListener());
		refuseBtn.addActionListener(new RefuseBtnListener());
		out.addActionListener(new CloseBtnListener());
		contentPane.add(top,BorderLayout.NORTH);
		contentPane.add(mid);
		contentPane.add(but,BorderLayout.SOUTH);
		setSize(500,400);
		setLocation(450,350);
		//setVisible(true);
	}
	private class AcceptBtnListener implements ActionListener{

		public void actionPerformed(ActionEvent arg0) {
		//	send.setDate(year, month, day);
			System.out.println("�ڳ��ڵ�: "+code);
			send.setVisible(true);
		}
	}
	private class RefuseBtnListener implements ActionListener{
		public void actionPerformed(ActionEvent arg0){
		//	send.setDate(year, month, day);
			System.out.println("�ڳ��ڵ� : "+code);
			refuse.setVisible(true);
		}
	}
	private class ShowResponseBtnListener implements ActionListener	{
		public void actionPerformed(ActionEvent arg0){
			allowanceUI.setVisible(true);
			
		}
	}
	private class CloseBtnListener implements ActionListener{
		public void actionPerformed(ActionEvent arg0){
			setVisible(false);
		}
	}
}
