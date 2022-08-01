package br.com.springz.repository;

import br.com.springz.model.Funcionario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FuncionarioRepository extends JpaRepository<Funcionario, Long> {

    Funcionario findByNome(String nome);

}
