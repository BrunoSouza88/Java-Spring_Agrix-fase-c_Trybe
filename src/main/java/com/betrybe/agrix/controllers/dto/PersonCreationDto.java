package com.betrybe.agrix.controllers.dto;

import com.betrybe.agrix.ebytr.staff.security.Role;

/**
 * javadoc.
 */
public record PersonCreationDto(String username, String password, Role role) {}
