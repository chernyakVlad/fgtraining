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
    <div class="col-md-12">
      <div class="card-header">
        <div class="row">
          <div class="col-md-6">
            <div class="float-left">
              Persons:
            </div>
          </div>
          <div class="col-md-6">
            <div class="float-right">
              <dsp:a href="addPerson.jsp">
                <i class="material-icons">add_circle_outline</i>
              </dsp:a>
            </div>
          </div>
        </div>
      </div>
      <div class="table-responsive persons-table">
        <table id="mytable"  class="table table-bordred table-striped table-hover">
          <dsp:droplet name="/atg/dynamo/droplet/RQLQueryRange">
            <dsp:param name="queryRQL" value="all"/>
            <dsp:param name="repository" value="/com/training/components/repository/PersonRepository"/>
            <dsp:param name="howMany" value="10"/>
            <dsp:param name="itemDescriptor" value="person"/>

            <dsp:oparam name="outputStart">
              <dsp:importbean bean="/com/training/components/repositoryFormHandlers/PersonRepositoryFormHandler"/>
              <thead>
                <th>ID</th>
                <th>FirstName</th>
                <th>LastName</th>
                <th>Roles</th>
                <th>Action</th>
              </thead>
              <tbdoy>
            </dsp:oparam>

            <dsp:oparam name="output">
              <tr>
                <td class="align-middle"><dsp:valueof param="element.id"/></td>
                <td class="align-middle"><dsp:valueof param="element.firstName"/></td>
                <td class="align-middle"><dsp:valueof param="element.lastName"/></td>
                <td class="align-middle personRole">
                  <dsp:droplet name="/atg/dynamo/droplet/ForEach">
                    <dsp:param name="array" param="element.roles"/>
                    <dsp:oparam name="output">
                      <li>
                        <dsp:valueof param="element.name"/>
                      </li>
                    </dsp:oparam>
                  </dsp:droplet>
                </td>
                <td class="align-middle">
                  <div class="btn-group" role="group" aria-label="First group">
                    <dsp:a href="detailPerson.jsp">
                      <dsp:param name="personId" param="element.id"/>
                      <div class="btn btn-info btn-sm">info</div>
                    </dsp:a>
                    <dsp:form>
                      <dsp:form action="<%=request.getRequestURI()%>" method="post">
                        <dsp:input type="hidden" bean="PersonRepositoryFormHandler.repositoryId" paramvalue="element.repositoryId" />
                        <dsp:input bean="PersonRepositoryFormHandler.delete" type="submit" value="delete" iclass="btn btn-danger btn-sm"/>
                        <dsp:input bean="PersonRepositoryFormHandler.deleteSuccessURL" type="hidden" value="index.jsp" />
                      </dsp:form>
                    </dsp:form>
                  </div>
                </td>
              </tr>
            </dsp:oparam>
            <dsp:oparam name="outputEnd">
              </tbdoy>
            </dsp:oparam>
          </dsp:droplet>
        </table>
      </div>
    </div>
    </body>
  </html>
</dsp:page>
