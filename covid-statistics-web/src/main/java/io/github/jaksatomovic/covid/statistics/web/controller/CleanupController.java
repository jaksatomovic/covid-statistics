package io.github.jaksatomovic.covid.statistics.web.controller;

import io.github.jaksatomovic.covid.statistics.api.features.cleanup.CleanupRequest;
import io.github.jaksatomovic.covid.statistics.api.features.cleanup.CleanupResponse;
import io.github.jaksatomovic.covid.statistics.commons.api.validation.Defense;
import io.github.jaksatomovic.covid.statistics.commons.exception.ApiException;
import io.github.jaksatomovic.covid.statistics.core.features.shared.peer.CleanupPeer;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Jakša Tomović
 * @since 1.0
 */
@RestController
@RequestMapping ("/cleanup")
@Api (value = "COVID-19 Cleanup Service")
public class CleanupController
{
    private final CleanupPeer peer;

    public CleanupController(final CleanupPeer peer)
    {
        this.peer = Defense.notNull(peer, CleanupPeer.class.getSimpleName());
    }

    @ApiOperation (value = "Deletes all entries from all tables except countries")
    @DeleteMapping
    public @ResponseBody
    CleanupResponse delete()
        throws ApiException
    {
        final CleanupRequest request = new CleanupRequest();

        return peer.delete(request);
    }
}
