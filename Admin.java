package project1;

import java.awt.Color;
import java.awt.Container;
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
import javax.swing.table.DefaultTableModel;

public class Admin extends JFrame implements ActionListener {
   
   JPanel adminCategoryPanel, adminLogPanel, itemPanel;
   JLabel logo, memberId, categoryName;
   JButton backBtn;
   JComboBox<String> adminCb;
   JProgressBar categoryJpb;
   JTable biddingTb, bidendTb;
   JScrollPane biddingScrollPane, bidendScrollPane;
   
   String logId;
   
   String[] cbList = {"ī�װ� �� ���", "��� log"};
   String[] categoryNames = {"�����б��", "�Ƿ�","��Ȱ����","������/����","���/����/����","��Ƽ/�̿�","�ݷ�������ǰ","����/���׸���","����","����"};
   
   String bidding_header[] = {"��ǰ �̸�", "�Ǹ��� ID", "���� �ο�", "���� ����"};
   String bidend_header[] = {"��ǰ �̸�", "�Ǹ��� ID", "���� �ο�","���� ����"};

   String endContents[][];
   DefaultTableModel biddingDtm = new DefaultTableModel(bidding_header, 0);
   DefaultTableModel bidendDtm = new DefaultTableModel(bidend_header, 0);
   
   AuctionMgr mgr;
   
   public Admin(String logId) {
      // mgr ��������
      mgr = new AuctionMgr();
      
      // container
      setTitle("DaBID ������ ������");
      setSize(1300, 900);
      setResizable(false);
      setLayout(null);
      setLocationRelativeTo(null);
      
      Container c = getContentPane();
      
      // index 0 ī�װ� Panel
      adminCategoryPanel = new JPanel();
      adminCategoryPanel.setLayout(new GridLayout(0, 1, 10, 10));
      
      // ������ �� �� �־�� �����ϱ� (ī�װ�)
      adminCategoryPanel.setBounds(50, 130, 1200, 350);
      
      // index 1 ��� log Panel
      adminLogPanel = new JPanel();
      adminLogPanel.setLayout(new GridLayout(1, 0, 10, 10));
      adminLogPanel.setBounds(40, 120, 610, 670);
      
      // JComboBox
      // adminCb (������ �޺��ڽ�)
      adminCb = new JComboBox<>(cbList);
      adminCb.setBounds(60, 80, 200, 34);
      adminCb.setFont(new Font("����ü", 0, 17));
      adminCb.addActionListener(this);
      // backBtn (�ڷΰ���)
       backBtn = new JButton("�ڷΰ���");
       backBtn.setBounds(1150, 820, 120, 30);
       backBtn.setFont(new Font("����ü", 0, 15));
       backBtn.addActionListener(this);
      
      // memberId (��� ���̵� ��)
      memberId = new JLabel("���̵� : aaa");
      memberId.setBounds(1150, 20, 100, 30);
      memberId.setFont(new Font("����ü", 0, 15));
      
      // logo (���� �ΰ�)
       logo = new JLabel(new ImageIcon(Login.class.getResource("./image/logo.png")));
      logo.setBounds(20, 20, 130, 40);
      
      // ������ ���� Panel
      itemPanel = new JPanel();
      itemPanel.setLayout(null);
//      itemPanel.setBounds(40, 120, 610, 670);
//      c.add(itemPanel);
      

      
      c.add(adminCb);
      c.add(memberId);
      c.add(logo);
      c.add(backBtn);
      c.add(adminCategoryPanel);
      addData(0);
      validate();
   }
   
   public JPanel statisticsData(int num, int values) {
      
      // ������ ���� Panel
      itemPanel = new JPanel();
      itemPanel.setLayout(null);
//      itemPanel.setBounds(100, 130, 1100, 55);
            
      // ī�װ� �̸�
      categoryName = new JLabel(categoryNames[num]);
      categoryName.setFont(new Font("����ü", 0, 17));
      categoryName.setHorizontalAlignment(SwingConstants.CENTER);
      categoryName.setBounds(5, 15, 160, 30);
            
      // ī�װ� �׷���
      JProgressBar categoryJpb = new JProgressBar();
      categoryJpb.setBounds(180, 10, 870, 35);
      categoryJpb.setValue(values);
      
      itemPanel.add(categoryName);
      itemPanel.add(categoryJpb);
      
      return itemPanel;
   }
   
   public JPanel logData(String[][] ingContents) {
	   
      
      // JPanel ����
      itemPanel = new JPanel();
      itemPanel.setLayout(null);
      
      // ���̺� ���� Table
      biddingTb = new JTable(ingContents,bidding_header);
      biddingScrollPane = new JScrollPane(biddingTb);
      biddingScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
      bidendTb = new JTable();
      bidendScrollPane = new JScrollPane(bidendTb);
      bidendScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
      
      itemPanel.add(biddingScrollPane);
      
      return itemPanel;
   }
   
   public void addData(int index) {
      
      adminCategoryPanel.removeAll();
      adminLogPanel.removeAll();
      
      Vector<ItemBean> vlist;
      
      if (index == 0) {
         
         vlist = mgr.getAdminCategory();         
         
         for (int i = 0; i < vlist.size(); i++) {
            JPanel p;
            
            p = statisticsData(vlist.get(i).getCategoryNum(),vlist.get(i).getAllCount());
            
            p.setPreferredSize(new Dimension(10, 20));
            adminCategoryPanel.add(p);
         }
      } else if (index == 1) {
    	 //��ǰ �̸�", "�Ǹ��� ID","���� �ο�","���� ����"
    	   //Object[][] listData = new Object[subjectList.size()][12];
    	  vlist = mgr.getAuctionIng();
   	   	  Object[][] ingContents = new Object[vlist.size()][4];
//    	  System.out.println(vlist.size());
    	  
    	  ArrayList<Object> data = new ArrayList<Object>();
    	  
         for (int i = 0; i < vlist.size(); i++) {
        	 	JPanel p;
        	 	
        	 	ItemBean ibean = vlist.get(i);
//        	 	System.out.println(ibean.getItemName());
        	 	data.add(ibean.getItemName());
				data.add(ibean.getItemSeller());
				data.add(Integer.toString(ibean.getPurchaserCount()));
				data.add(Integer.toString(ibean.getItemPrice()));
				System.out.println(data);
				ingContents[i] = data.toArray();
		}

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
         Admin AdminChange = new Admin("logId");
         AdminChange.setVisible(true);
   }
}