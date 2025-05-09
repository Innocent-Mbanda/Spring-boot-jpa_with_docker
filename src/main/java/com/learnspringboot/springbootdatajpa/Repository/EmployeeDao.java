package com.learnspringboot.springbootdatajpa.Repository;

import com.learnspringboot.springbootdatajpa.Model.Employee;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class EmployeeDao {
    private final EntityManager entityManager;
    public List<Employee> findEmployeesBySimpleQuery(String firstname,
                                                     String lastname,
                                                     String email)
    {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Employee> criteriaQuery = cb.createQuery(Employee.class);
        criteriaQuery.select(criteriaQuery.from(Employee.class));

        // SELECT *FROM employee WHERE firstname like '%firstname%'
         Root<Employee> root = criteriaQuery.from(Employee.class);
        Predicate firstNamePredicate = cb.like(root.get("firstName"), firstname + "%");
        Predicate lastNamePredicate = cb.like(root.get("lastName"), lastname + "%");
        Predicate emailPredicate = cb.like(root.get("email"), email + "%");

        Predicate firstNamePredicateOrLastNamePredicate =
                cb.or(firstNamePredicate, lastNamePredicate);


        // final query ==> select *from employee where firstname
        // like '%firstname%' or lastname like '%lastname%' or email like '%email%'

        var andEmailPredicate = cb.and(firstNamePredicateOrLastNamePredicate, emailPredicate);
        criteriaQuery.where(andEmailPredicate);
        TypedQuery<Employee> query = entityManager.createQuery(criteriaQuery);
        return query.getResultList();

    }

    public List <Employee> findEmployeeByCriteria(SearchRequest request){
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Employee> criteriaQuery = criteriaBuilder.createQuery(Employee.class);

        List<Predicate> predicates = new ArrayList();
        Root<Employee> root = criteriaQuery.from(Employee.class);

        if(request.getFirstName() != null){
            Predicate employeeFirstNamePredicate = criteriaBuilder.like(
                    root.get("firstname"), "%" + request.getFirstName() + "%");
            predicates.add(employeeFirstNamePredicate);

        }
        if(request.getLastName() != null){
            predicates.add(criteriaBuilder.like(root.get("lastName"), "%" + request.getLastName() + "%"));
        }

        if(request.getEmail() != null){
            predicates.add(criteriaBuilder.like(root.get("email"), "%" + request.getEmail() + "%"));
        }
        criteriaQuery.where(predicates.toArray(new Predicate[0]));
        TypedQuery<Employee> query = entityManager.createQuery(criteriaQuery);
        return query.getResultList();
    }
}
