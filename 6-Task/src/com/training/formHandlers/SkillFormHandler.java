package com.training.formHandlers;

import atg.common.access.Transaction;
import atg.droplet.DropletException;
import atg.droplet.GenericFormHandler;
import atg.dtm.TransactionDemarcation;
import atg.dtm.TransactionDemarcationException;
import atg.repository.*;
import atg.servlet.DynamoHttpServletRequest;
import atg.servlet.DynamoHttpServletResponse;

import javax.servlet.ServletException;
import javax.transaction.SystemException;
import javax.transaction.TransactionManager;
import java.io.IOException;
import java.util.ResourceBundle;
import java.util.Set;

public class SkillFormHandler extends GenericFormHandler {
    private String personIdsString;
    private String addedSkillId;
    private MutableRepository repository = null;
    private String transactionDemacrationMode;

    public void handleAddSkill(DynamoHttpServletRequest req, DynamoHttpServletResponse res) throws ServletException, IOException {
        long startTime = System.currentTimeMillis();
        TransactionManager tm = this.getTransactionManager();
        TransactionDemarcation td = new TransactionDemarcation();
        try{
            td.begin(tm, Integer.parseInt(transactionDemacrationMode));
            logInfo("Transaction has been started, transaction mode - " + transactionDemacrationMode);

            String[] personIdArr = personIdsString.split(",");
            int i = 0;
            for(String personId: personIdArr) {
                i++;
                try {
                    MutableRepositoryItem resume = this.getRepository().getItemForUpdate(personId, "resume");
                    if(resume != null) {
                        RepositoryItem addedSkill = this.getRepository().getItem(addedSkillId, "skill");
                        if(!checkSkillInResume(addedSkillId, resume)){
                            Set<RepositoryItem> skills = (Set<RepositoryItem>) resume.getPropertyValue("skills");
                            skills.add(addedSkill);
                            resume.setPropertyValue("skills", skills);
                            //resume.setPropertyValue("name", "Test resume name");
                            this.getRepository().updateItem(resume);
                            Thread.sleep(10000);
                            logInfo("Skill - " + addedSkill.getPropertyValue("name") + " has been added to person - " + personId);
                        }
                        else {
                            logInfo("Skill - " + addedSkill.getPropertyValue("name") + " already exists in a person - " + personId);
                        }
                    } else {
                        this.addFormException(new DropletException("Person with id - " + personId + " doesn't exists"));
                        logInfo("Person with id - " + personId + " doesn't exists");
                    }
                } catch (RepositoryException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        } catch (TransactionDemarcationException e) {
            try {
                this.getTransactionManager().setRollbackOnly();
            } catch (SystemException ex) {
                ex.printStackTrace();
            }
            throw new ServletException(e);
        } finally {
            try {
                logInfo("Transaction has been ended");
                td.end();
                long stopTime = System.currentTimeMillis();
                long elapsedTime = stopTime - startTime;
                logInfo("TIME - " + elapsedTime);
            } catch (TransactionDemarcationException e) {
                throw new ServletException(e);
            }
        }
    }

    public void handleAddSkillInSeparateTransactions(DynamoHttpServletRequest req, DynamoHttpServletResponse res) throws ServletException, IOException {
        long startTime = System.currentTimeMillis();

        String[] personIdArr = personIdsString.split(",");

        for (String personId : personIdArr) {
            try {
                MutableRepositoryItem resume = this.getRepository().getItemForUpdate(personId, "resume");
                if (resume != null) {
                    RepositoryItem addedSkill = this.getRepository().getItem(addedSkillId, "skill");
                    if (!checkSkillInResume(addedSkillId, resume)) {
                        Set<RepositoryItem> skills = (Set<RepositoryItem>) resume.getPropertyValue("skills");
                        skills.add(addedSkill);
                        resume.setPropertyValue("skills", skills);
                        //this.getRepository().updateItem(resume);
                        Thread.sleep(10000);
                        logInfo("Skill - " + addedSkill.getPropertyValue("name") + " has been added to person - " + personId);
                    } else {
                        logInfo("Skill - " + addedSkill.getPropertyValue("name") + " already exists in a person - " + personId);
                    }
                } else {
                    this.addFormException(new DropletException("Person with id - " + personId + " doesn't exists"));
                    logInfo("Person with id - " + personId + " doesn't exists");
                }
            } catch (RepositoryException | InterruptedException e) {

                e.printStackTrace();
            }
        }

        long stopTime = System.currentTimeMillis();
        long elapsedTime = stopTime - startTime;
        logInfo("TIME SEPARATED TS - " + elapsedTime);
    }



    private boolean checkSkillInResume(String skillId, MutableRepositoryItem resume) {
        Set<RepositoryItem> skills = (Set<RepositoryItem>) resume.getPropertyValue("skills");
        if(skills.size() > 0) {
            for(RepositoryItem skill : skills){
                if(skill.getRepositoryId().equals(skillId)) {
                   return true;
                }
            }
        }
        return false;
    }

    private TransactionManager getTransactionManager() {
        Repository r = this.getRepository();
        return r instanceof RepositoryImpl ? ((RepositoryImpl)r).getTransactionManager() : null;
    }

    @Override
    public void logInfo(String pMessage) {
        long threadId = Thread.currentThread().getId();
        String output = String.format("SkillFormHandler(Thread - %d) : %s", threadId, pMessage);
        super.logInfo(output);
    }

    public MutableRepository getRepository() {
        return repository;
    }

    public void setRepository(MutableRepository repository) {
        this.repository = repository;
    }

    public String getPersonIdsString() {
        return personIdsString;
    }

    public void setPersonIdsString(String personIdsString) {
        this.personIdsString = personIdsString;
    }

    public String getAddedSkillId() {
        return addedSkillId;
    }

    public void setAddedSkillId(String addedSkillId) {
        this.addedSkillId = addedSkillId;
    }

    public String getTransactionDemacrationMode() {
        return transactionDemacrationMode;
    }

    public void setTransactionDemacrationMode(String transactionDemacrationMode) {
        this.transactionDemacrationMode = transactionDemacrationMode;
    }
}
