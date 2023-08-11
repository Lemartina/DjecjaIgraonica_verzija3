/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package novoselac.controller;


import java.util.List;
import novoselac.model.Dijete;
import novoselac.util.Alati;
import novoselac.util.NovoselacException;

/**
 *
 * @author Administrator
 */
public class ObradaDijete extends Obrada<Dijete> {

    
    //implementacija abstraktnih 
    @Override
    public List<Dijete> read() {
   return session.createQuery("from Dijete order by prezime, ime", 
           Dijete.class).list();
    }

    
        public List<Dijete> read(String uvjet) {
        uvjet=uvjet.trim();
        uvjet = "%" + uvjet + "%";
       return session.createQuery("from Dijete "
               + " where concat(ime,' ',prezime,' ',ime, ' ', oib) "
               + " like :uvjet "
               + " order by prezime, ime ", 
               Dijete.class)
               .setParameter("uvjet", uvjet)
               .list();
    }
    
    
        public List<Dijete> read(String uvjet,
                boolean traziOdPocetkaImena) {
        uvjet=uvjet.trim();
        if(traziOdPocetkaImena){
            uvjet = uvjet + "%";
        }else{
            uvjet = "%" + uvjet + "%";
        }
        
       return session.createQuery("from Dijete "
               + " where concat(ime,' ',prezime,' ',ime) "
               + " like :uvjet "
               + " order by prezime, ime ", 
               Dijete.class)
               .setParameter("uvjet", uvjet)
               .list();
    }
    
    
    @Override
    protected void kontrolaUnos() throws NovoselacException {
 kontrolaFormatOib();
     kontrolaOibDjetetaDupliUBazi();
    }

    @Override
    protected void kontrolaPromjena() throws NovoselacException {
 
    }

    @Override
    protected void kontrolaBrisanje() throws NovoselacException {
    if(entitet.getPosjete()!= null &&
                !entitet.getPosjete().isEmpty()){
        throw new NovoselacException("Dijete se ne može brisati"
                + " jer ima zabilježenu posjetu/e ");
    }
    }
   private void kontrolaOibDjetetaDupliUBazi() throws NovoselacException{
         List<Dijete> djeca=null;
        try {
            djeca = session.createQuery("from Dijete u "
                    + " where u.oib=:oib", 
                    Dijete.class)
                    .setParameter("oib", entitet.getOib())
                    .list();
        } catch (Exception e) {
        }
        if(djeca!=null && !djeca.isEmpty()){
            throw new NovoselacException("Dijete s istim oibom postoji u bazi");
        }
    }
            
            
            
    private void kontrolaFormatOib() throws NovoselacException{
        if(!Alati.kontrolaOIB(entitet.getOib())){
            throw new NovoselacException("OIB nije u dobrom formatu");
        }
    }
  
    

    }


