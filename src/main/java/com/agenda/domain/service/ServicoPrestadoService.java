package com.agenda.domain.service;

import com.agenda.domain.exception.ServicoPrestadoNaoEncontradoException;
import com.agenda.domain.model.ServicoPrestado;
import com.agenda.domain.repository.ServicoPrestadoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class ServicoPrestadoService {
    private final ServicoPrestadoRepository repository;

    public List<ServicoPrestado> listar() {
        return repository.findAll();
    }

    public ServicoPrestado buscar(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ServicoPrestadoNaoEncontradoException(id));
    }

    public void salvar(ServicoPrestado servicoPrestado) {
        repository.save(servicoPrestado);
    }

    public void atualizar(Long id, ServicoPrestado servicoPrestado) {
        ServicoPrestado servicoPrestadoAtual = buscar(id);
        BeanUtils.copyProperties(servicoPrestado, servicoPrestadoAtual, "id");
    }

    public void excluir(Long id) {
        repository.deleteById(id);
    }
}
