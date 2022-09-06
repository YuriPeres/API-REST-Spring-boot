package br.com.springz.model;

import br.com.springz.dtoform.TelefoneDto;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigInteger;
import java.util.List;

@Entity @Table(name="tb_telefone")
@Data @AllArgsConstructor @NoArgsConstructor
public class Telefone {

    @Id @Column(name = "id") @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="numero")
    private BigInteger numero;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "tb_funcionario_telefone",
        joinColumns = @JoinColumn(name = "id_telefone", referencedColumnName = "id"),
        inverseJoinColumns = @JoinColumn(name = "id_funcionario", referencedColumnName = "id"))
    private List<Funcionario> funcionarios;

    public Telefone(TelefoneDto dto, int i) {
        this.numero = dto.getNumeros().get(i);
        this.funcionarios = dto.getFuncionarios();
    }

    public Telefone(Long id, List<BigInteger> numero, int i) {
        this.id = id;
        this.numero = numero.get(i);
    }

    @Override
    public String toString() {
        return "Telefone{" +
                "id=" + id +
                ", numero=" + numero +
                '}';
    }
}
