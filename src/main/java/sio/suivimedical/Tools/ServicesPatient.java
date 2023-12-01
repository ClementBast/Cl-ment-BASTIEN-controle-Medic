package sio.suivimedical.Tools;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import sio.suivimedical.Entities.Consultation;
import sio.suivimedical.Entities.Medicament;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ServicesPatient {
    private Connection uneCnx;
    private PreparedStatement ps;
    private ResultSet rs;

    public ServicesPatient() {
        uneCnx = ConnexionBDD.getCnx();
    }

    public ObservableList<String> getAllPatient() throws SQLException {
        ObservableList<String> lesPatients = FXCollections.observableArrayList();
            ps = uneCnx.prepareStatement( "SELECT nomPatient FROM patient");
            rs = ps.executeQuery();

            while (rs.next()) {
                String nomPatient = rs.getString("nomPatient");
                lesPatients.add(nomPatient);
            }
        return lesPatients;
    }
}

