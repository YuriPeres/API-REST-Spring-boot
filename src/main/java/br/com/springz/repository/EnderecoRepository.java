package br.com.springz.repository;

import br.com.springz.model.Endereco;
import br.com.springz.model.Funcionario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EnderecoRepository extends JpaRepository<Endereco, Long> {


}
