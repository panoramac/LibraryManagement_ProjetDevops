package com.app.users.service;

import com.app.users.dto.ClientDTO;
import java.util.List;

public interface IUserService {
    public ClientDTO add(ClientDTO user);
    public ClientDTO remove(String id);        // ✅ int → String
    public ClientDTO update(ClientDTO user, String id);  // ✅ int → String
    public List<ClientDTO> findAll();
    public ClientDTO findUser(String id);      // ✅ int → String
}