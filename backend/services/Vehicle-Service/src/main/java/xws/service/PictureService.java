package xws.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xws.model.Picture;
import xws.repository.PictureRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class PictureService {

    @Autowired
    PictureRepository pictureRepository;

    public List<String> getPictures(Long id){
        System.out.println("EVO GA i oovde "+id);
        List<Picture> pictures = pictureRepository.findAllByVehicleId(id);
        List<String> ret = new ArrayList<String>();
        for(Picture p : pictures){
            ret.add(p.getUrl());
        }
        return ret;
    }
}
