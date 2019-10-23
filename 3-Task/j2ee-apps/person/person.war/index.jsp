
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="dsp" uri="/WEB-INF/tld/dspjspTaglib1_0.tld" %>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>

<dsp:page>
    <html>
        <head>
            <link rel="stylesheet" href="css/bootstrap.min.css">
            <title>Persons</title>
        </head>
        <body>
            <div class="col-md-12">
                <h4>Persons</h4>
                <div class="table-responsive">
                    <table id="mytable" class="table table-bordred table-striped table-hover">
                        <dsp:droplet name="/atg/dynamo/droplet/RQLQueryRange">
                            <dsp:param name="queryRQL" value="all"/>
                            <dsp:param name="repository" value="/com/training/components/repository/PersonRepository1"/>
                            <dsp:param name="howMany" value="10"/>
                            <dsp:param name="itemDescriptor" value="person"></dsp:param>

                            <dsp:oparam name="output">
                                <tr>
                                    <td><dsp:valueof param="element.firstName"/></td>
                                    <td><dsp:valueof param="element.lastName"/></td>
                                    <td>
                                        <dsp:droplet name="/atg/dynamo/droplet/ForEach">
                                            <dsp:param name="array" param="element.roles" />
                                            <dsp:oparam name="output">
                                                <dsp:valueof param="element.name" />
                                            </dsp:oparam>
                                        </dsp:droplet>
                                    </td>
                                </tr>
                            </dsp:oparam>
                        </dsp:droplet>
                    </table>
                </div>

                <h4>Roles of Person: James </h4>
                <div class="table-responsive">
                        <dsp:droplet name="/atg/dynamo/droplet/RQLQueryRange">
                            <dsp:param name="name" value="James"/>
                            <dsp:param name="queryRQL" value="firstName = :name"/>
                            <dsp:param name="repository" value="/com/training/components/repository/PersonRepository1"/>
                            <dsp:param name="howMany" value="10"/>
                            <dsp:param name="itemDescriptor" value="person"></dsp:param>
                            <dsp:oparam name="output">
                                        <dsp:droplet name="/atg/dynamo/droplet/ForEach">
                                            <dsp:param name="array" param="element.roles" />
                                            <dsp:oparam name="output">
                                                <dsp:valueof param="element.name" />
                                            </dsp:oparam>
                                        </dsp:droplet>
                            </dsp:oparam>
                        </dsp:droplet>
                </div>

                <h4>Visit:</h4>
                <div class="table-responsive">
                    <table class="table table-bordred table-striped table-hover">
                        <dsp:droplet name="/atg/dynamo/droplet/RQLQueryRange">
                            <dsp:param name="isOk" value="1"/>
                            <dsp:param name="date1"  value="2019-10-22"/>
                            <dsp:param name="queryRQL" value="visitDate = date(date1)"/>
                            <dsp:param name="repository" value="/com/training/components/repository/PersonRepository1"/>
                            <dsp:param name="howMany" value="10"/>
                            <dsp:param name="itemDescriptor" value="visit"></dsp:param>

                            <dsp:oparam name="output">
                                <tr>
                                    <td><dsp:valueof param="element.personId"/></td>
                                    <td><dsp:valueof param="element.visitDate"/></td>
                                    <td><dsp:valueof param="element.isOk"/></td>
                                </tr>
                            </dsp:oparam>
                        </dsp:droplet>
                    </table>
                </div>
            </div>



        </body>
    </html>
</dsp:page>
