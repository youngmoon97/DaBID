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
      setTitle("DaBID ���̵�/��й�ȣ ã��");
      setSize(700, 600);
      setResizable(false);
      setLocationRelativeTo(null);
      getContentPane().setLayout(null);
      Container c = getContentPane();
      //Id ����
      idTb = new TitledBorder(new LineBorder(Color.black,2,true),"���̵� ã��");
      idTb.setTitleFont(new Font("���� ���", Font.BOLD, 20));
      //Pw ����
      pwTb = new TitledBorder(new LineBorder(Color.BLACK,2,true),"��й�ȣ ã��");
      pwTb.setTitleFont(new Font("���� ���", Font.BOLD, 20));
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
      idFindName.setFont(new Font("���� ���", Font.BOLD, 15));
      idFindEmail = new JLabel("�̸���");
      idFindEmail.setBounds(120, 100, 80, 50);
      idFindEmail.setFont(new Font("���� ���", Font.BOLD, 15));
      //�̸� �̸��� �Է�ĭ -->���̵�ã��
      idFindNametxt = new JTextField();
      idFindEmailtxt = new JTextField();
      idFindNametxt.setBounds(190,75,100,25);
      idFindEmailtxt.setBounds(190,115,100,25);
      //���̵�ã���ư
      findIdBtn = new JButton("���̵�ã��");
      findIdBtn.setBounds(310,75,110,65);
      findIdBtn.addActionListener(this);
      //�̸� ���̵� �̸��� -->��й�ȣã�� 
      pwFindName = new JLabel("�� ��");
      pwFindName.setBounds(120, 60, 50, 50);
      pwFindName.setFont(new Font("���� ���", Font.BOLD, 15));
      pwFindId = new JLabel("���̵�");
      pwFindId.setBounds(120, 100, 80, 50);
      pwFindId.setFont(new Font("���� ���", Font.BOLD, 15));
      pwFindEmail = new JLabel("�̸���");
      pwFindEmail.setBounds(120, 140, 80, 50);
      pwFindEmail.setFont(new Font("���� ���", Font.BOLD, 15));
      //�̸� ���̵� �̸��� �Է�ĭ -->��й�ȣã��
      pwFindEmailtxt = new JTextField();
      pwFindIdtxt = new JTextField();
      pwFindNametxt = new JTextField();
      pwFindNametxt.setBounds(190,70,100,25);
      pwFindIdtxt.setBounds(190,110,100,25);
      pwFindEmailtxt.setBounds(190,150,100,25);
      //��й�ȣã�� ��ư
      findPwBtn = new JButton("��й�ȣ ã��");
      findPwBtn.setBounds(310,70,110,105);
      findPwBtn.addActionListener(this);
      //�ڷΰ���btn
      backBtn = new JButton("�ڷΰ���");
      backBtn.setBounds(570,520,100,30);
      backBtn.setFont(new Font("���� ���",Font.BOLD,13));
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
      //logo
      logo = new JLabel(new ImageIcon(Login.class.getResource("./image/logo.png")));
     logo.setBounds(270,10,130,40);
     //������
     //����
      c.setBackground(Color.white);
      findIdPanel.setBackground(Color.white);
      findPwPanel.setBackground(Color.white);
      //TextField ��
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
      //���̵�ã���ư ������
      findIdBtn.setBackground(Color.black);
      findIdBtn.setForeground(Color.white);
      findIdBtn.setBorderPainted(false);
      findIdBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
      this.add(findIdBtn);
      //��й�ȣã���ư ������
      findPwBtn.setBackground(Color.black);
      findPwBtn.setForeground(Color.white);
      findPwBtn.setBorderPainted(false);
      findPwBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
      this.add(findPwBtn);
      //�ڷΰ��� ��ư ������
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
    		  alarm.showMessageDialog(null, "�̸��� �̸����� Ȯ���ϼ���","���",JOptionPane.ERROR_MESSAGE);
    		  idFindName.requestFocus();
    	  }else {
    		  String memName , memEmail;
    		  memName = idFindNametxt.getText();
    		  memEmail = idFindEmailtxt.getText();
    		  MemberBean mbean = mgr.getMemberId(memName, memEmail);
    		  String memId = mbean.getMemberId();
    		  alarm.showMessageDialog(this, "���̵� : "+memId);
    	  }

      }else if(obj==findPwBtn) {
    	  if(pwFindIdtxt.getText().isEmpty()||pwFindNametxt.getText().equals("")||pwFindEmailtxt.getText().equals("")) {
    		  alarm.showMessageDialog(null, "�̸� �Ǵ� ���̵�, �̸����� Ȯ���ϼ���");
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