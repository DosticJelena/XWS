package xws.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xws.dto.request.DiscountDTO;
import xws.model.Discount;
import xws.model.Vehicle;
import xws.repository.DiscountRepository;

import java.util.List;

@Service
public class DiscountService {
    @Autowired
    private DiscountRepository discountRepository;

    @Autowired
    private VehicleService vehicleService;

    public List<Discount> findAll() {return discountRepository.findAll();}
    public Discount findOne(Long id) {return discountRepository.findOneById(id);}
    public Vehicle addDiscountToVehicle(Long vehicleId,Long discountId) {
        Discount discount = discountRepository.findOneById(discountId);
        Vehicle v = vehicleService.getById(vehicleId);
        v.setDiscount(discount);
        return vehicleService.save(v);
    }
    public Discount addNewDiscount(DiscountDTO request) {
        Discount d = new Discount();
        d.setAmount(request.getAmount());
        return discountRepository.save(d);
    }

}
