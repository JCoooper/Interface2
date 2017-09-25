import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;

public class ShoppingCart implements Cart {
	ArrayList<SelectedItem> index = new ArrayList<SelectedItem>();
	private double total;

	public ShoppingCart() {
	}

	public void addItem(SelectedItem newItem) {
		index.add(newItem);
		for (SelectedItem Item : index) {
			if (Item.getItemNumber() == newItem.getItemNumber()) {
				Item.setQuantity(Item.getQuantity());
			}
		}
	}

	public void deleteItem(int deleteItemNumber) {
		for (SelectedItem Item : index) {
			if (Item.getItemNumber() == deleteItemNumber) {
				index.remove(Item);
			}
		}
	}

	public double getTotal() {
		for (SelectedItem Item : index) {
			total = total + (Item.getQuantity() * Item.getUnitPrice());
		}
		return Double.parseDouble(new DecimalFormat("#.0#").format(total));
	}

	public double getTax() {
		return total * 0.045;
	}

	public double getShipping() {
		if (total <= 10) {
			return 2.50;
		} else {
			return total * 0.15;
		}
	}

	public double grandTotal() {
		double sum;
		sum = this.getTotal() + this.getShipping() + this.getTax();
		return sum;
	}

	public String toString() {
		for (SelectedItem Item : index) {
			int quant;
			quant = Item.getQuantity();

			double unitp;
			unitp = Item.getUnitPrice();

			double tote;
			tote = Item.getQuantity() * Item.getUnitPrice();

			double gtotal;
			gtotal = grandTotal();

			System.out.println("Item: " + Item.getDescription());
			System.out.println("Quantity: " + quant);
			System.out.println("Unit Price: " + NumberFormat.getCurrencyInstance(new Locale("en", "US")).format(unitp));
			System.out.println("Total Price: " + NumberFormat.getCurrencyInstance(new Locale("en", "US")).format(tote));
			System.out.println(" ");
		}
		return "Total: " + NumberFormat.getCurrencyInstance(new Locale("en", "US")).format(getTotal()) + "\n" + "Tax: "
				+ NumberFormat.getCurrencyInstance(new Locale("en", "US")).format(getTax()) + "\n" + "Shipping: "
				+ NumberFormat.getCurrencyInstance(new Locale("en", "US")).format(getShipping()) + "\n" + "GrandTotal: "
				+ NumberFormat.getCurrencyInstance(new Locale("en", "US")).format(grandTotal());

	}

}
