package org.danlee.cardealer.aspects;

import org.apache.catalina.session.StandardSessionFacade;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.danlee.cardealer.entities.User;
import org.danlee.cardealer.enums.UserRoles;
import org.danlee.cardealer.exceptions.AccessDeniedException;
import org.danlee.cardealer.repositories.UserRepository;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpSession;
import java.util.MissingFormatArgumentException;
import java.util.UUID;

@Aspect
@Component
public class UsersOnlyAspect {

    protected final UserRepository userRepository;

    public UsersOnlyAspect(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Before("@annotation(org.danlee.cardealer.annotations.UsersOnly)")
    public void filterUsers(JoinPoint joinPoint) throws Exception {

    }

    @Before("@annotation(org.danlee.cardealer.annotations.BuyersOnly)")
    public void filterBuyers(JoinPoint joinPoint) throws Exception {

    }

    private void filter(UserRoles role, JoinPoint joinPoint) {
        boolean hasHttpSessionArgument = false;

        Object[] arguments = joinPoint.getArgs();

        for (Object loopedArgument :arguments) {
            if (loopedArgument.getClass() == StandardSessionFacade.class) {
                hasHttpSessionArgument = true;
                HttpSession session = (HttpSession) loopedArgument;
                System.out.println(session.getAttribute("test"));
                UUID userId = (UUID) session.getAttribute("userId");
                if (userId == null) {
                    throw new AccessDeniedException("Only Buyers can access this page");
                }

                User possibleUser = userRepository.findBuyerById(userId);

                if (possibleUser == null) {
                    throw new AccessDeniedException("Only Buyers can access this page");
                }

                if (!possibleUser.getRole().equals(UserRoles.Buyer)) {
                    throw new AccessDeniedException("Only Buyers can access this page");
                }

                break;
            }
        }

        if (!hasHttpSessionArgument) {
            throw new MissingFormatArgumentException("Controller action must have an HttpSession argument");
        }
    }

}

