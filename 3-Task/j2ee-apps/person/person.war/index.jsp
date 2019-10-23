
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
        <c:set var="userIdTest" scope="page"
               value="2"/>
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

                <h4>Roles of Person: (id - ${userIdTest})</h4>
                <div class="table-responsive">
                    <dsp:droplet name="/atg/dynamo/droplet/RQLQueryRange">
                        <dsp:param name="id1" value="${userIdTest}"/>
                        <dsp:param name="queryRQL" value="id = :id1"/>
                        <dsp:param name="repository" value="/com/training/components/repository/PersonRepository1"/>
                        <dsp:param name="howMany" value="10"/>
                        <dsp:param name="itemDescriptor" value="person"/>
                        <ul>
                            <dsp:oparam name="output">
                                <dsp:droplet name="/atg/dynamo/droplet/ForEach">
                                    <dsp:param name="array" param="element.roles" />
                                    <dsp:oparam name="output">
                                        <li>
                                            <dsp:valueof param="element.name" />
                                        </li>
                                    </dsp:oparam>
                                </dsp:droplet>
                            </dsp:oparam>
                        </ul>
                    </dsp:droplet>
                </div>

                <h4>Persons mentored of Person: Maria(id - 5)</h4>
                <div class="table-responsive">
                    <dsp:droplet name="/atg/dynamo/droplet/RQLQueryRange">
                        <dsp:param name="id" value="5"/>
                        <dsp:param name="queryRQL" value="id = :id"/>
                        <dsp:param name="repository" value="/com/training/components/repository/PersonRepository1"/>
                        <dsp:param name="howMany" value="10"/>
                        <dsp:param name="itemDescriptor" value="person"></dsp:param>
                        <ul>
                            <dsp:oparam name="output">
                                <dsp:droplet name="/atg/dynamo/droplet/ForEach">
                                    <dsp:param name="array" param="element.mentoredUsers" />
                                    <dsp:oparam name="output">
                                        <li>
                                            <dsp:valueof param="element.firstName" />
                                            <dsp:valueof param="element.lastName" />
                                        </li>
                                    </dsp:oparam>
                                </dsp:droplet>
                            </dsp:oparam>
                        </ul>
                    </dsp:droplet>
                </div>

                <h4>Visit on "22/OCT/2019" and isOk = true:</h4>
                <div class="table-responsive">
                    <dsp:droplet name="/atg/dynamo/droplet/RQLQueryRange">
                        <dsp:param name="date" value="23/OCT/2019"/>
                        <dsp:param name="queryRQL" value="visitDate=:date and isOk = true"/>
                        <dsp:param name="repository" value="/com/training/components/repository/PersonRepository1"/>
                        <dsp:param name="howMany" value="10"/>
                        <dsp:param name="itemDescriptor" value="visit"/>
                        <ul>
                            <dsp:oparam name="output">
                                <li>
                                    <dsp:valueof param="element.personId" />
                                    <dsp:valueof param="element.visitDate" />
                                    <dsp:valueof param="element.isOk" />
                                </li>
                            </dsp:oparam>
                        </ul>
                    </dsp:droplet>
                </div>

                <h4>Scores of person James(id - 1)</h4>
                <div class="table-responsive">
                    <dsp:droplet name="/atg/dynamo/droplet/RQLQueryRange">
                        <dsp:param name="personId" value="1"/>
                        <dsp:param name="queryRQL" value="personId = :personId order by scoreNumber sort asc"/>
                        <dsp:param name="repository" value="/com/training/components/repository/PersonRepository1"/>
                        <dsp:param name="howMany" value="10"/>
                        <dsp:param name="itemDescriptor" value="score"/>
                        <ul>
                            <dsp:oparam name="output">
                                <li>
                                    <dsp:valueof param="element.scoreNumber" />
                                    <dsp:valueof param="element.balanceUsd" />
                                    <dsp:valueof param="element.personId" />
                                </li>
                            </dsp:oparam>
                        </ul>
                    </dsp:droplet>
                </div>

            </div>
        </body>
    </html>
</dsp:page>
