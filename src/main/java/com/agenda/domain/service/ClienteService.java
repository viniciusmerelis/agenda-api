package com.agenda.domain.service;

import com.agenda.domain.exception.ClienteNaoEncontradoException;
import com.agenda.domain.model.Cliente;
import com.agenda.domain.repository.ClienteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class ClienteService {
    private final ClienteRepository repository;

    public List<Cliente> listar() {
        return repository.findAll();
    }

    public Cliente buscar(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ClienteNaoEncontradoException(id));
    }

    public Cliente buscarReferencia(Long id) {
        return repository.getReferenceById(id);
    }

    public void salvar(Cliente cliente) {
        repository.save(cliente);
    }

    public void atualizar(Long id, Cliente cliente) {
        Cliente clienteAtual = buscar(id);
        BeanUtils.copyProperties(cliente, clienteAtual, "id");
    }

    public void excluir(Long id) {
        repository.deleteById(id);
    }
}
