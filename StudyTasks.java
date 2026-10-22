import javax.swing.*;

public class StudyTasks {

    public StudyTasks() {

        JFrame frame = new JFrame("Smart Study Planner");
        frame.setSize(450, 620);
        frame.setLayout(null);

        JLabel label = new JLabel("Enter Study Task:");
        label.setBounds(40, 40, 150, 30);
        frame.add(label);

        JTextField task = new JTextField();
        task.setBounds(210, 40, 190, 30);
        frame.add(task);

        JLabel deadlineLabel = new JLabel("Deadline (YYYY-MM-DD):");
        deadlineLabel.setBounds(40, 85, 170, 30);
        frame.add(deadlineLabel);

        JTextField deadline = new JTextField();
        deadline.setBounds(210, 85, 190, 30);
        frame.add(deadline);

        JLabel priorityLabel = new JLabel("Priority:");
        priorityLabel.setBounds(40, 130, 150, 30);
        frame.add(priorityLabel);

        String[] priorities = {"High", "Medium", "Low"};
        JComboBox<String> priority =
            new JComboBox<>(priorities);
        priority.setBounds(210, 130, 190, 30);
        frame.add(priority);

        JButton add = new JButton("Add Task");
        add.setBounds(160, 185, 120, 35);
        frame.add(add);

        DefaultListModel<String> taskList =
            new DefaultListModel<>();

        JList<String> list = new JList<>(taskList);

        JScrollPane scroll = new JScrollPane(list);
        scroll.setBounds(40, 240, 360, 150);
        frame.add(scroll);

        TaskDatabase.loadTasks(
            LoginDatabase.getStudentId(), taskList);

        JButton delete = new JButton("Delete Task");
        delete.setBounds(160, 410, 120, 35);
        frame.add(delete);

        add.addActionListener(e -> {

            String taskName = task.getText().trim();
            String taskDeadline = deadline.getText().trim();
            String taskPriority =
                (String) priority.getSelectedItem();

            if (taskName.isEmpty() ||
                taskDeadline.isEmpty()) {

                JOptionPane.showMessageDialog(frame,
                    "Please enter task and deadline!");
                return;
            }

            try {
                java.time.LocalDate.parse(taskDeadline);
            } catch (java.time.format.DateTimeParseException ex) {
                JOptionPane.showMessageDialog(frame,
                    "Enter date as YYYY-MM-DD!");
                return;
            }

            boolean saved = TaskDatabase.addPlannedTask(
                LoginDatabase.getStudentId(),
                taskName,
                taskDeadline,
                taskPriority
            );

            if (saved) {
                taskList.addElement(taskName);

                JOptionPane.showMessageDialog(frame,
                    "Task Added Successfully!");

                task.setText("");
                deadline.setText("");
            } else {
                JOptionPane.showMessageDialog(frame,
                    "Could not save task!");
            }
        });

        delete.addActionListener(e -> {

            int index = list.getSelectedIndex();

            if (index == -1) {
                JOptionPane.showMessageDialog(frame,
                    "Please select a task!");
                return;
            }

            String taskName = taskList.getElementAt(index);

            boolean deleted = TaskDatabase.deleteTask(
                LoginDatabase.getStudentId(), taskName);

            if (deleted) {
                taskList.remove(index);

                JOptionPane.showMessageDialog(frame,
                    "Task Deleted Successfully!");
            } else {
                JOptionPane.showMessageDialog(frame,
                    "Could not delete task!");
            }
        });
        JButton suggest = new JButton("Suggested Study Order");
suggest.setBounds(115, 460, 210, 35);
frame.add(suggest);

suggest.addActionListener(e -> {

    DefaultListModel<String> orderedTasks =
        new DefaultListModel<>();

    TaskDatabase.loadSuggestedOrder(
        LoginDatabase.getStudentId(),
        orderedTasks
    );

    JList<String> orderedList = new JList<>(orderedTasks);

    JScrollPane orderScroll =
        new JScrollPane(orderedList);

    orderScroll.setPreferredSize(
        new java.awt.Dimension(430, 220)
    );

    JOptionPane.showMessageDialog(
        frame,
        orderScroll,
        "Suggested Study Order",
        JOptionPane.INFORMATION_MESSAGE
    );
});
JButton edit = new JButton("Edit Task");
edit.setBounds(160, 500, 120, 35);
frame.add(edit);

edit.addActionListener(e -> {

    int index = list.getSelectedIndex();

    if (index == -1) {
        JOptionPane.showMessageDialog(frame,
            "Please select a task!");
        return;
    }

    String taskName = taskList.getElementAt(index);

    String newDeadline = JOptionPane.showInputDialog(
        frame, "Enter New Deadline (YYYY-MM-DD):");

    if (newDeadline == null) return;

    try {
        java.time.LocalDate.parse(newDeadline);
    } catch (Exception ex) {
        JOptionPane.showMessageDialog(frame,
            "Invalid Date!");
        return;
    }

    String[] editpriorities = {"High", "Medium", "Low"};

    String newPriority = (String)
        JOptionPane.showInputDialog(
            frame,
            "Select New Priority:",
            "Edit Priority",
            JOptionPane.QUESTION_MESSAGE,
            null,
            editpriorities,
            editpriorities[0]
        );

    if (newPriority == null) return;

    boolean updated = TaskDatabase.updateTask(
        LoginDatabase.getStudentId(),
        taskName,
        newDeadline,
        newPriority
    );

    if (updated) {
        JOptionPane.showMessageDialog(frame,
            "Task Updated Successfully!");
    } else {
        JOptionPane.showMessageDialog(frame,
            "Task Update Failed!");
    }
});

        frame.setDefaultCloseOperation(
            JFrame.DISPOSE_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}