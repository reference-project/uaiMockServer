/*
 * Copyright 2015 uaiHebert
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * */
package com.uaihebert.uaimockserver.util;

import com.uaihebert.uaimockserver.model.UaiMockServerContext;
import com.uaihebert.uaimockserver.server.UaiMockServerHandler;
import com.uaihebert.uaimockserver.servlet.CssMapServlet;
import com.uaihebert.uaimockserver.servlet.CssServlet;
import com.uaihebert.uaimockserver.servlet.FaviconServlet;
import com.uaihebert.uaimockserver.servlet.JavascriptServlet;
import com.uaihebert.uaimockserver.servlet.UaiIndexServlet;
import com.uaihebert.uaimockserver.servlet.UaiPageServlet;
import com.uaihebert.uaimockserver.servlet.UaiRouteServlet;
import io.undertow.Handlers;
import io.undertow.Undertow;
import io.undertow.server.HttpHandler;
import io.undertow.server.handlers.PathHandler;
import io.undertow.servlet.Servlets;
import io.undertow.servlet.api.DeploymentInfo;
import io.undertow.servlet.api.DeploymentManager;

import javax.servlet.ServletException;

import static io.undertow.servlet.Servlets.servlet;

/**
 * This class is responsible for the servlet server instantiation
 */
public final class HttpServerUtil {
    private static final String SERVLET_CONTEXT_PATH = "/uai-mock-server-gui";

    private HttpServerUtil() {
    }

    public static Undertow startHttpServer() {
        final Undertow httpServer;

        try {
            final PathHandler path = Handlers.path(Handlers.redirect(SERVLET_CONTEXT_PATH))
                    .addPrefixPath(SERVLET_CONTEXT_PATH, createHtmlManager())
                    .addPrefixPath("/", new UaiMockServerHandler());

            httpServer = Undertow.builder()
                    .addHttpListener(UaiMockServerContext.INSTANCE.uaiMockServerConfig.getPort(), UaiMockServerContext.INSTANCE.uaiMockServerConfig.getHost())
                    .setHandler(path)
                    .build();

            httpServer.start();
        } catch (final Exception ex) {
            throw new IllegalStateException("Could not start the uaiMockServer.", ex);
        }

        return httpServer;
    }

    private static HttpHandler createHtmlManager() throws ServletException {
        final DeploymentInfo deploymentInfo = Servlets.deployment()
                .setClassLoader(HttpServerUtil.class.getClassLoader())
                .setContextPath(SERVLET_CONTEXT_PATH)
                .setDeploymentName("uaiMockServer.war")
                .addServlets(
                        servlet("UaiIndexServlet", UaiIndexServlet.class).addMapping("/index"),
                        servlet("UaiPageServlet", UaiPageServlet.class).addMapping("/page"),
                        servlet("FaviconServlet", FaviconServlet.class).addMapping("/favicon"),
                        servlet("JavascriptServlet", JavascriptServlet.class).addMapping("/javascript"),
                        servlet("CssServlet", CssServlet.class).addMapping("/css"),
                        servlet("CssMapServlet", CssMapServlet.class).addMapping("/bootstrap.css.map"),
                        servlet("UaiRouteServlet", UaiRouteServlet.class).addMapping("/uaiRoute")
                );

        final DeploymentManager manager = Servlets.defaultContainer().addDeployment(deploymentInfo);
        manager.deploy();

        return manager.start();
    }
}