package expressJava.lesson5.museum;

public class MuseumController {

    void controlExhibit(Exhibit exhibit) {
        exhibit.showStorageConditions();
    }

    void showInfo(Exhibit exhibit) {
        exhibit.showHistory();
    }
}
