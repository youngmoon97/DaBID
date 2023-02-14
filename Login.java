package project1;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class Login extends JFrame
implements ActionListener{

   private JFrame frame = new JFrame();
   
   JLabel memberId, memberPw, logolbl;
   JTextField memberIdtxt;
   JButton loginBtn, joinMemberBtn,findIdPwBtn;
   JPasswordField memberPwtxt;
   
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
         //TODO
         try {
            dispose();
            Main main = new Main();
            main.setVisible(true);
         } catch (Exception e2) {
        	 e2.printStackTrace();
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