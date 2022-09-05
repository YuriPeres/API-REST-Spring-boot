package br.com.springz.service;

import br.com.springz.config.exceptions.ExceptionIdNaoEcontrado;
import br.com.springz.dtoform.FuncionarioDto;
import br.com.springz.dtoform.FuncionarioDtoDetalhado;
import br.com.springz.dtoform.TelefoneDto;
import br.com.springz.model.Funcionario;
import br.com.springz.model.Telefone;
import br.com.springz.repository.FuncionarioRepository;
import br.com.springz.repository.TelefoneRepository;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.constraints.NotBlank;
import java.lang.reflect.Type;
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

    @Transactional
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

        try{
            Telefone telefoneExiste = telefoneRepository.findByNumero(telefoneDto.getNumero());

            funcionarioRepository.ligarTelefoneExistenteEmFuncionario(idFuncionario, telefoneExiste.getId());

        } catch (Exception ex) {
            funcionario.getTelefones().add(telefoneRepository.save(telefone));

        }
        funcionarioRepository.save(funcionario);

        return funcionario;

    }

    @Transactional
    public Funcionario cadastrarTelefoneEmFuncionario(Long idFuncionario, String stringTelefones) {
        Funcionario funcionario = funcionarioRepository.findById(idFuncionario).orElseThrow(() ->
                new ExceptionIdNaoEcontrado("Id não encontrado: " + idFuncionario,
                        "O Id informado não existe no banco de dados "));


        Gson gson = new Gson();

        Type tipoLista = new TypeToken<ArrayList<Telefone>>() {}.getType();

        ArrayList<Telefone> listaTelefones  = gson.fromJson(stringTelefones, tipoLista);

        for (Telefone telefoneDaLista:
             listaTelefones) {

            Telefone telefone = telefoneDaLista;
            if(telefone.getFuncionarios() == null) {
                telefone.setFuncionarios(new ArrayList<>());
            }

            telefone.getFuncionarios().add(funcionario);

            if(funcionario.getTelefones() == null) {
                funcionario.setTelefones(new ArrayList<>());
            }

            try{
                Telefone telefoneExiste = telefoneRepository.findByNumero(telefoneDaLista.getNumero());

                funcionarioRepository.ligarTelefoneExistenteEmFuncionario(idFuncionario, telefoneExiste.getId());

            } catch (Exception ex) {
                funcionario.getTelefones().add(telefoneRepository.save(telefone));

            }
            funcionarioRepository.save(funcionario);

        }



        return funcionario;

    }



    @Transactional
    public Funcionario atualizarTelefone(Long idFuncionario, Long idTelefone){
        Boolean existeLigacao = telefoneRepository.telefoneExisteEmFuncionario(idFuncionario, idTelefone);
        System.out.println(existeLigacao);

        return null;
    }


    @Transactional
    public ResponseEntity<?> deletarTelefone(Long id) {
        telefoneRepository.findById(id).orElseThrow(() -> new ExceptionIdNaoEcontrado("Id não encontrado: " + id,
                "O Id informado não existe no banco de dados "));
        telefoneRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }

    public ResponseEntity<?> deletarTelefoneApenasDoFuncionario(Long idFuncionario, Long idTelefone) {
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
