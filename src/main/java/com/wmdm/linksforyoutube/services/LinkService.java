package com.wmdm.linksforyoutube.services;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.wmdm.linksforyoutube.models.Link;

public interface LinkService {

	Link addLink(Link link) throws Exception;
	List<Link> getAllLinks() throws Exception;
	Optional<Link> getLinkById(Long id) throws Exception;
	Link updateLink(Long id, Link link) throws Exception;
	void removeLinkById(Long id) throws Exception;
	Page<Link> getAllLinks(Pageable pageable);

}
