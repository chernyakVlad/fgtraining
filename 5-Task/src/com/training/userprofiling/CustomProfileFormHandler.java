package com.training.userprofiling;

import atg.droplet.DropletException;
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
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.TimeUnit;


public class CustomProfileFormHandler extends atg.userprofiling.ProfileFormHandler {
    public static  final int LOCK_TIME_IN_MINUTES  = 5;
    String role;

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public boolean handleLogin(DynamoHttpServletRequest pRequest, DynamoHttpServletResponse pResponse) throws ServletException, IOException {
        RepositoryItem  user = findUserByLogin();

        if(user != null) {
            if(checkUserLoginLock(user)) {
                return true;
            } else {
                boolean loginSuccess = !super.handleLogin(pRequest, pResponse);
                createLoginInfo(user.getRepositoryId(), loginSuccess);
                int countOfNonOkLogins = 0;
                try {
                    countOfNonOkLogins = checkLimitOfNonOkLogins(user.getRepositoryId());
                } catch (RepositoryException e) {
                    e.printStackTrace();
                }
                if(!loginSuccess && countOfNonOkLogins > 0) {
                    if(countOfNonOkLogins >= 3) {
                        addLoginLock(user.getRepositoryId(), LOCK_TIME_IN_MINUTES);
                    }
                }
                return !loginSuccess;
            }
        }

        return true;

    }

    @Override
    protected void postCreateUser(DynamoHttpServletRequest pRequest, DynamoHttpServletResponse pResponse) throws ServletException, IOException {
        try {
            if (role != null) {
                Set<MutableRepositoryItem> roless = new HashSet<MutableRepositoryItem>();

                MutableRepositoryItem roleItem = this.getProfile().getProfileTools().getProfileRepository().getItemForUpdate(role, "role");
                roless.add(roleItem);
                MutableRepository repository = this.getProfileTools().getProfileRepository();
                MutableRepositoryItem mutableItem = repository.getItemForUpdate(this.getRepositoryId(), this.getCreateProfileType());
                mutableItem.setPropertyValue("roles", roless);
                repository.updateItem(mutableItem);
            }

        } catch (RepositoryException e) {
            e.printStackTrace();
        }
        super.postCreateUser(pRequest, pResponse);
    }

    private void addLoginLock(String userId, int timeInMinutes) {
        MutableRepositoryItem mRepositoryItem = null;
        long millis1=System.currentTimeMillis();
        try {
            mRepositoryItem = this.getProfileTools().getProfileRepository().getItemForUpdate(userId,"user");
            if(mRepositoryItem != null) {
                mRepositoryItem.setPropertyValue("blockEntryUntil", new java.util.Date(millis1 + TimeUnit.MINUTES.toMillis(timeInMinutes)));
                this.getProfileTools().getProfileRepository().updateItem(mRepositoryItem);
            }
        } catch (RepositoryException e) {
            e.printStackTrace();
        }
    }

    private int checkLimitOfNonOkLogins(String  userId) throws RepositoryException {
        Repository rep = this.getProfileTools().getProfileRepository();
        RepositoryView view = rep.getView("action");
        long millis=System.currentTimeMillis();
        java.util.Date date=new java.util.Date(millis - TimeUnit.MINUTES.toMillis(5));
        Object[] params = new Object[3];
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

    private boolean checkUserLoginLock(RepositoryItem user) {
        if(user.getPropertyValue("blockEntryUntil") != null) {
            long currentTime = System.currentTimeMillis();
            long lockedUntil = ((Date)user.getPropertyValue("blockEntryUntil")).getTime();
            if(currentTime <= lockedUntil) {
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss ");
                this.addFormException(new DropletException("Login blocked until " + format.format(lockedUntil)));
                return true;
            }
        }

        return false;
    }

    private RepositoryItem createLoginInfo(String userId, boolean loginIsOk) {
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

    private RepositoryItem findUserByLogin() {
        ProfileTools ptools = this.getProfileTools();
        PropertyManager pmgr = ptools.getPropertyManager();
        String loginPropertyName = pmgr.getLoginPropertyName();
        String login = this.getStringValueProperty(loginPropertyName);
        RepositoryItem item = ptools.getItem(login, (String)null, this.getLoginProfileType());
        return item;
    }
}
