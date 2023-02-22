package project1;

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
   String[] categoryName = {"디지털기기", "의류","생활가전","스포츠/레저","취미/게임/음반","뷰티/미용","반려동물용품","가구/인테리어","차량","도서"};
   String[] timelist = {"5분", "60분"};
   FileDialog read;
   Image img;
   String filename;
   String dirfile;
   File file;
   ImageCanvas canvas; 
   FileReader reader;
   AuctionMgr mgr = new AuctionMgr();
   String logId;
   Color color = new Color(240,240,240);
   
   public ItemRegister(String logId) {
      setTitle("DaBID 상품등록 페이지");
      setSize(1300, 900);
      setResizable(false);
      setLocationRelativeTo(null); //가운데 출력
      setLayout(null);
      Container c = getContentPane();
      
      //JPanel
      itemPanel = new JPanel();
      itemPanel.setBounds(60, 80, 1150, 700);
      itemPanel.setLayout(null);
      
      //TitledBorder
      registerTb = new TitledBorder(new LineBorder(Color.black, 2, true), "상품 등록");
      registerTb.setTitleFont(new Font("맑은 고딕", Font.BOLD, 25));
      itemPanel.setBorder(registerTb);
      
      //JLabel
      memberId = new JLabel("아이디 : " + logId);
      memberId.setBounds(1150, 20, 150, 30);
      memberId.setFont(new Font("맑은 고딕", 0, 15));
      
      itemName = new JLabel("상품명 :");
      itemName.setBounds(60 ,63, 100, 30);
      itemName.setFont(new Font("맑은 고딕", Font.BOLD , 17));
      
      canvas = new ImageCanvas();
      canvas.setBounds(60, 120, 510, 540);
//      itemPhoto = new JLabel("이미지 등록");
//      itemPhoto.setBorder(new LineBorder(Color.black, 1, true));
//      itemPhoto.setBounds(50, 120, 550, 470);
//      itemPhoto.setFont(new Font("맑은 고딕", 0, 20));
      
      itemCategory = new JLabel("카테고리 :");
      itemCategory.setBounds(650, 410, 100, 30);
      itemCategory.setFont(new Font("맑은 고딕", Font.BOLD, 17));
      
      itemprice = new JLabel("시작 가격 :");
      itemprice.setBounds(650, 460, 100, 30);
      itemprice.setFont(new Font("맑은 고딕", Font.BOLD,17));
      
      itemmemo = new JLabel("상품 설명");
      itemmemo.setBounds(650, 63, 100, 30);
      itemmemo.setFont(new Font("맑은 고딕", Font.BOLD, 17));
      
      won = new JLabel("원");
      won.setBounds(970, 460, 40, 30);
      won.setFont(new Font("맑은 고딕", Font.BOLD, 17));
      
      endtime = new JLabel("종료 시간 :");
      endtime.setBounds(650, 510, 100, 30);
      endtime.setFont(new Font("맑은 고딕", Font.BOLD, 17));
      
      //JTextArea
      taName = new JTextField(); //상품명
      taName.setBounds(140, 67, 430, 25);
      
      taMemo = new JTextArea(); //상품설명
      taMemo.setBounds(650, 100, 450, 280);
      
      taPrice = new JTextField(); //시작 가격
      taPrice.setBounds(750, 460, 200, 30);
      
      //JButton
      registerBtn = new JButton("상품 등록");
      registerBtn.setBounds(1060, 680, 100, 40);
      registerBtn.setFont(new Font("맑은 고딕", Font.BOLD, 15));
      registerBtn.addActionListener(this);
      backBtn = new JButton("뒤로가기");
      backBtn.setBounds(1150, 820, 120, 30);
      backBtn.setFont(new Font("맑은 고딕",Font.BOLD,15));
      backBtn.addActionListener(this);
      imageBtn = new JButton("이미지등록");
      imageBtn.setBounds(540, 180, 90, 30);
      imageBtn.setFont(new Font("맑은 고딕",Font.BOLD,10));
      imageBtn.addActionListener(this);

      //JComboBox
      categoryCombobox = new JComboBox<String>(categoryName);
      categoryCombobox.setBounds(750, 410, 270, 30);
      timeCombobox = new JComboBox<>(timelist);
      timeCombobox.setBounds(750, 510, 100, 30);

      //패널에 추가
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
      //디자인
      //배경색
      c.setBackground(Color.white);
      itemPanel.setBackground(Color.white);
      //textField 디자인
      taName.setBackground(color);
      taName.setBorder(javax.swing.BorderFactory.createEmptyBorder());
      taMemo.setBackground(color);
      taMemo.setBorder(javax.swing.BorderFactory.createEmptyBorder());
      taPrice.setBackground(color);
      taPrice.setBorder(javax.swing.BorderFactory.createEmptyBorder());
      //이미지버튼 디자인
      imageBtn.setBackground(Color.black);
      imageBtn.setForeground(Color.white);
      imageBtn.setBorderPainted(false);
      imageBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
      this.add(imageBtn);
      //상품등록버튼 디자인
      registerBtn.setBackground(Color.black);
      registerBtn.setForeground(Color.white);
      registerBtn.setBorderPainted(false);
      registerBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
      this.add(registerBtn);
      //버튼 위 커서 모양 변경
      backBtn.setBackground(Color.black);
      backBtn.setForeground(Color.white);
      backBtn.setBorderPainted(false);
      backBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
      this.add(backBtn);
      //출력
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
      
      else if(obj==registerBtn) { //상품 등록 버튼 
    	  if(taName.getText()==null || taPrice.getText()==null) {
    		  alarm.showMessageDialog(this, "상품 이름 / 상품 가격을 입력하세요!");
    	  }else {
          String itName = taName.getText(); //상품명
          String itMemo = taMemo.getText(); //상품 설명
          Integer itPrice = Integer.parseInt(taPrice.getText()); //시작 가격
          Integer categoryIdx = categoryCombobox.getSelectedIndex() + 1; //카테고리 인덱스                
          Integer timeIdx = timeCombobox.getSelectedIndex(); //종료 시간 인덱스
          Integer time=0; //종료 시간 설정 변수
          if (timeIdx == 0) {
             time = 5;
             mgr.insertItem(logId, categoryIdx, itName, itPrice, file, itMemo, time);
          }else if (timeIdx == 1) {
             time = 60;
             mgr.insertItem(logId, categoryIdx, itName, itPrice, file, itMemo, time);
          }       
          alarm.showMessageDialog(null, "등록완료");
          try {
             dispose();
             Main main = new Main(logId);
             main.setVisible(true);
          } catch (Exception e2) {
             e2.printStackTrace();
          }
       }
      }
      
      else if(obj==imageBtn){ //이미지 등록 버튼
         if(read ==null) {
            read=new FileDialog(this,"이미지열기", FileDialog.LOAD);
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
   
   
   //이미지 넣기
   class ImageCanvas extends Canvas{
      @Override
      public void paint(Graphics g) {
         g.drawImage(img, 5, 20,500,500, this);
      }

   }
   
   public static void main(String[] args) {
	   try {
		ItemRegister ir = new ItemRegister("aaa");
		ir.setVisible(true);
	} catch (Exception e) {
		// TODO: handle exception
	}
   }
}