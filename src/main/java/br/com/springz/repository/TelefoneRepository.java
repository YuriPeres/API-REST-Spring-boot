package br.com.springz.repository;

import br.com.springz.model.Telefone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.math.BigInteger;

public interface TelefoneRepository extends JpaRepository<Telefone, Long> {

    @Modifying
    @Query(value = "DELETE FROM tb_funcionario_telefone WHERE id_funcionario=? AND id_telefone=?", nativeQuery=true)
    void deletarVinculoTelefoneDoFuncionario(Long idFuncionario, Long idTelefone);

    @Modifying
    @Query(value = "DELETE FROM tb_funcionario WHERE tb_funcionario_telefone.id_telefone=?", nativeQuery=true)
    void deletarTelefoneApenasDoFuncionario(Long idFuncionario, Long idTelefone);

    Telefone findByNumero(BigInteger numero);

}
