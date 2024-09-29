package com.api_vendinha.api.domain.service;

import com.api_vendinha.api.Infrastructure.repository.ProdutoRepository;
import com.api_vendinha.api.Infrastructure.repository.UserRepository;
import com.api_vendinha.api.domain.dtos.request.UserRequestDto;
import com.api_vendinha.api.domain.dtos.response.UserResponseDto;
import com.api_vendinha.api.domain.entities.Produto;
import com.api_vendinha.api.domain.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Implementação do serviço de usuários.
 *
 * Esta classe fornece a implementação dos métodos definidos na interface UserServiceInterface,
 * lidando com a lógica de negócios relacionada aos usuários, como criar e atualizar usuários.
 */
@Service
public class UserServiceImpl implements UserServiceInterface {

    // Repositório para a persistência de dados de usuários.
    private final UserRepository userRepository;
    private final ProdutoRepository produtoRepository;

    /**
     * Construtor para injeção de dependência do UserRepository.
     *
     * @param userRepository O repositório de usuários a ser injetado.
     */
    @Autowired
    public UserServiceImpl(
            UserRepository userRepository,
            ProdutoRepository produtoRepository
    ) {
        this.userRepository = userRepository;
        this.produtoRepository = produtoRepository;
    }
    /**
     * Salva um novo usuário ou atualiza um usuário existente.
     *
     * Cria uma nova entidade User a partir dos dados fornecidos no UserRequestDto, persiste essa
     * entidade no banco de dados, e retorna um UserResponseDto com as informações do usuário salvo.
     *
     * @param userRequestDto DTO contendo os dados do usuário a ser salvo ou atualizado.
     * @return DTO com as informações do usuário salvo, incluindo o ID gerado e o nome.
     */
    @Override
    public UserResponseDto save(UserRequestDto userRequestDto) {
        // Cria uma nova instância de User.
        User user = new User();
        // Define o nome do usuário a partir do DTO.
        user.setName(userRequestDto.getName());
        user.setEmail(userRequestDto.getEmail());
        user.setPassword(userRequestDto.getPassword());
        user.setIs_activate(userRequestDto.getIs_activate());
        user.setCpf_cnpj(userRequestDto.getCpf_cnpj());

        // Salva o usuário no banco de dados e obtém a entidade persistida com o ID gerado.
        User savedUser = userRepository.save(user);

        List<Produto> produtos = userRequestDto.getProdutoRequestDto().stream().map(
                dto -> {
                    Produto produto = new Produto();
                    produto.setNome(dto.getNome());
                    produto.setPreco(dto.getPreco());
                    produto.setQuantidade(dto.getQuantidade());
                    produto.setUser(savedUser);
                    return produto;
                }).collect(Collectors.toList());

        produtoRepository.saveAll(produtos);
        // Cria um DTO de resposta com as informações do usuário salvo.
        UserResponseDto userResponseDto = new UserResponseDto();
        userResponseDto.setId(savedUser.getId());
        userResponseDto.setName(savedUser.getName());
        userResponseDto.setEmail(savedUser.getEmail());
        userResponseDto.setPassword(savedUser.getPassword());
        userResponseDto.setIs_activate(savedUser.getIs_activate());
        userResponseDto.setCpf_cnpj(savedUser.getCpf_cnpj());

        // Retorna o DTO com as informações do usuário salvo.
        return userResponseDto;
    }

    @Override
    public UserResponseDto update(Long id, UserRequestDto userRequestDto) {
        User userexiste= userRepository.findById(id).orElseThrow();
        userexiste.setName(userRequestDto.getName());
        userexiste.setEmail(userRequestDto.getEmail());
        userexiste.setPassword((userRequestDto.getPassword()));
        userexiste.setIs_activate((userRequestDto.getIs_activate()));
        userexiste.setCpf_cnpj(userRequestDto.getCpf_cnpj());

        userRepository.save(userexiste);

        UserResponseDto userResponseDto=new UserResponseDto();
        userResponseDto.setId(userexiste.getId());
        userResponseDto.setName(userexiste.getName());
        userResponseDto.setEmail(userexiste.getEmail());
        userResponseDto.setPassword(userexiste.getPassword());
        userResponseDto.setIs_activate(userexiste.getIs_activate());
        userResponseDto.setCpf_cnpj(userexiste.getCpf_cnpj());
        return userResponseDto;
    }

    @Override
    public UserResponseDto buscar(Long id) {
        User userexiste= userRepository.findById(id).orElseThrow();

        UserResponseDto userResponseDto=new UserResponseDto();
        userResponseDto.setId(userexiste.getId());
        userResponseDto.setName(userexiste.getName());
        userResponseDto.setEmail(userexiste.getEmail());
        userResponseDto.setPassword(userexiste.getPassword());
        userResponseDto.setIs_activate(userexiste.getIs_activate());
        userResponseDto.setCpf_cnpj(userexiste.getCpf_cnpj());
        return userResponseDto;
    }

    @Override
    public UserResponseDto active(Long id, UserRequestDto userRequestDto) {
        User userexiste= userRepository.findById(id).orElseThrow();
        userexiste.setIs_activate((userRequestDto.getIs_activate()));


        userRepository.save(userexiste);

        UserResponseDto userResponseDto=new UserResponseDto();
        userResponseDto.setId(userexiste.getId());
        userResponseDto.setName(userexiste.getName());
        userResponseDto.setEmail(userexiste.getEmail());
        userResponseDto.setPassword(userexiste.getPassword());
        userResponseDto.setIs_activate(userexiste.getIs_activate());
        userResponseDto.setCpf_cnpj(userexiste.getCpf_cnpj());
        return userResponseDto;
    }
}
