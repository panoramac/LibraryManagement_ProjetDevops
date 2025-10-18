package com.app.users.service;

import com.app.users.bo.Client;
import com.app.users.dao.UserDAO;
import com.app.users.dto.ClientDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService implements IUserService{

    @Autowired
    private UserDAO dao;

    @Override
    public ClientDTO add(ClientDTO user) {
        return fromUser(dao.save(toUser(user)));
    }

    @Override
    public ClientDTO remove(String id) {  // ✅ Changé int → String
        Optional<Client> user = dao.findById(id);
        if(user.isPresent()) {
            dao.deleteById(id);
            return this.fromUser(user.get());
        } else {
            throw new RuntimeException("User not found");
        }
    }

    @Override
    public ClientDTO update(ClientDTO newUserDTO, String id) {  // ✅ Changé int → String
        Optional<Client> userOld = dao.findById(id);
        if(userOld.isPresent()){
            Client userToUpdate = userOld.get();
            userToUpdate.setNom(newUserDTO.getNom());
            userToUpdate.setPrenom(newUserDTO.getPrenom());
            Client updatedUser = dao.save(userToUpdate);
            return this.fromUser(updatedUser);
        } else {
            throw new RuntimeException("User not found");
        }
    }

    @Override
    public List<ClientDTO> findAll() {
        return dao.findAll().stream()
                .map(this::fromUser)
                .collect(Collectors.toList());
    }

    @Override
    public ClientDTO findUser(String id) {  // ✅ Changé int → String
        Optional<Client> user = dao.findById(id);
        return user.map(this::fromUser).orElse(null);
    }

    public Client toUser(ClientDTO userDTO) {
        return Client.builder()
                .cin(userDTO.getCin())    // ✅ AJOUT IMPORTANT !
                .nom(userDTO.getNom())
                .prenom(userDTO.getPrenom())
                .build();
    }

    public ClientDTO fromUser(Client user) {
        return ClientDTO.builder()
                .cin(user.getCin())       // ✅ AJOUT IMPORTANT !
                .prenom(user.getPrenom())
                .nom(user.getNom())
                .build();
    }
}