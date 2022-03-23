package me.okramt.prueba;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;

import org.h2.Driver;
import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Database {

    private static String connectionURL = null;
    public static void registerDatabase(File folder){
        connectionURL = "jdbc:h2:"+folder.getAbsolutePath()+"/data/itemsbase";

        try {
            Class.forName ("org.h2.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection(){
        Connection connection = null;
        if(connectionURL == null){
            Bukkit.getConsoleSender().sendMessage(ChatColor.RED+" -> "+connectionURL);
            return null;
        }
        try {
            connection = DriverManager.getConnection (connectionURL);
        } catch (SQLException e) {
            System.out.println(e);
            Bukkit.getConsoleSender().sendMessage(ChatColor.RED+"ERROR1");
        }
        return connection;
    }

    public static void initializeDatabase(){
        Connection con = getConnection();
        if(con == null) return;

        PreparedStatement preparedStatement;
        PreparedStatement preparedStatement2;
        try{
            preparedStatement
                    = con.prepareStatement("CREATE TABLE IF NOT EXISTS Evolution(id bigint auto_increment primary key, item_object varchar NOT NULL, upgraded varchar NOT NULL, money double);");
            preparedStatement2 = con.prepareStatement(
                    "CREATE TABLE IF NOT EXISTS Items(id_ce bigint NOT NULL, item varchar NOT NULL, FOREIGN KEY(id_ce) REFERENCES Evolution(id));");

            preparedStatement.execute();
            preparedStatement2.execute();
            con.close();
        }catch(SQLException e){
            Bukkit.getConsoleSender().sendMessage(ChatColor.RED+"ERROR2");
        }
    }
}
