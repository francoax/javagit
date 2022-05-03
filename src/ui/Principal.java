package ui;


import DB.jdbc;
import java.sql.*;
import java.util.InputMismatchException;
import java.util.LinkedList;
import java.util.Scanner;

public class Principal {

	public static void main(String[] args) throws IllegalAccessException, InstantiationException {
		
		
		
		
		jdbc db = new jdbc();
		
		Scanner reader = new Scanner(System.in);
		boolean out = false;
		
		while(!out) {
			
			System.out.println("1. Listar productos.");
			System.out.println("2. Buscar producto.");
			System.out.println("3. Cargar nuevo producto.");
			System.out.println("4. Eliminar producto por ID.");
			System.out.println("5. Modificar producto por ID.");
			System.out.println("0. Salir.");
			
			try {
				
				System.out.print("Opcion: ");
				int opcion = reader.nextInt();
				int idP = 0;
				
				switch (opcion) {
					
				case 1:
					db.listarProductos();
					break;
				case 2:
					System.out.println("Busqueda de producto.");
					System.out.print("Ingrese codigo de producto: ");
					idP = reader.nextInt();
					db.buscarProducto(idP);
					break;
				case 3:
					
					System.out.print("Nombre del producto: ");
					String name = reader.next();
					System.out.println();
					
					System.out.print("Descripcion del producto: ");
					String description = reader.next();
					System.out.println();
					
					System.out.print("Precio del producto: ");
					double price = reader.nextDouble();
					System.out.println();
					
					System.out.print("Stock del producto: ");
					int stock = reader.nextInt();
					System.out.println();
					
					System.out.print("Define si incluye envio. t (true) o f (false): ");
					String resp = reader.next();
					boolean shippingIncluded;
					if(resp.equals("t")) {
						 shippingIncluded = true;
					} else {
						 shippingIncluded = false;
					}
					System.out.println();
					
					db.cargarProducto(name, description, price, stock, shippingIncluded);
					break;
				case 4:
					System.out.println("ID producto a eliminar: ");
					idP = reader.nextInt();
					db.eliminarProducto(idP);
					break;
				case 5:
					db.modificarProducto();
					break;
				case 0:
					out = true;
					break;
				default:
					System.out.println("Solo del 0 a 5.");
				}
				
			} catch (InputMismatchException e) {
				System.out.println("Inserte un numero.");
				reader.next();
			}
		}
		
		reader.close();
		
		
	}

}
