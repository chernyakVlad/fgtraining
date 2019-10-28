package com.training.piplineableServlets;

import atg.servlet.DynamoHttpServletRequest;
import atg.servlet.DynamoHttpServletResponse;
import atg.servlet.pipeline.InsertableServletImpl;
import sun.rmi.runtime.Log;

import javax.servlet.ServletException;
import java.io.IOException;

public class LoggingUrlPipelineableServlet extends InsertableServletImpl {
    public LoggingUrlPipelineableServlet(){}

    @Override
    public void service(DynamoHttpServletRequest pRequest, DynamoHttpServletResponse pResponse) throws IOException, ServletException {
        logInfo("LoggingUrlPipelineableServlet: RequestURI:  " + pRequest.getRequestURI());
        logInfo("LoggingUrlPipelineableServlet: Parameters:  " + pRequest.getParameterMap());
        super.service(pRequest, pResponse);
    }
}
