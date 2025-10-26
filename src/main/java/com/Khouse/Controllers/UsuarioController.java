package com.Khouse.Controllers;


import com.Khouse.Model.Usuario;
import com.Khouse.Service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/usuario")
@CrossOrigin(origins = "*")
public class UsuarioController {
    @Autowired private UsuarioService usuarioService;
    @Autowired private PasswordEncoder passwordEncoder;

    @PostMapping
    public ResponseEntity<Usuario> cadastrarUsuario(@RequestBody Usuario usuario){
        try{
            Usuario salvo = usuarioService.cadastrar(usuario);
            return ResponseEntity.status(HttpStatus.CREATED).body(salvo);
        } catch (IllegalArgumentException e){
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }

    @GetMapping
    public ResponseEntity<List<Usuario>> listarUsuario(){
        return ResponseEntity.ok(usuarioService.listartodos());
    }


    @GetMapping("/{id}")
    public ResponseEntity<Usuario> buscaPorID(@PathVariable Long id){
        Usuario usuario = usuarioService.buscarPorId(id);
        if (usuario != null){
            return ResponseEntity.ok(usuario);
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Usuario> atualizarUsuario(@PathVariable Long id, @RequestBody Usuario usuario){
        Usuario atualizado = usuarioService.atualizar(id, usuario);
        if (atualizado != null){
            return ResponseEntity.ok(atualizado);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/Login")
    public ResponseEntity<Usuario> login(@RequestBody Usuario loginRequest){
        Usuario autenticado = usuarioService.autenticar(loginRequest.getEmail(), loginRequest.getSenha());
        if (autenticado != null) {
            return ResponseEntity.ok(autenticado);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    @GetMapping("email/{email}")
    public ResponseEntity<Usuario> buscarPorEmail(@PathVariable String email){
        Usuario usuario = usuarioService.buscarPorEmail(email);
        return usuario != null ? ResponseEntity.ok(usuario) : ResponseEntity.notFound().build();
    }

    @PatchMapping("email/{email}")
    public ResponseEntity<Usuario> atualizarParcial(@PathVariable String email, @RequestBody Usuario atualizacao) {
        try {
            Usuario usuarioExistente = usuarioService.buscarPorEmail(email);

            if (atualizacao.getEmail() != null) {
                usuarioExistente.setEmail(atualizacao.getEmail());
            }
            if (atualizacao.getRole() != null) {
                usuarioExistente.setRole(atualizacao.getRole());
            }
            if (atualizacao.getSenha() != null && !atualizacao.getSenha().isEmpty()) {
                usuarioExistente.setSenha(passwordEncoder.encode(atualizacao.getSenha()));
            }

            Usuario atualizado = usuarioService.salvar(usuarioExistente);
            return ResponseEntity.ok(atualizado);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }


    @DeleteMapping("/email/{email}")
    public ResponseEntity<Void> deletarPorEmail(@PathVariable String email){
        Usuario usuario = usuarioService.buscarPorEmail(email);
        if(usuario == null){
            return  ResponseEntity.notFound().build();
        }

        usuarioService.deletar(usuario);
        return ResponseEntity.noContent().build();
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletarUsuario(@PathVariable Long id){
        try{
            usuarioService.deletarPorId(id);
            return ResponseEntity.ok().build();
        } catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(500).body("Eroo ao deletar login " + e.getMessage());
        }
    }


    @PostMapping("verificar_senha/{email}")
    public ResponseEntity<Boolean> verificarSenha(@PathVariable String email, @RequestBody Map<String, String> senhaRequest){
        String senha = senhaRequest.get("senha");
        boolean senhaCorreta = usuarioService.verificarSenha(email, senha);
        return ResponseEntity.ok(senhaCorreta);
    }


}
