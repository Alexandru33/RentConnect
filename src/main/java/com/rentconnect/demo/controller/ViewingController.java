package com.rentconnect.demo.controller;

import com.rentconnect.demo.dto.ViewingDTO;
import com.rentconnect.demo.exception.PropertyIdNotFoundException;
import com.rentconnect.demo.exception.ViewingNotFoundException;
import com.rentconnect.demo.model.Viewing;
import com.rentconnect.demo.service.ViewingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/viewing")
public class ViewingController {

    private final ViewingService viewingService;

    @GetMapping("/personal_viewing")
    public ResponseEntity<List<ViewingDTO>> getPersonalViewings(
            @RequestHeader (name = "Authorization") String token
    )
    {
        List<Viewing> viewingList = viewingService.personalViewings(token.substring(7));
        List<ViewingDTO> viewingDTOList = new ArrayList<>();

        for (Viewing v: viewingList
             ) {
            viewingDTOList.add(viewingService.toViewingDTO(v));

        }
        return ResponseEntity.ok(viewingDTOList);

    }

    @PostMapping("/viewing_add/{property_id}")
    public ResponseEntity<String> addViewing(
            @RequestHeader (name = "Authorization") String token,
            @RequestBody ViewingDTO viewingDTO,
            @PathVariable Integer property_id

    )
    {

            System.out.println("in controller");

        try{
            return ResponseEntity.ok(viewingService.addViewing(viewingDTO , property_id, token.substring(7)));
        }
        catch(PropertyIdNotFoundException e)
        {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Property not found");

        }

    }

    @DeleteMapping("/delete_viewing")
    public ResponseEntity<String> deleteViewing(
            @RequestHeader (name = "Authorization") String token,
            @RequestBody Integer viewingId
    )
    {
        try
        {
            String mesaj = viewingService.deleteViewing(viewingId, token.substring(7));
            return ResponseEntity.ok(mesaj);

        } catch ( ViewingNotFoundException e)
        {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Viewing was NOT deleted");
        }

    }

    @PutMapping("/update_viewing/{old_id}")
    public ResponseEntity<ViewingDTO> updateViewing(
            @RequestHeader (name = "Authorization") String token,
            @RequestBody ViewingDTO viewingDTO,
            @PathVariable Integer old_id
    )
    {
        try
        {
            viewingService.updateViewing(viewingService.fromViewingDTO(viewingDTO) , old_id, token.substring(7));
            return ResponseEntity.ok(viewingDTO);
        }
        catch( Exception e)
        {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);

        }

    }

}
