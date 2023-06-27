module com.project.chatfx {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires spring.boot.autoconfigure;
    requires spring.boot;
    requires spring.context;
    requires spring.web;
    requires spring.beans;
    requires com.fasterxml.jackson.annotation;
    requires org.jetbrains.annotations;

    opens com.project.clients to javafx.fxml;
    exports com.project.clients;
    exports com.project.services;
    opens com.project.services to javafx.fxml;
    exports com.project.servers;
    opens com.project.servers to javafx.fxml;
}