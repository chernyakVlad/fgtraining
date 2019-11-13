<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="dsp" uri="/WEB-INF/tld/dspjspTaglib1_0.tld" %>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld" %>

<dsp:page>
  <html>
  <head>
    <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
    <link rel="stylesheet" href="css/bootstrap.min.css">
    <link rel="stylesheet" href="css/main.css">
    <script src="js/main.js"></script>
    <title>Persons</title>
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
            <div class="card-header">Register</div>
            <div class="card-body">
              <dsp:form onsubmit="return validform()" name="my-form" action="registration.jsp" method="post">
                <dsp:input bean="ProfileFormHandler.createSuccessURL" type="hidden" value="detailProfile.jsp"/>

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

                <div class="form-group row">
                  <label class="col-md-4 col-form-label text-md-right">Password Confirmation</label>
                  <div class="col-md-6">
                    <dsp:input bean="ProfileFormHandler.value.confirmpassword" iclass="form-control" type="text"/>
                  </div>
                </div>



                <div class="form-group row">
                  <label class="col-md-4 col-form-label text-md-right">E-mail</label>
                  <div class="col-md-6">
                    <dsp:input bean="ProfileFormHandler.value.email" iclass="form-control" type="text"/>
                  </div>
                </div>

                <div class="col-md-6 offset-md-4">
                  <dsp:input iclass="btn btn-primary" bean="ProfileFormHandler.create" type="submit" value="Register"/>
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
