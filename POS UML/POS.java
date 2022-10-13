public class TestPOS {
	public static void main(String[] args) {
		// POST 객체를 준비
		Store store = new Store();
		ProductCatalog catalog = new ProductCatalog();
		catalog.addSpec(1, new ProductSpec(1, "pencil", 1000));
		catalog.addSpec(2, new ProductSpec(2, "eraser", 500));
		catalog.addSpec(3, new ProductSpec(3, "fountain pen", 50000));
		POST post = new POST(store, catalog);
		
		// 첫 번째 판매
		post.enterItem(1, 12);
		post.enterItem(2, 4);
		post.enterItem(3, 1);
		
		post.makePayment();
		
		post.endSale();
		
		// 두 번째 판매
		post.enterItem(1, 2);
		post.enterItem(2, 1);
		
		post.makePayment();
		
		post.endSale();
		
		// 출력을 보여주어 이해를 돕기위한 코드
		for (Sale sale : store.completedSales) {
			System.out.println(sale.getDate());
			sale.printLineItems();
			System.out.println("total = " + sale.getTotal());
		}
	}
}
-----------------------------------------------------------
import java.util.Date;
public class POST {
	private Store store;
	private ProductCatalog catalog;
	private Sale sale = null;
	
	public POST(Store store, ProductCatalog catalog) {
		this.store = store;
		this.catalog = catalog;
	}
	
	public void enterItem(int upc, int qty) {
		if (sale == null) {
			Date date = new Date(System.currentTimeMillis());
			sale = new Sale(date);
		}
		ProductSpec s = catalog.spec(upc);
		sale.makeLineItem(s, qty);
	}
	
	public void makePayment() {
		if (sale != null) sale.makePayment();
	}
	
	public void endSale() {
		store.addCompleteSale(sale);
		sale = null;
	}
}
-----------------------------------------------------------
import java.util.ArrayList;
public class Store {
	protected ArrayList<Sale> completedSales = null;
	
	public Store() {
		completedSales = new ArrayList<Sale>();
	}
	
	public void addCompleteSale(Sale sale) {
		completedSales.add(sale);
	}
}
-----------------------------------------------------------
import java.util.ArrayList;
import java.util.Date;
public class Sale {
	private Date date;
	private ArrayList<SalesLineItem> lineItem = null;
	private Payment payment = null;
	
	public Sale(Date date)
	{
		this.date = date;
		lineItem = new ArrayList<SalesLineItem>();
	}
	
	public void makeLineItem(ProductSpec s, int qty) {
		SalesLineItem item = new SalesLineItem(s, qty);
		lineItem.add(item);
	}
	
	public int getTotal() {
		int total = 0;	
		for (SalesLineItem item : lineItem) {
			total += item.getSubTotal();
		}
		return total;
	}
	
	public void makePayment() {
		int total = this.getTotal();
		payment = new Payment(total);	
	}
	
	// 출력을 보여주어 이해를 돕기위한 메소드, 클래스 다이어그램에 반영하지 않음
	public Date getDate() {
		return date;
	}
	
	// 출력을 보여주어 이해를 돕기위한 메소드, 클래스 다이어그램에 반영하지 않음
	public void printLineItems() {
		for (SalesLineItem item : lineItem) {
			System.out.println("upc : " + item.getItemUpc() +", name : " + item.getItemName() + ", price : "
					+ item.getItemPrice() + ", quantity : " + item.getQuantity());
		}
	}
}
-----------------------------------------------------------
public class Payment {
	private int amount;
	
	public Payment(int amount) {
		this.amount = amount;
	}

	// 출력을 보여주어 이해를 돕기위한 메소드, 클래스 다이어그램에 반영하지 않음
	public int getAmount() {
		return amount;
	}
}
-----------------------------------------------------------
public class SalesLineItem  {
	private int quantity;
	private ProductSpec spec;
	
	public SalesLineItem(ProductSpec spec, int quantity) {
		this.spec = spec;
		this.quantity = quantity;
	}
	
	public int getSubTotal() {
		int price = spec.getPrice();
		return price * quantity;
	}
	
	// 출력을 보여주어 이해를 돕기위한 메소드, 클래스 다이어그램에 반영하지 않음
	public int getItemUpc() {
		return spec.getUpc();
	}
	
	// 출력을 보여주어 이해를 돕기위한 메소드, 클래스 다이어그램에 반영하지 않음
	public String getItemName() {
		return spec.getName();
	}
	
	// 출력을 보여주어 이해를 돕기위한 메소드, 클래스 다이어그램에 반영하지 않음
	public int getItemPrice() {
		return spec.getPrice();
	}
	
	// 출력을 보여주어 이해를 돕기위한 메소드, 클래스 다이어그램에 반영하지 않음
	public int getQuantity() {
		return quantity;
	}
}
-----------------------------------------------------------
import java.util.HashMap;
public 	class ProductCatalog  {
	private HashMap<Integer, ProductSpec> specTable = new HashMap<Integer, ProductSpec>();
	
	public void addSpec(int upc, ProductSpec spec) {
		specTable.put(upc, spec);
	}
	
	public ProductSpec spec(int upc) {
		return specTable.get(upc);
	}
}
-----------------------------------------------------------
public class ProductSpec  {
	private int upc;
	private String name;
	private int price;
	
	public ProductSpec(int upc, String name, int price) {
		this.upc = upc;
		this.name = name;
		this.price = price;
	}
	
	public int getPrice() {
		return price;
	}
	
	// 출력을 보여주어 이해를 돕기위한 메소드, 클래스 다이어그램에 반영하지 않음
	public int getUpc() {
		return upc;
	}
	
	// 출력을 보여주어 이해를 돕기위한 메소드, 클래스 다이어그램에 반영하지 않음
	public String getName()	{
		return name;
	}
}
