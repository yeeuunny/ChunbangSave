package com.lectopia.chunbangsave.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.Set;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import com.lectopia.chunbangsave.vo.MonthPocketMoneyVO;
import com.lectopia.chunbangsave.vo.PocketMoneyVO;
import com.lectopia.chunbangsave.vo.QuarterAdminManager;

public class NeedAllowanceUI extends JDialog{
	private int code;
	private JLabel needTitleLabel;
	private JComboBox useMoneyCategory;
	private JTextArea inputContentArea;
	private JTextField inputMoneyTf;
	private JButton lookBtn;
	private JButton sendBtn;
	private JButton cancelBtn;
	private JLabel today;
	private GregorianCalendar calendar;
	private AllowanceListUI allowanceUI;
	
	
	public NeedAllowanceUI(JDialog dialog,int code){
		super(dialog,true);
		this.calendar = new GregorianCalendar();
		setTitle("용돈이 필요해요!");
		this.calendar = calendar;
		this.code = code;
		
		allowanceUI = new AllowanceListUI(this,calendar,this.code);
		allowanceUI.setVisible(false);
		
		needTitleLabel = new JLabel("엄마!아빠!",SwingConstants.CENTER);
		needTitleLabel.setFont(new Font("맑은고딕",Font.BOLD,30));
		JLabel needTitleLabel2 = new JLabel("용돈이 필요해요!",SwingConstants.CENTER);
		needTitleLabel2.setFont(new Font("맑은고딕",Font.BOLD,30));
		String[] useMoneyList = {"선택해주세요!","학용품","간식","취미","오락","기타"};
		useMoneyCategory = new JComboBox(useMoneyList);
		lookBtn = new JButton("답변보기");
		lookBtn.addActionListener(new ShowResponseBtnListener());
		sendBtn = new JButton("보내기");
		sendBtn.addActionListener(new AskAllowanceBtnListener());
		cancelBtn = new JButton("취소");
		
		//제목 panel
		JPanel temp2 = new JPanel();
		temp2.add(new JLabel(" "));			
		JPanel titlePanel1 = new JPanel();
		titlePanel1.setLayout(new GridLayout(3,1));
		titlePanel1.add(temp2);
		titlePanel1.add(needTitleLabel);
		titlePanel1.add(needTitleLabel2);
		//날짜 panel
		today = new JLabel(calendar.get(Calendar.YEAR) + "년 "
				+ (calendar.get(Calendar.MONTH) + 1) + "월 "
				+ calendar.get(Calendar.DATE) + "일");
		JLabel dayName = new JLabel("날짜 : ");
		JPanel datePanel = new JPanel();
		datePanel.setLayout(new FlowLayout());
		datePanel.add(dayName, BorderLayout.CENTER);
		datePanel.add(today, BorderLayout.CENTER);
		JPanel totdatePanel = new JPanel();
		JPanel emptyPanel = new JPanel();
		totdatePanel.setLayout(new GridLayout(2, 1));
		totdatePanel.add(emptyPanel);
		totdatePanel.add(datePanel);
		//카테고리
		String[] detailUseMoneyList = {"선택해주세요!","학용품","간식","취미","오락","기타"};
		useMoneyCategory = new JComboBox(detailUseMoneyList);
		JLabel kindLabel = new JLabel("여기에 쓸게요!                               ");
		JPanel kindPanel = new JPanel();
		kindPanel.setLayout(new FlowLayout());
		//kindPanel.setLayout(new GridLayout(1,2));
		kindPanel.add(kindLabel);
		kindPanel.add(useMoneyCategory);
		//금액
		JLabel inputMoneyLabel = new JLabel("이만큼 필요해요!               ");
		JPanel inputMoneyPanel = new JPanel();
		kindPanel.setLayout(new FlowLayout());
		
		inputMoneyPanel.add(inputMoneyLabel);
		inputMoneyTf = new JTextField(15);
		inputMoneyPanel.add(inputMoneyTf);
		//내역
		JLabel inputTextLabel = new JLabel("이렇게 쓸게요!");
		JPanel inputTextPanel = new JPanel();
		//inputTextPanel.setLayout(new BoxLayout(inputTextPanel, BoxLayout.Y_AXIS));
		//inputTextPanel.setLayout(new GridLayout(2,1));
		//inputTextPanel.setLayout(new BoxLayout(inputTextPanel,BoxLayout.Y_AXIS));
		//inputTextPanel.setLayout(new BorderLayout());
		
		inputTextPanel.add(inputTextLabel,BorderLayout.NORTH);
		inputContentArea = new JTextArea(5, 20);
		inputTextPanel.add(inputContentArea);
		
		//버튼
		JPanel btnPanel = new JPanel();
		//btnPanel.setLayout(new BoxLayout(btnPanel, BoxLayout.X_AXIS));
		btnPanel.setLayout(new FlowLayout());
		btnPanel.add(lookBtn,BorderLayout.CENTER);
		btnPanel.add(sendBtn,BorderLayout.CENTER);
		btnPanel.add(cancelBtn,BorderLayout.CENTER);
		JPanel totBtnPanel = new JPanel();
		JPanel emptyPanel2 = new JPanel();
		totBtnPanel.setLayout(new GridLayout(2,1));
		totBtnPanel.add(emptyPanel2);
		totBtnPanel.add(btnPanel);
		
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		panel.add(titlePanel1);
		panel.add(totdatePanel);
		panel.add(kindPanel);
		panel.add(inputMoneyPanel);
		panel.add(inputTextPanel);
		panel.add(btnPanel);
		
		cancelBtn.addActionListener(new CancelBtnListener(this,inputMoneyTf,inputContentArea));
		add(panel);
		
		super.setResizable(false);
		super.setLocation(750, 550); // 위치
		super.setPreferredSize(new Dimension(500, 600)); // 크기cx000000000000000000
		// super.setDefaultCloseOperation(JDialog.EXIT_ON_CLOSE);
		super.pack();
	//	super.setVisible(true);
	}
	/**
	 * 
	 * @author 동익
	 *   용돈 요청 답변 보기 버튼 Event Listener
	 */
	private class ShowResponseBtnListener implements ActionListener{//--------------------------부모코드를 가져와서 읽음.
		@Override
		public void actionPerformed(ActionEvent e) {
			
			MonthPocketMoneyVO vo=QuarterAdminManager.getTotManager()[calendar.get(Calendar.MONTH)/3].getPocketMoneyList().get("P1");
			
			//System.out.println(vo);
			String[][] data=new String[vo.getPocketInfos().size()][4];
			Set key=vo.getPocketInfos().keySet();
			Iterator iter=key.iterator();
			for(int i=0;i<data.length;++i){
				String str=(String)iter.next();
				data[i][0]=calendar.get(Calendar.YEAR)+"년"+(calendar.get(Calendar.MONTH)+1)+"달"+str;
				data[i][1]=vo.get(str).getIsReceived();
				data[i][2]=vo.get(str).getAmount();
				data[i][3]=vo.get(str).getContent();
			}
			//System.out.println(data);
			allowanceUI.setData(calendar, 1, data);
			allowanceUI.setVisible(true);
		}
	}
	/**
	 * 
	 * @author 동익
	 *    보내기 버튼 Event Listener
	 */
	private class AskAllowanceBtnListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {//-----------------------------------------------------
			
			PocketMoneyVO vo=new PocketMoneyVO(useMoneyCategory.getSelectedItem().toString(),inputMoneyTf.getText(),inputContentArea.getText(),"0");
			Integer day=calendar.get(Calendar.DATE);
			QuarterAdminManager.getTotManager()[calendar.get(Calendar.MONTH)/3].getPocketMoneyList().get("C1").put(day.toString(), vo);
			
			System.out.println("요청날짜 : "+ calendar.get(Calendar.YEAR) + "년 "
					+ (calendar.get(Calendar.MONTH) + 1) + "월 "
					+ calendar.get(Calendar.DATE) + "일");
			System.out.println("요청 카테고리 : "+useMoneyCategory.getSelectedItem().toString());
			System.out.println("요청 금액 : "+ inputMoneyTf.getText());
			System.out.println("요청 이유 : "+ inputContentArea.getText());
			
			JOptionPane.showMessageDialog(null, "용돈을 요청했어요!");
		}
	}
}
