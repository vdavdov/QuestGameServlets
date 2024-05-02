package com.javarush.by.vdavdov.util;

import liquibase.Scope;
import liquibase.command.CommandScope;
import liquibase.resource.ClassLoaderResourceAccessor;

public class LiquibaseRunner {
    public static void main(String[] args) throws Exception {
        System.out.println("Running Liquibase...");

        Scope.child(Scope.Attr.resourceAccessor, new ClassLoaderResourceAccessor(), () -> {
            CommandScope update = new CommandScope("update");

            update.addArgumentValue("changelogFile", "db/changelog.xml");
            update.addArgumentValue("url", "jdbc:postgresql://localhost:5432/quest_game");
            update.addArgumentValue("username", "postgres");
            update.addArgumentValue("password", "postgres");

            update.execute();
        });

        System.out.println("Running Liquibase...DONE");
    }
}