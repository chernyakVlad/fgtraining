<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="dsp" uri="/WEB-INF/tld/dspjspTaglib1_0.tld" %>


<dsp:page>
    <html>
        <head>
            <link rel="stylesheet" href="css/bootstrap.min.css">
            <title>Hello</title>
        </head>
        <body>
            <div class="alert alert-primary" role="alert">
              Hello wordl!
            </div>
            <div class="alert alert-primary" role="alert">
                Person name:
                <dsp:valueof bean="/com/training/components/Person.name"/>
            </div>
            <dsp:form name="personNameForm">
                <dsp:input type="submit" name="button" value="Name_1" bean="/com/training/components/PersonFormHandler.PersonTitle"/>
                <dsp:input type="submit" name="button" value="Name_2" bean="/com/training/components/PersonFormHandler.PersonTitle"/>
                <dsp:input type="submit" name="button" value="Name_3" bean="/com/training/components/PersonFormHandler.PersonTitle"/>
            </dsp:form>
        </body>
    </html>
</dsp:page>
