package com.wmdm.linksforyoutube.services;

import java.util.List;
import java.util.Optional;

import com.wmdm.linksforyoutube.models.Collection;

public interface CollectionService {
	
	Collection addCollection(Collection collection) throws Exception;
	List<Collection> getAllCollections() throws Exception;
	Optional<Collection> getCollectionById(Long id) throws Exception;
	Collection updateCollection(Long id, Collection collection) throws Exception;
	void removeCollection(Long id) throws Exception;
	
}
