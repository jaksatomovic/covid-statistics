package io.github.jaksatomovic.covid.statistics.core.persistence.store;

import io.github.jaksatomovic.commons.api.validation.Defense;
import io.github.jaksatomovic.covid.statistics.core.persistence.domain.DbCountry;
import io.github.jaksatomovic.covid.statistics.core.persistence.domain.DbSearch;
import io.github.jaksatomovic.covid.statistics.core.persistence.repository.SearchRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

/**
 * @author Jakša Tomović
 * @since 1.0
 */
@Service
@Transactional
public class SearchStore
    extends Store<Long, DbSearch, SearchRepository>
{
    private final SearchRepository repository;

    protected SearchStore(final SearchRepository repository, final EntityManager entityManager)
    {
        super(repository, entityManager);
        this.repository = Defense.notNull(repository, SearchRepository.class.getSimpleName());
    }

    @Override
    protected Class<DbSearch> getEntityClass()
    {
        return DbSearch.class;
    }

    // TODO Refactor fields
    public List<DbSearch> getIfOverlapsBy(final Optional<DbCountry> country, final LocalDate dateFrom, final LocalDate dateTo)
    {
        return execute(entityManager ->
        {
            final CriteriaBuilder         builder    = entityManager.getCriteriaBuilder();
            final CriteriaQuery<DbSearch> query      = builder.createQuery(DbSearch.class);
            final Root<DbSearch>          root       = query.from(DbSearch.class);
            final List<Predicate>         predicates = new LinkedList<>();

            if (country.isPresent())
            {
                predicates.add(builder.equal(root.get("country"), country.get().getId()));
            }

            predicates.add(
                builder.and(
                    builder.lessThan(root.get("dateTo").as(LocalDate.class), dateFrom),
                    builder.greaterThan(root.get("dateFrom").as(LocalDate.class), dateFrom)));

            predicates.add(builder.greaterThan(root.get("dateTo").as(LocalDate.class), dateTo));

            return (entityManager.createQuery(query.select(root).where(clause(predicates))).getResultList());
        });
    }

    public List<DbSearch> getAllBy(final Optional<DbCountry> country, final LocalDate dateFrom, final LocalDate dateTo)
    {
        return execute(entityManager ->
        {
            final CriteriaBuilder         builder    = entityManager.getCriteriaBuilder();
            final CriteriaQuery<DbSearch> query      = builder.createQuery(DbSearch.class);
            final Root<DbSearch>          root       = query.from(DbSearch.class);
            final List<Predicate>         predicates = new LinkedList<>();

            if (country.isPresent())
            {
                predicates.add(builder.equal(root.get("country"), country.get().getId()));
            }

            predicates.add(builder.greaterThanOrEqualTo(root.get("dateFrom").as(LocalDate.class), dateFrom));
            predicates.add(builder.lessThanOrEqualTo(root.get("dateTo").as(LocalDate.class), dateTo));

            return (entityManager.createQuery(query.select(root).where(clause(predicates))).getResultList());
        });
    }
}
