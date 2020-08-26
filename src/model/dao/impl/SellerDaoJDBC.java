package model.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

import db.DB;
import db.DbException;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;

public class SellerDaoJDBC implements SellerDao{

	public Connection conn;
	
    public SellerDaoJDBC(Connection conn) {
		this.conn=conn;
	}
	@Override
	public void insert(Seller obj) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(Seller obj) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Seller obj) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Seller findById(Integer id) {
		PreparedStatement st=null;
		ResultSet rs=null;
		try {
			st=(PreparedStatement) conn.prepareStatement("SELECT seller.*,department.Name as DepName "
					+"FROM seller INNER JOIN department "
					+"ON seller.DepartmentId = department.Id "
					+"WHERE seller.Id = ?");
			st.setInt(1, id);
			rs=st.executeQuery();
			if(rs.next()) {
				Department dep=instantieteDepartment(rs);
				Seller obj=instantienteSeller(rs,dep); 	
				return obj;
			}
			return null;
		}catch (SQLException e) {
			throw new DbException(e.getMessage());
		}
		finally {
			DB.closeStatement(st);
			DB.closeResultSet(rs);
		}
		
	}

	private Seller instantienteSeller(ResultSet rs, Department dep) throws SQLException {
		Seller obj=new Seller();
		obj.setId(rs.getInt("Id"));
		obj.setName(rs.getString("Name"));
		obj.setEmail(rs.getString("Email"));
		obj.setBirthDate(rs.getDate("BirthDate"));
		obj.setBaseSalary(rs.getDouble("BaseSalary"));
		obj.setDepartment(dep);
		return obj;
	}
	private Department instantieteDepartment(ResultSet rs) throws SQLException {
		Department dep=new Department();
		dep.setId(rs.getInt("DepartmentId"));
		dep.setName(rs.getString("DepName"));
		return dep;
	}
	@Override
	public List<Seller> findAll() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public List<Seller> findByDepartment(Department department) {
		PreparedStatement st=null;
		ResultSet rs=null;
		try {
			st=(PreparedStatement) conn.prepareStatement("SELECT seller.*,department.Name as DepName " 
					+"FROM seller INNER JOIN department ON seller.DepartmentId = department.Id "
					+"WHERE DepartmentId = ? ORDER BY Name;");
			st.setInt(1, department.getId());
			rs=st.executeQuery();
			
			List<Seller> list = new ArrayList<>();
			Map<Integer, Department> map=new HashMap<>();
			
			while(rs.next()) {
				
				Department dep=map.get(rs.getInt("DepartmentId"));
				if(dep==null) {
					dep=instantieteDepartment(rs);
					map.put(rs.getInt("DepartmentId"), dep);
				}
				Seller obj=instantienteSeller(rs,dep);
				list.add(obj);
				
			}
			return list;
		}catch (SQLException e) {
			throw new DbException(e.getMessage());
		}
		finally {
			DB.closeStatement(st);
			DB.closeResultSet(rs);
		}
		
	}

}
