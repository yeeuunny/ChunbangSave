package com.lectopia.chunbangsave.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.LinkedList;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import com.lectopia.chunbangsave.vo.CashBookDetailVO;
import com.lectopia.chunbangsave.vo.MonthCashBookDetailVO;
import com.lectopia.chunbangsave.vo.QuarterAdminManager;
import com.lectopia.chunbangsave.vo.QuarterManager;

public class AddMoneyUI extends JDialog{
	private DayAccountUI dayAccountUI;
	private JPanel panel;
	private int code;
	private JLabel addTitleLabel;
	private JComboBox useMoneyCategory;
	private JComboBox detailUseMoneyCategory;
	private JTextArea inputContentTf;
	private JTextField inputMoneyTf;
	private JButton addBtn;
	private JButton cancelBtn;
	
	private GregorianCalendar calendar;
	private String[] reviseData;
	private JLabel editDay;

	private JLabel balance;
	private JLabel category;
	private JLabel detailCategory;
	private JLabel useContent;
	
	public AddMoneyUI(JDialog dialog,GregorianCalendar calendar, int code){
		super(dialog,true);
		this.code = code;
		setTitle("����� �߰�");
		//new CancelBtnListener(this,inputMoneyTf,inputContentTf);//��� ��ư ������ ������ ����
		
		/*
		 * code 1�� ��� �ڳ�
		 */
		if(code==2){
			addTitleLabel = new JLabel("����� �߰�",SwingConstants.CENTER);
			addTitleLabel.setFont(new Font("�����Ȼ��ü",Font.BOLD,40));
			String[] useMoneyList = {"�������ּ���!","����","����","����","���"};
			useMoneyCategory = new JComboBox(useMoneyList);
		}
		else{
			addTitleLabel = new JLabel("���� �����ؿ�!",SwingConstants.CENTER);
			addTitleLabel.setFont(new Font("�����Ȼ��ü",Font.BOLD,40));
			String[] useMoneyList = {"�������ּ���!","���ϱ�","����","���ϱ�","������"};
			useMoneyCategory = new JComboBox(useMoneyList);
		}
		JPanel titlePanel = new JPanel();
		titlePanel.add(addTitleLabel);
		inputContentTf = new JTextArea(3,20);
		inputMoneyTf = new JTextField(13);
		addBtn = new JButton("�߰�");
		addBtn.addActionListener(new EditBtnListener());
		cancelBtn = new JButton("�ݱ�");
		
		String[] notSelect = {"�з��� �������ּ���!"};
		detailUseMoneyCategory = new JComboBox(notSelect);
		//ī�װ� ������
		useMoneyCategory.addActionListener(new CategoryChangeListener());
		
		JLabel kindLabel = new JLabel("���з�                              ");
		JPanel kindPanel = new JPanel();
		//kindPanel.setLayout(new BoxLayout(kindPanel, BoxLayout.X_AXIS));
		//kindPanel.setLayout(new GridLayout(1,2));
		kindPanel.setLayout(new FlowLayout());
		kindPanel.add(kindLabel);
		kindPanel.add(useMoneyCategory);
		
		JLabel detailKindLabel = new JLabel("�󼼺з�           ");
		JPanel detailKindPanel = new JPanel();
		//detailKindPanel.setLayout(new GridLayout(1,2));
		detailKindLabel.setLayout(new FlowLayout());
		detailKindPanel.add(detailKindLabel);
		detailKindPanel.add(detailUseMoneyCategory);
		
		
		editDay=new JLabel(Integer.toString(calendar.get(Calendar.YEAR))+"��"+
							Integer.toString(calendar.get(Calendar.MONTH)+1)+"��"+
								Integer.toString(calendar.get(Calendar.DATE))+"��");

		JLabel dateLabel = new JLabel("��¥");
		JPanel dateChoicePanel = new JPanel();
		//dateChoicePanel.setLayout(new GridLayout(1,2));
		dateChoicePanel.setLayout(new FlowLayout());
		dateChoicePanel.add(dateLabel);
		dateChoicePanel.add(editDay);
		
		JLabel inputTextLabel = new JLabel("����");
		JPanel inputTextPanel = new JPanel();
		//inputTextPanel.setLayout(new GridLayout(1,2));
		inputTextPanel.setLayout(new FlowLayout());
		inputTextPanel.add(inputTextLabel);
		inputTextPanel.add(inputContentTf);

		JLabel inputMoneyLabel = new JLabel("�ݾ�                    ");
		JLabel wonLabel = new JLabel("��");
		JPanel inputMoneyPanel = new JPanel();
		//inputMoneyPanel.setLayout(new GridLayout(1,2));
		inputMoneyPanel.setLayout(new FlowLayout());
		inputMoneyPanel.add(inputMoneyLabel);
		inputMoneyPanel.add(inputMoneyTf);
		inputMoneyPanel.add(wonLabel);
		
		JPanel totContentPanel = new JPanel();
		totContentPanel.setLayout(new BoxLayout(totContentPanel, BoxLayout.Y_AXIS));
		totContentPanel.add(dateChoicePanel);
		totContentPanel.add(kindPanel);
		totContentPanel.add(detailKindPanel);
		totContentPanel.add(inputTextPanel);
		totContentPanel.add(inputMoneyPanel);
		
		JPanel btnPanel = new JPanel();
		//btnPanel.setLayout(new BoxLayout(btnPanel, BoxLayout.X_AXIS));
		btnPanel.setLayout(new FlowLayout());
		btnPanel.setBackground(Color.white);
		btnPanel.add(addBtn,BorderLayout.CENTER);
		btnPanel.add(cancelBtn,BorderLayout.CENTER);
		JPanel totBtnPanel = new JPanel();
		JPanel emptyPanel = new JPanel();
		emptyPanel.setBackground(Color.white);
		totBtnPanel.setLayout(new GridLayout(2,1));
		totBtnPanel.add(emptyPanel);
		totBtnPanel.add(btnPanel);
		
		//setLayout(new BoxLayout(dialog, BoxLayout.Y_AXIS));
		setLayout(new GridLayout(7,1));
		add(titlePanel);
		add(dateChoicePanel);
		add(kindPanel);
		add(detailKindPanel);
		add(inputMoneyPanel);
		add(inputTextPanel);
		add(totBtnPanel);

		cancelBtn.addActionListener(new CancelBtnListener(this,inputMoneyTf,inputContentTf));
		
		super.setBackground(Color.WHITE);
		super.setResizable(false);
		super.setLocation(750, 550); // ��ġ
		super.setPreferredSize(new Dimension(500, 700)); // ũ��cx000000000000000000
		super.pack();
		//super.setVisible(true);
	}
	/**
	 * 
	 * @param dialog ���� dialog
	 * @param calendar ��¥ ����
	 * @param code ����ڵ�
	 * @param reviseData ������ ������
	 */
	public AddMoneyUI(JDialog dialog,GregorianCalendar calendar, int code,String[]reviseDatas){
		super(dialog,true);
		this.code = code;
		setTitle("����� ����");
		/*
		 * code 2�� ��� �θ�
		 */
		reviseData=reviseDatas;
		if(code==2){
			addTitleLabel = new JLabel("����� ����",SwingConstants.CENTER);
			addTitleLabel.setFont(new Font("�����Ȼ��ü",Font.BOLD,40));
			String[]useMoneyList = new String[4];
			if(reviseData[1].equals("����")){
				useMoneyList[0] = new String("����");
				useMoneyList[1] = "����";
				useMoneyList[2] = "����";
				useMoneyList[3] = "���";
			}
			else if(reviseData[1].equals("����")){
				useMoneyList[0] = "����";
				useMoneyList[1] = "����";
				useMoneyList[2] = "����";
				useMoneyList[3] = "���";
			}
			else if(reviseData[1].equals("����")){
				useMoneyList[0] = "����";
				useMoneyList[1] = "����";
				useMoneyList[2] = "����";
				useMoneyList[3] = "���";
			}
			else if(reviseData[1].equals("���")){
				useMoneyList[0] = "���";
				useMoneyList[1] = "����";
				useMoneyList[2] = "����";
				useMoneyList[3] = "����";
			}
			useMoneyCategory = new JComboBox(useMoneyList);
		}
		else{
			addTitleLabel = new JLabel("���� �����ؿ�!",SwingConstants.CENTER);
			addTitleLabel.setFont(new Font("�����Ȼ��ü",Font.BOLD,40));
			String[] useMoneyList = {"�������ּ���!","���ϱ�","����","���ϱ�","������"};
			if(reviseData[1].equals("���ϱ�")){
				useMoneyList[0] = new String("���ϱ�");
				useMoneyList[1] = "����";
				useMoneyList[2] = "���ϱ�";
				useMoneyList[3] = "������";
			}
			else if(reviseData[1].equals("����")){
				useMoneyList[0] = "����";
				useMoneyList[1] = "���ϱ�";
				useMoneyList[2] = "���ϱ�";
				useMoneyList[3] = "������";
			}
			else if(reviseData[1].equals("���ϱ�")){
				useMoneyList[0] = "���ϱ�";
				useMoneyList[1] = "���ϱ�";
				useMoneyList[2] = "����";
				useMoneyList[3] = "������";
			}
			else if(reviseData[1].equals("������")){
				useMoneyList[0] = "������";
				useMoneyList[1] = "���ϱ�";
				useMoneyList[2] = "����";
				useMoneyList[3] = "���ϱ�";
			}
			useMoneyCategory = new JComboBox(useMoneyList);
		}
		JPanel titlePanel = new JPanel();
		titlePanel.add(addTitleLabel);
		inputContentTf = new JTextArea(3,20);
		inputMoneyTf = new JTextField(13);
		addBtn = new JButton("����");
		addBtn.addActionListener(new EditBtnListener());
		cancelBtn = new JButton("�ݱ�");
		
		String[] notSelect = {reviseData[2]};
		detailUseMoneyCategory = new JComboBox(notSelect);
		//ī�װ� ������
		useMoneyCategory.addActionListener(new CategoryChangeListener());
		
		JLabel kindLabel = new JLabel("���з�                              ");
		JPanel kindPanel = new JPanel();
		//kindPanel.setLayout(new BoxLayout(kindPanel, BoxLayout.X_AXIS));
		//kindPanel.setLayout(new GridLayout(1,2));
		kindPanel.setLayout(new FlowLayout());
		kindPanel.add(kindLabel);
		kindPanel.add(useMoneyCategory);

		JLabel detailKindLabel = new JLabel("�󼼺з�           ");
		JPanel detailKindPanel = new JPanel();
		//detailKindPanel.setLayout(new GridLayout(1,2));
		detailKindLabel.setLayout(new FlowLayout());
		detailKindPanel.add(detailKindLabel);
		detailKindPanel.add(detailUseMoneyCategory);
		

		
		editDay=new JLabel(Integer.toString(calendar.get(Calendar.YEAR))+"��"+
							Integer.toString(calendar.get(Calendar.MONTH)+1)+"��"+
								Integer.toString(calendar.get(Calendar.DATE))+"��");

		JLabel dateLabel = new JLabel("��¥");
		JPanel dateChoicePanel = new JPanel();
		//dateChoicePanel.setLayout(new GridLayout(1,2));
		dateChoicePanel.setLayout(new FlowLayout());
		dateChoicePanel.add(dateLabel);
		dateChoicePanel.add(editDay);
		
		JLabel inputTextLabel = new JLabel("����");
		JPanel inputTextPanel = new JPanel();
		//inputTextPanel.setLayout(new GridLayout(1,2));
		inputTextPanel.setLayout(new FlowLayout());
		inputTextPanel.add(inputTextLabel);
		inputTextPanel.add(inputContentTf);
		//������ ������ ����
		inputContentTf.setText(reviseData[3]);
		inputTextPanel.add(inputContentTf);
		
		JLabel inputMoneyLabel = new JLabel("�ݾ�                    ");
		JLabel wonLabel = new JLabel("��");
		JPanel inputMoneyPanel = new JPanel();
		//inputMoneyPanel.setLayout(new GridLayout(1,2));
		inputMoneyPanel.setLayout(new FlowLayout());
		inputMoneyPanel.add(inputMoneyLabel);	
		//������ ������ ����
		inputMoneyTf.setText(reviseData[0]);
		inputMoneyPanel.add(inputMoneyTf);
		inputMoneyPanel.add(wonLabel);
		
		JPanel totContentPanel = new JPanel();
		totContentPanel.setLayout(new BoxLayout(totContentPanel, BoxLayout.Y_AXIS));
		totContentPanel.add(dateChoicePanel);
		totContentPanel.add(kindPanel);
		totContentPanel.add(detailKindPanel);
		totContentPanel.add(inputTextPanel);
		totContentPanel.add(inputMoneyPanel);
		
		JPanel btnPanel = new JPanel();
		//btnPanel.setLayout(new BoxLayout(btnPanel, BoxLayout.X_AXIS));
		btnPanel.setLayout(new FlowLayout());
		btnPanel.setBackground(Color.white);
		btnPanel.add(addBtn,BorderLayout.CENTER);
		btnPanel.add(cancelBtn,BorderLayout.CENTER);
		JPanel totBtnPanel = new JPanel();
		JPanel emptyPanel = new JPanel();
		emptyPanel.setBackground(Color.white);
		totBtnPanel.setLayout(new GridLayout(2,1));
		totBtnPanel.add(emptyPanel);
		totBtnPanel.add(btnPanel);
		
		//setLayout(new BoxLayout(dialog, BoxLayout.Y_AXIS));
		setLayout(new GridLayout(7,1));
		add(titlePanel);
		add(dateChoicePanel);
		add(kindPanel);
		add(detailKindPanel);
		add(inputTextPanel);
		add(inputMoneyPanel);
		add(totBtnPanel);

		
		super.setBackground(Color.WHITE);
		super.setResizable(false);
		super.setLocation(800, 600); // ��ġ
		super.setPreferredSize(new Dimension(500, 700)); // ũ��cx000000000000000000
		super.pack();
		//super.setVisible(true);
	}
	
	public void setData(GregorianCalendar calendar,int code,JPanel panel,DayAccountUI dayAccountUI){
		this.calendar=calendar;
		this.code=code;
		this.panel = panel;
		this.dayAccountUI = dayAccountUI;
	}
	public void setData(GregorianCalendar calendar,int code,String[] reviseData,JLabel balance,JLabel category,JLabel detailCategory,JLabel useContent){
		this.calendar=calendar;
		this.code=code;
		this.reviseData=reviseData;
		inputContentTf.setText(reviseData[3]);
		this.balance = balance;
		this.category = category;
		this.detailCategory = detailCategory;
		this.useContent = useContent;
	}
	/**
	 * 
	 * @author ����
	 *   �ڳ�,�θ�,ī�װ��� �ٸ��� ���ִ� listener
	 */
	private class CategoryChangeListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			String selectedSearchList = useMoneyCategory.getSelectedItem().toString();
			if(selectedSearchList.equals("����")||selectedSearchList.contains("���ϱ�")){
				detailUseMoneyCategory.removeAllItems();
				if(code==2){
					String[] detailUseMoneyList = {"-------�������ּ���!------","����","�����","����","���ʽ�","��Ÿ"};
					for(int i = 0; i < 6; ++i)
						detailUseMoneyCategory.addItem(detailUseMoneyList[i]);
				}
				else{
					String[] detailUseMoneyList = {"-------�������ּ���!------","�뵷","��Ÿ"};
					for(int i = 0; i < 3; ++i)
						detailUseMoneyCategory.addItem(detailUseMoneyList[i]);					
				}
			}
			else if(selectedSearchList.equals("����")||selectedSearchList.contains("����")){
				detailUseMoneyCategory.removeAllItems();
				if(code ==2){
					String[] detailUseMoneyList = {"-------�������ּ���!------","�ĺ�","����","������","����","ī����","��Ÿ"};
					for(int i = 0; i < 7; ++i)
						detailUseMoneyCategory.addItem(detailUseMoneyList[i]);
				}
				else{	
					String[] detailUseMoneyList = {"-------�������ּ���!------","�п�ǰ","����","���","����","��Ÿ"};
					for(int i = 0; i < 6; ++i)
						detailUseMoneyCategory.addItem(detailUseMoneyList[i]);
				}
			}
			else if(selectedSearchList.equals("����")||selectedSearchList.contains("���ϱ�")){
				detailUseMoneyCategory.removeAllItems();
				if(code ==2){
					String[] detailUseMoneyList = {"-------�������ּ���!------"};
						detailUseMoneyCategory.addItem(detailUseMoneyList[0]);
				}
				else{	
					String[] detailUseMoneyList = {"-------�������ּ���!------"};
						detailUseMoneyCategory.addItem(detailUseMoneyList[0]);
				}
			}
			else if(selectedSearchList.equals("���")||selectedSearchList.contains("������")){
				detailUseMoneyCategory.removeAllItems();
				if(code ==2){
					String[] detailUseMoneyList = {"-------�������ּ���!------"};
						detailUseMoneyCategory.addItem(detailUseMoneyList[0]);
				}
				else{	
					String[] detailUseMoneyList = {"-------�������ּ���!------"};
						detailUseMoneyCategory.addItem(detailUseMoneyList[0]);
				}
			}
		}
	}
	/**
	 * 
	 * @author ����
	 *  ���� �߰�ȭ�鿡�� �Ϸ� ��ư Ŭ��
	 */
	private class EditBtnListener implements ActionListener {
		private QuarterAdminManager totManager;
		@Override
		public void actionPerformed(ActionEvent e) {
			totManager = new QuarterAdminManager();
			/*
			 * ���� ���
			 */
			if(reviseData!=null){
				int[]registerDate = {Integer.parseInt(reviseData[4]),Integer.parseInt(reviseData[5]),
										Integer.parseInt(reviseData[6]),Integer.parseInt(reviseData[7]),Integer.parseInt(reviseData[8]),Integer.parseInt(reviseData[9])};
				int[]registerTime = {Integer.parseInt(reviseData[7]),Integer.parseInt(reviseData[8]),Integer.parseInt(reviseData[9])};
				//�б�����
				int quarterNo = (int) Math.ceil( (calendar.get(Calendar.MONTH)+1) / 3.0 );
				RegisterCategory tempCategorey = RegisterCategory.DONATION;
				if(useMoneyCategory.getSelectedItem().toString().contains("����")||useMoneyCategory.getSelectedItem().toString().contains("���ϱ�")){
					tempCategorey = RegisterCategory.IMPORT;
					/*
					 * ���� �� ���� �ܾ� ����
					 */
					//QuarterManager.setCurrentAmount(
					//		Integer.toString(Integer.parseInt(QuarterManager.getCurrentAmount())+Integer.parseInt(inputMoneyTf.getText())));
				}
				else if(useMoneyCategory.getSelectedItem().toString().contains("����")||useMoneyCategory.getSelectedItem().toString().contains("����")){
					tempCategorey = RegisterCategory.EXPORT;
					/*
					 * ���� �� ���� �ܾ� ����
					 */
					//QuarterManager.setCurrentAmount(
					//		Integer.toString(Integer.parseInt(QuarterManager.getCurrentAmount())-Integer.parseInt(inputMoneyTf.getText())));
					
				}
				else if(useMoneyCategory.getSelectedItem().toString().contains("����")||useMoneyCategory.getSelectedItem().toString().contains("���ϱ�")){
					/*
					 * ���� �� ���� �ܾ� ����
					 */
					//QuarterManager.setCurrentAmount(
					//		Integer.toString(Integer.parseInt(QuarterManager.getCurrentAmount())-Integer.parseInt(inputMoneyTf.getText())));			
					//tempCategorey = RegisterCategory.SAVE;
				}
				else 
					/*
					 * ��� �� ���� �ܾ� ����
					 */
					//QuarterManager.setCurrentAmount(
					//	Integer.toString(Integer.parseInt(QuarterManager.getCurrentAmount())-Integer.parseInt(inputMoneyTf.getText())));
				
				// ���� �б��� ��� �ڵ��� ���� �������� �Ѱ��� ���� ����
				if(QuarterAdminManager.getTotManager()[quarterNo-1]==null)
					System.out.println("AddMoney-Listener:"+quarterNo+"�б� ������ ����ս��ϴ�.");
				ArrayList<MonthCashBookDetailVO>tempVo  = totManager.getTotManager()[quarterNo-1].getDetailInfoList().get("p1");
				if(detailUseMoneyCategory.getSelectedItem().toString().contains("����")){
					//if(tempVo.get(calendar.get(Calendar.MONTH)+3 - 3*quarterNo).get(calendar.get(Calendar.DATE))))
			
					if(tempVo.get(calendar.get(Calendar.MONTH)+3 - 3*quarterNo).replace(Integer.parseInt(reviseData[6]), 
							registerTime, new CashBookDetailVO(registerDate, tempCategorey, "����", 
												inputMoneyTf.getText(), inputContentTf.getText()))){
						JOptionPane.showMessageDialog(null, tempCategorey.value()+" ������ �����Ǿ����ϴ�!");
						setVisible(false);
						
						balance.setText(inputMoneyTf.getText());
						category.setText(tempCategorey.value());
						detailCategory.setText("����");
						useContent.setText(inputContentTf.getText());					
					}
				}
				else
					if(tempVo.get(calendar.get(Calendar.MONTH)+3 - 3*quarterNo).replace(Integer.parseInt(reviseData[6]), 
							registerTime, new CashBookDetailVO(registerDate, tempCategorey, detailUseMoneyCategory.getSelectedItem().toString(), 
												inputMoneyTf.getText(), inputContentTf.getText()))){
					JOptionPane.showMessageDialog(null, detailUseMoneyCategory.getSelectedItem().toString()+" ������ �����Ǿ����ϴ�!");
					setVisible(false);
					
					balance.setText(inputMoneyTf.getText());
					category.setText(tempCategorey.value());
					detailCategory.setText(detailUseMoneyCategory.getSelectedItem().toString());
					useContent.setText(inputContentTf.getText());
				}
				LinkedList<CashBookDetailVO> tempList = tempVo.get(calendar.get(Calendar.MONTH)+3 - 3*quarterNo).get(calendar.get(Calendar.DATE));
				for(int i = 0 ; i < tempList.size();++i)
					System.out.println("DayAccountUI : "+tempList.get(i));
				//dayAccountUI.panelUpdate(tempVo.get(calendar.get(Calendar.MONTH)+3 - 3*quarterNo).get(calendar.get(Calendar.DATE)));
				reviseData = null;
				MainUI.getGoalBar().setValue(
						(int)(Double.parseDouble(QuarterManager.getCurrentAmount())/
								totManager.getTotManager()[quarterNo-1].getSavingGoalList().get("P1").get(quarterNo).getGoalAmount()*100));
			}
			/*
			 * �߰�
			 */
			else{
				int[]registerDate = {calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH),calendar.get(Calendar.DATE),
										calendar.get(Calendar.HOUR),calendar.get(Calendar.MINUTE),calendar.get(Calendar.SECOND)};
				//�б�����
				int quarterNo = (int) Math.ceil( (calendar.get(Calendar.MONTH)+1) / 3.0 );
				RegisterCategory tempCategorey = RegisterCategory.DONATION;
				if(useMoneyCategory.getSelectedItem().toString().contains("����")||useMoneyCategory.getSelectedItem().toString().contains("���ϱ�")){
					tempCategorey = RegisterCategory.IMPORT;
					/*
					 * ���� �� ���� �ܾ� ����
					 */
					//QuarterManager.setCurrentAmount(
					//		Integer.toString(Integer.parseInt(QuarterManager.getCurrentAmount())+Integer.parseInt(inputMoneyTf.getText())));
				}
				else if(useMoneyCategory.getSelectedItem().toString().contains("����")||useMoneyCategory.getSelectedItem().toString().contains("����")){
					tempCategorey = RegisterCategory.EXPORT;
					/*
					 * ���� �� ���� �ܾ� ����
					 */
					//QuarterManager.setCurrentAmount(
					//		Integer.toString(Integer.parseInt(QuarterManager.getCurrentAmount())-Integer.parseInt(inputMoneyTf.getText())));
					
				}
				else if(useMoneyCategory.getSelectedItem().toString().contains("����")||useMoneyCategory.getSelectedItem().toString().contains("���ϱ�")){
					/*
					 * ���� �� ���� �ܾ� ����
					 */
					//QuarterManager.setCurrentAmount(
					//		Integer.toString(Integer.parseInt(QuarterManager.getCurrentAmount())-Integer.parseInt(inputMoneyTf.getText())));			
					//tempCategorey = RegisterCategory.SAVE;
				}
				else 
					/*
					 * ��� �� ���� �ܾ� ����
					 */
					/*QuarterManager.setCurrentAmount(
						Integer.toString(Integer.parseInt(QuarterManager.getCurrentAmount())-Integer.parseInt(inputMoneyTf.getText())));
				MainUI.getGoalBar().setValue(
						(int)(Double.parseDouble(QuarterManager.getCurrentAmount())/
								totManager.getTotManager()[quarterNo-1].getSavingGoalList().get("P1").get(quarterNo).getGoalAmount()*100));
		*/
					// ���� �б��� ��� �ڵ��� ���� �������� �Ѱ��� ���� ����
				if(QuarterAdminManager.getTotManager()[quarterNo-1]==null)
					System.out.println("AddMoney-Listener:"+quarterNo+"�б� ������ ����ս��ϴ�.");
				//System.out.println("AddMoneyUI-Listener:"+(quarterNo-1) + " p1 " + (quarterNo*3 - (Integer.parseInt(reviseData[5]))));
				ArrayList<MonthCashBookDetailVO>tempVo  = totManager.getTotManager()[quarterNo-1].getDetailInfoList().get("p1");
				//	System.out.println("AddMoneyUI-Listener:"+tempVo.get(1).get(Integer.parseInt(reviseData[6])).get(0));
				if(tempVo.get(calendar.get(Calendar.MONTH)+3 - 3*quarterNo).put(calendar.get(Calendar.DATE), new CashBookDetailVO(registerDate, tempCategorey, useMoneyCategory.getSelectedItem().toString(), 
						inputMoneyTf.getText(), inputContentTf.getText()))){
					JOptionPane.showMessageDialog(null, tempCategorey.value()+"������ ���������� �߰��Ǿ����ϴ�!");
					setVisible(false);
					dayAccountUI.panelUpdate(tempVo.get(calendar.get(Calendar.MONTH)+3 - 3*quarterNo).get(calendar.get(Calendar.DATE)));
				}
			}
			
			//�߰� �� ���� �� â ����
			useMoneyCategory.setSelectedIndex(0);
			detailUseMoneyCategory.setSelectedIndex(0);
			inputContentTf.setText("");
			inputMoneyTf.setText("");

			System.out.print("\n��¥ : ");
			System.out.print(calendar.get(Calendar.YEAR)+"-");
			System.out.print(calendar.get(Calendar.MONTH)+"+1 -");
			System.out.println(calendar.get(Calendar.DATE));
			System.out.println("���з� : "+useMoneyCategory.getSelectedItem().toString());
			System.out.println("�󼼺з� : "+detailUseMoneyCategory.getSelectedItem().toString());
			System.out.println("���� : "+inputContentTf.getText());
			System.out.println("�ݾ� : "+inputMoneyTf.getText());
		}
	}
}
