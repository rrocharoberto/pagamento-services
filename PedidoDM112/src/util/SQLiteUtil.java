package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class SQLiteUtil {

	private static final String DB_FILE_NAME = "/home/aluno/pagamento.sqlite";
	
	public static void main(String[] args) {
		try {
			new SQLiteUtil().performCreateTable();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void performCreateTable() throws Exception {

		// Loading the SQLite JDBC driver for the current thread
		Class.forName("org.sqlite.JDBC");

		// Handler for the database connection
		Connection connection = null;

		try {
			String script = "CREATE TABLE Pedido ( " 
					+ "    numero INTEGER NOT NULL, " 
					+ "	   cpf VARCHAR(14) NOT NULL, "
					+ "	   valor NUMERIC NOT NULL, " 
					+ "    status INTEGER NOT NULL, " 
					+ "    dataPedido DATE NOT NULL, "
					+ "    dataEmissao DATE NULL, " 
					+ "    dataPagamento DATE NULL, "
					+ "    CONSTRAINT Pedido_pk PRIMARY KEY (numero) " 
					+ " ); ";

			// Opening database connection
			// TODO: cria o arquivo do banco no home do usuário
			connection = DriverManager.getConnection("jdbc:sqlite:" + DB_FILE_NAME);
			Statement statement = connection.createStatement();

			// Creating database table
			// statement.executeUpdate("DROP TABLE IF EXISTS product");
			statement.executeUpdate(script);
			System.out.println("Criou com sucesso o banco de pedidos (sqlite).");

			// // Adding contents into the database statement
			// statement.executeUpdate("INSERT INTO product VALUES(1, 'Product 1')");
			// statement.executeUpdate("INSERT INTO product VALUES(2, 'Product 2')");
			//
			// // Querying the contents of the database using native SQLite query
			// ResultSet results = statement.executeQuery("SELECT * FROM product");
			//
			// System.out.println("List of products");
			//
			// while (results.next()) {
			// 	System.out.println(results.getString("name") + " (id=" + results.getInt("id") + ")");
			// }
		} catch (SQLException e) {
			
			System.err.println("Erro criando o banco de pedidos (sqlite): " + e.getMessage());
		} finally {
			try {
				// Closing database connection
				if (connection != null)
					connection.close();
			} catch (SQLException e) {

				System.err.println(e);
			}
		}
	}

}
