package org.ciclo.model.connect;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "connection")
public class UserConnection implements Serializable {
    private String _bd_name;
    private String _bd_ip;
    private String _bd_port;
    private String _timezone;
    private String _user;
    private String _pass;


    public UserConnection() {
    }

    public String get_bd_name() {
        return _bd_name;
    }

    public void set_bd_name(String _bd_name) {
        this._bd_name = _bd_name;
    }

    public String get_bd_ip() {
        return _bd_ip;
    }

    public void set_bd_ip(String _bd_ip) {
        this._bd_ip = _bd_ip;
    }

    public String get_bd_port() {
        return _bd_port;
    }

    public void set_bd_port(String _bd_port) {
        this._bd_port = _bd_port;
    }

    public String get_user() {
        return _user;
    }

    public void set_user(String _user) {
        this._user = _user;
    }

    public String get_pass() {
        return _pass;
    }

    public void set_pass(String _pass) {
        this._pass = _pass;
    }

    public String get_timezone() {
        return _timezone;
    }

    public void set_timezone(String _timezone) {
        this._timezone = _timezone;
    }

    @Override
    public String toString() {
        return "UserConnection{" +
                "_bd_name='" + _bd_name + '\'' +
                ", _bd_ip='" + _bd_ip + '\'' +
                ", _bd_port='" + _bd_port + '\'' +
                ", _timezone='" + _timezone + '\'' +
                ", _user='" + _user + '\'' +
                ", _pass='" + _pass + '\'' +
                '}';
    }
}
