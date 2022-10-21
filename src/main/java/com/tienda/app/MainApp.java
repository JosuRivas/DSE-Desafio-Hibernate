package com.tienda.app;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.swing.JOptionPane;

import com.tienda.model.Producto;

public class MainApp {

	public static void main(String[] args) {
		int opcion = 0;
		Producto producto;

		EntityManager entity = JPAUtil.getEntityManagerFactory().createEntityManager();
		while (opcion!=5) {
			
			opcion = Integer.parseInt(JOptionPane.showInputDialog(""
					+ "1. Ingresar Producto\n"
					+ "2. Buscar Producto\n"
					+ "3. Actualizar Producto\n"
					+ "4. Eliminar Producto\n"
					+ "5. Salir\n\n"
					+ "Elija una opcion: "));
			switch (opcion) {
			case 1:
				producto = new Producto();
				
				producto.setId(null);
				producto.setNombre(JOptionPane.showInputDialog("Nombre del producto: "));
				producto.setCategoria(JOptionPane.showInputDialog("Categoria del producto: "));
				producto.setDescripcion(JOptionPane.showInputDialog("Descripcion del producto: "));
				producto.setCantidad(Integer.parseInt(JOptionPane.showInputDialog("Cantidad del producto: ")));
				producto.setPrecio(Double.parseDouble(JOptionPane.showInputDialog("Precio del producto: ")));
	
				entity.getTransaction().begin();
				entity.persist(producto);
				entity.getTransaction().commit();
				JOptionPane.showMessageDialog(null, "Producto ingresado: "
						+ "Nombre: " + producto.getNombre() + "\n"
						+ "Categoria: " + producto.getCategoria() + "\n"
						+ "Descripcion: " + producto.getDescripcion() + "\n"
						+ "Cantidad: " + producto.getCantidad() + "\n"
						+ "Precio: " + producto.getPrecio() + "\n");
				break;

			case 2:
				producto = new Producto();
				
				producto = entity.find(Producto.class, Long.parseLong(JOptionPane.showInputDialog("Digite el ID del producto a buscar:")));
				if (producto != null) {
					JOptionPane.showMessageDialog(null, "Producto encontrado: "
							+ "Nombre: " + producto.getNombre() + "\n"
							+ "Categoria: " + producto.getCategoria() + "\n"
							+ "Descripcion: " + producto.getDescripcion() + "\n"
							+ "Cantidad: " + producto.getCantidad() + "\n"
							+ "Precio: " + producto.getPrecio() + "\n");
					
				} else {
					JOptionPane.showMessageDialog(null, "Producto no econtrado");
				}

				break;
			case 3:
				producto = new Producto();

				producto = entity.find(Producto.class, Long.parseLong(JOptionPane.showInputDialog("Digite el ID del producto que desea actualizar:")));
				if (producto != null) {
					JOptionPane.showMessageDialog(null, "Producto encontrado: " + producto.getNombre());
					
					producto.setNombre(JOptionPane.showInputDialog("Nombre actual: " + producto.getNombre() + "\n\n"
							+ "Nueva nombre: "));
					
					producto.setCategoria(JOptionPane.showInputDialog("Categoría actual: " + producto.getCategoria() + "\n\n"
							+ "Nueva categoria: "));
					
					producto.setDescripcion(JOptionPane.showInputDialog("Descripción actual: " + producto.getDescripcion() + "\n\n"
							+ "Nueva descripcion: "));
					
					producto.setCantidad(Integer.parseInt(JOptionPane.showInputDialog("Cantidad de producto actual: " + producto.getCantidad() + "\n\n"
							+ "Nueva cantidad: ")));
					
					producto.setPrecio(Double.parseDouble(JOptionPane.showInputDialog("Precio actual: " + producto.getPrecio() + "\n\n"
							+ "Nuevo precio: ")));
					
					entity.getTransaction().begin();
					entity.merge(producto);
					entity.getTransaction().commit();
					
					JOptionPane.showMessageDialog(null, "Producto actualizado");
				} else {
					JOptionPane.showMessageDialog(null, "Producto no encontrado");
				}
				break;
			case 4:
				producto = new Producto();

				producto = entity.find(Producto.class, Long.parseLong(JOptionPane.showInputDialog("Ingrese el ID del producto que desea eliminar: ")));
				if (producto != null) {
					JOptionPane.showMessageDialog(null, "Producto encontrado: " + producto.getNombre());
					entity.getTransaction().begin();
					entity.remove(producto);
					entity.getTransaction().commit();
					JOptionPane.showMessageDialog(null, "Producto eliminado");
				} else {
					JOptionPane.showMessageDialog(null, "Producto no encontrado");
				}
				break;
			case 5:entity.close();JPAUtil.shutdown();
			break;

			default:
				JOptionPane.showMessageDialog(null, "Esa opcion no es valida");
				break;
			}
		}
	}

}
