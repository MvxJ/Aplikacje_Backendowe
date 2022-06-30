package com.gymworkouts.gymworkouts.Repository;

import com.gymworkouts.gymworkouts.Entity.WorkoutEntity;
import com.gymworkouts.gymworkouts.Interfaces.CustomRepositoryInterface;

import javax.persistence.EntityManager;
import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;

public class CustomWorkoutsRepository implements CustomRepositoryInterface {
    private EntityManager entityManager;

    @Override
    public List<WorkoutEntity> findWorkoutByNameOrDescription(String keyWord, String keyWord2) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<WorkoutEntity> query = criteriaBuilder.createQuery(WorkoutEntity.class);
        Root<WorkoutEntity> workoutEntityRoot = query.from(WorkoutEntity.class);
        Path<String> namePath = workoutEntityRoot.get("name");
        Path<String> descriptionPath = workoutEntityRoot.get("description");

        List<Predicate> predicates = new ArrayList<>();
        predicates.add(criteriaBuilder.like(namePath, keyWord));
        predicates.add(criteriaBuilder.like(descriptionPath, keyWord2));

        query.select(workoutEntityRoot)
                .where(criteriaBuilder.or(predicates.toArray(new Predicate[predicates.size()])));

        return entityManager.createQuery(query)
                .getResultList();
    }
}
