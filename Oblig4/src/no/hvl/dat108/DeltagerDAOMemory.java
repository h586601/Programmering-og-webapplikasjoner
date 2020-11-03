package no.hvl.dat108;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;



@Stateless
public class DeltagerDAOMemory {


	@PersistenceContext(name = "deltagerPU")
	private EntityManager em;
	
	
    public synchronized void lagreNyDeltager(Deltager deltager) {
    	em.persist(deltager);
    }
    
    public List<Deltager> hentAlle() {
    	return em.createQuery("SELECT d FROM Deltager d", Deltager.class).getResultList();
    }

	public Deltager hentDeltager(String mobil) {
		return em.find(Deltager.class, mobil);
	
	}
	
}
