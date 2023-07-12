package com.br.IBMterceiroteste.controllers;

import java.util.Arrays;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;

import com.br.IBMterceiroteste.services.CandidatoService;

@RestController
@RequestMapping("/api/v1/hiring")
public class CandidatoController {
	
	private final CandidatoService candidatoService;
	
	public CandidatoController(CandidatoService candidatoService) {
		this.candidatoService = candidatoService;
	}
	
	@PostMapping("/start")
	public ResponseEntity<?> iniciarProcesso(@RequestBody CandidatoRequest candidatoRequest) {
		try {
			int id = candidatoService.iniciarProcesso(candidatoRequest.getNome());
			return ResponseEntity.ok("O candidato tem o id: " + id);
		} catch (IllegalArgumentException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}
	
    @PostMapping("/schedule")
    public ResponseEntity<?> marcarEntrevista(@RequestBody CodCandidatoRequest codCandidatoRequest) {
        try {
        	boolean entrevistaMarcada = candidatoService.marcarEntrevista(codCandidatoRequest.getCodCandidato());
        	if (entrevistaMarcada) {
        		return ResponseEntity.ok("Entrevista marcada com sucesso.");
        	}  else {
        		return ResponseEntity.ok("Candidato já marcou entrevista.");
        	}
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PostMapping("/disqualify")
    public ResponseEntity<String> desqualificarCandidato(@RequestBody CodCandidatoRequest codCandidatoRequest) {
        try {
            boolean candidatoDesqualificado = candidatoService.desqualificarCandidato(codCandidatoRequest.getCodCandidato());
            if (candidatoDesqualificado) {
                return ResponseEntity.ok("Candidato desqualificado do processo seletivo");
            } else {
            	return ResponseEntity.ok("Candidato não encontrado.");
            }
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PostMapping("/approve")
    public ResponseEntity<?> aprovarCandidato(@RequestBody CodCandidatoRequest codCandidatoRequest) {
        try {
        	boolean candidatoAprovado = candidatoService.aprovarCandidato(codCandidatoRequest.getCodCandidato());
        	if(candidatoAprovado) {
        		return ResponseEntity.ok("Candidato aprovado.");
        	} else {
        		return ResponseEntity.ok("Candidato não encontrado.");
        	}
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
    
    @GetMapping("/status/candidate/{codCandidato}")
    public ResponseEntity<String> verificarStatusCandidato(@PathVariable int codCandidato) {
        try {
            String statusCandidato = candidatoService.verificarStatusCandidato(codCandidato);
            return ResponseEntity.ok(statusCandidato);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @GetMapping("/approved")
    public ResponseEntity<List<String>> obterAprovados() {
        try {
            List<String> aprovados = candidatoService.obterAprovados();
            if(aprovados.isEmpty()) {
            	return ResponseEntity.ok(Arrays.asList("Candidato não encontrado."));	
            } else {
            	return ResponseEntity.ok(aprovados);
            }
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }
}
