package com.pcmaster.AFK.iam.interfaces.rest.transform;


import com.pcmaster.AFK.iam.domain.model.commands.SignInCommand;
import com.pcmaster.AFK.iam.interfaces.rest.resources.SignInResource;

public class SignInCommandFromResourceAssembler {

  public static SignInCommand toCommandFromResource(SignInResource signInResource) {
    return new SignInCommand(signInResource.username(), signInResource.password());
  }
}
