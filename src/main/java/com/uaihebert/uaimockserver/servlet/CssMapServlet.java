package com.uaihebert.uaimockserver.servlet;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CssMapServlet extends AbstractServlet {

    @Override
    // todo alterar instance do Context para ser igual ao do Calendar
    protected void doGet(final HttpServletRequest httpRequest, final HttpServletResponse httpResponse) throws ServletException, IOException {
        httpResponse.setContentType("text/plain");

        super.addDefaultHeaders(httpResponse);

        printResource(httpResponse.getOutputStream(), "/css/bootstrap.css.map");
    }
}