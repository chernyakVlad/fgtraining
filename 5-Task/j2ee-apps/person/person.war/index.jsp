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
    <dsp:importbean bean="/atg/userprofiling/ProfileFormHandler"/>
    <dsp:importbean bean="/atg/userprofiling/Profile"/>
    <dsp:importbean bean="/atg/userprofiling/ProfileFormHandler"/>
    <dsp:importbean bean="/atg/userprofiling/PropertyManager"/>
    <dsp:importbean bean="/atg/dynamo/droplet/Compare"/>

    <dsp:form name="my-form" action="index.jsp" method="post">
    <nav class="navbar navbar-expand-lg navbar-light navbar-laravel">
      <div class="container">
        <a class="navbar-brand" href="#">5-Task</a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent"
                aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
          <span class="navbar-toggler-icon"></span>
        </button>

        <div class="collapse navbar-collapse" id="navbarSupportedContent">
          <ul class="navbar-nav ml-auto">
            <dsp:droplet name="Compare">
            <dsp:param bean="Profile.securityStatus" name="obj1"/>
            <dsp:param bean="PropertyManager.securityStatusLogin" name="obj2"/>
            <dsp:oparam name="lessthan">
              <li class="nav-item">
                <dsp:a iclass="nav-link" href="login.jsp">Login</dsp:a>
              </li>
              <li class="nav-item">
                <dsp:a iclass="nav-link" href="registration.jsp">Registration</dsp:a>
              </li>
            </dsp:oparam>
              <dsp:oparam name="equal">
                <li class="nav-item">
                  <dsp:input iclass="btn btn-primary" bean="ProfileFormHandler.logout" type="submit" value="Logout"/>
                </li>
              </dsp:oparam>
            <dsp:oparam name="greaterthan">
              <li class="nav-item">
                <dsp:input iclass="btn btn-primary" bean="ProfileFormHandler.logout" type="submit"   value="Logout"/>
              </li>
            </dsp:oparam>
            </dsp:droplet>
          </ul>
        </div>
      </div>
    </nav>
    </dsp:form>
    </body>
  </html>
</dsp:page>
