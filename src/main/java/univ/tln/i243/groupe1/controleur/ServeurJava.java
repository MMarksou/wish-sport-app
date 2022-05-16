package univ.tln.i243.groupe1.controleur;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.websocket.*;
import univ.tln.i243.groupe1.daos.EnregistrementDao;
import univ.tln.i243.groupe1.daos.FrameDao;
import univ.tln.i243.groupe1.entitees.Enregistrement;
import univ.tln.i243.groupe1.entitees.Frame;
import univ.tln.i243.groupe1.entitees.Jointure;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


@ClientEndpoint
public class ServeurJava {
    private static final Logger LOGGER = Logger.getLogger(ServeurJava.class.getName());
    private boolean transmission = false;
    private String donnees;

    protected static List<Frame> listeFrames = new ArrayList<>();

    private static EntityManager em = Persistence.createEntityManagerFactory("bddlocal").createEntityManager();
    private static EnregistrementDao enregistrementDao = new EnregistrementDao(em);
    private static int nbFrames;

    private static Enregistrement enregistrement;
    private static boolean etatConnexion = true;



    @OnOpen
    public void onOpen(Session session) throws IOException {
        LOGGER.log(Level.INFO, ()-> "Connecté à la session :  "+session.getId());
        session.getBasicRemote().sendText("Demarrage");
    }

    @OnMessage
    public void onMessage(String message, Session session) throws IOException {

        if(transmission){
            if(message.equals("Fin")){
                session.close();
                transmission = false;
                session.close();
                etatConnexion = false;
            } else {
                donnees = message;
                recupererJson();
            }
        } else {
            if(message.equals("OK")){
                session.getBasicRemote().sendText("JSON " + nbFrames);
                transmission = true;
            }
        }
    }

    @OnClose
    public void onClose(Session session) {
        LOGGER.log(Level.INFO, () -> "Fermeture de la connexion avec la session : "+session.getId());
    }

    public void recupererJson() throws JsonProcessingException {

        ObjectMapper objectMapper = new ObjectMapper();
        Frame frame = objectMapper.readValue(donnees, new TypeReference<>() {});
        listeFrames.add(frame);
    }

    public static void main(int nombreFrames, String enregistrementCible)
    {

        nbFrames = nombreFrames * 30;
        enregistrement =  enregistrementDao.rechercherParNom(enregistrementCible);

        LOGGER.log(Level.INFO, "Lancement client");
        WebSocketContainer container = ContainerProvider.getWebSocketContainer();

        try {
            container.connectToServer(ServeurJava.class, URI.create("ws://localhost:9001/"));

        } catch (DeploymentException | IOException ex)
        {
            LOGGER.log(Level.SEVERE, ()->"Impossible de se connecter au serveur"+ex);
        }
        while (etatConnexion);
    }
}
