package com.lectopia.chunbangsave.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.LinkedList;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import com.lectopia.chunbangsave.vo.CashBookDetailVO;
import com.lectopia.chunbangsave.vo.MonthCashBookDetailVO;
import com.lectopia.chunbangsave.vo.QuarterAdminManager;
import com.lectopia.chunbangsave.vo.QuarterManager;

public class DetailAccountUI extends JDialog {
	private DayAccountUI dayAccountUI;
	private JPanel panel;
	private JLabel today;
	private JLabel balance;
	private JLabel category;
	private JLabel detailCategory;
	private JLabel useContent;
	private JButton reviseBtn;
	private JButton deleteBtn;
	private JButton cancelBtn;
	private GregorianCalendar calendar;
	private int code;
	private CashBookDetailVO reviseVo;
	private int index;
	private AddMoneyUI addMoneyUI;
	public DetailAccountUI(JDialog dialog, int code, GregorianCalendar calendar) {
		super(dialog, true);
		this.calendar = calendar;
		this.code = code;
		String[] reviseData=new String[]{"100","수입","-----선택해주세요----","잘해라"};
		
		addMoneyUI=new AddMoneyUI(DetailAccountUI.this,calendar,code,reviseData);
		addMoneyUI.setVisible(false);
		
		JPanel panel = new JPanel();
		TitledBorder title = new TitledBorder("영수증");
		title.setTitleFont(new Font("휴먼중간팸체", Font.BOLD, 30));
		panel.setBorder(title);

		today = new JLabel(calendar.get(Calendar.YEAR) + "년 "
				+ (calendar.get(Calendar.MONTH) + 1) + "월 "
				+ calendar.get(Calendar.DATE) + "일");
		today.setFont(new Font("휴먼중간팸체", Font.BOLD, 20));
		JLabel dayName = new JLabel("날짜 : ");
		dayName.setFont(new Font("휴먼중간팸체", Font.BOLD, 20));
		JPanel datePanel = new JPanel();
		datePanel.setLayout(new FlowLayout());
		datePanel.add(dayName, BorderLayout.CENTER);
		datePanel.add(today, BorderLayout.CENTER);
		JPanel totdatePanel = new JPanel();
		JPanel emptyPanel = new JPanel();
		totdatePanel.setLayout(new GridLayout(2, 1));
		totdatePanel.add(emptyPanel);
		totdatePanel.add(datePanel);
		
		/*
		 * 현재 잔액을 reviseVo를 통해 불러와야하는부분
		 */
		balance = new JLabel(Integer.toString(1000));
		balance.setFont(new Font("휴먼중간팸체", Font.BOLD, 20));
		JLabel balanceName = new JLabel("금액 : ");
		balanceName.setFont(new Font("휴먼중간팸체", Font.BOLD, 20));
		JPanel balancePanel = new JPanel();
		balancePanel.setLayout(new FlowLayout());
		balancePanel.add(balanceName, BorderLayout.CENTER);
		balancePanel.add(balance, BorderLayout.CENTER);
		JPanel totBalancePanel = new JPanel();
		totBalancePanel.setLayout(new GridLayout(2, 1));
		JPanel emptyPanel2 = new JPanel();
		totBalancePanel.add(emptyPanel2);
		totBalancePanel.add(balancePanel);
		/*
		 * 현재 분류를 reviseVo를 통해 불러와야하는부분
		 */
		category = new JLabel("+ 수입");
		category.setForeground(Color.BLUE);
		category.setFont(new Font("휴먼중간팸체", Font.BOLD, 20));
		JLabel categoryName = new JLabel("분류 : ");
		categoryName.setFont(new Font("휴먼중간팸체", Font.BOLD, 20));
		JPanel categoryPanel = new JPanel();
		categoryPanel.setLayout(new FlowLayout());
		categoryPanel.add(categoryName, BorderLayout.CENTER);
		categoryPanel.add(category, BorderLayout.CENTER);
		JPanel totCategoryPanel = new JPanel();
		totCategoryPanel.setLayout(new GridLayout(2, 1));
		JPanel emptyPanel3 = new JPanel();
		totCategoryPanel.add(emptyPanel3);
		totCategoryPanel.add(categoryPanel);
		/*
		 * 현재 카테고리를 reviseVo를 통해 불러와야하는부분
		 */
		detailCategory = new JLabel("간식");
		detailCategory.setFont(new Font("휴먼중간팸체", Font.BOLD, 20));
		JLabel detailCategoryName = new JLabel("카테고리 : ");
		detailCategoryName.setFont(new Font("휴먼중간팸체", Font.BOLD, 20));
		JPanel detailCategoryPanel = new JPanel();
		detailCategoryPanel.setLayout(new FlowLayout());
		detailCategoryPanel.add(detailCategoryName, BorderLayout.CENTER);
		detailCategoryPanel.add(detailCategory, BorderLayout.CENTER);
		JPanel totDetailCategoryPanel = new JPanel();
		totDetailCategoryPanel.setLayout(new GridLayout(2, 1));
		JPanel emptyPanel4 = new JPanel();
		totDetailCategoryPanel.add(emptyPanel4);
		totDetailCategoryPanel.add(detailCategoryPanel);
		/*
		 * 현재 내역을 reviseVo를 통해 불러와야하는부분
		 */
		useContent = new JLabel("떡볶이");
		useContent.setFont(new Font("휴먼중간팸체", Font.BOLD, 20));
		JLabel useContentName = new JLabel("내역 : ");
		useContentName.setFont(new Font("휴먼중간팸체", Font.BOLD, 20));
		JPanel useContentPanel = new JPanel();
		useContentPanel.setLayout(new FlowLayout());
		useContentPanel.add(useContentName, BorderLayout.CENTER);
		useContentPanel.add(useContent, BorderLayout.CENTER);
		JPanel totUseContentPanel = new JPanel();
		totUseContentPanel.setLayout(new GridLayout(2, 1));
		JPanel emptyPanel5 = new JPanel();
		totUseContentPanel.add(emptyPanel5);
		totUseContentPanel.add(useContentPanel);
		/*
		 * 수정, 삭제 버튼 리스너
		 */
		reviseBtn = new JButton("수정");
		reviseBtn.setFont(new Font("휴먼중간팸체", Font.BOLD, 20));
		reviseBtn.addActionListener(new HistoryViewModifyBtnListener());
		deleteBtn = new JButton("삭제");
		deleteBtn.setFont(new Font("휴먼중간팸체", Font.BOLD, 20));
		deleteBtn.addActionListener(new HistroyViewDelBtnListener());
		cancelBtn = new JButton("닫기");
		cancelBtn.setFont(new Font("휴먼중간팸체", Font.BOLD, 20));
		
		cancelBtn.addActionListener(new HistroyViewCloseBtnListener());
		//cancelBtn.addActionListener();
		JPanel btnPanel = new JPanel();
		btnPanel.setLayout(new FlowLayout());
		btnPanel.add(reviseBtn, BorderLayout.CENTER);
		btnPanel.add(deleteBtn, BorderLayout.CENTER);
		btnPanel.add(cancelBtn, BorderLayout.CENTER);
		JPanel totBtnPanel = new JPanel();
		totBtnPanel.setLayout(new GridLayout(2, 1));
		JPanel emptyPanel6 = new JPanel();
		totBtnPanel.add(emptyPanel6);
		totBtnPanel.add(btnPanel);
		
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));		
		panel.add(totdatePanel);
		panel.add(totBalancePanel);
		panel.add(totCategoryPanel);
		panel.add(totDetailCategoryPanel);
		panel.add(totUseContentPanel);
		panel.add(totBtnPanel);
		add(panel);

		super.setBackground(Color.WHITE);
		super.setResizable(false);
		super.setLocation(750, 550); // 위치
		super.setPreferredSize(new Dimension(500, 600)); // 크기cx000000000000000000
		// super.setDefaultCloseOperation(JDialog.EXIT_ON_CLOSE);
		super.pack();
		//super.setVisible(true);
	}
	public void setData(DayAccountUI dayAccountUI,GregorianCalendar calendar,CashBookDetailVO reviseVo,int index,int code,JPanel panel){
		this.dayAccountUI = dayAccountUI;
		this.calendar=calendar;
		this.reviseVo=reviseVo;
		this.index = index;
		this.code=code;
		this.panel = panel;
		
		today.setText(reviseVo.getRegisterDate()[0] + "년 "+ reviseVo.getRegisterDate()[1] + "월 "+ reviseVo.getRegisterDate()[2] + "일");
		balance.setText(reviseVo.getAmount());
		if(reviseVo.getRegisterCategory()==RegisterCategory.IMPORT){
			if(code==2)category.setText("수입");
			else if(code ==1)category.setText("+ 용돈 더하기");
			category.setForeground(Color.blue);
		}
		else if(reviseVo.getRegisterCategory()==RegisterCategory.EXPORT){
			if(code==2)category.setText("지출");
			else if(code ==1)category.setText("- 용돈 빼기");
			category.setForeground(Color.red);
		}
		else if(reviseVo.getRegisterCategory()==RegisterCategory.SAVE){
			if(code==2)category.setText("저축");
			else if(code ==1)category.setText("x 용돈 곱하기");
			category.setForeground(Color.green);
		}
		else if(reviseVo.getRegisterCategory()==RegisterCategory.DONATION){
			if(code==2)category.setText("기부");
			else if(code ==1)category.setText("÷ 용돈 나누기");
			category.setForeground(Color.yellow);
		}
		detailCategory.setText(reviseVo.getDetailCategory());
		useContent.setText(reviseVo.getContent());
		reviseBtn.setEnabled(true);
		deleteBtn.setEnabled(true);
	}
	/**
	 * 
	 * @author 동익
	 *  수정 버튼 클릭 Event Listener
	 */
	private class HistoryViewModifyBtnListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			String []reviseData = new String[10];
			reviseData[0] = balance.getText();	
			/*
			 * 카테고리 분류해서 보내자
			 */
			reviseData[1] = category.getText();
			reviseData[2] = detailCategory.getText();
			reviseData[3] = useContent.getText();
			reviseData[4] = Integer.toString(reviseVo.getRegisterDate()[0]);
			reviseData[5] = Integer.toString(reviseVo.getRegisterDate()[1]);
			reviseData[6] = Integer.toString(reviseVo.getRegisterDate()[2]);
			reviseData[7] = Integer.toString(reviseVo.getRegisterDate()[3]);
			reviseData[8] = Integer.toString(reviseVo.getRegisterDate()[4]);
			reviseData[9] = Integer.toString(reviseVo.getRegisterDate()[5]);
			System.out.println("DetailAccountUI-수정listener(일시정보):"+reviseData[7]+" "+reviseData[8]+" "+reviseData[9]);
			addMoneyUI.setData(calendar, code,reviseData,balance,category,detailCategory,useContent);
			addMoneyUI.setVisible(true);
		}
	}
	/**
	 * 
	 * @author 동익
	 *  삭제 버튼 클릭 Event Listener
	 */
	private class HistroyViewDelBtnListener implements ActionListener{
		private QuarterAdminManager totManager;
		@Override
		public void actionPerformed(ActionEvent e) {
			int []registerTime = {reviseVo.getRegisterDate()[3],reviseVo.getRegisterDate()[4],reviseVo.getRegisterDate()[5]};
			//분기정보
			int quarterNo = (int) Math.ceil( (calendar.get(Calendar.MONTH)+1) / 3.0 );
			
			ArrayList<MonthCashBookDetailVO>tempVo  = totManager.getTotManager()[quarterNo-1].getDetailInfoList().get("p1");
			System.out.println("DetailAccountUI-HistoryViewDelBtnLister(지울패널번호) : "+index);
			/*
			 * 패널좀 지우고 싶다...............
			 */
			//panel.remove(index);
			//panel.validate();
			
			//dayAccountUI.getPanel().remove(tempVo.get(calendar.get(Calendar.MONTH)+3 - 3*quarterNo).get(calendar.get(Calendar.DATE)).size()-1);
		
			
			if(totManager.getTotManager()[quarterNo-1].getDetailInfoList().get("p1").get(calendar.get(Calendar.MONTH)+3 - 3*quarterNo).remove(calendar.get(Calendar.DATE),registerTime )){
				 JOptionPane.showMessageDialog(null, reviseVo.getContent()+" 정보가 삭제되었습니다!");
				 dayAccountUI.panelUpdate(tempVo.get(calendar.get(Calendar.MONTH)+3 - 3*quarterNo).get(calendar.get(Calendar.DATE)));
			}
			/*
			 * 목록 업데이트
			 */
			dayAccountUI.panelUpdate(tempVo.get(calendar.get(Calendar.MONTH)+3 - 3*quarterNo).get(calendar.get(Calendar.DATE)));
			System.out.println("\n삭제할 등록코드 : "+code);
			System.out.println("삭제 날짜 : "+calendar.get(Calendar.YEAR) + "년 "+ (calendar.get(Calendar.MONTH) + 1) + "월 "+ calendar.get(Calendar.DATE) + "일");
			System.out.println("삭제할 reviseVo : "+reviseVo);
			balance.setText("");
			category.setText("");
			detailCategory.setText("");
			useContent.setText("");
			reviseBtn.setEnabled(false);
			deleteBtn.setEnabled(false);
			reviseVo = null;
		}
	}
	private class HistroyViewCloseBtnListener implements ActionListener{
		private QuarterAdminManager totManager;
		@Override
		public void actionPerformed(ActionEvent e) {
			setVisible(false);
			//DetailViewMouseBtnListener tempListener= new DetailViewMouseBtnListener();
			int quarterNo = (int) Math.ceil( (calendar.get(Calendar.MONTH)+1) / 3.0 );
			MonthCashBookDetailVO tempVo = totManager.getTotManager()[quarterNo-1].getDetailInfoList().get("p1").get(calendar.get(Calendar.MONTH)+3 - 3*quarterNo);
			LinkedList<CashBookDetailVO> tempList = tempVo.get(calendar.get(Calendar.DATE));
			for(int i =0; i < tempList.size();++i)
				System.out.println("DayAccountUI-closeListener : "+tempList.get(i));
			dayAccountUI.panelUpdate(tempList);
			MainUI.getGoalBar().setValue(
					(int)(Double.parseDouble(QuarterManager.getCurrentAmount())/
							totManager.getTotManager()[quarterNo-1].getSavingGoalList().get("P1").get(quarterNo).getGoalAmount()*100));
		
			/*panel.removeAll();
			for(int i=0;i<tempList.size();++i){
				JPanel tempPanel=new JPanel(new GridLayout(1,4));
				tempPanel.setBackground(Color.white);
				JLabel registerLabel = new JLabel(tempList.get(i).getRegisterCategory().value());
				registerLabel.setFont(new Font("맑은고딕",Font.BOLD,25));
				if(tempList.get(i).getRegisterCategory()==RegisterCategory.IMPORT)	
					registerLabel.setForeground(Color.blue);
				 else if(tempList.get(i).getRegisterCategory()==RegisterCategory.EXPORT)
					 registerLabel.setForeground(Color.red);
				 else if(tempList.get(i).getRegisterCategory()==RegisterCategory.SAVE)
					 registerLabel.setForeground(Color.green);
				 else if(tempList.get(i).getRegisterCategory()==RegisterCategory.DONATION)
					 registerLabel.setForeground(Color.yellow);
				tempPanel.add(registerLabel);
				JLabel detailCategory = new JLabel(tempList.get(i).getDetailCategory());
				detailCategory.setFont(new Font("맑은고딕",Font.BOLD,15));
				tempPanel.add(detailCategory);
				JLabel reviseVoLabel = new JLabel(Integer.toString(i));
				reviseVoLabel.setForeground(Color.white);
				reviseVoLabel.addMouseListener(tempListener);
				tempPanel.add(reviseVoLabel);
				JLabel amountLabel = new JLabel(tempList.get(i).getAmount());
				amountLabel.setFont(new Font("맑은고딕",Font.BOLD,15));
				tempPanel.add(amountLabel);
				panel.add(tempPanel);
				panel.setBackground(Color.white);			
			}
			getContentPane().validate();
			*/
		}
	}
	/**
	 * 
	 * @author 동익
	 *  상세보기를 위한 listener
	 */
	/*
	private class DetailViewMouseBtnListener extends MouseAdapter{
		@Override
		public void mouseClicked(MouseEvent e) {
			JLabel tempLabel = (JLabel)e.getSource();
			setData(calendar, reviseVo, code,panel);
			setVisible(true);
		}
	}*/
}
