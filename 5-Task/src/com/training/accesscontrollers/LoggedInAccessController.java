package com.training.accesscontrollers;

import atg.beans.PropertyNotFoundException;
import atg.servlet.DynamoHttpServletRequest;
import atg.userprofiling.AccessController;
import atg.userprofiling.Profile;

public class LoggedInAccessController implements AccessController {

    @Override
    public boolean allowAccess(Profile profile, DynamoHttpServletRequest dynamoHttpServletRequest) {
        boolean isLoggedIn = false;

        try {
            Integer securityStatus =  profile.getProfileTools().getSecurityStatus(profile);
            System.out.println("Security status - " + securityStatus);
            if(securityStatus >= profile.getProfileTools().getPropertyManager().getSecurityStatusLogin()){
                isLoggedIn = true;
            }
        } catch (PropertyNotFoundException e) {
            e.printStackTrace();
        }
        return isLoggedIn;
    }

    @Override
    public String getDeniedAccessURL(Profile profile) {
        return "/person/login.jsp";
    }
}
