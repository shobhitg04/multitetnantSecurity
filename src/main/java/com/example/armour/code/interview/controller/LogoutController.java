package com.example.armour.code.interview.controller;

import com.example.armour.code.interview.security.UserTenantInformation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.Serializable;
import java.security.Principal;
import java.util.Map;

/**

 */
@RestController
@RequestMapping("/api/product/logout")
@Slf4j
public class LogoutController implements Serializable {

    @Autowired
    ApplicationContext applicationContext;

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public ResponseEntity<?> logoutFromApp(Principal principal) {
        log.info("AuthenticationController::logoutFromApp() method call..");

        UserTenantInformation userCharityInfo = applicationContext.getBean(UserTenantInformation.class);
        Map<String, String> map = userCharityInfo.getMap();
        map.remove(principal.getName());
        userCharityInfo.setMap(map);
        return ResponseEntity.ok(HttpStatus.OK);
    }
}
