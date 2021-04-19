package com.example.armour.code.interview.controller;

import com.example.armour.code.interview.constant.UserStatus;
import com.example.armour.code.interview.dto.AuthResponse;
import com.example.armour.code.interview.dto.UserLoginDTO;
import com.example.armour.code.interview.mastertenant.config.DBContextHolder;
import com.example.armour.code.interview.mastertenant.entity.MasterTenant;
import com.example.armour.code.interview.mastertenant.service.MasterTenantService;
import com.example.armour.code.interview.security.UserTenantInformation;
import com.example.armour.code.interview.util.JwtTokenUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.annotation.ApplicationScope;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**

 */
@RestController
@RequestMapping("/api/auth")
@Slf4j
public class AuthenticationController implements Serializable {


    private Map<String, String> mapValue = new HashMap<>();
    private Map<String, String> userDbMap = new HashMap<>();


    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Autowired
    MasterTenantService masterTenantService;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseEntity<?> userLogin(@RequestBody @NotNull UserLoginDTO userLoginDTO) throws AuthenticationException {
        log.info("userLogin() method call...");
        if(null == userLoginDTO.getUserName() || userLoginDTO.getUserName().isEmpty()){
            return new ResponseEntity<>("User name is required", HttpStatus.BAD_REQUEST);
        }
        //set database parameter
        MasterTenant masterTenant = masterTenantService.findByClientId(userLoginDTO.getTenantOrClientId());
        if(null == masterTenant || masterTenant.getStatus().toUpperCase().equals(UserStatus.INACTIVE)){
            throw new RuntimeException("Please contact service provider.");
        }
        //Entry Client Wise value dbName store into bean.
        loadCurrentDatabaseInstance(masterTenant.getDbName(), userLoginDTO.getUserName());
        final Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userLoginDTO.getUserName(), userLoginDTO.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        final String token = jwtTokenUtil.generateToken(userDetails.getUsername(),String.valueOf(userLoginDTO.getTenantOrClientId()));
        //Map the value into applicationScope bean
        setMetaDataAfterLogin();
        return ResponseEntity.ok(new AuthResponse(userDetails.getUsername(),token));
    }

    private void loadCurrentDatabaseInstance(String databaseName, String userName) {
        DBContextHolder.setCurrentDb(databaseName);
        mapValue.put(userName, databaseName);
    }

    @Bean(name = "userTenantInfo")
    @ApplicationScope
    public UserTenantInformation setMetaDataAfterLogin() {
        UserTenantInformation tenantInformation = new UserTenantInformation();
        if (mapValue.size() > 0) {
            for (String key : mapValue.keySet()) {
                if (null == userDbMap.get(key)) {
                    //Here Assign putAll due to all time one come.
                    userDbMap.putAll(mapValue);
                } else {
                    userDbMap.put(key, mapValue.get(key));
                }
            }
            mapValue = new HashMap<>();
        }
        tenantInformation.setMap(userDbMap);
        return tenantInformation;
    }
}
