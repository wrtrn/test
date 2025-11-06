package learning.expressJava.lesson3;

public class University {

    private static String universityName;
    private final int studentID;
    private String studentName;

    University(int studentID, String studentName) {
        this.studentID = studentID;
        this.studentName = studentName;
    }

    static void changeUniversityName(String newName) {
        universityName = newName;
    }

    String getStudentName() {
        return studentName;
    }

    void printStudentInfo() {
        System.out.println(studentName + " " + studentID + " " + universityName);
    }
}
