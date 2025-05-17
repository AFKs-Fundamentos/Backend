package com.pcmaster.AFK.iam.domain.services;


import com.pcmaster.AFK.iam.domain.model.commands.SeedRolesCommand;

public interface RoleCommandService {
  void handle(SeedRolesCommand command);
}
