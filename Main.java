package project1;

import java.awt.Button;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Container;
import java.awt.FileDialog;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Iterator;
import java.util.Vector;

import javax.imageio.ImageIO;
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

import project1.ItemRegister.ImageCanvas;
import project1.Mypage.timerSet;


public class Main extends JFrame
implements ActionListener{
   
   JPanel itemPanel,categoryPanel,imagePanel;
   JLabel logo, memberId, itemName, itemPhoto, itemMemo, currentPrice, purchaserCount, itemTime;
   JTextArea catagoryArea;
   JButton manegerBtn, registerBtn, backBtn,myPageBtn, hotBidBtn; //frame
   TitledBorder hotbidTb, commentTb;
   JOptionPane alarm = new JOptionPane();
   String logId;
   String cateName[];
   Button categoryNameBtn[] = new Button[10];
   AuctionMgr mgr = new AuctionMgr();
   ImageCanvas canvas;
   Image img;
   String filename;
   String dirfile;
   File file;
   FileReader reader;
   FileDialog read;
   
   public Main(String logId) {
        setTitle("DaBID 메인페이지");
        setSize(1300,900);
        setResizable(false);
        setLocationRelativeTo(null); //가운데 출력
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
   
        ItemBean ibean = mgr.getHotItem();
       // itemBena ibean = mgr.getItem(1);
        //상품명레이블
        itemName = new JLabel("상품명 : " + ibean.getItemName());
        itemName.setBounds(60, 60, 400, 30);
        itemName.setFont(new Font("돋움체", 0, 17));
        //이미지 리사이즈
        ImageIcon icon = new ImageIcon(Login.class.getResource("./image/"+ibean.getItemName()+".jpg"));
        Image img = icon.getImage();
        Image changeImg = img.getScaledInstance(450, 370, Image.SCALE_SMOOTH);
        ImageIcon changeIcon = new ImageIcon(changeImg);
        
        itemPhoto = new JLabel(changeIcon);
        itemPhoto.setBorder(new LineBorder(Color.black,1,true));
        itemPhoto.setBounds(60, 90, 450, 370);
        //상품설명
        itemMemo= new JLabel("상품 설명 : " + ibean.getItemMemo());
        itemMemo.setBorder(new LineBorder(Color.black,1,true));
        itemMemo.setBounds(60, 470, 450, 100);
        // 상품현재입찰가
        currentPrice= new JLabel(" 현재 가격 : " + ibean.getItemPrice()+" 원");
        currentPrice.setBorder(new LineBorder(Color.black,1,true));
        currentPrice.setBounds(60, 580, 200, 50);
        currentPrice.setFont(new Font("돋움체", 0, 17));
        //상품시간
        int time = ibean.getItemEndTime();
        
        int hour = time / (60*60);
        int minute = time / 60 - (hour*60);
        int second = time % 60;
        
        String reHour = Integer.toString(hour);
        String reMin = Integer.toString(minute);
        String reSec = Integer.toString(second);
        
        itemTime = new JLabel("남은 시간 : " +reHour+":"+reMin+":"+reSec);
        itemTime.setBounds(60, 630, 300, 50);
        itemTime.setFont(new Font("돋움체", 0, 16));
        
        timerSet ts = new timerSet(itemTime, time);
        // 현재참여인원 
        purchaserCount= new JLabel(" 현재 참여 인원 : " + ibean.getPurchaserCount()+"명");
        purchaserCount.setBorder(new LineBorder(Color.black,1,true));
        purchaserCount.setBounds(280, 580, 210, 50);
        purchaserCount.setFont(new Font("돋움체", 0, 17));
        //핫한경매 참여버튼 
        hotBidBtn = new JButton("참여하기");
        hotBidBtn.setBounds(410, 645, 100, 30);
        hotBidBtn.setFont(new Font("돋움체", 0, 17));
        hotBidBtn.addActionListener(this);
        ////
        itemPanel.add(itemName);
        itemPanel.add(itemTime);
        itemPanel.add(itemPhoto);
        itemPanel.add(itemMemo);
        itemPanel.add(purchaserCount);
        itemPanel.add(currentPrice);
        itemPanel.add(hotBidBtn);
        //오른쪽 패널(카테고리)
        categoryPanel = new JPanel();
        categoryPanel.setLayout(null);
        categoryPanel.setBounds(660, 80, 550, 350);
        categoryPanel.setBorder(commentTb);
        //카테고리 넣기
        
       Vector<CategoryBean> clist = mgr.getCategory();
      
       for (int i = 0; i < clist.size(); i++) {
         CategoryBean cbean = clist.get(i);
         categoryNameBtn[i] = new Button(cbean.getCategoryName());
         int x=30;
          int y=40;
          int  w= 140; //고정
          int h = 40; //고정
           categoryNameBtn[i].setBounds(x+(i%3)*(w+x),y+(i/3)*70,w,h);
           categoryNameBtn[i].setFont(new Font("돋움체", 0, 15));
           categoryNameBtn[i].addActionListener(this);
           categoryPanel.add(categoryNameBtn[i]);       
        }
        //버튼 2개+이름레이블
        manegerBtn = new JButton("관리자모드");
        backBtn = new JButton("뒤로가기");
        registerBtn = new JButton("상품등록");
        memberId = new JLabel("아이디 : " + logId);
        
        manegerBtn.setBounds(1000, 820, 120, 30);
        backBtn.setBounds(1150, 820, 120, 30);
        registerBtn.setBounds(1000, 20, 100, 30);
        memberId.setBounds(1150,20,150,30);
        
        manegerBtn.setFont(new Font("돋움체", 0, 15));
        backBtn.setFont(new Font("돋움체", 0, 15));
        registerBtn.setFont(new Font("돋움체", 0, 15));
        memberId.setBounds(1150,20,150,30);
        memberId.setFont(new Font("돋움체", 0, 15));
        
        manegerBtn.addActionListener(this);
        backBtn.addActionListener(this);
        registerBtn.addActionListener(this);
        //logo
        logo = new JLabel(new ImageIcon(Login.class.getResource("./image/logo.png")));
        logo.setBounds(20,20,130,40);
        //logo.setFont(new Font("돋움체", 1, 25));
        //mypagebtn
        myPageBtn = new JButton("마이페이지");
        myPageBtn.setBounds(1150   , 50, 100,30);
        myPageBtn.setFont(new Font("돋움체",0,12));
        myPageBtn.addActionListener(this);
        //add
        c.add(myPageBtn);
        c.add(logo);
        c.add(memberId);
        c.add(itemPanel);
        c.add(categoryPanel);
        c.add(manegerBtn);
        c.add(registerBtn);
        c.add(backBtn);
        
        validate();
   }
   class ImageCanvas extends Canvas{
	   @Override
	public void paint(Graphics g) {
		g.drawImage(img, 0, 0, this);
	}
   }
   
   @Override
   public void actionPerformed(ActionEvent e) {
      
	   logId = memberId.getText();
	   logId = logId.substring(logId.lastIndexOf(":")+1).trim();
	      
	      Object obj = e.getSource();
	      if(obj==registerBtn/*상품등록*/) {
	          try {
	            dispose();
	            ItemRegister ir = new ItemRegister(logId);
	            ir.setVisible(true);
	         } catch (Exception e2) {
	            e2.printStackTrace();
	         }
	      }else if(obj==manegerBtn/*관리자*/){   
	         //TODO position 검사
	         MemberBean mbean = mgr.getPosition(logId);
	         if(mbean.getMemberPosition()==1) {
	            try {
	               dispose();
	               Admin admin = new Admin(logId);
	               admin.setVisible(true);
	            } catch (Exception e2) {
	               e2.printStackTrace();
	            }
	         }else {
	            alarm.showMessageDialog(null, "권한이 없습니다.");
	         }
	      }else if(obj==backBtn) {//뒤로가기
	         alarm.showMessageDialog(this, "메인 페이지입니다.");
	      }else if(obj==myPageBtn) {//마이페이지
	    	  mgr.getIngItemList(logId);
	    	  mgr.getBuyItemList(logId);
	    	  mgr.getSellItemList(logId);
	         try {
	            dispose();
	            Mypage mp = new Mypage(logId);
	            mp.setVisible(true);
	         } catch (Exception e2) {
	            e2.printStackTrace();
	         }
	      }else if(obj==hotBidBtn) {//경매페이지로 이동
	         try {
	            dispose();
	            AuctionMgr mgr = new AuctionMgr();
	            ItemBean ibean= mgr.getHotItem();
	            Auction auc = new Auction(logId,ibean.getItemNum());
	            auc.setVisible(true);
	         } catch (Exception e2) {
	            e2.printStackTrace();
	         }
	      } else {//카테코리 리스트 이름 출력 --> 지우
	         for (int i = 0; i < categoryNameBtn.length; i++) {
	            if (obj == categoryNameBtn[i]) {
	               try {
	                  dispose();
	                  CategoryList cl = new CategoryList(categoryNameBtn[i].getLabel(), logId);
	                  cl.setVisible(true);
	               } catch (Exception e2) {
	                  e2.printStackTrace();
	               }
	            }
	         }
	      }
   }
   class timerSet implements Runnable{
	    
	    JLabel itemTime;
	    int time;
	    
	    public timerSet(JLabel itemTime, int time) {
	       this.itemTime = itemTime;
	       this.time = time;
	       new Thread(this).start();
	    }
	    
	    @Override
	    public void run() {
	       while(true) {
	          try {
	             time--;
	             if (time < 0) {
	            break;
	         }
	             int hour = time / (60 * 60);
	             int minute = time / 60 - (hour * 60);
	             int second = time % 60;

	             String reHour = Integer.toString(hour);
	             String reMin = Integer.toString(minute);
	             String reSec = Integer.toString(second);
	             
	             itemTime.setText("남은 시간 : " +reHour + ":" + reMin + ":" + reSec);
	             Thread.sleep(1000);
	            
	          } catch (InterruptedException e) {
	             // TODO Auto-generated catch block
	             e.printStackTrace();
	          }
	       }
	    }
	    
	 }
   public static void main(String[] args) {
      try {
         //Main main = new Main();
         //main.setVisible(true);
      } catch (Exception e) {
         e.printStackTrace();
      }
   }
   

}