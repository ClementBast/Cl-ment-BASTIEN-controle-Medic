package sio.suivimedical.Tools;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ServicesMedecin {
    private Connection uneCnx;
    private PreparedStatement ps;
    private ResultSet rs;

    public ServicesMedecin() {
        uneCnx = ConnexionBDD.getCnx();
    }

    public ObservableList<String> getAllMedecin() throws SQLException {
        ObservableList<String> lesMedecins = FXCollections.observableArrayList();
            ps = uneCnx.prepareStatement("SELECT nomMedecin FROM medecin");
            rs = ps.executeQuery();

            while (rs.next()) {
                String nomMedecin = rs.getString("nomMedecin");
                lesMedecins.add(nomMedecin);
            }
        return lesMedecins;
    }
}
