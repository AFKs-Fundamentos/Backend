package com.pcmaster.AFK.advisorymanagement.interfaces.rest.transform;

import com.pcmaster.AFK.advisorymanagement.domain.model.aggregates.Advisory;
import com.pcmaster.AFK.advisorymanagement.interfaces.rest.resources.AdvisoryResource;
import com.pcmaster.AFK.advisorymanagement.interfaces.rest.resources.UpdateAdvisoryResource;

public class AdvisoryResourceFromEntityAssembler {
    public static AdvisoryResource toResourceFromEntity(Advisory advisory) {
        return new AdvisoryResource(
                advisory.getId(),
                advisory.getAdvisoryType(),
                advisory.getAdvisoryStatus(),
                advisory.getAdvisorId().advisorId(),
                advisory.getCustomerId().customerId(),
                advisory.getAdvisoryDate(),
                advisory.getAdvisoryTime(),
                advisory.getMeetUrl(),
                advisory.getClientEmail(),
                advisory.getAdvisoryDescription(),
                advisory.getLocation()
        );
    }
}
