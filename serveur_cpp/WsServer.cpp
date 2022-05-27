#include <stdio.h>
#include <stdlib.h>
#include <cstring>
#include <k4a/k4a.h>
#include <k4abt.h>
#include <jsoncpp/json/json.h>
#include <iostream>
#include <fstream>
#include <iomanip>
#include <algorithm>
#include <functional>
#include <uWebSockets/App.h>
#include <string>
#include <unistd.h>


using namespace std;


#define VERIFY(result, error)                                                                            \
    if(result != K4A_RESULT_SUCCEEDED)                                                                   \
    {                                                                                                    \
        printf("%s \n - (File: %s, Function: %s, Line: %d)\n", error, __FILE__, __FUNCTION__, __LINE__); \
        exit(1);                                                                                         \
    }                                                                                                    \



int main() {

/* ws->getUserData returns one of these */
 struct UserData {
 /* Define your user data */
 int something;
 };

uWS::App().get("/hello", [](auto *res, auto *req) {

 /* You can efficiently stream huge files too */
 res->writeHeader("Content-Type", "text/html; charset=utf-8")->end("Hello HTTP!");

}).ws<UserData>("/*", {

 /* Just a few of the available handlers */
 .open = [](auto *ws) {
 /* MQTT syntax */
 ws->subscribe("sensors/+/house");
 },
 .message = [](auto *ws, std::string_view message, uWS::OpCode opCode) {
 std::cout << message << '\n';
 if(message == "Demarrage"){

   ws->send("OK", opCode);
 }

std::basic_string_view<char> valeur = message.substr(5, message.size() - 5);
std::basic_string_view<char> jsonMessage = message.substr(0, 4);

if(jsonMessage == "JSON"){
   
   k4a_device_t device = NULL;
    VERIFY(k4a_device_open(0, &device), "Open K4A Device failed!");

    // Start camera. Make sure depth camera is enabled.
    k4a_device_configuration_t deviceConfig = K4A_DEVICE_CONFIG_INIT_DISABLE_ALL;
    deviceConfig.depth_mode = K4A_DEPTH_MODE_NFOV_UNBINNED;
    deviceConfig.color_resolution = K4A_COLOR_RESOLUTION_OFF;
    VERIFY(k4a_device_start_cameras(device, &deviceConfig), "Start K4A cameras failed!");

    k4a_calibration_t sensor_calibration;
    VERIFY(k4a_device_get_calibration(device, deviceConfig.depth_mode, deviceConfig.color_resolution, &sensor_calibration),
        "Get depth camera calibration failed!");

    k4abt_tracker_t tracker = NULL;
    k4abt_tracker_configuration_t tracker_config = K4ABT_TRACKER_CONFIG_DEFAULT;
    VERIFY(k4abt_tracker_create(&sensor_calibration, tracker_config, &tracker), "Body tracker initialization failed!");

    int frame_count = 0;

    Json::Value event; 
    Json::Value event2;

    Json::Value vec(Json::arrayValue);
    Json::Value vec2(Json::arrayValue);

    do
    {
        k4a_capture_t sensor_capture;
        k4a_wait_result_t get_capture_result = k4a_device_get_capture(device, &sensor_capture, K4A_WAIT_INFINITE);
        if (get_capture_result == K4A_WAIT_RESULT_SUCCEEDED)
        {
            frame_count++;
            k4a_wait_result_t queue_capture_result = k4abt_tracker_enqueue_capture(tracker, sensor_capture, K4A_WAIT_INFINITE);
            k4a_capture_release(sensor_capture); // Remember to release the sensor capture once you finish using it
            if (queue_capture_result == K4A_WAIT_RESULT_TIMEOUT)
            {
                // It should never hit timeout when K4A_WAIT_INFINITE is set.
                printf("Error! Add capture to tracker process queue timeout!\n");
                break;
            }
            else if (queue_capture_result == K4A_WAIT_RESULT_FAILED)
            {
                printf("Error! Add capture to tracker process queue failed!\n");
                break;
            }

            k4abt_frame_t body_frame = NULL;

            const char myNumbers[32][50] = {"BASSIN","COLONNE_NOMBRIL","COLONNE_THORAX","COU","CLAVICULE_GAUCHE","EPAULE_GAUCHE","COUDE_GAUCHE","POIGNET_GAUCHE","MAIN_GAUCHE","BOUTMAIN_GAUCHE","POUCE_GAUCHE","CLAVICULE_DROITE","EPAULE_DROITE","COUDE_DROIT","POIGNET_DROIT","MAIN_DROITE","BOUTMAIN_DROITE","POUCE_DROIT","HANCHE_GAUCHE","GENOUX_GAUCHE","CHEVILLE_GAUCHE","PIED_GAUCHE","HANCHE_DROITE","GENOUX_DROIT","CHEVILLE_DROITE","PIED_DROIT","TETE","NEZ","OEIL_GAUCHE","OREILLE_GAUCHE","OEIL_DROIT","OREILLE_DROITE"};
        
            k4a_wait_result_t pop_frame_result = k4abt_tracker_pop_result(tracker, &body_frame, K4A_WAIT_INFINITE);
            if (pop_frame_result == K4A_WAIT_RESULT_SUCCEEDED)
            {
                // Successfully popped the body tracking result. Start your processing

                size_t num_bodies = k4abt_frame_get_num_bodies(body_frame);
                printf("frame %d \n", frame_count);


                event2["frameNumero"] = frame_count;
                

                for (size_t i = 0; i < num_bodies; i++)
                {
                    for(size_t i = 0; i < 32; i++){
                    k4abt_skeleton_t skeleton;
                    k4abt_frame_get_body_skeleton(body_frame, i, &skeleton);
                    //uint32_t id = k4abt_frame_get_body_id(body_frame, i);
                                    

                    event["nom"] = myNumbers[i];
                    event["w"] = skeleton.joints[i].orientation.wxyz.w;
                    event["wx"] = skeleton.joints[i].orientation.wxyz.x;
                    event["wy"] = skeleton.joints[i].orientation.wxyz.y;
                    event["wz"] = skeleton.joints[i].orientation.wxyz.z;
                    event["x"] = skeleton.joints[i].position.xyz.x;
                    event["y"] = skeleton.joints[i].position.xyz.y;
                    event["z"] = skeleton.joints[i].position.xyz.z;

                    vec2.append(event);

                    }
                }


                event2["jointures"]= vec2;


                //vec.append(event2);
                Json::FastWriter fastWriter;
                std::string output = fastWriter.write(event2);
                ws->send(output, opCode);
                vec2.clear();

              
                k4abt_frame_release(body_frame); // Remember to release the body frame once you finish using it
            }
            else if (pop_frame_result == K4A_WAIT_RESULT_TIMEOUT)
            {
                //  It should never hit timeout when K4A_WAIT_INFINITE is set.
                printf("Error! Pop body frame result timeout!\n");
                break;
            }
            else
            {
                printf("Pop body frame result failed!\n");
                break;
            }
        }
        else if (get_capture_result == K4A_WAIT_RESULT_TIMEOUT)
        {
            // It should never hit time out when K4A_WAIT_INFINITE is set.
            printf("Error! Get depth frame time out!\n");
            break;
        }
        else
        {
            printf("Get depth capture returned error: %d\n", get_capture_result);
            break;
        }
    } while (frame_count < atoi(valeur.data()));

    ws->send("Fin", opCode);

    printf("Finished body tracking processing!\n");

    k4abt_tracker_shutdown(tracker);
    k4abt_tracker_destroy(tracker);
    k4a_device_stop_cameras(device);
    k4a_device_close(device);
 }
//  if(message == "Stop"){
//    ws->send("Fin", opCode);
//  }
 }

}).listen(9001, [](auto *listenSocket) {

 if (listenSocket) {
 std::cout << "Listening on port " << 9001 << std::endl;
 }

}).run();

}
