package com.qsp.shop.view;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;
import com.qsp.shop.controller.Controller;
import com.qsp.shop.model.Product;

public class Shopview1 {
	static Scanner myInput = new Scanner(System.in);
	static Product product = new Product();
	static Controller controller = new Controller();
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		do {
			System.out.println("Select operation to perform");
			System.out.println("1. Add product. \n2. Remove product. \n3 Update product details. \n4 Fetch product. \n0 Exited");
			System.out.print("Enter desired option :");
			int option = myInput.nextInt();
			myInput.nextLine();
			switch (option) {
			case 0:
				myInput.close();
				System.out.println("--------------------Exited--------------------");
				System.exit(0);
				break;
			case 1:
				System.out.println("To enter single product press 1 \nTo enter multiple products press 2");
				int product_to_enter = myInput.nextInt();
				myInput.nextLine();
				if (product_to_enter == 1) {
					System.out.print("Enter product id :");
					int i_p_id = myInput.nextInt();
					myInput.nextLine();
					
					System.out.print("Enter product name :");
					String i_p_name = myInput.nextLine();
					
					System.out.print("Enter product price :");
					int i_p_price = myInput.nextInt();
					myInput.nextLine();
					
					System.out.print("Enter product quantity :");
					int i_p_quantity = myInput.nextInt();
					myInput.nextLine();
					
					boolean i_p_availability = false;
					
					
					if (i_p_quantity > 0) {
						i_p_availability = true;
					}
					int values = controller.addProduct(i_p_id, i_p_name, i_p_price, i_p_quantity, i_p_availability);
					if (values != 0) {
						System.out.println("Product is Inserted");
					} else {
						System.out.println("Product is not Inserted");
					}
				} 
				else {
					boolean stop = true;
					
					ArrayList<Product> products_to_add = new ArrayList<Product>();
					while (stop){
						
						Product product = new Product();
						
						System.out.print("Enter product id :");
						int i_p_id = myInput.nextInt();
						myInput.nextLine();
						product.setP_id(i_p_id);
						
						System.out.print("Enter product name :");
						String i_p_name = myInput.nextLine();
						product.setP_name(i_p_name);
						
						System.out.print("Enter product price :");
						int i_p_price = myInput.nextInt();
						myInput.nextLine();
						product.setP_price(i_p_price);
						
						System.out.print("Enter product quantity :");
						int i_p_quantity = myInput.nextInt();
						myInput.nextLine();
						product.setP_quantity(i_p_quantity);
						
						boolean i_p_availability = false;
						
						
						if (i_p_quantity > 0) {
							i_p_availability = true;
						}
						product.setP_availability(i_p_availability);
						System.out.print("To enter more press 1 nahito 2");
						int want_to_continue = myInput.nextInt();
						myInput.nextLine();
						products_to_add.add(product);
						if(want_to_continue == 1) {
							stop = true;
						}
						else {
							stop = false;
						}
						
					}
					int values = controller.addProducts(products_to_add);
					if (values != 0) {
						System.out.println("Products are Inserted");
						System.out.println(values+"Products are added");
					} else {
						System.out.println("Products are not Inserted");
					}
				}
				break;
			case 2:
				System.out.println("To remove product enter id ");
				int i1_p_id = myInput.nextInt();
				myInput.nextLine();
				int product_removed = controller.remove_product_by_id(i1_p_id);
				System.out.println(product_removed+" no of products removed");
				break;
			case 3:
				System.out.println("To update product enter id ");
				int i2_p_id = myInput.nextInt();
				myInput.nextLine();
				ResultSet fetch_product_by_id = controller.fetch_product_by_id(i2_p_id);
				try {
					fetch_product_by_id.next();
					System.out.println("Product id :" + fetch_product_by_id.getInt(1));
					System.out.println("Product name :" + fetch_product_by_id.getString(2));
					System.out.println("Product price :" + fetch_product_by_id.getInt(3));
					System.out.println("Product quantity :" + fetch_product_by_id.getInt(4));
					System.out.println("Product availability :" + fetch_product_by_id.getBoolean(5));
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				Product product = new Product();
				product.setP_id(i2_p_id);
	
				
					
					System.out.println("To change name press YES else NO ");
					String choice = myInput.nextLine();
					if (choice.equals("YES")) {
						System.out.println("Enter name to change ");
						product.setP_name(myInput.nextLine());
					} 
					
					System.out.println("To change price press YES else NO ");
					choice = myInput.nextLine();
					
					if (choice.equals("YES")) {
						System.out.println("Enter price to change ");
						product.setP_price(myInput.nextInt());
						myInput.nextLine();
					} 
					System.out.println("To change quantity press YES else NO ");
					choice = myInput.nextLine();
					if (choice.equals("YES")) {
						System.out.println("Enter quantity to change ");
						product.setP_quantity(myInput.nextInt());
					} 
					myInput.nextLine();
					controller.update(product);
					ResultSet fetch_product_by_id1 = controller.fetch_product_by_id(i2_p_id);
				try {
					fetch_product_by_id1.next();
					System.out.println("Product id :" + fetch_product_by_id1.getInt(1));
					System.out.println("Product name :" + fetch_product_by_id1.getString(2));
					System.out.println("Product price :" + fetch_product_by_id1.getInt(3));
					System.out.println("Product quantity :" + fetch_product_by_id1.getInt(4));
					System.out.println("Product availability :" + fetch_product_by_id1.getBoolean(5));
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				break;
			case 4:
				
				System.out.println("To see all products press 1 \nTo see specific product press 2");
				int search = myInput.nextInt();
				myInput.nextLine();
				if (search == 1) {
					ResultSet resultSet1 = controller.fetch_product();
					System.out.println("p_id"+"\t"+"p_name"+"\t"+"p_price"+"\t"+"p_quantity"+"\t"+"p_availability");
					try {
						while (resultSet1.next()) {
							System.out.println(resultSet1.getInt(1)+"\t"+resultSet1.getString(2)+"\t"+resultSet1.getInt(3)+"\t"+resultSet1.getInt(4)+"\t"+resultSet1.getBoolean(5));
						}
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				} else {
					System.out.println("To search products by id press 1 \nTo search product by name press 2");
					int search_choice  = myInput.nextInt();
					myInput.nextLine();
					if (search_choice == 1) {
						System.out.print("Enter product id :");
						int i_p_id = myInput.nextInt();
						myInput.nextLine();
						ResultSet resultSet1 = controller.fetch_product_by_id(i_p_id);
						try {
							if (resultSet1.next()) {
								System.out.println("Product id : "+ resultSet1.getInt(1));
								System.out.println("Product name : "+ resultSet1.getString(2));
								System.out.println("Product price : "+ resultSet1.getInt(3));
								System.out.println("Product quantity : "+ resultSet1.getInt(4));
								if (resultSet1.getBoolean(5)) {
									System.out.println("Product Availability : Availabel  ");
								} else {
									System.out.println("Product Availability : Not Availabel ");
								}
							} 
							else {
								System.out.println("Product with id: "+i_p_id+" is no available");
							}
						} catch(SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
		
					} 
					else if (search_choice == 2){
						System.out.print("Enter product name :");
						String i_p_name = myInput.nextLine();
						ResultSet resultSet1 = controller.fetch_product_by_name(i_p_name);
						try {
							
							if (resultSet1.next()) {
								
									System.out.println("Product id : "+ resultSet1.getInt(1));
									System.out.println("Product name : "+ resultSet1.getString(2));
									System.out.println("Product price : "+ resultSet1.getInt(3));
									System.out.println("Product quantity : "+ resultSet1.getInt(4));
									if (resultSet1.getBoolean(5)) {
										System.out.println("Product Availability : Availabel  ");
									} else {
										System.out.println("Product Availability : Not Availabel ");
									}
									
	
							} else {
								System.out.println("Product with name "+i_p_name+" does not exist");
								
							}
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}

				}
				break;

			default:
				System.out.println("--------------------Invalid option--------------------");
				
				break;
			}
			
			
			
		} while (true);
	}

}
