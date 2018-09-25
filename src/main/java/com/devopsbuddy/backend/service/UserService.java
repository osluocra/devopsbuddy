package com.devopsbuddy.backend.service;

import com.devopsbuddy.backend.persistence.domain.backend.Plan;
import com.devopsbuddy.backend.persistence.domain.backend.User;
import com.devopsbuddy.backend.persistence.domain.backend.UserRole;
import com.devopsbuddy.backend.persistence.repositories.PlanRepository;
import com.devopsbuddy.backend.persistence.repositories.RoleRepository;
import com.devopsbuddy.backend.persistence.repositories.UserRepository;
import com.devopsbuddy.enums.PlansEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;


@Service
@Transactional(readOnly = true)
public class UserService {

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PlanRepository planRepository;

    @Autowired
    UserRepository userRepository;

    @Transactional
    public User createUser(User user, PlansEnum plansEnum, Set<UserRole> userRoles) {
        Plan plan = new Plan(plansEnum);
        if (!planRepository.exists( plan.getId() ) ) {
            plan = planRepository.save(plan);
        }

        user.setPlan(plan);

        for(UserRole userRole : userRoles){
            roleRepository.save(userRole.getRole());

        }

        user.getUserRoles().addAll(userRoles);

        user = userRepository.save(user);

        return user;

    }


}
