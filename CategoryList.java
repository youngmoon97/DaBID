package project1;

import java.awt.Color;
import java.awt.Container;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;   
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

public class CategoryList extends JFrame
implements ActionListener{
   
   
   JLabel logo, categoryName, memberId,itemName, itemPhoto,auctionPrice, auctionTime, purchaserCount;
   JButton backBtn;
   JPanel listPanel,itemPanel;
   TitledBorder catagoryListTb;
   JScrollPane scroll;
   String logId;
   AuctionMgr mgr;
   Color lightGray = new Color(0,0,0);
   Color txtColor = new Color(240,240,240); //배경 색상 선언
   Font f = new Font("맑은 고딕",0,15);
   LineBorder lb = new LineBorder(Color.black,1,true);
   
   public CategoryList(String category, String logId) {
      
     // mgr 가져오기
     mgr = new AuctionMgr();
     
     
      Container c = getContentPane();
     c.setBackground(Color.white);
     
     setTitle("DaBID 카테고리 리스트 페이지");
      setSize(1300,900);
      setResizable(false);
      setLayout(null);
      setLocationRelativeTo(null); //가운데 출력

       listPanel = new JPanel();
       listPanel.setLayout(new GridLayout(0,1, 10, 10));
       catagoryListTb = new TitledBorder(new LineBorder(Color.black,1,true),"카테고리 리스트");
       catagoryListTb.setTitleFont(new Font("맑은 고딕",Font.BOLD,25));
  
       //
       scroll = new JScrollPane(listPanel);
       scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
       scroll.getVerticalScrollBar().setUnitIncrement(16);
       scroll.setBounds(60, 80, 1150, 700);
       scroll.setBorder(lb);
       
      // main 끝나면 라벨 수정
      categoryName = new JLabel(category);
      categoryName.setBounds(600,30,150,30);
      categoryName.setFont(new Font("맑은 고딕", Font.BOLD, 20));
      
      Vector<ItemBean> vc = mgr.getItemList(category);
      
      memberId = new JLabel("아이디 : " + logId);
      memberId.setBounds(1150,20,200,30);
      memberId.setFont(f);
      
      backBtn = new JButton("뒤로가기");
      backBtn.setBounds(1150, 820, 120, 30);
      backBtn.setFont(new Font("맑은 고딕", Font.BOLD, 13));
      backBtn.setForeground(Color.white);
      backBtn.setBackground(Color.black);
      backBtn.setBorderPainted(false);
      backBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
      backBtn.addActionListener(this);
      //
      logo = new JLabel(new ImageIcon(Login.class.getResource("./image/logo.png")));
      logo.setBounds(20,25,130,40);
      c.add(logo);
      //
     
      
      
      CreateData(vc);
      c.add(categoryName);
      c.add(logo);
      c.add(scroll);
      c.add(memberId);
      c.add(backBtn);
      
      repaint();
      validate();
   }
   
   public void CreateData(Vector<ItemBean> vlist) {

      listPanel.removeAll();

      for (int i = 0; i < vlist.size(); i++) {
         
         ItemBean ib = vlist.get(i);
         
            itemPanel = new JPanel();
            itemPanel.setLayout(null);
            itemPanel.setPreferredSize(new Dimension(1050, 200));
            itemPanel.setBorder(new LineBorder(Color.black,2,true));
            itemPanel.setBackground(Color.white);
            //itempanel안에 label 
          //이미지 리사이즈
            ImageIcon icon = new ImageIcon(Login.class.getResource("./image/"+ib.getItemName()+".jpg"));
            Image img = icon.getImage();
            Image changeImg = img.getScaledInstance(250, 180, Image.SCALE_SMOOTH);
            ImageIcon changeIcon = new ImageIcon(changeImg);
            
            itemPhoto = new JLabel(changeIcon);
            itemPhoto.setBounds(50,5, 250, 200);
            itemPhoto.setFont(new Font("맑은 고딕", Font.BOLD,15));
//            itemPhoto.setBorder(new LineBorder(Color.black,1,true));
            //
            itemName = new JLabel(ib.getItemName());
            itemName.setBounds(370, 25, 220, 50);
            itemName.setFont(new Font("맑은 고딕", Font.BOLD,15));
            itemName.setHorizontalAlignment(SwingConstants.CENTER);
            itemName.setOpaque(true);
            itemName.setBackground(txtColor);
            itemName.setForeground(lightGray);

            //
            auctionPrice = new JLabel(Integer.toString(ib.getItemPrice()) + " 원");
            auctionPrice.setBounds(370, 115, 220, 50);
            auctionPrice.setFont(new Font("맑은 고딕", Font.BOLD,15));
            auctionPrice.setHorizontalAlignment(SwingConstants.CENTER);
            auctionPrice.setOpaque(true);
            auctionPrice.setBackground(txtColor);
            auctionPrice.setForeground(lightGray);

            //시간 계산 
            int time = ib.getItemEndTime();
            
            int hour = time / (60*60);
            int minute = time / 60 - (hour*60);
            int second = time % 60;
            
            String reHour = Integer.toString(hour);
            String reMin = Integer.toString(minute);
            String reSec = Integer.toString(second);
      //
            auctionTime = new JLabel(reHour + ":" + reMin + ":" + reSec);
            auctionTime.setBounds(660, 25, 220, 50);
            auctionTime.setFont(new Font("맑은 고딕", Font.BOLD,15));
            auctionTime.setHorizontalAlignment(SwingConstants.CENTER);
            auctionTime.setOpaque(true);
            auctionTime.setBackground(txtColor);
            auctionTime.setForeground(lightGray);

           
            timerSet ts = new timerSet(auctionTime, time);
      //
            purchaserCount = new JLabel("참여 인원 : "+Integer.toString(ib.getPurchaserCount()) + " 명");
            purchaserCount.setBounds(660, 115, 220, 50);
            purchaserCount.setFont(new Font("맑은 고딕", Font.BOLD,15));
            purchaserCount.setHorizontalAlignment(SwingConstants.CENTER);
            purchaserCount.setOpaque(true);
            purchaserCount.setBackground(txtColor);
            purchaserCount.setForeground(lightGray);
      //
            JButton partBtn = new JButton("참여하기");
            partBtn.setBounds(960, 50, 100, 100);
            partBtn.setFont(new Font("맑은 고딕", Font.BOLD, 15));
            partBtn.setForeground(txtColor);
            partBtn.setBackground(Color.black);
            partBtn.setBorderPainted(false);
            partBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
            partBtn.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent e) {
               logId = memberId.getText();
                logId = logId.substring(logId.lastIndexOf(":")+1).trim();
               dispose();
               Auction auction = new Auction(logId, ib.getItemNum()) ;
               auction.setVisible(true);
            }
         });
            //
             itemPanel.add(itemName);
             itemPanel.add(itemPhoto);
             itemPanel.add(auctionPrice);
             itemPanel.add(auctionTime);
             itemPanel.add(partBtn);
             itemPanel.add(purchaserCount);
             
             itemPanel.setBounds(50, 50 + 200*i, 1050, 200);
             
             listPanel.add(itemPanel);
         }
}
   
   
   
   @Override
   public void actionPerformed(ActionEvent e) {
      
      logId = memberId.getText();
      logId = logId.substring(logId.lastIndexOf(":")+1).trim();
      Vector<ItemBean> vlist = new Vector<ItemBean>();
      
         Object obj = e.getSource();
         
         if(obj==backBtn) {
            //TODO
            try {
               dispose();
               Main main = new Main(logId);
               main.setVisible(true);
            } catch (Exception e2) {
               e2.printStackTrace();
            }
         }/*else {
            for (int i = 0; i < vlist.size(); i++) {
            ItemBean ibean = vlist.get(i);
            if (obj == partBtn[i]) {
                     try {
                        dispose();
                        Auction auction = new Auction(logId,ibean.getItemNum());
                        auction.setVisible(true);
                     } catch (Exception e2) {
                        e2.printStackTrace();
                     }
                  }
         }
         }*/
   }
   class timerSet implements Runnable{
         
         JLabel auctionTime ;
         int time;
         
         public timerSet(JLabel auctionTime, int time) {
            this.auctionTime = auctionTime;
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
                   
                   auctionTime.setText("남은 시간 : " +reHour + ":" + reMin + ":" + reSec);
                   Thread.sleep(1000);
                  
                } catch (InterruptedException e) {
                   // TODO Auto-generated catch block
                   e.printStackTrace();
                }
             }
            }
         }
   
   public static void main(String[] args) {
      
   }
}