package maikon.cartas.api.repository;

import maikon.cartas.api.entity.Carta;
import maikon.cartas.api.enumerated.ClasseCarta;
import maikon.cartas.api.enumerated.Tipo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartaRepository extends JpaRepository<Carta, Long> {

    Carta findById(long id);
    List<Carta> findByNomeContainingIgnoreCase(String nome);
    Carta findByNomeIgnoreCase(String nome);
    List<Carta> findByClasseCarta(ClasseCarta classeCarta);
    List<Carta> findByTipo(Tipo tipo);

}
