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
      //���̵� label
      memberId = new JLabel("���̵�");
      memberId.setFont(new Font("���� ���",Font.BOLD, 20));;
      memberId.setHorizontalAlignment(SwingConstants.CENTER);
      memberId.setBounds(280, 220, 75, 37);
      //��й�ȣ label
      memberPw = new JLabel("��й�ȣ");
      memberPw.setFont(new Font("���� ���",Font.BOLD, 20));
      memberPw.setBounds(270, 280, 90, 37);
      //���̵� �Է�ĭ
      memberIdtxt = new JTextField();
      memberIdtxt.setFont(new Font("���� ���", 0, 15));
      memberIdtxt.setBounds(370, 220, 158, 34);
      //��й�ȣ �Է�ĭ
      memberPwtxt = new JPasswordField();
      memberPwtxt.setFont(new Font("���� ���", 0, 15));
      memberPwtxt.setBounds(370, 280, 158, 34);
      //ȸ������ ��ư
      joinMemberBtn = new JButton("ȸ������");
      joinMemberBtn.setFont(new Font("���� ���",Font.BOLD, 13));
      joinMemberBtn.setBounds(320, 340, 100, 30);
      joinMemberBtn.addActionListener(this);   
      //���̵� ��й�ȣ ã�� ��ư
      findIdPwBtn = new JButton("���̵�/��й�ȣ ã��");
      findIdPwBtn.setFont(new Font("���� ���",Font.BOLD, 13));
      findIdPwBtn.setBounds(440, 340, 180, 30);
      findIdPwBtn.addActionListener(this);
      //loginbtn
      loginBtn = new JButton("�α���");
      loginBtn.setBounds(550, 220, 100, 95);
      loginBtn.setFont(new Font("���� ���",Font.BOLD, 17));
      loginBtn.addActionListener(this);
      
      //������
      //����
      c.setBackground(Color.white);
      //��ư ������
      loginBtn.setBackground(Color.black);
      loginBtn.setForeground(Color.white);
      joinMemberBtn.setBackground(Color.black);
      joinMemberBtn.setForeground(Color.white);
      findIdPwBtn.setBackground(Color.black);
      findIdPwBtn.setForeground(Color.white);
      //��ư �׵θ�
      joinMemberBtn.setBorderPainted(false);
      findIdPwBtn.setBorderPainted(false);
      loginBtn.setBorderPainted(false);
      //��ư �� Ŀ�� ��� ����
      joinMemberBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
      findIdPwBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
      loginBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
      this.add(joinMemberBtn);
      this.add(findIdPwBtn);
      this.add(loginBtn);
      //���̵� ��й�ȣ �Է�ĭ ������
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
    		  alarm.showMessageDialog(this, "���̵�� ��й�ȣ�� �Է��ϼ���.");
    		  memberIdtxt.requestFocus();
    	  }else {
    		  if(mgr.loginChk(memberIdtxt.getText(), new String(memberPwtxt.getPassword()))) {
        		  //�α��μ���
    			  logId = memberIdtxt.getText();
        		  try {
        			  	alarm.showMessageDialog(this, "�α��� ����");
        			  	logId=memberIdtxt.getText().trim();
        	            dispose();
        	            Main main = new Main(logId);
        	            main.setVisible(true);
        	         } catch (Exception e2) {
        	        	 e2.printStackTrace();
        	         }
        	  }else {
        		  //����
        		  memberPwtxt.setText("");
        		  alarm.showMessageDialog(this, "�α��� ����");
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