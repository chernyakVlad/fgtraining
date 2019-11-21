<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="dsp" uri="/WEB-INF/tld/dspjspTaglib1_0.tld" %>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld" %>

<dsp:page>
  <dsp:importbean bean="/atg/multisite/Site" var="currentSite"/>
  <dsp:importbean bean="/atg/store/StoreConfiguration" var="storeConfiguration"/>

  <html>
  <head>
    <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
    <link rel="stylesheet" href="css/bootstrap.min.css">
    <link rel="stylesheet" href="css/main.css">
    <title>Persons</title>
    <dsp:getvalueof var="siteCssFile" bean="Site.cssFile" />
    <link rel="stylesheet" href="${contextPath}/css/${siteCssFile}"/>
  </head>
  <body>

  <dsp:importbean bean="/atg/userprofiling/ProfileFormHandler"/>
  <dsp:importbean bean="/atg/userprofiling/ProfileErrorMessageForEach"/>
  <dsp:importbean bean="/atg/dynamo/droplet/Switch"/>
  <dsp:importbean bean="/atg/dynamo/droplet/ForEach"/>

  <dsp:droplet name="Switch">
    <dsp:param bean="ProfileFormHandler.formError" name="value" />
    <dsp:oparam name="true">
      <ul>
        <dsp:droplet name="ProfileErrorMessageForEach">
          <dsp:param bean="ProfileFormHandler.formExceptions" name="exceptions" />
          <dsp:oparam name="output">
            <li>
              <div class="alert alert-danger" role="alert">
                <dsp:valueof param="message" />
              </div>
            </li>
          </dsp:oparam>
        </dsp:droplet>
      </ul>
    </dsp:oparam>
  </dsp:droplet>


  <main class="my-form">
    <div class="cotainer">
      <div class="row justify-content-center">
        <div class="col-md-6">
          <div class="card">
            <div class="card-header">Login</div>
            <div class="card-body">
              <dsp:form name="my-form" action="login.jsp" method="post">
                <dsp:input bean="ProfileFormHandler.loginSuccessURL" type="hidden" value="detailProfile.jsp"/>

                <div class="form-group row">
                  <label class="col-md-4 col-form-label text-md-right">Login</label>
                  <div class="col-md-6">
                    <dsp:input bean="ProfileFormHandler.value.login" iclass="form-control" type="text"/>
                  </div>
                </div>

                <div class="form-group row">
                  <label class="col-md-4 col-form-label text-md-right">Password</label>
                  <div class="col-md-6">
                    <dsp:input bean="ProfileFormHandler.value.password" iclass="form-control" type="text"/>
                  </div>
                </div>

                <div class="col-md-6 offset-md-4">
                  <dsp:input iclass="btn btn-primary" bean="ProfileFormHandler.login" type="submit" value="Login"/>
                </div>

              </dsp:form>


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
