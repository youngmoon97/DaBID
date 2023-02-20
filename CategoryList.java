package project1;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
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
   
   Font f = new Font("����ü",0,15);
   
   public CategoryList(String category, String logId) {
      
     // mgr ��������
     mgr = new AuctionMgr();
     
     
      Container c = getContentPane();
      setTitle("DaBID ī�װ� ����Ʈ ������");
      setLocationRelativeTo(null);
       setSize(1300,900);
       setResizable(false);
       setLayout(null);
       
       listPanel = new JPanel();
       listPanel.setLayout(new GridLayout(0,1, 10, 10));
       catagoryListTb = new TitledBorder(new LineBorder(Color.black,1,true),"ī�װ� ����Ʈ");
       catagoryListTb.setTitleFont(new Font("����ü",0,25));
  
       //
       scroll = new JScrollPane(listPanel);
       scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
       scroll.setBounds(60, 80, 1150, 700);
       
       
      // main ������ �� ����
      categoryName = new JLabel(category);
      categoryName.setBounds(550,30,150,30);
      categoryName.setFont(new Font("����ü", 0, 20));
      
      Vector<ItemBean> vc = mgr.getItemList(category);
      System.out.println(category);
      
      memberId = new JLabel("���̵� : " + logId);
      memberId.setBounds(1150,20,200,30);
      memberId.setFont(new Font("����ü", 0, 15));
      
      backBtn = new JButton("�ڷΰ���");
      backBtn.setBounds(1150, 820, 120, 30);
      backBtn.setFont(new Font("����ü", 0, 15));
      backBtn.addActionListener(this);
      //
      logo = new JLabel(new ImageIcon(Login.class.getResource("./image/logo.png")));
      logo.setBounds(20,20,130,40);
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

      System.out.println(vlist.size());
      for (int i = 0; i < vlist.size(); i++) {
         
         ItemBean ib = vlist.get(i);
         
            itemPanel = new JPanel();
            itemPanel.setLayout(null);
            itemPanel.setPreferredSize(new Dimension(1050, 200));
            itemPanel.setBorder(new LineBorder(Color.black,1,true));
            //itempanel�ȿ� label / ��ư �ֱ�
            itemPhoto = new JLabel(/*new ImageIcon(Login.class.getResource("./image/"+ib.getItemName()+".jpg"))*/);
            itemPhoto.setBounds(0,0, 250, 200);
            itemPhoto.setFont(new Font("����ü", 0, 15));
            itemPhoto.setBorder(new LineBorder(Color.black,1,true));
            //
            itemName = new JLabel(ib.getItemName());
            itemName.setBounds(300, 30, 220, 50);
            itemName.setFont(new Font("����ü", 0, 15));
            itemName.setHorizontalAlignment(SwingConstants.CENTER);
            itemName.setBorder(new LineBorder(Color.black,1,true));
            //
            auctionPrice = new JLabel(Integer.toString(ib.getItemPrice()) + " ��");
            auctionPrice.setBounds(300, 120, 220, 50);
            auctionPrice.setFont(new Font("����ü", 0, 15));
            auctionPrice.setHorizontalAlignment(SwingConstants.CENTER);
            auctionPrice.setBorder(new LineBorder(Color.black,1,true));
            //
            int time = ib.getItemEndTime();
            
            int hour = time / (60*60);
            int minute = time / 60 - (hour*60);
            int second = time % 60;
            
            String reHour = Integer.toString(hour);
            String reMin = Integer.toString(minute);
            String reSec = Integer.toString(second);
            
            
            auctionTime = new JLabel(reHour + ":" + reMin + ":" + reSec);
            auctionTime.setBounds(600, 30, 220, 50);
            auctionTime.setFont(new Font("����ü", 0, 15));
            auctionTime.setHorizontalAlignment(SwingConstants.CENTER);
            auctionTime.setBorder(new LineBorder(Color.black,1,true));
      //
            purchaserCount = new JLabel(Integer.toString(ib.getPurchaserCount()) + " ��");
            purchaserCount.setBounds(600, 120, 220, 50);
            purchaserCount.setFont(new Font("����ü", 0, 15));
            purchaserCount.setHorizontalAlignment(SwingConstants.CENTER);
            purchaserCount.setBorder(new LineBorder(Color.black,1,true));
      //
            JButton partBtn = new JButton("�����ϱ�");
            partBtn.setBounds(950, 50, 100, 100);
            partBtn.setFont(new Font("����ü", 0, 15));
            partBtn.setBorder(new LineBorder(Color.black,1,true));
            partBtn.addActionListener(this);
            ib.setPartBtn(partBtn);
       
            
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
         }
   }
   
   public static void main(String[] args) {
      
   }
}