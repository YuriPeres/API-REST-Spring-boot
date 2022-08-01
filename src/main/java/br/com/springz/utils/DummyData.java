package br.com.springz.utils;

import br.com.springz.model.Funcionario;
import br.com.springz.repository.FuncionarioRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@AllArgsConstructor
@Component
public class DummyData {
    private final FuncionarioRepository funcionarioRepository;

    //@PostConstruct
    public void funcionarioCadastroFicticio() {

        Funcionario funcionarioUm = new Funcionario("José", "Silva", "josesilva@email.com", 25, true);
        Funcionario funcionarioDois = new Funcionario("Maria", "Silveira", "mariasilveira@email.com", 32, true);
        Funcionario funcionarioTres = new Funcionario("Carlos", "Machado", "carlosmachado@jmail.com", 31, false);
        Funcionario funcionarioQuatro = new Funcionario("Carlota", "Machadinha", "carlotamachadinha@jmail.com", 26, false);

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
