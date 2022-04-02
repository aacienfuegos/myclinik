package com.myclinik.controller;

import com.myclinik.model.Client;
import com.myclinik.repository.ClientRepository;
import com.myclinik.service.IClientService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

@Controller
public class MyController {
	@Autowired
	private IClientService clientService;

	@GetMapping("/clients")
	public String findClients(Model model) {
		var clients = (List<Client>) clientService.findAll();
		model.addAttribute("clients", clients);
		return "showClients";
	}
	@GetMapping("/clients/client")
	public String getClient(Model model, @RequestParam("id") String itemid){
		var client  = clientService.findOne(Long.parseLong(itemid));
		model.addAttribute("client", client);
		return "client";
	}  
	@RequestMapping("/clients/new")
	public String createClient(Model model){
		var newclient = clientService.createClient();
		model.addAttribute("client", newclient);
		return "newclient";	
	}
	@PostMapping("/clients/new/save")
    public String saveClient(@ModelAttribute("client") Client client) {
        clientService.saveClient(client);
        return "redirect:/clients";
    }
	@RequestMapping ("/clients/delete")
	public String deleteClient(@RequestParam("id") Long itemid) {
		clientService.deleteClient(itemid);
		return "redirect:/clients";		
	}

	@RequestMapping ("/clients/update")
	public String editClient(@RequestParam("id") Long itemid, Client client){
		clientService.updateClient(itemid, client);
		return "redirect:/clients";
	}
	/*
	@PutMapping("/clients/client")
    ResponseEntity<TFG> updateClient(@RequestBody TFG newTFG, @PathVariable String id) {

      return tfgRepository.findById(id).map(tfg -> {

        tfg.setNombre(newTFG.getNombre());

        tfg.setTitulo(newTFG.getTitulo());

        tfg.setTutor(newTFG.getTutor());

        tfg.setStatus(newTFG.getStatus());

        tfg.setNota(newTFG.getNota());

        tfg.setMemoria(newTFG.getMemoria());

        tfgRepository.save(tfg);

        return ResponseEntity.ok().body(tfg);

      }).orElse(new ResponseEntity<TFG>(HttpStatus.NOT_FOUND));

    }*/
}
