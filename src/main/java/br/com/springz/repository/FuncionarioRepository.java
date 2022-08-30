package br.com.springz.repository;

import br.com.springz.model.Funcionario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface FuncionarioRepository extends JpaRepository<Funcionario, Long> {

    Funcionario findByNome(String nome);

    @Modifying
    @Query(value = "INSERT INTO tb_funcionario_telefone WHERE tb_funcionario_telefone.id_funcionario=? " +
            "AND tb_funcionario_telefone.id_telefone=?", nativeQuery=true)
    void ligarTelefoneExistenteEmFuncionario(Long idFuncionario, Long idTelefone);

}
