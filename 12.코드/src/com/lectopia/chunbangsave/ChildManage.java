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
		System.out.println("year month day ����ڵ�"+calenda.get(Calendar.YEAR)+calenda.get(Calendar.MONTH)+calenda.get(Calendar.DATE));
		
		childUI = new ChildMainUI(this);
		childUI.setVisible(false);
		
		this.calenda=calenda;
		moneyInput=new MoneyInputUI(this,calenda,1);
		moneyInput.setVisible(false);
		saving=new SaveGoalUI(this,1,1);//�ڳ� , �θ� ����ڵ带 �޴� �������� �ΰ� �߰�.
		saving.setVisible(false);
		memo = new MemoUI(this,this.code);
		memo.setVisible(false);
		
		Container contentPane=getContentPane();
		//���� ��� �κ��� �г� ��ġ
		JButton home=new JButton("Ȩ");
		JPanel homes=new JPanel(new FlowLayout(FlowLayout.RIGHT));
		homes.add(home);
		ImageIcon child=new ImageIcon("child.png");
		JLabel childIcon=new JLabel(child);
		JLabel childLabel=new JLabel("�ڳ����");
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
		//�߰� �κ��� ��ư ��ġ
		JButton childCalendar=new JButton("�ڳ� �޷� ��ȸ");
		JPanel calendar=new JPanel(new FlowLayout(FlowLayout.CENTER,0,1));
		calendar.add(childCalendar);
		childCalendar.setSize(40,50);
		JButton childMoneyIn=new JButton("     �뵷 �ֱ�     ");
		JPanel money=new JPanel(new FlowLayout(FlowLayout.CENTER));
		money.add(childMoneyIn);
		JButton childToTalk=new JButton(" �ڳ���� ��ȭ ");
		JPanel talk=new JPanel(new FlowLayout(FlowLayout.CENTER));
		childToTalk.addActionListener(new CommunicationBtnListener());
		talk.add(childToTalk);
		JButton togetherSave=new JButton(" �Բ� �����ؿ� ");
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
			//�ڳ� �뵷�ֱ� ��ư�� ������ �� �ڳ��ڵ忡 ����Ǿ��ִ� �뵷�ֱ��ϴ� string���� set�޼ҵ带 ��������ؼ� �Ѱ��־� �뵷�ֱ�â���� setText�ؾ��Ѵ�!!
			System.out.println("��¥ ����,�ڳ��ڵ� :"+calenda.get(Calendar.YEAR)+calenda.get(Calendar.MONTH)+calenda.get(Calendar.DATE));
			moneyInput.setVisible(true);
		}
	}
	private class CommunicationBtnListener implements ActionListener{
		public void actionPerformed(ActionEvent arg0){
			System.out.println("��¥ ����,�ڳ��ڵ� :"+calenda.get(Calendar.YEAR)+calenda.get(Calendar.MONTH)+calenda.get(Calendar.DATE));
			memo.setVisible(true);
		}
	}
	private class SavingBtnListener implements ActionListener{
		public void actionPerformed(ActionEvent arg0){
			System.out.println("�ڳ� ����ڵ�");
			saving.setVisible(true);
		}
	}
	/*private class CancelBtnListener implements ActionListener{
		public void actionPerformed(ActionEvent arg0){
			setVisible(false);
		}
	}*/
}
