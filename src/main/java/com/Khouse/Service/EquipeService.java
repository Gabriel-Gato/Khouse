package com.Khouse.Service;


import com.Khouse.Model.Equipe;
import com.Khouse.Model.Gato;
import com.Khouse.Repository.EquipeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.UUID;

@Service
public class EquipeService {
    @Autowired
    private EquipeRepository equipeRepository;

    public Equipe salvarImagem(long id, MultipartFile file) throws IOException {
        String uploadDIR = "uploads/equipe";

        Path uploadPath = Paths.get(uploadDIR);
        if (!Files.exists(uploadPath)){
            Files.createDirectories(uploadPath);
        }

        String nomeArquivo = UUID.randomUUID() + "_" + file.getOriginalFilename();
        Path caminho = uploadPath.resolve(nomeArquivo);
        Files.copy(
                file.getInputStream(), caminho, StandardCopyOption.REPLACE_EXISTING);

        Equipe equipe = equipeRepository.findById(id).orElseThrow(() -> new RuntimeException("Gato não encontrado"));

        equipe.setImagem(nomeArquivo);
        return equipeRepository.save(equipe);
    }

    public Equipe salvar(Equipe equipe){
        return equipeRepository.save(equipe);
    }

    public Equipe buscarPorId(Long id){
        return equipeRepository.findById(id).orElse(null);
    }

    public Equipe buscarPorNome(String nome) {return equipeRepository.findByNome(nome);}

    public List<Equipe> listarTodos(){return equipeRepository.findAll();}

    public void deletar(Equipe equipe){equipeRepository.delete(equipe);}

    public void deletarPorId(Long id){equipeRepository.deleteById(id);}

    public Equipe atualizar(Long id, Equipe equipeAtualizado){
        Equipe equipe = equipeRepository.findById(id).orElseThrow(() -> new RuntimeException("Gato não encontrado"));

        if(equipeAtualizado.getNome() != null)
            equipe.setNome(equipeAtualizado.getNome());

        if(equipeAtualizado.getCargo() != null)
            equipe.setCargo(equipeAtualizado.getCargo());

        if(equipeAtualizado.getImagem() != null)
            equipe.setImagem(equipeAtualizado.getImagem());

        if(equipeAtualizado.getEmail() != null)
            equipe.setEmail(equipeAtualizado.getEmail());

        return equipeRepository.save(equipeAtualizado);
    }
}
