package novoselac.controller;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import java.math.BigDecimal;
import java.util.List;
import novoselac.model.GrafPodaci;
import novoselac.model.Usluga;
import novoselac.util.NovoselacException;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class ObradaUsluga {

    @PersistenceContext
    private EntityManager em;

    public List<GrafPodaci> getGrafPodaci() {
        List<GrafPodaci> rezultati = em.createNativeQuery(
                "SELECT c.naziv, COUNT(a.datum_vrijeme_dolaska) AS broj_dogovorenih_termina "
                + "FROM posjeta a "
                + "INNER JOIN usluga_posjeta b ON a.sifra = b.posjeta_id "
                + "INNER JOIN usluga c ON b.usluga_id = c.sifra "
                + "GROUP BY c.naziv", "GrafPodaciMapping")
                .getResultList();
        
        return rezultati;
    }

    public List<Usluga> read(String uvjet) {
        uvjet = uvjet == null ? "" : uvjet.trim();
        String search = "%" + uvjet + "%";
        
        TypedQuery<Usluga> query = em.createQuery(
                "SELECT u FROM Usluga u "
                + "WHERE CONCAT(u.naziv, ' ') LIKE :search "
                + "ORDER BY u.naziv", Usluga.class);
        
        query.setParameter("search", search);
        return query.getResultList();
    }

    public List<Usluga> readAll() {
        return em.createQuery("SELECT u FROM Usluga u ORDER BY u.naziv", Usluga.class)
                .getResultList();
    }

    public Usluga create(Usluga usluga) throws NovoselacException {
        kontrolaUnos(usluga);
        em.persist(usluga);
        return usluga;
    }

    public Usluga update(Usluga usluga) throws NovoselacException {
        kontrolaPromjena(usluga);
        return em.merge(usluga);
    }

    public void delete(Long id) throws NovoselacException {
        Usluga usluga = em.find(Usluga.class, id);
        kontrolaBrisanje(usluga);
        em.remove(usluga);
    }

    private void kontrolaUnos(Usluga u) throws NovoselacException {
        kontrolaNaziv(u);
        kontrolaCijena(u);
    }

    private void kontrolaPromjena(Usluga u) throws NovoselacException {
        kontrolaNaziv(u);
        kontrolaCijena(u);
    }

    private void kontrolaBrisanje(Usluga u) throws NovoselacException {
        if (!u.getPosjete().isEmpty()) {
            throw new NovoselacException("Usluga se ne može brisati jer ima povezane posjete");
        }
    }

    // Ostale kontrolne metode ostaju slične, samo prilagođene za rad s EntityManagerom
    private void kontrolaNaziv(Usluga u) throws NovoselacException {
        if (u.getNaziv() == null || u.getNaziv().trim().isEmpty()) {
            throw new NovoselacException("Naziv usluge je obavezan");
        }
        
        if (u.getNaziv().length() < 3 || u.getNaziv().length() > 20) {
            throw new NovoselacException("Naziv usluge mora imati 3-20 znakova");
        }
       
        // Provjera duplikata
        Long count = em.createQuery(
                "SELECT COUNT(u) FROM Usluga u WHERE LOWER(u.naziv) = LOWER(:naziv) AND u.sifra != :sifra", Long.class)
                .setParameter("naziv", u.getNaziv())
                //.setParameter("sifra", u.getSifra() == null ? -1 : u.getSifra())
                .getSingleResult();
        
        if (count > 0) {
            throw new NovoselacException("Usluga s tim nazivom već postoji");
        }
    }

    private void kontrolaCijena(Usluga u) throws NovoselacException {
        if (u.getCijena() == null 
                || u.getCijena().compareTo(BigDecimal.ZERO) <= 0 
                || u.getCijena().compareTo(new BigDecimal(500)) > 0) {
            throw new NovoselacException("Cijena mora biti između 0.01 i 500");
        }
    }
}
