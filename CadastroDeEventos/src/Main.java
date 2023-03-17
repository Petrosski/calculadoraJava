import jdk.dynalink.linker.ConversionComparator;

import javax.swing.*;
import java.sql.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        final String URL = "jdbc:mysql://localhost:3306/cadeventos";
        final String USER = "root";
        final String PASSWORD = "root";
        final String CONSULTA = "select * from evento";

        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Conectado");

            Statement st = con.createStatement();
            //System.out.println("Statement criado");

            String query = "insert into evento (nome, local, data, qtdparticipantes) values (?,?,?,?)";

            PreparedStatement stmt = con.prepareStatement(query);

            String nome = JOptionPane.showInputDialog("Digite o nome do evento:");
            String local = JOptionPane.showInputDialog("Insira o local:");
            String data = JOptionPane.showInputDialog("Insira a data:");
            String qtdparticipantes = JOptionPane.showInputDialog("Insira a quantidade de participantes:");


            stmt.setString(1,nome);
            stmt.setString(2,local);
            stmt.setString(3,data);
            stmt.setString(4,qtdparticipantes);

            int linhasAfetadas = stmt.executeUpdate();

            System.out.println("Sucesso na excecução");

            ResultSet resultSet = stmt.executeQuery(CONSULTA);

            while (resultSet.next()){
                System.out.println(resultSet.getString("nome"));
                System.out.println(resultSet.getString("local"));
                System.out.println(resultSet.getString("data"));
                System.out.println(resultSet.getString("qtdparticipantes"));

            }

        } catch (Exception e){
            System.out.println(e);
            e.printStackTrace();
        }
    }
}