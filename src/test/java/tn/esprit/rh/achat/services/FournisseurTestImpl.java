package tn.esprit.rh.achat.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import tn.esprit.rh.achat.entities.CategorieFournisseur;
import tn.esprit.rh.achat.entities.Fournisseur;
import tn.esprit.rh.achat.repositories.FournisseurRepository;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class FournisseurTestImpl {
    
    @Mock
    FournisseurRepository fournisseurRepository;

    @InjectMocks
    FournisseurServiceImpl fournisseurServiceImpl;

    Fournisseur fournisseur = new Fournisseur(1L, "code1", "libelle1", null, null, null, null);

    List<Fournisseur> listFournisseurs = new ArrayList<Fournisseur>() {
      

		

		{
            add(new Fournisseur(2L, "code2", "libelle2", null, null, null, null));
            add(new Fournisseur(3L, "code3", "libelle3", null, null, null, null));
        }
    };

    @Test
     void testretrieveFournisseur(){

        Mockito.when(fournisseurRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(fournisseur));
        
        Assertions.assertNotNull(fournisseurServiceImpl.retrieveFournisseur(2L));
    }

    @Test
     void testaddFournisseur(){

        Mockito.when(fournisseurRepository.save(fournisseur)).thenReturn(fournisseur);

        Assertions.assertNotNull(fournisseurServiceImpl.addFournisseur(fournisseur));
    }

    @Test
     void testdeleteFournisseur(){

        fournisseurServiceImpl.deleteFournisseur(3L);

        Mockito.verify(fournisseurRepository, times(1)).deleteById(3L);

        assertEquals(null, fournisseurServiceImpl.retrieveFournisseur(3L));
    }
}
