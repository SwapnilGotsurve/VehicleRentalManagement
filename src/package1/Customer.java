package package1;

public class Customer {
	private int customerId;
	private String name;
	private String phone;
	
	//constructor
	public Customer(int customerId,String name,String phone) {
		this.customerId = customerId;
		this.name =  name;
		this.phone = phone;
	}
		//getter
		public int getCustomerId() {
			return customerId;
		}
		public String getName() {
			return name;
		}
		public String getPhone() {
			return phone;
		}
		//setter
		public void setName(String name) {
			this.name = name;
		
		}
		public void setPhone(String phone) {
			this.phone = phone;
		}
		
		// Display customer details
	    public void displayCustomer() {
	        System.out.println("Customer ID: " + customerId);
	        System.out.println("Name: " + name);
	        System.out.println("Phone: " + phone +"\n");
	    }
		
	
}
