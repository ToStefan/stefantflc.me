package t.stefan.portfolio.service.impl;

import com.dropbox.core.DbxException;
import com.dropbox.core.DbxRequestConfig;
import com.dropbox.core.v2.DbxClientV2;
import com.dropbox.core.v2.files.FileMetadata;
import com.dropbox.core.v2.files.UploadErrorException;
import com.dropbox.core.v2.files.WriteMode;
import com.dropbox.core.v2.sharing.SharedLinkMetadata;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import t.stefan.portfolio.service.DropboxService;
import t.stefan.portfolio.web.dto.ImageDTO;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.File;

@Slf4j
@Service
public class DropboxServiceImpl implements DropboxService {

    @Value("${dropbox.token}")
    private String accessToken;

    @Value("${dropbox.env}")
    private String dropboxEnv;

    @Override
    public ImageDTO uploadFile(ImageDTO imageDTO) {
        DbxClientV2 client = getDXClient();
        FileInputStream inputStream;
        FileMetadata metaData;
        SharedLinkMetadata sharedUrl = null;
        File file = imageDTO.getFile();

        try {
            inputStream = new FileInputStream(file);
            metaData = client.files().uploadBuilder(imageDTO.getFullPath(dropboxEnv))
                    .withMode(WriteMode.ADD)
                    .uploadAndFinish(inputStream);
            sharedUrl = client.sharing().createSharedLinkWithSettings(imageDTO.getFullPath(dropboxEnv));
            inputStream.close();
        } catch (IOException e) {
            log.error(e.getMessage());
        } catch (UploadErrorException e) {
            log.error(e.getMessage());
        } catch (DbxException e) {
            log.error(e.getMessage());
        }
        file.delete();

        imageDTO.createImageUrl(sharedUrl.getUrl());
        return imageDTO;
    }

    private DbxClientV2 getDXClient() {
        DbxRequestConfig config = DbxRequestConfig.newBuilder("stefantflc-me").build();
        DbxClientV2 client = new DbxClientV2(config, accessToken);
        return client;
    }
}
