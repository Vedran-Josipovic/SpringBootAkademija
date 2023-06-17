package hr.kingict.akademija2023.springbootakademija.controller;

import hr.kingict.akademija2023.springbootakademija.services.DashboardService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DashboardController {
    private DashboardService dashboardService;



    @GetMapping(value = "/dashboard")
    public String getDashboard(){
        return dashboardService.getDashboard();
    }




}
