package hr.kingict.akademija2023.springbootakademija.services.implementation;

import hr.kingict.akademija2023.springbootakademija.services.DashboardService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@Profile("!Test & !Prod")
public class DashboardServiceDefault implements DashboardService {
    @Override
    public String getDashboard() {
        return "dashboard";
    }
}
