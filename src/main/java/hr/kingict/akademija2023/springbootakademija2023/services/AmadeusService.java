package hr.kingict.akademija2023.springbootakademija2023.services;

import com.amadeus.Amadeus;
import com.amadeus.Params;
import com.amadeus.exceptions.ResponseException;
import com.amadeus.referencedata.Locations;
import com.amadeus.resources.FlightOfferSearch;
import com.amadeus.resources.Location;
import hr.kingict.akademija2023.springbootakademija2023.dto.FlightSearchResultDto;
import hr.kingict.akademija2023.springbootakademija2023.mapper.FlightOfferSearchFlightSearchResultDtoMapper;
import hr.kingict.akademija2023.springbootakademija2023.mapper.FlightSearchResultDtoFlightSearchResultEntityMapper;
import hr.kingict.akademija2023.springbootakademija2023.mapper.FlightSearchResultEntityFlightSearchResultDtoMapper;
import hr.kingict.akademija2023.springbootakademija2023.model.FlightSearchEntity;
import hr.kingict.akademija2023.springbootakademija2023.model.FlightSearchResultEntity;
import hr.kingict.akademija2023.springbootakademija2023.repository.FlightSearchEntityRepo;
import hr.kingict.akademija2023.springbootakademija2023.repository.FlightSearchResultEntityRepo;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;



//Link na github voditelja akademije --> https://github.com/ivan12andric/SpringBootAkademija2023

@Service
public class AmadeusService {
    Logger logger = LoggerFactory.getLogger(AmadeusService.class);

    @Autowired
    private FlightSearchResultEntityFlightSearchResultDtoMapper flightSearchResultEntityFlightSearchResultDtoMapper;

    @Autowired
    private FlightOfferSearchFlightSearchResultDtoMapper flightSearchResultDtoMapper;
    @Autowired
    private Amadeus amadeus;

    @Autowired
    private FlightSearchEntityRepo flightSearchEntityRepo;


    @Autowired
    private FlightSearchResultEntityRepo flightSearchResultEntityRepo;

    @Autowired
    private FlightSearchResultDtoFlightSearchResultEntityMapper flightSearchResultDtoFlightSearchResultEntityMapper;

    public List<Location> searchAirports(String keyword) {
        try {
            Params param = Params
                    .with("subType", Locations.AIRPORT)
                    .and("keyword", keyword);
            return Arrays.asList(
                    amadeus.referenceData.locations.get(param)
            );
        } catch (Exception e) {
            logger.error("Search airports error: ", e);
            return Collections.emptyList();
        }
    }

    @Transactional
    public List<FlightSearchResultDto> searchFlights(String originLocationCode, String destinationLocationCode,
                                                     LocalDate departureDate, LocalDate returnDate, Integer adults) {
        try {
            //Alternativna opcija preko querya --> https://www.baeldung.com/the-persistence-layer-with-spring-data-jpa
            FlightSearchEntity existingFlightSearch = flightSearchEntityRepo.findOneByOriginLocationCodeAndDestinationLocationCodeAndDepartureDateAndReturnDateAndAdults(
                    originLocationCode,destinationLocationCode,departureDate,returnDate, adults
            );
            if(existingFlightSearch != null){
                List<FlightSearchResultEntity> flightSearchResultEntityList = existingFlightSearch.getFlightSearchResultEntityList();

                logger.info("Dohvatio podatke iz baze");

                return flightSearchResultEntityList.stream()
                        .map(flightSearchResultEntity -> flightSearchResultEntityFlightSearchResultDtoMapper.map(flightSearchResultEntity))
                        .toList();
            }


            FlightSearchEntity flightSearchEntity = new FlightSearchEntity();
            flightSearchEntity.setOriginLocationCode(originLocationCode);
            flightSearchEntity.setDestinationLocationCode(destinationLocationCode);
            flightSearchEntity.setDepartureDate(departureDate);
            flightSearchEntity.setReturnDate(returnDate);
            flightSearchEntity.setAdults(adults);

            flightSearchEntity.setDateCreated(LocalDate.now());
            flightSearchEntity.setUserCreated("Vedran");

            flightSearchEntityRepo.save(flightSearchEntity);

            Params params = Params
                    .with("originLocationCode", originLocationCode)
                    .and("destinationLocationCode", destinationLocationCode)
                    .and("departureDate", departureDate.toString())
                    .and("adults", adults)
                    .and("nonStop", true)
                    .and("max", 5);

            if (returnDate != null) {
                params.and("returnDate", returnDate.toString());
            }

            List<FlightOfferSearch> flightOfferSearchList = Arrays.asList
                    (amadeus.shopping.flightOffersSearch.get(params));

            List<FlightSearchResultDto> flightSearchResultDtoList =
                    flightOfferSearchList
                            .stream()
                            .map(flightOfferSearch -> flightSearchResultDtoMapper.map(flightOfferSearch))
                            .toList();

            flightSearchResultDtoList
                    .stream()
                    .map(flightSearchResultDto -> flightSearchResultDtoFlightSearchResultEntityMapper.map(flightSearchResultDto))
                    .forEach(flightSearchResultEntity ->
                            {
                                flightSearchResultEntity.setFlightSearchEntity(flightSearchEntity);
                                flightSearchResultEntityRepo.save(flightSearchResultEntity);
                            });


            logger.warn("Dohvatio podatke iz Amadeusa, to će nas koštati.");
            return flightSearchResultDtoList;
        } catch (Exception e) {
            logger.error("Search flight error: ", e);
            return Collections.emptyList();
        }

    }


}
