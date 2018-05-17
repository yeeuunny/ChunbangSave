package com.lectopia.chunbangsave.view;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class MoneyRefuseUI extends JDialog{
	private JTextArea refuse;
	private int code;
	public MoneyRefuseUI(JDialog frame,int code){
		super(frame,true);
		setTitle("�ڳ�� ��ȭ�ϱ�");
		Container contentPane=getContentPane();
		this.code=code;
		
		System.out.println("�ڳ��Ϲ�ȣ ���� : "+code);
		
		JLabel title=new JLabel("���� ����");
		title.setFont(new Font("����",Font.PLAIN,30));
		JPanel top=new JPanel(new FlowLayout(FlowLayout.CENTER));
		top.add(title);
		
		refuse=new JTextArea(10,20);
		JPanel mid=new JPanel(new FlowLayout(FlowLayout.CENTER));
		mid.add(refuse);
		
		JButton send=new JButton("������");
		JButton cancel=new JButton("���");
		JPanel but=new JPanel(new FlowLayout());
		but.add(send);
		but.add(cancel);
		
		send.addActionListener(new SendRefuseMsgListener());
		cancel.addActionListener(new CloseBtnListener());
		contentPane.add(top,BorderLayout.NORTH);
		contentPane.add(mid);
		contentPane.add(but,BorderLayout.SOUTH);
		
		setSize(500,400);
		setLocation(500,400);
	}
	private class SendRefuseMsgListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			System.out.println("�ڳ��Ϲ�ȣ : "+code);
			System.out.println("���� ���� : "+refuse.getText());
		}
	}
	private class CloseBtnListener implements ActionListener{
		public void actionPerformed(ActionEvent e){
			refuse.setText("");
			setVisible(false);
		}
	}
}
