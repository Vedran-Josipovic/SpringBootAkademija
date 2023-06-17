package hr.kingict.akademija2023.springbootakademija.controller;

import hr.kingict.akademija2023.springbootakademija.services.DashboardService;
import hr.kingict.akademija2023.springbootakademija.services.PageSuffixService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import util.FilenameUtils;

@Controller
public class DashboardController {
    @Autowired
    private DashboardService dashboardService;

    @Autowired
    @Qualifier("html")
    private PageSuffixService pageSuffixService;

    @Autowired
    private FilenameUtils filenameUtils;

    @GetMapping(value = "/dashboard")
    public String getDashboard(){
        return dashboardService.getDashboard() + filenameUtils.getSuffixSeparator() + pageSuffixService.getSuffix();
    }




}
