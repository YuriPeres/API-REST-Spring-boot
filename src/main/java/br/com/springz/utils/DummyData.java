package br.com.springz.utils;

import br.com.springz.model.Endereco;
import br.com.springz.model.Funcionario;
import br.com.springz.model.Telefone;
import br.com.springz.repository.FuncionarioRepository;
import br.com.springz.repository.TelefoneRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@AllArgsConstructor
@Component
public class DummyData {
    private final FuncionarioRepository funcionarioRepository;
//    private final TelefoneRepository telefoneRepository;

    //@PostConstruct
    public void funcionarioCadastroFicticio() {

//        Telefone telefone = new Telefone(999887766L);
//        ArrayList<Telefone> telefoneLista = new ArrayList<>();
//        telefoneLista.add(telefone);

        Endereco enderecoUm = new Endereco("99999000","rua bonita","apartamento 1",
                "centro","Cidade","RS");
        Endereco enderecoDois = new Endereco("99999000","rua bonita","apartamento 2",
                "centro","Cidade","RS");
        Endereco enderecoTres = new Endereco("99999000","rua bonita","apartamento 3",
                "centro","Cidade","RS");
        Endereco enderecoQuatro = new Endereco("99999000","rua bonita","apartamento 4",
                "centro","Cidade","RS");
        Funcionario funcionarioUm = new Funcionario("José", "Silva", "josesilva@email.com", 25, true, enderecoUm);
        Funcionario funcionarioDois = new Funcionario("Maria", "Silveira", "mariasilveira@email.com", 32, true, enderecoDois);
        Funcionario funcionarioTres = new Funcionario("Carlos", "Machado", "carlosmachado@jmail.com", 31, false, enderecoTres);
        Funcionario funcionarioQuatro = new Funcionario("Carlota", "Machadinha", "carlotamachadinha@jmail.com", 26, false, enderecoQuatro);

//        funcionarioUm.setTelefones(new ArrayList<>());
//        funcionarioUm.getTelefones().add(telefoneRepository.save(telefone));
        List<Funcionario> funcionarios = Arrays.asList(
                funcionarioUm,
                funcionarioDois,
                funcionarioTres,
                funcionarioQuatro);

        for(Funcionario funcionario: funcionarios){
            funcionarioRepository.save(funcionario);
        }
    }

}
