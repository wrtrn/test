package learning.clearCode.complexTasks.streamingLibrary;

public class Video {
    private int id;
    private String url;

    public Video(int id, String url) {
        this.id = id;
        this.url = url;
    }

    public String getUrl() {
        return url;
    }

    public int getId() {
        return id;
    }
}
