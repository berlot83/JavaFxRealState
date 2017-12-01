package com.costa.controllers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.Instant;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;
import com.costa.dao.IConnection;
import com.costa.dao.ITransactions;
import com.costa.entities.Family;
import com.costa.entities.House;
import java.sql.Statement;

public class HouseFactory implements ITransactions<House, Family>, IConnection {

	Connection connection = null;

	public boolean insertRepair(String name, String action, String description) {

		boolean inserted = false;

		try {
			connection = getConnection();
			String sql = "INSERT INTO repair(name, action, description) VALUES(?,?,?)";
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setString(1, name);
			ps.setString(2, action);
			ps.setString(3, description);
			ps.execute();

		} catch (Exception error) {
			System.out.println("Cannot even connect");
		}
		return inserted;
	}

	public boolean insert(String lastname, String address, String telephone, String cbu, int quantity,
			LocalDate fromDate, LocalDate toDate, double reservation, String idHouse) {

		boolean inserted = false;

		Period lapse = Period.between(fromDate, toDate);
		String lapseResult = "Años: "+lapse.getYears()+", Meses: "+lapse.getMonths()+", Días: "+lapse.getDays();
		
		try {
			connection = getConnection();
			String sql = "INSERT INTO families(lastname, address, telephone, cbu, quantity, fromDate, toDate, lapse, reservation, id_house) VALUES(?,?,?,?,?,?,?,?,?,?)";
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setString(1, lastname);
			ps.setString(2, address);
			ps.setString(3, telephone);
			ps.setString(4, cbu);
			ps.setInt(5, quantity);
			ps.setString(6, fromDate.toString());
			ps.setString(7, toDate.toString());
			ps.setString(8, lapseResult);
			ps.setDouble(9, reservation);
			ps.setString(10, idHouse);
			ps.execute();
			ps.close();
			closeConnection();
			inserted = true;

		} catch (Exception error) {
			error.printStackTrace();
			System.out.println("Cannot even connect");
		}
		return inserted;
	}

	public static void main(String[] args) {

		HouseFactory hf = new HouseFactory();
		hf.getAllRepair();
		
		LocalDate l1 = LocalDate.of(2017, 10, 30);
		LocalDate l2 = LocalDate.of(2017, 12 , 10);
		Period p = Period.between(l1, l2);
		System.out.println(p.getDays());
		System.out.println(p.getYears()+", "+p.getMonths()+", "+p.getDays());

	}

	public List<Family> getAllFamilies() {
		List<Family> list = new ArrayList<>();
		Connection connection = null;
		try {
			connection = getConnection();

			Statement st;
			String sql = "SELECT id, lastname, address, telephone, cbu, quantity, fromDate, toDate, lapse, reservation, id_house FROM families";
			st = connection.createStatement();
			ResultSet rs = st.executeQuery(sql);

			while (rs.next()) {
				list.add(new Family(rs.getInt("id"), rs.getString("lastname"), rs.getString("address"),
						rs.getString("telephone"), rs.getString("cbu"), rs.getInt("quantity"), rs.getString("fromDate"),
						rs.getString("toDate"), rs.getString("lapse"), rs.getDouble("reservation"), rs.getString("id_house")));
			}
		} catch (Exception error) {
			error.printStackTrace();
		}
		return list;
	}

	public List<House> getAllRepair() {
		List<House> list = new ArrayList<>();
		Connection connection = null;
		try {
			connection = getConnection();

			Statement st;
			String sql = "SELECT id, name, action, description FROM repair";
			st = connection.createStatement();
			ResultSet rs = st.executeQuery(sql);

			while (rs.next()) {
				list.add(new House(rs.getInt("id"), rs.getString("name"), rs.getString("action"),
						rs.getString("description")));
			}
		} catch (Exception error) {
			error.printStackTrace();
		}
		return list;
	}

	public boolean deleteFamily(int id) {
		boolean deleted = false;
		Connection connection = null;
		try {
			connection = getConnection();
			PreparedStatement ps;
			String sql = "DELETE FROM families WHERE id=?";
			ps = connection.prepareStatement(sql);
			ps.setInt(1, id);
			ps.execute();
			deleted = true;

		} catch (Exception error) {
			error.printStackTrace();
		}
		return deleted;
	}

	public boolean deleteRepair(int id) {
		boolean deleted = false;
		Connection connection = null;
		try {
			connection = getConnection();
			PreparedStatement ps;
			String sql = "DELETE FROM repair WHERE id=?";
			ps = connection.prepareStatement(sql);
			ps.setInt(1, id);
			ps.execute();
			deleted = true;

		} catch (Exception error) {
			error.printStackTrace();
		}
		return deleted;
	}

}
