package expressJava.lesson5.museum;

public class Main {
    public static void main(String[] args) {
        MuseumController museumController = new MuseumController();

        Manuscript manuscript = new Manuscript("Very old");
        Sculpture sculpture = new Sculpture("Ancient history");

        museumController.controlExhibit(manuscript);
        museumController.showInfo(manuscript);

        museumController.controlExhibit(sculpture);
        museumController.showInfo(sculpture);
    }
}
