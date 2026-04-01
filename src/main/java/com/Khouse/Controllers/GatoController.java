package com.Khouse.Controllers;


import com.Khouse.Model.Gato;
import com.Khouse.Service.GatoService;
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
@RequestMapping("api/gato")
@CrossOrigin(origins = "*")
public class GatoController {
    @Autowired
    private GatoService gatoService;

    @PostMapping("upload/{id}")
    public ResponseEntity<Gato> uploadImagem(@PathVariable Long id, @RequestParam("imagem")MultipartFile imagem) throws IOException{
        Gato atualizado = gatoService.salvarImagem(id, imagem);
        return  ResponseEntity.ok(atualizado);
    }

    @PostMapping
    public ResponseEntity<Gato> cadastrarGato(@RequestBody Gato gato){
        try{
            Gato salvo = gatoService.salvar(gato);
            return  ResponseEntity.status(HttpStatus.CREATED).body(salvo);
        } catch (IllegalArgumentException e){
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Gato> buscarPorID(@PathVariable Long id){
        Gato gato = gatoService.buscarPorId(id);

        if(gato != null){
            return ResponseEntity.ok(gato);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping
    public ResponseEntity<List<Gato>> listarGatos(){
        return ResponseEntity.ok(gatoService.listarTodos());
    }

    @GetMapping("/Imagem/{nomeImagem}")
    public ResponseEntity<byte[]> obterImagem(@PathVariable String nomeImagem)
        throws IOException {
        File imagem = new File("uploads/gatos/" + nomeImagem);
        if(!imagem.exists()){
            return ResponseEntity.notFound().build();
        }

        byte[] conteudo = Files.readAllBytes(imagem.toPath());
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_JPEG);
        return new ResponseEntity<>(conteudo, headers, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Gato> atualizarGato(@PathVariable Long id, @RequestBody Gato gato){
        Gato atualizado = gatoService.atualizar(id, gato);
        if (atualizado != null){
            return ResponseEntity.ok(atualizado);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("nome/{nome}")
        public ResponseEntity<Gato> buscarPorNome(@PathVariable String nome) {
            Gato gato = gatoService.buscarPorNome(nome);
            return gato != null ? ResponseEntity.ok(gato) : ResponseEntity.notFound().build();

        }

        @PatchMapping("nome/{nome}")
        public ResponseEntity<Gato> atualizarParcial(@PathVariable String nome, @RequestBody Gato atualizacao){
            Gato gatoexistente = gatoService.buscarPorNome(nome);

            if (gatoexistente == null){
                return ResponseEntity.notFound().build();
            }

            if (atualizacao.getNome() != null){
                gatoexistente.setNome(atualizacao.getNome());
            }

            if (atualizacao.getIdade() != null) {
                gatoexistente.setIdade(atualizacao.getIdade());
            }

           if (atualizacao.getVacinado() != null){
               gatoexistente.setVacinado(atualizacao.getVacinado());
           }

           if (atualizacao.getCastrado() != null){
               gatoexistente.setCastrado(atualizacao.getCastrado());
           }

           if (atualizacao.getPersonalidade() != null){
               gatoexistente.setPersonalidade(atualizacao.getPersonalidade());
           }

            if (atualizacao.getGenero() != null){
                gatoexistente.setGenero(atualizacao.getGenero());
            }

            if (atualizacao.getDescricao() != null){
                gatoexistente.setDescricao(atualizacao.getDescricao());
            }


            Gato atualizado = gatoService.salvar(gatoexistente);
            return ResponseEntity.ok(atualizado);
        }

        @DeleteMapping("/nome/{nome}")
        public  ResponseEntity<Void> deletarPorNome(@PathVariable String nome){
            Gato gato = gatoService.buscarPorNome(nome);
            if(gato == null){
                return ResponseEntity.notFound().build();
            }

            gatoService.deletar(gato);
            return ResponseEntity.noContent().build();
        }


        @DeleteMapping("/{id}")
        public ResponseEntity<?> deletarGato(@PathVariable Long id){
            try {
                gatoService.deletarPorId(id);
                return ResponseEntity.ok().build();
            } catch (Exception e){
                e.printStackTrace();
                return ResponseEntity.status(500).body("Erro ao deletar gato" + e.getMessage());
            }
        }



}
