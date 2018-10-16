package com.cgpicaporte.springboot.backend.apirest.view.xml;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.cgpicaporte.springboot.backend.apirest.models.entity.Cliente;

@XmlRootElement(name = "ClienteList")
public class ClienteList {

	@XmlElement(name = "Cliente")
	public List<Cliente> clientes;

	public ClienteList() {}
	
	public ClienteList(List<Cliente> clientes) {
		this.clientes = clientes;
	}

	public List<Cliente> getClientes() {
		return clientes;
	}
		
}
