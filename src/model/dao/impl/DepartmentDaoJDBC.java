package model.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;

import db.DB;
import db.DbException;
import model.dao.DepartmentDao;
import model.entities.Department;

public class DepartmentDaoJDBC implements DepartmentDao{

	Connection conn;
	
	public DepartmentDaoJDBC(Connection conn) {
		this.conn=conn;
	}
	
	@Override
	public void insert(Department obj) {
		PreparedStatement st=null;
		try {
			st=(PreparedStatement) conn.prepareStatement("INSERT INTO department (Name) value (?)",Statement.RETURN_GENERATED_KEYS);
			st.setString(1, obj.getName());
			int rowsAffected = st.executeUpdate();
			if(rowsAffected>0) {
				ResultSet rs = st.getGeneratedKeys();
				if(rs.next()) {
					int id = rs.getInt(1);
					obj.setId(id);
				}
				DB.closeResultSet(rs);
			}else {
				throw new DbException("Unexpected error! No rows affected!");
			}
			
			
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		}finally {
			DB.closeStatement(st);
		}
		
	}

	@Override
	public void update(Department obj) {
		PreparedStatement st = null;
		try {
			st=(PreparedStatement) conn.prepareStatement("UPDATE department SET Name = ? WHERE Id= ?");
			st.setString(1, obj.getName());
			st.setInt(2, obj.getId());
			st.executeUpdate();
			
		}catch (SQLException e) {
			throw new DbException(e.getMessage());
		}finally {
			DB.closeStatement(st);
		}
		
	}



	@Override
	public Department findById(Integer id) {
		Department obj=null;
		PreparedStatement st=null;
		ResultSet rs = null;
		try {
		st=(PreparedStatement) conn.prepareStatement("SELECT * FROM department WHERE Id = ? ");
		st.setInt(1, id);
		rs=st.executeQuery();
		if(rs.next()) {
			obj=instantiateDepartment(rs);
			return obj;
		}
			
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		}finally {
			DB.closeStatement(st);
		}
		return obj;
	}

	
	@Override
	public List<Department> findAll() {
		Department obj=null;
		PreparedStatement st=null;
		ResultSet rs = null;
		try {
		st=(PreparedStatement) conn.prepareStatement("SELECT * FROM department");	
		rs=st.executeQuery();
		List<Department> list = new ArrayList<>();
		while(rs.next()) {
			obj=instantiateDepartment(rs);
			list.add(obj);
			
		}
		return list;
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		}finally {
			DB.closeStatement(st);
		}
		
	}

	@Override
	public void delete(Integer id) {
		PreparedStatement st=null;
		try {
			st=(PreparedStatement) conn.prepareStatement("DELETE from department WHERE Id = ? ");
			st.setInt(1, id);
			st.executeQuery();
			
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		}finally {
			DB.closeStatement(st);
		}
		
	}
	
	private Department instantiateDepartment(ResultSet rs) throws SQLException {
		Department dep = new Department();
		dep.setId(rs.getInt("Id"));
		dep.setName(rs.getString("Name"));
		return dep;
	}


}
