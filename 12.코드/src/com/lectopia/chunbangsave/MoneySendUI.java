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
	private JTextArea memoArea;//사용자가 입력하는 부분을 필드멤버로 변혼 
	
	private int code;
	public MoneySendUI(JDialog frame,int code){
		super(frame,true);
		setTitle("자녀와 대화하기");
		Container contentPane=getContentPane();
		this.code=code;
		
		System.out.println("자녀등록코드 전달받음 : "+code);
		
		JLabel title=new JLabel("용돈 보내기");
		
		title.setFont(new Font("돋움",Font.PLAIN,30));
		JPanel top=new JPanel(new FlowLayout(FlowLayout.CENTER,0,30));
		top.add(title);
		
		JLabel money=new JLabel("금액");
		moneyText=new JTextField(20);
		JPanel moneyPanel=new JPanel(new FlowLayout());
		moneyPanel.add(money);
		moneyPanel.add(moneyText);
		JLabel memo=new JLabel("메모");
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
		
		JButton send=new JButton("보내기");
		JButton cancel=new JButton("취소");
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
			System.out.println("자녀등록 코드 : "+code);
			System.out.println("보내는 금액 : "+moneyText.getText());
			System.out.println("승인 이유 : "+memoArea.getText());
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
