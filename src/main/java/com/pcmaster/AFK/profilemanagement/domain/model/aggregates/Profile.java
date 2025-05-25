package com.pcmaster.AFK.profilemanagement.domain.model.aggregates;


import com.pcmaster.AFK.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import com.pcmaster.AFK.profilemanagement.domain.model.commands.CreateProfileCommand;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "profiles")
@Setter
@Getter
@NoArgsConstructor
public class Profile extends AuditableAbstractAggregateRoot<Profile> {

    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "email")
    @Email(message = "El email registrado no es válido.")
    private String email;
    @Column(name = "phone")
    private String phone;

    public Profile(String firstName, String lastName, String email, String phone) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phone = phone;
    }

    public Profile(CreateProfileCommand command){
        this.firstName = command.firstName();
        this.lastName = command.lastName();
        this.email = command.email();
        this.phone = command.phone();
    }

    public Profile updateProfile(String firstName, String lastName, String email, String phone) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phone = phone;
        return this;
    }


}
