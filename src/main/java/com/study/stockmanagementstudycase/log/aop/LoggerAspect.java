package com.study.stockmanagementstudycase.log.aop;

import com.study.stockmanagementstudycase.log.entity.LogEntity;
import com.study.stockmanagementstudycase.log.service.LogService;
import com.study.stockmanagementstudycase.security.Identity;
import com.study.stockmanagementstudycase.security.model.mappers.UserMapper;
import com.study.stockmanagementstudycase.security.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.Objects;

@Aspect
@Component
@RequiredArgsConstructor
public class LoggerAspect {

    private final LogService logService;
    private final UserService userService;
    private final Identity identity;

    /**
     * Pointcut for all methods within the Rest Controller annotation.
     */
    @Pointcut("within(@org.springframework.web.bind.annotation.RestController *)")
    public void restControllerPointcut() {

    }

    /**
     * A method that will work after each successful controller method.
     * @param joinPoint JoinPoint.
     * @param result Result of the endpoint.
     */
    @AfterReturning(pointcut = "restControllerPointcut()", returning = "result")
    public void afterReturning(JoinPoint joinPoint, Object result) {

        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();

        if (!Objects.isNull(requestAttributes)) {

            HttpServletRequest request = requestAttributes.getRequest();

            ResponseEntity<?> response = (ResponseEntity<?>) result;

            identity.getEmail();
            LogEntity logEntity = LogEntity.builder()
                    .path(request.getRequestURI())
                    .httpMethod(request.getMethod())
                    .status(response.getStatusCode().value())
                    .user(UserMapper.toEntity(userService.getUserByEmail(identity.getEmail())))
                    .ip(request.getRemoteAddr())
                    .build();

            logService.saveLogToDatabase(logEntity);
        }

    }
}
