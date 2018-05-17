package com.lectopia.chunbangsave.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.GregorianCalendar;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import net.sourceforge.jdatepicker.impl.JDatePanelImpl;
import net.sourceforge.jdatepicker.impl.JDatePickerImpl;
import net.sourceforge.jdatepicker.impl.UtilDateModel;

public class AddMoneyUI extends JDialog{
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
	private UtilDateModel dateModel;
	public AddMoneyUI(JDialog dialog,GregorianCalendar calendar, int code){
		super(dialog,true);
		this.code = code;
		setTitle("가계부 추가");
		//new CancelBtnListener(this,inputMoneyTf,inputContentTf);//취소 버튼 누르는 리스너 새성
		
		/*
		 * code 1일 경우 부모
		 */
		if(code==1){
			addTitleLabel = new JLabel("가계부 추가",SwingConstants.CENTER);
			addTitleLabel.setFont(new Font("굵은안상수체",Font.BOLD,40));
			String[] useMoneyList = {"선택해주세요!","수입","지출","저축","기부"};
			useMoneyCategory = new JComboBox(useMoneyList);
		}
		else{
			addTitleLabel = new JLabel("돈을 관리해요!",SwingConstants.CENTER);
			addTitleLabel.setFont(new Font("굵은안상수체",Font.BOLD,40));
			String[] useMoneyList = {"선택해주세요!","용돈더하기","용돈빼기","용돈곱하기","용돈나누기"};
			useMoneyCategory = new JComboBox(useMoneyList);
		}
		JPanel titlePanel = new JPanel();
		titlePanel.add(addTitleLabel);
		inputContentTf = new JTextArea(3,20);
		inputMoneyTf = new JTextField(13);
		addBtn = new JButton("추가");
		addBtn.addActionListener(new EditBtnListener());
		cancelBtn = new JButton("닫기");
		
		String[] notSelect = {"분류를 선택해주세요!"};
		detailUseMoneyCategory = new JComboBox(notSelect);
		//카테고리 리스너
		useMoneyCategory.addActionListener(new CategoryChangeListener());
		
		JLabel kindLabel = new JLabel("사용분류                              ");
		JPanel kindPanel = new JPanel();
		//kindPanel.setLayout(new BoxLayout(kindPanel, BoxLayout.X_AXIS));
		//kindPanel.setLayout(new GridLayout(1,2));
		kindPanel.setLayout(new FlowLayout());
		kindPanel.add(kindLabel);
		kindPanel.add(useMoneyCategory);
		
		JLabel detailKindLabel = new JLabel("상세분류           ");
		JPanel detailKindPanel = new JPanel();
		//detailKindPanel.setLayout(new GridLayout(1,2));
		detailKindLabel.setLayout(new FlowLayout());
		detailKindPanel.add(detailKindLabel);
		detailKindPanel.add(detailUseMoneyCategory);
		
/*달력*/		
		dateModel=new UtilDateModel();
		dateModel.setSelected(true);

		JDatePanelImpl datePanel = new JDatePanelImpl(dateModel);
		JDatePickerImpl datePicker = new JDatePickerImpl(datePanel);
		datePicker.addActionListener(new DateSelectListener());
/*달력*/	
		JLabel dateLabel = new JLabel("날짜");
		JPanel dateChoicePanel = new JPanel();
		//dateChoicePanel.setLayout(new GridLayout(1,2));
		dateChoicePanel.setLayout(new FlowLayout());
		dateChoicePanel.add(dateLabel);
		dateChoicePanel.add(datePicker);
		
		JLabel inputTextLabel = new JLabel("내역");
		JPanel inputTextPanel = new JPanel();
		//inputTextPanel.setLayout(new GridLayout(1,2));
		inputTextPanel.setLayout(new FlowLayout());
		inputTextPanel.add(inputTextLabel);
		inputTextPanel.add(inputContentTf);

		JLabel inputMoneyLabel = new JLabel("금액                    ");
		JLabel wonLabel = new JLabel("원");
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
		super.setLocation(750, 550); // 위치
		super.setPreferredSize(new Dimension(500, 700)); // 크기cx000000000000000000
		super.pack();
		//super.setVisible(true);
	}
	/**
	 * 
	 * @param dialog 이전 dialog
	 * @param calendar 날짜 정보
	 * @param code 등록코드
	 * @param reviseData 수정할 데이터
	 */
	public AddMoneyUI(JDialog dialog,GregorianCalendar calendar, int code,String[]reviseDatas){
		super(dialog,true);
		this.code = code;
		setTitle("가계부 수정");
		/*
		 * code 1일 경우 부모
		 */
		reviseData=reviseDatas;
		if(code==1){
			addTitleLabel = new JLabel("가계부 수정",SwingConstants.CENTER);
			addTitleLabel.setFont(new Font("굵은안상수체",Font.BOLD,40));
			String[]useMoneyList = new String[4];
			if(reviseData[1].equals("수입")){
				useMoneyList[0] = new String("수입");
				useMoneyList[1] = "지출";
				useMoneyList[2] = "저축";
				useMoneyList[3] = "기부";
			}
			else if(reviseData[1].equals("지출")){
				useMoneyList[0] = "지출";
				useMoneyList[1] = "수입";
				useMoneyList[2] = "저축";
				useMoneyList[3] = "기부";
			}
			else if(reviseData[1].equals("저축")){
				useMoneyList[0] = "저축";
				useMoneyList[1] = "수입";
				useMoneyList[2] = "지출";
				useMoneyList[3] = "기부";
			}
			else if(reviseData[1].equals("기부")){
				useMoneyList[0] = "기부";
				useMoneyList[1] = "수입";
				useMoneyList[2] = "지출";
				useMoneyList[3] = "저축";
			}
			useMoneyCategory = new JComboBox(useMoneyList);
		}
		else{
			addTitleLabel = new JLabel("돈을 관리해요!",SwingConstants.CENTER);
			addTitleLabel.setFont(new Font("굵은안상수체",Font.BOLD,40));
			String[] useMoneyList = {"선택해주세요!","용돈더하기","용돈빼기","용돈곱하기","용돈나누기"};
			useMoneyCategory = new JComboBox(useMoneyList);
		}
		JPanel titlePanel = new JPanel();
		titlePanel.add(addTitleLabel);
		inputContentTf = new JTextArea(3,20);
		inputMoneyTf = new JTextField(13);
		addBtn = new JButton("추가");
		addBtn.addActionListener(new EditBtnListener());
		cancelBtn = new JButton("닫기");
		
		String[] notSelect = {reviseData[2]};
		detailUseMoneyCategory = new JComboBox(notSelect);
		//카테고리 리스너
		useMoneyCategory.addActionListener(new CategoryChangeListener());
		
		JLabel kindLabel = new JLabel("사용분류                              ");
		JPanel kindPanel = new JPanel();
		//kindPanel.setLayout(new BoxLayout(kindPanel, BoxLayout.X_AXIS));
		//kindPanel.setLayout(new GridLayout(1,2));
		kindPanel.setLayout(new FlowLayout());
		kindPanel.add(kindLabel);
		kindPanel.add(useMoneyCategory);

		JLabel detailKindLabel = new JLabel("상세분류           ");
		JPanel detailKindPanel = new JPanel();
		//detailKindPanel.setLayout(new GridLayout(1,2));
		detailKindLabel.setLayout(new FlowLayout());
		detailKindPanel.add(detailKindLabel);
		detailKindPanel.add(detailUseMoneyCategory);
		
/*달력*/		
		dateModel=new UtilDateModel();
		dateModel.setSelected(true);

		JDatePanelImpl datePanel = new JDatePanelImpl(dateModel);
		JDatePickerImpl datePicker = new JDatePickerImpl(datePanel);
		datePicker.addActionListener(new DateSelectListener());
/*달력*/	
		JLabel dateLabel = new JLabel("날짜");
		JPanel dateChoicePanel = new JPanel();
		//dateChoicePanel.setLayout(new GridLayout(1,2));
		dateChoicePanel.setLayout(new FlowLayout());
		dateChoicePanel.add(dateLabel);
		datePicker.setEnabled(true);
		dateChoicePanel.add(datePicker);
		
		JLabel inputTextLabel = new JLabel("내역");
		JPanel inputTextPanel = new JPanel();
		//inputTextPanel.setLayout(new GridLayout(1,2));
		inputTextPanel.setLayout(new FlowLayout());
		inputTextPanel.add(inputTextLabel);
		inputTextPanel.add(inputContentTf);
		//수정할 데이터 삽입
		inputContentTf.setText(reviseData[3]);
		inputTextPanel.add(inputContentTf);
		
		JLabel inputMoneyLabel = new JLabel("금액                    ");
		JLabel wonLabel = new JLabel("원");
		JPanel inputMoneyPanel = new JPanel();
		//inputMoneyPanel.setLayout(new GridLayout(1,2));
		inputMoneyPanel.setLayout(new FlowLayout());
		inputMoneyPanel.add(inputMoneyLabel);	
		//수정할 데이터 삽입
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
		super.setLocation(800, 600); // 위치
		super.setPreferredSize(new Dimension(500, 700)); // 크기cx000000000000000000
		super.pack();
		//super.setVisible(true);
	}
	
	public void setData(GregorianCalendar calendar,int code){
		this.calendar=calendar;
		this.code=code;
	}
	public void setData(GregorianCalendar calendar,int code,String[] reviseData){
		this.calendar=calendar;
		this.code=code;
		this.reviseData=reviseData;
	}
	/**
	 * 
	 * @author 동익
	 *   자녀,부모별,카테고리별 다르게 해주는 listener
	 */
	private class CategoryChangeListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			String selectedSearchList = useMoneyCategory.getSelectedItem().toString();
			if(selectedSearchList.equals("수입")){
				detailUseMoneyCategory.removeAllItems();
				if(code==1){
					String[] detailUseMoneyList = {"-------선택해주세요!------","월급","보험금","매출","보너스","기타"};
					for(int i = 0; i < 3; ++i)
						detailUseMoneyCategory.addItem(detailUseMoneyList[i]);
				}
				else{
					String[] detailUseMoneyList = {"-------선택해주세요!------","용돈","기타"};
					for(int i = 0; i < 3; ++i)
						detailUseMoneyCategory.addItem(detailUseMoneyList[i]);					
				}
			}
			else if(selectedSearchList.equals("지출")){
				detailUseMoneyCategory.removeAllItems();
				if(code ==1){
					String[] detailUseMoneyList = {"-------선택해주세요!------","식비","보험","경조사","세금","카드대금","기타"};
					for(int i = 0; i < 6; ++i)
						detailUseMoneyCategory.addItem(detailUseMoneyList[i]);
				}
				else{	
					String[] detailUseMoneyList = {"-------선택해주세요!------","학용품","간식","취미","오락","기타"};
					for(int i = 0; i < 6; ++i)
						detailUseMoneyCategory.addItem(detailUseMoneyList[i]);
				}
			}
		}
	}
	/**
	 * 
	 * @author 동익
	 *  일지 추가화면에서 완료 버튼 클릭
	 */
	private class EditBtnListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			System.out.print("\n날짜 : ");
			System.out.print(dateModel.getYear()+"-");
			System.out.print(dateModel.getMonth()+1+"-");
			System.out.println(dateModel.getDay());
			System.out.println("사용분류 : "+useMoneyCategory.getSelectedItem().toString());
			System.out.println("상세분류 : "+detailUseMoneyCategory.getSelectedItem().toString());
			System.out.println("내역 : "+inputContentTf.getText());
			System.out.println("금액 : "+inputMoneyTf.getText());
		}
	}
	/**
	 * 
	 * @author 동익
	 *   datePicker 선택 Event Listener
	 */
	private class DateSelectListener implements ActionListener{
		public void actionPerformed(ActionEvent e){
			int year = dateModel.getYear();
			int month = dateModel.getMonth();
			int day=dateModel.getDay();

			System.out.println("넘어가는 데이터 code : "+code);
			System.out.println("넘어가는 데이터 year : "+year);
			System.out.println("넘어가는 데이터 month : "+month+"1월");
			System.out.println("넘어가는 데이터 day : "+day);
		}
	}
}
