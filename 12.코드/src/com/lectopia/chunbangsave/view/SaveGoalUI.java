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
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;

import com.lectopia.chunbangsave.vo.SavingGoalVO;

public class SaveGoalUI extends JDialog{
	private JLabel single;
	private JLabel duble;
	
	
	private JButton goalSel;
	private JButton close;
	
	private NewSaveGoalUI save;
	private int parentCode;
	private int childCode;
	
	private JLabel goals; //�ڳ� ����� ǥ�� ����
	private JLabel pGoals;//�θ� ����� ǥ�� ����
	
	private JLabel goal;//�ڳ� ������ ����
	private JLabel pGoal;//�θ� ������ ����
	
	private JProgressBar childBar;//���� �ۼ�Ʈ ��
	private JProgressBar parentBar;//�θ� �ۼ�Ʈ ��
	private int pan;
	public SaveGoalUI(JDialog frame,int parentCode,int childCode,int pan){//----------------------------------
		super(frame,true);
		setTitle("�Բ� �����ؿ�!");
		this.parentCode=parentCode;
		this.childCode=childCode;
		this.pan=pan;//------------------------------------------------------
		System.out.println("�ڳ��� �ڵ�� �θ����ڵ带 ���� "+parentCode+childCode);
		
		Container contentPane=getContentPane();
		save=new NewSaveGoalUI(this,1,1,pan,this);
		save.setVisible(false);
		
		//�̹��� ���� ���̺� ����
		ImageIcon singleIcon=new ImageIcon("single.png");
		single=new JLabel("��",singleIcon,JLabel.LEFT);
		ImageIcon dubleIcon=new ImageIcon("double.png");
		duble=new JLabel("�θ�",dubleIcon,JLabel.LEFT);
		ImageIcon goalIcon=new ImageIcon("gift.png");
		JLabel iconSetGift=new JLabel(goalIcon);
		
		//���� ��� �����ƺ� �Բ������ؿ� �κ�
		JLabel title1=new JLabel("");
	//	if(pan==1){
			title1.setText("�츮 ���̿�");
	//	}
		//else{
		//	title1.setText("����! �ƺ�!");
		//}
		JLabel title2=new JLabel("�Բ� �����ؿ� ");
		JPanel topPan=new JPanel(new GridLayout(2,1));
		topPan.add(title1);
		topPan.add(title2);
		JPanel top=new JPanel();
		top.setLayout(new FlowLayout());
		top.add(iconSetGift);
		top.add(topPan);
		
		//�ߴ� �κ��� ���� �ι� �ۼ�
		childBar=new JProgressBar();
		childBar.setMinimum(0);
		childBar.setMaximum(100);
		childBar.setStringPainted(true);
		childBar.setValue(0);
		childBar.setForeground(Color.RED);
		
		//childBar.setStringPainted(false);
		
		JPanel child=new JPanel(new FlowLayout());
		JPanel childSub=new JPanel(new GridLayout(3,1));
		goal=new JLabel("��ǥ�� �����ּ���");
		goals=new JLabel("");
		childSub.add(goal);
		childSub.add(childBar);
		childSub.add(goals);
		child.add(single);
		child.add(childSub);
		
		//�ߴܺι��� �θ� �ۼ�
		parentBar=new JProgressBar();//�긦 �гο��ٰ� ������ ����
		parentBar.setMinimum(0);//0~100���� ����
		parentBar.setMaximum(100);
		parentBar.setStringPainted(true);//���̰� ����
		parentBar.setValue(0);// ���� �ۼ�Ʈ ���̰� 70�ϸ� 70%�� ����
		parentBar.setForeground(Color.blue);//���α׷����� ����
		//parentBar.setStringPainted(false);//���α׷����ٿ� 70�� ���� �׷����� 70%��� ǥ�õǴµ� �װ� ����� �ڵ�
		
		JPanel parent=new JPanel(new FlowLayout());
		JPanel parentSub=new JPanel(new GridLayout(3,1));
		 pGoal=new JLabel("��° ����ġ���� ");
		pGoals=new JLabel("2016~11");
		parentSub.add(pGoal);
		parentSub.add(parentBar);
		parentSub.add(pGoals);
		parent.add(duble);
		parent.add(parentSub);
		
		JPanel info=new JPanel();
		info.setLayout(new BoxLayout(info,BoxLayout.Y_AXIS));
		info.add(child);
		info.add(parent);
		
		goalSel=new JButton("��ǥ���ϱ�");
		close=new JButton("�ݱ�");
		JPanel but=new JPanel();
		but.setLayout(new FlowLayout());
		but.add(goalSel);
		but.add(close);
		
		goalSel.addActionListener(new SetGoalStartBtnListener());
		close.addActionListener(new CancelBtnListener(this));
		
		contentPane.add(top,BorderLayout.NORTH);
		contentPane.add(info);
		contentPane.add(but,BorderLayout.SOUTH);
		
		
		setSize(500,400);
		setLocation(400,300);
		//setVisible(true);
		//setVisible(true);
	}
	public void setData(SavingGoalVO[] data){
		if(data[0]!=null){
			goal.setText(data[0].getGoalTitle());
			int[] childDate=new int[]{Integer.parseInt(data[0].getStartDate()[0]),Integer.parseInt(data[0].getStartDate()[1]),
					Integer.parseInt(data[0].getStartDate()[2])};
			goals.setText(childDate[0]+"��"+childDate[1]+"��"+childDate[2]+"�� ���� ~");
			childBar.setValue(data[0].getSuccessRate());
		
			if(pan!=1)goalSel.setEnabled(false);
		}else{
			goal.setText("��ǥ�� �����ּ���!");
			goals.setText("");
			childBar.setValue(0);
			if(pan!=1)goalSel.setEnabled(true);
		}
		
		if(data[1]!=null){
		pGoal.setText(data[1].getGoalTitle());
		int[] parentDate=new int[]{Integer.parseInt(data[1].getStartDate()[0]),Integer.parseInt(data[1].getStartDate()[1]),
				Integer.parseInt(data[1].getStartDate()[2])};
		pGoals.setText(parentDate[0]+"��"+parentDate[1]+"��"+parentDate[2]+"�� ���� ~");
		parentBar.setValue(data[1].getSuccessRate());
		if(pan==1)goalSel.setEnabled(false);
		}else
		{
			pGoal.setText("��ǥ�� ������ �ʾҾ��!");
			pGoals.setText("");
			parentBar.setValue(0);
			goalSel.setEnabled(true);
			if(pan==1)goalSel.setEnabled(true);
		}
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
