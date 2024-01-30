package com.agenda.domain.service;

import com.agenda.domain.exception.ClienteNaoEncontradoException;
import com.agenda.domain.exception.EntidadeEmUsoException;
import com.agenda.domain.model.Cliente;
import com.agenda.domain.repository.ClienteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.agenda.domain.util.Mensagens.MSG_CLIENTE_EM_USO;

@Service
@Transactional
@RequiredArgsConstructor
public class ClienteService {

    private final ClienteRepository repository;

    public List<Cliente> listar() {
        return repository.findAll();
    }

    public Cliente consultar(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ClienteNaoEncontradoException(id));
    }

    public void incluir(Cliente cliente) {
        repository.save(cliente);
    }

    public void alterar(Long id, Cliente cliente) {
        Cliente clienteAtual = consultar(id);
        BeanUtils.copyProperties(cliente, clienteAtual, "id");
    }

    public void excluir(Long id) {
        try {
            repository.deleteById(id);
            repository.flush();
        } catch (EmptyResultDataAccessException e) {
            throw new ClienteNaoEncontradoException(id);
        } catch (DataIntegrityViolationException e) {
            throw new EntidadeEmUsoException(MSG_CLIENTE_EM_USO);
        }
    }
}
