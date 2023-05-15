package View;
//메인 창의 UI를 구성하는 클래스
import javax.swing.*;

public class MainView extends JFrame {
  public MainView() {
    // JFrame의 기본 설정
    this.setTitle("가계부 프로그램");
    this.setSize(1000, 500);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    
    // JFrame에 컴포넌트 추가
    JLabel label = new JLabel("안녕하세요!");
    this.add(label);
    
    // JFrame을 화면에 표시
    this.setVisible(true);
  }
  
  public static void main(String[] args) {
    // 새로운 MainView 객체 생성
    MainView frame = new MainView();
  }
}

