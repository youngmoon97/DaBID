package project1;

import java.awt.Button;
import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

public class Main extends JFrame
implements ActionListener{
	
	JPanel itemPanel,catagoryPanel;
	JLabel logo,memberId, itemName, itemPhoto, itemMemo, currentPrice, purchaserCount;
	JTextArea catagoryArea;
	JButton manegerBtn, registerBtn, backBtn,myPageBtn; //frame
	TitledBorder hotbidTb, commentTb;
	JOptionPane alarm = new JOptionPane();
	//TODO ī�װ� ������ ��������
	String catagoryName[] = {"�����б��","���/����/����","����/���׸���","������/����","��Ȱ����","�Ƿ�","�ݷ�������ǰ","��Ƽ/�̿�","�߰���","����"};
	
	Button catagoryNameBtn[] = new Button[catagoryName.length];
	
	public Main() {
	     setTitle("DaBID ����������");
	     setSize(1300,900);
	     setResizable(false);
	     setLayout(null);
	     Container c = getContentPane();
	     hotbidTb = new TitledBorder(new LineBorder(Color.black,1,true),"HOT BID");
	     commentTb = new TitledBorder(new LineBorder(Color.black,1,true),"ī�װ�");
	     hotbidTb.setTitleFont(new Font("����ü",0,25));
	     commentTb.setTitleFont(new Font("����ü",0,25));
	     //���� �г� ( ��ǰ ����)
	     itemPanel = new JPanel();
	     itemPanel.setLayout(null);
	     itemPanel.setBounds(60, 80, 550, 700);
	     itemPanel.setBorder(hotbidTb);	
	     //TODO ��ǰ���̺�
	     itemName = new JLabel("��ǰ�� : ");
	     itemName.setBounds(60, 60, 100, 30);
	     itemName.setFont(new Font("����ü", 0, 15));
	     //TODO ��ǰ�̹���
	     itemPhoto = new JLabel("�̹���");
	     itemPhoto.setBorder(new LineBorder(Color.black,1,true));
	     itemPhoto.setBounds(60, 90, 450, 400);
	     //TODO ��ǰ����
	     itemMemo= new JLabel("��ǰ����");
	     itemMemo.setBorder(new LineBorder(Color.black,1,true));
	     itemMemo.setBounds(60, 500, 450, 100);
	     //TODO ��ǰ����������
	     currentPrice= new JLabel("���� ������ : ");
	     currentPrice.setBorder(new LineBorder(Color.black,1,true));
	     currentPrice.setBounds(60, 610, 200, 50);
	     currentPrice.setFont(new Font("����ü", 0, 15));
	     //TODO ���������ο� 
	     purchaserCount= new JLabel("���� ���� �ο� :  ");
	     purchaserCount.setBorder(new LineBorder(Color.black,1,true));
	     purchaserCount.setBounds(300, 610, 200, 50);
	     purchaserCount.setFont(new Font("����ü", 0, 15));

	     ////
	     itemPanel.add(itemName);
	     itemPanel.add(itemPhoto);
	     itemPanel.add(itemMemo);
	     itemPanel.add(purchaserCount);
	     itemPanel.add(currentPrice);

	     //������ �г�(ī�װ�)
	     catagoryPanel = new JPanel();
	     catagoryPanel.setLayout(null);
	     catagoryPanel.setBounds(660, 80, 550, 350);
	     catagoryPanel.setBorder(commentTb);
	     
	     for (int i = 0; i < catagoryName.length; i++) {//w=550,h700
	    	int x=30;
	    	int y=40;
	    	int  w= 140; //����
	    	int h = 40; //����
	    	 catagoryNameBtn[i] = new Button(catagoryName[i]);
	    	 catagoryNameBtn[i].setBounds(x+(i%3)*(w+x),y+(i/3)*70,w,h);
		     catagoryNameBtn[i].setFont(new Font("����ü", 0, 15));
		     catagoryNameBtn[i].addActionListener(this);
		     catagoryPanel.add(catagoryNameBtn[i]);		    
	     }
	     //��ư 2��+�̸����̺�
	     manegerBtn = new JButton("�����ڸ��");
	     backBtn = new JButton("�ڷΰ���");
	     registerBtn = new JButton("��ǰ���");
	     memberId = new JLabel("���̵� : aaa");
	     
	     manegerBtn.setBounds(1000, 820, 120, 30);
	     backBtn.setBounds(1150, 820, 120, 30);
	     registerBtn.setBounds(1000, 20, 100, 30);
	     memberId.setBounds(1150,20,100,30);
	     
	     manegerBtn.setFont(new Font("����ü", 0, 15));
	     backBtn.setFont(new Font("����ü", 0, 15));
	     registerBtn.setFont(new Font("����ü", 0, 15));
	     memberId.setFont(new Font("����ü", 0, 15));
	     
	     manegerBtn.addActionListener(this);
	     backBtn.addActionListener(this);
	     registerBtn.addActionListener(this);
	     //logo
	     logo = new JLabel(new ImageIcon(Login.class.getResource("./image/logo.png")));
	     logo.setBounds(20,20,150,50);
	     //logo.setFont(new Font("����ü", 1, 25));
	   //mypagebtn
	     myPageBtn = new JButton("����������");
	     myPageBtn.setBounds(1150	, 50, 100,30);
	     myPageBtn.setFont(new Font("����ü",0,12));
	     myPageBtn.addActionListener(this);
	    //add
	     c.add(myPageBtn);
	     c.add(logo);
	     c.add(memberId);
	     c.add(itemPanel);
	     c.add(catagoryPanel);
	     c.add(manegerBtn);
	     c.add(registerBtn);
	     c.add(backBtn);
	     validate();
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		//TODO
		Object obj = e.getSource();
		if(obj==registerBtn/*��ǰ���*/) {
			 try {
				 dispose();
				itemRegister ir = new itemRegister();
				ir.setVisible(true);
			} catch (Exception e2) {
				e2.printStackTrace();
			} {
				 
			 }
		}else if(obj==manegerBtn/*������*/){
			//if(){//����ok
			try {
				dispose();
				Admin admin = new Admin();
				admin.setVisible(true);
			} catch (Exception e2) {
				e2.printStackTrace();
			}
				//new Manager();
			//}else{
			alarm.showMessageDialog(null, "������ �����ϴ�.");
		}else if(obj==backBtn) {//�ڷΰ���
			alarm.showMessageDialog(null, "���� �������Դϴ�.");
		}else if(obj==myPageBtn) {//����������
			try {
				dispose();
				Mypage mp = new Mypage();
				mp.setVisible(true);
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	}
	
	public static void main(String[] args) {
	
		
	}
	

}
