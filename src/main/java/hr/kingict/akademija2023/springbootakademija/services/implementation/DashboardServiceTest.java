package hr.kingict.akademija2023.springbootakademija.services.implementation;

import hr.kingict.akademija2023.springbootakademija.services.DashboardService;
import org.springframework.stereotype.Service;

@Service
public class DashboardServiceTest implements DashboardService {
    @Override
    public String getDashboard() {
        return "dashboardTest.html";
    }

}
