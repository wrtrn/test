package clearCode.complexTasks.streamingLibrary.adapters;

public class WmvVideoAdapter implements VideoAdapter {
    @Override
    public String convertToMp4(String path) {
        String result = path.substring(0, path.length() - 3);
        return result + "mp4";
    }
}
