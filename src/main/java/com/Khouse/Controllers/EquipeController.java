package com.Khouse.Controllers;



import com.Khouse.Model.Equipe;
import com.Khouse.Service.EquipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

@RestController
@RequestMapping("/api/equipe")
@CrossOrigin(origins = "*")
public class EquipeController {
    @Autowired
    EquipeService equipeService;

    @PostMapping("upload/{id}")
    public ResponseEntity<Equipe> uploadImagem(@PathVariable Long id, @RequestParam("imagem") MultipartFile imagem) throws IOException {
        Equipe atualizado = equipeService.salvarImagem(id, imagem);
        return  ResponseEntity.ok(atualizado);
    }

    @PostMapping
    public ResponseEntity<Equipe> cadastrarEquipe(@RequestBody Equipe equipe){
        try{
            Equipe salvo = equipeService.salvar(equipe);
            return  ResponseEntity.status(HttpStatus.CREATED).body(salvo);
        } catch (IllegalArgumentException e){
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Equipe> buscarPorID(@PathVariable Long id){
        Equipe equipe = equipeService.buscarPorId(id);

        if(equipe != null){
            return ResponseEntity.ok(equipe);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping
    public ResponseEntity<List<Equipe>> listarEquipe(){
        return ResponseEntity.ok(equipeService.listarTodos());
    }

    @GetMapping("/Imagem/{nomeImagem}")
    public ResponseEntity<byte[]> obterImagem(@PathVariable String nomeImagem)
            throws IOException {
        File imagem = new File("uploads/equipe/" + nomeImagem);
        if(!imagem.exists()){
            return ResponseEntity.notFound().build();
        }

        byte[] conteudo = Files.readAllBytes(imagem.toPath());
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_JPEG);
        return new ResponseEntity<>(conteudo, headers, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Equipe> atualizarGato(@PathVariable Long id, @RequestBody Equipe equipe){
        Equipe atualizado = equipeService.atualizar(id, equipe);
        if (atualizado != null){
            return ResponseEntity.ok(atualizado);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("nome/{nome}")
    public ResponseEntity<Equipe> buscarPorNome(@PathVariable String nome) {
        Equipe equipe = equipeService.buscarPorNome(nome);
        return equipe != null ? ResponseEntity.ok(equipe) : ResponseEntity.notFound().build();

    }

    @DeleteMapping("/nome/{nome}")
    public  ResponseEntity<Void> deletarPorNome(@PathVariable String nome){
        Equipe equipe = equipeService.buscarPorNome(nome);
        if(equipe == null){
            return ResponseEntity.notFound().build();
        }

        equipeService.deletar(equipe);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletarEquipe(@PathVariable Long id){
        try {
            equipeService.deletarPorId(id);
            return ResponseEntity.ok().build();
        } catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(500).body("Erro ao deletar membro da equipe" + e.getMessage());
        }
    }

    @PatchMapping("nome/{nome}")
    public ResponseEntity<Equipe> atualizarParcial(@PathVariable String nome, @RequestBody Equipe atualizacao){
        Equipe equipeexistente = equipeService.buscarPorNome(nome);

        if (equipeexistente == null){
            return ResponseEntity.notFound().build();
        }

        if (atualizacao.getNome() != null){
            equipeexistente.setNome(atualizacao.getNome());
        }

        if (atualizacao.getCargo() != null){
            equipeexistente.setCargo(atualizacao.getCargo());
        }

        if (atualizacao.getImagem() != null){
            equipeexistente.setImagem(atualizacao.getImagem());
        }

        if (atualizacao.getEmail() != null){
            equipeexistente.setEmail(atualizacao.getEmail());
        }

        Equipe atualizado = equipeService.salvar(equipeexistente);
        return ResponseEntity.ok(atualizado);
    }

}
