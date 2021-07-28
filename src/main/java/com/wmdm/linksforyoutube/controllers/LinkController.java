package com.wmdm.linksforyoutube.controllers;

import java.util.List;
import java.util.Optional;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

//Swagger
//import io.swagger.annotations.Api;
//import io.swagger.annotations.ApiOperation;

//Pageable
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

//Direction
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PagedResourcesAssembler;

import com.wmdm.linksforyoutube.models.Link;
import com.wmdm.linksforyoutube.services.LinkService;

//Hateoas
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@RestController
@RequestMapping("/links")
//@Api(value = "Link End Point")
public class LinkController {

	@Autowired
	private LinkService linkService;

	@GetMapping
	public ResponseEntity<?> index() {
		return new ResponseEntity<String>("IsOk", HttpStatus.OK);
	}
//	 @ApiOperation("Add Link")
	@PostMapping(value = "/addlinks", produces = { "application/json", "application/xml" })
	public ResponseEntity<?> add(@RequestBody Link link) {

		try {
			Link linkSaved = this.linkService.addLink(link);
			return new ResponseEntity<Link>(linkSaved, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping(value = "/getlinks",  produces = { "application/json;charset=utf-8", "application/xml;charset=utf-8" })
	public ResponseEntity<?> listAll() {
		try {
			List<Link> links = this.linkService.getAllLinks();
			for (Link link : links) {
				Long idLink = link.getId();
				link.add(linkTo(methodOn(LinkController.class).findById(idLink)).withSelfRel());
			}
			return new ResponseEntity<List<Link>>(links, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping(value = "/getlinkspageable", produces = { "application/json", "application/xml" })
	public ResponseEntity<?> listAllPageable(@RequestParam(value = "page") int page, @RequestParam(value = "size") int size,
			@RequestParam(value = "direction") Direction direction, PagedResourcesAssembler assembler) {
		Pageable pageable = PageRequest.of(page, size, Sort.by(direction, "collection"));
		try {
			Page<Link> links = this.linkService.getAllLinks(pageable);
			for (Link link : links) {
				Long idLink = link.getId();
				link.add(linkTo(methodOn(LinkController.class).findById(idLink)).withSelfRel());
			}
			return new ResponseEntity<Page<Link>>(links, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping(value = "/getlink/{id}", produces = { "application/json", "application/xml" })
	public ResponseEntity<?> findById(@PathVariable(value = "id") Long id) {
		try {
			Optional<Link> link = this.linkService.getLinkById(id);
			link.get().add(linkTo(methodOn(LinkController.class).listAll()).withRel("Link list"));
			// link.get().add(linkTo(methodOn(LinkController.class).listAllPageable(1,
			// 10, Direction.ASC)).withRel("Link list"));
			return new ResponseEntity<Link>(link.get(), HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@PutMapping(value = "/getlink/{id}", produces = { "application/json", "application/xml" })
	public ResponseEntity<?> update(@PathVariable(value = "id") Long id, @RequestBody Link link) {

		try {
			Link linkUpdated = this.linkService.updateLink(id, link);
			return new ResponseEntity<Link>(linkUpdated, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}

	@DeleteMapping("/getlink/{id}")
	public ResponseEntity<?> delete(@PathVariable(value = "id") Long id) {
		try {
			this.linkService.removeLinkById(id);
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
}
