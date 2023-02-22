package project1;

import java.awt.Color;
import java.awt.Container;
import java.awt.Cursor;
import java.awt.FileDialog;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class Login extends JFrame
implements ActionListener{

   private JFrame frame = new JFrame();
   
   JLabel logo, memberId, memberPw, logolbl;
   JTextField memberIdtxt;
   JButton loginBtn, joinMemberBtn,findIdPwBtn;
   JPasswordField memberPwtxt;
   JOptionPane alarm = new JOptionPane();
   AuctionMgr mgr = new AuctionMgr();
   String logId;
  
   public Login() {
        setTitle("DaBID 로그인 페이지");
        setSize(700, 600);
        setResizable(false);
        setLocationRelativeTo(null);//가운데 출력
        getContentPane().setLayout(null);
        Container c = getContentPane();
        
      //logo이미지
      logolbl = new JLabel(new ImageIcon(Login.class.getResource("./image/fullshot.png")));
      //logolbl.setIcon(new ImageIcon(Login.class.getResource("/dabid/dabid.jpg")));
      logolbl.setHorizontalAlignment(SwingConstants.CENTER);
      logolbl.setBounds(15, 30,300,500);
      
      //아이디 label
      memberId = new JLabel("아이디");
      memberId.setFont(new Font("돋움체", 0, 20));
      memberId.setHorizontalAlignment(SwingConstants.CENTER);
      memberId.setBounds(280, 220, 75, 37);
      //비밀번호 label
      memberPw = new JLabel("비밀번호");
      memberPw.setFont(new Font("돋움체", 0, 20));
      memberPw.setBounds(280, 280, 90, 37);
      //아이디 입력칸
      memberIdtxt = new JTextField();
      memberIdtxt.setFont(new Font("돋움체", 0, 15));
      memberIdtxt.setBounds(370, 220, 158, 34);
      //비밀번호 입력칸
      memberPwtxt = new JPasswordField();
      memberPwtxt.setFont(new Font("돋움체", 0, 15));
      memberPwtxt.setBounds(370, 280, 158, 34);
      //회원가입 버튼
      joinMemberBtn = new JButton("회원가입");
      joinMemberBtn.setFont(new Font("돋움체", 0, 15));
      joinMemberBtn.setBounds(320, 330, 100, 30);
      joinMemberBtn.addActionListener(this);	
      //아이디 비밀번호 찾기 버튼
      findIdPwBtn = new JButton("아이디/비밀번호 찾기");
      findIdPwBtn.setFont(new Font("돋움체", 0, 15));
      findIdPwBtn.setBounds(440, 330, 180, 30);
      findIdPwBtn.addActionListener(this);
      //loginbtn
      loginBtn = new JButton("로그인");
      loginBtn.setBounds(550, 220, 100, 95);
      loginBtn.setFont(new Font("돋움체", 0, 15));
      loginBtn.addActionListener(this);
      //아이디 label
      memberId = new JLabel("아이디");
      memberId.setFont(new Font("맑은 고딕",Font.BOLD, 20));;
      memberId.setHorizontalAlignment(SwingConstants.CENTER);
      memberId.setBounds(280, 220, 75, 37);
      //비밀번호 label
      memberPw = new JLabel("비밀번호");
      memberPw.setFont(new Font("맑은 고딕",Font.BOLD, 20));
      memberPw.setBounds(270, 280, 90, 37);
      //아이디 입력칸
      memberIdtxt = new JTextField();
      memberIdtxt.setFont(new Font("맑은 고딕", 0, 15));
      memberIdtxt.setBounds(370, 220, 158, 34);
      //비밀번호 입력칸
      memberPwtxt = new JPasswordField();
      memberPwtxt.setFont(new Font("맑은 고딕", 0, 15));
      memberPwtxt.setBounds(370, 280, 158, 34);
      //회원가입 버튼
      joinMemberBtn = new JButton("회원가입");
      joinMemberBtn.setFont(new Font("맑은 고딕",Font.BOLD, 13));
      joinMemberBtn.setBounds(320, 340, 100, 30);
      joinMemberBtn.addActionListener(this);   
      //아이디 비밀번호 찾기 버튼
      findIdPwBtn = new JButton("아이디/비밀번호 찾기");
      findIdPwBtn.setFont(new Font("맑은 고딕",Font.BOLD, 13));
      findIdPwBtn.setBounds(440, 340, 180, 30);
      findIdPwBtn.addActionListener(this);
      //loginbtn
      loginBtn = new JButton("로그인");
      loginBtn.setBounds(550, 220, 100, 95);
      loginBtn.setFont(new Font("맑은 고딕",Font.BOLD, 17));
      loginBtn.addActionListener(this);
      
      //디자인
      //배경색
      c.setBackground(Color.white);
      //버튼 디자인
      loginBtn.setBackground(Color.black);
      loginBtn.setForeground(Color.white);
      joinMemberBtn.setBackground(Color.black);
      joinMemberBtn.setForeground(Color.white);
      findIdPwBtn.setBackground(Color.black);
      findIdPwBtn.setForeground(Color.white);
      //버튼 테두리
      joinMemberBtn.setBorderPainted(false);
      findIdPwBtn.setBorderPainted(false);
      loginBtn.setBorderPainted(false);
      //버튼 위 커서 모양 변경
      joinMemberBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
      findIdPwBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
      loginBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
      this.add(joinMemberBtn);
      this.add(findIdPwBtn);
      this.add(loginBtn);
      //아이디 비밀번호 입력칸 디자인
      Color txtColor = new Color(240,240,240);
      memberIdtxt.setBackground(txtColor);
      memberIdtxt.setBorder(javax.swing.BorderFactory.createEmptyBorder());
      memberPwtxt.setBackground(txtColor);
      memberPwtxt.setBorder(javax.swing.BorderFactory.createEmptyBorder());
      //add      
      c.add(loginBtn);
      c.add(logolbl);

      c.add(memberId);
      c.add(memberPw);
      c.add(memberIdtxt);
      c.add(memberPwtxt);
      c.add(joinMemberBtn);
      c.add(findIdPwBtn);
      validate();
   }
   
   @Override
   public void actionPerformed(ActionEvent e) {
      Object obj = e.getSource();
      
      if(obj==loginBtn) {
    	  if(memberIdtxt.getText().equals("") || new String(memberPwtxt.getPassword()).equals("")) {
    		  alarm.showMessageDialog(this, "아이디와 비밀번호를 입력하세요.");
    		  memberIdtxt.requestFocus();
    	  }else {
    		  if(mgr.loginChk(memberIdtxt.getText(), new String(memberPwtxt.getPassword()))) {
        		  //로그인성공
    			  logId = memberIdtxt.getText();
        		  try {
        			  	alarm.showMessageDialog(this, "로그인 성공");
        			  	logId=memberIdtxt.getText().trim();
        	            dispose();
        	            Main main = new Main(logId);
        	            main.setVisible(true);
        	         } catch (Exception e2) {
        	        	 e2.printStackTrace();
        	         }
        	  }else {
        		  //실패
        		  memberPwtxt.setText("");
        		  alarm.showMessageDialog(this, "로그인 실패");
        	  }
    	  }
    	  
         
      }else if(obj==findIdPwBtn) {
    	  try {
              dispose();
              FindIdPassword fip = new FindIdPassword();
              fip.setVisible(true);
           } catch (Exception e2) {
          	 e2.printStackTrace();
           }
      }else if(obj==joinMemberBtn) {
    	  try {
              dispose();
              JoinMember jm = new JoinMember();
              jm.setVisible(true);
           } catch (Exception e2) {
          	 e2.printStackTrace();
           }
      }
   }
   

   public static void main(String[] args) {
         try {
             Login login = new Login();
             login.setVisible(true);
          } catch (Exception e) {
             e.printStackTrace();
          }
   }


}