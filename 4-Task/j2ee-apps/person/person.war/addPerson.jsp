<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="dsp" uri="/WEB-INF/tld/dspjspTaglib1_0.tld" %>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld" %>

<dsp:page>
  <html>
  <head>
    <link rel="stylesheet" href="css/bootstrap.min.css">
    <link rel="stylesheet" href="css/main.css">
    <title>Person detail</title>
  </head>
  <body>
  <div class="container">
    <div class="row justify-content-center align-items-center">
      <div class="col-md-6">
        <dsp:form   method="post">
          <dsp:importbean bean="/com/training/components/repositoryFormHandlers/PersonRepositoryFormHandler"/>
          <div class="card">
            <div class="card-header">
              Adding persons
            </div>
            <ul class="list-group list-group-flush">
              <li class="list-group-item">
                FirstName:
                <dsp:input bean="PersonRepositoryFormHandler.value.firstName" iclass="form-control"  type="text"/>
              </li>
              <li class="list-group-item">
                LastName:
                <dsp:input bean="PersonRepositoryFormHandler.value.lastName" iclass="form-control"  type="text"/>
              </li>
              <li class="list-group-item">
                Roles:
                <dsp:select bean="PersonRepositoryFormHandler.rolesList" iclass="form-control" multiple="true">
                  <dsp:droplet name="/atg/dynamo/droplet/RQLQueryRange">

                    <dsp:param name="queryRQL" value="all"/>
                    <dsp:param name="repository" value="/com/training/components/repository/PersonRepository"/>
                    <dsp:param name="howMany" value="4"/>
                    <dsp:param name="itemDescriptor" value="role"/>

                    <dsp:oparam name="output">
                      <dsp:option paramvalue="element.repositoryId"><dsp:valueof param="element.name"/></dsp:option>
                    </dsp:oparam>

                  </dsp:droplet>
                </dsp:select>
              </li>
              <li class="list-group-item">
                Mentors:
                <dsp:select bean="PersonRepositoryFormHandler.menotrsIdList" iclass="form-control" multiple="true">
                  <dsp:droplet name="/atg/dynamo/droplet/RQLQueryRange">

                    <dsp:param name="queryRQL" value="all"/>
                    <dsp:param name="repository" value="/com/training/components/repository/PersonRepository"/>
                    <dsp:param name="howMany" value="10"/>
                    <dsp:param name="itemDescriptor" value="person"/>

                    <dsp:oparam name="output">
                      <dsp:option paramvalue="element.repositoryId">
                        <dsp:valueof param="element.firstName"/>
                        <dsp:valueof param="element.lastName"/>
                      </dsp:option>
                    </dsp:oparam>

                  </dsp:droplet>
                </dsp:select>
              </li>

              <dsp:input bean="PersonRepositoryFormHandler.create" type="submit" iclass="btn btn-primary" value="Add" />
              <dsp:input bean="PersonRepositoryFormHandler.createSuccessURL" type="hidden" value="index.jsp"/>
            </ul>
          </div>
        </dsp:form>
      </div>
    </div>
  </div>
  </body>
  </html>
</dsp:page>
