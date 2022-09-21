package br.com.springz.service;

import br.com.springz.config.exceptions.ExceptionIdNaoEcontrado;
import br.com.springz.dtoform.TelefoneDto;
import br.com.springz.model.Funcionario;
import br.com.springz.model.Telefone;
import br.com.springz.repository.FuncionarioRepository;
import br.com.springz.repository.TelefoneRepository;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.ResponseBody;

import java.lang.reflect.Type;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
    public Funcionario cadastrarTelefoneEmFuncionario(TelefoneDto telefoneDto) {
        Funcionario funcionario = funcionarioRepository.findById(telefoneDto.getIdFuncionario()).orElseThrow(() ->
                new ExceptionIdNaoEcontrado("Id não encontrado: " + telefoneDto.getIdFuncionario(),
                        "O Id informado não existe no banco de dados "));
        for (int i=0; i < telefoneDto.getNumeros().size(); i++ ){
            Telefone telefone = new Telefone(telefoneDto, i);
            if(telefone.getFuncionarios() == null) {
                telefone.setFuncionarios(new ArrayList<>());
            }
            telefone.getFuncionarios().add(funcionario);
            if(funcionario.getTelefones() == null) {
                funcionario.setTelefones(new ArrayList<>());
            }
            try{
                Telefone telefoneExiste = telefoneRepository.findByNumero(telefoneDto.getNumeros().get(i));

                funcionarioRepository.ligarTelefoneExistenteEmFuncionario
                        (telefoneDto.getIdFuncionario(), telefoneExiste.getId());

            } catch (Exception ex) {
                funcionario.getTelefones().add(telefoneRepository.save(telefone));

            }
            funcionarioRepository.save(funcionario);
        }
        return funcionario;
    }

//    @Transactional //NÃO ESTOU USANDO, MAS TEM USO DE GSON PARA LEMBRAR
//    public Funcionario cadastrarTelefoneEmFuncionario(Long idFuncionario, String stringTelefones) {
//        Funcionario funcionario = funcionarioRepository.findById(idFuncionario).orElseThrow(() ->
//                new ExceptionIdNaoEcontrado("Id não encontrado: " + idFuncionario,
//                        "O Id informado não existe no banco de dados "));
//        Gson gson = new Gson();
//        Type tipoLista = new TypeToken<ArrayList<Telefone>>() {}.getType();
//        ArrayList<Telefone> listaTelefones  = gson.fromJson(stringTelefones, tipoLista);
//        for (Telefone telefoneDaLista:
//             listaTelefones) {
//            Telefone telefone = telefoneDaLista;
//            if(telefone.getFuncionarios() == null) {
//                telefone.setFuncionarios(new ArrayList<>());
//            }
//            telefone.getFuncionarios().add(funcionario);
//            if(funcionario.getTelefones() == null) {
//                funcionario.setTelefones(new ArrayList<>());
//            }
//            try{
//                Telefone telefoneExiste = telefoneRepository.findByNumero(telefoneDaLista.getNumero());
//                funcionarioRepository.ligarTelefoneExistenteEmFuncionario(idFuncionario, telefoneExiste.getId());
//            } catch (Exception ex) {
//                funcionario.getTelefones().add(telefoneRepository.save(telefone));
//            }
//        }
//        return funcionario;
//    }


    /*
    Primeira vez funciona só acrescentar coisa nova
    Segunda vez deleta tanto os telefones, quanto os funcionários envolvidos
     */
    @Transactional
    public Funcionario atualizarTelefone(TelefoneDto dto){
        //1verificar se numero ja existe e
            //1.1se existe em já funcionario, não fazer nada (FEITO)
            //1.2se existe, mas não tem vinculo, não cadastrar telefone novo, só vincular (FEITO)
            //1.3se não existe, salvar telefone e vincular (FEITO)
        //2verificar se algum número antigo vinculado ao funcionario não está na lista (dando erro)
            //2.1se tal número não estiver nas duas listas, deletar ele de funcionario
                //2.1.1se não tiver mais nenhum vínculo nele, deletar ele de telefones
        Funcionario funcionario = funcionarioRepository.findById(dto.getIdFuncionario()).
                orElseThrow(() -> new ExceptionIdNaoEcontrado
                ("Id não encontrado: " + dto.getIdFuncionario(),
                "O Id informado não existe no banco de dados "));

        List<Telefone> listaTelAntigos = funcionario.getTelefones();
        paraCadaNumero: for (BigInteger numero:
             dto.getNumeros()) {
            try{
                Telefone telefone = telefoneRepository.findByNumero(numero);
                //O número já possui id em tb_telefone, ou seja, já existe
                if(!(telefone == null)){
                    //Se o telefone já tem ligação com esse funcionário, não faz nada
                    if(telefoneRepository.telefoneExisteEmFuncionario(funcionario.getId(),telefone.getId())){
                        continue paraCadaNumero;
                    } else { //Se ele existe e não tem ligação com funcionário, faz a ligação
                        funcionarioRepository.ligarTelefoneExistenteEmFuncionario
                                (funcionario.getId(), telefone.getId());
                        continue paraCadaNumero;
                    }
                } else {
                    //cadastrar telefone que não existe
                    telefone = new Telefone();
                    telefone.setNumero(numero);
                    if(telefone.getFuncionarios() == null) {
                        telefone.setFuncionarios(new ArrayList<>());
                    }
                    telefone.getFuncionarios().add(funcionario); //--> Como verifica isso
                    funcionario.getTelefones().add(telefoneRepository.save(telefone));
                }
            }catch (Exception ex){
                System.out.println(ex.getMessage());
                System.out.println(ex.getLocalizedMessage());
            }
        }

        //verificar quais números não estão mais na lista e deleta eles
        List<Telefone> telParaDeletar = listaTelAntigos.stream()
                .filter(telefoneNovo -> (!(dto.getNumeros().contains(telefoneNovo.getNumero()))))
                .collect(Collectors.toList());

        for (Telefone telefoneDel:
             telParaDeletar) {
            telefoneRepository.deletarTelefoneApenasDoFuncionario(funcionario.getId(), telefoneDel.getId());
            if(telefoneRepository.telefoneNaoTemVinculoAlgum(telefoneDel.getId())==0){
                funcionario.getTelefones().remove(telefoneDel); //--> Como verifica isso
                //telefoneRepository.deleteTelefoneNativoById(telefoneDel.getId());
            }

        }
        return funcionarioRepository.findById(funcionario.getId()).get();
    }





    @Transactional
    public ResponseEntity<?> deletarTelefone(Long id) {
        telefoneRepository.findById(id).orElseThrow(() -> new ExceptionIdNaoEcontrado
                ("Id não encontrado: " + id,
                "O Id informado não existe no banco de dados "));

        if(telefoneRepository.telefoneNaoTemVinculoAlgum(id)!=0){
            telefoneRepository.deletarTodosVinculosTelefone(id);
        }
        telefoneRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }

    @Transactional
    public ResponseEntity<?> deletarTelefoneApenasDoFuncionario(Long idFuncionario, Long idTelefone) {
        funcionarioRepository.findById(idFuncionario).orElseThrow(
                () -> new ExceptionIdNaoEcontrado("Id não encontrado: " + idFuncionario,
                "O Id informado não existe no banco de dados "));
        telefoneRepository.findById(idTelefone).orElseThrow(
                () -> new ExceptionIdNaoEcontrado("Id não encontrado: " + idTelefone,
                "O Id informado não existe no banco de dados "));

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
