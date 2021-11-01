module AppointmentsJavaFX {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires spring.boot.autoconfigure;
    requires spring.context;
    requires spring.core;
    requires spring.boot;
    requires spring.data.jpa;
    requires spring.beans;
    requires spring.jdbc;
    requires spring.orm;
    requires spring.expression;
    requires java.persistence;
    requires static lombok;
    requires spring.data.commons;
    requires org.hibernate.orm.core;
    requires org.hibernate.commons.annotations;

    opens com.csia.anish;

    exports com.csia.anish;
    exports com.csia.anish.data;
    opens com.csia.anish.data;
    exports com.csia.anish.controller;
    opens com.csia.anish.controller;
    exports com.csia.anish.repo;
    opens com.csia.anish.repo;
    exports com.csia.anish.service;
    opens com.csia.anish.service;

}
