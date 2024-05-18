package com.school.sample;

import javafx.scene.control.Alert;

import java.sql.*;

public class MyConnection {
    private static String driverJDBC = "com.mysql.cj.jdbc.Driver";
    private static String User = "root";
    private static String Password = "root";
    private static String URL = "jdbc:mysql://localhost:3306/lojadb";
    private static Connection conn = null;

    public static Connection openDB(){
        try{
            //Driver do MySQL
            Class.forName(driverJDBC);
            //Conexão ao sistema de gestão de base de dados MySQL
            //Ligação à base de dados agendadb
            conn = DriverManager.getConnection(URL, User, Password);
            /*if (conn != null){
                System.out.println("Ligação à base de dados...OK");
            }*/
        }
        catch (ClassNotFoundException | SQLException ex){
            //System.out.println("Erro na ligação à base de dados: "+ex);
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erro");
            alert.setHeaderText("Erro na ligação à base de dados: "+ex);
            alert.showAndWait();
        }
        return conn;
    }
    public static void closeDB(){
        try{
            if(conn != null){
                conn.close();
            }
        }
        catch(SQLException ex){
            System.out.println("Erro ao fechar a ligação à base de dados: "+ex);
            //FXAlert.showException(ex);
        }
    }

    public static void closeDB(PreparedStatement stmt){
        try{
            if(stmt != null){
                stmt.close();
            }
        }
        catch(SQLException ex){
            System.out.println("Erro ao fechar a ligação à base de dados: "+ex);
        }
        finally {
            closeDB();
        }
    }
    public static void closeDB(PreparedStatement stmt, ResultSet rs){
        try{
            if(rs != null){
                rs.close();
            }
        }
        catch(SQLException ex){
            System.out.println("Erro ao fechar a ligação à base de dados: "+ex);
        }
        finally {
            closeDB(stmt);
        }
    }
    public Connection getConn(){
        String loginUser = "";
        String loginPass = "";
        try{
            conn = DriverManager.getConnection(URL, loginUser, loginPass);

        }catch (Exception e){
            e.printStackTrace();

        }
        return conn;
    }



}

