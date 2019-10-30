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

  <dsp:importbean bean="/atg/userprofiling/ProfileFormHandler"/>
  <dsp:importbean bean="/atg/userprofiling/ProfileErrorMessageForEach"/>
  <dsp:importbean bean="/atg/userprofiling/Profile"/>
  <dsp:importbean bean="/atg/userprofiling/PropertyManager"/>
  <dsp:importbean bean="/atg/dynamo/droplet/Switch"/>
  <dsp:importbean bean="/atg/dynamo/droplet/Compare"/>

  <body>
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

  <main class="my-form">
    <div class="cotainer">
      <div class="row justify-content-center">
        <div class="col-md-6">
          <div class="card">
            <div class="card-header">Detail</div>
            <div class="card-body">

              <div class="form-group row">
                <label class="col-md-4 col-form-label text-md-right">Login</label>
                <div class="col-md-6">
                  <div class="form-control">
                    <dsp:valueof bean="ProfileFormHandler.value.login"/>
                  </div>
                </div>
              </div>

              <div class="form-group row">
                <label class="col-md-4 col-form-label text-md-right">E-mail</label>
                <div class="col-md-6">
                  <div class="form-control">
                    <dsp:valueof bean="ProfileFormHandler.value.email"/>
                  </div>
                </div>
              </div>

            </div>
          </div>
        </div>
      </div>
    </div>
  </main>
  </body>
  </html>
</dsp:page>
