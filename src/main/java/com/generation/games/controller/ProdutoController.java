package com.generation.games.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.generation.games.model.Categoria;
import com.generation.games.model.Produto;
import com.generation.games.repository.ProdutoRepository;

@RestController
@RequestMapping("/produtos") 
@CrossOrigin(origins ="*", allowedHeaders="*")
public class ProdutoController {

	@Autowired
	private ProdutoRepository produtoRepository;
	
	@GetMapping
	public ResponseEntity<List<Produto>> findAll(){
		return ResponseEntity.ok(produtoRepository.findAll());	
	}
		
	@GetMapping("/{id}")
	public ResponseEntity< Produto>getById(@PathVariable Long id){
		return  produtoRepository.findById(id)
				.map(resposta -> ResponseEntity.ok(resposta))
				.orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
	}
	@GetMapping("/classificacao/{classificacao}")
	public ResponseEntity<List<Produto>> getByTitle(@PathVariable String classificacao){
		return ResponseEntity.ok( produtoRepository
				.findAllByClassificacaoContainingIgnoreCase(classificacao));
	}
}
