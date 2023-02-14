package project1;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

public class Mypage extends JFrame
implements ActionListener{
   
   private JFrame frame = new JFrame();
   
   JPanel myPagePanel, particPanel;
   JLabel logo, memberId, nowPwlbl, newPwlbl, newCheckPwlbl, particPhoto, particName, particPrice, particMemo
   ;
   JPasswordField nowPw, newPw, newCheckPw;
   JButton saveBtn, itemDeleteBtn, backBtn;
   TitledBorder soonTb, buyTb, sellTb, pwTb;
   JScrollPane particScroll;
   
   String myPageCt[] = {"경매 참여 상품", "구매한 상품", "판매한 상품", "비밀번호 변경"};
   JComboBox<String> myPageCb = new JComboBox<String>(myPageCt);
   
   public Mypage() {
      setTitle("DaBID 마이페이지");
      setSize(1300, 900);
        setResizable(false);
        setLocationRelativeTo(null); //가운데 출력
        getContentPane().setLayout(null);
        Container c = getContentPane();
        
        //panel 지정
        myPagePanel = new JPanel();
        myPagePanel.setBounds(180, 70, 1000, 700);
        myPagePanel.setLayout(null);
        
        // border 지정
        soonTb = new TitledBorder(new LineBorder(Color.black,1,true),"판매 예정");
        soonTb.setTitleFont(new Font("돋움체",0,15));
        
        buyTb = new TitledBorder(new LineBorder(Color.black,1,true),"구매 완료");
        buyTb.setTitleFont(new Font("돋움체",0,15));
        
        sellTb = new TitledBorder(new LineBorder(Color.black,1,true),"판매 완료");
        sellTb.setTitleFont(new Font("돋움체",0,15));
        
        pwTb = new TitledBorder(new LineBorder(Color.black,1,true),"비밀번호 변경");
        pwTb.setTitleFont(new Font("돋움체",0,15));
        
        
        // 마이페이지 콤보박스 combobox 
        myPageCb.setBounds(20, 85, 150, 40);
        myPageCb.setFont(new Font("돋움체",0,17));
        myPageCb.addActionListener(this);
        
        // index 0 (경매 참여 상품)
        // 경매 참여 리스트 particPanel
        particPanel = new JPanel();
        particPanel.setLayout(null);
        particPanel.setBounds(10, 20, 940, 300);
        particPanel.setBorder(new LineBorder(Color.black, 1, true));
        
        // 경매 참여 리스트 스크롤 particScroll
        particScroll = new JScrollPane(myPagePanel);
        particScroll.setBounds(1127,16,20,682);
        
        // 상품 정보 label
        particPhoto = new JLabel("상품 이미지");
        particName = new JLabel("상품 이름");
        particPrice = new JLabel("상품 가격");
        particMemo = new JLabel("상품 설명");
        
        particPhoto.setBounds(30, 50, 250, 200);
        particName.setBounds(300, 50, 290, 60);
        particPrice.setBounds(600, 50, 210, 60);
        particMemo.setBounds(300, 120, 510, 130);
        
        
        particPhoto.setFont(new Font("돋움체", 0, 18));
        particName.setFont(new Font("돋움체", 0, 18));
        particPrice.setFont(new Font("돋움체", 0, 18));
        particMemo.setFont(new Font("돋움체", 0, 18));
        
        particPhoto.setBorder(new LineBorder(Color.black,1,true));
        particName.setBorder(new LineBorder(Color.black,1,true));
        particPrice.setBorder(new LineBorder(Color.black,1,true));
        particMemo.setBorder(new LineBorder(Color.black,1,true));

        
        // 상품 삭제 버튼
        itemDeleteBtn = new JButton("상품삭제");
        itemDeleteBtn.setBounds(830, 100, 100, 100);
        itemDeleteBtn.setFont(new Font("돋움체", 0, 16));
       
        
        
        // index 4 (비밀번호 변경)
        // 현재 비밀번호, 새 비밀번호, 비밀번호 확인 label
        nowPwlbl = new JLabel("현재 비밀번호");
        newPwlbl = new JLabel("새 비밀번호");
        newCheckPwlbl = new JLabel("비밀번호 확인");
        
        nowPwlbl.setBounds(200, 170, 190, 30);
        newPwlbl.setBounds(200, 320, 190, 30);
        newCheckPwlbl.setBounds(200, 470, 190, 30);
        
        nowPwlbl.setFont(new Font("돋움체", 1, 21));
        newPwlbl.setFont(new Font("돋움체", 1, 21));
        newCheckPwlbl.setFont(new Font("돋움체", 1, 21));
        
        // 현재 비밀번호, 새비밀번호, 비밀번호 확인 JPasswordField
        nowPw = new JPasswordField();
        newPw = new JPasswordField();
        newCheckPw = new JPasswordField();
        
        nowPw.setBounds(390, 170, 300, 30);
        newPw.setBounds(390, 320, 300, 30);
        newCheckPw.setBounds(390, 470, 300, 30);
        
        nowPw.setFont(new Font("돋움체", 0, 21));
        newPw.setFont(new Font("돋움체", 0, 21));
        newCheckPw.setFont(new Font("돋움체", 0, 21));
        
        // save 버튼
        saveBtn = new JButton("변경");
        saveBtn.setBounds(850,600, 80, 30);
        saveBtn.setFont(new Font("돋움체", 0, 20));

        // 기본
        // logo
        logo = new JLabel("DaBID");
        logo.setBounds(20,10, 50, 30);
        logo.setFont(new Font("돋움체", 0, 20));
        
        //id
        memberId = new JLabel("아이디 : aaa");
        memberId.setBounds(1150, 20, 100, 30);
        memberId.setFont(new Font("돋움체", 0, 15));
        
        // back버튼
        backBtn = new JButton("뒤로가기");
        backBtn.setBounds(1150,800, 100, 30);
        backBtn.setFont(new Font("돋움체", 0, 15));
        backBtn.addActionListener(this);
        //add
        c.add(myPagePanel);
        c.add(logo);
        c.add(myPageCb);
        c.add(memberId);
        c.add(backBtn);
        
        
        validate();
   }
   
   public void statistics(int index) {
	   
	   myPagePanel.removeAll();
	   
	   if (index == 0) {
		   myPagePanel.setBorder(soonTb);
		   myPagePanel.add(particPanel);
		   myPagePanel.add(particScroll);
		   
		   particPanel.add(particPhoto);
		   particPanel.add(particName);
		   particPanel.add(particPrice);
		   particPanel.add(particMemo);
		   particPanel.add(itemDeleteBtn);
		   
		   
	   } else if (index == 1) {
		   myPagePanel.setBorder(buyTb);
	   } else if (index == 2) {
		   myPagePanel.setBorder(sellTb);
	   } else if (index == 3) {
		   myPagePanel.setBorder(pwTb);
		   myPagePanel.add(nowPwlbl);
	       myPagePanel.add(newPwlbl);
	       myPagePanel.add(newCheckPwlbl);
	       myPagePanel.add(nowPw);
	       myPagePanel.add(newPw);
	       myPagePanel.add(newCheckPw);
	       myPagePanel.add(saveBtn);
	   }
	   
	   
	   
	   
   }
   
   @Override
   public void actionPerformed(ActionEvent e) {
	   Object obj = e.getSource();
	   if (obj == myPageCb) {
			int index = myPageCb.getSelectedIndex();
			statistics(index);
			repaint(); 
		}
	   else if(obj==backBtn) {
		   try {
			   dispose();
			   Main main = new Main();
			   main.setVisible(true);
		   } catch (Exception e2) {
				e2.printStackTrace();
		   }
	   }
   }

   public static void main(String[] args) {
     
   }

}