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
		String[] reviseData=new String[]{"100","����","-----�������ּ���----","���ض�"};
		
		addMoneyUI=new AddMoneyUI(DetailAccountUI.this,calendar,code,reviseData);
		addMoneyUI.setVisible(false);
		
		JPanel panel = new JPanel();
		TitledBorder title = new TitledBorder("������");
		title.setTitleFont(new Font("�޸��߰���ü", Font.BOLD, 30));
		panel.setBorder(title);

		today = new JLabel(calendar.get(Calendar.YEAR) + "�� "
				+ (calendar.get(Calendar.MONTH) + 1) + "�� "
				+ calendar.get(Calendar.DATE) + "��");
		today.setFont(new Font("�޸��߰���ü", Font.BOLD, 20));
		JLabel dayName = new JLabel("��¥ : ");
		dayName.setFont(new Font("�޸��߰���ü", Font.BOLD, 20));
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
		 * ���� �ܾ��� reviseVo�� ���� �ҷ��;��ϴºκ�
		 */
		balance = new JLabel(Integer.toString(1000));
		balance.setFont(new Font("�޸��߰���ü", Font.BOLD, 20));
		JLabel balanceName = new JLabel("�ݾ� : ");
		balanceName.setFont(new Font("�޸��߰���ü", Font.BOLD, 20));
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
		 * ���� �з��� reviseVo�� ���� �ҷ��;��ϴºκ�
		 */
		category = new JLabel("+ ����");
		category.setForeground(Color.BLUE);
		category.setFont(new Font("�޸��߰���ü", Font.BOLD, 20));
		JLabel categoryName = new JLabel("�з� : ");
		categoryName.setFont(new Font("�޸��߰���ü", Font.BOLD, 20));
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
		 * ���� ī�װ��� reviseVo�� ���� �ҷ��;��ϴºκ�
		 */
		detailCategory = new JLabel("����");
		detailCategory.setFont(new Font("�޸��߰���ü", Font.BOLD, 20));
		JLabel detailCategoryName = new JLabel("ī�װ� : ");
		detailCategoryName.setFont(new Font("�޸��߰���ü", Font.BOLD, 20));
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
		 * ���� ������ reviseVo�� ���� �ҷ��;��ϴºκ�
		 */
		useContent = new JLabel("������");
		useContent.setFont(new Font("�޸��߰���ü", Font.BOLD, 20));
		JLabel useContentName = new JLabel("���� : ");
		useContentName.setFont(new Font("�޸��߰���ü", Font.BOLD, 20));
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
		 * ����, ���� ��ư ������
		 */
		reviseBtn = new JButton("����");
		reviseBtn.setFont(new Font("�޸��߰���ü", Font.BOLD, 20));
		reviseBtn.addActionListener(new HistoryViewModifyBtnListener());
		deleteBtn = new JButton("����");
		deleteBtn.setFont(new Font("�޸��߰���ü", Font.BOLD, 20));
		deleteBtn.addActionListener(new HistroyViewDelBtnListener());
		cancelBtn = new JButton("�ݱ�");
		cancelBtn.setFont(new Font("�޸��߰���ü", Font.BOLD, 20));
		
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
		super.setLocation(750, 550); // ��ġ
		super.setPreferredSize(new Dimension(500, 600)); // ũ��cx000000000000000000
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
		
		today.setText(reviseVo.getRegisterDate()[0] + "�� "+ reviseVo.getRegisterDate()[1] + "�� "+ reviseVo.getRegisterDate()[2] + "��");
		balance.setText(reviseVo.getAmount());
		if(reviseVo.getRegisterCategory()==RegisterCategory.IMPORT){
			if(code==2)category.setText("����");
			else if(code ==1)category.setText("+ �뵷 ���ϱ�");
			category.setForeground(Color.blue);
		}
		else if(reviseVo.getRegisterCategory()==RegisterCategory.EXPORT){
			if(code==2)category.setText("����");
			else if(code ==1)category.setText("- �뵷 ����");
			category.setForeground(Color.red);
		}
		else if(reviseVo.getRegisterCategory()==RegisterCategory.SAVE){
			if(code==2)category.setText("����");
			else if(code ==1)category.setText("x �뵷 ���ϱ�");
			category.setForeground(Color.green);
		}
		else if(reviseVo.getRegisterCategory()==RegisterCategory.DONATION){
			if(code==2)category.setText("���");
			else if(code ==1)category.setText("�� �뵷 ������");
			category.setForeground(Color.yellow);
		}
		detailCategory.setText(reviseVo.getDetailCategory());
		useContent.setText(reviseVo.getContent());
		reviseBtn.setEnabled(true);
		deleteBtn.setEnabled(true);
	}
	/**
	 * 
	 * @author ����
	 *  ���� ��ư Ŭ�� Event Listener
	 */
	private class HistoryViewModifyBtnListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			String []reviseData = new String[10];
			reviseData[0] = balance.getText();	
			/*
			 * ī�װ� �з��ؼ� ������
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
			System.out.println("DetailAccountUI-����listener(�Ͻ�����):"+reviseData[7]+" "+reviseData[8]+" "+reviseData[9]);
			addMoneyUI.setData(calendar, code,reviseData,balance,category,detailCategory,useContent);
			addMoneyUI.setVisible(true);
		}
	}
	/**
	 * 
	 * @author ����
	 *  ���� ��ư Ŭ�� Event Listener
	 */
	private class HistroyViewDelBtnListener implements ActionListener{
		private QuarterAdminManager totManager;
		@Override
		public void actionPerformed(ActionEvent e) {
			int []registerTime = {reviseVo.getRegisterDate()[3],reviseVo.getRegisterDate()[4],reviseVo.getRegisterDate()[5]};
			//�б�����
			int quarterNo = (int) Math.ceil( (calendar.get(Calendar.MONTH)+1) / 3.0 );
			
			ArrayList<MonthCashBookDetailVO>tempVo  = totManager.getTotManager()[quarterNo-1].getDetailInfoList().get("p1");
			System.out.println("DetailAccountUI-HistoryViewDelBtnLister(�����гι�ȣ) : "+index);
			/*
			 * �г��� ����� �ʹ�...............
			 */
			//panel.remove(index);
			//panel.validate();
			
			//dayAccountUI.getPanel().remove(tempVo.get(calendar.get(Calendar.MONTH)+3 - 3*quarterNo).get(calendar.get(Calendar.DATE)).size()-1);
		
			
			if(totManager.getTotManager()[quarterNo-1].getDetailInfoList().get("p1").get(calendar.get(Calendar.MONTH)+3 - 3*quarterNo).remove(calendar.get(Calendar.DATE),registerTime )){
				 JOptionPane.showMessageDialog(null, reviseVo.getContent()+" ������ �����Ǿ����ϴ�!");
				 dayAccountUI.panelUpdate(tempVo.get(calendar.get(Calendar.MONTH)+3 - 3*quarterNo).get(calendar.get(Calendar.DATE)));
			}
			/*
			 * ��� ������Ʈ
			 */
			dayAccountUI.panelUpdate(tempVo.get(calendar.get(Calendar.MONTH)+3 - 3*quarterNo).get(calendar.get(Calendar.DATE)));
			System.out.println("\n������ ����ڵ� : "+code);
			System.out.println("���� ��¥ : "+calendar.get(Calendar.YEAR) + "�� "+ (calendar.get(Calendar.MONTH) + 1) + "�� "+ calendar.get(Calendar.DATE) + "��");
			System.out.println("������ reviseVo : "+reviseVo);
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
				registerLabel.setFont(new Font("�������",Font.BOLD,25));
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
				detailCategory.setFont(new Font("�������",Font.BOLD,15));
				tempPanel.add(detailCategory);
				JLabel reviseVoLabel = new JLabel(Integer.toString(i));
				reviseVoLabel.setForeground(Color.white);
				reviseVoLabel.addMouseListener(tempListener);
				tempPanel.add(reviseVoLabel);
				JLabel amountLabel = new JLabel(tempList.get(i).getAmount());
				amountLabel.setFont(new Font("�������",Font.BOLD,15));
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
	 * @author ����
	 *  �󼼺��⸦ ���� listener
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
