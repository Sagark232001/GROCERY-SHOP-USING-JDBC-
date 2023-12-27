package com.qsp.shop.controller;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Properties;

import org.postgresql.Driver;

import com.qsp.shop.model.Product;

public class Controller {
	public int addProduct(int id , String name, int price, int quantity, boolean availability) {
		Connection connection = null;
		int rowsAffected = 0;
		try {
			Driver driver = new Driver();
			DriverManager.registerDriver(driver);
			
			FileInputStream fileInputStream = new FileInputStream("dbconfig.properties");
			Properties properties = new Properties();
			properties.load(fileInputStream);
			connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/shop", properties);
			PreparedStatement prepareStatement = connection.prepareStatement("INSERT INTO product VALUES(?,?,?,?,?)");
			prepareStatement.setInt(1, id);
			prepareStatement.setString(2, name);
			prepareStatement.setInt(3, price);
			prepareStatement.setInt(4, quantity);
			prepareStatement.setBoolean(5, availability);
			
			rowsAffected = prepareStatement.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return rowsAffected;
	}
	public int addProducts(ArrayList<Product> products) {
		Connection connection = null;
		int rowsAffected = 0;
		try {
			Driver driver = new Driver();
			DriverManager.registerDriver(driver);
			
			FileInputStream fileInputStream = new FileInputStream("dbconfig.properties");
			Properties properties = new Properties();
			properties.load(fileInputStream);
			connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/shop", properties);
			PreparedStatement prepareStatement = connection.prepareStatement("INSERT INTO product VALUES(?,?,?,?,?)");
			
			for (Product product: products) {
				prepareStatement.setInt(1, product.getP_id());
				prepareStatement.setString(2, product.getP_name());
				prepareStatement.setInt(3, product.getP_price());
				prepareStatement.setInt(4, product.getP_quantity());
				prepareStatement.setBoolean(5, product.getP_availability());
				prepareStatement.addBatch();
				
			}
			int[] executeBatch = prepareStatement.executeBatch();
			rowsAffected = executeBatch.length;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return rowsAffected;
	}
	public ResultSet fetch_product() {
		Connection connection = null;
		ResultSet resultSet = null;
		try {
			Driver driver = new Driver();
			DriverManager.registerDriver(driver);
			
			FileInputStream fileInputStream = new FileInputStream("dbconfig.properties");
			Properties properties = new Properties();
			properties.load(fileInputStream);
			connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/shop", properties);
			Statement statement = connection.createStatement();
			resultSet = statement.executeQuery("SELECT * FROM product");
//			System.out.println("p_id"+"\t"+"p_name"+"\t"+"p_price"+"\t"+"p_quantity"+"\t"+"p_availability");
//			while (resultSet.next()) {
//				System.out.println(resultSet.getInt(1)+"\t"+resultSet.getString(2)+"\t"+resultSet.getInt(3)+"\t"+resultSet.getInt(4)+"\t"+resultSet.getBoolean(5));
//			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return resultSet;
	}

	public ResultSet fetch_product_by_id(int id) {
		Connection connection = null;
		ResultSet resultSet = null;
		try {
			Driver driver = new Driver();
			DriverManager.registerDriver(driver);
			
			FileInputStream fileInputStream = new FileInputStream("dbconfig.properties");
			Properties properties = new Properties();
			properties.load(fileInputStream);
			connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/shop", properties);
			PreparedStatement prepareStatement = connection.prepareStatement("select * from product where p_id = ?");
			prepareStatement.setInt(1, id);
			resultSet = prepareStatement.executeQuery();
//			System.out.println("p_id"+"\t"+"p_name"+"\t"+"p_price"+"\t"+"p_quantity"+"\t"+"p_availability");
//			while (resultSet.next()) {
//				System.out.println(resultSet.getInt(1)+"\t"+resultSet.getString(2)+"\t"+resultSet.getInt(3)+"\t"+resultSet.getInt(4)+"\t"+resultSet.getBoolean(5));
//			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return resultSet;
	}
	public ResultSet fetch_product_by_name(String name) {
		Connection connection = null;
		ResultSet resultSet = null;
		try {
			Driver driver = new Driver();
			DriverManager.registerDriver(driver);
			
			FileInputStream fileInputStream = new FileInputStream("dbconfig.properties");
			Properties properties = new Properties();
			properties.load(fileInputStream);
			connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/shop", properties);
			PreparedStatement prepareStatement = connection.prepareStatement("select * from product where p_name = ?");
			prepareStatement.setString(1, name);
			resultSet = prepareStatement.executeQuery();
//			System.out.println("p_id"+"\t"+"p_name"+"\t"+"p_price"+"\t"+"p_quantity"+"\t"+"p_availability");
//			while (resultSet.next()) {
//				System.out.println(resultSet.getInt(1)+"\t"+resultSet.getString(2)+"\t"+resultSet.getInt(3)+"\t"+resultSet.getInt(4)+"\t"+resultSet.getBoolean(5));
//			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return resultSet;
	}
	public int remove_product_by_id(int id) {
		Connection connection = null;
		int number_of_product_removed= 0;
		try {
			Driver driver = new Driver();
			DriverManager.registerDriver(driver);
			
			FileInputStream fileInputStream = new FileInputStream("dbconfig.properties");
			Properties properties = new Properties();
			properties.load(fileInputStream);
			connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/shop", properties);
			PreparedStatement prepareStatement = connection.prepareStatement("DELETE FROM product WHERE p_id = ?");
			prepareStatement.setInt(1, id);
			number_of_product_removed = prepareStatement.executeUpdate();
			} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return number_of_product_removed;
	}
	public int update_product_by_id(int id ,String name) {
		Connection connection = null;
		int number_of_product_updated= 0;
		try {
			Driver driver = new Driver();
			DriverManager.registerDriver(driver);
			
			FileInputStream fileInputStream = new FileInputStream("dbconfig.properties");
			Properties properties = new Properties();
			properties.load(fileInputStream);
			connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/shop", properties);
			PreparedStatement prepareStatement = connection.prepareStatement("UPDATE  product  SET p_name = ?  WHERE p_id = ?");
			prepareStatement.setString(1 , name);
			prepareStatement.setInt(2, id);
			number_of_product_updated = prepareStatement.executeUpdate();
			} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return number_of_product_updated;
	}
	public void update(Product product) {
		Connection connection = null;
		int rowsAffected = 0;
		try {
			Driver driver = new Driver();
			DriverManager.registerDriver(driver);
			
			FileInputStream fileInputStream = new FileInputStream("dbconfig.properties");
			Properties properties = new Properties();
			properties.load(fileInputStream);
			connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/shop", properties);
			PreparedStatement prepareStatement = connection.prepareStatement("UPDATE product SET p_name=?, p_price=? , p_quantity=? where p_id=?");
			prepareStatement.setString(1, product.getP_name());
			prepareStatement.setInt(2, product.getP_price());
			prepareStatement.setInt(3, product.getP_quantity());
			prepareStatement.setInt(4, product.getP_id());
			
			
			rowsAffected = prepareStatement.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
}
