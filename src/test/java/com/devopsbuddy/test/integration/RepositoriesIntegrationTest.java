package com.devopsbuddy.test.integration;

import com.devopsbuddy.DevopsbuddyApplication;
import com.devopsbuddy.backend.persistence.domain.backend.Plan;
import com.devopsbuddy.backend.persistence.domain.backend.Role;
import com.devopsbuddy.backend.persistence.domain.backend.User;
import com.devopsbuddy.backend.persistence.domain.backend.UserRole;
import com.devopsbuddy.backend.persistence.repositories.PlanRepository;
import com.devopsbuddy.backend.persistence.repositories.RoleRepository;
import com.devopsbuddy.backend.persistence.repositories.UserRepository;
import com.devopsbuddy.enums.PlansEnum;
import com.devopsbuddy.enums.RolesEnum;
import com.devopsbuddy.utils.UsersUtils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.swing.text.html.Option;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = DevopsbuddyApplication.class)

public class RepositoriesIntegrationTest {

    //private static final int BASIC_PLAN_ID = 1;
    //private static final int BASIC_ROLE_ID = 1;


    @Autowired
    private PlanRepository planRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserRepository userRepository;

    @Before
    public void init(){
        Assert.assertNotNull(planRepository);
        Assert.assertNotNull(userRepository);
        Assert.assertNotNull(roleRepository);


    }


    @Test
    public void testCreateNewRole() throws Exception{
        Role userRole =  createNewRole(RolesEnum.BASIC);
        roleRepository.save(userRole);

        Role retrieveRole = roleRepository.findOne(RolesEnum.BASIC.getId());
        Assert.assertNotNull(retrieveRole);
    }

    @Test
    public void testCreateNewPlan() throws Exception{
        Plan basicPlan = createNewPlan(PlansEnum.BASIC);
        planRepository.save(basicPlan);

       Plan retrievePlan = planRepository.findOne(PlansEnum.BASIC.getId());

        Assert.assertNotNull(retrievePlan);

    }


    private Plan createNewPlan(PlansEnum plansEnum){
        return  new Plan(PlansEnum.BASIC);

    }

    private Role createNewRole(RolesEnum rolesEnum){
        return new Role(RolesEnum.BASIC);

    }



    @Test
    public void createNewUser() throws Exception{
        Plan basicPlan = createNewPlan(PlansEnum.BASIC);
        planRepository.save(basicPlan);

        User basicUser = UsersUtils.createBasicUser();
        basicUser.setPlan(basicPlan);

        Role basicRole = createNewRole(RolesEnum.BASIC);
        Set<UserRole> userRoles = new HashSet<>();

        UserRole userRole = new UserRole(basicUser, basicRole);
        userRoles.add(userRole);

        basicUser.getUserRoles().addAll(userRoles);

        for (UserRole ur : userRoles) {
            roleRepository.save(ur.getRole());
        }

        basicUser = userRepository.save(basicUser);

        User newlyCreatedUser = userRepository.findOne(basicUser.getId());

        Assert.assertNotNull(newlyCreatedUser);
        Assert.assertTrue(newlyCreatedUser.getId()!=0);
        Assert.assertNotNull(newlyCreatedUser.getPlan());
        Assert.assertNotNull(newlyCreatedUser.getPlan().getId());
        Set<UserRole> newlyCreatedUserRoles = newlyCreatedUser.getUserRoles();

        for (UserRole ur:newlyCreatedUserRoles){
            Assert.assertNotNull(ur.getRole());
            Assert.assertNotNull(ur.getRole().getId());
        }




    }


}
