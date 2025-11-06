package learning.clearCode.complexTasks.streamingLibrary;

import learning.clearCode.complexTasks.streamingLibrary.adapters.AviVideoAdapter;
import learning.clearCode.complexTasks.streamingLibrary.adapters.MovVideoAdapter;
import learning.clearCode.complexTasks.streamingLibrary.adapters.WmvVideoAdapter;

public class Main {
    public static void main(String[] args) {
        VideoService videoService = new VideoService(new AviVideoAdapter(), new MovVideoAdapter(), new WmvVideoAdapter());

        String videoPath = "path/to/example.avi";
        Video video = videoService.uploadVideo(videoPath);

        videoService.streamVideo(video.getId());
    }
}
