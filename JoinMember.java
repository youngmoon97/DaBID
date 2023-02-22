package project1;

import java.awt.Color;
import java.awt.Container;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.SwingConstants;

public class JoinMember extends JFrame
implements ActionListener{
   
   private JFrame frame = new JFrame();
   
   JPanel joinPanel;
   JLabel logo, joinName, joinId, joinPw, joinPwCheck, joinEmail, joinAt, joinUseIdlbl, joinUsePwlbl;
   JTextField joinNametxt, joinIdtxt, joinEmailtxt;
   JPasswordField joinPwtxt, joinPwChecktxt;
   JButton joinBtn, idCheckBtn, pwCheckBtn;
   String joinOk[] = {"��� ������ ���̵��Դϴ�.", "��� ���� ���̵��Դϴ�."};
   JComboBox<String> emailCb = new JComboBox<String>();
   AuctionMgr mgr = new AuctionMgr();
   JOptionPane alarm = new JOptionPane();
   DBConnectionMgr con;
   
   public JoinMember() {
        setTitle("DaBID ȸ������");
        setSize(700, 600);
        setResizable(false);
        setLocationRelativeTo(null);//��� ���
        getContentPane().setLayout(null);
        Container c = getContentPane();
        //ȸ������ �г�
        joinPanel = new JPanel();
        joinPanel.setLayout(null);
        joinPanel.setBounds(0, 0, 684, 561);
        //�̸�
        joinName = new JLabel("�� �� ");
        joinName.setBounds(158, 110, 110, 30);
        joinName.setFont(new Font("���� ���", 0, 20));
        //���̵�
        joinId = new JLabel("���̵� ");
        joinId.setBounds(158, 185, 100, 30);
        joinId.setFont(new Font("���� ���", 0, 20));
        //���̵� �ߺ�Ȯ�� ǥ��ĭ
        joinUseIdlbl = new JLabel("");
        joinUseIdlbl.setBounds(380, 195, 300, 30);
        joinUseIdlbl.setFont(new Font("���� ���", 0, 10));
        //��й�ȣ
        joinPw = new JLabel("��й�ȣ ");
        joinPw.setBounds(158, 260, 110, 30);
        joinPw.setFont(new Font("���� ���", 0, 20));
        //��й�ȣ Ȯ��
        joinPwCheck = new JLabel("��й�ȣ Ȯ��");
        joinPwCheck.setBounds(158, 335, 160, 30);
        joinPwCheck.setFont(new Font("���� ���", 0, 20));
        //��й�ȣ �ߺ�Ȯ�� ǥ��ĭ
        joinUsePwlbl = new JLabel("");
        joinUsePwlbl.setBounds(380, 370, 300, 30);
        joinUsePwlbl.setFont(new Font("���� ���", 0, 10));
        //�̸���
        joinEmail = new JLabel("�̸��� ");
        joinEmail.setBounds(158, 410, 100, 30);
        joinEmail.setFont(new Font("���� ���", 0, 20));
        //�̸� �Է�ĭ
        joinNametxt = new JTextField();
        joinNametxt.setBounds(328, 110, 232, 30);
        joinNametxt.setFont(new Font("���� ���", 0, 15));
        //���̵� �Է�ĭ
        joinIdtxt = new JTextField("���̵�");
        joinIdtxt.setBounds(328, 185, 150, 30);
        joinIdtxt.setFont(new Font("���� ���", 0, 15));
        //��й�ȣ Ȯ�� �Է�ĭ
        joinPwtxt = new JPasswordField();
        joinPwtxt.setBounds(328, 260, 232, 30);
        joinPwtxt.setFont(new Font("���� ���", 0, 15));
        //��й�ȣ �ߺ�Ȯ�� �Է�ĭ
        joinPwChecktxt = new JPasswordField();
        joinPwChecktxt.setBounds(328, 345, 150, 30);
        joinPwChecktxt.setFont(new Font("���� ���", 0, 15));
        //�̸���
        joinEmailtxt = new JTextField();
        joinEmailtxt.setBounds(328, 415, 103, 30);
        joinEmailtxt.setFont(new Font("���� ���", 0, 15));
        //�̸��� @
        joinAt = new JLabel("@");
        joinAt.setHorizontalAlignment(SwingConstants.CENTER);
        joinAt.setBounds(430, 415, 20, 30);
        joinAt.setFont(new Font("���� ���", 0, 20));
        emailCb.setBackground(new Color(255, 255, 255));
        //�̸��� �޺��ڽ�
        emailCb.setBounds(450,415,110,30);
        emailCb.setFont(new Font("���� ���", 0, 15));
        emailCb.addItem("naver.com");
        emailCb.addItem("daum.net");
        emailCb.addItem("gmail.com");
        //ȸ������ ��ư
        joinBtn = new JButton("ȸ������");
        joinBtn.setBackground(new Color(255, 255, 255));
        joinBtn.setBounds(300,500,100,30);
        joinBtn.setFont(new Font("���� ���", Font.BOLD, 15));
        joinBtn.addActionListener(this);
        //���̵��ߺ�Ȯ�� ��ư
        idCheckBtn = new JButton("�ߺ�Ȯ��");
        idCheckBtn.setBackground(new Color(255, 255, 255));
        idCheckBtn.setBounds(480,185,80,30);
        idCheckBtn.setFont(new Font("���� ���", Font.BOLD, 11));
        idCheckBtn.addActionListener(this);
        //��й�ȣ �ߺ�Ȯ�� ��ư
        pwCheckBtn = new JButton("�ߺ�Ȯ��");
        pwCheckBtn.setBackground(new Color(255, 255, 255));
        pwCheckBtn.setBounds(480,345,80,30);
        pwCheckBtn.setFont(new Font("���� ���", Font.BOLD, 11));
        pwCheckBtn.addActionListener(this);
        //logo
        logo = new JLabel(new ImageIcon(Login.class.getResource("./image/logo.png")));
        logo.setBounds(280,20,130,40);
        //������
        //����
        joinPanel.setBackground(Color.white);
        //TextField ��
        Color txtColor = new Color(240,240,240);
        joinNametxt.setBackground(txtColor);
        joinNametxt.setBorder(javax.swing.BorderFactory.createEmptyBorder());
        joinIdtxt.setBackground(txtColor);
        joinIdtxt.setBorder(javax.swing.BorderFactory.createEmptyBorder());
        joinPwtxt.setBackground(txtColor);
        joinPwtxt.setBorder(javax.swing.BorderFactory.createEmptyBorder());
        joinPwChecktxt.setBackground(txtColor);
        joinPwChecktxt.setBorder(javax.swing.BorderFactory.createEmptyBorder());
        joinEmailtxt.setBackground(txtColor);
        joinEmailtxt.setBorder(javax.swing.BorderFactory.createEmptyBorder());
        //���̵� �ߺ�Ȯ�� ��ư ������
        idCheckBtn.setBackground(Color.black);
        idCheckBtn.setForeground(Color.white);
        idCheckBtn.setBorderPainted(false);
        idCheckBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        //��й�ȣ �ߺ�Ȯ�� ��ư ������
        pwCheckBtn.setBackground(Color.black);
        pwCheckBtn.setForeground(Color.white);
        pwCheckBtn.setBorderPainted(false);
        pwCheckBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        this.add(pwCheckBtn);
        //ȸ������ ��ư ������
        joinBtn.setBackground(Color.black);
        joinBtn.setForeground(Color.white);
        joinBtn.setBorderPainted(false);
        joinBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        this.add(joinBtn);
        //�̸��� �޺� �ڽ� ����
        emailCb.setBackground(txtColor);
        emailCb.setCursor(new Cursor(Cursor.HAND_CURSOR));
        //add
        joinPanel.add(joinName);
        joinPanel.add(joinId);
        joinPanel.add(joinPw);
        joinPanel.add(joinPwCheck);
        joinPanel.add(joinEmail);
        joinPanel.add(joinNametxt);
        joinPanel.add(joinIdtxt);
        joinPanel.add(joinPwtxt);
        joinPanel.add(joinPwChecktxt);
        joinPanel.add(joinEmailtxt);
        joinPanel.add(joinAt);
        joinPanel.add(emailCb);
        joinPanel.add(joinBtn);
        joinPanel.add(joinUseIdlbl);
        joinPanel.add(joinUsePwlbl);
        joinPanel.add(idCheckBtn);
        joinPanel.add(pwCheckBtn);
        joinPanel.add(pwCheckBtn);
        c.add(logo);
        c.add(joinPanel);
        validate();
   }
   
   @Override
   public void actionPerformed(ActionEvent e) {
      Object obj = e.getSource();
      if(obj==joinBtn) {
    	  if(joinNametxt.getText().equals("")) {
    		  alarm.showMessageDialog(this, "�̸��� �Է����ּ���");
    		  joinNametxt.requestFocus();
    	  }else if(joinIdtxt.getText().equals("")) {
    		  alarm.showMessageDialog(this, "���̵��� �Է����ּ���");
    	  }else if(joinPwtxt.getPassword().length == 0) {
    		  alarm.showMessageDialog(this, "��й�ȣ�� �Է����ּ���");
    	  }else if(joinEmailtxt.getText().equals("")) {
    		  alarm.showMessageDialog(this, "�̸����� �Է����ּ���");
    	  }else if(joinIdtxt.isEnabled()==true){
    		  alarm.showMessageDialog(this, "���̵� �ߺ��� Ȯ���ϼ���");
    	  }else {
        	  String memname = joinNametxt.getText();
        	  String memid = joinIdtxt.getText();
        	  String mempw="";
        	  char[] joinpwchk = joinPwtxt.getPassword();
        	  for (int i = 0; i < joinpwchk.length; i++) {
        		  Character.toString(joinpwchk[i]);
               		mempw += (mempw.equals("")) ? ""+joinpwchk[i]+"" : ""+joinpwchk[i]+"";
              }
        	  System.out.println(mempw);
        	  String mememail = joinEmailtxt.getText()+"@"+emailCb.getSelectedItem(); 
        	  
        	  System.out.println(mememail);
        	  mgr.joinMember(memid, memname,mempw,mememail);
        	  
        	  try {
        		  dispose();
				Login login = new Login();
				login.setVisible(true);
			} catch (Exception e2) {
				// TODO: handle exception
			}
    	  }
      }else if(obj==idCheckBtn) {
         //id �ߺ� ���� Ȯ��
    	  if(joinIdtxt.getText().length()==0) {
    		  alarm.showMessageDialog(null, "���̵� �Է��ϼ���.");
    	  }else {
    		  if(mgr.IdDupChk(joinIdtxt.getText())){
    			//�����ϴ� ���̵� ���� �׷��� ��밡��.
    	        alarm.showMessageDialog(this, "��� �����մϴ�."); 
    	        joinIdtxt.setEnabled(false);
  	         }else {
  	        	alarm.showMessageDialog(this, "�̹� �����ϴ� ���̵��Դϴ�.");
  	            joinIdtxt.setText(" ");
  	            joinIdtxt.requestFocus();
  	         }
    	  }
      }else if(obj==pwCheckBtn) {
         //pw ������ Ȯ��
    	//��й�ȣ �ߺ�Ȯ��
    	  String pw="", pw2="";
          char[] pwcheck = joinPwtxt.getPassword();
          char[] pwcheck2 = joinPwChecktxt.getPassword();
          
    	  if(pwcheck.length==0) {
    		alarm.showMessageDialog(this, "��й�ȣ�� �Է��ϼ���.");
    		joinPwtxt.requestFocus();
    	  }else {
              for (int i = 0; i < pwcheck.length; i++) {
              	Character.toString(pwcheck[i]);
              	pw += (pw.equals("")) ? ""+pwcheck[i]+"" : ""+pwcheck[i]+"";
              }
              for (int i = 0; i < pwcheck2.length; i++) {
              	Character.toString(pwcheck2[i]);
              	pw2 += (pw2.equals("")) ? ""+pwcheck2[i]+"" : ""+pwcheck2[i]+"";   
              }
              
              if(pw.equals(pw2)) {
              	joinUsePwlbl.setText("��й�ȣ�� ��ġ�մϴ�.");
              }else if(pw!=pw2){
              	joinUsePwlbl.setText("��й�ȣ�� ����ġ�մϴ�.");
              }
    	  }
      }
      
   }
   
   public static void main(String[] args) {
     try {
       JoinMember jm = new JoinMember();
       
       jm.setVisible(true);
   } catch (Exception e) {
      // TODO: handle exception
   } 
     
      
   }
}