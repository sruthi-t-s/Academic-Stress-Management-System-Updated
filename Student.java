public class Student {

    private int studentId;
    private String username;

    public Student(int studentId, String username) {
        this.studentId = studentId;
        this.username = username;
    }

    public int getStudentId() {
        return studentId;
    }

    public String getUsername() {
        return username;
    }
}