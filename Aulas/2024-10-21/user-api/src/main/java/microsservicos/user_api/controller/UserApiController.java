package microsservicos.user_api.controller;

import org.springframework.web.bind.annotation.RestController;

import java.util.*;
import java.time.LocalDateTime;

import jakarta.annotation.PostConstruct;
import microsservicos.user_api.dto.UserDTO;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/user")
public class UserApiController {

    protected static final List<UserDTO> usuarios = new ArrayList<>();

    @GetMapping
    public List<UserDTO> getUsers(){
        return usuarios;
    }

    @GetMapping("/")
    public String getUser(){
        return "Hello World! Spring boot is working!";
    }

    @PostConstruct
    public void initateList(){

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
        userDTO2.setTelefone("1234-3456");
        userDTO2.setDataCadastro(LocalDateTime.now());

        UserDTO userDTO3 = new UserDTO();
        userDTO3.setNome("Bruna");
        userDTO3.setCpf("678");
        userDTO3.setEndereco("Rua c");
        userDTO3.setEmail("bruna@email.com");
        userDTO3.setTelefone("1234-3457");
        userDTO3.setDataCadastro(LocalDateTime.now());
        usuarios.add(userDTO);
        usuarios.add(userDTO2);
        usuarios.add(userDTO3);
    }

    @GetMapping("/{cpf}")
    public UserDTO getUserFiltro(@PathVariable String cpf) {
        return usuarios
            .stream()
            .filter(userDTO -> userDTO.getCpf().equals(cpf))
            .findFirst()
            .orElseThrow(() -> new RuntimeException("User not found."));
    }
    
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserDTO insertUser(@RequestBody @Valid UserDTO userDTO) {
        
        userDTO.setDataCadastro(LocalDateTime.now());
        usuarios.add(userDTO);
        return userDTO;
    }
    
    @DeleteMapping("{cpf}")
    public boolean remover(@PathVariable String cpf){
        return usuarios
            .removeIf(userDTO -> userDTO.getCpf().equals(cpf));
    }

    
}