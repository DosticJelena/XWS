package xml.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import xml.model.Picture;
import xml.service.PictureService;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("pictures")
public class PictureController {
    @Autowired
    PictureService pictureService;

    @RequestMapping(value = "", method = RequestMethod.GET, produces = APPLICATION_JSON_VALUE)
    public List<Picture> getAll() {
        return pictureService.getAll();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = APPLICATION_JSON_VALUE)
    public List<String> getById(@PathVariable("id") Long id) {
        System.out.println("EVO GA "+id);
        return pictureService.getPictures(id);
    }

}
