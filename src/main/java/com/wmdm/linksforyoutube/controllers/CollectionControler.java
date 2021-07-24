package com.wmdm.linksforyoutube.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.wmdm.linksforyoutube.models.Collection;
import com.wmdm.linksforyoutube.services.CollectionService;

@RestController
public class CollectionControler {

	@Autowired
	private CollectionService collectionService;

	@PostMapping("/addcollection")
	public ResponseEntity<?> add(@RequestBody Collection collection) {
		try {
			Collection collectionSalved = this.collectionService.addCollection(collection);
			return new ResponseEntity<Collection>(collectionSalved, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping("/getallcollection")
	public ResponseEntity<?> getAll() {
		try {
			List<Collection> collections = this.collectionService.getAllCollections();
			return new ResponseEntity<List<Collection>>(collections, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping("/getcollectionbyid/{id}")
	public ResponseEntity<?> getById(@PathVariable(value = "id") Long id) {
		try {
			Optional<Collection> collectionInBase = this.collectionService.getCollectionById(id);
			return new ResponseEntity<Collection>(collectionInBase.get(), HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@PutMapping("/updatecollection/{id}")
	public ResponseEntity<?> update(@PathVariable(value = "id") Long id, @RequestBody Collection collection) {
		try {
			Optional<Collection> collectionInBase = this.collectionService.getCollectionById(id);
			collectionInBase.get().setName(collection.getName());
			return new ResponseEntity<Collection>(collectionInBase.get(), HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping("/deletecollection/{id}")
	public ResponseEntity<?> delete(@PathVariable(value = "id") Long id) {
		try {
			Optional<Collection> collectionInBase = this.collectionService.getCollectionById(id);
			this.collectionService.removeCollection(id);
			return new ResponseEntity<Collection>(collectionInBase.get(), HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

}
