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
					System.out.println(db.buscarProducto(idP));
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
					modificarProducto();
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
	
	
	public static void modificarProducto() {
		
		jdbc db = new jdbc();
		
		Scanner reader = new Scanner(System.in);
		
		System.out.print("ID de producto a modificar: ");
		int id = reader.nextInt();
		System.out.println();
		
		System.out.println("Producto a modificar: \n"+db.buscarProducto(id));
		System.out.println();
		System.out.println("Cambiar: \n 1-nombre y descripcion \n 2-precio \n 3-envio \n 0-salir");
		int opc = reader.nextInt();
		
		while(opc!=0) {
			
			switch (opc) {
			
				case 1: {
					System.out.print("Nombre nuevo: ");
					String nombre = reader.nextLine();
					System.out.print("Nueva descripcion: ");
					String desc = reader.nextLine();
					db.modificarProducto(nombre, desc);
					break;
				}
				case 2 : {
					System.out.print("Nuevo precio: ");
					float precio = reader.nextFloat();
					db.modificarProducto(precio);
					break;
				}
				case 3: {
					System.out.print("Definir envio: t (true) o f (false): ");
					String shipp = reader.nextLine();
					boolean shippingIncluded;
					if(shipp.equals("t")) {
						 shippingIncluded = true;
					} else {
						 shippingIncluded = false;
					}
					db.modificarProducto(shippingIncluded);
					break;
				
				}
			
			}
			
		}
		
	}
	
	

}
