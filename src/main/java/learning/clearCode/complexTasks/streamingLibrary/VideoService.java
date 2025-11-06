package learning.clearCode.complexTasks.streamingLibrary;

import learning.clearCode.complexTasks.streamingLibrary.adapters.AviVideoAdapter;
import learning.clearCode.complexTasks.streamingLibrary.adapters.MovVideoAdapter;
import learning.clearCode.complexTasks.streamingLibrary.adapters.VideoAdapter;
import learning.clearCode.complexTasks.streamingLibrary.adapters.WmvVideoAdapter;

import java.util.ArrayList;
import java.util.List;

public class VideoService {
    AviVideoAdapter aviVideoAdapter;
    MovVideoAdapter movVideoAdapter;
    WmvVideoAdapter wmvVideoAdapter;
    static Integer id = 0;
    List<Video> videos = new ArrayList<>();

    public VideoService(AviVideoAdapter aviVideoAdapter, MovVideoAdapter movVideoAdapter, WmvVideoAdapter wmvVideoAdapter) {
        this.aviVideoAdapter = aviVideoAdapter;
        this.movVideoAdapter = movVideoAdapter;
        this.wmvVideoAdapter = wmvVideoAdapter;
    }

    public Video uploadVideo(String videoPath) {
        String format = videoPath.substring(videoPath.length() - 3);
        VideoAdapter adapter;

        switch (format) {
            case "avi":
                adapter = aviVideoAdapter;
                break;
            case "mov":
                adapter = movVideoAdapter;
                break;
            case "wmv":
                adapter = wmvVideoAdapter;
                break;
            default:
                throw new NoFormatException("We don't have this format");
        }
        String mp4Video = adapter.convertToMp4(videoPath);
        Video uploadedVideo = new Video(id++, mp4Video);
        videos.add(uploadedVideo);
        return uploadedVideo;
    }

    public void streamVideo(int id) {
        Video foundedVideo = videos
                .stream()
                .filter(el -> el.getId() == id)
                .findFirst()
                .orElseThrow(() -> new RuntimeException("We didn't manage to find this video"));

        System.out.println("Streaming video: " + foundedVideo.getUrl());
    }
}
