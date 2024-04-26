package com.rentconnect.demo.service;

import com.rentconnect.demo.config.JwtService;
import com.rentconnect.demo.dto.ViewingDTO;
import com.rentconnect.demo.exception.PropertyIdNotFoundException;
import com.rentconnect.demo.exception.ViewingNotFoundException;
import com.rentconnect.demo.model.Property;
import com.rentconnect.demo.model.User;
import com.rentconnect.demo.model.Viewing;
import com.rentconnect.demo.repository.PropertyRepository;
import com.rentconnect.demo.repository.UserRepository;
import com.rentconnect.demo.repository.ViewingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ViewingService {

    @Autowired
    ViewingRepository viewingRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    PropertyRepository propertyRepository;

    @Autowired
    JwtService jwtService;

    public Viewing fromViewingDTO (ViewingDTO viewingDTO)
    {
        Viewing viewing = new Viewing();
        viewing.setDate(viewingDTO.getDate());
        return viewing;
    }
    public ViewingDTO toViewingDTO ( Viewing viewing)
    {
        ViewingDTO viewingDTO = new ViewingDTO();
        viewingDTO.setDate(viewing.getDate());
        return viewingDTO;

    }

    public List<Viewing> personalViewings(String token) {
        String email = jwtService.extractUsername(token);
        User user = userRepository.findByEmail(email).orElseThrow();

        return viewingRepository.personalViewings(user.getId());
    }

    public String addViewing(ViewingDTO viewingDTO, Integer propertyId, String token) throws PropertyIdNotFoundException {

        User user = userRepository.findByEmail(jwtService.extractUsername(token)).orElseThrow();
        System.out.println("in service " + propertyId + " " + user.getId());
        Property property = propertyRepository.findById(propertyId).orElseThrow(PropertyIdNotFoundException::new);
        Viewing viewing = fromViewingDTO(viewingDTO);
        viewing.setUser(user);
        viewing.setProperty(property);

        viewingRepository.save(viewing);

        return "Viewing pentru data de " + viewing.getDate() + "adaugat cu succes";


    }

    public String deleteViewing(Integer viewingId, String substring) throws ViewingNotFoundException {
        Viewing viewing  = viewingRepository.findById(viewingId).orElseThrow(ViewingNotFoundException::new);

        viewingRepository.delete(viewing);
        return "Viewing with id " + viewing.getId() + " succesfully deleted!";



    }

    public void updateViewing(Viewing viewing, Integer old_id, String token) {

        String email = jwtService.extractUsername(token);
        User user = userRepository.findByEmail(email).orElseThrow();
        Viewing oldViewing = viewingRepository.findById(old_id).orElseThrow();

        viewing.setUser(user);
        viewing.setProperty(oldViewing.getProperty());
        viewing.setId(oldViewing.getId());

        viewingRepository.save(viewing);


    }
}
