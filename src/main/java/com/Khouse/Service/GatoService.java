package com.Khouse.Service;


import com.Khouse.Model.Gato;
import com.Khouse.Repository.GatoRepository;
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
public class GatoService {
    @Autowired
    private GatoRepository gatoRepository;

    public Gato salvarImagem(long id, MultipartFile file) throws IOException{
        String uploadDIR = "uploads/gatos";

        Path uploadPath = Paths.get(uploadDIR);
        if (!Files.exists(uploadPath)){
            Files.createDirectories(uploadPath);
        }

        String nomeArquivo = UUID.randomUUID() + "_" + file.getOriginalFilename();
        Path caminho = uploadPath.resolve(nomeArquivo);
        Files.copy(
                file.getInputStream(), caminho, StandardCopyOption.REPLACE_EXISTING);

        Gato gato = gatoRepository.findById(id).orElseThrow(() -> new RuntimeException("Gato não encontrado"));

        gato.setImagem(nomeArquivo);
        return gatoRepository.save(gato);
    }

    public Gato salvar(Gato gato){
        return gatoRepository.save(gato);
    }

    public List<Gato> listarTodos(){
        return gatoRepository.findAll();
    }

    public Gato buscarPorNome(String nome){
        return gatoRepository.findByNome(nome);
    }

    public Gato buscarPorId(Long id){
        return  gatoRepository.findById(id).orElse(null);
    }

    public Gato atualizar(Long id, Gato gatoAtualizado){
        Gato gato = gatoRepository.findById(id).orElseThrow(() -> new RuntimeException("Gato não encontrado"));

        if(gatoAtualizado.getNome() != null)
            gato.setNome(gatoAtualizado.getNome());

        if(gatoAtualizado.getIdade() != null)
            gato.setIdade(gatoAtualizado.getIdade());

        if(gatoAtualizado.getVacinado() != null)
            gato.setVacinado(gatoAtualizado.getVacinado());

        if(gatoAtualizado.getCastrado() != null)
            gato.setCastrado(gatoAtualizado.getCastrado());

        if(gatoAtualizado.getPersonalidade() != null)
            gato.setPersonalidade(gatoAtualizado.getPersonalidade());

        if(gatoAtualizado.getGenero() != null)
            gato.setGenero(gatoAtualizado.getGenero());

        if(gatoAtualizado.getDescricao() != null)
            gato.setDescricao(gatoAtualizado.getDescricao());

        if(gatoAtualizado.getImagem() != null)
            gato.setImagem(gatoAtualizado.getImagem());

        return gatoRepository.save(gato);
    }

    public void deletar(Gato gato){
        gatoRepository.delete(gato);
    }

    public void deletarPorId(Long id){
        Gato gato = gatoRepository.findById(id).orElseThrow(() -> new RuntimeException("Gato não encontrado"));
        gatoRepository.delete(gato);
    }


}
