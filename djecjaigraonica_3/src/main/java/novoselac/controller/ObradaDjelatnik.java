/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package novoselac.controller;

import java.util.ArrayList;
import java.util.List;
import novoselac.model.Dijete;
import novoselac.model.Djelatnik;
import novoselac.model.GrafPodaci;
import novoselac.util.Alati;
import novoselac.util.NovoselacException;

/**
 *
 * @author Administrator
 */
public class ObradaDjelatnik  extends Obrada<Djelatnik>{
    public List<GrafPodaci> getGrafPodaci(){
        List<GrafPodaci> l = new ArrayList<>();
        
         List<Object[]> lista =  session.createNativeQuery(
                 " select a.ime, a.prezime, count(b.datumVrijemeDolaska) " +
" from djelatnik a  " +
" inner join posjeta b on a.sifra=b.djelatnik_sifra " +
" inner join uslugaposjeta c on b.sifra=c. posjeta " +
" group by a.ime "
                            
                  ,Object[].class
                 ).getResultList();
       
         GrafPodaci gf;
         for(Object[] niz : lista){
            gf = new GrafPodaci();
            gf.setNaziv(niz[0].toString());
            gf.setBroj(Integer.valueOf(niz[1].toString()));
            l.add(gf);
         }
    
    
       return l;
    }

    
            public List<Djelatnik> read(String uvjet) {
        uvjet=uvjet.trim();
        uvjet = "%" + uvjet + "%";
       return session.createQuery("from Djelatnik "
               + " where concat(ime,' ',prezime,' ',ime, ' ', oib) "
               + " like :uvjet "
               + " order by prezime, ime ", 
               Djelatnik.class)
               .setParameter("uvjet", uvjet)
               .list();
    }
    

    
    //implementacija abstraktnih metoda
    @Override
    public List<Djelatnik> read() {
   return session.createQuery
        ("from Djelatnik", Djelatnik.class).list();
           
           }

    @Override
    protected void kontrolaUnos() throws NovoselacException {
    kontrolaFormatOib();
     kontrolaOibDupliUBazi();
    }

    @Override
    protected void kontrolaPromjena() throws NovoselacException {
    }

    @Override
    protected void kontrolaBrisanje() throws NovoselacException {
   if(entitet.getPosjete()!= null &&
                !entitet.getPosjete().isEmpty()){
        throw new NovoselacException("Djelatnik se ne može brisati"
                + " jer ima dogovorne posjete ");
     
   }
    }
 
    private void kontrolaOibDupliUBazi() throws NovoselacException{
         List<Djelatnik> djelatnici=null;
        try {
            djelatnici = session.createQuery("from Djelatnik u "
                    + " where u.oib=:oib", 
                    Djelatnik.class)
                    .setParameter("oib", entitet.getOib())
                    .list();
        } catch (Exception e) {
        }
        if(djelatnici!=null && !djelatnici.isEmpty()){
            throw new NovoselacException("Djelatnik s istim oibom postoji u bazi");
        }
    }
            
            
            
    private void kontrolaFormatOib() throws NovoselacException{
//        if(!Alati.kontrolaOIB(entitet.getOib())){
//            throw new NovoselacException("OIB nije u dobrom formatu");
//        }
    }
}
