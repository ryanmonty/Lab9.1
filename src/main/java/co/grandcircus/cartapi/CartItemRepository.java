package co.grandcircus.cartapi;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface CartItemRepository extends MongoRepository<CartItems, String>{

	List<CartItems> findByName(String product);
}
