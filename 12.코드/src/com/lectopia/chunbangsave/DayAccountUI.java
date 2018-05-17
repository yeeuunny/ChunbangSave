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

public class DayAccountUI extends JDialog{
	private GregorianCalendar today;
	private int code;
	private JLabel dayLabel;
	private JLabel allowanceTitle;
	private JLabel dayLimit;
	private JLabel useTotal;
	private JButton setDayLimitBtn;
	private JButton addAccountBtn;
	private AddMoneyUI addMoneyUI;
	private SetDayLimitUI setDayLimitUI;
	private DetailAccountUI detailAccountUI;
	public void getCalendar(GregorianCalendar today){
		this.today = today;
		System.out.println(this.today.get(Calendar.DATE)+"  dasdsd");	
		dayLabel.setText(this.today.get(Calendar.YEAR)+"�� "
		+(this.today.get(Calendar.MONTH)+1)+"�� "
		+this.today.get(Calendar.DATE)+"��");
	}
	public DayAccountUI(JFrame frame,GregorianCalendar today, int code){
		super(frame,true);
		setTitle("���ϰ��");

		this.today = today;
		this.code = code;
		
		addMoneyUI=new AddMoneyUI(DayAccountUI.this,today,code);
		addMoneyUI.setVisible(false);
		
		setDayLimitUI=new SetDayLimitUI(DayAccountUI.this,today,code);
		setDayLimitUI.setVisible(false);
		
		detailAccountUI=new DetailAccountUI(DayAccountUI.this,code, today);
		
		Container contentpane = getContentPane();
		System.out.println(this.today.get(Calendar.YEAR));
		System.out.println(this.today.get(Calendar.MONTH)+1);
		System.out.println(this.today.get(Calendar.DATE));
		dayLabel = new JLabel(this.today.get(Calendar.YEAR)+"�� "
								+(this.today.get(Calendar.MONTH)+1)+"�� "
									+this.today.get(Calendar.DATE)+"��",SwingConstants.CENTER);
		
		dayLabel.setFont(new Font("�޸��߰���ü",Font.BOLD,30));
		/*
		 * �θ� �ڳ� ����
		 */
		if(code == 1){
			allowanceTitle = new JLabel("������ �뵷",SwingConstants.CENTER);
			allowanceTitle.setFont(new Font("�����Ȼ��ü",Font.BOLD,40));
			setDayLimitBtn = new JButton("������ �̸�ŭ ���Կ�!");
			addAccountBtn = new JButton("���� �����ؿ�!");
		}
		else if(code == 2){
			allowanceTitle = new JLabel("������ ����",SwingConstants.CENTER);
			allowanceTitle.setFont(new Font("�����Ȼ��ü",Font.BOLD,40));
			setDayLimitBtn = new JButton("����γ��� �߰�");
			addAccountBtn = new JButton("��Ȱ�� ����");
		}

		JPanel titlePanel1 = new JPanel();
		JPanel temp2 = new JPanel();
		temp2.add(new JLabel(" "));	
		temp2.setBackground(Color.white);
		titlePanel1.setLayout(new GridLayout(3,1));
		titlePanel1.add(temp2);
		titlePanel1.add(dayLabel);
		titlePanel1.add(allowanceTitle);
		titlePanel1.setBackground(Color.white);
		/*
		 * ������ �̸�ŭ ! nested class ��ư ������!
		 */
		setDayLimitBtn.addActionListener(new TodayLimitBtnListener());
		addAccountBtn.addActionListener(new MoneyEditBtnListener());
		/*
		 * �����ѵ� �����;��ϴºκ�
		 */
		//if(�����ѵ� != 0){
			dayLimit = new JLabel("5000"+"��",SwingConstants.CENTER);
			useTotal = new JLabel("3500"+"��",SwingConstants.CENTER);
			dayLimit.setFont(new Font("�޸��߰���ü",Font.BOLD,20));
			dayLimit.setForeground(Color.BLUE);
			useTotal.setFont(new Font("�޸��߰���ü",Font.BOLD,20));
			useTotal.setForeground(Color.RED);
			
			
			JPanel temp = new JPanel();
			JPanel temp4 = new JPanel();
			temp4.add(new JLabel("========================",SwingConstants.CENTER));
			temp4.setBackground(Color.white);
	
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
/*�����ѵ������*/		//}
			/*else{//�����ѵ� �������� ������
			    // ����� �߰� ��ư ��Ȱ��ȭ
				addAccountBtn.setEnabled(false);
				// �����ѵ��� ���ݾ� �ʱ�ȭ
				if(code==1)
					dayLimit = new JLabel("������ �� �� ���ΰ���?",SwingConstants.CENTER);
				else
					dayLimit = new JLabel("�����ѵ��� ���� �������ּ���!",SwingConstants.CENTER);
				//�� �г�
				useTotal = new JLabel(" ",SwingConstants.CENTER);
				dayLimit.setFont(new Font("�޸��߰���ü",Font.BOLD,20));
				dayLimit.setForeground(Color.BLUE);
				useTotal.setFont(new Font("�޸��߰���ü",Font.BOLD,20));
				useTotal.setForeground(Color.RED);
				// �г� ����
				 JPanel temp = new JPanel();
				JPanel temp4 = new JPanel();
				temp4.add(new JLabel("========================",SwingConstants.CENTER));
				temp4.setBackground(Color.white);
				JPanel titlePanel3 = new JPanel();
				titlePanel3.setLayout(new GridLayout(4,1));
				titlePanel3.add(dayLimit);
				titlePanel3.add(useTotal);
				titlePanel3.add(temp4);
				setLayout(new GridLayout(4, 1));
				// ��� �˸� â
				add(titlePanel1);
				add(titlePanel3);
				// ����� ���� ĭ
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
				add(panel);
			}*/
		JPanel btnPanel = new JPanel();
		//btnPanel.setLayout(new BoxLayout(btnPanel, BoxLayout.X_AXIS));
		btnPanel.setLayout(new FlowLayout());
		btnPanel.setBackground(Color.white);
		btnPanel.add(setDayLimitBtn,BorderLayout.CENTER);
		btnPanel.add(addAccountBtn,BorderLayout.CENTER);
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
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		super.pack();
	//	super.setVisible(true);
	}
	/**
	 * 
	 * @author ����
	 *  ����� ���� �߰� ��ư listener
	 */
	private class MoneyEditBtnListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			addMoneyUI.setData(today, code);
			addMoneyUI.setVisible(true);
		}
	}
	/**
	 * 
	 * @author ����
	 *  �����ѵ� ���� ��ư listener
	 */
	private class TodayLimitBtnListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			setDayLimitUI.setData(today, code);
			setDayLimitUI.setVisible(true);
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
			detailAccountUI.setData(today,  Integer.parseInt(tempLabel.getText()), code);
			detailAccountUI.setVisible(true);
		}
	}
}
