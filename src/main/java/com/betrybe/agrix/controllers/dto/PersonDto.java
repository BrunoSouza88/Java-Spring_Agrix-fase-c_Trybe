package com.betrybe.agrix.controllers.dto;

import com.betrybe.agrix.ebytr.staff.security.Role;

/**
 * javadoc.
 */
public record PersonDto(Long id, String username, Role role) {}
