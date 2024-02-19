package com.dev.vkbot;

import io.undertow.servlet.Servlets;
import io.undertow.servlet.api.DeploymentInfo;
import io.undertow.servlet.core.ErrorPages;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello fucking world!");

        DeploymentInfo servletBuilder = Servlets.deployment();
        servletBuilder.addWelcomePage("./index.jsp");

    }
}
