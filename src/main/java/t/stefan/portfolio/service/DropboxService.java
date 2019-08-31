package t.stefan.portfolio.service;

import t.stefan.portfolio.web.dto.ImageDTO;

public interface DropboxService {

    ImageDTO uploadFile(ImageDTO file);
}
