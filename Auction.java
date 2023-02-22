package project1;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Vector;

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

import project1.CategoryList.timerSet;

public class Auction extends JFrame
implements ActionListener{
	
	JPanel itemPanel,commentPanel;
	JLabel logo, memberId, itemName, itemPhoto, itemMemo, currentPrice, purchaserCount, auctionPriceLbl, auctionTime;
	JTextArea commentArea;
	JTextField commentTf, bidpriceTf;
	JButton commentBtn, auctionBtn, backBtn; 
	TitledBorder itemTb, commentTb;
	JOptionPane alarm = new JOptionPane();
	String logId;	// �������� ���������� �ϱ�
	
    
	Font f = new Font("����ü",0,15);
	
	public Auction(String logId, int itemNum) {
		setTitle("DaBID ���������");
	    setSize(1300,900);
	    setResizable(false);
	    setLayout(null);
	    setLocationRelativeTo(null); //��� ���
	    int itemNum2 = itemNum;
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
	    
	    //memberid
	    memberId = new JLabel("���̵� : "+logId);
	    memberId.setBounds(1150,20,150,30);
        memberId.setFont(new Font("����ü", 0, 15));
        
        AuctionMgr mgr = new AuctionMgr();
        ItemBean ibean = mgr.getItem(itemNum);
	     //��ǰ���̺�
	    itemName = new JLabel("��ǰ�� : " + ibean.getItemName());
	    itemName.setBounds(60, 60, 100, 30);
	    itemName.setFont(new Font("����ü", 0, 15));
	   
	    //��ǰ�̹���
	    itemPhoto = new JLabel(new ImageIcon(Login.class.getResource("./image/"+ibean.getItemName()+".jpg")));
	    itemPhoto.setBorder(new LineBorder(Color.black,1,true));
	    itemPhoto.setBounds(60, 90, 450, 400);
	     //��ǰ����
	    itemMemo= new JLabel(ibean.getItemMemo());
	    itemMemo.setBorder(new LineBorder(Color.black,1,true));
	    itemMemo.setBounds(60, 500, 450, 100);
	     //��ǰ����������
	    currentPrice= new JLabel("���� ������ : " + ibean.getItemPrice()+"��");
	    currentPrice.setBorder(new LineBorder(Color.black,1,true));
	    currentPrice.setBounds(60, 610, 200, 50);
	    currentPrice.setFont(new Font("����ü", 0, 15));
	     //���������ο� 
	    purchaserCount= new JLabel("���� ���� �ο� :  "+ibean.getPurchaserCount()+"��");	     
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
	    commentArea.setEnabled(false);
	    //���� ��� ��������
	    Vector<CommentBean> clist = mgr.getCommentList(ibean.getItemNum());
	    for (int i = 0; i < clist.size(); i++) {
	    	
	    	CommentBean cbean = clist.get(i);
	    	if(cbean.getCommentContent()==null) {
	    		commentArea.append("���� ����� �����ϴ�.\n");
	    	}else {
	    		Date time = cbean.getCommentTime();
				String purchaserId = cbean.getPurchaserId();
				String comment = cbean.getCommentContent();
				commentArea.append(time+"\n"+purchaserId+" : "+comment+"\n");
	    	}
		}
	  //�ð� ��� 
        int time = ibean.getItemEndTime();
        
        int hour = time / (60*60);
        int minute = time / 60 - (hour*60);
        int second = time % 60;
        
        String reHour = Integer.toString(hour);
        String reMin = Integer.toString(minute);
        String reSec = Integer.toString(second);
  //
        auctionTime = new JLabel("���� �ð� : " +reHour + ":" + reMin + ":" + reSec);
	    auctionTime.setBounds(340,30,180,30);
	    auctionTime.setBorder(new LineBorder(Color.black,1,true));
	    auctionTime.setFont(f);
	    timerSet ts = new timerSet(auctionTime, time);
	    //comment����Է�
	    commentTf = new JTextField();
	    commentTf.setBounds(60, 540, 380, 30);
	    commentTf.setBorder(new LineBorder(Color.black,1,true));
	    commentTf.setFont(f);
	    commentTf.requestFocus();
	    //comment��ư����
	    commentBtn = new JButton("����");
	    commentBtn.setBounds(440, 540, 70, 30);
	    commentBtn.setBorder(new LineBorder(Color.black,1,true));
	    commentBtn.setFont(f);
	    commentBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(commentTf.getText().isEmpty()) {
					 alarm.showMessageDialog(null, "����� �Է��ϼ���.");
				 }else {
					 String comment = commentTf.getText();
					 LocalDate now = LocalDate.now();
					 commentArea.append(now+"\n"+logId +" : "+comment+"\n");
					 //��� ����
					 String seller = ibean.getItemSeller();
					 int itemNum = ibean.getItemNum();
					 System.out.println(seller+logId+itemNum+comment);
					 mgr.insertComment(seller, logId, itemNum, comment);
					
					 commentTf.setText(" ");
				 }
			}
		});
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
	    auctionBtn.setBounds(420, 600, 70, 30);
	    auctionBtn.setBorder(new LineBorder(Color.black,1,true));
	    auctionBtn.setFont(f);
	    auctionBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int itemNum = ibean.getItemNum();
				if(ibean.getItemSeller().equals(logId)) {
					alarm.showMessageDialog(null, "�ڽ��� ��ǰ�� �������� ���մϴ�.");
				}else {
					if(bidpriceTf.getText().equals("")) {
			               alarm.showMessageDialog(null, "������ �Է��ϼ���.");
			               bidpriceTf.requestFocus();
			            }else {
			               int itemPrice = Integer.parseInt(bidpriceTf.getText());
			               if (itemPrice > ibean.getItemPrice()) {
			                  mgr.insertAuction(itemNum, itemPrice, logId);
			                  alarm.showMessageDialog(null, "���� ����!");
			                  
			                  try {
			                     dispose();
			                     Main main = new Main(logId);
			                     main.setVisible(true);
			                  } catch (Exception e2) {
			                     // TODO: handle exception
			                  }
			               }else {
			                  alarm.showMessageDialog(null, "������ ���簡 �̻����� �Է����ּ���.");
			                  bidpriceTf.setText("");
			                  bidpriceTf.requestFocus();
			               }
			            }
				}
	            
	            
			}
		});
	    //
	    commentPanel.add(commentArea);
	    commentPanel.add(auctionTime);
	    commentPanel.add(commentTf);
		commentPanel.add(commentBtn); 
		commentPanel.add(auctionBtn);
		commentPanel.add(auctionPriceLbl); 
		commentPanel.add(bidpriceTf);
		//
		logo = new JLabel(new ImageIcon(Login.class.getResource("./image/logo.png")));
	    logo.setBounds(20,20,130,40);
	    //
	    backBtn = new JButton("�ڷΰ���");
		backBtn.setBounds(1150, 820, 120, 30);
	    backBtn.setBorder(new LineBorder(Color.black,1,true));
		backBtn.setFont(f);
		backBtn.addActionListener(this);
	    //
		c.add(memberId);
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
			 
			 
		}else if(obj==auctionBtn/*�����ϱ�*/){
			
		}else if(obj==backBtn) {
			try {
				dispose();
				Main main = new Main(logId);
				main.setVisible(true);
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	}
class timerSet implements Runnable{
	    
	    JLabel itemTime;
	    int time;
	    
	    public timerSet(JLabel itemTime, int time) {
	       this.itemTime = itemTime;
	       this.time = time;
	       new Thread(this).start();
	    }
	    
	    @Override
	    public void run() {
	       while(true) {
	          try {
	             time--;
	             if (time < 0) {
	            break;
	         }
	             int hour = time / (60 * 60);
	             int minute = time / 60 - (hour * 60);
	             int second = time % 60;

	             String reHour = Integer.toString(hour);
	             String reMin = Integer.toString(minute);
	             String reSec = Integer.toString(second);
	             
	             itemTime.setText("���� �ð� : " +reHour + ":" + reMin + ":" + reSec);
	             Thread.sleep(1000);
	            
	          } catch (InterruptedException e) {
	             // TODO Auto-generated catch block
	             e.printStackTrace();
	          }
	       }
	    }
	    
	 }
	
	public static void main(String[] args) {
		
	}
}
