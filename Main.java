package project1;

import java.awt.Button;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Container;
import java.awt.Cursor;
import java.awt.FileDialog;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.security.PublicKey;
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
   Color color = new Color(240,240,240);
   Color lightGray = new Color(0,0,0);
   
   public Main(String logId) {
        setTitle("DaBID ����������");
        setSize(1300,900);
        setResizable(false);
        setLocationRelativeTo(null); //��� ���
        setLayout(null);
        Container c = getContentPane();
        hotbidTb = new TitledBorder(new LineBorder(Color.black,2,true),"HOT BID");
        commentTb = new TitledBorder(new LineBorder(Color.black,2,true),"ī�װ�");
        hotbidTb.setTitleFont(new Font("���� ���",Font.BOLD,25));
        commentTb.setTitleFont(new Font("���� ���",Font.BOLD,25));
        //���� �г� ( ��ǰ ����)
        itemPanel = new JPanel();
        itemPanel.setLayout(null);
        itemPanel.setBounds(60, 80, 550, 700);
        itemPanel.setBorder(hotbidTb);   
   
        ItemBean ibean = mgr.getHotItem();
        // ��ǰ���̺�
        itemName = new JLabel("��ǰ�� : " + ibean.getItemName());
        itemName.setBounds(60, 60, 400, 30);
        itemName.setFont(new Font("���� ���", Font.BOLD, 17));
        //�̹��� ��������
        ImageIcon icon = new ImageIcon(Login.class.getResource("./image/"+ibean.getItemName()+".jpg"));
        Image img = icon.getImage();
        Image changeImg = img.getScaledInstance(430, 370, Image.SCALE_SMOOTH);
        ImageIcon changeIcon = new ImageIcon(changeImg);
        
        itemPhoto = new JLabel(changeIcon);
       itemPhoto.setBounds(60, 90, 430, 370);
        //��ǰ����
        itemMemo= new JLabel(ibean.getItemMemo());
        itemMemo.setBorder(new LineBorder(Color.black,1,true));
        itemMemo.setHorizontalAlignment(JLabel.CENTER);
        itemMemo.setBounds(60, 470, 430, 100);
        itemMemo.setFont(new Font("���� ���",Font.BOLD,12));

        // ��ǰ����������
        currentPrice= new JLabel(" ���� ���� : " + ibean.getItemPrice()+" ��");
        currentPrice.setHorizontalAlignment(JLabel.CENTER);
        currentPrice.setBounds(60, 580, 200, 50);
        currentPrice.setFont(new Font("���� ���", Font.BOLD, 15));
        //��ǰ�ð�
        int time = ibean.getItemEndTime();
        int hour = time / (60*60);
        int minute = time / 60 - (hour*60);
        int second = time % 60;
        
        String reHour = Integer.toString(hour);
        String reMin = Integer.toString(minute);
        String reSec = Integer.toString(second);
        
        itemTime = new JLabel("���� �ð� : " +reHour+":"+reMin+":"+reSec);
        itemTime.setBounds(60, 630, 300, 50);
        itemTime.setFont(new Font("���� ���", Font.BOLD, 17));
        
        timerSet ts = new timerSet(itemTime, time);
        // ���������ο� 
        purchaserCount= new JLabel(" ���� ���� �ο� : " + ibean.getPurchaserCount()+"��");
        purchaserCount.setHorizontalAlignment(JLabel.CENTER);
        purchaserCount.setBounds(280, 580, 210, 50);
        purchaserCount.setFont(new Font("���� ���",Font.BOLD, 15));
      //���Ѱ�� ������ư 
        hotBidBtn = new JButton("�����ϱ�");
        hotBidBtn.setBounds(440, 725, 110, 30);
        hotBidBtn.setFont(new Font("���� ���", Font.BOLD, 13));
        hotBidBtn.addActionListener(this);
        ////
        itemPanel.add(itemName);
        itemPanel.add(itemTime);
        itemPanel.add(itemPhoto);
        itemPanel.add(itemMemo);
        itemPanel.add(purchaserCount);
        itemPanel.add(currentPrice);
        itemPanel.add(hotBidBtn);
        //������ �г�(ī�װ�)
        categoryPanel = new JPanel();
        categoryPanel.setLayout(null);
        categoryPanel.setBounds(660, 80, 550, 350);
        categoryPanel.setBorder(commentTb);
        //ī�װ� �ֱ�
        
       Vector<CategoryBean> clist = mgr.getCategory();
      
       for (int i = 0; i < clist.size(); i++) {
         CategoryBean cbean = clist.get(i);
         categoryNameBtn[i] = new Button(cbean.getCategoryName());
         int x=33;
          int y=65;
          int  w= 140; //����
          int h = 40; //����
           categoryNameBtn[i].setBounds(x+(i%3)*(w+x),y+(i/3)*70,w,h);
           categoryNameBtn[i].setFont(new Font("���� ���", Font.BOLD, 15));
           categoryNameBtn[i].addActionListener(this);
           categoryNameBtn[i].setBackground(color);
           categoryNameBtn[i].setCursor(new Cursor(Cursor.HAND_CURSOR));
           //add
           this.add(categoryNameBtn[i]);
           categoryPanel.add(categoryNameBtn[i]);     
        }
        //��ư 2��+�̸����̺�
        manegerBtn = new JButton("�����ڸ��");
        backBtn = new JButton("������");
        registerBtn = new JButton("��ǰ���");
        memberId = new JLabel("���̵� : " + logId);
        
        manegerBtn.setBounds(1000, 820, 120, 30);
        backBtn.setBounds(1150, 820, 120, 30);
        registerBtn.setBounds(1000, 20, 100, 30);
        memberId.setBounds(1150,20,150,30);
        
        manegerBtn.setFont(new Font("���� ���", Font.BOLD, 13));
        backBtn.setFont(new Font("���� ���", Font.BOLD, 13));
        registerBtn.setFont(new Font("���� ���", Font.BOLD, 13));
        memberId.setBounds(1150,20,150,30);
        memberId.setFont(new Font("���� ���", 0, 15));
        
        manegerBtn.addActionListener(this);
        backBtn.addActionListener(this);
        registerBtn.addActionListener(this);
        //logo
        logo = new JLabel(new ImageIcon(Login.class.getResource("./image/logo.png")));
        logo.setBounds(20,25,130,40);
        
        //mypagebtn
        myPageBtn = new JButton("����������");
        myPageBtn.setBounds(1140, 55, 100,30);
        myPageBtn.setFont(new Font("���� ���",Font.BOLD,12));
        myPageBtn.addActionListener(this);
      //������
        c.setBackground(Color.white);
        itemPanel.setBackground(color.white);
        categoryPanel.setBackground(color.white);
        //�� ������
        purchaserCount.setOpaque(true);
        purchaserCount.setBackground(color);
        purchaserCount.setForeground(lightGray);
        currentPrice.setOpaque(true);
        currentPrice.setBackground(color);
        currentPrice.setForeground(lightGray);
        //���������� ��ư ������
        myPageBtn.setBackground(Color.black);
        myPageBtn.setForeground(Color.white);
        myPageBtn.setBorderPainted(false);
        myPageBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        this.add(myPageBtn);
        //��ǰ��� ��ư ������
        registerBtn.setBackground(Color.black);
        registerBtn.setForeground(Color.white);
        registerBtn.setBorderPainted(false);
        registerBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        this.add(registerBtn);
        //�����ڸ�� ��ư ������
        manegerBtn.setBackground(Color.black);
        manegerBtn.setForeground(Color.white);
        manegerBtn.setBorderPainted(false);
        manegerBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        this.add(manegerBtn);
        //�ڷΰ��� ��ư ������
        backBtn.setBackground(Color.black);
        backBtn.setForeground(Color.white);
        backBtn.setBorderPainted(false);
        backBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        this.add(backBtn);
        //���� ��� �����ϱ� ��ư ������
        hotBidBtn.setBackground(Color.black);
        hotBidBtn.setForeground(Color.white);
        hotBidBtn.setBorderPainted(false);
        hotBidBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        this.add(hotBidBtn);
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
	      if(obj==registerBtn/*��ǰ���*/) {
	          try {
	            dispose();
	            ItemRegister ir = new ItemRegister(logId);
	            ir.setVisible(true);
	         } catch (Exception e2) {
	            e2.printStackTrace();
	         }
	      }else if(obj==manegerBtn/*������*/){   
	         //TODO position �˻�
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
	            alarm.showMessageDialog(null, "������ �����ϴ�.");
	         }
	      }else if(obj==backBtn) {//�ڷΰ���
	    	  int yesOrNo  = JOptionPane.showConfirmDialog(null, "�����������Դϴ�.\n�α��� â���� �����ðڽ��ϱ�?","������", JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);
	         if(yesOrNo==0) {
	        	 try {
	        		 dispose();
					Login login = new Login();
					login.setVisible(true);
				} catch (Exception e2) {
					// TODO: handle exception
				}
	         }
	      }else if(obj==myPageBtn) {//����������
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
	      }else if(obj==hotBidBtn) {//����������� �̵�
	         try {
	            dispose();
	            AuctionMgr mgr = new AuctionMgr();
	            ItemBean ibean= mgr.getHotItem();
	            Auction auc = new Auction(logId,ibean.getItemNum());
	            auc.setVisible(true);
	         } catch (Exception e2) {
	            e2.printStackTrace();
	         }
	      } else {//ī���ڸ� ����Ʈ �̸� ��� --> ����
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
	             
	             itemTime.setText("���� �ð� : " +reHour + ":" + reMin + ":" + reSec);
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