package co.grandcircus.cartapi;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CartController {
	
	@Autowired
	private CartItemRepository ci_repo;
	
	@GetMapping("/reset")
	public String reset() {
		
		// Delete all
		ci_repo.deleteAll();
		
		
		CartItems ci = new CartItems("Mango", 1.99, 4);
		ci_repo.insert(ci);
		
		ci = new CartItems("Paper Towel", 9.99, 2);
		ci_repo.insert(ci);
		
		ci = new CartItems("Oat Milk", 4.39, 1);
		ci_repo.insert(ci);
		
		ci = new CartItems("Brats", 3.99, 2);
		ci_repo.insert(ci);

		ci = new CartItems("Dumplings", 2.79, 8);
		ci_repo.insert(ci);
		
		ci = new CartItems("Toothpaste", 5.00, 1);
		ci_repo.insert(ci);
		
		return "Data reset.";

	}
	
	@GetMapping("/cart-items")
		public List<CartItems> readAll(@RequestParam(required=false) String product) {
			if (product != null) {
				return ci_repo.findByName(product);
			}
			else {
				return ci_repo.findAll();
			}
		}
	
	@GetMapping("/cart-items/{id}")
	public CartItems readOne(@PathVariable("id") String id) {
		return ci_repo.findById(id).orElseThrow(() -> new ProductNotFoundException(id) );
	}
	
	@PostMapping("/cart-items")
	public CartItems create(@RequestBody CartItems product) {
		ci_repo.insert(product);
		return product;
	}
	
	@PutMapping("/cart-items/{id}")
	public CartItems update(@PathVariable("id") String id, @RequestBody CartItems product) {
		return ci_repo.save(product);
	}
	
	@DeleteMapping("/cart-items/{id}")
	public void delete(@PathVariable("id") String id) {
		ci_repo.deleteById(id);
	}
	
	@GetMapping("/cart-items/total-cost")
	public double total(double price, Integer quantity) {
		List<CartItems> allItems = ci_repo.findAll();
		double sum = 0;
		double total =0;
		
		for (CartItems p : allItems) {
			sum = price * quantity;
			total += sum;
		}
		
		return total;
		
		
		
		
		
		
		
		
	}
	
	}

