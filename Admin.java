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
	
	String[] cbList = {"카테고리 별 통계", "금액 별 통계", "경매 log"};
	String[] categoryNames = {"디지털기기","취미/게임/음반","가구/인테리어","스포츠/레저","생활가전","의류","반려동물용품","뷰티/미용","중고차","도서"};
	
	String bidding_header[] = {"상품 이름", "판매자 ID", "참여 인원", "현재 가격"};
	String bidend_header[] = {"상품 이름", "판매자 ID", "구매자 ID", "참여 인원", "현재 가격"};
	
	String bidding_contents[][] = {
			{"인형ㅍㅍ", "aaa", "4", "5600"},
			{"아이디ㅍㅍ", "ccc", "6", "19000"}
	};
	
	String bidend_contents[][] = {
			{"인형ㅍㅍ", "aaa", "bbb", "4", "5600"},
			{"아이디ㅍㅍ", "ccc", "aaa", "6", "19000"}
	};
	
	public Admin() {
		
		setTitle("DaBID 관리자 페이지");
		setSize(1300, 900);
		setResizable(false);
		setLayout(null);
		
		Container c = getContentPane();
		
		adminPanel = new JPanel();
		adminPanel.setLayout(null);
		adminPanel.setBounds(60, 98, 1150, 682);
		
		// JComboBox
		// adminCb (관리자 콤보박스)
		adminCb = new JComboBox<>(cbList);
		adminCb.setBounds(60, 60, 200, 34);
		adminCb.setFont(new Font("돋움체", 0, 17));
		adminCb.addActionListener(this);
		
		// JLabel
		// categoryName (카테고리 이름 라벨)
		
		// memberId (멤버 아이디 라벨)
		memberId = new JLabel("아이디 : aaa");
	    memberId.setBounds(1150, 20, 100, 30);
	    memberId.setFont(new Font("돋움체", 0, 15));
	    // biddingLb (진행중인 경매 라벨)
	    biddingLb = new JLabel("진행중인 경매");
	    biddingLb.setBounds(250, 25, 221, 27);
	    biddingLb.setFont(new Font("돋움체", 0, 19));
	    // bidendLb (종료된 경매 라벨)
	    bidendLb = new JLabel("종료된 경매");
	    bidendLb.setBounds(790, 25, 221, 27);
	    bidendLb.setFont(new Font("돋움체", 0, 19));
	    // logo (메인 로고)
	    logo = new JLabel(new ImageIcon(Login.class.getResource("./image/logo.png")));
		logo.setBounds(20,20,130,40);
		 
	    
	    // JButton
	    // backBtn (뒤로가기)
	    backBtn = new JButton("뒤로가기");
	    backBtn.setBounds(1150, 800, 120, 30);
	    backBtn.setFont(new Font("돋움체", 0, 15));
	    backBtn.addActionListener(this);
	    
	    // JProgressBar
	    // categoryJpb (카테고리 막대 바)
	    JProgressBar categoryJpb = new JProgressBar();
	    // priceJpb (금액 막대 바)
	    JProgressBar priceJpb = new JProgressBar();
	    
		
		
		// JTable
		// biddingTb (진행중인 경매 테이블)
		DefaultTableModel biddingMd = new DefaultTableModel(bidding_contents, bidding_header);
		biddingTb = new JTable(biddingMd);
		// bidendTb (종료된 경매 테이블)
		DefaultTableModel bidendMd = new DefaultTableModel(bidend_contents, bidend_header);
		bidendTb = new JTable(bidendMd);
		
		// 패널에 추가
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
			// 카테고리별 통계
			for (int i = 0; i < n; i++) {
				// 카테고리 이름
				categoryName = new JLabel("카테고리" + i);
				categoryName.setFont(new Font("돋움체", 0, 17));
				categoryName.setBounds(x[0], y, 200, 35);
				adminPanel.add(categoryName);
				
				// 카테고리 그래프
				JProgressBar categoryJpb = new JProgressBar();
				categoryJpb.setBounds(x[1], y, 900, 35);
				categoryJpb.setValue((int)Math.random() * 100);
				adminPanel.add(categoryJpb);
				
				y += 60;
			}
			
		} else if (index == 1) {
			// 금액별 통계
			for (int i = 0; i < n; i++) {
				// 카테고리 이름
				categoryName = new JLabel("카테고리" + i);
				categoryName.setFont(new Font("돋움체", 0, 17));
				categoryName.setBounds(x[0], y, 200, 35);
				adminPanel.add(categoryName);
				
				// 카테고리 그래프
				JProgressBar priceJpb = new JProgressBar();
				priceJpb.setBounds(x[1], y, 900, 35);
				priceJpb.setValue((int)Math.random() * 100);
				adminPanel.add(priceJpb);
				
				y += 60;
			}
		} else if (index == 2) {
			// 경매 log
			adminPanel.add(biddingLb);
			adminPanel.add(bidendLb);
			
			// biddingJsp (진행중인 경매 스크롤)
			// biddingTb 삽입
			JScrollPane biddingJsp = new JScrollPane(biddingTb);
			biddingJsp.setBounds(60, 65, 480, 600);
			
			adminPanel.add(biddingJsp);
			biddingJsp.setViewportView(biddingTb);
			
			// bidendJsp (종료된 경매 스크롤)
			// bidendTb 삽입
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
