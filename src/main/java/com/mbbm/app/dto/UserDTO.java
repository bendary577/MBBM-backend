package com.mbbm.app.dto;

import java.util.List;

public record UserDTO(String userId,
                      String username,
                      String email,
                      List<String> roles) { }
