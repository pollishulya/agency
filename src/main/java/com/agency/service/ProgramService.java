package com.agency.service;

import com.agency.dto.ProgramDto;
import com.agency.entity.Account;
import com.agency.entity.Program;
import com.agency.mapper.ProgramMapper;
import com.agency.repository.AccountRepository;
import com.agency.repository.ProgramRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Transactional
@Slf4j
@Service
public class ProgramService {

    private final ProgramRepository locationRepository;
    private final ProgramMapper locationMapper;
    private final AccountRepository accountRepository;
//    private final DescriptionRepository descriptionRepository;

    @Autowired
    public ProgramService(ProgramRepository locationRepository, ProgramMapper locationMapper, AccountRepository accountRepository
        //    , DescriptionRepository descriptionRepository
    ) {
        this.locationRepository = locationRepository;
        this.locationMapper = locationMapper;
        this.accountRepository = accountRepository;
      //  this.descriptionRepository = descriptionRepository;
    }


    public ResponseEntity save(Program location) {

        Optional<Program> locationOptional = locationRepository.findFirstByName(location.getName());
        if (!locationOptional.isPresent()) {
            Long id = getCurrentUserId();

            location.setRating(-1);
            location.setCompany(accountRepository.findById(id).get());
            location.setImage("/resources/images/imageForFood");

//            if (location.getDescription() != null) {
//                location.getDescription().forEach(td -> td.setLocation(location));
//            }

            Program savedTour = locationRepository.saveAndFlush(location);

            return new ResponseEntity(savedTour,HttpStatus.OK);
        }
        return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    public ProgramDto update(ProgramDto locationDto) {

        locationDto.setCompanyId(getCurrentUserId());
        locationDto.setRating(3);
        locationDto.setImage("/resources/images/imageForFood");

        Program location = locationRepository.saveAndFlush(locationMapper.toEntity(locationDto));
        return locationMapper.toDto(location);
    }

    public ResponseEntity getAll(Pageable pageable, String param) {

        Page<Program> locations;
        Long id = getCurrentUserId();

        if ("".equals(param)) {
            locations =locationRepository.findAllByCompanyId(id, pageable);
        } else {
            locations = locationRepository.findPrograms(param, pageable);
        }

        if (locations.getSize() != 0) {
            List<ProgramDto> locationsDto = new ArrayList<>();
            for (Program location : locations) {
                locationsDto.add(locationMapper.toDto(location));
            }

            return new ResponseEntity(locationsDto, HttpStatus.OK);
        }

        return new ResponseEntity(HttpStatus.BAD_REQUEST);
    }


    public Long getCurrentUserId() {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        Account account = accountRepository.findByEmail(email);
        Long id = account.getId();

        return id;
    }
}