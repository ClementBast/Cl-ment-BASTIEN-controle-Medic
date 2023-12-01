package sio.suivimedical.Tools;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ServicesPrescription
{
    private Connection uneCnx;
    private PreparedStatement ps;
    private ResultSet rs;

    public ServicesPrescription()
    {
        uneCnx = ConnexionBDD.getCnx();
    }

    // A vous de jouer

}
