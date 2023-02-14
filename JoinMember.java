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
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.SwingConstants;

public class JoinMember extends JFrame
implements ActionListener{
   
   private JFrame frame = new JFrame();
   
   JPanel joinPanel;
   JLabel joinId, joinPw, joinPwCheck, joinEmail, joinAt, joinUseIdlbl, joinUsePwlbl;
   JTextField joinIdtxt, joinEmailtxt;
   JPasswordField joinPwtxt, joinPwChecktxt;
   JButton joinBtn, idCheckBtn, pwCheckBtn;
   String joinOk[] = {"��� ������ ���̵��Դϴ�.", "��� ���� ���̵��Դϴ�."};
   JComboBox<String> emailCb = new JComboBox<String>();
   
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
        //���̵�
        joinId = new JLabel("���̵� ");
        joinId.setBounds(158, 115, 100, 30);
        joinId.setFont(new Font("����ü", 0, 20));
        //���̵� �ߺ�Ȯ�� label
      /*
       * if(=1) { joinUseIdlbl= new JLabel(joinOk[0]); }else if(=0) {
       * 
       * }
       */
        joinUseIdlbl = new JLabel("��� ������ ���̵��Դϴ�.");
        joinUseIdlbl.setBounds(380, 140, 300, 30);
        joinUseIdlbl.setFont(new Font("����ü", 0, 10));
        //��й�ȣ
        joinPw = new JLabel("��й�ȣ ");
        joinPw.setBounds(158, 215, 110, 30);
        joinPw.setFont(new Font("����ü", 0, 20));
        //��й�ȣ Ȯ��
        joinPwCheck = new JLabel("��й�ȣ Ȯ��");
        joinPwCheck.setBounds(158, 315, 160, 30);
        joinPwCheck.setFont(new Font("����ü", 0, 20));
        //��й�ȣ �ߺ�Ȯ��
        //char[] pwcheck = joinPwtxt.getPassword();
        //String pw="";
        //for (int i = 0; i < pwcheck.length; i++) {
      //   Character.toString(pwcheck[i]);
      //   pw += (pw.equals("")) ? ""+pwcheck[i]+"" : ""+pwcheck[i]+"";   
      //}
        //char[] pwcheck2 = joinPwtxt.getPassword();
        //String pw2="";
       // for (int i = 0; i < pwcheck2.length; i++) {
      //   Character.toString(pwcheck2[i]);
      //   pw2 += (pw2.equals("")) ? ""+pwcheck2[i]+"" : ""+pwcheck2[i]+"";   
      //}
       joinUsePwlbl = new JLabel("��й�ȣ Ȯ���ϴ� ���Դϴ�");

        //if(pw.equals(pw2)) {
        //   joinUsePwlbl.setText("��й�ȣ�� ��ġ�մϴ�.");
        //}else if(pw!=pw2){
        //   joinUsePwlbl.setText("��й�ȣ�� ����ġ�մϴ�.");

        //}
        joinUsePwlbl.setBounds(380, 340, 300, 30);
        joinUsePwlbl.setFont(new Font("����ü", 0, 10));
        //�̸���
        joinEmail = new JLabel("�̸��� ");
        joinEmail.setBounds(158, 410, 100, 30);
        joinEmail.setFont(new Font("����ü", 0, 20));
        //���̵� �Է�ĭ
        joinIdtxt = new JTextField();
        joinIdtxt.setBounds(328, 115, 150, 30);
        joinIdtxt.setFont(new Font("����ü", 0, 15));
        //��й�ȣ Ȯ�� �Է�ĭ
        joinPwtxt = new JPasswordField();
        joinPwtxt.setBounds(328, 215, 232, 30);
        joinPwtxt.setFont(new Font("����ü", 0, 15));
        //��й�ȣ �ߺ�Ȯ�� �Է�ĭ
        joinPwChecktxt = new JPasswordField();
        joinPwChecktxt.setBounds(328, 315, 150, 30);
        joinPwChecktxt.setFont(new Font("����ü", 0, 15));
        //�̸���
        joinEmailtxt = new JTextField();
        joinEmailtxt.setBounds(328, 415, 103, 30);
        joinEmailtxt.setFont(new Font("����ü", 0, 15));
        //�̸��� @
        joinAt = new JLabel("@");
        joinAt.setHorizontalAlignment(SwingConstants.CENTER);
        joinAt.setBounds(430, 415, 20, 30);
        joinAt.setFont(new Font("����ü", 0, 20));
        emailCb.setBackground(new Color(255, 255, 255));
        //�̸��� �޺��ڽ�
        emailCb.setBounds(450,415,110,30);
        emailCb.setFont(new Font("����ü", 0, 15));
        emailCb.addItem("naver.com");
        emailCb.addItem("daum.net");
        emailCb.addItem("gmail.com");
        //TODO ���õ� �̸��� ����
        //ȸ������ ��ư
        joinBtn = new JButton("ȸ������");
        joinBtn.setBackground(new Color(255, 255, 255));
        joinBtn.setBounds(300,500,100,30);
        joinBtn.setFont(new Font("����ü", 0, 15));
        joinBtn.addActionListener(this);
        //TODO ȸ�����������ϱ� ���
        //���̵��ߺ�Ȯ�� ��ư
        idCheckBtn = new JButton("�ߺ�Ȯ��");
        idCheckBtn.setBackground(new Color(255, 255, 255));
        idCheckBtn.setBounds(480,115,80,30);
        idCheckBtn.setFont(new Font("����ü", 0, 10));
        //��й�ȣ �ߺ�Ȯ�� ��ư
        pwCheckBtn = new JButton("�ߺ�Ȯ��");
        pwCheckBtn.setBackground(new Color(255, 255, 255));
        pwCheckBtn.setBounds(480,315,80,30);
        pwCheckBtn.setFont(new Font("����ü", 0, 10));
        //add
        joinPanel.add(joinId);
        joinPanel.add(joinPw);
        joinPanel.add(joinPwCheck);
        joinPanel.add(joinEmail);
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
        c.add(joinPanel);
        validate();
   }
   
   @Override
   public void actionPerformed(ActionEvent e) {
	   Object obj = e.getSource();
	   if(obj==joinBtn) {
		   try {
			   dispose();
				Login login = new Login();
				login.setVisible(true);
			} catch (Exception e2) {
				e2.printStackTrace();
			}
	   }else if(obj==idCheckBtn) {
		   //id �ߺ� ���� Ȯ��iduselbl
	   }else if(obj==pwCheckBtn) {
		   //pw ������ Ȯ��
	   }
	   
   }
   
   public static void main(String[] args) {
     
      
   }
}