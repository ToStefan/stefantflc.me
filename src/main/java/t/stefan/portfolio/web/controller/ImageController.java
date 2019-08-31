package t.stefan.portfolio.web.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import t.stefan.portfolio.service.impl.ImageServiceImpl;
import t.stefan.portfolio.web.dto.ImageDTO;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping(value = "/api/images")
public class ImageController {

    private final ImageServiceImpl imageService;

    @PostMapping
    public ResponseEntity<ImageDTO> uploadFile(@RequestParam("file") MultipartFile file,
                                               @RequestParam("user") String user,
                                               @RequestParam("collection_id") Long collectionId){
        ImageDTO imageDTO = imageService.add(file, user, collectionId);
        return new ResponseEntity<>(imageDTO, HttpStatus.OK);
    }

    @GetMapping("/{collection_id}")
    public ResponseEntity<List<ImageDTO>> getImagesByCollection(@PathVariable("collection_id") Long id){
        List<ImageDTO> images = imageService.findAllByCollection(id);
        return new ResponseEntity<>(images, HttpStatus.OK);
    }
}
