package com.pcmaster.AFK.profilemanagement.domain.model.commands;

public record UpdateProfileCommand(Long id,String firstName,String lastName,String email,String phone) {
}
