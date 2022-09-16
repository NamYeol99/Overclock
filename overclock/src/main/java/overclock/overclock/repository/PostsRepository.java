package overclock.overclock.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import overclock.overclock.entity.Posts;

import java.util.List;

@Repository
public interface PostsRepository extends JpaRepository<Posts, Long> {

    @Query("select p, m from Posts p left join p.member m where p.id =:id")
    Object getPostsWithMember(@Param("id") Long id);

    @Query("select p, c from Posts p left join Comment c on c.posts = p where p.id = :id")
    List<Object[]> getPostsWithComment(@Param("id") Long id);

    //게시물 화면에 필요한 데이터 추출
//    @Query("select p, m from Posts p left join p.member m where p.id =:id")

//    @Query(value ="SELECT p, i, count(m) " +
//                  " FROM Posts p " +
//                  " LEFT JOIN p.id i " +
//                  " LEFT JOIN Member m ON m.posts = p " +
//                  " GROUP BY p",
//                  countQuery = "SELECT count(p) FROM Posts p", nativeQuery = true)
//    Page<Object[]> getPostsWithMemberPage(Pageable pageable);

//    게시물 화면에 필요한 데이터 추출
    @Query(value = "SELECT p, m, count(c) " +
            " FROM Posts p " +
            " LEFT JOIN p.member m " +
            " LEFT JOIN Comment c ON c.posts = p " +
            " GROUP BY p",
            countQuery = "SELECT count(p) FROM Posts p")
    Page<Object[]> getPostsWithCommentCount(Pageable pageable);

    @Query("select p, m from Posts p left join p.member m where p.id =:id")
    Page<Object[]> getPostsWithMemberPage(Pageable pageable);

//    @Query("SELECT p , m, count(c) " +
//            " FROM Posts p LEFT JOIN p.member m " +
//            " LEFT OUTER JOIN Comment c ON c.posts = p " +
//            " WHERE p.id = :id")
//    Object getPostsById(@Param("id") Long id);

}
