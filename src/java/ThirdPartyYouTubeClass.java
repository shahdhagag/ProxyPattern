/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

import java.util.HashMap;

public class ThirdPartyYouTubeClass implements ThirdPartyYouTubeLib {
    @Override
    public HashMap<String, Video> popularVideos() {
        // Simulate fetching popular videos from the third-party service
        return getRandomVideos();
    }

    @Override
    public Video getVideo(String videoId) {
        // Simulate fetching a video by ID from the third-party service
        return getSomeVideo(videoId);
    }

    private HashMap<String, Video> getRandomVideos() {
        // Simulate fetching random videos from the third-party service
        HashMap<String, Video> hmap = new HashMap<String, Video>();
        hmap.put("catzzzzzzzzz", new Video("sadgahasgdas", "Catzzzz.avi"));
        hmap.put("mkafksangasj", new Video("mkafksangasj", "Dog play with ball.mp4"));
        hmap.put("dancesvideoo", new Video("asdfas3ffasd", "Dancing video.mpq"));
        hmap.put("dlsdk5jfslaf", new Video("dlsdk5jfslaf", "Barcelona vs RealM.mov"));
        hmap.put("3sdfgsd1j333", new Video("3sdfgsd1j333", "Programing lesson#1.avi"));
        return hmap;
    }

    private Video getSomeVideo(String videoId) {
        // Simulate fetching a specific video from the third-party service
        return new Video(videoId, "Some video title");
    }
}
