package br.com.springz.model;

import br.com.springz.dtoform.TelefoneDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity @Table(name="tb_telefone")
@Data @AllArgsConstructor @NoArgsConstructor
public class Telefone {

    @Id @Column(name = "id") @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="numero")
    private Long numero;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "tb_funcionario_telefone",
        joinColumns = @JoinColumn(name = "id_telefone", referencedColumnName = "id"),
        inverseJoinColumns = @JoinColumn(name = "id_funcionario", referencedColumnName = "id"))
    private List<Funcionario> funcionarios;

    public Telefone(TelefoneDto dto) {
        this.numero = dto.getNumero();
        this.funcionarios = dto.getFuncionarios();
    }

    public Telefone(Long id, Long numero) {
        this.id = id;
        this.numero = numero;
    }
}
