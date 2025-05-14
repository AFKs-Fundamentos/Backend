package com.pcmaster.AFK.advisorymanagement.application.internal.commandservices;

import com.pcmaster.AFK.advisorymanagement.domain.model.aggregates.Advisory;
import com.pcmaster.AFK.advisorymanagement.domain.model.commands.CreateAdvisoryCommand;
import com.pcmaster.AFK.advisorymanagement.domain.model.commands.DeleteAdvisoryCommand;
import com.pcmaster.AFK.advisorymanagement.domain.model.commands.UpdateAdvisoryCommand;
import com.pcmaster.AFK.advisorymanagement.domain.services.AdvisoryCommandService;
import com.pcmaster.AFK.advisorymanagement.infrastructure.persistence.jpa.repositories.AdvisoryRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AdvisoryCommandServiceImpl implements AdvisoryCommandService {

    private final AdvisoryRepository advisoryRepository;

    public AdvisoryCommandServiceImpl(AdvisoryRepository advisoryRepository) {
        this.advisoryRepository = advisoryRepository;
    }


    @Override
    public Optional<Advisory> handle(CreateAdvisoryCommand command) {
        var time = command.advisoryTime();
        var date = command.advisoryDate();

        if(advisoryRepository.existsByAdvisoryTimeAndAdvisoryDate(time, date)){
            throw new IllegalArgumentException("Advisory with this time and date already exists");
        }

        var advisory = new Advisory(command);

        try {
            advisoryRepository.save(advisory);
        } catch (Exception e) {
            throw new IllegalArgumentException("Error creating advisory: %s".formatted(e.getMessage()));
        }

        return  Optional.of(advisory);
    }



    @Override
    public Void handle(DeleteAdvisoryCommand command) {
        if (!advisoryRepository.existsById(command.advisoryId())) {
            throw new IllegalArgumentException("Advisory with id %s not found".formatted(command.advisoryId()));
        }

        try {
            advisoryRepository.deleteById(command.advisoryId());
        }
        catch (Exception e) {
            throw new IllegalArgumentException("Error deleting advisory: %s".formatted(e.getMessage()));
        }

        return null;
    }

    @Override
    public Optional<Advisory> handle(UpdateAdvisoryCommand command) {
        //implementar reglas de negocio
        var existsAdvisoryId = advisoryRepository.existsById(command.advisoryId());
        if (!existsAdvisoryId) {
            throw new IllegalArgumentException("Advisory with id %s not found".formatted(command.advisoryId()));
        }

        var result = this.advisoryRepository.findById(command.advisoryId());
        var advisoryToUpdate = result.get();
        advisoryToUpdate.udpdateInformation(
                command.advisoryType(),
                command.advisoryStatus(),
                command.advisorId(),
                command.customerId(),
                command.advisoryDate(),
                command.advisoryTime(),
                command.meetUrl(),
                command.clientEmail(),
                command.advisoryDescription(),
                command.location()
        );

        try{
            var updatedAdvisory = advisoryRepository.save(advisoryToUpdate);
            return Optional.of(updatedAdvisory);
        }
        catch (Exception e) {
            throw new IllegalArgumentException("Error updating advisory: %s".formatted(e.getMessage()));
        }

    }


}
