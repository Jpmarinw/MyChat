package ifam.edu.dra.chat.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ifam.edu.dra.chat.model.Contato;

public interface ContatoRepository extends JpaRepository<Contato, Long> {
	
//	@Query("select c from Contato c")
//    List<Contato> list();
//
//    @Query("select c from Cidade c where c.ibge = :parIbge")
//    Contato findOneByNome(String parNome);
//    
    
}
