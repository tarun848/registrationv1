package com.api.service;


import com.api.contoller.RegistrationController;
import com.api.entity.Registration;
import com.api.exception.ResourceNotFoundException;
import com.api.payload.RegistrationDto;
import com.api.repository.RegistrationRepository;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RegistrationService {
    private RegistrationRepository registrationRepository;
    private ModelMapper modelMapper;

    public RegistrationService(RegistrationRepository registrationRepository, ModelMapper modelMapper) {
        this.registrationRepository = registrationRepository;

        this.modelMapper = modelMapper;
    }

    public List<RegistrationDto> getRegistrations() {

        List<Registration> registrations = registrationRepository.findAll();
        List<RegistrationDto> dtos = registrations.stream().map(r->mapToDto(r)).collect(Collectors.toList());
        return dtos;
    }

//first method for creation
//    public Registration createRegistration(Registration registration) {
//
//        Registration savedEntity = registrationRepository.save(registration);
//
//           return savedEntity;
//    }

    public void deleteRegistration(Long id) {

        registrationRepository.deleteById(id);
    }


    public Registration updateRegistration(long id, Registration registration) {
        Registration r = registrationRepository.findById(id).get();
        r.setName(registration.getName());
        r.setEmail(registration.getEmail());
        r.setMobile(registration.getMobile());

        Registration savedEntity = registrationRepository.save(r);
        return savedEntity;
    }

//second method for creation(entity to dto )
//    public RegistrationDto createRegistration( Registration registration){
//
//        Registration reg  = registrationRepository.save(registration);
//
//        RegistrationDto regDto = new RegistrationDto();
//
//        regDto.setName(reg.getName());
//        regDto.setEmail(reg.getEmail());
//        regDto.setMobile(reg.getMobile());
//
//        return regDto;
// }


    //Third method for creation (dto to entity then entity to dto)
    public RegistrationDto createRegistration(RegistrationDto registrationDto) {

        Registration registration = mapToEntity(registrationDto);
        Registration savedEntity = registrationRepository.save(registration);
        RegistrationDto dto = mapToDto(savedEntity);
        return dto;
    }

    Registration mapToEntity(RegistrationDto registrationDto) {
       Registration registration = modelMapper.map(registrationDto , Registration.class);
//        Registration registration = new Registration();
//
//        registration.setName(registrationDto.getName());
//        registration.setEmail(registrationDto.getEmail());
//        registration.setMobile(registrationDto.getMobile());
        return registration;
    }

    RegistrationDto mapToDto(Registration registration) {
          RegistrationDto regDto= modelMapper.map(registration ,RegistrationDto.class);
        //        RegistrationDto regDto = new RegistrationDto();
//
//        regDto.setName(reg.getName());
//        regDto.setEmail(reg.getEmail());
//        regDto.setMobile(reg.getMobile());

        return regDto;
    }

    public RegistrationDto getRegistrationById(long id) {
        Registration registration = registrationRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Record not Found"));
        return mapToDto(registration);
    }
}








//    Registration mapToEntity(RegistrationDto registrationDto) {
//
//        Registration registration = modelMapper.map(registrationDto, Registration.class);
//        return registration;
//    }
//
//    RegistrationDto mapToDto(Registration registraion) {
//        RegistrationDto dto = modelMapper.map(registraion, RegistrationDto.class);
//        return dto;
//    }
//}