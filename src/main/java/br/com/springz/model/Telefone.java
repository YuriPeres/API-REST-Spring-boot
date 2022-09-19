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
    @ManyToMany(mappedBy = "telefones")
    private List<Funcionario> funcionarios;

    public Telefone(TelefoneDto dto, int i) {
        this.numero = dto.getNumeros().get(i);
        this.funcionarios = dto.getFuncionarios();
    }

    public Telefone(Long id, List<BigInteger> numero, int i) {
        this.id = id;
        this.numero = numero.get(i);
    }

    public Telefone(Long id, BigInteger numero) {
        this.id = id;
        this.numero = numero;
    }


    @Override
    public String toString() {
        return "Telefone{" +
                "id=" + id +
                ", numero=" + numero +
                '}';
    }

}
