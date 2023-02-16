package project1;

import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class Admin extends JFrame implements ActionListener {
	
	JPanel adminPanel;
	JLabel categoryName, memberId, biddingLb, bidendLb, logo;
	JButton backBtn;
	JComboBox<String> adminCb;
	JProgressBar categoryJpb, priceJpb;
	JScrollPane biddingJsp, bidendJsp;
	JTable biddingTb, bidendTb;
	
	String[] cbList = {"ī�װ� �� ���", "�ݾ� �� ���", "��� log"};
	String[] categoryNames = {"�����б��","���/����/����","����/���׸���","������/����","��Ȱ����","�Ƿ�","�ݷ�������ǰ","��Ƽ/�̿�","�߰���","����"};
	
	String bidding_header[] = {"��ǰ �̸�", "�Ǹ��� ID", "���� �ο�", "���� ����"};
	String bidend_header[] = {"��ǰ �̸�", "�Ǹ��� ID", "������ ID", "���� �ο�", "���� ����"};
	
	String bidding_contents[][] = {
			{"��������", "aaa", "4", "5600"},
			{"���̵𤽤�", "ccc", "6", "19000"}
	};
	
	String bidend_contents[][] = {
			{"��������", "aaa", "bbb", "4", "5600"},
			{"���̵𤽤�", "ccc", "aaa", "6", "19000"}
	};
	
	public Admin() {
		
		setTitle("DaBID ������ ������");
		setSize(1300, 900);
		setResizable(false);
		setLayout(null);
		
		Container c = getContentPane();
		
		adminPanel = new JPanel();
		adminPanel.setLayout(null);
		adminPanel.setBounds(60, 98, 1150, 682);
		
		// JComboBox
		// adminCb (������ �޺��ڽ�)
		adminCb = new JComboBox<>(cbList);
		adminCb.setBounds(60, 60, 200, 34);
		adminCb.setFont(new Font("����ü", 0, 17));
		adminCb.addActionListener(this);
		
		// JLabel
		// categoryName (ī�װ� �̸� ��)
		
		// memberId (��� ���̵� ��)
		memberId = new JLabel("���̵� : aaa");
	    memberId.setBounds(1150, 20, 100, 30);
	    memberId.setFont(new Font("����ü", 0, 15));
	    // biddingLb (�������� ��� ��)
	    biddingLb = new JLabel("�������� ���");
	    biddingLb.setBounds(250, 25, 221, 27);
	    biddingLb.setFont(new Font("����ü", 0, 19));
	    // bidendLb (����� ��� ��)
	    bidendLb = new JLabel("����� ���");
	    bidendLb.setBounds(790, 25, 221, 27);
	    bidendLb.setFont(new Font("����ü", 0, 19));
	    // logo (���� �ΰ�)
	    logo = new JLabel(new ImageIcon(Login.class.getResource("./image/logo.png")));
		logo.setBounds(20,20,130,40);
		 
	    
	    // JButton
	    // backBtn (�ڷΰ���)
	    backBtn = new JButton("�ڷΰ���");
	    backBtn.setBounds(1150, 800, 120, 30);
	    backBtn.setFont(new Font("����ü", 0, 15));
	    backBtn.addActionListener(this);
	    
	    // JProgressBar
	    // categoryJpb (ī�װ� ���� ��)
	    JProgressBar categoryJpb = new JProgressBar();
	    // priceJpb (�ݾ� ���� ��)
	    JProgressBar priceJpb = new JProgressBar();
	    
		
		
		// JTable
		// biddingTb (�������� ��� ���̺�)
		DefaultTableModel biddingMd = new DefaultTableModel(bidding_contents, bidding_header);
		biddingTb = new JTable(biddingMd);
		// bidendTb (����� ��� ���̺�)
		DefaultTableModel bidendMd = new DefaultTableModel(bidend_contents, bidend_header);
		bidendTb = new JTable(bidendMd);
		
		// �гο� �߰�
		c.add(adminPanel);
		c.add(adminCb);
		c.add(memberId);
		c.add(backBtn);
		c.add(logo);
		
		
		validate();
		
	}
	
	public void statistics(int index) {
		
		adminPanel.removeAll();
		
		int n = 10;
		int [] x = {60, 200, 1100};
		int y = 50;
		
		if (index == 0) {
			// ī�װ��� ���
			for (int i = 0; i < n; i++) {
				// ī�װ� �̸�
				categoryName = new JLabel("ī�װ�" + i);
				categoryName.setFont(new Font("����ü", 0, 17));
				categoryName.setBounds(x[0], y, 200, 35);
				adminPanel.add(categoryName);
				
				// ī�װ� �׷���
				JProgressBar categoryJpb = new JProgressBar();
				categoryJpb.setBounds(x[1], y, 900, 35);
				categoryJpb.setValue((int)Math.random() * 100);
				adminPanel.add(categoryJpb);
				
				y += 60;
			}
			
		} else if (index == 1) {
			// �ݾ׺� ���
			for (int i = 0; i < n; i++) {
				// ī�װ� �̸�
				categoryName = new JLabel("ī�װ�" + i);
				categoryName.setFont(new Font("����ü", 0, 17));
				categoryName.setBounds(x[0], y, 200, 35);
				adminPanel.add(categoryName);
				
				// ī�װ� �׷���
				JProgressBar priceJpb = new JProgressBar();
				priceJpb.setBounds(x[1], y, 900, 35);
				priceJpb.setValue((int)Math.random() * 100);
				adminPanel.add(priceJpb);
				
				y += 60;
			}
		} else if (index == 2) {
			// ��� log
			adminPanel.add(biddingLb);
			adminPanel.add(bidendLb);
			
			// biddingJsp (�������� ��� ��ũ��)
			// biddingTb ����
			JScrollPane biddingJsp = new JScrollPane(biddingTb);
			biddingJsp.setBounds(60, 65, 480, 600);
			
			adminPanel.add(biddingJsp);
			biddingJsp.setViewportView(biddingTb);
			
			// bidendJsp (����� ��� ��ũ��)
			// bidendTb ����
			JScrollPane bidendJsp = new JScrollPane();
			bidendJsp.setBounds(600, 65, 480, 600);
			
			adminPanel.add(bidendJsp);
			bidendJsp.setViewportView(bidendTb);
		}
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		if (obj == adminCb) {
			int index = adminCb.getSelectedIndex();
			statistics(index);
			repaint(); 
		}else if(obj == backBtn) {
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
