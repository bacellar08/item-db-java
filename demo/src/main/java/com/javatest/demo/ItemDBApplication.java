package com.javatest.demo;



import java.util.Optional;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
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
	String itemName;
	String description;
	Double price;
	Long stock;


	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
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
	public Long getStock() {
		return stock;
	}
	public void setStock(Long stock) {
		this.stock = stock;
	}

	

	
}

@RestController
@RequestMapping("/item")
class ItemController {

	ItemService itemService;	

	public ItemController(ItemService itemService) {
		this.itemService = itemService;
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

}

interface ItemRepository extends JpaRepository<Item, Long>{

}







