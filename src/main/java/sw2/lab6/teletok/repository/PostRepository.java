package sw2.lab6.teletok.repository;

import javafx.geometry.Pos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import sw2.lab6.teletok.dto.InformacionPostDto;
import sw2.lab6.teletok.entity.Post;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Integer> {


    @Query(value="SELECT * FROM teletok.post ORDER BY creation_date desc", nativeQuery=true)
    List<Post> listarPostDescendentes ();

    @Query(value="SELECT p.id as id,  p.description as descripcion, p.media_url as foto, count(pl.post_id) as cantidadlikes, count(pc.post_id) as cantidadcomentarios, u.username as nombreusuario, p.creation_date as fechasubida\n" +
            "FROM post p\n" +
            "INNER JOIN post_like pl on p.id = pl.post_id\n" +
            "INNER JOIN post_comment pc on p.id=pc.post_id\n" +
            "INNER JOIN user u on p.user_id=u.id\n" +
            "GROUP BY p.id\n" +
            "ORDER BY creation_date desc", nativeQuery=true)
    List<InformacionPostDto> listaPostsQuery();

    @Query(value="SELECT p.id\n" +
            "FROM post p\n" +
            "INNER JOIN user u ON p.user_id=u.id\n" +
            "WHERE p.description like %?1%\n" +
            "OR u.username like %?1%", nativeQuery=true)
    List<Post> buscadorPosts(String busqueda);


}
