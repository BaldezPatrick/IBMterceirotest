package com.br.IBMterceiroteste.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

@Service
public class CandidatoService {
	
	private static int geradorId = 1;
	private final Map<Integer, String> candidatos;
	private final Map<Integer, String> status;
	
	public CandidatoService() {
		candidatos = new HashMap<>();
		status = new HashMap<>();
	}
	
    public int iniciarProcesso(String nome) throws IllegalArgumentException {
    	if(nome.trim().isEmpty()) {
			throw new IllegalArgumentException("Nome inválido.");
		}
		
		if(!nome.matches("[a-zA-Z\\s]+")) {
			throw new IllegalArgumentException("Nome inválido.");
		}
		
		for(Map.Entry<Integer, String> entry : candidatos.entrySet()) {
			if(entry.getValue().equals(nome)) {
				throw new IllegalArgumentException("Candidato já participa do processo.");
			}
		}
		
		int id = geradorId;
		geradorId++;
		candidatos.put(id,  nome);
		status.put(id, "Recebido");
		return id;
    }
    
	public boolean marcarEntrevista(int codCandidato) throws IllegalArgumentException {
		if(candidatos.containsKey(codCandidato)) {
			if(status.get(codCandidato).equals("Recebido")) {
				status.put(codCandidato, "Qualificado");
				return true;
			} else {
				return false;
			}
		} else {
			throw new IllegalArgumentException("Candidato não encontrado.");
		}
	}
	
	public boolean desqualificarCandidato(int codCandidato) throws IllegalArgumentException {
		if(candidatos.containsKey(codCandidato)) {
			String statusCandidato = status.get(codCandidato);
			if(statusCandidato.equals("Recebido") || statusCandidato.equals("Qualificado") || statusCandidato.equals("Aprovado")) {
				candidatos.remove(codCandidato);
				status.remove(codCandidato);
				return true;
			} else {
				return false;
			}
		}  else {
			throw new IllegalArgumentException("Candidato não encontrado.");
		}
		
	}
	
    public String verificarStatusCandidato(int codCandidato) throws IllegalArgumentException {
        String nome = candidatos.get(codCandidato);
        String statusCandidato = status.get(codCandidato);
        if(candidatos.containsKey(codCandidato)) {
        	return "Status do candidato " + nome + " é " + statusCandidato;
        } else {
        	throw new IllegalArgumentException("Candidato não encontrado.");
        }
   }
    
	public boolean aprovarCandidato(int codCandidato) throws IllegalArgumentException{
		if(candidatos.containsKey(codCandidato)) {
			String statusCandidato = status.get(codCandidato);
			if(statusCandidato.equals("Qualificado")) {				
				status.put(codCandidato, "Aprovado");
				return true;
			} else {
				return false;
			}
		} else {
			throw new IllegalArgumentException("Candidato não encontrado.");
		}
	}
	
	public List<String> obterAprovados() throws IllegalArgumentException {
		List<String> aprovados = new ArrayList<>();
		for(Map.Entry<Integer, String> entry : status.entrySet()) {
			int id = entry.getKey();
			String statusCandidato = entry.getValue();
			if(statusCandidato.equals("Aprovado")) {
				String nome = candidatos.get(id);
				aprovados.add(nome);
			}
		}
		
		return aprovados;
	}
}
