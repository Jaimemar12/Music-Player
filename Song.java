import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport; // google-api-java-client
import com.google.api.client.json.jackson2.JacksonFactory; // jar_files
import com.google.api.services.youtube.YouTube; // google-api-services-youtube-v3-rev222-1.25.0
import com.google.api.services.youtube.model.SearchResult; // google-api-java-client


import java.util.List;

public class Song {
    private String tittle;
    private String url;
    Song next;

    public Song(String tittle) {
        YouTube youTube = null;
        try {
            // Default Way to Construct a YouTube Object
            youTube = new YouTube.Builder(
                    GoogleNetHttpTransport.newTrustedTransport(),
                    JacksonFactory.getDefaultInstance(),
                    null
            )
                    .setApplicationName("CS1301")
                    .build();
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            assert youTube != null;
            // List of obtained results from search query of 'tittle'
            List<SearchResult> results = youTube.search()
                    .list("id,snippet")
                    .setQ(tittle)
                    .setMaxResults(1L)
                    .setType("video")
                    .setFields("items(id/kind,id/videoId,snippet/title,snippet/thumbnails/default/url)")
                    .setOrder("relevance")
                    .setKey("AIzaSyDlLx9wM-OJ8rYWMRSXIId5TVoKRjw-uwE")
                    .execute()
                    .getItems();
            //AIzaSyAUZ3nR3vEalbYd2izFFJBlOBO3DNAxtIs

            if (!results.isEmpty()) {
                url = "https://www.youtube.com/watch?v=" + results.get(0).getId().getVideoId();
                this.tittle = results.get(0).getSnippet().getTitle();
                next = null;
            } else {
                this.tittle = "";
                next = null;
            }
        } catch (Exception e) {
            this.tittle = "";
            next = null;
        }
    }
    public String getTittle() {
        return tittle;
    }

    public String getUrl(){
        return url;
    }
}
