package com.dev.vkbot;

import io.undertow.Undertow;
import io.undertow.servlet.Servlets;
import io.undertow.servlet.api.DeploymentInfo;
import io.undertow.servlet.core.*;
import io.undertow.servlet.handlers.*;
import io.undertow.servlet.
public class Main {
    public static void main(String[] args) {
        System.out.println("Hello fucking world!");

        DeploymentInfo servletBuilder = Servlets.deployment();
        servletBuilder.setClassLoader(Main.class.getClassLoader());
        servletBuilder.setContextPath("/vkbot");
        servletBuilder.setDeploymentName("test.war");
        servletBuilder.addServlets(
                Servlets.servlet("MsgSerlvet", Servlet.class)
        );
    }
}
