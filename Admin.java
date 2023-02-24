package project1;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

public class Admin extends JFrame implements ActionListener {
   
   JPanel adminCategoryPanel, adminLogPanel, itemPanel;
   JLabel logo, memberId, categoryName;
   JButton backBtn;
   JComboBox<String> adminCb;
   JProgressBar categoryJpb;
   JTable biddingTb, bidendTb;
   JScrollPane biddingScrollPane, bidendScrollPane;
   Color color = new Color(240,240,240);
   LineBorder lb = new LineBorder(Color.black,1,true);
   LineBorder lb1 = new LineBorder(Color.white,0,true);
   
   String logId;
   //상품 이름", "판매자 ID","참여 인원","최종 가격"
   String[] cbList = {"카테고리 별 판매량", "경매 log"};
   String[] categoryNames = {"디지털기기", "의류","생활가전","스포츠/레저","취미/게임/음반","뷰티/미용","반려동물용품","가구/인테리어","차량","도서"};
   
   Container c = getContentPane();
   AuctionMgr mgr;
   
   public Admin(String logId) {
      // mgr 
      mgr = new AuctionMgr();
      
      // container
      setTitle("DaBID 관리자 페이지");
      setSize(1300, 900);
      setResizable(false);
      setLayout(null);
      setLocationRelativeTo(null);
      // 
      adminCategoryPanel = new JPanel();
      adminCategoryPanel.setLayout(new GridLayout(0, 1, 10, 10));
      //
      adminCategoryPanel.setBounds(150, 140, 1000, 350);
      // index 1 log Panel
      adminLogPanel = new JPanel();
      adminLogPanel.setLayout(null);
      adminLogPanel.setBounds(60, 120, 1160, 670);
      // JComboBox
      //adminCb (관리자 콤보박스)
      adminCb = new JComboBox<>(cbList);
      adminCb.setBounds(60, 80, 200, 34);
      adminCb.setFont(new Font("맑은 고딕", Font.BOLD, 17));
      adminCb.addActionListener(this);
      //backBtn (뒤로가기)
      backBtn = new JButton("뒤로가기");
      backBtn.setBounds(1150, 820, 120, 30);
      backBtn.setFont(new Font("맑은 고딕", Font.BOLD, 15));
      backBtn.addActionListener(this);
      // memberId (멤버 아이디 라벨)
      memberId = new JLabel("아이디 : "+logId);
      memberId.setBounds(1150,20,150,30);
      memberId.setFont(new Font("맑은 고딕", 0, 15));
       // logo (메인 로고)
      logo = new JLabel(new ImageIcon(Login.class.getResource("./image/logo.png")));
      logo.setBounds(20, 25, 130, 40);
       
       //디자인
       //뒤로가기 버튼 디자인
       backBtn.setBackground(Color.black);
       backBtn.setForeground(Color.white);
       backBtn.setBorderPainted(false);
       backBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
       this.add(backBtn);
       //콤보 박스 디자인
       adminCb.setBackground(color);
       adminCb.setBorder(lb);
       adminCb.setCursor(new Cursor(Cursor.HAND_CURSOR));
       this.add(adminCb);
       //배경
       c.setBackground(Color.white);
       adminCategoryPanel.setBackground(Color.white);
       //Panel
      itemPanel = new JPanel();
      itemPanel.setLayout(null);
      c.add(adminCb);
      c.add(memberId);
      c.add(logo);
      c.add(backBtn);
      validate();
   }
   
   public JPanel statisticsData(int num, int values) {
	      
	      // 아이템 넣을 Panel
	      itemPanel = new JPanel();
	      itemPanel.setLayout(null);
//	      itemPanel.setBounds(100, 130, 1100, 55);
	            
	      // 카테고리 이름
	      categoryName = new JLabel(categoryNames[num]);
	      categoryName.setFont(new Font("맑은 고딕", Font.BOLD, 17));
	      categoryName.setHorizontalAlignment(SwingConstants.CENTER);
	      categoryName.setBounds(5, 5, 160, 30);
	            
	      // 카테고리 그래프
	      JProgressBar categoryJpb = new JProgressBar();
	      categoryJpb.setBounds(190, 0, 800, 40);
	      categoryJpb.setValue(values);
	      
	      itemPanel.add(categoryName);
	      itemPanel.add(categoryJpb);
	      
	      //디자인
	      itemPanel.setBackground(Color.white);
	      categoryJpb.setForeground(Color.black);
	      
	      return itemPanel;
	   }

	   public void addData(int index) {
	      
	   
	      Vector<ItemBean> vlist;
	      vlist = mgr.getAdminCategory();
	      ItemBean ibean;
	      for (int i = 0; i < vlist.size(); i++) {
			ibean = vlist.get(i);
		}
	      if (index == 0) {
	    	 c.remove(adminLogPanel);
	         
	         
	         for (int i = 0; i < vlist.size(); i++) {
	            JPanel p;
	        	ibean = vlist.get(i);
	            p = statisticsData(ibean.getCategoryNum(),vlist.get(i).getAllCount());
	            
	            p.setPreferredSize(new Dimension(10, 20));
	            adminCategoryPanel.add(p);
	            c.add(adminCategoryPanel);
	         }
	      } else if (index == 1) {
	    	  //상품 이름", "판매자 ID","참여 인원","최종 가격"
	    	   //Object[][] listData = new Object[subjectList.size()][12];
	    	  c.remove(adminCategoryPanel);
		     
	    	  String ing_header[] = {"상품 이름", "판매자 ID","참여 인원","판매 가격"};
	    	  String end_header[] = {"상품 이름", "판매자 ID","참여 인원","최종 가격"};
	    	  //Vector<String> ing_data = new Vector<>();
	    	  
	    	  DefaultTableModel ingModel = new DefaultTableModel(ing_header, 0);
	    	  DefaultTableModel endModel = new DefaultTableModel(end_header, 0);
	    	  mgr.getAuctionIng(ingModel);
	    	  mgr.getAuctionEnd(endModel);
	    	 
	    	  biddingTb = new JTable(ingModel);
	    	  bidendTb = new JTable(endModel);
	    	  biddingScrollPane  = new JScrollPane(biddingTb);
	    	  biddingScrollPane.setBounds(65,30,500,600);
	    	  biddingScrollPane.setFont(new Font("맑은고딕",0,20));
	    	  bidendScrollPane = new JScrollPane(bidendTb);
	    	  bidendScrollPane.setBounds(595,30,500,600);
	    	  
	     	  //디자인
	    	  adminLogPanel.setBackground(Color.white);
	    	  biddingTb.setBackground(Color.white);
	    	  biddingScrollPane.setBorder(lb1);
	    	  bidendScrollPane.setBorder(lb1);
	    	  biddingTb.getColumn("상품 이름").setPreferredWidth(200);
	    	  bidendTb.getColumn("상품 이름").setPreferredWidth(200);
	    	  biddingTb.setRowHeight(30);
	    	  bidendTb.setRowHeight(30);
	    	  biddingTb.setBorder(lb);
	    	  bidendTb.setBorder(lb);
	    	  biddingTb.getTableHeader().setBackground(Color.black);
	    	  biddingTb.getTableHeader().setFont(new Font("맑은 고딕", Font.BOLD, 15));
	    	  biddingTb.getTableHeader().setForeground(Color.white);
	    	  biddingTb.setFont(new Font("맑은 고딕", 0, 12));
	    	  bidendTb.getTableHeader().setBackground(Color.black);
	    	  bidendTb.getTableHeader().setFont(new Font("맑은 고딕", Font.BOLD, 15));
	    	  bidendTb.getTableHeader().setForeground(Color.white);
	    	  bidendTb.setFont(new Font("맑은 고딕", 0, 12));
	    	  
	    	  adminLogPanel.add(biddingScrollPane);
	    	  adminLogPanel.add(bidendScrollPane);
	    	  c.add(adminLogPanel);
	      }
	      
	      repaint();
	      validate();
	   }
	   

	   @Override
	   public void actionPerformed(ActionEvent e) {
	      logId = memberId.getText();
	      logId = logId.substring(logId.lastIndexOf(":")+1).trim();
	      
	      Object obj = e.getSource();
	      if (obj == adminCb) {
	         int index = adminCb.getSelectedIndex();
	         
	         addData(index);
	         repaint(); 
	      }else if(obj == backBtn) {
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