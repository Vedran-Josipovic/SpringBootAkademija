package hr.kingict.akademija2023.springbootakademija2023.controller;

import com.amadeus.resources.Location;
import hr.kingict.akademija2023.springbootakademija2023.dto.FlightSearchResultDto;
import hr.kingict.akademija2023.springbootakademija2023.dto.LocationDto;
import hr.kingict.akademija2023.springbootakademija2023.form.FlightSearchForm;
import hr.kingict.akademija2023.springbootakademija2023.mapper.LocationLocationDtoMapper;
import hr.kingict.akademija2023.springbootakademija2023.services.AmadeusService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping(value = "/api/flight-search")
public class FlightSearchController {

    @Autowired
    private AmadeusService amadeusService;
    @Autowired
    private LocationLocationDtoMapper locationLocationDtoMapper;

    @GetMapping(value = "/airports/{keyword}")
    public ResponseEntity<List<LocationDto>> searchAirports(@PathVariable String keyword){

        List<Location> locationList = amadeusService.searchAirports(keyword);

        List<LocationDto> locationDtoList = locationList
            .stream()
            .map(location -> locationLocationDtoMapper.map(location))
            .toList();

        return ResponseEntity
                .ok()
                .body(locationDtoList);
    }

    @PostMapping(value = "/flights")
    public ResponseEntity<List<FlightSearchResultDto>> searchFlights(@RequestBody @Valid FlightSearchForm flightSearchForm){
        List<FlightSearchResultDto> flightSearchResultDtoList =
                amadeusService.searchFlights(
                flightSearchForm.getOriginLocationCode(),
                flightSearchForm.getDestinationLocationCode(),
                flightSearchForm.getDepartureDate(),
                flightSearchForm.getReturnDate(),
                flightSearchForm.getAdults()
                );
        return ResponseEntity.ok().body(flightSearchResultDtoList);
    }

}
