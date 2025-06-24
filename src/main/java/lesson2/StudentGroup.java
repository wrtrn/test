package lesson2;

public class StudentGroup {
    String groupName;
    int studentCount;

    StudentGroup(String groupName, int studentCount) {
        this.groupName = groupName;
        this.studentCount = studentCount;
    }

    void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    void setStudentCount(int studentCount) {
        this.studentCount = studentCount;
    }

    String getGroupName() {
        return groupName;
    }

    int getStudentCount() {
        return studentCount;
    }

    void printInfo() {
        System.out.println(groupName + " " + studentCount);
    }
}
