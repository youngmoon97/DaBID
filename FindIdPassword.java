package project1;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

public class FindIdPassword extends JFrame
implements ActionListener{
   
   private JFrame frame = new JFrame();
   
   JPanel findIdPanel, findPwPanel;
   JLabel idFindName, idFindEmail, pwFindName,pwFindId, pwFindEmail;
   JTextField idFindNametxt, idFindEmailtxt, pwFindIdtxt,pwFindNametxt,pwFindEmailtxt;
   JButton findIdBtn, findPwBtn, backBtn;
   TitledBorder idTb, pwTb;
   
   public FindIdPassword() {
      setTitle("DaBID 아이디/비밀번호 찾기");
      setSize(700, 600);
      setResizable(false);
      setLocationRelativeTo(null);
      getContentPane().setLayout(null);
      Container c = getContentPane();
      //Id 라인
      idTb = new TitledBorder(new LineBorder(Color.BLACK,1,true),"아이디 찾기");
      idTb.setTitleFont(new Font("돋움체", 0, 20));
      //Pw 라인
      pwTb = new TitledBorder(new LineBorder(Color.BLACK,1,true),"비밀번호 찾기");
      pwTb.setTitleFont(new Font("돋움체", 0, 20));
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
      idFindName.setFont(new Font("돋움체", 0, 15));
      idFindEmail = new JLabel("이메일");
      idFindEmail.setBounds(120, 100, 80, 50);
      idFindEmail.setFont(new Font("돋움체", 0, 15));
      //이름 이메일 입력칸 -->아이디찾기
      idFindNametxt = new JTextField();
      idFindEmailtxt = new JTextField();
      idFindNametxt.setBounds(180,70,100,25);
      idFindEmailtxt.setBounds(180,110,100,25);
      //아이디찾기버튼
      findIdBtn = new JButton("아이디찾기");
      findIdBtn.setBounds(300,70,120,60);
      //이름 아이디 이메일 -->비밀번호찾기 
      pwFindName = new JLabel("이 름");
      pwFindName.setBounds(120, 60, 50, 50);
      pwFindName.setFont(new Font("돋움체", 0, 15));
      pwFindId = new JLabel("아이디");
      pwFindId.setBounds(120, 100, 80, 50);
      pwFindId.setFont(new Font("돋움체", 0, 15));
      pwFindEmail = new JLabel("이메일");
      pwFindEmail.setBounds(120, 140, 80, 50);
      pwFindEmail.setFont(new Font("돋움체", 0, 15));
      //이름 아이디 이메일 입력칸 -->비밀번호찾기
      pwFindEmailtxt = new JTextField();
      pwFindIdtxt = new JTextField();
      pwFindNametxt = new JTextField();
      pwFindNametxt.setBounds(180,70,100,25);
      pwFindIdtxt.setBounds(180,110,100,25);
      pwFindEmailtxt.setBounds(180,150,100,25);
      //비밀번호찾기 버튼
      findPwBtn = new JButton("비밀번호 찾기");
      findPwBtn.setBounds(300,70,120,100);
      //뒤로가기btn
      backBtn = new JButton("뒤로가기");
      backBtn.setBackground(new Color(255, 255, 255));
      backBtn.setBounds(580,520,90,30);
      backBtn.setFont(new Font("돋움체",0,15));
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
      
      c.add(findIdPanel);
      c.add(findPwPanel);
      c.add(backBtn);
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
    	  
      }else if(obj==findPwBtn) {
    	  
      }
   }
   
   public static void main(String[] args) {
      
   }

}