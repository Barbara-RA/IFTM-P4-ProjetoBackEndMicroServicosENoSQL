package br.edu.iftm.user_api.dto;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
    private String nome;
    private String cpf;
    private String endereco;
    private String email;
    private String telefone;
    private LocalDateTime dataCadastro;
}
