package hr.kingict.akademija2023.springbootakademija.services.implementation;

import hr.kingict.akademija2023.springbootakademija.services.PageSuffixService;
import org.springframework.stereotype.Service;

@Service("html")
public class PageSuffixServiceHtml implements PageSuffixService {
    @Override
    public String getSuffix() {
        return "html";
    }

}
