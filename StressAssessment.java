import javax.swing.*;

public class StressAssessment {

    public StressAssessment() {

        JFrame frame = new JFrame("Stress Assessment");

        frame.setSize(500, 650);
        frame.setLayout(null);

        JLabel title = new JLabel("Academic Stress Assessment");
        title.setBounds(140, 20, 250, 30);
        frame.add(title);
        JLabel q1 = new JLabel("1. Do you feel stressed about exams?");
q1.setBounds(40, 80, 400, 30);
frame.add(q1);

JComboBox<String> answer1 = new JComboBox<>(
    new String[]{"Never", "Sometimes", "Often"}
);
answer1.setBounds(40, 120, 200, 30);
frame.add(answer1);
JLabel q2 = new JLabel("2. Do you struggle to finish assignments?");
q2.setBounds(40, 160, 420, 30);
frame.add(q2);

JComboBox<String> answer2 = new JComboBox<>(
    new String[]{"Never", "Sometimes", "Often"}
);
answer2.setBounds(40, 195, 200, 30);
frame.add(answer2);
JLabel q3 =new JLabel("3. Do you feel overwhelmed by studies?");
q3.setBounds(40,235,420,30);
frame.add(q3);
JComboBox<String> answer3 = new JComboBox<>(
    new String[]{"Never","Sometimes","Often"}
);
answer3.setBounds(40,270,200,30);
frame.add(answer3);
JLabel q4 = new JLabel("4. Do you find it difficult to concentrate?");
q4.setBounds(40, 310, 420, 30);
frame.add(q4);

JComboBox<String> answer4 = new JComboBox<>(
    new String[]{"Never", "Sometimes", "Often"}
);
answer4.setBounds(40, 345, 200, 30);
frame.add(answer4);
JLabel q5 = new JLabel("5.Do you worry about academic performanance?");
q5.setBounds(40, 385, 420, 30);
frame.add(q5);

JComboBox<String> answer5 = new JComboBox<>(
    new String[]{"Never", "Sometimes", "Often"}
);
answer5.setBounds(40, 420, 200, 30);
frame.add(answer5);

        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        JButton submit = new JButton("Submit");
submit.setBounds(180, 490, 120, 35);
frame.add(submit);
submit.addActionListener(e -> {

    int score = 0;

    JComboBox[] answers = {
        answer1, answer2, answer3, answer4, answer5
    };

    for (int i = 0; i < 5; i++) {

        String selected = (String) answers[i].getSelectedItem();

        if (selected.equals("Sometimes")) {
            score = score + 1;
        }
        else if (selected.equals("Often")) {
            score = score + 2;
        }
    }

    String result;

    if (score <= 3) {
        result = "Low Academic Stress";
    }
    else if (score <= 6) {
        result = "Moderate Academic Stress";
    }
    else {
        result = "High Academic Stress";
    }
    String level;

if (score <= 3) {
    level = "Low";
} else if (score <= 6) {
    level = "Moderate";
} else {
    level = "High";
}

boolean saved = StressDatabase.saveResult(LoginDatabase.getStudentId(), score, level);

if (!saved) {
    JOptionPane.showMessageDialog(frame,
        "Could not save the result to database!");
}

    JOptionPane.showMessageDialog(frame,
        "Your Score: " + score + "/10\n" + result);
        new Recommendations(score);
});
        frame.setVisible(true);

    }
}