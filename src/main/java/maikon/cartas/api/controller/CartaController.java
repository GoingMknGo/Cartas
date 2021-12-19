package maikon.cartas.api.controller;

import maikon.cartas.api.entity.Carta;
import maikon.cartas.api.enumerated.ClasseCarta;
import maikon.cartas.api.enumerated.Tipo;
import maikon.cartas.api.repository.CartaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/carta")
public class CartaController {

    @Autowired
    CartaRepository cartaRepository;

    @PostMapping("/cadastrar")
    public String salvar(@RequestBody Carta carta) {
        if (cartaRepository.findByNomeIgnoreCase(carta.getNome()) != null) {

            return "Carta com nome duplicado!";

        } else {

            cartaRepository.save(carta);
            return "Carta salva com sucesso!";

        }
    }

    @GetMapping
    public List<Carta> pesquisar() {
        return cartaRepository.findAll();
    }

    @GetMapping(path = "/findById/{id}")
    public Carta findById(@PathVariable("id") long id) {
        return cartaRepository.findById(id);
    }

    @GetMapping(path = "/findByNome/{nome}")
    public List<Carta> findByNome(@PathVariable("nome") String nome) {
        return cartaRepository.findByNomeContainingIgnoreCase(nome);
    }


    @GetMapping(path = "/findByClasse/{classe}")
    public Object findByClasse(@PathVariable ("classe") String strClasse) {

        try {

            String normalizedClasse = normalizeString(strClasse);

            ClasseCarta classeCarta = ClasseCarta.valueOf(normalizedClasse);

            return classeCarta == ClasseCarta.Qualquer
                    ? cartaRepository.findAll()
                    : cartaRepository.findByClasseCarta(classeCarta);

        } catch (IllegalArgumentException ex) {

            return "Nome de classe inválido!\n" +
                    "As classes válidas são Mago, Paladino, Caçador, Druida e 'Qualquer Classe'!\n" +
                    ex.getMessage();

        }

    }

    @GetMapping(path = "/findByTipo/{tipo}")
    public Object findByTipo(@PathVariable ("tipo") String strTipo) {
        try {

            String normalizedTipo = normalizeString(strTipo);

            Tipo tipo = Tipo.valueOf(normalizedTipo);

            return cartaRepository.findByTipo(tipo);

        } catch (IllegalArgumentException ex) {

            return "Tipo inválido! Os tipos válidos são Magia e Criatura!\n" +
                    ex.getMessage();

        }

    }

    @DeleteMapping("/deletar")
    public String delete(@RequestBody Carta carta) {

        if (carta.getId() != null && carta.getId() != 0) {

            cartaRepository.delete(carta);
            return "Carta excluída com sucesso!";

        } else {

            return "A carta não possui um id válido!";

        }
    }

    @PutMapping("/alterar")
    public Carta atualizaCarta(@RequestBody Carta carta) {
        return cartaRepository.save(carta);
    }

    private String normalizeString(String texto) {
        return texto.substring(0,1).toUpperCase().concat(texto.substring(1).toLowerCase());
    }

}
