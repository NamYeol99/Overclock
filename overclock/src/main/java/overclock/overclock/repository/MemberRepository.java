package overclock.overclock.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;
import overclock.overclock.entity.Member;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
@EnableJpaRepositories
public interface MemberRepository extends JpaRepository<Member, Long> {


    Member findByEmail(String email);

    public Member findOne(Long id){
        return member
    }

}
