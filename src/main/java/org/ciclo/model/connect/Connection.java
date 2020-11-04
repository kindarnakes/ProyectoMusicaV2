package org.ciclo.model.connect;

import javax.xml.bind.*;
import java.io.*;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connection {
    private static final String XML = "UserConf.xml";
    public static UserConnection CONNECTION_DATA = loadConfig();
    public static String error;

    public static UserConnection loadConfig() {
        UserConnection user = null;
        try {
            FileReader fr = new FileReader(XML);
            BufferedReader bfw = new BufferedReader(fr);

            JAXBContext context = JAXBContext.newInstance(UserConnection.class);
            Unmarshaller unm = context.createUnmarshaller();

            user = (UserConnection) unm.unmarshal(bfw);


        } catch (FileNotFoundException | JAXBException e) {
            user = new UserConnection();
            user.set_bd_ip("localhost");
            user.set_bd_name("bd_musica");
            user.set_timezone("?useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=Europe/Madrid");
            user.set_bd_port("3306");
            user.set_pass("");
            user.set_user("root");
        }
        return user;
    }

    public static void saveConfig(){

        try{
        FileWriter fw = new FileWriter(XML);

        JAXBContext context = JAXBContext.newInstance(UserConnection.class);
        Marshaller m = context.createMarshaller();
        m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        m.marshal(CONNECTION_DATA, fw);

            fw.close();
        } catch (IOException | JAXBException e) {
            e.printStackTrace();
        }
    }

    static java.sql.Connection getConnect() {

        java.sql.Connection conn = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://" + CONNECTION_DATA.get_bd_ip() + ":" + CONNECTION_DATA.get_bd_port()
                    + "/" + CONNECTION_DATA.get_bd_name() + CONNECTION_DATA.get_timezone(), CONNECTION_DATA.get_user(), CONNECTION_DATA.get_pass());
            return conn;
        } catch (SQLException ex) {
            error = "No se ha podido conectar a la Base de Datos: " + ex;
        } catch (ClassNotFoundException ex) {
            error = "No se ha podido iniciar el conector: " + ex;
        }

        return conn;
    }
}
