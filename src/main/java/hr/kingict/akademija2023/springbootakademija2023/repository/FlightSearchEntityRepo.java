package hr.kingict.akademija2023.springbootakademija2023.repository;

import hr.kingict.akademija2023.springbootakademija2023.model.FlightSearchEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

//Link na h2 bazu od King ICT http://localhost:8081/demoAppTest/h2-console/login.jsp?jsessionid=b39a360baadeb8d6c26500e19e29e3fd
@Repository
public interface FlightSearchEntityRepo extends JpaRepository<FlightSearchEntity, Integer> {

}
