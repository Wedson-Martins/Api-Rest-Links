package com.wmdm.linksforyoutube.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wmdm.linksforyoutube.models.Collection;
import com.wmdm.linksforyoutube.repositories.CollectionRepository;

import javassist.NotFoundException;

@Service
public class CollectionServiceImpl implements CollectionService {

	@Autowired
	private CollectionRepository collectionRepository;

	@Override
	public Collection addCollection(Collection collection) throws Exception {
		Collection collectionSalved = this.collectionRepository.save(collection);
		if (collectionSalved != null) {
			return collectionSalved;
		} else {
			new NotFoundException("NOT FOUND");
			return null;
		}
	}

	@Override
	public List<Collection> getAllCollections() throws Exception {
		List<Collection> collections = this.collectionRepository.findAll();
		if (collections.size() > 0) {
			return collections;
		} else {
			new NotFoundException("NOT FOUND");
			return null;
		}
	}

	@Override
	public Optional<Collection> getCollectionById(Long id) throws Exception {
		Optional<Collection> collection = this.collectionRepository.findById(id);
		if (collection.isPresent()) {
			return collection;
		} else {
			new NotFoundException("NOT FOUND");
			return null;
		}
	}

	@Override
	public Collection updateCollection(Long id, Collection collection) throws Exception {
		Optional<Collection> collectionInBase = this.collectionRepository.findById(id);
		if (collectionInBase.isPresent()) {
			collectionInBase.get().setName(collection.getName());
			return this.collectionRepository.save(collectionInBase.get());
		} else {
			new NotFoundException("NOT FOUND");
			return null;
		}
	}

	@Override
	public void removeCollection(Long id) throws Exception {
		Optional<Collection> collectionInBase = this.collectionRepository.findById(id);
		if (collectionInBase.isPresent()) {
			this.collectionRepository.deleteById(id);
		} else {
			new NotFoundException("NOT FOUND");
		}
	}

}
