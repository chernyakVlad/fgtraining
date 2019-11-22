<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="dsp" uri="/WEB-INF/tld/dspjspTaglib1_0.tld" %>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld" %>

<dsp:page>
  <dsp:importbean bean="/atg/userprofiling/ProfileFormHandler"/>
  <dsp:importbean bean="/atg/userprofiling/ProfileErrorMessageForEach"/>
  <dsp:importbean bean="/atg/userprofiling/Profile"/>
  <dsp:importbean bean="/atg/userprofiling/PropertyManager"/>
  <dsp:importbean bean="/atg/dynamo/droplet/Switch"/>
  <dsp:importbean bean="/atg/dynamo/droplet/Compare"/>
  <dsp:importbean bean="/atg/commerce/ShoppingCart"/>
  <dsp:importbean bean="/atg/dynamo/droplet/ForEach"/>
  <dsp:importbean bean="/atg/multisite/Site" var="currentSite"/>
  <dsp:importbean bean="/atg/store/StoreConfiguration" var="storeConfiguration"/>
  <dsp:getvalueof bean="/OriginatingRequest.contextPath" var="contextPath" />

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
  <dsp:importbean bean="/atg/multisite/Site" var="currentSite"/>
  <a href="#">${currentSite.id}</a>
  <dsp:form name="my-form" formid="2" action="detailProfile.jsp" method="post">
    <nav class="navbar navbar-expand-lg navbar-light navbar-laravel">
      <div class="container">
        <a class="navbar-brand" href="#">7-Task</a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent"
                aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
          <span class="navbar-toggler-icson"></span>
        </button>

        <div class="collapse navbar-collapse" id="navbarSupportedContent">
          <ul class="navbar-nav ml-auto">
            <li class="mr-2 nav-item">
              <dsp:input bean="ProfileFormHandler.searchString" iclass="form-control" type="text"/>
            </li>

            <li class="mr-2 nav-item">
              <dsp:input iclass="btn btn-primary" bean="ProfileFormHandler.search" type="submit" value="Search"/>
            </li>

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
            <ds class="card-body">

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

              <div>
                <dsp:form name="my-form1" formid="1" action="detailProfile.jsp" method="post">
                  <dsp:droplet name="/atg/dynamo/droplet/Switch">
                    <dsp:param bean="ShoppingCart.savedEmpty" name="value"/>

                    <dsp:oparam name="true">
                      <!-- since there are no saved carts, we cannot switch to another so
                      we only give them the option to create a new cart -->
                      <dsp:input bean="ShoppingCart.create" value="Create" type="submit"/>
                      another shopping cart
                    </dsp:oparam>

                    <dsp:oparam name="false">
                      <!-- We have other shopping carts, so let them do everything -->
                      Shopping Cart <dsp:select bean="ShoppingCart.handlerOrderId">
                      <dsp:droplet name="ForEach">
                        <dsp:param bean="ShoppingCart.saved" name="array"/>
                        <dsp:param value="savedcart" name="elementName"/>
                        <dsp:oparam name="output">
                          <dsp:getvalueof id="option26" param="savedcart.id"
                                          idtype="java.lang.String">
                            <dsp:option value="<%=option26%>"/>
                          </dsp:getvalueof><dsp:valueof param="savedcart.id"/>
                        </dsp:oparam>
                      </dsp:droplet>
                    </dsp:select>:
                      <dsp:input bean="ShoppingCart.switch" value="Switch" type="submit"/> to,
                      <dsp:input bean="ShoppingCart.delete" value="Delete" type="submit"/> or
                      <dsp:input bean="ShoppingCart.create" value="Create" type="submit"/>
                      another shopping cart.<BR>
                      <dsp:input bean="ShoppingCart.deleteAll"
                                 value="Delete All Shopping Carts" type="submit"/>
                    </dsp:oparam>

                  </dsp:droplet>
                </dsp:form>
              </div>>
              <hr>
              <h4>Targeter based on roles:</h4>
              <dsp:droplet name="/atg/targeting/TargetingFirst">
              <dsp:param
                  bean="/atg/registry/RepositoryTargeters/TargeterBasedOnRoles"
                  name="targeter"/>
              <dsp:param name="howMany" value="5"/>
              <dsp:oparam name="output">
                  <dsp:valueof param="element.name"/>
              </dsp:oparam>
              </dsp:droplet>
              <hr>
              <h4>Targeter based on last search:</h4>
              <dsp:droplet name="/atg/targeting/TargetingFirst">
                <dsp:param
                    bean="/atg/registry/RepositoryTargeters/TargeterBasedOnLastSearch"
                    name="targeter"/>
                <dsp:param name="howMany" value="5"/>
              <dsp:oparam name="output">
                <dsp:valueof param="element.name"/>
              </dsp:oparam>
              </dsp:droplet>
            </div>
          </div>
        </div>
      </div>
    </div>
  </main>
  </body>
  </html>
</dsp:page>
