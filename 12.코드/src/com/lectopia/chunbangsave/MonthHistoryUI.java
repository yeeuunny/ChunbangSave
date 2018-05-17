package com.lectopia.chunbangsave.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

public class MonthHistoryUI extends JDialog{
	private JLabel prev;
	private JLabel next;
	private JLabel history;
	private ImageIcon prevIcon;
	private ImageIcon nextIcon;
	
	private GregorianCalendar cal;
	private int code;
	public MonthHistoryUI(JFrame frame,GregorianCalendar cal,int code){
		super(frame,true);
		setTitle("월별결산 보기");
		this.cal=cal;
		this.code=code;
		
		System.out.println("년도 정보와 월정보 : "+cal.get(Calendar.YEAR)+cal.get(Calendar.MONTH)+"등록코드 필요 "+code);
		
		Container contentPane=getContentPane();
		// 가장 상단 부분의 월결산과 화살표 버튼 생성
		history=new JLabel(cal.get(Calendar.MONTH)+"월 결산");
		prevIcon = new ImageIcon("prevImage.png");
		nextIcon=new ImageIcon("nextImage.png");
		prev=new JLabel(prevIcon);
		next=new JLabel(nextIcon);
		//가장 상단 부분의 월결산과 화살표 버튼 배치
		JPanel top=new JPanel();
		top.setLayout(new FlowLayout());
		top.add(prev);
		top.add(history);
		top.add(next);
		
		prev.addMouseListener(new PreviousMonthListener());
		next.addMouseListener(new NextMonthListener());
		//테이블  생성
		String[]columNames={"날짜","카테고리","금액","달성여부"};
		DefaultTableModel model=new DefaultTableModel (columNames,0){
			public boolean isCellEditable(int row, int column) 
			{
				return false;
			}
		};
		JTable table = new JTable(model);
		JScrollPane scrollPane = new JScrollPane(table);
		
		//잔액 레이블 생성후 테이블과 묶는 작업
		JLabel money=new JLabel("잔액 : 1000원");
		JPanel moneySet=new JPanel(new FlowLayout(FlowLayout.CENTER));
		moneySet.add(money);
		JPanel subMid=new JPanel();
		subMid.setLayout(new BoxLayout(subMid,BoxLayout.Y_AXIS));
		JPanel subsubMid=new JPanel();
		
		//subMid.add(subsubMid);
		subMid.setBorder(new TitledBorder(""));
		//subMid.setLayout(new BoxLayout(contentPane,BoxLayout.X_AXIS));
		subMid.add(scrollPane);
		subMid.add(moneySet);
		
		//그만 보기 생성
		JButton stop=new JButton("그만보기");
		JPanel stopBtn=new JPanel();
		stopBtn.setLayout(new FlowLayout());
		stopBtn.add(stop);
		stop.addActionListener(new CloseBtnListener());
		
		JPanel mid=new JPanel();
		mid.setLayout(new GridLayout(2,1));
		
		//그래프 그리기
		JPanel draw=new JPanel(){
			public void paint(Graphics g){
				
				g.clearRect(0,0,getWidth(),getHeight());
				g.drawLine(50, 250, 350, 250);
				for(int cnt=1;cnt<4;++cnt){
					g.drawString("학용품", 0, cnt*80);
					
				}
				g.setColor(Color.RED);
				g.drawLine(50,20,50,250);//x,y부터 x2,y2 선그림
				g.fillRect(60,68,40,20);//패널의 x,y좌표 ,가로넓이, 세로넓이
				g.drawString("40%", 100,78);
				g.fillRect(60,136,80,20);
				g.drawString("80%", 140,146);
				g.fillRect(60,214,30,20);
				g.drawString("30%", 90,214);
				
			}
		};
		draw.repaint();
		mid.add(draw);
		mid.add(subMid);
		
		contentPane.add(top,BorderLayout.NORTH);
		contentPane.add(mid);
		contentPane.add(stopBtn,BorderLayout.SOUTH);
		
		setSize(500,700);
		setLocation(400,300);
	//	setVisible(true);
	}
private class PreviousMonthListener extends MouseAdapter{
		
		public void mouseClicked(MouseEvent e){
			if(e.getClickCount()==1){
				if(cal.get(Calendar.MONTH)==1)return;
				cal.set(Calendar.MONTH,cal.get(Calendar.MONTH)-1);
				history.setText(cal.get(Calendar.MONTH)+"월 결산");
				System.out.println("현재년도, 이전달 , 등록코드"+cal.get(Calendar.YEAR)+cal.get(Calendar.MONTH)+code);
			}
		}
	}
	private class NextMonthListener extends MouseAdapter{
		
		public void mouseClicked(MouseEvent e){
			if(e.getClickCount()==1){
				if(cal.get(Calendar.MONTH)==12)return;
				cal.set(Calendar.MONTH,cal.get(Calendar.MONTH)+1);
				history.setText(cal.get(Calendar.MONTH)+"월 결산");
				System.out.println("현재년도, 이전달 , 등록코드"+cal.get(Calendar.YEAR)+cal.get(Calendar.MONTH)+code);
			}
		}
	}
	private class CloseBtnListener implements ActionListener{

		public void actionPerformed(ActionEvent e) {
			setVisible(false);
			//setDefaultCloseOperation(EXIT_ON_CLOSE);
		}
		
	}
}
