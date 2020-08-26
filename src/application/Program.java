package application;

import java.sql.Connection;

import java.sql.SQLException;
import java.util.Date;

import com.mysql.jdbc.Statement;

import db.DB;
import db.DbException;
import model.entities.Department;
import model.entities.Seller;

public class Program {

	public static void main(String[] args) {

		Department department = new Department(1, "Books");
		Seller seller =  new Seller(21,"Bob","bob@gmail.com",new Date(), 3000.0,department);
		System.out.println(seller);
		
		
		/*Connection conn = null;
		Statement st = null;
		try {
			conn = DB.getConnection();
			conn.setAutoCommit(false);

			st = (Statement) conn.createStatement();
			int rows1 = st.executeUpdate("UPDATE seller SET BaseSalary = 2700 WHERE DepartmentId = 1");
			//boolean x = true;
			//if (x)
				//throw new SQLException("Fake Error");
			int rows2 = st.executeUpdate("UPDATE seller SET BaseSalary = 3700 WHERE DepartmentId = 2");
			conn.commit();

			System.out.println("rows1: " + rows1);
			System.out.println("rows2: " + rows2);

		} catch (SQLException e) {
			try {
				conn.rollback();
				throw new DbException("Transaction rolled back! Caused by: "+e.getMessage());
			} catch (SQLException e1) {
				throw new DbException("Error trying to rollback! Caused by: "+e1.getMessage());
			}
		} finally {
			DB.closeStatement(st);
			DB.closeConnection();
		}*/
	}
}
