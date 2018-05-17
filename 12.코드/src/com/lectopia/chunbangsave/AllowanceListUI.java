package com.lectopia.chunbangsave.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

public class AllowanceListUI extends JDialog{
	private GregorianCalendar calendar;
	private int code;
	private JTable allowanceTable;
	private JButton detailBtn;
	private JButton cancelBtn;
	private DetailAllowanceListUI detailUI;
	public AllowanceListUI(JDialog dialog,GregorianCalendar calendar, int code){
		super(dialog,true);
		this.calendar = calendar;
		this.code = code;
		String[]tempList = new String[4];
		detailUI=new DetailAllowanceListUI(this,tempList,this.code);
		detailUI.setVisible(false);
		
		this.detailBtn = new JButton("�ڼ��� ����");
		detailBtn.addActionListener(new ShowDetailAllowanceBtnListener());
		this.cancelBtn = new JButton("�׸� ����");
		JLabel needTitleLabel = new JLabel("�뵷�� �ʿ��߱���!",SwingConstants.CENTER);
		String[] colNames1 = { "��¥","����", "�ֽűݾ�","�����ƺ��� �̷��� �����ߴܴ�!" };
		if(code==1){
			colNames1[0] = "��¥";
			colNames1[1] = "����";
			colNames1[2] = "�̸�ŭ�ʿ��ؿ�!";
			colNames1[3] = "�̷����ʿ��ؿ�!";
			needTitleLabel.setText("�뵷�� �ʿ��ؿ�!");
		}
		DefaultTableModel model;
		model = new DefaultTableModel(null, colNames1);
		allowanceTable = new JTable(model);
		model.addRow(new Object[]{
				calendar.get(Calendar.YEAR) + "�� "+ (calendar.get(Calendar.MONTH) + 1) + "�� "+ calendar.get(Calendar.DATE) + "��",
				"O", "3000","�װͺ��� �� ���� �Ͽ� ���ڲٳ�"
		});
		JScrollPane scrollPane = new JScrollPane(allowanceTable);
	//needTitleLabel = new JLabel("�뵷�� �ʿ��߱���!",SwingConstants.CENTER);
		needTitleLabel.setFont(new Font("�������",Font.BOLD,30));
		//���� panel
		JPanel temp2 = new JPanel();
		temp2.add(new JLabel(" "));			
		JPanel titlePanel1 = new JPanel();
		titlePanel1.setLayout(new GridLayout(3,1));
		titlePanel1.add(temp2);
		titlePanel1.add(needTitleLabel);
		//��ư panel
		JPanel btnPanel = new JPanel();
		//btnPanel.setLayout(new BoxLayout(btnPanel, BoxLayout.X_AXIS));
		btnPanel.setLayout(new FlowLayout());
		btnPanel.add(detailBtn,BorderLayout.CENTER);
		btnPanel.add(cancelBtn,BorderLayout.CENTER);
		JPanel totBtnPanel = new JPanel();
		JPanel emptyPanel = new JPanel();
		totBtnPanel.setLayout(new GridLayout(2,1));
		totBtnPanel.add(emptyPanel);
		totBtnPanel.add(btnPanel);
		//����Ʈ �г�
		JPanel tempPanel = new JPanel();
		//tempPanel.setLayout(new GridLayout(1,3));
		tempPanel.setLayout(new BoxLayout(tempPanel, BoxLayout.X_AXIS));
		JPanel emptyPanel2 = new JPanel();
		tempPanel.add(emptyPanel2);
		tempPanel.add(scrollPane);
		tempPanel.add(emptyPanel);
		
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		panel.add(titlePanel1);
		panel.add(tempPanel);
		panel.add(btnPanel);
		
		add(panel);
		
		cancelBtn.addActionListener(new CancelBtnListener(this));
		super.setResizable(false);
		super.setLocation(800, 600); // ��ġ
		super.setPreferredSize(new Dimension(500, 600)); // ũ��cx000000000000000000
		// super.setDefaultCloseOperation(JDialog.EXIT_ON_CLOSE);
		super.pack();
		//super.setVisible(true);
	}
	/**
	 * 
	 * @param calendar ������Ʈ�� �޷�
	 * @param code ����ڵ�
	 */
	public void setData(GregorianCalendar calendar ,int code){
		this.calendar=calendar;
		this.code=code;
	}
	/**
	 * 
	 * @author ����
	 *   �ڼ��� ���� ��ư Event Listener
	 */
	private class ShowDetailAllowanceBtnListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			int row = allowanceTable.getSelectedRow();
			DefaultTableModel model = (DefaultTableModel)allowanceTable.getModel();
			String[]showList = new String[4]; 
			showList[0] = model.getValueAt(row, 0).toString();
			showList[1] = model.getValueAt(row, 1).toString();
			showList[2] = model.getValueAt(row, 2).toString();
			showList[3] = model.getValueAt(row, 3).toString();
			System.out.print("\n����� ���� �뵷 �亯 ��¥ : ");
			System.out.println(showList[0]);
			System.out.println("���޿��� : "+showList[1]);
			System.out.println("���ޱݾ� : "+showList[2]);
			System.out.println("�������� : "+showList[3]);
			detailUI.setShowList(showList);
			detailUI.setVisible(true);
		}
	}
}
