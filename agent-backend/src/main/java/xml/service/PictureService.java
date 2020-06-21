package xml.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xml.model.Picture;
import xml.repository.PictureRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class PictureService {

    @Autowired
    PictureRepository pictureRepository;

    public List<String> getPictures(Long id){
        System.out.println("EVO GA i oovde "+id);
        List<Picture> pictures = pictureRepository.findAllByVehicleId(id);
        List<String> ret = new ArrayList<>();
        for(Picture p : pictures){
            ret.add(p.getUrl());
        }
        return ret;
    }

    public List<Picture> getAll() {
        return pictureRepository.findAll();
    }
}