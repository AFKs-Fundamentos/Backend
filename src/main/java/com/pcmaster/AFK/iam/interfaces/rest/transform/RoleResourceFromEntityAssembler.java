package com.pcmaster.AFK.iam.interfaces.rest.transform;


import com.pcmaster.AFK.iam.domain.model.entities.Role;
import com.pcmaster.AFK.iam.interfaces.rest.resources.RoleResource;

public class RoleResourceFromEntityAssembler {

  public static RoleResource toResourceFromEntity(Role role) {
    return new RoleResource(role.getId(), role.getStringName());
  }
}
