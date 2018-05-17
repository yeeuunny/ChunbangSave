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
		
		this.detailBtn = new JButton("자세히 보기");
		detailBtn.addActionListener(new ShowDetailAllowanceBtnListener());
		this.cancelBtn = new JButton("그만 보기");
		JLabel needTitleLabel = new JLabel("용돈이 필요했구나!",SwingConstants.CENTER);
		String[] colNames1 = { "날짜","결정", "주신금액","엄마아빠는 이렇게 생각했단다!" };
		if(code==1){
			colNames1[0] = "날짜";
			colNames1[1] = "상태";
			colNames1[2] = "이만큼필요해요!";
			colNames1[3] = "이래서필요해요!";
			needTitleLabel.setText("용돈이 필요해요!");
		}
		DefaultTableModel model;
		model = new DefaultTableModel(null, colNames1);
		allowanceTable = new JTable(model);
		model.addRow(new Object[]{
				calendar.get(Calendar.YEAR) + "년 "+ (calendar.get(Calendar.MONTH) + 1) + "월 "+ calendar.get(Calendar.DATE) + "일",
				"O", "3000","그것보다 더 좋은 일에 쓰자꾸나"
		});
		JScrollPane scrollPane = new JScrollPane(allowanceTable);
	//needTitleLabel = new JLabel("용돈이 필요했구나!",SwingConstants.CENTER);
		needTitleLabel.setFont(new Font("맑은고딕",Font.BOLD,30));
		//제목 panel
		JPanel temp2 = new JPanel();
		temp2.add(new JLabel(" "));			
		JPanel titlePanel1 = new JPanel();
		titlePanel1.setLayout(new GridLayout(3,1));
		titlePanel1.add(temp2);
		titlePanel1.add(needTitleLabel);
		//버튼 panel
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
		//리스트 패널
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
		super.setLocation(800, 600); // 위치
		super.setPreferredSize(new Dimension(500, 600)); // 크기cx000000000000000000
		// super.setDefaultCloseOperation(JDialog.EXIT_ON_CLOSE);
		super.pack();
		//super.setVisible(true);
	}
	/**
	 * 
	 * @param calendar 업데이트할 달력
	 * @param code 등록코드
	 */
	public void setData(GregorianCalendar calendar ,int code){
		this.calendar=calendar;
		this.code=code;
	}
	/**
	 * 
	 * @author 동익
	 *   자세히 보기 버튼 Event Listener
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
			System.out.print("\n사용자 선택 용돈 답변 날짜 : ");
			System.out.println(showList[0]);
			System.out.println("지급여부 : "+showList[1]);
			System.out.println("지급금액 : "+showList[2]);
			System.out.println("지급이유 : "+showList[3]);
			detailUI.setShowList(showList);
			detailUI.setVisible(true);
		}
	}
}
