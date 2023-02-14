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
        setTitle("DaBID �α��� ������");
        setSize(700, 600);
        setResizable(false);
        setLocationRelativeTo(null);//��� ���
        getContentPane().setLayout(null);
        Container c = getContentPane();
      //logo�̹���
      logolbl = new JLabel(new ImageIcon(Login.class.getResource("./image/fullshot.png")));
      //logolbl.setIcon(new ImageIcon(Login.class.getResource("/dabid/dabid.jpg")));
      logolbl.setHorizontalAlignment(SwingConstants.CENTER);
      logolbl.setBounds(15, 30,300,500);
      //���̵� label
      memberId = new JLabel("���̵�");
      memberId.setFont(new Font("����ü", 0, 20));
      memberId.setHorizontalAlignment(SwingConstants.CENTER);
      memberId.setBounds(280, 220, 75, 37);
      //��й�ȣ label
      memberPw = new JLabel("��й�ȣ");
      memberPw.setFont(new Font("����ü", 0, 20));
      memberPw.setBounds(280, 280, 90, 37);
      //���̵� �Է�ĭ
      memberIdtxt = new JTextField();
      memberIdtxt.setFont(new Font("����ü", 0, 15));
      memberIdtxt.setBounds(370, 220, 158, 34);
      //��й�ȣ �Է�ĭ
      memberPwtxt = new JPasswordField();
      memberPwtxt.setFont(new Font("����ü", 0, 15));
      memberPwtxt.setBounds(370, 280, 158, 34);
      //ȸ������ ��ư
      joinMemberBtn = new JButton("ȸ������");
      joinMemberBtn.setFont(new Font("����ü", 0, 15));
      joinMemberBtn.setBounds(320, 330, 100, 30);
      joinMemberBtn.addActionListener(this);	
      //���̵� ��й�ȣ ã�� ��ư
      findIdPwBtn = new JButton("���̵�/��й�ȣ ã��");
      findIdPwBtn.setFont(new Font("����ü", 0, 15));
      findIdPwBtn.setBounds(440, 330, 180, 30);
      findIdPwBtn.addActionListener(this);
      //loginbtn
      loginBtn = new JButton("�α���");
      loginBtn.setBounds(550, 220, 100, 95);
      loginBtn.setFont(new Font("����ü", 0, 15));
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