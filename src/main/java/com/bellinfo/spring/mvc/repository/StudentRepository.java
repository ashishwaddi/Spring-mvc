package com.bellinfo.spring.mvc.repository;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.springframework.stereotype.Component;

import com.bellinfo.spring.mvc.model.Student;

@Component
public class StudentRepository {

/*	public void persistStudentData(Student student) {
		System.out.println("persisted student data");
	}
}
*/


	Connection con = null;
	private static final String CREATE_STUDENT_STRUCTURE = "CREATE TABLE STUDENT(name varchar(10))";
	private static final String INSERT_STUDENT_RECORD = "INSERT INTO STUDENT VALUES(?)";

	public Connection getConnection() {
		try {
			Class.forName("org.postgresql.Driver");
			con = DriverManager.getConnection(
					"jdbc:postgresql://localhost:5433/postgres", "postgres",
					"postgres");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return con;
	}

	public void createStudentStructure() {
		Statement stmt = null;
		try {
			con = getConnection();
			stmt = con.createStatement();
			DatabaseMetaData dbm = con.getMetaData();
			ResultSet tables = dbm.getTables(null, null, "student", null);
			if (tables.next()) {
				System.out.println("Table already exsist..I'm skipping it");
			} else {
				boolean result = stmt.execute(CREATE_STUDENT_STRUCTURE);
				System.out.println("Table has been created successfully");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				stmt.close();
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	PreparedStatement ps = null;

	public int insertStudent(Student student) {
		con = getConnection();
		int result = 0;

		try {
			ps = con.prepareStatement(INSERT_STUDENT_RECORD);
			ps.setString(1, student.getName());
			result = ps.executeUpdate();
			System.out.println(result);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				ps.close();
				con.close();
			} catch (SQLException e) {

				e.printStackTrace();
			}

		}
		return result;

	}
	
}	
	
