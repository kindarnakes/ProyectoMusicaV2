package org.ciclo;

import org.ciclo.model.connect.Connection;



public class Run {
    public static void main(String[] args) {
        //MainApp.main(args);
        System.out.println(Connection.CONNECTION_DATA.toString());
        Connection.saveConfig();
    }
}
