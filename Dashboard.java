import javax.swing.*;

public class Dashboard {

    public Dashboard() {

        JFrame frame = new JFrame("Student Dashboard");
        frame.setSize(400, 350);
        frame.setLayout(null);

        JLabel title = new JLabel("Student Dashboard");
        title.setBounds(130, 20, 200, 30);
        frame.add(title);

        JButton stress = new JButton("Stress Assessment");
        stress.setBounds(100, 80, 200, 35);
        frame.add(stress);
        stress.addActionListener(e -> {
        new StressAssessment();
        });
        JButton tasks = new JButton("Study Tasks");
        tasks.setBounds(100, 140, 200, 35);
        frame.add(tasks);
        tasks.addActionListener(e -> {
    new StudyTasks();
});

        JButton tips = new JButton("Recommendations");
        tips.setBounds(100, 200, 200, 35);
        frame.add(tips);
        tips.addActionListener(e -> {
    JOptionPane.showMessageDialog(frame,
        "Please complete the Stress Assessment to view your recommendations.");
});

        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}