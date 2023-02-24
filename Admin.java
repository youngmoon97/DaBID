package project1;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

public class Admin extends JFrame implements ActionListener {
   
   JPanel adminCategoryPanel, adminLogPanel, itemPanel;
   JLabel logo, memberId, categoryName;
   JButton backBtn;
   JComboBox<String> adminCb;
   JProgressBar categoryJpb;
   JTable biddingTb, bidendTb;
   JScrollPane biddingScrollPane, bidendScrollPane;
   Color color = new Color(240,240,240);
   LineBorder lb = new LineBorder(Color.black,1,true);
   LineBorder lb1 = new LineBorder(Color.white,0,true);
   
   String logId;
   //��ǰ �̸�", "�Ǹ��� ID","���� �ο�","���� ����"
   String[] cbList = {"ī�װ� �� �Ǹŷ�", "��� log"};
   String[] categoryNames = {"�����б��", "�Ƿ�","��Ȱ����","������/����","���/����/����","��Ƽ/�̿�","�ݷ�������ǰ","����/���׸���","����","����"};
   
   Container c = getContentPane();
   AuctionMgr mgr;
   
   public Admin(String logId) {
      // mgr 
      mgr = new AuctionMgr();
      
      // container
      setTitle("DaBID ������ ������");
      setSize(1300, 900);
      setResizable(false);
      setLayout(null);
      setLocationRelativeTo(null);
      // 
      adminCategoryPanel = new JPanel();
      adminCategoryPanel.setLayout(new GridLayout(0, 1, 10, 10));
      //
      adminCategoryPanel.setBounds(150, 140, 1000, 350);
      // index 1 log Panel
      adminLogPanel = new JPanel();
      adminLogPanel.setLayout(null);
      adminLogPanel.setBounds(60, 120, 1160, 670);
      // JComboBox
      //adminCb (������ �޺��ڽ�)
      adminCb = new JComboBox<>(cbList);
      adminCb.setBounds(60, 80, 200, 34);
      adminCb.setFont(new Font("���� ���", Font.BOLD, 17));
      adminCb.addActionListener(this);
      //backBtn (�ڷΰ���)
      backBtn = new JButton("�ڷΰ���");
      backBtn.setBounds(1150, 820, 120, 30);
      backBtn.setFont(new Font("���� ���", Font.BOLD, 15));
      backBtn.addActionListener(this);
      // memberId (��� ���̵� ��)
      memberId = new JLabel("���̵� : "+logId);
      memberId.setBounds(1150,20,150,30);
      memberId.setFont(new Font("���� ���", 0, 15));
       // logo (���� �ΰ�)
      logo = new JLabel(new ImageIcon(Login.class.getResource("./image/logo.png")));
      logo.setBounds(20, 25, 130, 40);
       
       //������
       //�ڷΰ��� ��ư ������
       backBtn.setBackground(Color.black);
       backBtn.setForeground(Color.white);
       backBtn.setBorderPainted(false);
       backBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
       this.add(backBtn);
       //�޺� �ڽ� ������
       adminCb.setBackground(color);
       adminCb.setBorder(lb);
       adminCb.setCursor(new Cursor(Cursor.HAND_CURSOR));
       this.add(adminCb);
       //���
       c.setBackground(Color.white);
       adminCategoryPanel.setBackground(Color.white);
       //Panel
      itemPanel = new JPanel();
      itemPanel.setLayout(null);
      c.add(adminCb);
      c.add(memberId);
      c.add(logo);
      c.add(backBtn);
      validate();
   }
   
   public JPanel statisticsData(int num, int values) {
	      
	      // ������ ���� Panel
	      itemPanel = new JPanel();
	      itemPanel.setLayout(null);
//	      itemPanel.setBounds(100, 130, 1100, 55);
	            
	      // ī�װ� �̸�
	      categoryName = new JLabel(categoryNames[num]);
	      categoryName.setFont(new Font("���� ���", Font.BOLD, 17));
	      categoryName.setHorizontalAlignment(SwingConstants.CENTER);
	      categoryName.setBounds(5, 5, 160, 30);
	            
	      // ī�װ� �׷���
	      JProgressBar categoryJpb = new JProgressBar();
	      categoryJpb.setBounds(190, 0, 800, 40);
	      categoryJpb.setValue(values);
	      
	      itemPanel.add(categoryName);
	      itemPanel.add(categoryJpb);
	      
	      //������
	      itemPanel.setBackground(Color.white);
	      categoryJpb.setForeground(Color.black);
	      
	      return itemPanel;
	   }

	   public void addData(int index) {
	      
	   
	      Vector<ItemBean> vlist;
	      vlist = mgr.getAdminCategory();
	      ItemBean ibean;
	      for (int i = 0; i < vlist.size(); i++) {
			ibean = vlist.get(i);
		}
	      if (index == 0) {
	    	 c.remove(adminLogPanel);
	         
	         
	         for (int i = 0; i < vlist.size(); i++) {
	            JPanel p;
	        	ibean = vlist.get(i);
	            p = statisticsData(ibean.getCategoryNum(),vlist.get(i).getAllCount());
	            
	            p.setPreferredSize(new Dimension(10, 20));
	            adminCategoryPanel.add(p);
	            c.add(adminCategoryPanel);
	         }
	      } else if (index == 1) {
	    	  //��ǰ �̸�", "�Ǹ��� ID","���� �ο�","���� ����"
	    	   //Object[][] listData = new Object[subjectList.size()][12];
	    	  c.remove(adminCategoryPanel);
		     
	    	  String ing_header[] = {"��ǰ �̸�", "�Ǹ��� ID","���� �ο�","�Ǹ� ����"};
	    	  String end_header[] = {"��ǰ �̸�", "�Ǹ��� ID","���� �ο�","���� ����"};
	    	  //Vector<String> ing_data = new Vector<>();
	    	  
	    	  DefaultTableModel ingModel = new DefaultTableModel(ing_header, 0);
	    	  DefaultTableModel endModel = new DefaultTableModel(end_header, 0);
	    	  mgr.getAuctionIng(ingModel);
	    	  mgr.getAuctionEnd(endModel);
	    	 
	    	  biddingTb = new JTable(ingModel);
	    	  bidendTb = new JTable(endModel);
	    	  biddingScrollPane  = new JScrollPane(biddingTb);
	    	  biddingScrollPane.setBounds(65,30,500,600);
	    	  biddingScrollPane.setFont(new Font("�������",0,20));
	    	  bidendScrollPane = new JScrollPane(bidendTb);
	    	  bidendScrollPane.setBounds(595,30,500,600);
	    	  
	     	  //������
	    	  adminLogPanel.setBackground(Color.white);
	    	  biddingTb.setBackground(Color.white);
	    	  biddingScrollPane.setBorder(lb1);
	    	  bidendScrollPane.setBorder(lb1);
	    	  biddingTb.getColumn("��ǰ �̸�").setPreferredWidth(200);
	    	  bidendTb.getColumn("��ǰ �̸�").setPreferredWidth(200);
	    	  biddingTb.setRowHeight(30);
	    	  bidendTb.setRowHeight(30);
	    	  biddingTb.setBorder(lb);
	    	  bidendTb.setBorder(lb);
	    	  biddingTb.getTableHeader().setBackground(Color.black);
	    	  biddingTb.getTableHeader().setFont(new Font("���� ���", Font.BOLD, 15));
	    	  biddingTb.getTableHeader().setForeground(Color.white);
	    	  biddingTb.setFont(new Font("���� ���", 0, 12));
	    	  bidendTb.getTableHeader().setBackground(Color.black);
	    	  bidendTb.getTableHeader().setFont(new Font("���� ���", Font.BOLD, 15));
	    	  bidendTb.getTableHeader().setForeground(Color.white);
	    	  bidendTb.setFont(new Font("���� ���", 0, 12));
	    	  
	    	  adminLogPanel.add(biddingScrollPane);
	    	  adminLogPanel.add(bidendScrollPane);
	    	  c.add(adminLogPanel);
	      }
	      
	      repaint();
	      validate();
	   }
	   

	   @Override
	   public void actionPerformed(ActionEvent e) {
	      logId = memberId.getText();
	      logId = logId.substring(logId.lastIndexOf(":")+1).trim();
	      
	      Object obj = e.getSource();
	      if (obj == adminCb) {
	         int index = adminCb.getSelectedIndex();
	         
	         addData(index);
	         repaint(); 
	      }else if(obj == backBtn) {
	         try {
	            dispose();
	            Main main = new Main(logId);
	            main.setVisible(true);
	         } catch (Exception e2) {
	            e2.printStackTrace();
	         }
	      }
	   }
	   
	   public static void main(String[] args) {
	         
	   }
	}