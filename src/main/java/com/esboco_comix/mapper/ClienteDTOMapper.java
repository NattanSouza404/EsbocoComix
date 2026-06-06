package com.esboco_comix.mapper;

import java.time.LocalDate;

import com.esboco_comix.dto.FiltrarClienteDTO;
import com.esboco_comix.model.enuns.Genero;

import jakarta.servlet.http.HttpServletRequest;

public class ClienteDTOMapper {
    public FiltrarClienteDTO mapearToFiltrarClienteDTO(HttpServletRequest req) {
        FiltrarClienteDTO filtro = new FiltrarClienteDTO();
        
        String nome = req.getParameter("nome");
        if (!nome.isBlank()){
            filtro.setNome(nome);
        }

        String cpf = req.getParameter("cpf");
        if (!cpf.isBlank()){
            filtro.setCpf(cpf);
        }

        String dataNascimento = req.getParameter("dataNascimento");
        if (!dataNascimento.isBlank()){
            filtro.setDataNascimento(LocalDate.parse(dataNascimento));
        }

        String genero = req.getParameter("genero");
        if (!genero.isBlank()){
            filtro.setGenero(Genero.valueOf(genero));
        }

        String email = req.getParameter("email");
        if (!email.isBlank()){
            filtro.setEmail(email);
        }

        String ranking = req.getParameter("ranking");
        if (!ranking.isBlank()){
            filtro.setRanking(Integer.parseInt(ranking));
        }

        String isAtivo = req.getParameter("isAtivo");
        if (!isAtivo.isBlank()){
            filtro.setIsAtivo(Boolean.valueOf(isAtivo));
        }

        return filtro;
    }
}
