package project1;

import java.awt.Color;
import java.awt.Container;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

public class FindIdPassword extends JFrame
implements ActionListener{
   
   private JFrame frame = new JFrame();
   
   JPanel findIdPanel, findPwPanel;
   JLabel logo,idFindName, idFindEmail, pwFindName,pwFindId, pwFindEmail;
   JTextField idFindNametxt, idFindEmailtxt, pwFindIdtxt,pwFindNametxt,pwFindEmailtxt;
   JButton findIdBtn, findPwBtn, backBtn;
   TitledBorder idTb, pwTb;
   JOptionPane alarm = new JOptionPane();
   AuctionMgr mgr = new AuctionMgr();
   DBConnectionMgr con;
   Color color = new Color(240,240,240);
   public FindIdPassword() {
      setTitle("DaBID 아이디/비밀번호 찾기");
      setSize(700, 600);
      setResizable(false);
      setLocationRelativeTo(null);
      getContentPane().setLayout(null);
      Container c = getContentPane();
      //Id 라인
      idTb = new TitledBorder(new LineBorder(Color.black,2,true),"아이디 찾기");
      idTb.setTitleFont(new Font("맑은 고딕", Font.BOLD, 20));
      //Pw 라인
      pwTb = new TitledBorder(new LineBorder(Color.BLACK,2,true),"비밀번호 찾기");
      pwTb.setTitleFont(new Font("맑은 고딕", Font.BOLD, 20));
      //아이디 찾기(Id 패널)
      findIdPanel = new JPanel();
      findIdPanel.setLayout(null);
      findIdPanel.setBounds(60, 50, 550, 200);
      findIdPanel.setBorder(idTb);
      //비밀번호 찾기(Pw 패널)
      findPwPanel = new JPanel();
      findPwPanel.setLayout(null);
      findPwPanel.setBounds(60, 280, 550, 230);
      findPwPanel.setBorder(pwTb);
      //이름 이메일 Label -->아이디찾기
      idFindName = new JLabel("이 름");
      idFindName.setBounds(120, 60, 50, 50);
      idFindName.setFont(new Font("맑은 고딕", Font.BOLD, 15));
      idFindEmail = new JLabel("이메일");
      idFindEmail.setBounds(120, 100, 80, 50);
      idFindEmail.setFont(new Font("맑은 고딕", Font.BOLD, 15));
      //이름 이메일 입력칸 -->아이디찾기
      idFindNametxt = new JTextField();
      idFindEmailtxt = new JTextField();
      idFindNametxt.setBounds(190,75,100,25);
      idFindEmailtxt.setBounds(190,115,100,25);
      //아이디찾기버튼
      findIdBtn = new JButton("아이디찾기");
      findIdBtn.setBounds(310,75,110,65);
      findIdBtn.addActionListener(this);
      //이름 아이디 이메일 -->비밀번호찾기 
      pwFindName = new JLabel("이 름");
      pwFindName.setBounds(120, 60, 50, 50);
      pwFindName.setFont(new Font("맑은 고딕", Font.BOLD, 15));
      pwFindId = new JLabel("아이디");
      pwFindId.setBounds(120, 100, 80, 50);
      pwFindId.setFont(new Font("맑은 고딕", Font.BOLD, 15));
      pwFindEmail = new JLabel("이메일");
      pwFindEmail.setBounds(120, 140, 80, 50);
      pwFindEmail.setFont(new Font("맑은 고딕", Font.BOLD, 15));
      //이름 아이디 이메일 입력칸 -->비밀번호찾기
      pwFindEmailtxt = new JTextField();
      pwFindIdtxt = new JTextField();
      pwFindNametxt = new JTextField();
      pwFindNametxt.setBounds(190,70,100,25);
      pwFindIdtxt.setBounds(190,110,100,25);
      pwFindEmailtxt.setBounds(190,150,100,25);
      //비밀번호찾기 버튼
      findPwBtn = new JButton("비밀번호 찾기");
      findPwBtn.setBounds(310,70,110,105);
      findPwBtn.addActionListener(this);
      //뒤로가기btn
      backBtn = new JButton("뒤로가기");
      backBtn.setBounds(570,520,100,30);
      backBtn.setFont(new Font("맑은 고딕",Font.BOLD,13));
      backBtn.addActionListener(this);
      //아이디add   
      findIdPanel.add(idFindName);
      findIdPanel.add(idFindEmail);
      findIdPanel.add(idFindNametxt);
      findIdPanel.add(idFindEmailtxt);
      findIdPanel.add(findIdBtn);
      //비밀번호add
      findPwPanel.add(pwFindName);
      findPwPanel.add(pwFindId);
      findPwPanel.add(pwFindEmail);
      findPwPanel.add(pwFindNametxt);
      findPwPanel.add(pwFindIdtxt);
      findPwPanel.add(pwFindEmailtxt);
      findPwPanel.add(findPwBtn);
      //logo
      logo = new JLabel(new ImageIcon(Login.class.getResource("./image/logo.png")));
     logo.setBounds(270,10,130,40);
     //디자인
     //배경색
      c.setBackground(Color.white);
      findIdPanel.setBackground(Color.white);
      findPwPanel.setBackground(Color.white);
      //TextField 색
      idFindNametxt.setBackground(color);
      idFindNametxt.setBorder(javax.swing.BorderFactory.createEmptyBorder());
      idFindEmailtxt.setBackground(color);
      idFindEmailtxt.setBorder(javax.swing.BorderFactory.createEmptyBorder());
      pwFindNametxt.setBackground(color);
      pwFindNametxt.setBorder(javax.swing.BorderFactory.createEmptyBorder());
      pwFindIdtxt.setBackground(color);
      pwFindIdtxt.setBorder(javax.swing.BorderFactory.createEmptyBorder());
      pwFindEmailtxt.setBackground(color);
      pwFindEmailtxt.setBorder(javax.swing.BorderFactory.createEmptyBorder());
      //아이디찾기버튼 디자인
      findIdBtn.setBackground(Color.black);
      findIdBtn.setForeground(Color.white);
      findIdBtn.setBorderPainted(false);
      findIdBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
      this.add(findIdBtn);
      //비밀번호찾기버튼 디자인
      findPwBtn.setBackground(Color.black);
      findPwBtn.setForeground(Color.white);
      findPwBtn.setBorderPainted(false);
      findPwBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
      this.add(findPwBtn);
      //뒤로가기 버튼 디자인
      backBtn.setBackground(Color.black);
      backBtn.setForeground(Color.white);
      backBtn.setBorderPainted(false);
      backBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
      this.add(backBtn);
     //add
     c.add(logo);
      c.add(findIdPanel);
      c.add(findPwPanel);
      c.add(backBtn);
      findIdPanel.add(findIdBtn);
      findPwPanel.add(findPwBtn);
      validate();
   }

   @Override
   public void actionPerformed(ActionEvent e) {
      Object obj = e.getSource();
      if(obj==backBtn) {
    	  try {
			dispose();
			Login login = new Login();
			login.setVisible(true);
		} catch (Exception e2) {
			e2.printStackTrace();
		}
      }else if(obj==findIdBtn) {
    	  if(idFindNametxt.getText().isEmpty()||idFindEmailtxt.getText().equals("")) {
    		  alarm.showMessageDialog(null, "이름과 이메일을 확인하세요","경고",JOptionPane.ERROR_MESSAGE);
    		  idFindName.requestFocus();
    	  }else {
    		  String memName , memEmail;
    		  memName = idFindNametxt.getText();
    		  memEmail = idFindEmailtxt.getText();
    		  MemberBean mbean = mgr.getMemberId(memName, memEmail);
    		  String memId = mbean.getMemberId();
    		  alarm.showMessageDialog(this, "아이디 : "+memId);
    	  }

      }else if(obj==findPwBtn) {
    	  if(pwFindIdtxt.getText().isEmpty()||pwFindNametxt.getText().equals("")||pwFindEmailtxt.getText().equals("")) {
    		  alarm.showMessageDialog(null, "이름 또는 아이디, 이메일을 확인하세요");
    		  pwFindIdtxt.requestFocus();
    	  }else {
    		  String memName , memId,memEmail;
    		  memName = pwFindNametxt.getText();
    		  memId = pwFindIdtxt.getText();
    		  memEmail = pwFindEmailtxt.getText();
    		  MemberBean mbean = mgr.getMemberPw(memName, memId, memEmail);
    		  String memPw = mbean.getMemberPwd();
    		  alarm.showMessageDialog(null, memPw);
    	  }
      }
   }
   
   
   public static void main(String[] args) {
      try {
		FindIdPassword fip = new FindIdPassword();
		fip.setVisible(true);
	} catch (Exception e) {
		e.printStackTrace();
		// TODO: handle exception
	}
   }

}