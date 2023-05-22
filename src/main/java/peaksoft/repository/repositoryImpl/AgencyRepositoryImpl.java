package peaksoft.repository.repositoryImpl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import peaksoft.entity.Agency;
import peaksoft.repository.AgencyRepository;

import java.util.ArrayList;
import java.util.List;
@Repository
@Transactional
public class AgencyRepositoryImpl implements AgencyRepository {
    @PersistenceContext
    private  EntityManager entityManager;
    @Override
    public void saveAgency(Agency agency) {
        entityManager.persist(agency);

    }
    @Override
    public boolean isAgencyNameExists(String name) {
        String queryString = "select count (a) from Agency a where a.name = :name";
        Long count = entityManager.createQuery(queryString, Long.class)
                .setParameter("name", name)
                .getSingleResult();
        return count > 0;
    }

    @Override
    public Agency getAgencyById(Long id) {
        return entityManager.find(Agency.class,id);
    }

    @Override
    public List<Agency> getAllAgencies() {
        return entityManager.createQuery("select a from Agency a", Agency.class).getResultList();
    }

    @Override
    public void updateAgency(Long id, Agency agency) {
        Agency agency1 = entityManager.find(Agency.class,id);
        agency1.setName(agency.getName());
        agency1.setEmail(agency.getEmail());
        agency1.setImageAgency(agency.getImageAgency());
        agency1.setCountry(agency.getCountry());
        agency1.setPhoneNumber(agency.getPhoneNumber());
    }

    @Override
    public void deleteAgencyById(Long id) {
        entityManager.remove(entityManager.find(Agency.class,id));
    }

    @Override
    public List<Agency> searchAgency(String name, String email, String country) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Agency> query = criteriaBuilder.createQuery(Agency.class);
        Root<Agency> root = query.from(Agency.class);
        List<Predicate> predicates = new ArrayList<>();

        if (name != null && !name.isEmpty()) {
            predicates.add(criteriaBuilder.equal(root.get("name"), name));
        }

        if (email != null && !email.isEmpty()) {
            predicates.add(criteriaBuilder.equal(root.get("email"), email));
        }
        if (country != null && !country.isEmpty()) {
            predicates.add(criteriaBuilder.equal(root.get("country"), country));
        }

        query.where(predicates.toArray(new Predicate[0]));

        TypedQuery<Agency> typedQuery = entityManager.createQuery(query);
        return typedQuery.getResultList();
    }
}
