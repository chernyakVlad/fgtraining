<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/tld/dspjspTaglib1_0.tld" prefix="dsp" %>

<dsp:page>
    <html>
        <head>
            <link rel="stylesheet" href="css/bootstrap.min.css">
            <title>Hello</title>
        </head>
        <body>
            <div class="alert alert-primary" role="alert">
              Hello woasdpkasdpoasdokprd!
            </div>
            <dsp:droplet name="/com/training/components/PersonDynamoServlet">
                <dsp:param name="name" bean="/com/training/components/Person.name"/>
                <dsp:oparam name="OUTPUT">
                  <h1><dsp:valueof param="upperCaseName"/></h1>
                </dsp:oparam>

            </dsp:droplet>
            <dsp:form name="personForm">
                <div class="form-group">
                    <label for="exampleInputEmail1">Name</label>
                    <dsp:input  name="fName" type="text" bean="/com/training/components/PersonFormHandler.firstName"/>
                </div>
                <div class="form-group">
                    <label for="exampleInputPassword1">Age</label>
                    <dsp:input name="fAge" type="text" bean="/com/training/components/PersonFormHandler.age"/>
                </div>
            </dsp:form>
        </body>
    </html>
</dsp:page>
