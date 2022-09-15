package br.com.springz.repository;

import br.com.springz.model.Telefone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigInteger;

public interface TelefoneRepository extends JpaRepository<Telefone, Long> {

    @Modifying
    @Query(value = "DELETE FROM tb_funcionario_telefone " +
            "WHERE id_funcionario =:idFuncionario AND id_telefone =:idTelefone", nativeQuery = true)
    void deletarTelefoneApenasDoFuncionario(@Param("idFuncionario") Long IdFuncionario,
                                            @Param("idTelefone") Long idTelefone);


    @Modifying
    @Query(value = "DELETE FROM tb_funcionario_telefone WHERE id_telefone=:idTelefone", nativeQuery = true)
    void deletarTodosVinculosTelefone(@Param("idTelefone") Long idTelefone);


    @Query(value = "SELECT EXISTS (SELECT * FROM tb_funcionario_telefone " +
            "WHERE id_funcionario =:idFuncionario AND id_telefone=:idTelefone)", nativeQuery = true)
    boolean telefoneExisteEmFuncionario(@Param("idFuncionario") Long IdFuncionario,
                                        @Param("idTelefone") Long idTelefone);


    Telefone findByNumero(BigInteger numero);

    @Query(value = "SELECT  COUNT (tft.id_funcionario) FROM tb_funcionario_telefone tft WHERE tft.id_telefone =:idTelefone",
            nativeQuery = true)
    int telefoneNaoTemVinculoAlgum(@Param("idTelefone") Long idTelefone);

    @Modifying
    @Query(value = "DELETE FROM tb_telefone tt WHERE tt.id =:idTelefone", nativeQuery = true)
    void deleteTelefoneNativoById(@Param("idTelefone") Long idTelefone);


}
