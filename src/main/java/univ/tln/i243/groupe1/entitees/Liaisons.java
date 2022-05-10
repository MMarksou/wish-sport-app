package univ.tln.i243.groupe1.entitees;

import javafx.util.Pair;
import lombok.Getter;


import java.util.ArrayList;
import java.util.List;


@Getter
public class Liaisons {

    private List<Pair<String, String>> listeLiaisons = new ArrayList<>();

    public Liaisons() {
        listeLiaisons.add(new Pair<>("K4ABT_JOINT_SPINE_NAVEL", "K4ABT_JOINT_PELVIS"));
        listeLiaisons.add(new Pair<>("K4ABT_JOINT_SPINE_CHEST", "K4ABT_JOINT_SPINE_NAVEL"));
        listeLiaisons.add(new Pair<>("K4ABT_JOINT_NECK", "K4ABT_JOINT_SPINE_CHEST"));
        listeLiaisons.add(new Pair<>("K4ABT_JOINT_CLAVICLE_LEFT", "K4ABT_JOINT_SPINE_CHEST"));
        listeLiaisons.add(new Pair<>("K4ABT_JOINT_SHOULDER_LEFT", "K4ABT_JOINT_CLAVICLE_LEFT"));
        listeLiaisons.add(new Pair<>("K4ABT_JOINT_ELBOW_LEFT", "K4ABT_JOINT_SHOULDER_LEFT"));
        listeLiaisons.add(new Pair<>("K4ABT_JOINT_WRIST_LEFT", "K4ABT_JOINT_ELBOW_LEFT"));
        listeLiaisons.add(new Pair<>("K4ABT_JOINT_HAND_LEFT", "K4ABT_JOINT_WRIST_LEFT"));
        listeLiaisons.add(new Pair<>("K4ABT_JOINT_HANDTIP_LEFT", "K4ABT_JOINT_HAND_LEFT"));
        listeLiaisons.add(new Pair<>("K4ABT_JOINT_THUMB_LEFT", "K4ABT_JOINT_WRIST_LEFT"));
        listeLiaisons.add(new Pair<>("K4ABT_JOINT_CLAVICLE_RIGHT", "K4ABT_JOINT_SPINE_CHEST"));
        listeLiaisons.add(new Pair<>("K4ABT_JOINT_SHOULDER_RIGHT", "K4ABT_JOINT_CLAVICLE_RIGHT"));
        listeLiaisons.add(new Pair<>("K4ABT_JOINT_ELBOW_RIGHT", "K4ABT_JOINT_SHOULDER_RIGHT"));
        listeLiaisons.add(new Pair<>("K4ABT_JOINT_WRIST_RIGHT", "K4ABT_JOINT_ELBOW_RIGHT"));
        listeLiaisons.add(new Pair<>("K4ABT_JOINT_HAND_RIGHT", "K4ABT_JOINT_WRIST_RIGHT"));
        listeLiaisons.add(new Pair<>("K4ABT_JOINT_HANDTIP_RIGHT", "K4ABT_JOINT_HAND_RIGHT"));
        listeLiaisons.add(new Pair<>("K4ABT_JOINT_THUMB_RIGHT", "K4ABT_JOINT_WRIST_RIGHT"));
        listeLiaisons.add(new Pair<>("K4ABT_JOINT_HIP_LEFT", "K4ABT_JOINT_PELVIS"));
        listeLiaisons.add(new Pair<>("K4ABT_JOINT_KNEE_LEFT", "K4ABT_JOINT_HIP_LEFT"));
        listeLiaisons.add(new Pair<>("K4ABT_JOINT_ANKLE_LEFT", "K4ABT_JOINT_KNEE_LEFT"));
        listeLiaisons.add(new Pair<>("K4ABT_JOINT_FOOT_LEFT", "K4ABT_JOINT_ANKLE_LEFT"));
        listeLiaisons.add(new Pair<>("K4ABT_JOINT_HIP_RIGHT", "K4ABT_JOINT_PELVIS"));
        listeLiaisons.add(new Pair<>("K4ABT_JOINT_KNEE_RIGHT", "K4ABT_JOINT_HIP_RIGHT"));
        listeLiaisons.add(new Pair<>("K4ABT_JOINT_ANKLE_RIGHT", "K4ABT_JOINT_KNEE_RIGHT"));
        listeLiaisons.add(new Pair<>("K4ABT_JOINT_FOOT_RIGHT", "K4ABT_JOINT_ANKLE_RIGHT"));
        listeLiaisons.add(new Pair<>("K4ABT_JOINT_HEAD", "K4ABT_JOINT_NECK"));
    }

}
