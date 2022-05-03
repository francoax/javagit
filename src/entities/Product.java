package entities;

public class Product {
	
	
	private int idProduct;
	private String name;
	private String description;
	private double price;
	private int stock;
	private boolean shippingIncluded;
	
	public int getIdProduct() {
		return idProduct;
	}
	public void setIdProduct(int idProduct) {
		this.idProduct = idProduct;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public int getStock() {
		return stock;
	}
	public void setStock(int stock) {
		this.stock = stock;
	}
	public boolean isShippingIncluded() {
		return shippingIncluded;
	}
	public void setShippingIncluded(boolean shippingIncluded) {
		this.shippingIncluded = shippingIncluded;
	}
	
	@Override
	public String toString() {
		
		return "\nProduct [idProduct="+ idProduct + ", nombre="+ name +", descripcion="+ description +", precio="+ price +", stock="+ stock + ", shippingIncluded="+ shippingIncluded;
	}
	

}
