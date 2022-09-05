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


//    select exists (select tf.id as "idFuncionario", tf.nome as "nome",
//                   tt.id as "idNumero", tt as "numero"
//                           from tb_funcionario tf
//                           join tb_funcionario_telefone tft on tf.id = tft.id_funcionario
//                           join tb_telefone tt on tt.id =tft.id_telefone
//                           where tft.id_funcionario =18 and tft.id_telefone
//                           = 35)::bool
    @Query(value = "SELECT EXISTS (SELECT * FROM tb_funcionario_telefone " +
            "WHERE id_funcionario =:idFuncionario AND id_telefone:idTelefone)", nativeQuery = true)
    boolean telefoneExisteEmFuncionario(@Param("idFuncionario") Long IdFuncionario,
                                        @Param("idTelefone") Long idTelefone);


    Telefone findByNumero(BigInteger numero);

}
