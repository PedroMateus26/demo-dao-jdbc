package application;

import java.util.List;
import java.sql.Connection;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import com.mysql.jdbc.Statement;

import db.DB;
import db.DbException;
import model.dao.DaoFactory;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;

public class Program {

	public static void main(String[] args) {
		SellerDao sellerDao = DaoFactory.createSellerDao();
		System.out.println("===TEST 1: Seller find by id===");
		Seller seller =  sellerDao.findById(3);
		System.out.println(seller);
		
		System.out.println("===TEST 2: Seller find by department===");
		Department dep=new Department(2, null);
		List<Seller> list=sellerDao.findByDepartment(dep);
		for(Seller obj:list) {
			System.out.println(obj);
		}
			
		
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
