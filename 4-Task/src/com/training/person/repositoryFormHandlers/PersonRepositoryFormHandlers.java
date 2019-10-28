package com.training.person.repositoryFormHandlers;

import atg.repository.MutableRepository;
import atg.repository.MutableRepositoryItem;
import atg.repository.RepositoryException;
import atg.repository.RepositoryItem;
import atg.repository.servlet.RepositoryFormHandler;
import atg.servlet.DynamoHttpServletRequest;
import atg.servlet.DynamoHttpServletResponse;
import com.sun.xml.xsom.impl.scd.Iterators;

import javax.servlet.ServletException;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class PersonRepositoryFormHandlers extends RepositoryFormHandler {
    String[] rolesList;
    String[] menotrsIdList;

    @Override
    protected void postUpdateItemProperties(MutableRepositoryItem pItem) throws ServletException, IOException {
        try {
            if (rolesList != null) {
                Set<MutableRepositoryItem> roless = new HashSet<MutableRepositoryItem>();
                for (String role : rolesList) {
                    MutableRepositoryItem roleItem = this.getRepository().getItemForUpdate(role, "role");
                    roless.add(roleItem);
                }
                pItem.setPropertyValue("roles", roless);
            }

            if (menotrsIdList != null) {
                Set<MutableRepositoryItem> mentors = new HashSet<MutableRepositoryItem>();
                for (String mentorId : menotrsIdList) {
                    MutableRepositoryItem mentorItem = this.getRepository().getItemForUpdate(mentorId, "person");
                    mentors.add(mentorItem);
                }
                pItem.setPropertyValue("mentors", mentors);
            }
        } catch (RepositoryException e) {
            e.printStackTrace();
        }
        super.postUpdateItemProperties(pItem);
    }

    public String[] getRolesList() {
        return rolesList;
    }

    public void setRolesList(String[] rolesList) {
        this.rolesList = rolesList;
    }

    public String[] getMenotrsIdList() {
        return menotrsIdList;
    }

    public void setMenotrsIdList(String[] menotrsIdList) {
        this.menotrsIdList = menotrsIdList;
    }
}
