package project1;

import java.awt.Button;
import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

public class Main extends JFrame
implements ActionListener{
	
	JPanel itemPanel,catagoryPanel;
	JLabel logo,memberId, itemName, itemPhoto, itemMemo, currentPrice, purchaserCount;
	JTextArea catagoryArea;
	JButton manegerBtn, registerBtn, backBtn,myPageBtn; //frame
	TitledBorder hotbidTb, commentTb;
	JOptionPane alarm = new JOptionPane();
	//TODO 카테고리 데이터 가져오기
	String catagoryName[] = {"디지털기기","취미/게임/음반","가구/인테리어","스포츠/레저","생활가전","의류","반려동물용품","뷰티/미용","중고차","도서"};
	
	Button catagoryNameBtn[] = new Button[catagoryName.length];
	
	public Main() {
	     setTitle("DaBID 메인페이지");
	     setSize(1300,900);
	     setResizable(false);
	     setLayout(null);
	     Container c = getContentPane();
	     hotbidTb = new TitledBorder(new LineBorder(Color.black,1,true),"HOT BID");
	     commentTb = new TitledBorder(new LineBorder(Color.black,1,true),"카테고리");
	     hotbidTb.setTitleFont(new Font("돋움체",0,25));
	     commentTb.setTitleFont(new Font("돋움체",0,25));
	     //왼쪽 패널 ( 상품 정보)
	     itemPanel = new JPanel();
	     itemPanel.setLayout(null);
	     itemPanel.setBounds(60, 80, 550, 700);
	     itemPanel.setBorder(hotbidTb);	
	     //TODO 상품명레이블
	     itemName = new JLabel("상품명 : ");
	     itemName.setBounds(60, 60, 100, 30);
	     itemName.setFont(new Font("돋움체", 0, 15));
	     //TODO 상품이미지
	     itemPhoto = new JLabel("이미지");
	     itemPhoto.setBorder(new LineBorder(Color.black,1,true));
	     itemPhoto.setBounds(60, 90, 450, 400);
	     //TODO 상품설명
	     itemMemo= new JLabel("상품설명");
	     itemMemo.setBorder(new LineBorder(Color.black,1,true));
	     itemMemo.setBounds(60, 500, 450, 100);
	     //TODO 상품현재입찰가
	     currentPrice= new JLabel("현재 입찰가 : ");
	     currentPrice.setBorder(new LineBorder(Color.black,1,true));
	     currentPrice.setBounds(60, 610, 200, 50);
	     currentPrice.setFont(new Font("돋움체", 0, 15));
	     //TODO 현재참여인원 
	     purchaserCount= new JLabel("현재 참여 인원 :  ");
	     purchaserCount.setBorder(new LineBorder(Color.black,1,true));
	     purchaserCount.setBounds(300, 610, 200, 50);
	     purchaserCount.setFont(new Font("돋움체", 0, 15));

	     ////
	     itemPanel.add(itemName);
	     itemPanel.add(itemPhoto);
	     itemPanel.add(itemMemo);
	     itemPanel.add(purchaserCount);
	     itemPanel.add(currentPrice);

	     //오른쪽 패널(카테고리)
	     catagoryPanel = new JPanel();
	     catagoryPanel.setLayout(null);
	     catagoryPanel.setBounds(660, 80, 550, 350);
	     catagoryPanel.setBorder(commentTb);
	     
	     for (int i = 0; i < catagoryName.length; i++) {//w=550,h700
	    	int x=30;
	    	int y=40;
	    	int  w= 140; //고정
	    	int h = 40; //고정
	    	 catagoryNameBtn[i] = new Button(catagoryName[i]);
	    	 catagoryNameBtn[i].setBounds(x+(i%3)*(w+x),y+(i/3)*70,w,h);
		     catagoryNameBtn[i].setFont(new Font("돋움체", 0, 15));
		     catagoryNameBtn[i].addActionListener(this);
		     catagoryPanel.add(catagoryNameBtn[i]);		    
	     }
	     //버튼 2개+이름레이블
	     manegerBtn = new JButton("관리자모드");
	     backBtn = new JButton("뒤로가기");
	     registerBtn = new JButton("상품등록");
	     memberId = new JLabel("아이디 : aaa");
	     
	     manegerBtn.setBounds(1000, 820, 120, 30);
	     backBtn.setBounds(1150, 820, 120, 30);
	     registerBtn.setBounds(1000, 20, 100, 30);
	     memberId.setBounds(1150,20,100,30);
	     
	     manegerBtn.setFont(new Font("돋움체", 0, 15));
	     backBtn.setFont(new Font("돋움체", 0, 15));
	     registerBtn.setFont(new Font("돋움체", 0, 15));
	     memberId.setFont(new Font("돋움체", 0, 15));
	     
	     manegerBtn.addActionListener(this);
	     backBtn.addActionListener(this);
	     registerBtn.addActionListener(this);
	     //logo
	     logo = new JLabel(new ImageIcon(Login.class.getResource("./image/logo.png")));
	     logo.setBounds(20,20,150,50);
	     //logo.setFont(new Font("돋움체", 1, 25));
	   //mypagebtn
	     myPageBtn = new JButton("마이페이지");
	     myPageBtn.setBounds(1150	, 50, 100,30);
	     myPageBtn.setFont(new Font("돋움체",0,12));
	     myPageBtn.addActionListener(this);
	    //add
	     c.add(myPageBtn);
	     c.add(logo);
	     c.add(memberId);
	     c.add(itemPanel);
	     c.add(catagoryPanel);
	     c.add(manegerBtn);
	     c.add(registerBtn);
	     c.add(backBtn);
	     validate();
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		//TODO
		Object obj = e.getSource();
		if(obj==registerBtn/*상품등록*/) {
			 try {
				 dispose();
				itemRegister ir = new itemRegister();
				ir.setVisible(true);
			} catch (Exception e2) {
				e2.printStackTrace();
			} {
				 
			 }
		}else if(obj==manegerBtn/*관리자*/){
			//if(){//권한ok
			try {
				dispose();
				Admin admin = new Admin();
				admin.setVisible(true);
			} catch (Exception e2) {
				e2.printStackTrace();
			}
				//new Manager();
			//}else{
			alarm.showMessageDialog(null, "권한이 없습니다.");
		}else if(obj==backBtn) {//뒤로가기
			alarm.showMessageDialog(null, "메인 페이지입니다.");
		}else if(obj==myPageBtn) {//마이페이지
			try {
				dispose();
				Mypage mp = new Mypage();
				mp.setVisible(true);
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	}
	
	public static void main(String[] args) {
	
		
	}
	

}
