/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import java.sql.*;
import java.util.HashMap;

public class YouTubeCacheProxy implements ThirdPartyYouTubeLib {
    private final ThirdPartyYouTubeLib youtubeService;
    private final HashMap<String, Video> cacheAll = new HashMap<>();

    public YouTubeCacheProxy() {
        this.youtubeService = new ThirdPartyYouTubeClass();
    }

    @Override
    public HashMap<String, Video> popularVideos() {
        HashMap<String, Video> cachePopular = new HashMap<>();
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/your_database", "username", "password")) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM popular_videos");
            while (resultSet.next()) {
                String videoId = resultSet.getString("video_id");
                String title = resultSet.getString("title");
                cachePopular.put(videoId, new Video(videoId, title));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cachePopular.isEmpty() ? youtubeService.popularVideos() : cachePopular;
    }

    @Override
    public Video getVideo(String videoId) {
        Video video = cacheAll.get(videoId);
        if (video == null) {
            video = youtubeService.getVideo(videoId);
            cacheAll.put(videoId, video);
            // Store video in MySQL for future access
            try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/your_database", "username", "password")) {
                PreparedStatement statement = connection.prepareStatement("INSERT INTO cached_videos (video_id, title) VALUES (?, ?)");
                statement.setString(1, video.id);
                statement.setString(2, video.title);
                statement.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Retrieved video '" + videoId + "' from cache.");
        }
        return video;
    }

    public void reset() {
        cacheAll.clear();
    }
}
