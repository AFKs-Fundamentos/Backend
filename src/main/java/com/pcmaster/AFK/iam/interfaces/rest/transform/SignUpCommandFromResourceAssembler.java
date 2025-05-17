package com.pcmaster.AFK.iam.interfaces.rest.transform;

import com.pcmaster.AFK.iam.domain.model.commands.SignUpCommand;
import com.pcmaster.AFK.iam.interfaces.rest.resources.SignUpResource;
import com.pcmaster.AFK.iam.domain.model.entities.Role;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class SignUpCommandFromResourceAssembler {

  public static SignUpCommand toCommandFromResource(SignUpResource resource) {
    var roles = resource.roles() != null
        ? resource.roles().stream().map(name -> Role.toRoleFromName(name)).toList()
        : new ArrayList<Role>();
    return new SignUpCommand(resource.username(), resource.password(), roles);
  }
}
