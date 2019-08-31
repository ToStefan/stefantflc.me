package t.stefan.portfolio.web.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.multipart.MultipartFile;
import t.stefan.portfolio.util.FileUtils;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;

@Slf4j
@Getter
@NoArgsConstructor
public class ImageDTO {

    private File file;
    private Long id;
    private String url;
    private String name;
    private String user;
    private Long collection;

    public String getFullPath(String dropboxEnv) {
        return "/" + dropboxEnv + "/" + this.user + "/" + this.collection + "/" + this.name;
    }

    public void createImageName(String name) {
        this.name = LocalDateTime.now() + "-" + name;
    }

    public void createImageUrl(String url) {
        this.url = url.replace("dl=0", "raw=1");
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setFile(MultipartFile file) {
        try {
            this.file = FileUtils.toFile(file);
        } catch (IOException e) {
            log.error(e.getMessage());
        }
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public void setCollection(Long collection) {
        this.collection = collection;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
