package com.lectopia.chunbangsave.view;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ChildManage extends JDialog{
	private MoneyInputUI moneyInput;
	private SaveGoalUI saving;
	private MemoUI memo;
	
	private ChildMainUI childUI;
	private GregorianCalendar calenda;
	private int code;
	public ChildManage(JFrame frame,GregorianCalendar calenda,int code){
		super(frame,true);
		this.calenda=calenda;
		this.code=code;
		System.out.println("year month day 등록코드"+calenda.get(Calendar.YEAR)+calenda.get(Calendar.MONTH)+calenda.get(Calendar.DATE));
		
		childUI = new ChildMainUI(this);
		childUI.setVisible(false);
		
		this.calenda=calenda;
		moneyInput=new MoneyInputUI(this,calenda,1);
		moneyInput.setVisible(false);
		saving=new SaveGoalUI(this,1,1);//자녀 , 부모 등록코드를 받는 전달인자 두개 추가.
		saving.setVisible(false);
		memo = new MemoUI(this,this.code);
		memo.setVisible(false);
		
		Container contentPane=getContentPane();
		//가장 상단 부분의 패널 배치
		JButton home=new JButton("홈");
		JPanel homes=new JPanel(new FlowLayout(FlowLayout.RIGHT));
		homes.add(home);
		ImageIcon child=new ImageIcon("child.png");
		JLabel childIcon=new JLabel(child);
		JLabel childLabel=new JLabel("자녀관리");
		JPanel top=new JPanel(new FlowLayout());
		top.add(childIcon);
		top.add(childLabel);
		JPanel topen=new JPanel();
		
		topen.setLayout(new BoxLayout(topen,BoxLayout.Y_AXIS));
		topen.add(homes);
		//homes.set;
		topen.add(top);
		
	//
		//home.addActionListener(new CancelBtnListener());
		home.addActionListener(new CancelBtnListener(this));
		//중간 부분의 버튼 배치
		JButton childCalendar=new JButton("자녀 달력 조회");
		JPanel calendar=new JPanel(new FlowLayout(FlowLayout.CENTER,0,1));
		calendar.add(childCalendar);
		childCalendar.setSize(40,50);
		JButton childMoneyIn=new JButton("     용돈 주기     ");
		JPanel money=new JPanel(new FlowLayout(FlowLayout.CENTER));
		money.add(childMoneyIn);
		JButton childToTalk=new JButton(" 자녀와의 대화 ");
		JPanel talk=new JPanel(new FlowLayout(FlowLayout.CENTER));
		childToTalk.addActionListener(new CommunicationBtnListener());
		talk.add(childToTalk);
		JButton togetherSave=new JButton(" 함께 저축해요 ");
		JPanel save=new JPanel(new FlowLayout(FlowLayout.CENTER));
		save.add(togetherSave);
		JPanel mid=new JPanel();
		mid.setLayout(new BoxLayout(mid,BoxLayout.Y_AXIS));
		mid.add(calendar);
		mid.add(money);
		mid.add(talk);
		mid.add(save);
		
		childCalendar.addActionListener(new CheckChildMainBtnListener());
		togetherSave.addActionListener(new SavingBtnListener());
		childMoneyIn.addActionListener(new GiveMoneyBtnListener());
		contentPane.add(topen,BorderLayout.NORTH);
		contentPane.add(mid);
		
		setSize(500,400);
		setLocation(350,250);
//		setVisible(true);
	}
	private class CheckChildMainBtnListener implements ActionListener{

		public void actionPerformed(ActionEvent arg0) {
			System.out.println("asdfasdf");
			childUI.setVisible(true);
		}
	}
	private class GiveMoneyBtnListener implements ActionListener{
		public void actionPerformed(ActionEvent arg0){
			//자녀 용돈주기 버튼이 눌렸을 때 자녀코드에 저장되어있는 용돈주기하는 string들을 set메소드를 만들던가해서 넘겨주어 용돈주기창에서 setText해야한다!!
			System.out.println("날짜 정보,자녀코드 :"+calenda.get(Calendar.YEAR)+calenda.get(Calendar.MONTH)+calenda.get(Calendar.DATE));
			moneyInput.setVisible(true);
		}
	}
	private class CommunicationBtnListener implements ActionListener{
		public void actionPerformed(ActionEvent arg0){
			System.out.println("날짜 정보,자녀코드 :"+calenda.get(Calendar.YEAR)+calenda.get(Calendar.MONTH)+calenda.get(Calendar.DATE));
			memo.setVisible(true);
		}
	}
	private class SavingBtnListener implements ActionListener{
		public void actionPerformed(ActionEvent arg0){
			System.out.println("자녀 등록코드");
			saving.setVisible(true);
		}
	}
	/*private class CancelBtnListener implements ActionListener{
		public void actionPerformed(ActionEvent arg0){
			setVisible(false);
		}
	}*/
}
