package com.lectopia.chunbangsave.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;

public class SaveGoalUI extends JDialog{
	private JLabel single;
	private JLabel duble;
	private JLabel goal;
	
	private JButton goalSel;
	private JButton close;
	
	private NewSaveGoalUI save;
	private int parentCode;
	private int childCode;
	public SaveGoalUI(JDialog frame,int parentCode,int childCode){
		super(frame,true);
		setTitle("함께 저축해요!");
		this.parentCode=parentCode;
		this.childCode=childCode;
		
		System.out.println("자녀등록 코드와 부모등록코드를 받음 "+parentCode+childCode);
		
		Container contentPane=getContentPane();
		save=new NewSaveGoalUI(this,1,1);
		save.setVisible(false);
		
		//이미지 들어가는 레이블 생성
		ImageIcon singleIcon=new ImageIcon("single.png");
		single=new JLabel("나",singleIcon,JLabel.LEFT);
		ImageIcon dubleIcon=new ImageIcon("double.png");
		duble=new JLabel("부모",dubleIcon,JLabel.LEFT);
		ImageIcon goalIcon=new ImageIcon("gift.png");
		JLabel iconSetGift=new JLabel(goalIcon);
		
		//가장 상단 엄마아빠 함께저축해요 부분
		JLabel title1=new JLabel("엄마! 아빠!");
		JLabel title2=new JLabel("함께 저축해요 ");
		JPanel topPan=new JPanel(new GridLayout(2,1));
		topPan.add(title1);
		topPan.add(title2);
		JPanel top=new JPanel();
		top.setLayout(new FlowLayout());
		top.add(iconSetGift);
		top.add(topPan);
		
		//중단 부분의 아이 부문 작성
		JProgressBar childBar=new JProgressBar();
		childBar.setMinimum(0);
		childBar.setMaximum(100);
		childBar.setStringPainted(true);
		childBar.setValue(30);
		childBar.setForeground(Color.RED);
		
		childBar.setStringPainted(false);
		
		JPanel child=new JPanel(new FlowLayout());
		JPanel childSub=new JPanel(new GridLayout(3,1));
		goal=new JLabel("목표를 세워주세양");
		JLabel goals=new JLabel("2016/02/01~2017/12/20");
		childSub.add(goal);
		childSub.add(childBar);
		childSub.add(goals);
		child.add(single);
		child.add(childSub);
		
		//중단부문의 부모 작성
		JProgressBar parentBar=new JProgressBar();//얘를 패널에다가 넣으면 보임
		parentBar.setMinimum(0);//0~100까지 설정
		parentBar.setMaximum(100);
		parentBar.setStringPainted(true);//보이게 설정
		parentBar.setValue(70);// 현재 퍼센트 보이게 70하면 70%가 보임
		parentBar.setForeground(Color.blue);//프로그레스바 색깔
		parentBar.setStringPainted(false);//프로그레스바에 70이 들어가면 그래프에 70%라고 표시되는데 그거 지우는 코드
		
		JPanel parent=new JPanel(new FlowLayout());
		JPanel parentSub=new JPanel(new GridLayout(3,1));
		JLabel pGoal=new JLabel("둘째 돌찬치까지 ");
		JLabel pGoals=new JLabel("2016~11");
		parentSub.add(pGoal);
		parentSub.add(parentBar);
		parentSub.add(pGoals);
		parent.add(duble);
		parent.add(parentSub);
		
		JPanel info=new JPanel();
		info.setLayout(new BoxLayout(info,BoxLayout.Y_AXIS));
		info.add(child);
		info.add(parent);
		
		goalSel=new JButton("목표정하기");
		close=new JButton("닫기");
		JPanel but=new JPanel();
		but.setLayout(new FlowLayout());
		but.add(goalSel);
		but.add(close);
		
		goalSel.addActionListener(new SetGoalStartBtnListener());
		close.addActionListener(new CloseBtnListener());
		
		contentPane.add(top,BorderLayout.NORTH);
		contentPane.add(info);
		contentPane.add(but,BorderLayout.SOUTH);
		
		
		setSize(500,400);
		setLocation(400,300);
		//setVisible(true);
		//setVisible(true);
	}
	private class SetGoalStartBtnListener implements ActionListener{

		public void actionPerformed(ActionEvent e) {
			save.setVisible(true);
		}
		
	}
	private class CloseBtnListener implements ActionListener{
		public void actionPerformed(ActionEvent e){
			setVisible(false);
		}
	}
}
