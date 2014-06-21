package utilidade;

import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;
import org.hibernate.Session;

/**
 *
 * @author Lucas
 */
public class PhaseListenerJSF implements PhaseListener {

    @Override
    public void afterPhase(PhaseEvent pe) {
        if (pe.getPhaseId().equals(PhaseId.RENDER_RESPONSE)) {
            System.out.println("Depois da Fase:" + getPhaseId().toString());
            Session session = FacesContextUtil.getRequestSession();
            try {
                session.getTransaction().commit();
            } catch (Exception e) {
                if (session.getTransaction().isActive()) {
                    session.getTransaction().rollback();
                }
            } finally {
                session.close();
            }
        }

    }

    @Override
    public void beforePhase(PhaseEvent pe) {
        if (pe.getPhaseId().equals(PhaseId.RESTORE_VIEW)) {
            System.out.println("Antes da Fase:" + getPhaseId().toString());
            Session session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            FacesContextUtil.setRequestSession(session);
        }
    }

    @Override
    public PhaseId getPhaseId() {
        return PhaseId.ANY_PHASE;
    }

}
