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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

public class itemRegister extends JFrame implements ActionListener {

   JPanel itemPanel;
   JLabel memberId, itemName, itemPhoto, itemCategory, itemprice, won, endtime;
   JTextArea taName, taMemo, taPrice;
   JButton registerBtn, backBtn;
   JComboBox<String> categoryCombobox, timeCombobox;
   TitledBorder registerTb;
   JOptionPane alarm = new JOptionPane();
   String[] categoryName = {"�����б��","���/����/����","����/���׸���","������/����","��Ȱ����","�Ƿ�","�ݷ�������ǰ","��Ƽ/�̿�","�߰���","����"};
   String[] timelist = {"24�ð�", "48�ð�", "120�ð�"};
   
   public itemRegister() {
      setTitle("DaBID ��ǰ��� ������");
      setSize(1300, 900);
      setResizable(false);
      setLayout(null);
      Container c = getContentPane();
      
      //JPanel
      itemPanel = new JPanel();
      itemPanel.setBounds(60, 80, 1150, 700);
      itemPanel.setLayout(null);
      
      //TitledBorder
      registerTb = new TitledBorder(new LineBorder(Color.black, 1, true), "��ǰ ���");
      registerTb.setTitleFont(new Font("����ü", 0, 25));
      itemPanel.setBorder(registerTb);
      
      //JLabel
      memberId = new JLabel("���̵� : aaa");
      memberId.setBounds(1150, 20, 100, 30);
      memberId.setFont(new Font("����ü", 0, 15));
      memberId.setBorder(new LineBorder(Color.black, 1, true));
      
      itemName = new JLabel("��ǰ�� :");
      itemName.setBounds(60 ,60, 100, 30);
      itemName.setFont(new Font("����ü", 0, 15));
      
      itemPhoto = new JLabel("�̹��� ���");
      itemPhoto.setBorder(new LineBorder(Color.black, 1, true));
      itemPhoto.setBounds(50, 120, 550, 470);
      itemPhoto.setHorizontalAlignment(JLabel.CENTER);
      itemPhoto.setFont(new Font("����ü", 0, 20));
      
      itemCategory = new JLabel("ī�װ� :");
      itemCategory.setBounds(650, 410, 100, 30);
      itemCategory.setFont(new Font("����ü", 0, 17));
      
      itemprice = new JLabel("���� ���� :");
      itemprice.setBounds(650, 460, 100, 30);
      itemprice.setFont(new Font("����ü", 0, 17));
      
      won = new JLabel("��");
      won.setBounds(970, 460, 40, 30);
      won.setFont(new Font("����ü", 0, 17));
      
      endtime = new JLabel("���� �ð� :");
      endtime.setBounds(650, 510, 100, 30);
      endtime.setFont(new Font("����ü", 0, 17));
      
      //JTextArea
      taName = new JTextArea();
      taName.setBounds(140, 65, 430, 20);
      
      taMemo = new JTextArea("��ǰ ����");
      taMemo.setBounds(650, 120, 450, 260);
      
      taPrice = new JTextArea();
      taPrice.setBounds(750, 460, 200, 30);
      
      //JButton
      registerBtn = new JButton("��ǰ ���");
      registerBtn.setBounds(520, 630, 100, 40);
      registerBtn.setFont(new Font("����ü", 0, 15));
      registerBtn.addActionListener(this);
      backBtn = new JButton("�ڷΰ���");
      backBtn.setBounds(1150, 800, 120, 30);
      backBtn.setFont(new Font("����ü",0,15));
      backBtn.addActionListener(this);
      
      //JComboBox
      categoryCombobox = new JComboBox<>(categoryName);
      categoryCombobox.setBounds(750, 410, 270, 30);
      
      timeCombobox = new JComboBox<>(timelist);
      timeCombobox.setBounds(750, 510, 100, 30);
      
      //�гο� �߰�
      itemPanel.add(itemName);
      itemPanel.add(itemPhoto);
      itemPanel.add(itemCategory);
      itemPanel.add(itemprice);
      itemPanel.add(won);
      itemPanel.add(endtime);
      itemPanel.add(taName);
      itemPanel.add(taMemo);
      itemPanel.add(taPrice);
      itemPanel.add(registerBtn);
      itemPanel.add(categoryCombobox);
      itemPanel.add(timeCombobox);
      
      //���
      c.add(memberId);
      c.add(itemPanel);
      c.add(backBtn);
      validate();
   }
   
   @Override
   public void actionPerformed(ActionEvent e) {
      Object obj = e.getSource();
      if(obj==backBtn) {
    	  //TODO
    	  try {
    		  dispose();
    		  Main main = new Main();
    		  main.setVisible(true);
    	  } catch (Exception e2) {
    		  e2.printStackTrace();
    	  }
      }else if(obj==registerBtn) {
    	  //TODO
    	  alarm.showMessageDialog(null, "��ϿϷ�");
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