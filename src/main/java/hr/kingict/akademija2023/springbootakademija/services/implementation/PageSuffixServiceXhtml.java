package hr.kingict.akademija2023.springbootakademija.services.implementation;

import hr.kingict.akademija2023.springbootakademija.services.PageSuffixService;
import org.springframework.stereotype.Service;

@Service("xhtml")
public class PageSuffixServiceXhtml implements PageSuffixService {
    @Override
    public String getSuffix() {
        return "xhtml";
    }
}
