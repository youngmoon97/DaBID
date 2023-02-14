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
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

public class Mypage extends JFrame
implements ActionListener{
   
   private JFrame frame = new JFrame();
   
   JPanel myPagePanel, particPanel;
   JLabel logo, memberId, nowPwlbl, newPwlbl, newCheckPwlbl, particPhoto, particName, particPrice, particMemo
   ;
   JPasswordField nowPw, newPw, newCheckPw;
   JButton saveBtn, itemDeleteBtn, backBtn;
   TitledBorder soonTb, buyTb, sellTb, pwTb;
   JScrollPane particScroll;
   
   String myPageCt[] = {"��� ���� ��ǰ", "������ ��ǰ", "�Ǹ��� ��ǰ", "��й�ȣ ����"};
   JComboBox<String> myPageCb = new JComboBox<String>(myPageCt);
   
   public Mypage() {
      setTitle("DaBID ����������");
      setSize(1300, 900);
        setResizable(false);
        setLocationRelativeTo(null); //��� ���
        getContentPane().setLayout(null);
        Container c = getContentPane();
        
        //panel ����
        myPagePanel = new JPanel();
        myPagePanel.setBounds(180, 70, 1000, 700);
        myPagePanel.setLayout(null);
        
        // border ����
        soonTb = new TitledBorder(new LineBorder(Color.black,1,true),"�Ǹ� ����");
        soonTb.setTitleFont(new Font("����ü",0,15));
        
        buyTb = new TitledBorder(new LineBorder(Color.black,1,true),"���� �Ϸ�");
        buyTb.setTitleFont(new Font("����ü",0,15));
        
        sellTb = new TitledBorder(new LineBorder(Color.black,1,true),"�Ǹ� �Ϸ�");
        sellTb.setTitleFont(new Font("����ü",0,15));
        
        pwTb = new TitledBorder(new LineBorder(Color.black,1,true),"��й�ȣ ����");
        pwTb.setTitleFont(new Font("����ü",0,15));
        
        
        // ���������� �޺��ڽ� combobox 
        myPageCb.setBounds(20, 85, 150, 40);
        myPageCb.setFont(new Font("����ü",0,17));
        myPageCb.addActionListener(this);
        
        // index 0 (��� ���� ��ǰ)
        // ��� ���� ����Ʈ particPanel
        particPanel = new JPanel();
        particPanel.setLayout(null);
        particPanel.setBounds(10, 20, 940, 300);
        particPanel.setBorder(new LineBorder(Color.black, 1, true));
        
        // ��� ���� ����Ʈ ��ũ�� particScroll
        particScroll = new JScrollPane(myPagePanel);
        particScroll.setBounds(1127,16,20,682);
        
        // ��ǰ ���� label
        particPhoto = new JLabel("��ǰ �̹���");
        particName = new JLabel("��ǰ �̸�");
        particPrice = new JLabel("��ǰ ����");
        particMemo = new JLabel("��ǰ ����");
        
        particPhoto.setBounds(30, 50, 250, 200);
        particName.setBounds(300, 50, 290, 60);
        particPrice.setBounds(600, 50, 210, 60);
        particMemo.setBounds(300, 120, 510, 130);
        
        
        particPhoto.setFont(new Font("����ü", 0, 18));
        particName.setFont(new Font("����ü", 0, 18));
        particPrice.setFont(new Font("����ü", 0, 18));
        particMemo.setFont(new Font("����ü", 0, 18));
        
        particPhoto.setBorder(new LineBorder(Color.black,1,true));
        particName.setBorder(new LineBorder(Color.black,1,true));
        particPrice.setBorder(new LineBorder(Color.black,1,true));
        particMemo.setBorder(new LineBorder(Color.black,1,true));

        
        // ��ǰ ���� ��ư
        itemDeleteBtn = new JButton("��ǰ����");
        itemDeleteBtn.setBounds(830, 100, 100, 100);
        itemDeleteBtn.setFont(new Font("����ü", 0, 16));
       
        
        
        // index 4 (��й�ȣ ����)
        // ���� ��й�ȣ, �� ��й�ȣ, ��й�ȣ Ȯ�� label
        nowPwlbl = new JLabel("���� ��й�ȣ");
        newPwlbl = new JLabel("�� ��й�ȣ");
        newCheckPwlbl = new JLabel("��й�ȣ Ȯ��");
        
        nowPwlbl.setBounds(200, 170, 190, 30);
        newPwlbl.setBounds(200, 320, 190, 30);
        newCheckPwlbl.setBounds(200, 470, 190, 30);
        
        nowPwlbl.setFont(new Font("����ü", 1, 21));
        newPwlbl.setFont(new Font("����ü", 1, 21));
        newCheckPwlbl.setFont(new Font("����ü", 1, 21));
        
        // ���� ��й�ȣ, ����й�ȣ, ��й�ȣ Ȯ�� JPasswordField
        nowPw = new JPasswordField();
        newPw = new JPasswordField();
        newCheckPw = new JPasswordField();
        
        nowPw.setBounds(390, 170, 300, 30);
        newPw.setBounds(390, 320, 300, 30);
        newCheckPw.setBounds(390, 470, 300, 30);
        
        nowPw.setFont(new Font("����ü", 0, 21));
        newPw.setFont(new Font("����ü", 0, 21));
        newCheckPw.setFont(new Font("����ü", 0, 21));
        
        // save ��ư
        saveBtn = new JButton("����");
        saveBtn.setBounds(850,600, 80, 30);
        saveBtn.setFont(new Font("����ü", 0, 20));

        // �⺻
        // logo
        logo = new JLabel("DaBID");
        logo.setBounds(20,10, 50, 30);
        logo.setFont(new Font("����ü", 0, 20));
        
        //id
        memberId = new JLabel("���̵� : aaa");
        memberId.setBounds(1150, 20, 100, 30);
        memberId.setFont(new Font("����ü", 0, 15));
        
        // back��ư
        backBtn = new JButton("�ڷΰ���");
        backBtn.setBounds(1150,800, 100, 30);
        backBtn.setFont(new Font("����ü", 0, 15));
        backBtn.addActionListener(this);
        //add
        c.add(myPagePanel);
        c.add(logo);
        c.add(myPageCb);
        c.add(memberId);
        c.add(backBtn);
        
        
        validate();
   }
   
   public void statistics(int index) {
	   
	   myPagePanel.removeAll();
	   
	   if (index == 0) {
		   myPagePanel.setBorder(soonTb);
		   myPagePanel.add(particPanel);
		   myPagePanel.add(particScroll);
		   
		   particPanel.add(particPhoto);
		   particPanel.add(particName);
		   particPanel.add(particPrice);
		   particPanel.add(particMemo);
		   particPanel.add(itemDeleteBtn);
		   
		   
	   } else if (index == 1) {
		   myPagePanel.setBorder(buyTb);
	   } else if (index == 2) {
		   myPagePanel.setBorder(sellTb);
	   } else if (index == 3) {
		   myPagePanel.setBorder(pwTb);
		   myPagePanel.add(nowPwlbl);
	       myPagePanel.add(newPwlbl);
	       myPagePanel.add(newCheckPwlbl);
	       myPagePanel.add(nowPw);
	       myPagePanel.add(newPw);
	       myPagePanel.add(newCheckPw);
	       myPagePanel.add(saveBtn);
	   }
	   
	   
	   
	   
   }
   
   @Override
   public void actionPerformed(ActionEvent e) {
	   Object obj = e.getSource();
	   if (obj == myPageCb) {
			int index = myPageCb.getSelectedIndex();
			statistics(index);
			repaint(); 
		}
	   else if(obj==backBtn) {
		   try {
			   dispose();
			   Main main = new Main();
			   main.setVisible(true);
		   } catch (Exception e2) {
				e2.printStackTrace();
		   }
	   }
   }

   public static void main(String[] args) {
     
   }

}