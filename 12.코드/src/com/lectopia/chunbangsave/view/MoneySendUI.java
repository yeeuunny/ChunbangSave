package com.lectopia.chunbangsave.view;

import java.awt.BorderLayout;
import java.awt.Container;
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
import javax.swing.JTextArea;
import javax.swing.JTextField;

import com.lectopia.chunbangsave.vo.PocketMoneyVO;
import com.lectopia.chunbangsave.vo.QuarterAdminManager;

public class MoneySendUI extends JDialog{
	private JTextField moneyText;
	private JTextArea memoArea;//사용자가 입력하는 부분을 필드멤버로 변혼 
	
	private int code;
	private JButton acBtn;
	private JButton rfBtn;
	private GregorianCalendar cal;
	public MoneySendUI(JDialog frame,int code,JButton acBtn,JButton rfBtn){//------------------------
		super(frame,true);
		setTitle("자녀와 대화하기");
		Container contentPane=getContentPane();
		this.code=code;
		cal=new GregorianCalendar();
		System.out.println("자녀등록코드 전달받음 : "+code);
		
		this.acBtn=acBtn;//---------------------
		this.rfBtn=rfBtn;
		
		JLabel title=new JLabel("용돈 보내기");
		
		title.setFont(new Font("돋움",Font.PLAIN,30));
		JPanel top=new JPanel(new FlowLayout(FlowLayout.CENTER,0,30));
		top.add(title);
		
		JLabel money=new JLabel("금액");
		moneyText=new JTextField(20);
		JPanel moneyPanel=new JPanel(new FlowLayout());
		moneyPanel.add(money);
		moneyPanel.add(moneyText);
		JLabel memo=new JLabel("메모");
		JPanel memoSet=new JPanel(new GridLayout(8,1));
		memoSet.add(memo);
		memoArea=new JTextArea(8,20);
		JPanel memoPanel=new JPanel(new FlowLayout());
		memoPanel.add(memoSet);
		memoPanel.add(memoArea);
		JPanel mid=new JPanel();
		mid.setLayout(new BoxLayout(mid,BoxLayout.Y_AXIS));
		mid.add(moneyPanel);
		mid.add(memoPanel);
		
		JButton send=new JButton("보내기");
		JButton cancel=new JButton("취소");
		JPanel but=new JPanel(new FlowLayout());
		but.add(send);
		but.add(cancel);
		
		send.addActionListener(new SendMoneyListener());
		cancel.addActionListener(new CancelBtnListener(this,moneyText,memoArea));
		
		
		contentPane.add(top,BorderLayout.NORTH);
		contentPane.add(mid);
		contentPane.add(but,BorderLayout.SOUTH);
		
		setSize(500,400);
		setLocation(500,400);
	}
	private class SendMoneyListener implements ActionListener{//------------------------------------------------------
		public void actionPerformed(ActionEvent arg0) {
			int month=cal.get(Calendar.MONTH);
			Integer day=cal.get(Calendar.DATE);
			PocketMoneyVO childVo=QuarterAdminManager.getTotManager()[month/3].getPocketMoneyList().get("P1").get(day.toString());//엄마에게 온 메시지의 날짜 확인 .
			System.out.println(childVo);
			PocketMoneyVO parentVo=new PocketMoneyVO(childVo.getCategory(),childVo.getAmount(),childVo.getContent(),childVo.getIsReceived());//자녀에게 메시지 남긴걸 자녀 VO에 저장.
			if(QuarterAdminManager.getTotManager()[month/3].getPocketMoneyList().get("C1").get(day.toString())==null){//자녀 날짜 VO가 NULL이라면
				QuarterAdminManager.getTotManager()[month/3].getPocketMoneyList().get("C1").put(day.toString(), parentVo);//자녀 날짜에 엄마가쓴 VO를 PUT
			}
			
			childVo.setAmount(moneyText.getText());
			childVo.setIsReceived("1");//자녀에게 메시지 수락 메시지 줫으니 1로 상태변환
			
			parentVo.setAmount(moneyText.getText());
			parentVo.setContent(memoArea.getText());
			
			acBtn.setEnabled(false);
			rfBtn.setEnabled(false);
			
			System.out.println("자녀등록 코드 : "+code);
			System.out.println("보내는 금액 : "+moneyText.getText());
			System.out.println("승인 이유 : "+memoArea.getText());
			setVisible(false);
		}
		
	}
	
}
