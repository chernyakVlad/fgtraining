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
  <dsp:getvalueof var="userId" param="personId" vartype="java.lang.String"/>
  <div class="container">
    <div class="row justify-content-center align-items-center">
      <div class="col-md-8">

        <div class="card">
          <div class="card-header">
            <div class="float-left">
              Person Id - ${userId}
            </div>
            <div class="float-right">
              <dsp:a href="editPerson.jsp">
                <dsp:param name="pId" param="personId"/>
                <div class="btn btn-info btn-sm">edit</div>
              </dsp:a>
            </div>
          </div>

          <dsp:droplet name="/com/training/components/droplets/GetPersonById">
          <dsp:param name="id" value="${userId}"/>
          <dsp:oparam name="output">
          <ul class="list-group list-group-flush">
            <li class="list-group-item">FirstName: <dsp:valueof param="element.firstName"></dsp:valueof></li>
            <li class="list-group-item">LastName: <dsp:valueof param="element.lastName"></dsp:valueof></li>
          </ul>
        </div>
          <dsp:droplet name="/atg/dynamo/droplet/ForEach">
            <dsp:param name="array" param="element.roles"/>
            <dsp:oparam name="outputStart">
              <div class="card mt-4">
              <div class="card-header">
                Roles:
              </div>
              <ul class="list-group list-group-flush">
            </dsp:oparam>
            <dsp:oparam name="output">
              <li class="list-group-item"><dsp:valueof param="element.name"/></li>
            </dsp:oparam>
            <dsp:oparam name="outputStart">
              </ul>
              </div>
            </dsp:oparam>
          </dsp:droplet>

          <dsp:droplet name="/atg/dynamo/droplet/ForEach">
            <dsp:param name="array" param="element.mentoredUsers"/>
            <dsp:oparam name="outputStart">
              <div class="card mt-4">
              <div class="card-header">
                Mentored:
              </div>
              <ul class="list-group list-group-flush">
            </dsp:oparam>
            <dsp:oparam name="output">
              <li class="list-group-item">
                <dsp:a href="detailPerson.jsp">
                  <dsp:param name="personId" param="element.id"/>
                  <dsp:valueof param="element.firstName"/>
                  <dsp:valueof param="element.lastName"/>
                </dsp:a>
              </li>
            </dsp:oparam>
            <dsp:oparam name="outputStart">
              </ul>
              </div>
            </dsp:oparam>
          </dsp:droplet>
          <dsp:droplet name="/atg/dynamo/droplet/ForEach">
            <dsp:param name="array" param="element.mentors"/>
            <dsp:oparam name="outputStart">
              <div class="card mt-4">
              <div class="card-header">
                Mentors:
              </div>
              <ul class="list-group list-group-flush">
            </dsp:oparam>
            <dsp:oparam name="output">
              <li class="list-group-item">
                <dsp:a href="detailPerson.jsp">
                  <dsp:param name="personId" param="element.id"/>
                  <dsp:valueof param="element.firstName"/>
                  <dsp:valueof param="element.lastName"/>
                </dsp:a>
              </li>
            </dsp:oparam>
            <dsp:oparam name="outputStart">
              </ul>
              </div>
            </dsp:oparam>
          </dsp:droplet>
        </dsp:oparam>
        </dsp:droplet>

      </div>
    </div>
  </div>
  </body>
  </html>
</dsp:page>
