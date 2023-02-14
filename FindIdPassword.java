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
      setTitle("DaBID ���̵�/��й�ȣ ã��");
      setSize(700, 600);
      setResizable(false);
      setLocationRelativeTo(null);
      getContentPane().setLayout(null);
      Container c = getContentPane();
      //Id ����
      idTb = new TitledBorder(new LineBorder(Color.BLACK,1,true),"���̵� ã��");
      idTb.setTitleFont(new Font("����ü", 0, 20));
      //Pw ����
      pwTb = new TitledBorder(new LineBorder(Color.BLACK,1,true),"��й�ȣ ã��");
      pwTb.setTitleFont(new Font("����ü", 0, 20));
      //���̵� ã��(Id �г�)
      findIdPanel = new JPanel();
      findIdPanel.setLayout(null);
      findIdPanel.setBounds(60, 50, 550, 200);
      findIdPanel.setBorder(idTb);
      //��й�ȣ ã��(Pw �г�)
      findPwPanel = new JPanel();
      findPwPanel.setLayout(null);
      findPwPanel.setBounds(60, 280, 550, 230);
      findPwPanel.setBorder(pwTb);
      //�̸� �̸��� Label -->���̵�ã��
      idFindName = new JLabel("�� ��");
      idFindName.setBounds(120, 60, 50, 50);
      idFindName.setFont(new Font("����ü", 0, 15));
      idFindEmail = new JLabel("�̸���");
      idFindEmail.setBounds(120, 100, 80, 50);
      idFindEmail.setFont(new Font("����ü", 0, 15));
      //�̸� �̸��� �Է�ĭ -->���̵�ã��
      idFindNametxt = new JTextField();
      idFindEmailtxt = new JTextField();
      idFindNametxt.setBounds(180,70,100,25);
      idFindEmailtxt.setBounds(180,110,100,25);
      //���̵�ã���ư
      findIdBtn = new JButton("���̵�ã��");
      findIdBtn.setBounds(300,70,120,60);
      //�̸� ���̵� �̸��� -->��й�ȣã�� 
      pwFindName = new JLabel("�� ��");
      pwFindName.setBounds(120, 60, 50, 50);
      pwFindName.setFont(new Font("����ü", 0, 15));
      pwFindId = new JLabel("���̵�");
      pwFindId.setBounds(120, 100, 80, 50);
      pwFindId.setFont(new Font("����ü", 0, 15));
      pwFindEmail = new JLabel("�̸���");
      pwFindEmail.setBounds(120, 140, 80, 50);
      pwFindEmail.setFont(new Font("����ü", 0, 15));
      //�̸� ���̵� �̸��� �Է�ĭ -->��й�ȣã��
      pwFindEmailtxt = new JTextField();
      pwFindIdtxt = new JTextField();
      pwFindNametxt = new JTextField();
      pwFindNametxt.setBounds(180,70,100,25);
      pwFindIdtxt.setBounds(180,110,100,25);
      pwFindEmailtxt.setBounds(180,150,100,25);
      //��й�ȣã�� ��ư
      findPwBtn = new JButton("��й�ȣ ã��");
      findPwBtn.setBounds(300,70,120,100);
      //�ڷΰ���btn
      backBtn = new JButton("�ڷΰ���");
      backBtn.setBackground(new Color(255, 255, 255));
      backBtn.setBounds(580,520,90,30);
      backBtn.setFont(new Font("����ü",0,15));
      backBtn.addActionListener(this);
      //���̵�add   
      findIdPanel.add(idFindName);
      findIdPanel.add(idFindEmail);
      findIdPanel.add(idFindNametxt);
      findIdPanel.add(idFindEmailtxt);
      findIdPanel.add(findIdBtn);
      //��й�ȣadd
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