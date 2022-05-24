CC = g++
FLAG = -flto -O3 -lpthread -Wpedantic -Wall -Wextra -Wsign-conversion -Wconversion -std=c++2a
LIB = -lz -lk4a -lk4abt -ljsoncpp
USOCKETS = -I /usr/local/include/uSockets /usr/local/include/uSockets/uSockets.a


all: WsServer.o 
	$(CC) -o WsServer $^ $(FLAG)  $(LIB) $(USOCKETS)

WsServer.o: WsServer.cpp
	$(CC) -c $(FLAG) $< -o $@ $(USOCKETS)

clean:
	rm -f *o