package com.lectopia.chunbangsave.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.Calendar;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class DetailAllowanceListUI extends JDialog{
	private JLabel today;
	private JLabel content;
	private JTextArea contentArea;
	private JLabel balance;
	private JLabel balanceLabel;
	private JButton confirmBtn;
	private String[]showList;
	public DetailAllowanceListUI(JDialog dialog,String[]showList,int code){
		super(dialog,true);
		this.showList = showList;
		JLabel needTitleLabel = new JLabel("�뵷�� �ʿ��߱���!",SwingConstants.CENTER);
		content = new JLabel("�̷��� �����ߴܴ�!                                                  ");
		balance = new JLabel("�̸�ŭ �ֱ�� �ߴܴ�!              ");
		if(code == 1){
			needTitleLabel.setText("�뵷�� �ʿ��ؿ�!");
			content.setText("�̷��� �ʿ��ؿ�!                                                  ");
			balance.setText("�̸�ŭ �ʿ��ؿ�!              ");
		}
		needTitleLabel.setFont(new Font("�������",Font.BOLD,30));
		//���� panel
		JPanel temp2 = new JPanel();
		temp2.add(new JLabel(" "));			
		JPanel titlePanel1 = new JPanel();
		titlePanel1.setLayout(new GridLayout(3,1));
		titlePanel1.add(temp2);
		titlePanel1.add(needTitleLabel);
		//��¥
		today = new JLabel(showList[0]);
		JLabel dayName = new JLabel("��¥ : ");
		JPanel datePanel = new JPanel();
		datePanel.setLayout(new FlowLayout());
		datePanel.add(dayName, BorderLayout.CENTER);
		datePanel.add(today, BorderLayout.CENTER);
		JPanel totdatePanel = new JPanel();
		JPanel emptyPanel = new JPanel();
		totdatePanel.setLayout(new GridLayout(2, 1));
		totdatePanel.add(emptyPanel);
		totdatePanel.add(datePanel);
		//�̷��� �����ߴܴ�!
		contentArea = new JTextArea(4,27);
		contentArea.setText(showList[3]);
/**/	contentArea.setEditable(false);
		JPanel contentPanel = new JPanel();
		//contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
		contentPanel.add(content);
		contentPanel.add(contentArea);
		//�̸�ŭ �ֱ���ߴܴ�!
		balanceLabel = new JLabel(showList[2]);
		JPanel balancePanel = new JPanel();
		balancePanel.add(balance);
		balancePanel.add(balanceLabel);
		//Ȯ�� ��ư
		confirmBtn = new JButton("Ȯ��");
		JPanel  confirmPanel = new JPanel();
		confirmPanel.add(confirmBtn);
		
		JPanel totPanel = new JPanel();
		totPanel.setLayout(new BoxLayout(totPanel, BoxLayout.Y_AXIS));
		totPanel.add(titlePanel1);
		totPanel.add(totdatePanel);
		totPanel.add(contentPanel);
		totPanel.add(balancePanel);
		totPanel.add(confirmPanel);
		
		add(totPanel);
				
		super.setResizable(false);
		super.setLocation(850, 650); // ��ġ
		super.setPreferredSize(new Dimension(500, 600)); // ũ��cx000000000000000000
		// super.setDefaultCloseOperation(JDialog.EXIT_ON_CLOSE);
		super.pack();
		//super.setVisible(true);
	}

 	public void setShowList(String[] showList){
 		this.showList=showList;
		today.setText(showList[0]);
		contentArea.setText(showList[3]);
		balanceLabel.setText(showList[2]);
 	}

}
