package project1;

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

public class Auction extends JFrame
implements ActionListener{
	
	JPanel itemPanel,commentPanel;
	JLabel logo, memberId, itemName, itemPhoto, itemMemo, currentPrice, purchaserCount, auctionPriceLbl, auctionTime;
	JTextArea commentArea;
	JTextField comment, bidpriceTf;
	JButton commentBtn, auctionBtn,backBtn; 
	TitledBorder itemTb, commentTb;
	String logId;
	Font f = new Font("돋움체",0,15);
	
	public Auction() {
		setTitle("DaBID 경매페이지");
	    setSize(1300,900);
	    setResizable(false);
	    setLayout(null);
	    Container c = getContentPane();
	    
	    itemTb = new TitledBorder(new LineBorder(Color.black,1,true),"상품설명");
	    commentTb = new TitledBorder(new LineBorder(Color.black,1,true),"댓글 및 입찰");	    
	    itemTb.setTitleFont(new Font("돋움체",0,25));
	    commentTb.setTitleFont(new Font("돋움체",0,25));
	    //왼쪽 패널 ( 상품 정보)
	    itemPanel = new JPanel();
	    itemPanel.setLayout(null);
	    itemPanel.setBounds(60, 80, 550, 700);
	    itemPanel.setBorder(itemTb);	
	     //상품명레이블
	    itemName = new JLabel("상품명 : ");
	    itemName.setBounds(60, 60, 100, 30);
	    itemName.setFont(new Font("돋움체", 0, 15));
	    //상품이미지
	    itemPhoto = new JLabel("이미지");
	    itemPhoto.setBorder(new LineBorder(Color.black,1,true));
	    itemPhoto.setBounds(60, 90, 450, 400);
	     //상품설명
	    itemMemo= new JLabel("상품설명");
	    itemMemo.setBorder(new LineBorder(Color.black,1,true));
	    itemMemo.setBounds(60, 500, 450, 100);
	     //상품현재입찰가
	    currentPrice= new JLabel("현재 입찰가 : ");
	    currentPrice.setBorder(new LineBorder(Color.black,1,true));
	    currentPrice.setBounds(60, 610, 200, 50);
	    currentPrice.setFont(new Font("돋움체", 0, 15));
	     //현재참여인원 
	    purchaserCount= new JLabel("현재 참여 인원 :  ");	     
	    purchaserCount.setBorder(new LineBorder(Color.black,1,true));
	    purchaserCount.setBounds(300, 610, 200, 50);
	    purchaserCount.setFont(new Font("돋움체", 0, 15));
	    //
	    itemPanel.add(itemName);
	    itemPanel.add(itemMemo);
	    itemPanel.add(itemPhoto);
	    itemPanel.add(purchaserCount);
	    itemPanel.add(currentPrice);
	    
	    //오른쪽 패널(댓글+입찰)
	    commentPanel = new JPanel();
	    commentPanel.setLayout(null);
	    commentPanel.setBounds(660, 80, 550, 700);
	    commentPanel.setBorder(commentTb);
	    //textarea
	    commentArea = new JTextArea();
	    commentArea.setBounds(60, 90, 450, 450);
	    commentArea.setBorder(new LineBorder(Color.black,1,true));
	    //남은시간
	    auctionTime = new JLabel("남은 시간 : ");
	    auctionTime.setBounds(400,30,130,30);
	    auctionTime.setBorder(new LineBorder(Color.black,1,true));
	    auctionTime.setFont(f);
	    //comment댓글입력
	    comment = new JTextField("댓글을 입력하세요.");
	    comment.setBounds(60, 540, 380, 30);
	    comment.setBorder(new LineBorder(Color.black,1,true));
	    comment.setFont(f);
	    //comment버튼전송
	    commentBtn = new JButton("전송");
	    commentBtn.setBounds(440, 540, 70, 30);
	    commentBtn.setBorder(new LineBorder(Color.black,1,true));
	    commentBtn.setFont(f);
	    commentBtn.addActionListener(this);
	    //입찰가격레이블
	    auctionPriceLbl = new JLabel("입찰가격");
	    auctionPriceLbl.setBounds(220, 600, 80, 30);
	    auctionPriceLbl.setBorder(new LineBorder(Color.black,1,true));
	    auctionPriceLbl.setFont(f);
	    //입찰가격TF
	    bidpriceTf = new JTextField();
	    bidpriceTf.setBounds(300, 600, 120, 30);
	    bidpriceTf.setBorder(new LineBorder(Color.black,1,true));
	    //입찰버튼
	    auctionBtn = new JButton("입찰하기");
	    auctionBtn.setBounds(400, 600, 70, 30);
	    auctionBtn.setBorder(new LineBorder(Color.black,1,true));
	    auctionBtn.setFont(f);
	    auctionBtn.addActionListener(this);
	    //
	    commentPanel.add(commentArea);
	    commentPanel.add(auctionTime);
	    commentPanel.add(comment);
		commentPanel.add(commentBtn); 
		commentPanel.add(auctionBtn);
		commentPanel.add(auctionPriceLbl); 
		commentPanel.add(bidpriceTf);
		backBtn = new JButton("뒤로가기");
		backBtn.setBounds(1150, 820, 120, 30);
		backBtn.setFont(new Font("돋움체", 0, 15));
		backBtn.addActionListener(this);
		//
		logo = new JLabel(new ImageIcon(Login.class.getResource("./image/logo.png")));
	    logo.setBounds(20,20,130,40);
	    //
	     c.add(logo);
	    c.add(itemPanel);
	    c.add(commentPanel);
	    c.add(backBtn);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		logId = memberId.getText();
		logId = logId.substring(logId.lastIndexOf(":")+1).trim();
		
		Object obj = e.getSource();
		if(obj==commentBtn/*댓글달기*/) {
			 //
		}else if(obj==auctionBtn/*입찰하기*/){
			//
		}else if(obj==backBtn) {
			dispose();
			try {
				Main main = new Main(logId);
				main.setVisible(true);
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	}
	
	
	public static void main(String[] args) {
		try {
			Auction ac = new Auction();
			ac.setVisible(true);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
}
