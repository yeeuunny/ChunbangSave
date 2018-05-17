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
		setTitle("가계부 추가");
		//new CancelBtnListener(this,inputMoneyTf,inputContentTf);//취소 버튼 누르는 리스너 새성
		
		/*
		 * code 1일 경우 자녀
		 */
		if(code==2){
			addTitleLabel = new JLabel("가계부 추가",SwingConstants.CENTER);
			addTitleLabel.setFont(new Font("굵은안상수체",Font.BOLD,40));
			String[] useMoneyList = {"선택해주세요!","수입","지출","저축","기부"};
			useMoneyCategory = new JComboBox(useMoneyList);
		}
		else{
			addTitleLabel = new JLabel("돈을 관리해요!",SwingConstants.CENTER);
			addTitleLabel.setFont(new Font("굵은안상수체",Font.BOLD,40));
			String[] useMoneyList = {"선택해주세요!","더하기","빼기","곱하기","나누기"};
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
		
		
		editDay=new JLabel(Integer.toString(calendar.get(Calendar.YEAR))+"년"+
							Integer.toString(calendar.get(Calendar.MONTH)+1)+"월"+
								Integer.toString(calendar.get(Calendar.DATE))+"일");

		JLabel dateLabel = new JLabel("날짜");
		JPanel dateChoicePanel = new JPanel();
		//dateChoicePanel.setLayout(new GridLayout(1,2));
		dateChoicePanel.setLayout(new FlowLayout());
		dateChoicePanel.add(dateLabel);
		dateChoicePanel.add(editDay);
		
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
		 * code 2일 경우 부모
		 */
		reviseData=reviseDatas;
		if(code==2){
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
			String[] useMoneyList = {"선택해주세요!","더하기","빼기","곱하기","나누기"};
			if(reviseData[1].equals("더하기")){
				useMoneyList[0] = new String("더하기");
				useMoneyList[1] = "빼기";
				useMoneyList[2] = "곱하기";
				useMoneyList[3] = "나누기";
			}
			else if(reviseData[1].equals("빼기")){
				useMoneyList[0] = "빼기";
				useMoneyList[1] = "더하기";
				useMoneyList[2] = "곱하기";
				useMoneyList[3] = "나누기";
			}
			else if(reviseData[1].equals("곱하기")){
				useMoneyList[0] = "곱하기";
				useMoneyList[1] = "더하기";
				useMoneyList[2] = "빼기";
				useMoneyList[3] = "나누기";
			}
			else if(reviseData[1].equals("나누기")){
				useMoneyList[0] = "나누기";
				useMoneyList[1] = "더하기";
				useMoneyList[2] = "빼기";
				useMoneyList[3] = "곱하기";
			}
			useMoneyCategory = new JComboBox(useMoneyList);
		}
		JPanel titlePanel = new JPanel();
		titlePanel.add(addTitleLabel);
		inputContentTf = new JTextArea(3,20);
		inputMoneyTf = new JTextField(13);
		addBtn = new JButton("수정");
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
		

		
		editDay=new JLabel(Integer.toString(calendar.get(Calendar.YEAR))+"년"+
							Integer.toString(calendar.get(Calendar.MONTH)+1)+"월"+
								Integer.toString(calendar.get(Calendar.DATE))+"일");

		JLabel dateLabel = new JLabel("날짜");
		JPanel dateChoicePanel = new JPanel();
		//dateChoicePanel.setLayout(new GridLayout(1,2));
		dateChoicePanel.setLayout(new FlowLayout());
		dateChoicePanel.add(dateLabel);
		dateChoicePanel.add(editDay);
		
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
	 * @author 동익
	 *   자녀,부모별,카테고리별 다르게 해주는 listener
	 */
	private class CategoryChangeListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			String selectedSearchList = useMoneyCategory.getSelectedItem().toString();
			if(selectedSearchList.equals("수입")||selectedSearchList.contains("더하기")){
				detailUseMoneyCategory.removeAllItems();
				if(code==2){
					String[] detailUseMoneyList = {"-------선택해주세요!------","월급","보험금","매출","보너스","기타"};
					for(int i = 0; i < 6; ++i)
						detailUseMoneyCategory.addItem(detailUseMoneyList[i]);
				}
				else{
					String[] detailUseMoneyList = {"-------선택해주세요!------","용돈","기타"};
					for(int i = 0; i < 3; ++i)
						detailUseMoneyCategory.addItem(detailUseMoneyList[i]);					
				}
			}
			else if(selectedSearchList.equals("지출")||selectedSearchList.contains("빼기")){
				detailUseMoneyCategory.removeAllItems();
				if(code ==2){
					String[] detailUseMoneyList = {"-------선택해주세요!------","식비","보험","경조사","세금","카드대금","기타"};
					for(int i = 0; i < 7; ++i)
						detailUseMoneyCategory.addItem(detailUseMoneyList[i]);
				}
				else{	
					String[] detailUseMoneyList = {"-------선택해주세요!------","학용품","간식","취미","오락","기타"};
					for(int i = 0; i < 6; ++i)
						detailUseMoneyCategory.addItem(detailUseMoneyList[i]);
				}
			}
			else if(selectedSearchList.equals("저축")||selectedSearchList.contains("곱하기")){
				detailUseMoneyCategory.removeAllItems();
				if(code ==2){
					String[] detailUseMoneyList = {"-------선택해주세요!------"};
						detailUseMoneyCategory.addItem(detailUseMoneyList[0]);
				}
				else{	
					String[] detailUseMoneyList = {"-------선택해주세요!------"};
						detailUseMoneyCategory.addItem(detailUseMoneyList[0]);
				}
			}
			else if(selectedSearchList.equals("기부")||selectedSearchList.contains("나누기")){
				detailUseMoneyCategory.removeAllItems();
				if(code ==2){
					String[] detailUseMoneyList = {"-------선택해주세요!------"};
						detailUseMoneyCategory.addItem(detailUseMoneyList[0]);
				}
				else{	
					String[] detailUseMoneyList = {"-------선택해주세요!------"};
						detailUseMoneyCategory.addItem(detailUseMoneyList[0]);
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
		private QuarterAdminManager totManager;
		@Override
		public void actionPerformed(ActionEvent e) {
			totManager = new QuarterAdminManager();
			/*
			 * 수정 경우
			 */
			if(reviseData!=null){
				int[]registerDate = {Integer.parseInt(reviseData[4]),Integer.parseInt(reviseData[5]),
										Integer.parseInt(reviseData[6]),Integer.parseInt(reviseData[7]),Integer.parseInt(reviseData[8]),Integer.parseInt(reviseData[9])};
				int[]registerTime = {Integer.parseInt(reviseData[7]),Integer.parseInt(reviseData[8]),Integer.parseInt(reviseData[9])};
				//분기정보
				int quarterNo = (int) Math.ceil( (calendar.get(Calendar.MONTH)+1) / 3.0 );
				RegisterCategory tempCategorey = RegisterCategory.DONATION;
				if(useMoneyCategory.getSelectedItem().toString().contains("수입")||useMoneyCategory.getSelectedItem().toString().contains("더하기")){
					tempCategorey = RegisterCategory.IMPORT;
					/*
					 * 수입 시 현재 잔액 증가
					 */
					//QuarterManager.setCurrentAmount(
					//		Integer.toString(Integer.parseInt(QuarterManager.getCurrentAmount())+Integer.parseInt(inputMoneyTf.getText())));
				}
				else if(useMoneyCategory.getSelectedItem().toString().contains("지출")||useMoneyCategory.getSelectedItem().toString().contains("빼기")){
					tempCategorey = RegisterCategory.EXPORT;
					/*
					 * 지출 시 현재 잔액 감소
					 */
					//QuarterManager.setCurrentAmount(
					//		Integer.toString(Integer.parseInt(QuarterManager.getCurrentAmount())-Integer.parseInt(inputMoneyTf.getText())));
					
				}
				else if(useMoneyCategory.getSelectedItem().toString().contains("저축")||useMoneyCategory.getSelectedItem().toString().contains("곱하기")){
					/*
					 * 저축 시 현재 잔액 감소
					 */
					//QuarterManager.setCurrentAmount(
					//		Integer.toString(Integer.parseInt(QuarterManager.getCurrentAmount())-Integer.parseInt(inputMoneyTf.getText())));			
					//tempCategorey = RegisterCategory.SAVE;
				}
				else 
					/*
					 * 기부 시 현재 잔액 감소
					 */
					//QuarterManager.setCurrentAmount(
					//	Integer.toString(Integer.parseInt(QuarterManager.getCurrentAmount())-Integer.parseInt(inputMoneyTf.getText())));
				
				// 현재 분기의 등록 코드의 월의 일정보의 한가지 정보 수정
				if(QuarterAdminManager.getTotManager()[quarterNo-1]==null)
					System.out.println("AddMoney-Listener:"+quarterNo+"분기 정보가 비어잇습니다.");
				ArrayList<MonthCashBookDetailVO>tempVo  = totManager.getTotManager()[quarterNo-1].getDetailInfoList().get("p1");
				if(detailUseMoneyCategory.getSelectedItem().toString().contains("선택")){
					//if(tempVo.get(calendar.get(Calendar.MONTH)+3 - 3*quarterNo).get(calendar.get(Calendar.DATE))))
			
					if(tempVo.get(calendar.get(Calendar.MONTH)+3 - 3*quarterNo).replace(Integer.parseInt(reviseData[6]), 
							registerTime, new CashBookDetailVO(registerDate, tempCategorey, "없음", 
												inputMoneyTf.getText(), inputContentTf.getText()))){
						JOptionPane.showMessageDialog(null, tempCategorey.value()+" 정보로 수정되었습니다!");
						setVisible(false);
						
						balance.setText(inputMoneyTf.getText());
						category.setText(tempCategorey.value());
						detailCategory.setText("없음");
						useContent.setText(inputContentTf.getText());					
					}
				}
				else
					if(tempVo.get(calendar.get(Calendar.MONTH)+3 - 3*quarterNo).replace(Integer.parseInt(reviseData[6]), 
							registerTime, new CashBookDetailVO(registerDate, tempCategorey, detailUseMoneyCategory.getSelectedItem().toString(), 
												inputMoneyTf.getText(), inputContentTf.getText()))){
					JOptionPane.showMessageDialog(null, detailUseMoneyCategory.getSelectedItem().toString()+" 정보로 수정되었습니다!");
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
			 * 추가
			 */
			else{
				int[]registerDate = {calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH),calendar.get(Calendar.DATE),
										calendar.get(Calendar.HOUR),calendar.get(Calendar.MINUTE),calendar.get(Calendar.SECOND)};
				//분기정보
				int quarterNo = (int) Math.ceil( (calendar.get(Calendar.MONTH)+1) / 3.0 );
				RegisterCategory tempCategorey = RegisterCategory.DONATION;
				if(useMoneyCategory.getSelectedItem().toString().contains("수입")||useMoneyCategory.getSelectedItem().toString().contains("더하기")){
					tempCategorey = RegisterCategory.IMPORT;
					/*
					 * 수입 시 현재 잔액 증가
					 */
					//QuarterManager.setCurrentAmount(
					//		Integer.toString(Integer.parseInt(QuarterManager.getCurrentAmount())+Integer.parseInt(inputMoneyTf.getText())));
				}
				else if(useMoneyCategory.getSelectedItem().toString().contains("지출")||useMoneyCategory.getSelectedItem().toString().contains("빼기")){
					tempCategorey = RegisterCategory.EXPORT;
					/*
					 * 지출 시 현재 잔액 감소
					 */
					//QuarterManager.setCurrentAmount(
					//		Integer.toString(Integer.parseInt(QuarterManager.getCurrentAmount())-Integer.parseInt(inputMoneyTf.getText())));
					
				}
				else if(useMoneyCategory.getSelectedItem().toString().contains("저축")||useMoneyCategory.getSelectedItem().toString().contains("곱하기")){
					/*
					 * 저축 시 현재 잔액 감소
					 */
					//QuarterManager.setCurrentAmount(
					//		Integer.toString(Integer.parseInt(QuarterManager.getCurrentAmount())-Integer.parseInt(inputMoneyTf.getText())));			
					//tempCategorey = RegisterCategory.SAVE;
				}
				else 
					/*
					 * 기부 시 현재 잔액 감소
					 */
					/*QuarterManager.setCurrentAmount(
						Integer.toString(Integer.parseInt(QuarterManager.getCurrentAmount())-Integer.parseInt(inputMoneyTf.getText())));
				MainUI.getGoalBar().setValue(
						(int)(Double.parseDouble(QuarterManager.getCurrentAmount())/
								totManager.getTotManager()[quarterNo-1].getSavingGoalList().get("P1").get(quarterNo).getGoalAmount()*100));
		*/
					// 현재 분기의 등록 코드의 월의 일정보의 한가지 정보 수정
				if(QuarterAdminManager.getTotManager()[quarterNo-1]==null)
					System.out.println("AddMoney-Listener:"+quarterNo+"분기 정보가 비어잇습니다.");
				//System.out.println("AddMoneyUI-Listener:"+(quarterNo-1) + " p1 " + (quarterNo*3 - (Integer.parseInt(reviseData[5]))));
				ArrayList<MonthCashBookDetailVO>tempVo  = totManager.getTotManager()[quarterNo-1].getDetailInfoList().get("p1");
				//	System.out.println("AddMoneyUI-Listener:"+tempVo.get(1).get(Integer.parseInt(reviseData[6])).get(0));
				if(tempVo.get(calendar.get(Calendar.MONTH)+3 - 3*quarterNo).put(calendar.get(Calendar.DATE), new CashBookDetailVO(registerDate, tempCategorey, useMoneyCategory.getSelectedItem().toString(), 
						inputMoneyTf.getText(), inputContentTf.getText()))){
					JOptionPane.showMessageDialog(null, tempCategorey.value()+"정보가 정상적으로 추가되었습니다!");
					setVisible(false);
					dayAccountUI.panelUpdate(tempVo.get(calendar.get(Calendar.MONTH)+3 - 3*quarterNo).get(calendar.get(Calendar.DATE)));
				}
			}
			
			//추가 및 수정 후 창 비우기
			useMoneyCategory.setSelectedIndex(0);
			detailUseMoneyCategory.setSelectedIndex(0);
			inputContentTf.setText("");
			inputMoneyTf.setText("");

			System.out.print("\n날짜 : ");
			System.out.print(calendar.get(Calendar.YEAR)+"-");
			System.out.print(calendar.get(Calendar.MONTH)+"+1 -");
			System.out.println(calendar.get(Calendar.DATE));
			System.out.println("사용분류 : "+useMoneyCategory.getSelectedItem().toString());
			System.out.println("상세분류 : "+detailUseMoneyCategory.getSelectedItem().toString());
			System.out.println("내역 : "+inputContentTf.getText());
			System.out.println("금액 : "+inputMoneyTf.getText());
		}
	}
}
