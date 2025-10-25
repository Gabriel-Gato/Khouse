package com.Khouse.Service;

import com.Khouse.Model.Enum.Role;
import com.Khouse.Model.Usuario;
import com.Khouse.Repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService {
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public Usuario cadastrar(Usuario usuario){
        if (usuarioRepository.existsByEmail(usuario.getEmail())){
            throw new IllegalArgumentException("Email ja Cadastrado");
        }

        usuario.setSenha(passwordEncoder.encode(usuario.getSenha()));

        if (usuario.getRole() == null) {
            usuario.setRole(Role.USER);
        }

        return usuarioRepository.save(usuario);
    }

    public Usuario salvar(Usuario usuario){
        return usuarioRepository.save(usuario);
    }

    public List<Usuario> listartodos(){
        return usuarioRepository.findAll();
    }

    public Usuario buscarPorEmail(String email){
        return usuarioRepository.findByEmail(email)
                .orElseThrow(()-> new IllegalArgumentException("Usuario não encontrado com o email: " + email));
    }

    public  Usuario buscarPorId(Long id){
        return usuarioRepository.findById(id).orElse(null);
    }

    public Usuario atualizar(Long id, Usuario usuarioAtualizado){
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Usuario não encontrado"));

        if (usuarioAtualizado.getRole() != null){
            usuario.setRole(usuarioAtualizado.getRole());
        }
        if (usuarioAtualizado.getEmail() != null && !usuarioAtualizado.getEmail().equals(usuario.getEmail())) {
            if (usuarioRepository.existsByEmail(usuarioAtualizado.getEmail())){
                throw new IllegalArgumentException("Email ja cadastrado");
            }
            usuario.setEmail(usuarioAtualizado.getEmail());
        }

        if (usuarioAtualizado.getSenha() != null && !usuarioAtualizado.getSenha().isEmpty())
            usuario.setSenha(usuarioAtualizado.getSenha());

        return usuarioRepository.save(usuario);
    }

    public void deletar(Usuario usuario){
        usuarioRepository.delete(usuario);
    }

    public void deletarPorId(Long id){
        Usuario usuario = usuarioRepository.findById(id).orElseThrow(() -> new RuntimeException("Login não encontrado"));
        usuarioRepository.delete(usuario);
    }

    public Usuario autenticar(String email, String senha) {
        Usuario usuario = usuarioRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("Usuario não encontrado"));

        if (usuario != null && passwordEncoder.matches(senha, usuario.getSenha())) {
            return usuario;
        } else {
            throw new IllegalArgumentException("Senha incorreta");
        }

    }

    //Service da maioria feito, estou com muito sono e vou continuar amanha para não afetar meu desempenho
    //Meta par amanha: Service dos gatos e Controller das duas classes











}


