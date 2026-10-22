import javax.swing.*;

public class Recommendations {

    public Recommendations(int score) {

        JFrame frame = new JFrame("Recommendations");
        frame.setSize(450, 350);
        frame.setLayout(null);

        JLabel title = new JLabel("Stress Management Tips");
        title.setBounds(120, 20, 250, 30);
        frame.add(title);

        JTextArea tips = new JTextArea();
        tips.setBounds(40, 70, 350, 180);
        tips.setEditable(false);

        StressTips stressTips;

if (score <= 3) {
    stressTips = new LowStressTips();
}
else if (score <= 6) {
    stressTips = new ModerateStressTips();
}
else {
    stressTips = new HighStressTips();
}

tips.setText(stressTips.getTips());


        frame.add(tips);

        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
