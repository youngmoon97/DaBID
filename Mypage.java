package project1;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import project1.CategoryList.timerSet;

public class Mypage extends JFrame
implements ActionListener{
   
   private JFrame frame = new JFrame();
   
   JPanel myPagePanel, itemPanel;
   JLabel logo, memberId, nowPwlbl, newPwlbl, newCheckPwlbl, particPhoto, particName, particPrice, particCount, particTime,
   sellPhoto, sellName, sellPrice, sellTime, sellCount, buyPhoto, buyName, buyPrice, buyCount, buyTime;
   
   JPasswordField nowPw, newPw, newCheckPw;
   JButton saveBtn, itemDeleteBtn, backBtn;
   TitledBorder soonTb, buyTb, sellTb, pwTb;
   JScrollPane myPageScroll;
   String logId;
   JOptionPane alarm = new JOptionPane();
   AuctionMgr mgr;
   
   String myPageCt[] = {"경매 참여 상품", "구매한 상품", "판매한 상품", "비밀번호 변경"};
   
   JComboBox<String> myPageCb = new JComboBox<String>(myPageCt);

   
   public Mypage(String logId) {
      
     mgr = new AuctionMgr();
     
      setTitle("DaBID 마이페이지");
      setSize(1300, 900);
        setResizable(false);
        setLocationRelativeTo(null); //가운데 출력
        getContentPane().setLayout(null);
        Container c = getContentPane();
        
        //panel 지정
        myPagePanel = new JPanel();
//        myPagePanel.setBounds(180, 70, 1000, 700);
        myPagePanel.setLayout(new GridLayout(0,1, 10, 10));
//        myPagePanel.setLayout(null);
        
        // border 지정
        soonTb = new TitledBorder(new LineBorder(Color.black,1,true),"판매 예정");
        soonTb.setTitleFont(new Font("돋움체",0,15));
        
        buyTb = new TitledBorder(new LineBorder(Color.black,1,true),"구매 완료");
        buyTb.setTitleFont(new Font("돋움체",0,15));
        
        sellTb = new TitledBorder(new LineBorder(Color.black,1,true),"판매 완료");
        sellTb.setTitleFont(new Font("돋움체",0,15));
        
        pwTb = new TitledBorder(new LineBorder(Color.black,1,true),"비밀번호 변경");
        pwTb.setTitleFont(new Font("돋움체",0,15));
        
        
        // 마이페이지 콤보박스 combobox 
        myPageCb.setBounds(20, 85, 150, 40);
        myPageCb.setFont(new Font("돋움체",0,17));
        myPageCb.addActionListener(this);
        
       
        // 마이페이지 스크롤 myPageScroll
       myPageScroll = new JScrollPane(myPagePanel);
       myPageScroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
       myPageScroll.setBounds(180, 70, 1000, 700);
        
        
       
        // 기본
        // logo
        logo = new JLabel(new ImageIcon(Login.class.getResource("./image/logo.png")));
          logo.setBounds(20,20,130,40);
          c.add(logo);
       
 
        //id
        memberId = new JLabel("아이디 : "+ logId);
        memberId.setBounds(1150, 20, 150, 30);
        memberId.setFont(new Font("돋움체", 0, 15));
        
        // back버튼
        backBtn = new JButton("뒤로가기");
        backBtn.setBounds(1150,800, 100, 30);
        backBtn.setFont(new Font("돋움체", 0, 15));
        backBtn.addActionListener(this);
       
        //add
        c.add(myPageScroll);
       
        c.add(myPageCb);
        c.add(memberId);
        c.add(backBtn);
        
        addData(0);
        validate();
   }
   
   // 경매 참여 상품 데이터 생성(index 0)
   public JPanel CreateParticData(ImageIcon photo, int itemNum, String name, int price, int count, String reHour, String reMin, String reSec, int time) {
      
          itemPanel = new JPanel();
          itemPanel.setLayout(null);
          itemPanel.setBounds(10, 20, 940, 300);
          itemPanel.setBorder(new LineBorder(Color.black, 1, true));
         
          // 상품 정보 label
          particPhoto = new JLabel(photo);
          particName = new JLabel(name);
          particPrice = new JLabel(Integer.toString(price));
          particCount = new JLabel(Integer.toString(count));
          particTime = new JLabel(reHour + ":" + reMin + ":" + reSec);
          
          particPhoto.setBounds(30, 50, 250, 200);
          particName.setBounds(300, 70, 200, 60);
          particTime.setBounds(550, 70, 200, 60);
          particPrice.setBounds(300, 170, 200, 60);
          particCount.setBounds(550, 170, 200, 60);
           
           
          particPhoto.setFont(new Font("돋움체", 0, 18));
          particName.setFont(new Font("돋움체", 0, 18));
          particTime.setFont(new Font("돋움체", 0, 18));
          particPrice.setFont(new Font("돋움체", 0, 18));
          particCount.setFont(new Font("돋움체", 0, 18));
          
          particName.setHorizontalAlignment(SwingConstants.CENTER);
          particTime.setHorizontalAlignment(SwingConstants.CENTER);
          particPrice.setHorizontalAlignment(SwingConstants.CENTER);
          particCount.setHorizontalAlignment(SwingConstants.CENTER);
          
           
          particPhoto.setBorder(new LineBorder(Color.black,1,true));
          particName.setBorder(new LineBorder(Color.black,1,true));
          particTime.setBorder(new LineBorder(Color.black,1,true));
          particPrice.setBorder(new LineBorder(Color.black,1,true));
          particCount.setBorder(new LineBorder(Color.black,1,true));
          
          timerSet ts = new timerSet(particTime, time);
           
           // 상품 재참여 버튼
           itemDeleteBtn = new JButton("재참여");
           itemDeleteBtn.setBounds(780, 100, 130, 100);
           itemDeleteBtn.setFont(new Font("돋움체", 0, 16));
           itemDeleteBtn.addActionListener(new ActionListener() {
         
         @Override
         public void actionPerformed(ActionEvent e) {
            logId = memberId.getText();
             logId = logId.substring(logId.lastIndexOf(":")+1).trim();
            dispose();
            Auction auction = new Auction(logId, itemNum) ;
            auction.setVisible(true);
         }
      });
           
           itemPanel.add(particPhoto);
           itemPanel.add(particName);
           itemPanel.add(particTime);
           itemPanel.add(particPrice);
           itemPanel.add(particCount);
           itemPanel.add(itemDeleteBtn);
           
           return itemPanel;
   }
   
// 판매한 상품 데이터 생성(index 1)
     public JPanel CreateBuyData(ImageIcon photo, String name, int price, int count, String reHour, String reMin, String reSec, int time) {
      
          itemPanel = new JPanel();
          itemPanel.setLayout(null);
          itemPanel.setBounds(10, 20, 940, 300);
          itemPanel.setBorder(new LineBorder(Color.black, 1, true));
          
          // 상품 정보 label
          buyPhoto = new JLabel(photo);
          buyName = new JLabel(name);
          buyTime = new JLabel(reHour + ":" + reMin + ":" + reSec);
          buyPrice = new JLabel(Integer.toString(price));
          buyCount = new JLabel(Integer.toString(count));
           
          buyPhoto.setBounds(30, 50, 250, 200);
          buyName.setBounds(300, 70, 200, 60);
          buyTime.setBounds(550, 70, 200, 60);
          buyPrice.setBounds(300, 170, 200, 60);
          buyCount.setBounds(550, 170, 200, 60);
           
          buyPhoto.setFont(new Font("돋움체", 0, 18));
          buyName.setFont(new Font("돋움체", 0, 18));
          buyTime.setFont(new Font("돋움체", 0, 18));
          buyPrice.setFont(new Font("돋움체", 0, 18));
          buyCount.setFont(new Font("돋움체", 0, 18));
          
          buyName.setHorizontalAlignment(SwingConstants.CENTER);
          buyTime.setHorizontalAlignment(SwingConstants.CENTER);
          buyPrice.setHorizontalAlignment(SwingConstants.CENTER);
          buyCount.setHorizontalAlignment(SwingConstants.CENTER);
           
          buyPhoto.setBorder(new LineBorder(Color.black,1,true));
          buyName.setBorder(new LineBorder(Color.black,1,true));
          buyTime.setBorder(new LineBorder(Color.black,1,true));
          buyPrice.setBorder(new LineBorder(Color.black,1,true));
          buyCount.setBorder(new LineBorder(Color.black,1,true));
          
          timerSet ts = new timerSet(buyTime, time);
           
           itemPanel.add(buyPhoto);
           itemPanel.add(buyName);
           itemPanel.add(buyTime);
           itemPanel.add(buyPrice);
           itemPanel.add(buyCount);
           
           return itemPanel;
  }
   
      // 판매한 상품 데이터 생성(index 2)
      public JPanel CreateSellData(ImageIcon photo, String name, int price, int count, String reHour, String reMin, String reSec, int time) {
      
          itemPanel = new JPanel();
          itemPanel.setLayout(null);
          itemPanel.setBounds(10, 20, 940, 300);
          itemPanel.setBorder(new LineBorder(Color.black, 1, true));
          
          // 상품 정보 label
          sellPhoto = new JLabel(photo);
          sellName = new JLabel(name);
          sellTime = new JLabel(reHour + ":" + reMin + ":" + reSec);
          sellPrice = new JLabel(Integer.toString(price));
          sellCount = new JLabel(Integer.toString(count));
          
          sellPhoto.setBounds(30, 50, 250, 200);
          sellName.setBounds(300, 70, 200, 60);
          sellTime.setBounds(550, 70, 200, 60);
          sellPrice.setBounds(300, 170, 200, 60);
          sellCount.setBounds(550, 170, 200, 60);
           
          sellPhoto.setFont(new Font("돋움체", 0, 18));
          sellName.setFont(new Font("돋움체", 0, 18));
          sellTime.setFont(new Font("돋움체", 0, 18));
          sellPrice.setFont(new Font("돋움체", 0, 18));
          sellCount.setFont(new Font("돋움체", 0, 18));
          
          sellName.setHorizontalAlignment(SwingConstants.CENTER);
          sellTime.setHorizontalAlignment(SwingConstants.CENTER);
          sellPrice.setHorizontalAlignment(SwingConstants.CENTER);
          sellCount.setHorizontalAlignment(SwingConstants.CENTER);
           
          sellPhoto.setBorder(new LineBorder(Color.black,1,true));
          sellName.setBorder(new LineBorder(Color.black,1,true));
          sellTime.setBorder(new LineBorder(Color.black,1,true));
          sellPrice.setBorder(new LineBorder(Color.black,1,true));
          sellCount.setBorder(new LineBorder(Color.black,1,true));
          
          timerSet ts = new timerSet(sellTime, time);
           
          itemPanel.add(sellPhoto);
          itemPanel.add(sellName);
          itemPanel.add(sellTime);
          itemPanel.add(sellPrice);
          itemPanel.add(sellCount);
           
          return itemPanel;
   }
     
      public JPanel CreateChangePwData(String id, String pw) {
         
         itemPanel = new JPanel();
          itemPanel.setLayout(null);
          itemPanel.setBounds(10, 20, 940, 300);
          itemPanel.setBorder(new LineBorder(Color.black, 1, true));
          
       // 현재 비밀번호, 새 비밀번호, 비밀번호 확인 label
         
          nowPwlbl = new JLabel("현재 비밀번호");
          newPwlbl = new JLabel("새 비밀번호");
          newCheckPwlbl = new JLabel("비밀번호 확인");
          
          nowPwlbl.setBounds(200, 170, 190, 30);
          newPwlbl.setBounds(200, 320, 190, 30);
          newCheckPwlbl.setBounds(200, 470, 190, 30);
          
          nowPwlbl.setFont(new Font("돋움체", 1, 21));
          newPwlbl.setFont(new Font("돋움체", 1, 21));
          newCheckPwlbl.setFont(new Font("돋움체", 1, 21));
          
          // 현재 비밀번호, 새비밀번호, 비밀번호 확인 JPasswordField
          nowPw = new JPasswordField();
          newPw = new JPasswordField();
          newCheckPw = new JPasswordField();
          
          nowPw.setBounds(390, 170, 300, 30);
          newPw.setBounds(390, 320, 300, 30);
          newCheckPw.setBounds(390, 470, 300, 30);
          
          nowPw.setFont(new Font("돋움체", 0, 21));
          newPw.setFont(new Font("돋움체", 0, 21));
          newCheckPw.setFont(new Font("돋움체", 0, 21));
          
          // save 버튼
          saveBtn = new JButton("변경");
          saveBtn.setBounds(850,600, 80, 30);
          saveBtn.setFont(new Font("돋움체", 0, 20));
          
          return itemPanel;
         
      }
      
   
   public void addData(int index) {
      
         myPagePanel.removeAll();
         
         Vector<ItemBean> vlist;
         
         if (index == 0) {
            vlist = mgr.getIngItemList(logId);
            
            System.out.println(vlist.size());
           
            for (int i = 0; i < vlist.size(); i++) {
               JPanel p;
               
               int time = vlist.get(i).getItemEndTime();
               
               if (time < 0) {
                  time = 0;
               }
               
               int hour = time / (60*60);
               int minute = time / 60 - (hour*60);
               int second = time % 60;
               
               String reHour = Integer.toString(hour);
               String reMin = Integer.toString(minute);
               String reSec = Integer.toString(second);
               
               System.out.println(reHour);
               System.out.println(reMin);
               System.out.println(reSec);
            
               ImageIcon icon = new ImageIcon(Login.class.getResource("./image/"+vlist.get(i).getItemName()+".jpg"));
               Image img = icon.getImage();
               Image changeImg = img.getScaledInstance(250, 200, Image.SCALE_SMOOTH);
               ImageIcon changeIcon = new ImageIcon(changeImg);
            
               p = CreateParticData(changeIcon, vlist.get(i).getItemNum() ,vlist.get(i).getItemName(), vlist.get(i).getItemPrice(),
                     vlist.get(i).getItemStatus(), reHour, reMin, reSec, time);
               
               p.setPreferredSize(new Dimension(420, 260));
              myPagePanel.add(p);
            }
            //
         } else if (index == 1) {
            vlist = mgr.getBuyItemList(logId);
            
            System.out.println(vlist.size());
            
            for (int i = 0; i < vlist.size(); i++) {
               JPanel p;
               
               int time = vlist.get(i).getItemEndTime();
               
               if (time < 0) {
                  time = 0;
               }
               
               int hour = time / (60 * 60);
               int minute = time / 60 - (hour * 60);
               int second = time % 60;

            String reHour = Integer.toString(hour);
            String reMin = Integer.toString(minute);
            String reSec = Integer.toString(second);
            
            ImageIcon icon = new ImageIcon(Login.class.getResource("./image/"+vlist.get(i).getItemName()+".jpg"));
            Image img = icon.getImage();
            Image changeImg = img.getScaledInstance(250, 200, Image.SCALE_SMOOTH);
            ImageIcon changeIcon = new ImageIcon(changeImg);
            
               p = CreateBuyData(changeIcon, vlist.get(i).getItemName(), vlist.get(i).getItemPrice(),
                     vlist.get(i).getItemStatus(), reHour, reMin, reSec, time);
               
               p.setPreferredSize(new Dimension(420, 260));
            myPagePanel.add(p);
            }
            
         } else if (index == 2) {
            vlist = mgr.getSellItemList(logId);
            
            System.out.println(vlist.size());
            for (int i = 0; i < vlist.size(); i++) {
               JPanel p;
               
               int time = vlist.get(i).getItemEndTime();
               
               if (time < 0) {
                  time = 0;
               }
               
               int hour = time / (60 * 60);
               int minute = time / 60 - (hour * 60);
               int second = time % 60;

            String reHour = Integer.toString(hour);
            String reMin = Integer.toString(minute);
            String reSec = Integer.toString(second);
            
            ImageIcon icon = new ImageIcon(Login.class.getResource("./image/"+vlist.get(i).getItemName()+".jpg"));
            Image img = icon.getImage();
            Image changeImg = img.getScaledInstance(250, 200, Image.SCALE_SMOOTH);
            ImageIcon changeIcon = new ImageIcon(changeImg);
            
               p = CreateSellData(changeIcon,vlist.get(i).getItemName(), vlist.get(i).getItemPrice(),
                     vlist.get(i).getItemStatus(), reHour, reMin, reSec, time);
               
               p.setPreferredSize(new Dimension(420, 260));
               myPagePanel.add(p);
            }
            
            
            
         } else if (index == 3) {
        	 // index 3 (비밀번호 변경)
        	 itemPanel = new JPanel();
             itemPanel.setLayout(null);
             itemPanel.setBounds(10, 20, 940, 300);
             itemPanel.setBorder(new LineBorder(Color.black, 1, true));
             
          // 현재 비밀번호, 새 비밀번호, 비밀번호 확인 label
         	
             nowPwlbl = new JLabel("현재 비밀번호");
             newPwlbl = new JLabel("새 비밀번호");
             newCheckPwlbl = new JLabel("비밀번호 확인");
             
             nowPwlbl.setBounds(200, 170, 190, 30);
             newPwlbl.setBounds(200, 320, 190, 30);
             newCheckPwlbl.setBounds(200, 470, 190, 30);
             
             nowPwlbl.setFont(new Font("돋움체", 1, 21));
             newPwlbl.setFont(new Font("돋움체", 1, 21));
             newCheckPwlbl.setFont(new Font("돋움체", 1, 21));
             
             // 현재 비밀번호, 새비밀번호, 비밀번호 확인 JPasswordField
             nowPw = new JPasswordField();
             newPw = new JPasswordField();
             newCheckPw = new JPasswordField();
             
             nowPw.setBounds(390, 170, 300, 30);
             newPw.setBounds(390, 320, 300, 30);
             newCheckPw.setBounds(390, 470, 300, 30);
             
             nowPw.setFont(new Font("돋움체", 0, 21));
             newPw.setFont(new Font("돋움체", 0, 21));
             newCheckPw.setFont(new Font("돋움체", 0, 21));
             
             // save 버튼
             saveBtn = new JButton("변경");
             saveBtn.setBounds(850,600, 80, 30);
             saveBtn.setFont(new Font("돋움체", 0, 20));
             saveBtn.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					if(mgr.pwChk(new String(nowPw.getPassword()), logId)) {
						//현재 비밀번호 일치
						System.out.println(new String(newPw.getPassword())+"111");
						System.out.println(new String(newCheckPw.getPassword())+"111");
						if(new String(newPw.getPassword()).equals(new String(newCheckPw.getPassword())) ) {
							//새 비밀번호 확인
							mgr.pwChange(new String(newPw.getPassword()), logId);
							//바꾸기
							alarm.showMessageDialog(null, "비밀번호가 변경되었습니다.");
							nowPw.setText("");
							newPw.setText("");
							newCheckPw.setText("");
						}else {
							alarm.showMessageDialog(null, "새로운 비밀번호를 확인하세요.");
						}
						
					}else {
						alarm.showMessageDialog(null, "현재 비밀번호를 확인하세요.");
						nowPw.setText("");
						nowPw.requestFocus();
					}
				}
			});
             
             itemPanel.add(nowPwlbl);
             itemPanel.add(newPwlbl);
             itemPanel.add(newCheckPwlbl);
             itemPanel.add(nowPw);
             itemPanel.add(newPw);
             itemPanel.add(newCheckPw);
             itemPanel.add(saveBtn);
             
             myPagePanel.add(itemPanel);
         }
   }
   

@Override
   public void actionPerformed(ActionEvent e) {

      logId = memberId.getText();
      logId = logId.substring(logId.lastIndexOf(":")+1).trim();
      
      Object obj = e.getSource();
      
      if (obj == myPageCb) {
         int index = myPageCb.getSelectedIndex();
         addData(index);
         repaint(); 
         validate();
      }
      else if(obj==backBtn) {
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
    
    JLabel particTime;
    int time;
    
    public timerSet(JLabel particTime, int time) {
       this.particTime = particTime;
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
             particTime.setText(reHour + ":" + reMin + ":" + reSec);
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