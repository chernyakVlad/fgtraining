<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="dsp" uri="/WEB-INF/tld/dspjspTaglib1_0.tld" %>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld" %>

<dsp:page>
  <html>
  <head>
    <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
    <link rel="stylesheet" href="css/bootstrap.min.css">
    <link rel="stylesheet" href="css/main.css">
    <title>Persons</title>
  </head>
  <body>
  <dsp:importbean bean="/com/training/components/formHandlers/SkillFormHandler"/>
  <dsp:importbean bean="/atg/dynamo/droplet/Switch"/>
  <dsp:importbean bean="/atg/userprofiling/ProfileErrorMessageForEach"/>

  <dsp:droplet name="Switch">
    <dsp:param bean="SkillFormHandler.formError" name="value" />
    <dsp:oparam name="true">
      <ul class="pr-5 pl-5">
        <dsp:droplet name="ProfileErrorMessageForEach">
          <dsp:param bean="SkillFormHandler.formExceptions" name="exceptions" />
          <dsp:oparam name="output">
            <div class="alert alert-danger" role="alert">
              <dsp:valueof param="message"/>
            </div>
          </dsp:oparam>
        </dsp:droplet>
      </ul>
    </dsp:oparam>
  </dsp:droplet>

  <nav class="navbar navbar-expand-lg navbar-light navbar-laravel">
    <div class="container">
      <a class="navbar-brand" href="#">8-Task</a>
      <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent"
              aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
      </button>
    </div>
  </nav>

  <dsp:form name="my-form" action="sevenTask.jsp" method="post">
  <main class="my-form">
    <div class="cotainer">
      <div class="row justify-content-center">
        <div class="col-md-6">
          <div class="card">
            <div class="card-header">Skill update task</div>
            <div class="card-body">

              <div class="form-group row">
                <label class="col-md-4 col-form-label text-md-right">Skill</label>
                <div class="col-md-6">
                  <dsp:select bean="SkillFormHandler.addedSkillId" iclass="form-control">
                    <dsp:droplet name="/atg/dynamo/droplet/RQLQueryRange">
                      <dsp:param name="queryRQL" value="all"/>
                      <dsp:param name="repository" value="/com/training/components/repository/PersonCVRepository"/>
                      <dsp:param name="howMany" value="10"/>
                      <dsp:param name="itemDescriptor" value="skill"/>

                      <dsp:oparam name="output">
                        <dsp:option paramvalue="element.repositoryId"><dsp:valueof param="element.name"/></dsp:option>
                      </dsp:oparam>

                    </dsp:droplet>
                  </dsp:select>
                </div>
              </div>

              <div class="form-group row">
                <label class="col-md-4 col-form-label text-md-right">Resume ids</label>
                <div class="col-md-6">
                  <dsp:input bean="SkillFormHandler.personIdsString" iclass="form-control" type="text"/>
                </div>
              </div>

              <div class="form-group row">
                <label class="col-md-4 col-form-label text-md-right">Transaction Mode</label>
                <div class="col-md-6">
                  <dsp:select bean="SkillFormHandler.transactionDemacrationMode" iclass="form-control">
                    <dsp:option value="1">NOT_SUPPORTED</dsp:option>
                    <dsp:option value="2">SUPPORTS</dsp:option>
                    <dsp:option value="3">REQUIRED</dsp:option>
                    <dsp:option value="4">REQUIRES_NEW</dsp:option>
                    <dsp:option value="5">MANDATORY</dsp:option>
                    <dsp:option value="6">NEVER</dsp:option>
                  </dsp:select>
                </div>
              </div>

              <div class="col-md-6 offset-md-4">
                <dsp:input iclass="btn btn-primary" bean="SkillFormHandler.addSkillUpdate" type="submit" value="Update"/>
                <dsp:input iclass="btn btn-primary" bean="SkillFormHandler.addSkillUpdate1" type="submit" value="Update1"/>
              </div>

            </div>
          </div>
        </div>
      </div>
    </div>
  </main>
  </dsp:form>

  </body>
  </html>
</dsp:page>
