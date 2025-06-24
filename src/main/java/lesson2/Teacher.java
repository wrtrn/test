package lesson2;

public class Teacher {
    String name, subject;

    Teacher(String name, String subject)
    {
        this.name=name;
        this.subject=subject;
    }

    public String getName() {
        return name;
    }

    public String getSubject()
    {
        return subject;
    }

    void setName(String name){
        this.name = name;
    }

    void setSubject(String subject)
    {
        this.subject=subject;
    }

    void printInfo()
    {
        System.out.println(name + " " + subject);
    }
}
