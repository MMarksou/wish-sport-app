package univ.tln.i243.groupe1.controleur;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.websocket.*;
import univ.tln.i243.groupe1.entitees.Frame;
import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 * Classe permettant l'instantiation du serveur java
 */

@ClientEndpoint
public class ServeurJava {

    private static final Logger LOGGER = Logger.getLogger(ServeurJava.class.getName());

    private boolean transmission = false;

    private String donnees;

    protected static List<Frame> listeFrames = new ArrayList<>();

    private int nbFrames;

    private boolean etatConnexion = true;



    @OnOpen
    public void onOpen(Session session) throws IOException {
        LOGGER.log(Level.INFO, ()-> "Connecté à la session :  "+session.getId());
        session.getBasicRemote().sendText("Demarrage");
    }

    @OnMessage
    public void onMessage(String message, Session session) throws IOException {

        if(transmission){ //une fois la communication établie
            if(message.equals("Fin")){
                session.close();
                transmission = false;
                session.close();
                etatConnexion = false;
            } else { //tant qu'il y a une transmission, récupération du message
                donnees = message;
                recupererJson();
            }
        } else { //échange du nombre de frame à récupérer
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

    /**
     * Fonction qui sérialise la chaine de caractère reçue en objet frame
     * @throws JsonProcessingException en cas d'échec de sérialisation
     */
    public void recupererJson() throws JsonProcessingException {

        ObjectMapper objectMapper = new ObjectMapper();
        Frame frame = objectMapper.readValue(donnees, new TypeReference<>() {});
        listeFrames.add(frame);
    }

    /**
     * Fonction main permettant le lancement du serveur ainsi que l'établissement de la première communication.
     * @param nombreSecondes le nombre de seconde de l'exercice
     */
    public void main(int nombreSecondes)
    {

        nbFrames = nombreSecondes * 30;

        LOGGER.log(Level.INFO, "Lancement client");
        WebSocketContainer container = ContainerProvider.getWebSocketContainer();

        try {
            container.connectToServer(ServeurJava.class, URI.create("ws://localhost:9001/"));

        } catch (DeploymentException | IOException ex)
        {
            LOGGER.log(Level.SEVERE, ()->"Impossible de se connecter au serveur"+ex);
        }
        while (etatConnexion); //tant que connection ouverte
    }
}
