package Repositories;


import Clasa.utilizator;
import utils.DBConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class UtilizatorRepository {
    public static void save(utilizator utilizator) throws SQLException {
        String sql="INSERT INTO utilizator(nume,prenume,username,parola) VALUES (?,?,?,?)";
        PreparedStatement preparedStatement= DBConnection.getConnection().prepareStatement(sql);
        preparedStatement.setString(1,utilizator.getNume());
        preparedStatement.setString(2,utilizator.getPrenume());
        preparedStatement.setString(3,utilizator.getUsername());
        preparedStatement.setString(4,utilizator.getParola());
        preparedStatement.execute();
    }
    public static utilizator findByUsernameAndPassword(String username,String parola) throws SQLException {
        Statement statement = DBConnection.getConnection().createStatement();
        String sql = "SELECT * FROM utilizator WHERE username like '" + username + "' AND parola like '"+parola+"'";

        ResultSet resultSet = statement.executeQuery(sql);

        if (resultSet.next()) {
            utilizator utilizator = new utilizator(
                    resultSet.getInt("id_utilizator"),
                    resultSet.getString("nume"),
                    resultSet.getString("prenume"),
                    resultSet.getString("username"),
                    resultSet.getString("parola")
            );
            return utilizator;
        } else
            return null;
    }
}
