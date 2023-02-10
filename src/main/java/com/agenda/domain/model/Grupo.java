package com.agenda.domain.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "grupo")
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Grupo {
    @Id
    @EqualsAndHashCode.Include
    @SequenceGenerator(name = "seq_grupo", sequenceName = "seq_grupo", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_grupo")
    private Long id;

    @Column(nullable = false)
    private String nome;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "grupo_permissao", joinColumns = @JoinColumn(name = "grupo_id"),
        inverseJoinColumns = @JoinColumn(name = "permissao_id"))
    private Set<Permissao> permissoes = new HashSet<>();

    public void adicionarPermissoes(List<Permissao> permissoes) {
        this.getPermissoes().addAll(permissoes);
    }

    public void removerPermissao(List<Permissao> permissoes) {
        this.getPermissoes().remove(permissoes);
    }
}
