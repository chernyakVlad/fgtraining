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
  <dsp:getvalueof var="userId1" param="pId" vartype="java.lang.String"/>
  <div class="container">
    <div class="row justify-content-center align-items-center">
      <div class="col-md-8">
        <dsp:form   method="post">
          <dsp:importbean bean="/com/training/components/repositoryFormHandlers/PersonRepositoryFormHandler"/>

          <div class="card">
            <div class="card-header">
              Person Id - ${userId1}
            </div>
            <ul class="list-group list-group-flush">
              <li class="list-group-item">
                FirstName:
                <dsp:input bean="PersonRepositoryFormHandler.value.firstName" iclass="form-control" type="text"/>
              </li>
              <li class="list-group-item">
                LastName:
                <dsp:input bean="PersonRepositoryFormHandler.value.lastName" iclass="form-control" type="text"/>
              </li>
              <dsp:input bean="PersonRepositoryFormHandler.repositoryId" type="hidden" value="${userId1}"   />
              <dsp:input bean="PersonRepositoryFormHandler.update" type="submit" iclass="btn btn-primary" value="Edit" />
              <dsp:input bean="PersonRepositoryFormHandler.updateSuccessURL" type="hidden" value="index.jsp"/>

            </ul>
          </div>
        </dsp:form>




      </div>
    </div>
  </div>
  </body>
  </html>
</dsp:page>
