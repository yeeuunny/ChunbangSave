package com.lectopia.chunbangsave.view;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class MoneySendUI extends JDialog{
	private JTextField moneyText;
	private JTextArea memoArea;//����ڰ� �Է��ϴ� �κ��� �ʵ����� ��ȥ 
	
	private int code;
	public MoneySendUI(JDialog frame,int code){
		super(frame,true);
		setTitle("�ڳ�� ��ȭ�ϱ�");
		Container contentPane=getContentPane();
		this.code=code;
		
		System.out.println("�ڳ����ڵ� ���޹��� : "+code);
		
		JLabel title=new JLabel("�뵷 ������");
		
		title.setFont(new Font("����",Font.PLAIN,30));
		JPanel top=new JPanel(new FlowLayout(FlowLayout.CENTER,0,30));
		top.add(title);
		
		JLabel money=new JLabel("�ݾ�");
		moneyText=new JTextField(20);
		JPanel moneyPanel=new JPanel(new FlowLayout());
		moneyPanel.add(money);
		moneyPanel.add(moneyText);
		JLabel memo=new JLabel("�޸�");
		JPanel memoSet=new JPanel(new GridLayout(8,1));
		memoSet.add(memo);
		memoArea=new JTextArea(8,20);
		JPanel memoPanel=new JPanel(new FlowLayout());
		memoPanel.add(memoSet);
		memoPanel.add(memoArea);
		JPanel mid=new JPanel();
		mid.setLayout(new BoxLayout(mid,BoxLayout.Y_AXIS));
		mid.add(moneyPanel);
		mid.add(memoPanel);
		
		JButton send=new JButton("������");
		JButton cancel=new JButton("���");
		JPanel but=new JPanel(new FlowLayout());
		but.add(send);
		but.add(cancel);
		
		send.addActionListener(new SendMoneyListener());
		cancel.addActionListener(new CloseBtnListener());
		
		
		contentPane.add(top,BorderLayout.NORTH);
		contentPane.add(mid);
		contentPane.add(but,BorderLayout.SOUTH);
		
		setSize(500,400);
		setLocation(500,400);
	}
	private class SendMoneyListener implements ActionListener{
		public void actionPerformed(ActionEvent arg0) {
			System.out.println("�ڳ��� �ڵ� : "+code);
			System.out.println("������ �ݾ� : "+moneyText.getText());
			System.out.println("���� ���� : "+memoArea.getText());
		}
		
	}
	private class CloseBtnListener implements ActionListener{
		public void actionPerformed(ActionEvent arg0){
			moneyText.setText("");
			memoArea.setText("");
			setVisible(false);
		}
	}
}
