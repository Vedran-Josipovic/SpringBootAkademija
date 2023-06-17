package hr.kingict.akademija2023.springbootakademija2023.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class PageSuffixServiceAspect {
    Logger logger = LoggerFactory.getLogger(DashboardServiceAspect.class);

    @Before("execution(* hr.kingict.akademija2023.springbootakademija2023.services.PageSuffixService.getSuffix(..))")
    public void beforePageServiceGetGetSuffix(JoinPoint joinPoint){
        logger.error("Before Page Suffix Service service get suffix: " + joinPoint.getTarget().getClass().getSimpleName());
    }


    @AfterReturning(value = "execution(* hr.kingict.akademija2023.springbootakademija2023.services.PageSuffixService.getSuffix(..))",returning = "result")
    public void afterPageServiceGetGetSuffix(String result){
        logger.error("After Page Suffix Service service get suffix: " + result);
    }

}
