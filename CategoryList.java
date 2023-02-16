package project1;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

public class CategoryList extends JFrame
implements ActionListener{
   
   
   JLabel logo, categoryName, memberId,itemName, itemPhoto,auctionPrice, auctionTime, purchaserCount;
   JButton partBtn, backBtn;
   JPanel listPanel,itemPanel;
   TitledBorder catagoryListTb;
   JScrollPane scroll;
   Font f = new Font("돋움체",0,15);
   
   public CategoryList() {
      Container c = getContentPane();
      setTitle("DaBID 카테고리 리스트 페이지");
       setSize(1300,900);
       setResizable(false);
       setLayout(null);
       
       listPanel = new JPanel();
       listPanel.setLayout(new GridLayout(0,1, 10, 10));
//       listPanel.setBounds(60, 80, 1150, 700);
       catagoryListTb = new TitledBorder(new LineBorder(Color.black,1,true),"카테고리 리스트");
       catagoryListTb.setTitleFont(new Font("돋움체",0,25));
//       listPanel.setBorder(catagoryListTb);   
       //itempanel을 listpanel에 넣기
       
       int x=50;
       int y=50;
       int w=1050;
       int h=200;
       
       
       //
       scroll = new JScrollPane(listPanel);
       scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
//       scroll.setBounds(1127,16,20,682); 
       scroll.setBounds(60, 80, 1150, 700);
       
       
       
       
       
       // 1150, 700
//       listPanel.add(scroll);
//       listPanel.add(itemPanel);
       
       		for (int i = 0; i < 10; i++) {
       			itemPanel = new JPanel();
       			itemPanel.setLayout(null);
       			//itemPanel.setBounds(x,y,w,h);
       			itemPanel.setPreferredSize(new Dimension(1050, 200));
       			itemPanel.setBorder(new LineBorder(Color.black,1,true));
       			//itempanel안에 label / 버튼 넣기
       			itemPhoto = new JLabel("itemPhoto");
       			itemPhoto.setBounds(0,0, 250, 200);
       			itemPhoto.setFont(new Font("돋움체", 0, 15));
       			itemPhoto.setBorder(new LineBorder(Color.black,1,true));
       			//
       			itemName = new JLabel("itemname");
       			itemName.setBounds(300, 40, 120, 30);
       			itemName.setFont(new Font("돋움체", 0, 15));
       			itemName.setBorder(new LineBorder(Color.black,1,true));
       			//
       			auctionPrice = new JLabel("auctionPrice");
       			auctionPrice.setBounds(300, 100, 120, 30);
       			auctionPrice.setFont(new Font("돋움체", 0, 15));
       			auctionPrice.setBorder(new LineBorder(Color.black,1,true));
           //
       			auctionTime = new JLabel("auctionTime");
       			auctionTime.setBounds(500, 40, 120, 30);
       			auctionTime.setFont(new Font("돋움체", 0, 15));
       			auctionTime.setBorder(new LineBorder(Color.black,1,true));
          //
       			purchaserCount = new JLabel("purchaserCount");
       			purchaserCount.setBounds(500, 100, 120, 30);
       			purchaserCount.setFont(new Font("돋움체", 0, 15));
       			purchaserCount.setBorder(new LineBorder(Color.black,1,true));
          //
       			partBtn = new JButton("참여하기");
       			partBtn.setBounds(900, 50, 100, 100);
       			partBtn.setFont(new Font("돋움체", 0, 15));
       			partBtn.setBorder(new LineBorder(Color.black,1,true));
       			partBtn.addActionListener(this);
           
           //
           
       			itemPanel.add(itemName);
           		itemPanel.add(itemPhoto);
           		itemPanel.add(auctionPrice);
           		itemPanel.add(auctionTime);
           		itemPanel.add(partBtn);
           		itemPanel.add(purchaserCount);
           		itemPanel.setBounds(x, y + 200*i, w, h);
          		listPanel.add(itemPanel);
       		}
       //

      categoryName = new JLabel("카테코리 이름");
      categoryName.setBounds(550,30,150,30);
      categoryName.setFont(new Font("돋움체", 0, 20));
      memberId = new JLabel("아이디 : aaa");
      memberId.setBounds(1150,20,100,30);
      memberId.setFont(new Font("돋움체", 0, 15));
      backBtn = new JButton("뒤로가기");
      backBtn.setBounds(1150, 820, 120, 30);
      backBtn.setFont(new Font("돋움체", 0, 15));
      //
      logo = new JLabel(new ImageIcon(Login.class.getResource("./image/logo.png")));
      logo.setBounds(20,20,130,40);
      c.add(logo);
      //
      c.add(categoryName);
      c.add(logo);
      c.add(scroll);
      c.add(memberId);
      c.add(backBtn);
      repaint();
      validate();
   }
   
   @Override
   public void actionPerformed(ActionEvent e) {
      Object obj = new Object();
      
   }
   public static void main(String[] args) {
      try {
         CategoryList cl = new CategoryList();
         cl.setVisible(true);
      } catch (Exception e) {
         e.printStackTrace();
      }
   }
}