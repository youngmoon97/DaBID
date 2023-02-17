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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

public class Auction extends JFrame
implements ActionListener{
	
	JPanel itemPanel,commentPanel;
	JLabel logo, memberId, itemName, itemPhoto, itemMemo, currentPrice, purchaserCount, auctionPriceLbl, auctionTime;
	JTextArea commentArea;
	JTextField comment, bidpriceTf;
	JButton commentBtn, auctionBtn,backBtn; 
	TitledBorder itemTb, commentTb;
	String logId;
	Font f = new Font("����ü",0,15);
	
	public Auction() {
		setTitle("DaBID ���������");
	    setSize(1300,900);
	    setResizable(false);
	    setLayout(null);
	    Container c = getContentPane();
	    
	    itemTb = new TitledBorder(new LineBorder(Color.black,1,true),"��ǰ����");
	    commentTb = new TitledBorder(new LineBorder(Color.black,1,true),"��� �� ����");	    
	    itemTb.setTitleFont(new Font("����ü",0,25));
	    commentTb.setTitleFont(new Font("����ü",0,25));
	    //���� �г� ( ��ǰ ����)
	    itemPanel = new JPanel();
	    itemPanel.setLayout(null);
	    itemPanel.setBounds(60, 80, 550, 700);
	    itemPanel.setBorder(itemTb);	
	     //��ǰ���̺�
	    itemName = new JLabel("��ǰ�� : ");
	    itemName.setBounds(60, 60, 100, 30);
	    itemName.setFont(new Font("����ü", 0, 15));
	    //��ǰ�̹���
	    itemPhoto = new JLabel("�̹���");
	    itemPhoto.setBorder(new LineBorder(Color.black,1,true));
	    itemPhoto.setBounds(60, 90, 450, 400);
	     //��ǰ����
	    itemMemo= new JLabel("��ǰ����");
	    itemMemo.setBorder(new LineBorder(Color.black,1,true));
	    itemMemo.setBounds(60, 500, 450, 100);
	     //��ǰ����������
	    currentPrice= new JLabel("���� ������ : ");
	    currentPrice.setBorder(new LineBorder(Color.black,1,true));
	    currentPrice.setBounds(60, 610, 200, 50);
	    currentPrice.setFont(new Font("����ü", 0, 15));
	     //���������ο� 
	    purchaserCount= new JLabel("���� ���� �ο� :  ");	     
	    purchaserCount.setBorder(new LineBorder(Color.black,1,true));
	    purchaserCount.setBounds(300, 610, 200, 50);
	    purchaserCount.setFont(new Font("����ü", 0, 15));
	    //
	    itemPanel.add(itemName);
	    itemPanel.add(itemMemo);
	    itemPanel.add(itemPhoto);
	    itemPanel.add(purchaserCount);
	    itemPanel.add(currentPrice);
	    
	    //������ �г�(���+����)
	    commentPanel = new JPanel();
	    commentPanel.setLayout(null);
	    commentPanel.setBounds(660, 80, 550, 700);
	    commentPanel.setBorder(commentTb);
	    //textarea
	    commentArea = new JTextArea();
	    commentArea.setBounds(60, 90, 450, 450);
	    commentArea.setBorder(new LineBorder(Color.black,1,true));
	    //�����ð�
	    auctionTime = new JLabel("���� �ð� : ");
	    auctionTime.setBounds(400,30,130,30);
	    auctionTime.setBorder(new LineBorder(Color.black,1,true));
	    auctionTime.setFont(f);
	    //comment����Է�
	    comment = new JTextField("����� �Է��ϼ���.");
	    comment.setBounds(60, 540, 380, 30);
	    comment.setBorder(new LineBorder(Color.black,1,true));
	    comment.setFont(f);
	    //comment��ư����
	    commentBtn = new JButton("����");
	    commentBtn.setBounds(440, 540, 70, 30);
	    commentBtn.setBorder(new LineBorder(Color.black,1,true));
	    commentBtn.setFont(f);
	    commentBtn.addActionListener(this);
	    //�������ݷ��̺�
	    auctionPriceLbl = new JLabel("��������");
	    auctionPriceLbl.setBounds(220, 600, 80, 30);
	    auctionPriceLbl.setBorder(new LineBorder(Color.black,1,true));
	    auctionPriceLbl.setFont(f);
	    //��������TF
	    bidpriceTf = new JTextField();
	    bidpriceTf.setBounds(300, 600, 120, 30);
	    bidpriceTf.setBorder(new LineBorder(Color.black,1,true));
	    //������ư
	    auctionBtn = new JButton("�����ϱ�");
	    auctionBtn.setBounds(400, 600, 70, 30);
	    auctionBtn.setBorder(new LineBorder(Color.black,1,true));
	    auctionBtn.setFont(f);
	    auctionBtn.addActionListener(this);
	    //
	    commentPanel.add(commentArea);
	    commentPanel.add(auctionTime);
	    commentPanel.add(comment);
		commentPanel.add(commentBtn); 
		commentPanel.add(auctionBtn);
		commentPanel.add(auctionPriceLbl); 
		commentPanel.add(bidpriceTf);
		backBtn = new JButton("�ڷΰ���");
		backBtn.setBounds(1150, 820, 120, 30);
		backBtn.setFont(new Font("����ü", 0, 15));
		backBtn.addActionListener(this);
		//
		logo = new JLabel(new ImageIcon(Login.class.getResource("./image/logo.png")));
	    logo.setBounds(20,20,130,40);
	    //
	     c.add(logo);
	    c.add(itemPanel);
	    c.add(commentPanel);
	    c.add(backBtn);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		logId = memberId.getText();
		logId = logId.substring(logId.lastIndexOf(":")+1).trim();
		
		Object obj = e.getSource();
		if(obj==commentBtn/*��۴ޱ�*/) {
			 //
		}else if(obj==auctionBtn/*�����ϱ�*/){
			//
		}else if(obj==backBtn) {
			dispose();
			try {
				Main main = new Main(logId);
				main.setVisible(true);
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	}
	
	
	public static void main(String[] args) {
		try {
			Auction ac = new Auction();
			ac.setVisible(true);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
}
