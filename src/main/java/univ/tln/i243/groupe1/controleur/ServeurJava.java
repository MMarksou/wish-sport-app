package univ.tln.i243.groupe1.controleur;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.websocket.*;
import univ.tln.i243.groupe1.daos.EnregistrementDao;
import univ.tln.i243.groupe1.daos.FrameDao;
import univ.tln.i243.groupe1.entitees.Enregistrement;
import univ.tln.i243.groupe1.entitees.Categorie;
import univ.tln.i243.groupe1.entitees.Frame;
import univ.tln.i243.groupe1.entitees.Jointure;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import java.io.IOException;
import java.net.URI;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


@ClientEndpoint
public class ServeurJava {
    private static final Logger LOGGER = Logger.getLogger(ServeurJava.class.getName());
    private boolean transmission = false;
    private String donnees;
    private EntityManager em = Persistence.createEntityManagerFactory("bddlocal").createEntityManager();
    private EnregistrementDao enregistrementDao = new EnregistrementDao(em);
    private int nbFrames = 100;

    private Enregistrement enregistrement;

    @OnOpen
    public void onOpen(Session session) throws IOException {
        LOGGER.log(Level.INFO, ()-> "Connecté à la session :  "+session.getId());

        Categorie categorie = Categorie.builder().nom("Categorie").build();
        enregistrement = Enregistrement.builder().nom("Test").categorie(categorie).build();
        enregistrementDao.persister(enregistrement);

        session.getBasicRemote().sendText("Demarrage");
    }

    @OnMessage
    public void onMessage(String message, Session session) throws IOException {

        if(transmission){
            if(message.equals("Fin")){
                session.close();
                transmission = false;
                session.close();
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

        FrameDao frameDao = new FrameDao(em);

        ObjectMapper objectMapper = new ObjectMapper();
        Frame frame = objectMapper.readValue(donnees, new TypeReference<>() {});

        frame.setEnregistrement(enregistrement);

        for (Jointure jointure: frame.getJointures()) {
            jointure.setFrame(frame);
        }
        enregistrement.ajouterFrame(frame);

        enregistrementDao.recharger(enregistrement);

        frameDao.persister(frame);
    }

    public static void main(String[] args)
    {
        LOGGER.log(Level.INFO, "Lancement client");
        WebSocketContainer container = ContainerProvider.getWebSocketContainer();

        try {
            Session session = container.connectToServer(ServeurJava.class, URI.create("ws://localhost:9001/"));

        } catch (DeploymentException | IOException ex)
        {
            LOGGER.log(Level.SEVERE, ()->"Impossible de se connecter au serveur"+ex);
        }
        while (true);
    }
}
