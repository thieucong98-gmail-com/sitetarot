package don616.apitarot.model.service;

import don616.apitarot.controller.form.CadastrarUsuarioForm;
import don616.apitarot.model.entity.Usuario;
import don616.apitarot.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.Map;
import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public ResponseEntity<?> cadastrarUsuario(CadastrarUsuarioForm form){

            if (usuarioRepository.findByEmail(form.getEmail()).isEmpty()) {
                Usuario usuario = form.converter();
                usuarioRepository.save(usuario);
                return ResponseEntity.status(201).body("Usuario cadastrado com sucesso");
            } else {
                return ResponseEntity.status(409).body("Email já existe na base de dados");
            }

    }

    public Optional<Usuario> buscarUsuarioPorId(Long id){
        return usuarioRepository.findById(id);
    }


    public Page<Usuario> listarUsuarios(Pageable pageable, Map<String, String> param){

        return usuarioRepository.findAll(pageable);

    }


    @Transactional
    public ResponseEntity<String> deletarUsuario(Long id){
        Optional<Usuario> usuario = this.buscarUsuarioPorId(id);

        if(usuario.isPresent()){
            usuarioRepository.deleteById(id);
            return ResponseEntity.status(200).body("Usuario deletado com sucesso");
        } else {
            return ResponseEntity.status(404).body("Usuário não encontrado");
        }
    }



    public ResponseEntity<String> atualizarUsuario(Long id, CadastrarUsuarioForm form) {

            Optional<Usuario> usuario = buscarUsuarioPorId(id);

            if (usuario.isPresent()) {

                Usuario usuarioCadastrado = usuario.get();

                if (!usuarioCadastrado.getNome().equals(form.getNome())) {
                    usuarioCadastrado.setNome(form.getNome());
                }
                if (!usuarioCadastrado.getEmail().equals(form.getEmail())) {
                    usuarioCadastrado.setEmail(form.getEmail());
                }
                if (!usuarioCadastrado.getSenha().equals(form.getSenha())) {
                    usuarioCadastrado.setSenha(form.getSenha());
                }
                if (!usuarioCadastrado.getDataNascimento().equals(form.getDataNascimento())) {
                    usuarioCadastrado.setDataNascimento(form.getDataNascimento());
                }
                if (!usuarioCadastrado.getGenero().equals(form.getGenero())) {
                    usuarioCadastrado.setGenero(form.getGenero());
                }

                usuarioRepository.save(usuarioCadastrado);
                return ResponseEntity.status(201).body("Usuario atualizado com sucesso");

            } else {
                return ResponseEntity.status(404).body("Usuario não encontrado");
            }

    }

    public ResponseEntity<?> getUsuarioPorId(Long id) {

        Optional<Usuario> usuario = buscarUsuarioPorId(id);

        if(usuario.isPresent()){
            return ResponseEntity.status(200).body(usuario);
        }
        return ResponseEntity.status(404).body("Usuario não encontrado");
    }
}
