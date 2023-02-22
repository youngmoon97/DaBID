package project1;

import java.awt.Color;
import java.awt.Container;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.SwingConstants;

public class JoinMember extends JFrame
implements ActionListener{
   
   private JFrame frame = new JFrame();
   
   JPanel joinPanel;
   JLabel logo, joinName, joinId, joinPw, joinPwCheck, joinEmail, joinAt, joinUseIdlbl, joinUsePwlbl;
   JTextField joinNametxt, joinIdtxt, joinEmailtxt;
   JPasswordField joinPwtxt, joinPwChecktxt;
   JButton joinBtn, idCheckBtn, pwCheckBtn;
   String joinOk[] = {"사용 가능한 아이디입니다.", "사용 중인 아이디입니다."};
   JComboBox<String> emailCb = new JComboBox<String>();
   AuctionMgr mgr = new AuctionMgr();
   JOptionPane alarm = new JOptionPane();
   DBConnectionMgr con;
   
   public JoinMember() {
        setTitle("DaBID 회원가입");
        setSize(700, 600);
        setResizable(false);
        setLocationRelativeTo(null);//가운데 출력
        getContentPane().setLayout(null);
        Container c = getContentPane();
        //회원가입 패널
        joinPanel = new JPanel();
        joinPanel.setLayout(null);
        joinPanel.setBounds(0, 0, 684, 561);
        //이름
        joinName = new JLabel("이 름 ");
        joinName.setBounds(158, 110, 110, 30);
        joinName.setFont(new Font("맑은 고딕", 0, 20));
        //아이디
        joinId = new JLabel("아이디 ");
        joinId.setBounds(158, 185, 100, 30);
        joinId.setFont(new Font("맑은 고딕", 0, 20));
        //아이디 중복확인 표시칸
        joinUseIdlbl = new JLabel("");
        joinUseIdlbl.setBounds(380, 195, 300, 30);
        joinUseIdlbl.setFont(new Font("맑은 고딕", 0, 10));
        //비밀번호
        joinPw = new JLabel("비밀번호 ");
        joinPw.setBounds(158, 260, 110, 30);
        joinPw.setFont(new Font("맑은 고딕", 0, 20));
        //비밀번호 확인
        joinPwCheck = new JLabel("비밀번호 확인");
        joinPwCheck.setBounds(158, 335, 160, 30);
        joinPwCheck.setFont(new Font("맑은 고딕", 0, 20));
        //비밀번호 중복확인 표시칸
        joinUsePwlbl = new JLabel("");
        joinUsePwlbl.setBounds(380, 370, 300, 30);
        joinUsePwlbl.setFont(new Font("맑은 고딕", 0, 10));
        //이메일
        joinEmail = new JLabel("이메일 ");
        joinEmail.setBounds(158, 410, 100, 30);
        joinEmail.setFont(new Font("맑은 고딕", 0, 20));
        //이름 입력칸
        joinNametxt = new JTextField();
        joinNametxt.setBounds(328, 110, 232, 30);
        joinNametxt.setFont(new Font("맑은 고딕", 0, 15));
        //아이디 입력칸
        joinIdtxt = new JTextField("아이디");
        joinIdtxt.setBounds(328, 185, 150, 30);
        joinIdtxt.setFont(new Font("맑은 고딕", 0, 15));
        //비밀번호 확인 입력칸
        joinPwtxt = new JPasswordField();
        joinPwtxt.setBounds(328, 260, 232, 30);
        joinPwtxt.setFont(new Font("맑은 고딕", 0, 15));
        //비밀번호 중복확인 입력칸
        joinPwChecktxt = new JPasswordField();
        joinPwChecktxt.setBounds(328, 345, 150, 30);
        joinPwChecktxt.setFont(new Font("맑은 고딕", 0, 15));
        //이메일
        joinEmailtxt = new JTextField();
        joinEmailtxt.setBounds(328, 415, 103, 30);
        joinEmailtxt.setFont(new Font("맑은 고딕", 0, 15));
        //이메일 @
        joinAt = new JLabel("@");
        joinAt.setHorizontalAlignment(SwingConstants.CENTER);
        joinAt.setBounds(430, 415, 20, 30);
        joinAt.setFont(new Font("맑은 고딕", 0, 20));
        emailCb.setBackground(new Color(255, 255, 255));
        //이메일 콤보박스
        emailCb.setBounds(450,415,110,30);
        emailCb.setFont(new Font("맑은 고딕", 0, 15));
        emailCb.addItem("naver.com");
        emailCb.addItem("daum.net");
        emailCb.addItem("gmail.com");
        //회원가입 버튼
        joinBtn = new JButton("회원가입");
        joinBtn.setBackground(new Color(255, 255, 255));
        joinBtn.setBounds(300,500,100,30);
        joinBtn.setFont(new Font("맑은 고딕", Font.BOLD, 15));
        joinBtn.addActionListener(this);
        //아이디중복확인 버튼
        idCheckBtn = new JButton("중복확인");
        idCheckBtn.setBackground(new Color(255, 255, 255));
        idCheckBtn.setBounds(480,185,80,30);
        idCheckBtn.setFont(new Font("맑은 고딕", Font.BOLD, 11));
        idCheckBtn.addActionListener(this);
        //비밀번호 중복확인 버튼
        pwCheckBtn = new JButton("중복확인");
        pwCheckBtn.setBackground(new Color(255, 255, 255));
        pwCheckBtn.setBounds(480,345,80,30);
        pwCheckBtn.setFont(new Font("맑은 고딕", Font.BOLD, 11));
        pwCheckBtn.addActionListener(this);
        //logo
        logo = new JLabel(new ImageIcon(Login.class.getResource("./image/logo.png")));
        logo.setBounds(280,20,130,40);
        //디자인
        //배경색
        joinPanel.setBackground(Color.white);
        //TextField 색
        Color txtColor = new Color(240,240,240);
        joinNametxt.setBackground(txtColor);
        joinNametxt.setBorder(javax.swing.BorderFactory.createEmptyBorder());
        joinIdtxt.setBackground(txtColor);
        joinIdtxt.setBorder(javax.swing.BorderFactory.createEmptyBorder());
        joinPwtxt.setBackground(txtColor);
        joinPwtxt.setBorder(javax.swing.BorderFactory.createEmptyBorder());
        joinPwChecktxt.setBackground(txtColor);
        joinPwChecktxt.setBorder(javax.swing.BorderFactory.createEmptyBorder());
        joinEmailtxt.setBackground(txtColor);
        joinEmailtxt.setBorder(javax.swing.BorderFactory.createEmptyBorder());
        //아이디 중복확인 버튼 디자인
        idCheckBtn.setBackground(Color.black);
        idCheckBtn.setForeground(Color.white);
        idCheckBtn.setBorderPainted(false);
        idCheckBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        //비밀번호 중복확인 버튼 디자인
        pwCheckBtn.setBackground(Color.black);
        pwCheckBtn.setForeground(Color.white);
        pwCheckBtn.setBorderPainted(false);
        pwCheckBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        this.add(pwCheckBtn);
        //회원가입 버튼 디자인
        joinBtn.setBackground(Color.black);
        joinBtn.setForeground(Color.white);
        joinBtn.setBorderPainted(false);
        joinBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        this.add(joinBtn);
        //이메일 콤보 박스 색깔
        emailCb.setBackground(txtColor);
        emailCb.setCursor(new Cursor(Cursor.HAND_CURSOR));
        //add
        joinPanel.add(joinName);
        joinPanel.add(joinId);
        joinPanel.add(joinPw);
        joinPanel.add(joinPwCheck);
        joinPanel.add(joinEmail);
        joinPanel.add(joinNametxt);
        joinPanel.add(joinIdtxt);
        joinPanel.add(joinPwtxt);
        joinPanel.add(joinPwChecktxt);
        joinPanel.add(joinEmailtxt);
        joinPanel.add(joinAt);
        joinPanel.add(emailCb);
        joinPanel.add(joinBtn);
        joinPanel.add(joinUseIdlbl);
        joinPanel.add(joinUsePwlbl);
        joinPanel.add(idCheckBtn);
        joinPanel.add(pwCheckBtn);
        joinPanel.add(pwCheckBtn);
        c.add(logo);
        c.add(joinPanel);
        validate();
   }
   
   @Override
   public void actionPerformed(ActionEvent e) {
      Object obj = e.getSource();
      if(obj==joinBtn) {
    	  if(joinNametxt.getText().equals("")) {
    		  alarm.showMessageDialog(this, "이름을 입력해주세요");
    		  joinNametxt.requestFocus();
    	  }else if(joinIdtxt.getText().equals("")) {
    		  alarm.showMessageDialog(this, "아이디을 입력해주세요");
    	  }else if(joinPwtxt.getPassword().length == 0) {
    		  alarm.showMessageDialog(this, "비밀번호을 입력해주세요");
    	  }else if(joinEmailtxt.getText().equals("")) {
    		  alarm.showMessageDialog(this, "이메일을 입력해주세요");
    	  }else if(joinIdtxt.isEnabled()==true){
    		  alarm.showMessageDialog(this, "아이디 중복을 확인하세요");
    	  }else {
        	  String memname = joinNametxt.getText();
        	  String memid = joinIdtxt.getText();
        	  String mempw="";
        	  char[] joinpwchk = joinPwtxt.getPassword();
        	  for (int i = 0; i < joinpwchk.length; i++) {
        		  Character.toString(joinpwchk[i]);
               		mempw += (mempw.equals("")) ? ""+joinpwchk[i]+"" : ""+joinpwchk[i]+"";
              }
        	  System.out.println(mempw);
        	  String mememail = joinEmailtxt.getText()+"@"+emailCb.getSelectedItem(); 
        	  
        	  System.out.println(mememail);
        	  mgr.joinMember(memid, memname,mempw,mememail);
        	  
        	  try {
        		  dispose();
				Login login = new Login();
				login.setVisible(true);
			} catch (Exception e2) {
				// TODO: handle exception
			}
    	  }
      }else if(obj==idCheckBtn) {
         //id 중복 유무 확인
    	  if(joinIdtxt.getText().length()==0) {
    		  alarm.showMessageDialog(null, "아이디를 입력하세요.");
    	  }else {
    		  if(mgr.IdDupChk(joinIdtxt.getText())){
    			//존재하는 아이디가 없음 그래서 사용가능.
    	        alarm.showMessageDialog(this, "사용 가능합니다."); 
    	        joinIdtxt.setEnabled(false);
  	         }else {
  	        	alarm.showMessageDialog(this, "이미 존재하는 아이디입니다.");
  	            joinIdtxt.setText(" ");
  	            joinIdtxt.requestFocus();
  	         }
    	  }
      }else if(obj==pwCheckBtn) {
         //pw 같은지 확인
    	//비밀번호 중복확인
    	  String pw="", pw2="";
          char[] pwcheck = joinPwtxt.getPassword();
          char[] pwcheck2 = joinPwChecktxt.getPassword();
          
    	  if(pwcheck.length==0) {
    		alarm.showMessageDialog(this, "비밀번호를 입력하세요.");
    		joinPwtxt.requestFocus();
    	  }else {
              for (int i = 0; i < pwcheck.length; i++) {
              	Character.toString(pwcheck[i]);
              	pw += (pw.equals("")) ? ""+pwcheck[i]+"" : ""+pwcheck[i]+"";
              }
              for (int i = 0; i < pwcheck2.length; i++) {
              	Character.toString(pwcheck2[i]);
              	pw2 += (pw2.equals("")) ? ""+pwcheck2[i]+"" : ""+pwcheck2[i]+"";   
              }
              
              if(pw.equals(pw2)) {
              	joinUsePwlbl.setText("비밀번호가 일치합니다.");
              }else if(pw!=pw2){
              	joinUsePwlbl.setText("비밀번호가 불일치합니다.");
              }
    	  }
      }
      
   }
   
   public static void main(String[] args) {
     try {
       JoinMember jm = new JoinMember();
       
       jm.setVisible(true);
   } catch (Exception e) {
      // TODO: handle exception
   } 
     
      
   }
}