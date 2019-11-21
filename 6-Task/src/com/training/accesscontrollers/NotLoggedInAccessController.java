package com.training.accesscontrollers;

import atg.beans.PropertyNotFoundException;
import atg.servlet.DynamoHttpServletRequest;
import atg.userprofiling.AccessController;
import atg.userprofiling.Profile;

public class NotLoggedInAccessController implements AccessController {

    @Override
    public boolean allowAccess(Profile profile, DynamoHttpServletRequest dynamoHttpServletRequest) {
        boolean isLoggedIn = false;

        try {
            Integer securityStatus =  profile.getProfileTools().getSecurityStatus(profile);
            if(securityStatus < profile.getProfileTools().getPropertyManager().getSecurityStatusLogin()){
                isLoggedIn = true;
            }
        } catch (PropertyNotFoundException e) {
            e.printStackTrace();
        }
        return isLoggedIn;
    }

    @Override
    public String getDeniedAccessURL(Profile profile) {
        return "/index.jsp";
    }
}
