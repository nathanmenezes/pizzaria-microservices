package br.com.cruzvita.usuario.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import br.com.cruzvita.usuario.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long>{

    boolean existsByCpf(String cpf);

    boolean existsByTelefone(String telefone);
    
}
