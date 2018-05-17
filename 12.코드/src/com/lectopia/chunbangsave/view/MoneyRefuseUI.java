package com.lectopia.chunbangsave.view;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import com.lectopia.chunbangsave.vo.PocketMoneyVO;
import com.lectopia.chunbangsave.vo.QuarterAdminManager;

public class MoneyRefuseUI extends JDialog{
	private JTextArea refuse;
	private int code;
	
	private JButton acBtn;
	private JButton rfBtn;
	private GregorianCalendar cal;
	public MoneyRefuseUI(JDialog frame,int code,JButton acBtn,JButton rfBtn){//------------------
		super(frame,true);
		setTitle("자녀와 대화하기");
		Container contentPane=getContentPane();
		this.code=code;
		
		this.acBtn=acBtn;//---------------------
		this.rfBtn=rfBtn;
		
		cal=new GregorianCalendar();
		System.out.println("자녀등록번호 받음 : "+code);
		
		JLabel title=new JLabel("거절 사유");
		title.setFont(new Font("돋움",Font.PLAIN,30));
		JPanel top=new JPanel(new FlowLayout(FlowLayout.CENTER));
		top.add(title);
		
		refuse=new JTextArea(10,20);
		JPanel mid=new JPanel(new FlowLayout(FlowLayout.CENTER));
		mid.add(refuse);
		
		JButton send=new JButton("보내기");
		JButton cancel=new JButton("취소");
		JPanel but=new JPanel(new FlowLayout());
		but.add(send);
		but.add(cancel);
		
		send.addActionListener(new SendRefuseMsgListener());
		cancel.addActionListener(new CancelBtnListener());
		contentPane.add(top,BorderLayout.NORTH);
		contentPane.add(mid);
		contentPane.add(but,BorderLayout.SOUTH);
		
		setSize(500,400);
		setLocation(500,400);
	}
	private class SendRefuseMsgListener implements ActionListener{//-------------------------------------------------------
		public void actionPerformed(ActionEvent e) {
			
			PocketMoneyVO vo=QuarterAdminManager.getTotManager()[cal.get(Calendar.MONTH)/3].getDailyPocketMoney("P1", cal.get(Calendar.DATE));
			System.out.println(QuarterAdminManager.getTotManager()[cal.get(Calendar.MONTH)/3]);
			System.out.println(vo);
			vo.setIsReceived("2");
			vo.setAmount("0");
			acBtn.setEnabled(false);
			rfBtn.setEnabled(false);
			System.out.println("자녀등록번호 : "+code);
			System.out.println("거절 사유 : "+refuse.getText());
			setVisible(false);
		}
	}
	private class CancelBtnListener implements ActionListener{
		public void actionPerformed(ActionEvent e){
			setVisible(false);
		}
	}
	
}
