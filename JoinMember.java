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
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.SwingConstants;

public class JoinMember extends JFrame
implements ActionListener{
   
   private JFrame frame = new JFrame();
   
   JPanel joinPanel;
   JLabel joinId, joinPw, joinPwCheck, joinEmail, joinAt, joinUseIdlbl, joinUsePwlbl;
   JTextField joinIdtxt, joinEmailtxt;
   JPasswordField joinPwtxt, joinPwChecktxt;
   JButton joinBtn, idCheckBtn, pwCheckBtn;
   String joinOk[] = {"사용 가능한 아이디입니다.", "사용 중인 아이디입니다."};
   JComboBox<String> emailCb = new JComboBox<String>();
   
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
        //아이디
        joinId = new JLabel("아이디 ");
        joinId.setBounds(158, 115, 100, 30);
        joinId.setFont(new Font("돋움체", 0, 20));
        //아이디 중복확인 label
      /*
       * if(=1) { joinUseIdlbl= new JLabel(joinOk[0]); }else if(=0) {
       * 
       * }
       */
        joinUseIdlbl = new JLabel("사용 가능한 아이디입니다.");
        joinUseIdlbl.setBounds(380, 140, 300, 30);
        joinUseIdlbl.setFont(new Font("돋움체", 0, 10));
        //비밀번호
        joinPw = new JLabel("비밀번호 ");
        joinPw.setBounds(158, 215, 110, 30);
        joinPw.setFont(new Font("돋움체", 0, 20));
        //비밀번호 확인
        joinPwCheck = new JLabel("비밀번호 확인");
        joinPwCheck.setBounds(158, 315, 160, 30);
        joinPwCheck.setFont(new Font("돋움체", 0, 20));
        //비밀번호 중복확인
        //char[] pwcheck = joinPwtxt.getPassword();
        //String pw="";
        //for (int i = 0; i < pwcheck.length; i++) {
      //   Character.toString(pwcheck[i]);
      //   pw += (pw.equals("")) ? ""+pwcheck[i]+"" : ""+pwcheck[i]+"";   
      //}
        //char[] pwcheck2 = joinPwtxt.getPassword();
        //String pw2="";
       // for (int i = 0; i < pwcheck2.length; i++) {
      //   Character.toString(pwcheck2[i]);
      //   pw2 += (pw2.equals("")) ? ""+pwcheck2[i]+"" : ""+pwcheck2[i]+"";   
      //}
       joinUsePwlbl = new JLabel("비밀번호 확인하는 곳입니다");

        //if(pw.equals(pw2)) {
        //   joinUsePwlbl.setText("비밀번호가 일치합니다.");
        //}else if(pw!=pw2){
        //   joinUsePwlbl.setText("비밀번호가 불일치합니다.");

        //}
        joinUsePwlbl.setBounds(380, 340, 300, 30);
        joinUsePwlbl.setFont(new Font("돋움체", 0, 10));
        //이메일
        joinEmail = new JLabel("이메일 ");
        joinEmail.setBounds(158, 410, 100, 30);
        joinEmail.setFont(new Font("돋움체", 0, 20));
        //아이디 입력칸
        joinIdtxt = new JTextField();
        joinIdtxt.setBounds(328, 115, 150, 30);
        joinIdtxt.setFont(new Font("돋움체", 0, 15));
        //비밀번호 확인 입력칸
        joinPwtxt = new JPasswordField();
        joinPwtxt.setBounds(328, 215, 232, 30);
        joinPwtxt.setFont(new Font("돋움체", 0, 15));
        //비밀번호 중복확인 입력칸
        joinPwChecktxt = new JPasswordField();
        joinPwChecktxt.setBounds(328, 315, 150, 30);
        joinPwChecktxt.setFont(new Font("돋움체", 0, 15));
        //이메일
        joinEmailtxt = new JTextField();
        joinEmailtxt.setBounds(328, 415, 103, 30);
        joinEmailtxt.setFont(new Font("돋움체", 0, 15));
        //이메일 @
        joinAt = new JLabel("@");
        joinAt.setHorizontalAlignment(SwingConstants.CENTER);
        joinAt.setBounds(430, 415, 20, 30);
        joinAt.setFont(new Font("돋움체", 0, 20));
        emailCb.setBackground(new Color(255, 255, 255));
        //이메일 콤보박스
        emailCb.setBounds(450,415,110,30);
        emailCb.setFont(new Font("돋움체", 0, 15));
        emailCb.addItem("naver.com");
        emailCb.addItem("daum.net");
        emailCb.addItem("gmail.com");
        //TODO 선택된 이메일 저장
        //회원가입 버튼
        joinBtn = new JButton("회원가입");
        joinBtn.setBackground(new Color(255, 255, 255));
        joinBtn.setBounds(300,500,100,30);
        joinBtn.setFont(new Font("돋움체", 0, 15));
        joinBtn.addActionListener(this);
        //TODO 회원정보저장하기 기능
        //아이디중복확인 버튼
        idCheckBtn = new JButton("중복확인");
        idCheckBtn.setBackground(new Color(255, 255, 255));
        idCheckBtn.setBounds(480,115,80,30);
        idCheckBtn.setFont(new Font("돋움체", 0, 10));
        //비밀번호 중복확인 버튼
        pwCheckBtn = new JButton("중복확인");
        pwCheckBtn.setBackground(new Color(255, 255, 255));
        pwCheckBtn.setBounds(480,315,80,30);
        pwCheckBtn.setFont(new Font("돋움체", 0, 10));
        //add
        joinPanel.add(joinId);
        joinPanel.add(joinPw);
        joinPanel.add(joinPwCheck);
        joinPanel.add(joinEmail);
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
        c.add(joinPanel);
        validate();
   }
   
   @Override
   public void actionPerformed(ActionEvent e) {
	   Object obj = e.getSource();
	   if(obj==joinBtn) {
		   try {
			   dispose();
				Login login = new Login();
				login.setVisible(true);
			} catch (Exception e2) {
				e2.printStackTrace();
			}
	   }else if(obj==idCheckBtn) {
		   //id 중복 유무 확인iduselbl
	   }else if(obj==pwCheckBtn) {
		   //pw 같은지 확인
	   }
	   
   }
   
   public static void main(String[] args) {
     
      
   }
}