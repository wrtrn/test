package expressJava.lesson3;

public class Company {
    static String companyName;
    final int employeeId;
    String employeeName;

    Company(int employeeId, String employeeName) {
        this.employeeId = employeeId;
        this.employeeName = employeeName;
    }

    static void printCompanyName() {
        System.out.println(companyName);
    }

    String getEmployeeName(){
        return employeeName;
    }

    void setEmployeeName(String employeeName)
    {
        this.employeeName=employeeName;
    }
}
