package project1;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Vector;

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
	JTextField commentTf, bidpriceTf;
	JButton commentBtn, auctionBtn, backBtn; 
	TitledBorder itemTb, commentTb;
	JOptionPane alarm = new JOptionPane();
	String logId;	// 다음에는 전역변수로 하기
	
    
	Font f = new Font("돋움체",0,15);
	
	public Auction(String logId, int itemNum) {
		setTitle("DaBID 경매페이지");
	    setSize(1300,900);
	    setResizable(false);
	    setLayout(null);
	    setLocationRelativeTo(null); //가운데 출력
	    int itemNum2 = itemNum;
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
	    
	    //memberid
	    memberId = new JLabel("아이디 : "+logId);
	    memberId.setBounds(1150,20,150,30);
        memberId.setFont(new Font("돋움체", 0, 15));
        
        AuctionMgr mgr = new AuctionMgr();
        ItemBean ibean = mgr.getItem(itemNum);
	     //상품명레이블
	    itemName = new JLabel("상품명 : " + ibean.getItemName());
	    itemName.setBounds(60, 60, 100, 30);
	    itemName.setFont(new Font("돋움체", 0, 15));
	   
	    //상품이미지
	    itemPhoto = new JLabel(new ImageIcon(Login.class.getResource("./image/"+ibean.getItemName()+".jpg")));
	    itemPhoto.setBorder(new LineBorder(Color.black,1,true));
	    itemPhoto.setBounds(60, 90, 450, 400);
	     //상품설명
	    itemMemo= new JLabel(ibean.getItemMemo());
	    itemMemo.setBorder(new LineBorder(Color.black,1,true));
	    itemMemo.setBounds(60, 500, 450, 100);
	     //상품현재입찰가
	    currentPrice= new JLabel("현재 입찰가 : " + ibean.getItemPrice()+"원");
	    currentPrice.setBorder(new LineBorder(Color.black,1,true));
	    currentPrice.setBounds(60, 610, 200, 50);
	    currentPrice.setFont(new Font("돋움체", 0, 15));
	     //현재참여인원 
	    purchaserCount= new JLabel("현재 참여 인원 :  "+ibean.getPurchaserCount()+"명");	     
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
	    commentArea.setEnabled(false);
	    //이전 댓글 가져오기
	    Vector<CommentBean> clist = mgr.getCommentList(ibean.getItemNum());
	    for (int i = 0; i < clist.size(); i++) {
	    	
	    	CommentBean cbean = clist.get(i);
	    	if(cbean.getCommentContent()==null) {
	    		commentArea.append("이전 댓글이 없습니다.\n");
	    	}else {
	    		Date time = cbean.getCommentTime();
				String purchaserId = cbean.getPurchaserId();
				String comment = cbean.getCommentContent();
				commentArea.append(time+"\n"+purchaserId+" : "+comment+"\n");
	    	}
		}
	    //남은시간
	    auctionTime = new JLabel("남은 시간 : ");
	    auctionTime.setBounds(400,30,130,30);
	    auctionTime.setBorder(new LineBorder(Color.black,1,true));
	    auctionTime.setFont(f);
	    //comment댓글입력
	    commentTf = new JTextField();
	    commentTf.setBounds(60, 540, 380, 30);
	    commentTf.setBorder(new LineBorder(Color.black,1,true));
	    commentTf.setFont(f);
	    commentTf.requestFocus();
	    //comment버튼전송
	    commentBtn = new JButton("전송");
	    commentBtn.setBounds(440, 540, 70, 30);
	    commentBtn.setBorder(new LineBorder(Color.black,1,true));
	    commentBtn.setFont(f);
	    commentBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(commentTf.getText().isEmpty()) {
					 alarm.showMessageDialog(null, "댓글을 입력하세요.");
				 }else {
					 String comment = commentTf.getText();
					 LocalDate now = LocalDate.now();
					 commentArea.append(now+"\n"+logId +" : "+comment+"\n");
					 //댓글 저장
					 String seller = ibean.getItemSeller();
					 int itemNum = ibean.getItemNum();
					 System.out.println(seller+logId+itemNum+comment);
					 mgr.insertComment(seller, logId, itemNum, comment);
					
					 commentTf.setText(" ");
				 }
			}
		});
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
	    auctionBtn.setBounds(420, 600, 70, 30);
	    auctionBtn.setBorder(new LineBorder(Color.black,1,true));
	    auctionBtn.setFont(f);
	    auctionBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(bidpriceTf.getText()==null) {
					alarm.showMessageDialog(null, "가격을 입력하세요.");
				}else {
					
					int itemNum = ibean.getItemNum();
					int itemPrice = Integer.parseInt(bidpriceTf.getText());
					
					mgr.insertAuction(itemNum, itemPrice, logId);
					alarm.showMessageDialog(null, "입찰 성공!");
					try {
						dispose();
						Main main = new Main(logId);
						main.setVisible(true);
					} catch (Exception e2) {
						// TODO: handle exception
					}
					
				}
			}
		});
	    //
	    commentPanel.add(commentArea);
	    commentPanel.add(auctionTime);
	    commentPanel.add(commentTf);
		commentPanel.add(commentBtn); 
		commentPanel.add(auctionBtn);
		commentPanel.add(auctionPriceLbl); 
		commentPanel.add(bidpriceTf);
		//
		logo = new JLabel(new ImageIcon(Login.class.getResource("./image/logo.png")));
	    logo.setBounds(20,20,130,40);
	    //
	    backBtn = new JButton("뒤로가기");
		backBtn.setBounds(1150, 820, 120, 30);
	    backBtn.setBorder(new LineBorder(Color.black,1,true));
		backBtn.setFont(f);
		backBtn.addActionListener(this);
	    //
		c.add(memberId);
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
			 
			 
		}else if(obj==auctionBtn/*입찰하기*/){
			
		}else if(obj==backBtn) {
			try {
				dispose();
				Main main = new Main(logId);
				main.setVisible(true);
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	}
	
	
	public static void main(String[] args) {
		
	}
}
