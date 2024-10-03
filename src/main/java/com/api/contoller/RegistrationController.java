package com.api.contoller;

import com.api.entity.Registration;
import com.api.payload.RegistrationDto;
import com.api.service.RegistrationService;
import jakarta.persistence.Id;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/registration")
public class RegistrationController {
    private RegistrationService registrationService;

    public RegistrationController(RegistrationService registrationService) {
        this.registrationService = registrationService;
    }

    @GetMapping
    public ResponseEntity<List<RegistrationDto>> getAllRegistrations() {
        List<RegistrationDto> dtos = registrationService.getRegistrations();
        return new ResponseEntity(dtos, HttpStatus.OK);

    }

//    @PostMapping
//    public ResponseEntity<Registration> createRegistration(@RequestBody Registration registration) {
//
//        Registration reg = registrationService.createRegistration(registration);
//
//        return new ResponseEntity<>(reg, HttpStatus.CREATED);
//    }


    @DeleteMapping
    public ResponseEntity<String> deleteRegistration(@RequestParam long id) {

        registrationService.deleteRegistration(id);
        return new ResponseEntity<>("Deleted", HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Registration> updateRegistration(@PathVariable long id, @RequestBody Registration registration) {

       Registration updateReg= registrationService.updateRegistration(id , registration);
       return new ResponseEntity<>(updateReg,HttpStatus.OK);
    }
//     @PostMapping
//    public ResponseEntity<RegistrationDto> createRegistration(@RequestBody Registration registration){
//
//        RegistrationDto regDto = registrationService.createRegistration(registration);
//
//        return new ResponseEntity(regDto, HttpStatus.CREATED) ;
//     }

    @PostMapping
    public ResponseEntity<?> createRegistration(@Valid @RequestBody RegistrationDto registrationDto, BindingResult result){
            if (result.hasErrors()){
                return new ResponseEntity<>(result.getFieldError().getDefaultMessage(), HttpStatus.CREATED) ;
            }

        RegistrationDto regDto = registrationService.createRegistration(registrationDto);

        return new ResponseEntity<>(regDto, HttpStatus.CREATED) ;
    }
    @GetMapping("/{id}")
    public ResponseEntity getRegistationById(@PathVariable long id){
            RegistrationDto dto = registrationService.getRegistrationById(id);
            return new ResponseEntity<>(dto , HttpStatus.OK);
    }


}
