package org.ciclo.model.connect;

import javax.xml.bind.*;
import java.io.*;

public class Connection {
    private static final String XML = "UserConf.xml";
    public static UserConnection CONNECTION_DATA = loadConfig();

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





}
