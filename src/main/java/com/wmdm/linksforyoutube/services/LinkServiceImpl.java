package com.wmdm.linksforyoutube.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.wmdm.linksforyoutube.models.Link;
import com.wmdm.linksforyoutube.repositories.LinkRepository;

import javassist.NotFoundException;

@Service
public class LinkServiceImpl implements LinkService {

	@Autowired
	LinkRepository linkRepository;

	@Override
	public Link addLink(Link link) throws Exception {
		Link linkBase = this.linkRepository.save(link);
		if (linkBase == null) {
			new NotFoundException("NOT FOUND");
			return null;
		} else {
			return linkBase;
		}
	}

	@Override
	public List<Link> getAllLinks() throws Exception {
		List<Link> links = this.linkRepository.findAll();
		if (links.size() > 0) {
			return links;
		} else {
			new NotFoundException("NOT FOUND");
			return null;
		}
	}

	@Override
	public Page<Link> getAllLinks(Pageable pageable) {
		Page<Link> links = this.linkRepository.findAll(pageable);
		if (links != null) {
			return links;
		} else {
			new NotFoundException("NOT FOUND");
			return null;
		}
	}

	@Override
	public Optional<Link> getLinkById(Long id) throws Exception {
		Optional<Link> link = this.linkRepository.findById(id);
		if (!link.isPresent()) {
			new NotFoundException("NOT FOUND");
			return null;
		} else {
			return link;
		}

	}

	@Override
	public Link updateLink(Long id, Link link) throws Exception {
		Optional<Link> linkSalvedInBase = this.getLinkById(id);
		if (linkSalvedInBase == null) {
			new NotFoundException("NOT FOUND");
			return null;
		} else {
			linkSalvedInBase.get().setLink(link.getLink());
			linkSalvedInBase.get().setDescription(link.getDescription());
			linkSalvedInBase.get().setTitle(link.getTitle());
			linkSalvedInBase.get().setCollection(link.getCollection());
			return this.linkRepository.saveAndFlush(linkSalvedInBase.get());
		}
	}

	@Override
	public void removeLinkById(Long id) throws Exception {
		Optional<Link> linkBase = this.getLinkById(id);
		if (!linkBase.isPresent()) {
			new NotFoundException("NOT FOUND");
		}
		this.linkRepository.deleteById(id);
	}

}
