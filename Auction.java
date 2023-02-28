package project1;

import java.awt.Color;
import java.awt.Container;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Image;
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
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import project1.CategoryList.timerSet;

public class Auction extends JFrame
implements ActionListener{
   
   JPanel itemPanel,commentPanel;
   JLabel logo, memberId, itemName, itemPhoto, itemMemo, currentPrice, purchaserCount, auctionPriceLbl, auctionTime;
   JTextArea commentArea;
   JScrollPane commentScroll;
   JTextPane commentPane;
   JTextField commentTf, bidpriceTf;
   JButton commentBtn, auctionBtn, backBtn; 
   TitledBorder itemTb, commentTb;
   JOptionPane alarm = new JOptionPane();
   String logId;   // �������� ���������� �ϱ�
   Color lightGray = new Color(0,0,0);
   Color txtColor = new Color(240,240,240); //��� ���� ����
   Font f = new Font("���� ���", Font.BOLD,13);
   Font f2 = new Font("���� ���", Font.BOLD, 15);
   
   public Auction(String logId, int itemNum) {
      setTitle("DaBID ���������");
       setSize(1300,900);
       setResizable(false);
       setLayout(null);
       setLocationRelativeTo(null); //��� ���
       int itemNum2 = itemNum;
       Container c = getContentPane();
       
       c.setBackground(Color.white);
       
       itemTb = new TitledBorder(new LineBorder(Color.black,2,true),"��ǰ����");
       commentTb = new TitledBorder(new LineBorder(Color.black,2,true),"��� �� ����");       
       itemTb.setTitleFont(new Font("���� ���",Font.BOLD,25));
       commentTb.setTitleFont(new Font("���� ���",Font.BOLD,25));
       //���� �г� ( ��ǰ ����)
       itemPanel = new JPanel();
       itemPanel.setLayout(null);
       itemPanel.setBounds(60, 80, 550, 700);
       itemPanel.setBorder(itemTb);
       itemPanel.setBackground(Color.white);
       
       //memberid
       memberId = new JLabel("���̵� : "+logId);
       memberId.setBounds(1150,20,150,30);
       memberId.setFont(new Font("���� ���", 0, 15));
        
       AuctionMgr mgr = new AuctionMgr();
       ItemBean ibean = mgr.getItem(itemNum);
        //��ǰ���̺�
       itemName = new JLabel("��ǰ�� : " + ibean.getItemName());
       itemName.setBounds(60, 60, 180, 30);
       itemName.setFont(new Font("���� ���", Font.BOLD, 17));
      
       //��ǰ�̹���
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
       itemMemo.setFont(new Font("���� ���", Font.BOLD,12));
       itemMemo.setBounds(60, 470, 430, 130);
       itemMemo.setHorizontalAlignment(JLabel.CENTER);
        //��ǰ����������
       currentPrice= new JLabel("���� ������ : " + ibean.getItemPrice()+"��");
       currentPrice.setBorder(new LineBorder(txtColor,1,true));
       currentPrice.setBounds(60, 610, 205, 50);
       currentPrice.setFont(f2);
       currentPrice.setOpaque(true);
       currentPrice.setBackground(txtColor);
       currentPrice.setForeground(lightGray);
       currentPrice.setHorizontalAlignment(JLabel.CENTER);
        //���������ο� 
       purchaserCount= new JLabel("���� ���� �ο� :  "+ibean.getPurchaserCount()+"��");        
       purchaserCount.setBorder(new LineBorder(txtColor,1,true));
       purchaserCount.setBounds(285, 610, 205, 50);
       purchaserCount.setFont(f2);
       purchaserCount.setOpaque(true);
       purchaserCount.setBackground(txtColor);
       purchaserCount.setForeground(lightGray);
       purchaserCount.setHorizontalAlignment(JLabel.CENTER);
       //
       itemPanel.add(itemName);
       itemPanel.add(itemMemo);
       itemPanel.add(itemPhoto);
       itemPanel.add(purchaserCount);
       itemPanel.add(currentPrice);
       
       //������ �г�(���+����)
       commentPanel = new JPanel();
       commentPanel.setLayout(null);
       commentPanel.setBounds(660, 80, 550, 700);
       commentPanel.setBorder(commentTb);
       commentPanel.setBackground(Color.white);
       //
       commentPane = new JTextPane();
       commentPane.setEditable(false);
       commentPane.setContentType("text/html");
       commentScroll = new JScrollPane(commentPane);
       commentScroll.setBounds(60, 90, 430, 450);
       commentScroll.setBorder(new LineBorder(lightGray,1,true));       

       commentPanel.add(commentScroll);
       //textarea
       
       /*commentArea = new JTextArea();
       commentArea.setBounds(60, 90, 430, 450);
       commentArea.setBorder(new LineBorder(lightGray,1,true));       
       commentArea.setEnabled(false);
       */
       //���� ��� ��������
       Vector<CommentBean> clist = mgr.getCommentList(ibean.getItemNum());
       
       for (int i = 0; i < clist.size(); i++) {
          
          CommentBean cbean = clist.get(i);
          if(cbean.getCommentContent()==null) {
             commentArea.append("���� ����� �����ϴ�.\n");
          }else {
            Date time = cbean.getCommentTime();
            String purchaserId = cbean.getPurchaserId();
            String sellerId = cbean.getSellerId();
            String comment = cbean.getCommentContent();
            String color = "black";

            if(purchaserId.equals(sellerId)) { //�Ǹ���
            	color = "blue";
            }
            String str = "<font color='" + color + "'>" + purchaserId + " : "+comment + "</font><br>";
            commentPane.setText(editStr(commentPane.getText()+str));
          }
      }
     //�ð� ��� 
        int time = ibean.getItemEndTime();
        
        int hour = time / (60*60);
        int minute = time / 60 - (hour*60);
        int second = time % 60;
        
        String reHour = Integer.toString(hour);
        String reMin = Integer.toString(minute);
        String reSec = Integer.toString(second);
  //
        auctionTime = new JLabel("���� �ð� : " +reHour + ":" + reMin + ":" + reSec);
       auctionTime.setBounds(340,50,150,30);
       auctionTime.setBorder(new LineBorder(txtColor,1,true));
       auctionTime.setForeground(lightGray);
       auctionTime.setFont(f2);
       auctionTime.setOpaque(true);
       auctionTime.setBackground(txtColor);
       auctionTime.setHorizontalAlignment(JLabel.CENTER);
       timerSet ts = new timerSet(auctionTime, time);
       //comment����Է�
       commentTf = new JTextField();
       commentTf.setBounds(60, 540, 360, 30);
       commentTf.setBorder(new LineBorder(txtColor,1,true));
       commentTf.setFont(new Font("���� ���", 0, 13));
       commentTf.setBackground(txtColor);
       commentTf.addActionListener(this);
       commentTf.requestFocus();
       //comment��ư����
       commentBtn = new JButton("����");
       commentBtn.setBounds(420, 540, 70, 30);
       commentBtn.setBorder(new LineBorder(Color.black,1,true));
       commentBtn.setFont(f);
       commentBtn.setForeground(Color.white);
       commentBtn.setBackground(Color.black);
       commentBtn.setBorderPainted(false);
       commentBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));

       commentBtn.addActionListener(new ActionListener() {
         
         @Override
         public void actionPerformed(ActionEvent e) {
            if(commentTf.getText().isEmpty()) {
                alarm.showMessageDialog(null, "����� �Է��ϼ���.");
             }else {
                String comment = commentTf.getText();
                String seller = ibean.getItemSeller();
                int itemNum = ibean.getItemNum();
                String color  = "black";
                if(seller.equals(logId)) {
                	color="blue";
                }           
                String str = "<font color='" + color + "'>" + logId + " : "+ comment + "</font><br>";
                LocalDate now = LocalDate.now();
                commentPane.setText(editStr(commentPane.getText()+str));
                //commentArea.append(now+"\n"+logId +" : "+comment+"\n");
                //��� ����
                
                mgr.insertComment(seller, logId, itemNum, comment);
               
                commentTf.setText(" ");
             }
         }
      });
       //�������ݷ��̺�
       auctionPriceLbl = new JLabel("��������");
       auctionPriceLbl.setBounds(220, 620, 80, 30);
       auctionPriceLbl.setBorder(new LineBorder(Color.black,1,true));
       auctionPriceLbl.setForeground(Color.white);
       auctionPriceLbl.setHorizontalAlignment(JLabel.CENTER);
       auctionPriceLbl.setFont(f);
       auctionPriceLbl.setOpaque(true);
       auctionPriceLbl.setBackground(Color.black);
       //��������TF
       bidpriceTf = new JTextField();
       bidpriceTf.setBounds(300, 620, 120, 30);
       bidpriceTf.setBorder(new LineBorder(Color.black,1,true));
       bidpriceTf.setBackground(txtColor);
       bidpriceTf.setBorder(new LineBorder(txtColor));
       //������ư
       auctionBtn = new JButton("�����ϱ�");
       auctionBtn.setBounds(420, 620, 70, 30);
       auctionBtn.setBorder(new LineBorder(Color.black,1,true));
       auctionBtn.setFont(f);
       auctionBtn.setForeground(Color.white);
       auctionBtn.setBackground(Color.black);
       auctionBtn.setBorderPainted(false);
       auctionBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
       auctionBtn.addActionListener(new ActionListener() {
         
         @Override
         public void actionPerformed(ActionEvent e) {
            int itemNum = ibean.getItemNum();
            if(ibean.getItemSeller().equals(logId)) {
               alarm.showMessageDialog(null, "�ڽ��� ��ǰ�� �������� ���մϴ�.");
            }else {
               if(bidpriceTf.getText().equals("")) {
                        alarm.showMessageDialog(null, "������ �Է��ϼ���.");
                        bidpriceTf.requestFocus();
                     }else {
                        int itemPrice = Integer.parseInt(bidpriceTf.getText());
                        if (itemPrice > ibean.getItemPrice()) {
                           mgr.insertAuction(itemNum, itemPrice, logId);
                           alarm.showMessageDialog(null, "���� ����!");
                           
                           try {
                              dispose();
                              Main main = new Main(logId);
                              main.setVisible(true);
                           } catch (Exception e2) {
                              // TODO: handle exception
                           }
                        }else {
                           alarm.showMessageDialog(null, "������ ���簡 �̻����� �Է����ּ���.");
                           bidpriceTf.setText("");
                           bidpriceTf.requestFocus();
                        }
                     }
            }
               
               
         }
      });
       //
       //commentPanel.add(commentArea);
       commentPanel.add(auctionTime);
       commentPanel.add(commentTf);
       commentPanel.add(commentBtn); 
       commentPanel.add(auctionBtn);
       commentPanel.add(auctionPriceLbl); 
       commentPanel.add(bidpriceTf);
       //
       logo = new JLabel(new ImageIcon(Login.class.getResource("./image/logo.png")));
       logo.setBounds(20,25,130,40);
       //
       backBtn = new JButton("�ڷΰ���");
       backBtn.setBounds(1150, 820, 120, 30);
       backBtn.setBorder(new LineBorder(Color.black,1,true));
       backBtn.setFont(f);
       backBtn.setForeground(txtColor);
       backBtn.setBackground(Color.black);
       backBtn.setBorderPainted(false);
       backBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
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
      
      if(obj==backBtn) {
         try {
            dispose();
            Main main = new Main(logId);
            main.setVisible(true);
         } catch (Exception e2) {
            e2.printStackTrace();
         }
      }
   }
   public String editStr(String str) {
		str =  str.replace("<html>", "");
		str =  str.replace("<head>", "");
		str =  str.replace("</head>", "");
		str =  str.replace("<body>", "");
		str =  str.replace("</body>", "");
		str =  str.replace("</html>", "");
		System.out.println(str);
		return str;
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
		/*
		 * try { Auction a = new Auction("aaa", 65); a.setVisible(true); } catch
		 * (Exception e) { // TODO: handle exception }
		 */
   }
}