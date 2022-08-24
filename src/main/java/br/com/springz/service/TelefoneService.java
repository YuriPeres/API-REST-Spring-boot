package br.com.springz.service;

import br.com.springz.dtoform.FuncionarioDto;
import br.com.springz.dtoform.TelefoneDto;
import br.com.springz.model.Funcionario;
import br.com.springz.model.Telefone;
import br.com.springz.repository.FuncionarioRepository;
import br.com.springz.repository.TelefoneRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseBody;

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
        for (Telefone telefone : telefoneRepository.findAll()){
            telefoneDtoList.add(new TelefoneDto(telefone));
        }
        return telefoneDtoList;
    }

    public Telefone cadastrar(TelefoneDto telefoneDto){
        return telefoneRepository.save(new Telefone(telefoneDto));
    }

    public String associarTelefoneAoFuncionario(Long idFuncionario, Long idTelefone){
        try{
            Telefone telefone = telefoneRepository.findById(idTelefone).orElseThrow();
            Funcionario funcionario = funcionarioRepository.findById(idFuncionario).orElseThrow();
            funcionario.getTelefones().add(telefone);
        } catch (Exception ex){
            System.out.println("deu ruim -> "+ex.getMessage());
        }
        System.out.println(funcionarioRepository.getReferenceById(idFuncionario));
        System.out.println(funcionarioRepository.getReferenceById(idFuncionario).getTelefones());
        return "feito";
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
