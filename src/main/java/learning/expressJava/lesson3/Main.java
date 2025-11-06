package learning.expressJava.lesson3;

public class Main {
    public static void main(String[] args) {

//        Company employee1 = new Company(1,"Ivan");
//        Company employee2 = new Company(2,"Peter");
//        Company employee3 = new Company(3,"Maxim");
//        Company.companyName = "Hello Company";
//        Company.printCompanyName();
//        employee1.employeeId=5;

//        System.out.println(MathConstants.calculateCircleArea(5.5));
//        System.out.println(MathConstants.calculateCircleArea(5.6));
//        System.out.println(MathConstants.calculateCircumference(7.5));
//        System.out.println(MathConstants.calculateCircumference(7.7));

//        Library library = new Library();

//    University student1 = new University(1,"Ivan1");
//    University student2= new University(2,"Ivan2");
//    University student3= new University(3,"Ivan3");
//    University.changeUniversityName("Hello");
//    student1.printStudentInfo();
//    student2.printStudentInfo();
//    student3.printStudentInfo();

//        GameSettings firstGame = new GameSettings("Jumbo");
//        GameSettings secondName = new GameSettings("JJasdkk");
//        GameSettings.maxPlayers = 3;
//        firstGame.addPlayer();
//        firstGame.addPlayer();
//        firstGame.addPlayer();
//        firstGame.addPlayer();
//        firstGame.addPlayer();
//        firstGame.addPlayer();
//        firstGame.addPlayer();
//        secondName.addPlayer();
//        secondName.addPlayer();
//        firstGame.printGameStatus();
//        secondName.printGameStatus();

        Person personOne = new Person("Иван","Иванов","123-45-6789");
        Person personTwo = new Person("Угор","Петров","113-11-245129");
        Person personThree = new Person("ерема","Сергеев","14124-31235-6312319");
        personOne.setFirstName("Константин");
        personOne.printPersonInfo();
        personTwo.printPersonInfo();
        personThree.printPersonInfo();
    }
}
