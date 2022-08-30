package br.com.springz.service;

import br.com.springz.config.exceptions.ExceptionIdNaoEcontrado;
import br.com.springz.dtoform.FuncionarioDto;
import br.com.springz.dtoform.TelefoneDto;
import br.com.springz.model.Funcionario;
import br.com.springz.model.Telefone;
import br.com.springz.repository.FuncionarioRepository;
import br.com.springz.repository.TelefoneRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Service
@ResponseBody
public class TelefoneService {

    private final TelefoneRepository telefoneRepository;
    private final FuncionarioRepository funcionarioRepository;

    public List<TelefoneDto> listarTodosTelefones() {
        List<TelefoneDto> telefoneDtoList = new ArrayList<>();
        for (Telefone telefone : telefoneRepository.findAll()) {
            telefoneDtoList.add(new TelefoneDto(telefone));
        }
        return telefoneDtoList;
    }

    public Funcionario cadastrarTelefoneEmFuncionario(Long idFuncionario, TelefoneDto telefoneDto) {
        Funcionario funcionario = funcionarioRepository.findById(idFuncionario).orElseThrow(() ->
                new ExceptionIdNaoEcontrado("Id não encontrado: " + idFuncionario,
                        "O Id informado não existe no banco de dados "));

        Telefone telefone = new Telefone(telefoneDto);
        if(telefone.getFuncionarios() == null) {
            telefone.setFuncionarios(new ArrayList<>());
        }

        telefone.getFuncionarios().add(funcionario);

        if(funcionario.getTelefones() == null) {
            funcionario.setTelefones(new ArrayList<>());
        }

        Telefone telefoneExiste = telefoneRepository.findByNumero(telefoneDto.getNumero());
        if(telefone.getNumero().equals(telefoneExiste.getNumero())){
            funcionarioRepository.ligarTelefoneExistenteEmFuncionario(idFuncionario, telefoneExiste.getId());
        }
        else {
            funcionario.getTelefones().add(telefoneRepository.save(telefone));
        }
        funcionarioRepository.save(funcionario);

        return funcionario;

    }

    public ResponseEntity<?> deletarTelefone(Long id) {
        telefoneRepository.findById(id).orElseThrow(() -> new ExceptionIdNaoEcontrado("Id não encontrado: " + id,
                "O Id informado não existe no banco de dados "));
        telefoneRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }

    public ResponseEntity<?> deletarTelefoneApenasDoFuncionario(long idFuncionario, long idTelefone) {
        telefoneRepository.deletarVinculoTelefoneDoFuncionario(idFuncionario, idTelefone);
        telefoneRepository.deletarTelefoneApenasDoFuncionario(idFuncionario, idTelefone);
        return ResponseEntity.ok().build();
    }


//    public FuncionarioDtoDetalhado acharPorId(Long id)  {
//        Funcionario funcionario = funcionarioRepository.findById(id).orElseThrow(() ->
//                new ExceptionIdNaoEcontrado("Id não encontrado: " + id,
//                        "O Id informado não existe no banco de dados ")
//        );
//        try {
//            return new FuncionarioDtoDetalhado(funcionario)
//                    .add(linkTo(methodOn(FuncionarioController.class).listarFuncionarios()).
//                            withRel("Lista de Funcionarios"));
//        } catch (Exception e) {
//            throw new ExceptionIdNaoEcontrado("Id não encontrado: " + id,
//                    "O Id informado não existe no banco de dados ");
//        }
//    }
//
//
//    public Funcionario cadastrar(FuncionarioForm funcionarioForm) {
//        return funcionarioRepository.save(new Funcionario(funcionarioForm));
//    }
//
//    public FuncionarioDto atualizar(Long id, FuncionarioFormAtualizacao funcionarioFormAtualizacao) {
//        Funcionario fun = funcionarioRepository.findById(id).orElseThrow(() ->
//                new ExceptionIdNaoEcontrado("Id não encontrado: " + id,
//                        "O Id informado não existe no banco de dados "));
//
//        Funcionario funcionario = funcionarioRepository.getReferenceById(id);
//        funcionario.setNome(funcionarioFormAtualizacao.getNome());
//        funcionario.setSobrenome(funcionarioFormAtualizacao.getSobrenome());
//
//        return new FuncionarioDto(funcionario);
//
//    }
//
//
//    public ResponseEntity<?> delete(Long id) {
//        funcionarioRepository.findById(id).orElseThrow(() -> new ExceptionIdNaoEcontrado("Id não encontrado: " + id,
//                "O Id informado não existe no banco de dados "));
//        funcionarioRepository.deleteById(id);
//        return ResponseEntity.ok().build();
//    }
//
//    /*
//    Utilidades
//     */
//
//    private List<FuncionarioDto> converterListaFuncionarioDto(List<Funcionario> funcionarios)  {
//        List<FuncionarioDto> listaFuncionarioDto = new ArrayList<>();
//        for (int i = 0; i < funcionarios.size(); i++) {
//            listaFuncionarioDto.add(new FuncionarioDto(funcionarios.get(i)));
//            long id = listaFuncionarioDto.get(i).getId();
//            try {
//                listaFuncionarioDto.get(i).add(linkTo(methodOn(FuncionarioController.class).funcionarioPorId(id)).withSelfRel());
//            } catch (Exception e) {
//                throw new ExceptionIdNaoEcontrado("Id não encontrado: " + id,
//                        "O Id informado não existe no banco de dados ");
//            }
//        }
//        return listaFuncionarioDto;
//    }


}
