package com.training.userprofiling;

import atg.repository.*;
import atg.repository.rql.RqlStatement;
import atg.servlet.DynamoHttpServletRequest;
import atg.servlet.DynamoHttpServletResponse;
import atg.userprofiling.Profile;
import atg.userprofiling.ProfileTools;
import atg.userprofiling.PropertyManager;

import javax.servlet.ServletException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;


public class CustomProfileFormHandler extends atg.userprofiling.ProfileFormHandler {

    @Override
    public boolean handleLogin(DynamoHttpServletRequest pRequest, DynamoHttpServletResponse pResponse) throws ServletException, IOException {
        RepositoryItem  user = checkUserByLogin();
        if(user.getPropertyValue("blockEntryUntil") != null) {
            long millis=System.currentTimeMillis();
            Date blockDate = (Date)user.getPropertyValue("blockEntryUntil");
            Date currentDate = new Date(millis);
            if(currentDate.getTime() <= blockDate.getTime()) {
                return true;
            } else {
                boolean loginSuccess = !super.handleLogin(pRequest, pResponse);

                if(user != null) {
                    createLoginInfo(user.getRepositoryId(), loginSuccess);
                    int countOfNonOkLogins = 0;
                    try {
                        countOfNonOkLogins = checkLimitOfNonOkLogins(user.getRepositoryId());
                    } catch (RepositoryException e) {
                        e.printStackTrace();
                    }
                    if(!loginSuccess && countOfNonOkLogins > 0) {
                        if(countOfNonOkLogins >= 3) {
                            try {
                                logInfo("CustomProfileFormHandler: handleLogin - blockEntryUntil");
                                long millis1=System.currentTimeMillis();
                                MutableRepositoryItem mRepositoryItem = this.getProfileTools().getProfileRepository().getItemForUpdate(user.getRepositoryId(),"blockTime");
                                mRepositoryItem.setPropertyValue("blockEntryUntil", new java.util.Date(millis1 + TimeUnit.MINUTES.toMillis(5)));
                                this.getProfileTools().getProfileRepository().addItem(mRepositoryItem);
                            } catch (RepositoryException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                    logInfo("CustomProfileFormHandler: user1." );
                } else {
                    logInfo("CustomProfileFormHandler: not user1." );
                }

                return !loginSuccess;
            }

        } else {
            boolean loginSuccess = !super.handleLogin(pRequest, pResponse);

            if(user != null) {
                createLoginInfo(user.getRepositoryId(), loginSuccess);
                int countOfNonOkLogins = 0;
                try {
                    countOfNonOkLogins = checkLimitOfNonOkLogins(user.getRepositoryId());
                } catch (RepositoryException e) {
                    e.printStackTrace();
                }
                if(!loginSuccess && countOfNonOkLogins > 0) {
                    if(countOfNonOkLogins >= 3) {
                        try {
                            logInfo("CustomProfileFormHandler: handleLogin - blockEntryUntil");
                            long millis1=System.currentTimeMillis();
                            MutableRepositoryItem mRepositoryItem = this.getProfileTools().getProfileRepository().getItemForUpdate(user.getRepositoryId(),"blockTime");
                            mRepositoryItem.setPropertyValue("blockEntryUntil", new java.util.Date(millis1 + TimeUnit.MINUTES.toMillis(5)));
                            this.getProfileTools().getProfileRepository().addItem(mRepositoryItem);
                        } catch (RepositoryException e) {
                            e.printStackTrace();
                        }
                    }
                }
                logInfo("CustomProfileFormHandler: user1." );
            } else {
                logInfo("CustomProfileFormHandler: not user1." );
            }

            return !loginSuccess;
        }

    }

    private int checkLimitOfNonOkLogins(String  userId) throws RepositoryException {
        Repository rep = this.getProfileTools().getProfileRepository();
        RepositoryView view = rep.getView("action");
        long millis=System.currentTimeMillis();
        java.util.Date date=new java.util.Date(millis - TimeUnit.MINUTES.toMillis(5));
        Object params[] = new Object[3];
        params[0] = userId;
        params[1] = false;
        params[2] = date;
        RqlStatement statement = RqlStatement.parseRqlStatement("userId = ?0 and isOk = ?1 and time >= ?2");
        RepositoryItem[] items = statement.executeQuery(view,params);
        if(items!=null) {
            logInfo("CustomProfileFormHandler: non ok logins per 5 minut: " + items.length);
            return items.length;
        }
        return 0;
    }

    private RepositoryItem createLoginInfo(String userId, boolean loginIsOk) {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        RepositoryItem loginInfo = null;
        long millis=System.currentTimeMillis();
        java.util.Date date=new java.util.Date(millis);
        try {
            MutableRepositoryItem mRepositoryItem = this.getProfileTools().getProfileRepository().createItem("action");
            mRepositoryItem.setPropertyValue("isOk", loginIsOk);
            mRepositoryItem.setPropertyValue("userId", userId);
            mRepositoryItem.setPropertyValue("time", date);

            loginInfo = this.getProfileTools().getProfileRepository().addItem(mRepositoryItem);
        } catch (RepositoryException e) {
            e.printStackTrace();
        }
        return loginInfo;
    }

    public RepositoryItem checkUserByLogin() {
        ProfileTools ptools = this.getProfileTools();
        PropertyManager pmgr = ptools.getPropertyManager();
        String loginPropertyName = pmgr.getLoginPropertyName();
        String login = this.getStringValueProperty(loginPropertyName);
        logInfo("TEEEEEEEEEEEEEEEST: " + login);
        RepositoryItem item = ptools.getItem(login, (String)null, this.getLoginProfileType());
        return item;
    }
}
