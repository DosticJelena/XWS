package xws.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import xws.model.Picture;
import xws.model.Vehicle;
import xws.service.PictureService;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("pictures")
public class PictureController {
    @Autowired
    PictureService pictureService;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = APPLICATION_JSON_VALUE)
    public List<String> getById(@PathVariable("id") Long id) {
        System.out.println("EVO GA "+id);
        return pictureService.getPictures(id);
    }

}
