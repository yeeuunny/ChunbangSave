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
import javax.swing.SwingConstants;

public class CommunicationWithParent extends JDialog{
	private int code;
	private NeedAllowanceUI needUI;
	private MemoUI memoUI;
	private SaveGoalUI saveUI;
	public CommunicationWithParent(JFrame frame,int code){
		super(frame,true);
		this.code = code;
		Container contentPane=getContentPane();
		//â �̸�����
		needUI = new NeedAllowanceUI(this, this.code);
		memoUI = new MemoUI(this,this.code);
		saveUI = new SaveGoalUI(this,1,this.code);
		needUI.setVisible(false);
		memoUI.setVisible(false);
		saveUI.setVisible(false);

		JLabel needTitleLabel = new JLabel("����!�ƺ�!",SwingConstants.CENTER);
		needTitleLabel.setFont(new Font("�������",Font.BOLD,30));
		//���� panel
		JPanel temp2 = new JPanel();
		temp2.add(new JLabel(" "));			
		JPanel titlePanel1 = new JPanel();
		titlePanel1.setLayout(new GridLayout(3,1));
		titlePanel1.add(temp2);
		titlePanel1.add(needTitleLabel);
		//���� ��� �κ��� �г� ��ġ
		JButton home=new JButton("Ȩ");
		JPanel homes=new JPanel(new FlowLayout(FlowLayout.RIGHT));
		homes.add(home);
		//ImageIcon child=new ImageIcon("child.png");
		//JLabel childIcon=new JLabel(child);

		JPanel topen=new JPanel();
		
		topen.setLayout(new BoxLayout(topen,BoxLayout.Y_AXIS));
		topen.add(homes);
		//homes.set;
		
		home.addActionListener(new CancelBtnListener());
		
		//�߰� �κ��� ��ư ��ġ
		JButton childCalendar=new JButton("    �뵷�� �ʿ��ؿ�!   ");
		JPanel calendar=new JPanel(new FlowLayout(FlowLayout.CENTER,0,1));
		calendar.add(childCalendar);
		childCalendar.setSize(40,50);
		JButton childMoneyIn=new JButton(" �ϰ���� ���� �־��!  ");
		JPanel money=new JPanel(new FlowLayout(FlowLayout.CENTER));
		childMoneyIn.addActionListener(new TalkToParentBtnListener());
		money.add(childMoneyIn);
		JButton togetherSave=new JButton("        �Բ� �����ؿ�         ");
		JPanel save=new JPanel(new FlowLayout(FlowLayout.CENTER));
		save.add(togetherSave);
		JPanel mid=new JPanel();
		mid.setLayout(new BoxLayout(mid,BoxLayout.Y_AXIS));
		mid.add(titlePanel1);
		mid.add(calendar);
		mid.add(money);
		mid.add(save);
		
		childCalendar.addActionListener(new NeedAllowanceBtnListener());
		togetherSave.addActionListener(new SavingBtnListener());
	//	childMoneyIn.addActionListener(new GiveMoneyBtnListener());
		contentPane.add(topen,BorderLayout.NORTH);
		contentPane.add(mid);
		
		setSize(500,400);
		setLocation(400,300);
	//	setVisible(true);
	}
	private class NeedAllowanceBtnListener implements ActionListener{

		public void actionPerformed(ActionEvent arg0) {
			needUI.setVisible(true);
		}
	}
	private class TalkToParentBtnListener implements ActionListener{
		public void actionPerformed(ActionEvent arg0){
			System.out.println("okok");
			memoUI.setVisible(true);
		}
	}
	private class SavingBtnListener implements ActionListener{
		public void actionPerformed(ActionEvent arg0){
			saveUI.setVisible(true);
		}
	}
	private class CancelBtnListener implements ActionListener{
		public void actionPerformed(ActionEvent arg0){
			setVisible(false);
		}
	}
}
