package br.unipar;

import java.sql.*;

public class Main {

    public static final String url = "jdbc:postgresql://localhost:5432/Exemplo1";

    public static final String user = "postgres";

    public static final String password = "admin123";

    public static void main(String[] args) {

        criarTabelaUsuario();

        inserirUsuario("Taffe", "123456","fabio", "1980-01-01");

    }

    public static Connection connection() throws SQLException {

        return DriverManager.getConnection(url, user, password);

    }

    public static void criarTabelaUsuario() {
        try {
            Connection conn = connection();

            Statement statement = conn.createStatement();
            String sql = " CREATE TABLE IF NOT EXISTS usuarios ("
                    + "CODIGO SERIAL PRIMARY KEY,"
                    + "username VARCHAR(50) UNIQUE NOT NULL,"
                    + "password VARCHAR(300)NOT NULL,"
                    + "nome VARCHAR(50) NOT NULL,"
                    + "nascimento DATE)";

            statement.execute(sql);

            System.out.println("tabela criada");


        } catch (SQLException exception) {
            exception.printStackTrace();


        }
    }

    public static void inserirUsuario(String username, String password, String nome, String dataNancimento) {


        try {
            Connection conn = connection();

            PreparedStatement preparedStatement = conn.prepareStatement(
                    "Insert into usuarios (username, password, nome, nascimento) " +
                            " VALUES (?,?,?,?)"
            );

            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            preparedStatement.setString(3, nome);
            preparedStatement.setDate(4, java.sql.Date.valueOf(dataNancimento));

            preparedStatement.executeUpdate();

            System.out.println("Usuario inserido");


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }


}
