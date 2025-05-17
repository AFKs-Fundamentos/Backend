package com.pcmaster.AFK.iam.domain.services;


import com.pcmaster.AFK.iam.domain.model.queries.GetAllRolesQuery;
import com.pcmaster.AFK.iam.domain.model.queries.GetRoleByNameQuery;
import com.pcmaster.AFK.iam.domain.model.entities.Role;


import java.util.List;
import java.util.Optional;

public interface RoleQueryService {
  List<Role> handle(GetAllRolesQuery query);
  Optional<Role> handle(GetRoleByNameQuery query);
}
