package io.github.jaksatomovic.covid.statistics.core.features.shared.client.api;

/**
 * @author Jakša Tomović
 * @since 1.0
 */
public interface Executor<I extends RapidApiRequest, O extends RapidApiResponse>
{
    I getRequest();

    O execute(I var1)
        throws Exception;
}
