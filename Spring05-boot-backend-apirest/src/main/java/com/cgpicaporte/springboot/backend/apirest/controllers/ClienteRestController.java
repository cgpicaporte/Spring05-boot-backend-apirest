package com.cgpicaporte.springboot.backend.apirest.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.cgpicaporte.springboot.backend.apirest.models.entity.Cliente;
import com.cgpicaporte.springboot.backend.apirest.models.services.IClienteService;
import com.cgpicaporte.springboot.backend.apirest.view.xml.ClienteList;

@CrossOrigin(origins= {"http://localhost:4200"})
@RestController
@RequestMapping("/api")
public class ClienteRestController {

	@Autowired
	private IClienteService clienteService;
	
	@GetMapping("/clientesall")
	public List<Cliente> index(){
		return clienteService.findAll();
	}
	
	@GetMapping(value = "/clientesxml")
	public @ResponseBody ClienteList listarXMLRest(@RequestParam(name = "page", defaultValue = "0") int page, @RequestParam(name = "size", defaultValue = "10") int size) {
		Pageable pageRequest = PageRequest.of(page, size);
		Page<Cliente> clientes = clienteService.findAll(pageRequest);
		return new ClienteList(clientes.getContent());
	}
	
	@GetMapping(value = "/clientes")
	public @ResponseBody List<Cliente> listarXMLRestJson(@RequestParam(name = "page", defaultValue = "0") int page, @RequestParam(name = "size", defaultValue = "10") int size) {
		Pageable pageRequest = PageRequest.of(page, size);
		Page<Cliente> clientes = clienteService.findAll(pageRequest);
		return clientes.getContent();
	}
	
	@GetMapping("/clientes/{id}")
	//@ResponseStatus(HttpStatus.OK) //esto retorna un 200, redundante lo comento
	public Cliente show(@PathVariable Long id) {
		return clienteService.findById(id);
	}
	
	@PostMapping("/clientes")
	@ResponseStatus(HttpStatus.CREATED) //con esto retornamos un 201
	public Cliente create(@RequestBody Cliente cliente) {
		//cliente.setCreateAt(new Date()); en el Entity ponemos un PrePersist
		return clienteService.save(cliente);		
	}
	
	@PutMapping("/clientes/{id}")
	@ResponseStatus(HttpStatus.CREATED)
	public Cliente update(@RequestBody Cliente cliente, @PathVariable Long id) {
		
		Cliente clienteActual= clienteService.findById(id);
		clienteActual.setApellido(cliente.getApellido());
		clienteActual.setNombre(cliente.getNombre());
		clienteActual.setEmail(cliente.getEmail());
		
		return clienteService.save(clienteActual);
	}
	
	@DeleteMapping("/clientes/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Long id) {
		clienteService.delete(id);
	}
	
}
