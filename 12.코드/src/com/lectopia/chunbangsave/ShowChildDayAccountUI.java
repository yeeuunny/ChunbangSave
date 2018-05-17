package com.lectopia.chunbangsave.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;

public class ShowChildDayAccountUI extends JDialog{
	private GregorianCalendar today;
	private int code;
	private JLabel dayLabel;
	private JLabel allowanceTitle;
	private JLabel dayLimit;
	private JLabel useTotal;
	private JButton complimentBtn;
	private JButton supportBtn;
	
	private ShowChildDetailAccountUI showChildDayAccountUI;
	
	public void getCalendar(GregorianCalendar today){
		this.today = today;
		//System.out.println(this.today+"  dasdsd");	
		dayLabel.setText(this.today.get(Calendar.YEAR)+"�� "
		+(this.today.get(Calendar.MONTH)+1)+"�� "
		+this.today.get(Calendar.DATE)+"��");

	}
	public ShowChildDayAccountUI(JFrame frame,GregorianCalendar today, int code){
		super(frame,true);
		setTitle("���ϰ��");
		this.today = today;
		this.code = code;
		Container contentpane = getContentPane();
		
		showChildDayAccountUI=new ShowChildDetailAccountUI(ShowChildDayAccountUI.this,code,today);
		showChildDayAccountUI.setVisible(false);
		System.out.println(this.today.get(Calendar.YEAR));
		System.out.println(this.today.get(Calendar.MONTH)+1);
		System.out.println(this.today.get(Calendar.DATE));
		dayLabel = new JLabel(this.today.get(Calendar.YEAR)+"�� "
								+(this.today.get(Calendar.MONTH)+1)+"�� "
									+this.today.get(Calendar.DATE)+"��",SwingConstants.CENTER);
		
		dayLabel.setFont(new Font("�޸��߰���ü",Font.BOLD,30));
	
		if(code == 1){
			allowanceTitle = new JLabel("������ �뵷",SwingConstants.CENTER);
			allowanceTitle.setFont(new Font("�����Ȼ��ü",Font.BOLD,40));
			complimentBtn = new JButton("Ī���ؿ�");
			supportBtn = new JButton("�����ؿ�");
		}
	
		/*
		 * �����ѵ� �����;��ϴºκ�
		 */
		dayLimit = new JLabel("5000"+"��",SwingConstants.CENTER);
		useTotal = new JLabel("3500"+"��",SwingConstants.CENTER);
		dayLimit.setFont(new Font("�޸��߰���ü",Font.BOLD,20));
		dayLimit.setForeground(Color.BLUE);
		useTotal.setFont(new Font("�޸��߰���ü",Font.BOLD,20));
		useTotal.setForeground(Color.RED);
		
		/*
		 * ������ �̸�ŭ ! nested class
		 */
		complimentBtn.addActionListener(new ComplimentBtnListener());
		supportBtn.addActionListener(new SupportBtnListener());
		
		
		JPanel temp = new JPanel();
		JPanel temp2 = new JPanel();
		temp2.add(new JLabel(" "));	
		temp2.setBackground(Color.white);
		JPanel temp4 = new JPanel();
		temp4.add(new JLabel("========================",SwingConstants.CENTER));
		temp4.setBackground(Color.white);
	
		
		JPanel titlePanel1 = new JPanel();
		titlePanel1.setLayout(new GridLayout(3,1));
		titlePanel1.add(temp2);
		titlePanel1.add(dayLabel);
		titlePanel1.add(allowanceTitle);
		titlePanel1.setBackground(Color.white);
		
		JPanel titlePanel3 = new JPanel();
		titlePanel3.setLayout(new GridLayout(4,1));
		titlePanel3.add(dayLimit);
		titlePanel3.add(useTotal);
		titlePanel3.add(temp4);
		/*
		 * �����ѵ� - ���ݾ�
		 */
		JLabel remainCost = new JLabel("1500"+"��",SwingConstants.CENTER);
		remainCost.setFont(new Font("�޸��߰���ü",Font.BOLD,20));
		titlePanel3.add(remainCost);
		titlePanel3.setBackground(Color.white);
		
		//add(titlePanel1,BorderLayout.NORTH);
		//add(titlePanel2,BorderLayout.CENTER);
		//add(titlePanel3,BorderLayout.SOUTH);
		
		/*
		 * ����� ��� 
		 */
		int listCnt = 5; // ��� ��
		int category = 1; // �ӽ� ī�װ� ��ȣ
		JPanel listPanel[][] = new JPanel[listCnt][];
		for(int i = 0; i < listCnt; ++i){
			listPanel[i] = new JPanel[3];
			for(int j = 0 ; j < 3;++j){
				listPanel[i][j] = new JPanel();
				listPanel[i][j].setBackground(Color.white);
			}
		}
		/*
		 * �󼼺��⸦ ���� ���콺 ��ư ������
		 */
		DetailViewMouseBtnListener tempListener = new DetailViewMouseBtnListener();
		
		for(int i = 0; i <listCnt;++i){
			JLabel[][]list = new JLabel[listCnt][];
			if(category == 1){
				list[i] = new JLabel[4];
				//��Ģ����
				list[i][0] = new JLabel("��");
				list[i][0].setFont(new Font("�޸��߰���ü",Font.BOLD,20));
				list[i][0].setForeground(Color.green);
				//ī�װ�
				list[i][1] = new JLabel("�뵷");
				list[i][1].setFont(new Font("�޸��߰���ü",Font.BOLD,20));
				/*
				 * ���� ������ ���콺 Ŭ���̺�Ʈ�� �߻��Ѵ�! ���⿡�ٰ� index��ȣ�� �ְų� unique�� �����͸� ����
				 *  - index��ȣ�� �༭ listener���� �� index�� ������ ȸ�� ����� ������ �����;��ҵ�
				 */
				list[i][2] = new JLabel(Integer.toString(i));
				list[i][2].addMouseListener(tempListener);
				list[i][2].setForeground(Color.white);
				//����ݾ�
				list[i][3] = new JLabel("10000");
				JPanel tempPanel = new JPanel();
				tempPanel.setLayout(new GridLayout(1, 4));
				tempPanel.setBackground(Color.white);
				
				tempPanel.add(list[i][0]);
				tempPanel.add(list[i][1]);
				tempPanel.add(list[i][2]);
				tempPanel.add(list[i][3]);
				listPanel[i][1].add(tempPanel);
				//�ӽ�
				category =2;
			}
			else if(category == 2){
				list[i] = new JLabel[4];
				//��Ģ����
				list[i][0] = new JLabel("X");
				list[i][0].setFont(new Font("Arial Unicode MS",Font.BOLD,20));
				list[i][0].setForeground(Color.YELLOW);
				//ī�װ�
				list[i][1] = new JLabel("����");
				list[i][1].setFont(new Font("�޸��߰���ü",Font.BOLD,20));
				/*
				 * ���� ������ ���콺 Ŭ���̺�Ʈ�� �߻��Ѵ�! ���⿡�ٰ� index��ȣ�� �ְų� unique�� �����͸� ����
				 *  - index��ȣ�� �༭ listener���� �� index�� ������ ȸ�� ����� ������ �����;��ҵ�
				 */
				list[i][2] = new JLabel(Integer.toString(i));
				list[i][2].addMouseListener(tempListener);
				list[i][2].setForeground(Color.white);
				//����ݾ�
				list[i][3] = new JLabel("10000");
				JPanel tempPanel = new JPanel();
				tempPanel.setLayout(new GridLayout(1, 4));
				tempPanel.setBackground(Color.white);
				tempPanel.add(list[i][0]);
				tempPanel.add(list[i][1]);
				tempPanel.add(list[i][2]);
				tempPanel.add(list[i][3]);
				listPanel[i][1].add(tempPanel);
				//�ӽ�
				category =1;
			}
		}	
		/*
		 * ȭ�� �ϼ�
		 */
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(listCnt,3));
		for(int i = 0 ; i < listCnt;++i)
			for(int j = 0 ; j < 3; ++j)
				panel.add(listPanel[i][j]);
		setLayout(new GridLayout(4, 1));
		add(titlePanel1);
		add(titlePanel3);
		add(panel);
		
		JPanel btnPanel = new JPanel();
		//btnPanel.setLayout(new BoxLayout(btnPanel, BoxLayout.X_AXIS));
		btnPanel.setLayout(new FlowLayout());
		btnPanel.setBackground(Color.white);
		btnPanel.add(complimentBtn,BorderLayout.CENTER);
		btnPanel.add(supportBtn,BorderLayout.CENTER);
		JPanel totBtnPanel = new JPanel();
		JPanel emptyPanel = new JPanel();
		emptyPanel.setBackground(Color.white);
		totBtnPanel.setLayout(new GridLayout(2,1));
		totBtnPanel.add(emptyPanel);
		totBtnPanel.add(btnPanel);
		
		add(totBtnPanel);
		
		super.setBackground(Color.WHITE);
		super.setResizable(false);
		super.setLocation(700, 500); // ��ġ
		super.setPreferredSize(new Dimension(500, 700)); // ũ��cx000000000000000000
		//super.setDefaultCloseOperation(JDialog.EXIT_ON_CLOSE);
		//frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		super.pack();
	//	super.setVisible(true);
	}
	/**
	 * 
	 * @author ����
	 *  ����� ���� �߰� ��ư listener
	 */
	private class SupportBtnListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			System.out.println(e.getActionCommand());
		}
	}
	/**
	 * 
	 * @author ����
	 *  �����ѵ� ���� ��ư listener
	 */
	private class ComplimentBtnListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			System.out.println(e.getActionCommand());
		}
	}
	/**
	 * 
	 * @author ����
	 *  �󼼺��⸦ ���� listener
	 */
	private class DetailViewMouseBtnListener extends MouseAdapter{
		@Override
		public void mouseClicked(MouseEvent e) {
			JLabel tempLabel = (JLabel)e.getSource();
			//���⿡ showChildDayAccountUI���� ���̺� ��ȣ�� �ش��ϴ� �������� �޾� �Ѱ��ִ� set�޼ҵ带 ���� ���� �Ѱ� �־����
			
			showChildDayAccountUI.setVisible(true);
		//	new DetailAccountUI(DayAccountUI.this,code, today, Integer.parseInt(tempLabel.getText()));
		}
	}
}
