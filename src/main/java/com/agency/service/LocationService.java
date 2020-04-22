package com.agency.service;

import com.agency.dto.LocationDto;
import com.agency.entity.Account;
import com.agency.entity.Location;
import com.agency.mapper.LocationMapper;
import com.agency.repository.AccountRepository;
import com.agency.repository.DescriptionRepository;
import com.agency.repository.LocationRepository;
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
public class LocationService {

    private final LocationRepository locationRepository;
    private final LocationMapper locationMapper;
    private final AccountRepository accountRepository;
    private final DescriptionRepository descriptionRepository;

    @Autowired
    public LocationService(LocationRepository locationRepository, LocationMapper locationMapper, AccountRepository accountRepository, DescriptionRepository descriptionRepository) {
        this.locationRepository = locationRepository;
        this.locationMapper = locationMapper;
        this.accountRepository = accountRepository;
        this.descriptionRepository = descriptionRepository;
    }


    public ResponseEntity save(Location location) {

        Optional<Location> locationOptional = locationRepository.findFirstByName(location.getName());
        if (!locationOptional.isPresent()) {
            Long id = getCurrentUserId();

            location.setRating(-1);
            location.setCompany(accountRepository.findById(id).get());
            location.setImage("/resources/images/imageForFood");

//            if (location.getDescription() != null) {
//                location.getDescription().forEach(td -> td.setLocation(location));
//            }

            Location savedTour = locationRepository.saveAndFlush(location);

            return new ResponseEntity(savedTour,HttpStatus.OK);
        }
        return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    public LocationDto update(LocationDto locationDto) {

        locationDto.setCompanyId(getCurrentUserId());
        locationDto.setRating(3);
        locationDto.setImage("/resources/images/imageForFood");

        Location location = locationRepository.saveAndFlush(locationMapper.toEntity(locationDto));
        return locationMapper.toDto(location);
    }

    public ResponseEntity getAll(Pageable pageable, String param) {

        Page<Location> locations;
        Long id = getCurrentUserId();

        if ("".equals(param)) {
            locations =locationRepository.findAllByCompanyId(id, pageable);
        } else {
            locations = locationRepository.findLocations(param, pageable);
        }

        if (locations.getSize() != 0) {
            List<LocationDto> locationsDto = new ArrayList<>();
            for (Location location : locations) {
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