package homework_11_10_2024;

import java.util.ArrayList;
import java.util.List;

public class Task_1 {

    public static void main(String[] args) {
        
        SimpleMediaCollection collection = new SimpleMediaCollection();
        
        // Добавление разных типов медиа
        collection.addMedia("Programming Book", MediaCollection.MediaType.EBOOK, "http://example.com/book");
        collection.addMedia("Nature Video", MediaCollection.MediaType.VIDEO, "http://example.com/video");
        collection.addMedia("Classical Music", MediaCollection.MediaType.AUDIO, "http://example.com/audio");
        collection.addMedia("Landscape Photo", MediaCollection.MediaType.IMAGE, "http://example.com/image");
        
        
        System.out.println("All media items:");
        collection.getAllMedia().forEach(System.out::println);
        
        System.out.println("\nFinding media by title:");
        System.out.println(collection.getMediaByTitle("Programming Book"));
    }

    
    public interface MediaCollection {
        
        enum MediaType {
            EBOOK, AUDIO, VIDEO, IMAGE
        }

        
        void addMedia(String title, MediaType type, String url);

        
        List<Media> getAllMedia();

        
        @NotNull
        Media getMediaByTitle(String title);

        
        @Deprecated
        void removeMedia(String title);

        // Внутренний класс для представления медиа
        class Media {
            private String title;
            private MediaType type;
            private String url;

            
            public Media(String title, MediaType type, String url) {
                this.title = title;
                this.type = type;
                if (url == null || url.isEmpty()) {
                    throw new IllegalArgumentException("URL cannot be null or empty");
                }
                this.url = url;
            }

            
            public String getTitle() {
                return title;
            }

            public MediaType getType() {
                return type;
            }

            public String getUrl() {
                return url;
            }

            @Override
            public String toString() {
                return "Media{" +
                        "title='" + title + '\'' +
                        ", type=" + type +
                        ", url='" + url + '\'' +
                        '}';
            }
        }
    }

    
    public static class SimpleMediaCollection implements MediaCollection {
        private List<Media> mediaList = new ArrayList<>();

        @Override
        public void addMedia(String title, MediaType type, String url) {
            mediaList.add(new Media(title, type, url));
        }

        @Override
        public List<Media> getAllMedia() {
            return new ArrayList<>(mediaList);
        }

        @Override
        @NotNull
        public Media getMediaByTitle(String title) {
            return mediaList.stream()
                    .filter(media -> media.getTitle().equals(title))
                    .findFirst()
                    .orElseThrow(() -> new IllegalArgumentException("Media not found: " + title));
        }

        @Override
        @Deprecated
        public void removeMedia(String title) {
            mediaList.removeIf(media -> media.getTitle().equals(title));
        }
    }
}