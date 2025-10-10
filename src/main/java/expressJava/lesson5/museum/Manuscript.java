package expressJava.lesson5.museum;

public class Manuscript  extends  Exhibit{
   private String history;

   Manuscript(String history)
   {
       this.history = history;
   }

    @Override
    void showStorageConditions() {
        System.out.println("Manuscript: Controlled humidity");
    }

    @Override
    void showHistory() {
        System.out.println("Manuscript history: " + history);
    }
}
