package library;

import java.util.Map;

public class Student extends Member{
    private String studentId;

    public Student(String name, int memberId, String type, String phoneNumber, String studentId) {
        super(name, memberId, type, phoneNumber, new LibrarySystem());
        this.studentId = studentId;
    }

    public String getStudentId() {
        return studentId;
    }
}
