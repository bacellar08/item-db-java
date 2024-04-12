package com.javatest.demo;



import java.util.Optional;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;





@SpringBootApplication
public class ItemDBApplication {

	public static void main(String[] args) {
		SpringApplication.run(ItemDBApplication.class, args);
	}

}

@Entity
class Item {
	@Id
	Long id;
	String productName;
	String description;
	Double price;
	Long quantity;


	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getproductName() {
		return productName;
	}
	public void setproductName(String productName) {
		this.productName = productName;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double value) {
		this.price = value;
	}
	public Long getQuantity() {
		return quantity;
	}
	public void setQuantity(Long quantity) {
		this.quantity = quantity;
	}

	

	
}

@RestController
@RequestMapping("/item")
@CrossOrigin(origins = "*")
class ItemController {

	ItemService itemService;	

	public ItemController(ItemService itemService) {
		this.itemService = itemService;
	}

	@PostMapping("/add")
	public ResponseEntity<Item> addItem(@RequestBody Item item) {
		Item savedItem = itemService.saveItem(item);
		return new ResponseEntity<>(savedItem, HttpStatus.CREATED);
	}




	public Item getItem(@PathVariable("id") Long id) {
		return itemService.getItem(id).orElse(null);
	}
}

@Service
class ItemService {
	ItemRepository itemRepository;

	public ItemService(ItemRepository itemRepository) {
		this.itemRepository = itemRepository;
	}

	public Optional<Item> getItem(Long id) {
		return itemRepository.findById(id);
	}
	
	public Item saveItem(Item item) {
		return itemRepository.save(item);
	}

}

interface ItemRepository extends JpaRepository<Item, Long>{

}







