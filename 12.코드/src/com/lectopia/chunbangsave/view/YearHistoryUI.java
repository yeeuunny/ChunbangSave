package com.lectopia.chunbangsave.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Graphics;
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

public class YearHistoryUI extends JDialog{
	private JLabel prev;
	private JLabel next;
	private ImageIcon prevIcon;
	private ImageIcon nextIcon;
	
	private JLabel history;
	private JButton stop;
	
	private GregorianCalendar cal;
	private int code;
	public YearHistoryUI(JFrame frame,GregorianCalendar cal,int code){
		super(frame,true);
		setTitle("년별결산");
		this.code=code;
		this.cal=cal;
		System.out.println("년정보 : "+cal.get(Calendar.YEAR)+"사용자 등록코드"+code);
		
		Container contentPane=getContentPane();
		
		//상단 버튼 생성
		history=new JLabel(cal.get(Calendar.YEAR)+"년 결산");
		prevIcon = new ImageIcon("prevImage.png");
		nextIcon=new ImageIcon("nextImage.png");
		prev=new JLabel(prevIcon);
		next=new JLabel(nextIcon);
		prev.addMouseListener(new PreviousYearListener());
		next.addMouseListener(new NextYearListener());
		
		JPanel top=new JPanel();
		top.setLayout(new FlowLayout());
		top.add(prev);
		top.add(history);
		top.add(next);
		//따로 모듈화 해둘것
		JPanel draw=new JPanel(){
			public void paint(Graphics g){
				setSize(400,270);
				g.clearRect(0,0,getWidth(),getHeight());
				g.drawLine(50, 250, 350, 250);
				for(int cnt=1;cnt<4;++cnt){
					g.drawString("학용품", 0, cnt*80);
					
				}
				g.drawLine(50,20,50,250);//x,y부터 x2,y2 선그림
				g.fillRect(60,68,40,20);//패널의 x,y좌표 ,가로넓이, 세로넓이
				g.drawString("40%", 100,78);
				g.setColor(Color.RED);
				g.fillRect(60,136,80,20);
				g.drawString("80%", 140,146);
				g.setColor(Color.BLUE);
				g.fillRect(60,204,30,20);
				g.drawString("30%", 90,214);
			}
		};
		JLabel money=new JLabel("금액 : 100000");
		money.setAlignmentX(CENTER_ALIGNMENT);
		JLabel mid=new JLabel();
		mid.setLayout(new BoxLayout(mid,BoxLayout.Y_AXIS));
		mid.add(draw);
		mid.add(money);
		
		stop=new JButton("그만보기");
		stop.addActionListener(new CancelBtnListener(this));
		
		JPanel bot=new JPanel();
		bot.setLayout(new FlowLayout());
		bot.add(stop);
		contentPane.add(top,BorderLayout.NORTH);
		contentPane.add(mid);
		contentPane.add(bot,BorderLayout.SOUTH);
		
		setSize(500,430);
		setLocation(400,300);
	}
	private class PreviousYearListener extends MouseAdapter{
		
		public void mouseClicked(MouseEvent e){
			if(e.getClickCount()==1){
				cal.set(Calendar.YEAR, cal.get(Calendar.YEAR)-1);

				history.setText(cal.get(Calendar.YEAR)+"년도 결산");
				System.out.println("년정보 등록코드"+cal.get(Calendar.YEAR)+"  "+code);
			}
		}
	}
	private class NextYearListener extends MouseAdapter{
		
		public void mouseClicked(MouseEvent e){
			if(e.getClickCount()==1){
				cal.set(Calendar.YEAR, cal.get(Calendar.YEAR)+1);

				history.setText(cal.get(Calendar.YEAR)+"년도 결산");
				System.out.println("년정보 등록코드"+cal.get(Calendar.YEAR)+"  "+code);
			}
		}
	}
	
}
