package project1;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Container;
import java.awt.FileDialog;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Vector;
import java.util.Locale.Category;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;


public class ItemRegister extends JFrame implements ActionListener {

   JPanel itemPanel;
   JLabel logo,memberId, itemName, itemPhoto, itemCategory,itemmemo, itemprice, won, endtime;
   JTextField taName, taPrice;
   JTextArea taMemo;
   JButton registerBtn, backBtn, imageBtn;
   JComboBox<String> categoryCombobox, timeCombobox;
   TitledBorder registerTb;
   JOptionPane alarm = new JOptionPane();
   String[] categoryName = {"�����б��", "�Ƿ�","��Ȱ����","������/����","���/����/����","��Ƽ/�̿�","�ݷ�������ǰ","����/���׸���","����","����"};
   String[] timelist = {"60��", "10��"};
   FileDialog read;
   Image img;
   String filename;
   String dirfile;
   File file;
   ImageCanvas canvas; 
   FileReader reader;
   AuctionMgr mgr = new AuctionMgr();
   String logId;
   
   public ItemRegister(String logId) {
      setTitle("DaBID ��ǰ��� ������");
      setSize(1300, 900);
      setResizable(false);
      setLocationRelativeTo(null); //��� ���
      setLayout(null);
      Container c = getContentPane();
      
      //JPanel
      itemPanel = new JPanel();
      itemPanel.setBounds(60, 80, 1150, 700);
      itemPanel.setLayout(null);
      
      //TitledBorder
      registerTb = new TitledBorder(new LineBorder(Color.black, 1, true), "��ǰ ���");
      registerTb.setTitleFont(new Font("����ü", 0, 25));
      itemPanel.setBorder(registerTb);
      
      //JLabel
      memberId = new JLabel("���̵� : " + logId);
      memberId.setBounds(1150, 20, 150, 30);
      memberId.setFont(new Font("����ü", 0, 15));
      
      itemName = new JLabel("��ǰ�� :");
      itemName.setBounds(60 ,60, 100, 30);
      itemName.setFont(new Font("����ü", 0, 15));
      
      canvas = new ImageCanvas();
      canvas.setBounds(50, 120, 550, 470);
//      itemPhoto = new JLabel("�̹��� ���");
//      itemPhoto.setBorder(new LineBorder(Color.black, 1, true));
//      itemPhoto.setBounds(50, 120, 550, 470);
//      itemPhoto.setFont(new Font("����ü", 0, 20));
      
      itemCategory = new JLabel("ī�װ� :");
      itemCategory.setBounds(650, 410, 100, 30);
      itemCategory.setFont(new Font("����ü", 0, 17));
      
      itemprice = new JLabel("���� ���� :");
      itemprice.setBounds(650, 460, 100, 30);
      itemprice.setFont(new Font("����ü", 0, 17));
      
      itemmemo = new JLabel("��ǰ ����");
      itemmemo.setBounds(650, 80, 100, 30);
      itemmemo.setFont(new Font("����ü", 0, 17));
      
      won = new JLabel("��");
      won.setBounds(970, 460, 40, 30);
      won.setFont(new Font("����ü", 0, 17));
      
      endtime = new JLabel("���� �ð� :");
      endtime.setBounds(650, 510, 100, 30);
      endtime.setFont(new Font("����ü", 0, 17));
      
      //JTextArea
      taName = new JTextField(); //��ǰ��
      taName.setBounds(140, 65, 430, 20);
      
      taMemo = new JTextArea(); //��ǰ����
      taMemo.setBounds(650, 120, 450, 260);
      
      taPrice = new JTextField(); //���� ����
      taPrice.setBounds(750, 460, 200, 30);
      
      //JButton
      registerBtn = new JButton("��ǰ ���");
      registerBtn.setBounds(520, 630, 100, 40);
      registerBtn.setFont(new Font("����ü", 0, 15));
      registerBtn.addActionListener(this);
      backBtn = new JButton("�ڷΰ���");
      backBtn.setBounds(1150, 800, 120, 30);
      backBtn.setFont(new Font("����ü",0,15));
      backBtn.addActionListener(this);
      imageBtn = new JButton("�̹������");
      imageBtn.setBounds(510, 95, 90, 20);
      imageBtn.setFont(new Font("����ü",0,10));
      imageBtn.addActionListener(this);


      //JComboBox
      categoryCombobox = new JComboBox<String>(categoryName);
    
      categoryCombobox.setBounds(750, 410, 270, 30);
      
      timeCombobox = new JComboBox<>(timelist);
      timeCombobox.setBounds(750, 510, 100, 30);

      //�гο� �߰�
      itemPanel.add(itemmemo);
      itemPanel.add(itemName);
//      itemPanel.add(itemPhoto);
      itemPanel.add(itemCategory);
      itemPanel.add(itemprice);
      itemPanel.add(won);
      itemPanel.add(endtime);
      itemPanel.add(taName);
      itemPanel.add(taMemo);
      itemPanel.add(taPrice);
      itemPanel.add(registerBtn);
      itemPanel.add(categoryCombobox);
      itemPanel.add(timeCombobox);
      itemPanel.add(imageBtn);
      itemPanel.add(canvas);
      //���
      c.add(memberId);
      c.add(itemPanel);
      c.add(backBtn);
      validate();
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
      
      else if(obj==registerBtn) { //��ǰ ��� ��ư 
    	  if(taName.getText()==null || taPrice.getText()==null) {
    		  alarm.showMessageDialog(this, "��ǰ �̸� / ��ǰ ������ �Է��ϼ���!");
    	  }else {
          String itName = taName.getText(); //��ǰ��
          String itMemo = taMemo.getText(); //��ǰ ����
          Integer itPrice = Integer.parseInt(taPrice.getText()); //���� ����
          Integer categoryIdx = categoryCombobox.getSelectedIndex() + 1; //ī�װ� �ε���                
          Integer timeIdx = timeCombobox.getSelectedIndex(); //���� �ð� �ε���
          Integer time=0; //���� �ð� ���� ����
          if (timeIdx == 0) {
             time = 60;
             mgr.insertItem(logId, categoryIdx, itName, itPrice, file, itMemo, time);
          }else if (timeIdx == 1) {
             time = 10;
             mgr.insertItem(logId, categoryIdx, itName, itPrice, file, itMemo, time);
          }       
          alarm.showMessageDialog(null, "��ϿϷ�");
          try {
             dispose();
             Main main = new Main(logId);
             main.setVisible(true);
          } catch (Exception e2) {
             e2.printStackTrace();
          }
       }
      }
      
      else if(obj==imageBtn){ //�̹��� ��� ��ư
         if(read ==null) {
            read=new FileDialog(this,"�̹�������", FileDialog.LOAD);
         }
         read.setVisible(true);
         try {
            filename = read.getFile();
            dirfile = read.getDirectory() + File.separator + filename;
            file = new File(dirfile);
            img = ImageIO.read(file);
            canvas.repaint();
         } catch (IOException e1) {
            e1.printStackTrace();
         }
       }
 }
   
   
   //�̹��� �ֱ�
   class ImageCanvas extends Canvas{
      @Override
      public void paint(Graphics g) {
         g.drawImage(img, 0, 0,550,470, this);
      }

   }
   
   public static void main(String[] args) {
	   
   }
}