import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class GameRunner {
    public static void main(String[] args){
        ImageIcon image = new ImageIcon("main.png");
        JLabel label = new JLabel();
        label.setText("<- STINKY 😀");
        label.setIcon(image);

        JFrame frame = new JFrame();

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.setSize(500,500);
        frame.add(label);

        WindowMaker window = new WindowMaker("uh window", null, );
    }
}
