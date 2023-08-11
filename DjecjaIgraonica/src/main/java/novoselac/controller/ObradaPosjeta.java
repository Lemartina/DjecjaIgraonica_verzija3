/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package novoselac.controller;

import java.util.Collection;
import java.util.List;
import novoselac.model.Dijete;
import novoselac.model.Djelatnik;
import novoselac.model.Posjeta;
import novoselac.model.Usluga;
import novoselac.util.NovoselacException;

/**
 *
 * @author Administrator
 */
public class ObradaPosjeta extends Obrada <Posjeta> {

    
    //implementacija abstraktnih metoda
    @Override
    public List<Posjeta> read() {
        return session.createQuery
        ("from Posjeta", Posjeta.class).list();
    }
    
    
    //veze
          public List<Posjeta> read(Usluga u) {
        return session.createQuery
        ("from Posjeta"
                + "where usluga= :usluga",
                Posjeta.class)
                .setParameter("usluga", u)
                .list();
    }
        
//        public List<Posjeta> read(Djelatnik d) {
//        return session.createQuery
//        ("from Posjeta"
//                + "where djelatnik= :djelatnik",
//                Posjeta.class)
//                .setParameter("djelatnik", d)
//                .list();
//    }
        
        
        
                public List<Posjeta> read(Dijete d) {
        return session.createQuery
        ("from Posjeta"
                + "where dijete= :dijete",
                Posjeta.class)
                .setParameter("dijete", d)
                .list();
    }
    
    public List<Posjeta> read(String uvjet) {
        uvjet=uvjet.trim();
        uvjet = "%" + uvjet + "%";
       return session.createQuery("from Posjeta "
               + " where "
               + "concat(datumVrijemeDolaska,' ',napomena) "
               + " like :uvjet "
               +" order by datumVrijemeDolaska desc" , 
               Posjeta.class)
               .setParameter("uvjet", uvjet)
               .setMaxResults(100)
               .list();
    }
    
//     public List<Posjeta> read(Djelatnik x) {
//        return session.createQuery
//        ("from Posjeta"
//                +" where djelatnik= :sifra_djelatnik is null"
//                + "order by djelatnik"     
//                , Posjeta.class)
//                .setParameter("djelatnik", x)
//                .list();
//    
//}
  
    
  

    @Override
    protected void kontrolaUnos() throws NovoselacException {
    kontrolaDodavanjePosjete();
    }
    @Override
    protected void kontrolaPromjena() throws NovoselacException {
    }

    @Override
    protected void kontrolaBrisanje() throws NovoselacException {
   }


     
    private void kontrolaDodavanjePosjete() throws NovoselacException{
         List<Posjeta> posjete=null;
        try {
            posjete = session.createQuery("from Posjete p "
                    + " where p.sifra_djelanik=:sifra_djelatnik", 
                    Posjeta.class)
                    .setParameter("sifra_djelatnk", entitet.getDjelatnik())
                    .list();
        } catch (Exception e) {
        }
        if(posjete!=null && !posjete.isEmpty()){
            throw new NovoselacException("Posjeta je već dodana na djelatnika u bazi");
        }
    }
    
    
}
