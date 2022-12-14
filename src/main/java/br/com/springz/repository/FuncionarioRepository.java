package br.com.springz.repository;

import br.com.springz.model.Funcionario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface FuncionarioRepository extends JpaRepository<Funcionario, Long> {

    Funcionario findByNome(String nome);

    @Modifying
    @Query(value = "INSERT INTO tb_funcionario_telefone (id_funcionario, id_telefone) " +
            "VALUES (:idFuncionario, :idTelefone);", nativeQuery=true)
    void ligarTelefoneExistenteEmFuncionario(@Param("idFuncionario") Long idFuncionario,
                                             @Param("idTelefone") Long idTelefone);

    @Query(value = "SELECT * FROM tb_funcionario tf " +
            "JOIN tb_funcionario_telefone tft on tf.id = tft.id_funcionario " +
            "JOIN tb_telefone tt on tt.id = tft.id_telefone " +
            "WHERE tf.id=:idFuncionario", nativeQuery = true)
    Funcionario acharFuncionarioPorIdNativo(@Param("idFuncionario") Long id);

}
