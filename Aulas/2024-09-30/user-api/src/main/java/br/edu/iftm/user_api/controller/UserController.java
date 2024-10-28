package br.edu.iftm.user_api.controller;

import org.springframework.web.bind.annotation.RestController;

import br.edu.iftm.user_api.dto.UserDTO;
import jakarta.annotation.PostConstruct;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("/user")
public class UserController {

    public static List<UserDTO> usuarios = new ArrayList<UserDTO>();

    @GetMapping
    public List<UserDTO> getUsers() {
        return usuarios;
    }

    @PostConstruct
    public void initiateList() {
        UserDTO userDTO = new UserDTO();
        userDTO.setNome("Eduardo");
        userDTO.setCpf("123");
        userDTO.setEndereco("Rua a");
        userDTO.setEmail("eduardo@email.com");
        userDTO.setTelefone("1234-3454");
        userDTO.setDataCadastro(LocalDateTime.now());

        UserDTO userDTO2 = new UserDTO();
        userDTO2.setNome("Luiz");
        userDTO2.setCpf("456");
        userDTO2.setEndereco("Rua b");
        userDTO2.setEmail("luiz@email.com");
        userDTO2.setTelefone("1234-3454");
        userDTO2.setDataCadastro(LocalDateTime.now());
        UserDTO userDTO3 = new UserDTO();

        userDTO3.setNome("Bruna");
        userDTO3.setCpf("678");
        userDTO3.setEndereco("Rua c");
        userDTO3.setEmail("bruna@email.com");
        userDTO3.setTelefone("1234-3454");
        userDTO3.setDataCadastro(LocalDateTime.now());
        usuarios.add(userDTO);
        usuarios.add(userDTO2);
        usuarios.add(userDTO3);
    }

    
}
