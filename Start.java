package project1;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.io.IOException;
import java.awt.image.BufferedImage;

class MyPanel1 extends JPanel implements ActionListener {
	//final 기호를 사용해 상수로 활용, 이미지의 크기 및 초기위치
	private final int WIDTH = 300;
	private final int HEIGHT = 500;
	private final int START_X = 300;
	private final int START_Y = 300;
	//이미지 파일을 담기 위한 BufferedImage 객체 변수를 생성
	private BufferedImage image;
	//타이머 객체도 생성
	private Timer timer;
	private int x, y;
	
	public MyPanel1() {
		//패널 배경식 지정, 패널 크기와 JFrame 크기가 동일
		setBackground(Color.WHITE);
		setPreferredSize(new Dimension(WIDTH, HEIGHT));
		//추천 기본 크기를 알아서 지정
		//배치관리자에 따라, 또는 패널, 버튼 컨테이너에 따라 
		//setSize(), setPreferrendSize() 쓸 수 있는게 다름
		setDoubleBuffered(true);//그림을 넣을 메모리 활성화. 없어도 되긴함..
		//이미지 파일 객체 생성
		File input = new File("C://Java2//myJava//project1//image/dabid.png");
		try {
			//imput에 가져온 이미지 파일을 버퍼이미지에 저장
			image = ImageIO.read(input);
		}catch(IOException e) { //예외처리
			e.printStackTrace();
		} //이미지를 읽는다.
		x = START_X;
		y = START_Y;
		
		//타이머 생성자, 1이상이면 어떤 값이 들어와도 동일한 결과가 수행되며
		//0이하에서는 매우 빠른 속도로 움직인다.
		timer = new Timer(1, this);
		timer.start(); //타이머 객체를 생성하고 시작
	}
	//MyPanel1 메소드는 초기 이미지 조건 파일 객체 등 생성만 담당하고
	//나머지 이미지의 동작은 모두 아래 메소드들이 하는 것 같다.
	@Override
	//그래픽을 출력하는 메소드
	public void paintComponent(Graphics g) {
		//그래픽 객체를 paintComponent 메소드로 이동시킴
		//그림그리려면 필수. 첫문장에 나와야함.
		super.paintComponent(g);
		//이미지를 화면에 그린다.
		//drawImage 메소드가 없으면 그림이 생성되지 않는다.
		g.drawImage(image, x, y, this);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		//이벤트가 발생하면 좌표를 해당 픽셀 만큼 이동시킴
		x += 1;
		y -= 1; //y는 -일수록 높아짐
		//이미지 객체가 범위를 벗어난 초기 위치로 이동시킴
		if(x > WIDTH) {
			x = START_X;
			y = START_Y;
		}
		//동작이 완료되면 repaint 메소드로 이미지를 갱신
		//repaint 메소드가 없으면 그림이 이동하지 않는다.
		repaint();
	}
}
//메인 클래스는 JFrame 객체가 된다.
public class Start extends JFrame {
	public Start() {
		//JPanel 클래스인 MyPanel1의 생성자를 추가한다
		add(new MyPanel1()); //패널객체 추가
		setTitle("당신의 경매사이트 DaBID");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);//가운데 출력
		setSize(700, 600);
		setVisible(true);
	}
	public static void main(String[] args) {
		new Start();
	}
}