package com.esboco_comix.cliente.mapper;

import java.time.LocalDate;
import java.util.List;

import com.esboco_comix.cliente.dominio.entidades.CartaoCredito;
import com.esboco_comix.cliente.dominio.entidades.Cliente;
import com.esboco_comix.cliente.dominio.entidades.Endereco;
import com.esboco_comix.cliente.dominio.enuns.Genero;
import com.esboco_comix.cliente.dominio.value_objects.Cpf;
import com.esboco_comix.cliente.dominio.value_objects.Email;
import com.esboco_comix.cliente.dto.AtualizarClienteDTO;
import com.esboco_comix.cliente.dto.CadastrarClienteDTO;
import com.esboco_comix.cliente.dto.FiltrarClienteDTO;

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

    public Cliente mapearToCliente(AtualizarClienteDTO dto) {
        return Cliente.builder()
            .id(dto.id())
            .nome(dto.nome())
            .genero(Genero.valueOf(dto.genero()))
            .dataNascimento(LocalDate.parse(dto.dataNascimento()))
            .cpf(new Cpf(dto.cpf()))
            .email(new Email(dto.email()))
            .telefone(dto.telefone())
        .build();
    }

    public CadastrarClienteDTO mapearToCadastrarClienteDTO(
        Cliente cliente,
        List<Endereco> enderecos,
        List<CartaoCredito> cartoesCredito
    ){
        return CadastrarClienteDTO.builder()
            .nome(cliente.getNome())
            .genero(cliente.getGenero())
            .dataNascimento(cliente.getDataNascimento())
            .cpf(cliente.getCpf().valor())
            .email(cliente.getEmail().valor())
            .enderecos(enderecos)
            .cartoesCredito(cartoesCredito)
        .build();
    }
}
