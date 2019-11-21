<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="dsp" uri="/WEB-INF/tld/dspjspTaglib1_0.tld" %>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld" %>

<dsp:page>
  <dsp:getvalueof var="contextPath" bean="/OriginatingRequest.contextPath"/>
  <dsp:importbean bean="/atg/multisite/Site" var="currentSite"/>
  <dsp:importbean bean="/atg/store/StoreConfiguration" var="storeConfiguration"/>
  <dsp:importbean bean="/atg/userprofiling/ProfileFormHandler"/>
  <dsp:importbean bean="/atg/userprofiling/Profile"/>
  <dsp:importbean bean="/atg/userprofiling/ProfileFormHandler"/>
  <dsp:importbean bean="/atg/userprofiling/PropertyManager"/>
  <dsp:importbean bean="/atg/dynamo/droplet/Compare"/>

  <html>
    <head>
      <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
      <link rel="stylesheet" href="css/bootstrap.min.css">
      <title>Persons</title>
      <dsp:getvalueof var="siteCssFile" bean="Site.cssFile" />
      <link rel="stylesheet" href="${contextPath}/css/${siteCssFile}"/>
    </head>

    <body>
    <a href="#">${currentSite.id}</a>
    <dsp:droplet name="/atg/dynamo/droplet/multisite/SiteLinkDroplet">
      <dsp:param name="siteId" value="${currentSite.id}"/>
      <dsp:oparam name="output">
        <dsp:getvalueof var="siteLinkUrl1" scope="request" param="url"/>
      </dsp:oparam>
    </dsp:droplet>
    <dsp:form name="my-form" action="${siteLinkUrl1}" method="post">
    <nav class="navbar navbar-expand-lg navbar-light navbar-laravel">
      <div class="container">
        <a class="navbar-brand" href="#">7-Task</a>
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
                <dsp:droplet name="/atg/dynamo/droplet/multisite/SiteLinkDroplet">
                  <dsp:param name="siteId" value="${currentSite.id}"/>
                  <dsp:param name="path" value="/login.jsp"/>
                  <dsp:oparam name="output">
                    <dsp:getvalueof var="siteLinkUrl" scope="request" param="url"/>
                  </dsp:oparam>
                </dsp:droplet>
                <dsp:a iclass="nav-link" href="${siteLinkUrl}">Login</dsp:a>
              </li>
              <li class="nav-item">
                <dsp:droplet name="/atg/dynamo/droplet/multisite/SiteLinkDroplet">
                  <dsp:param name="siteId" value="${currentSite.id}"/>
                  <dsp:param name="path" value="/registration.jsp"/>
                  <dsp:oparam name="output">
                    <dsp:getvalueof var="siteLinkUrl2" scope="request" param="url"/>
                  </dsp:oparam>
                </dsp:droplet>
                <dsp:a iclass="nav-link" href="${siteLinkUrl2}">Registration</dsp:a>
              </li>
            </dsp:oparam>
            <dsp:oparam name="equal">
              <li class="nav-item">
                <dsp:input iclass="btn btn-primary" bean="ProfileFormHandler.logout" type="submit" value="Logout"/>
              </li>
            </dsp:oparam>
            <dsp:oparam name="greaterthan">
              <li class="nav-item">
                <dsp:input iclass="btn btn-primary" bean="ProfileFormHandler.logout" type="submit" value="Logout"/>
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
