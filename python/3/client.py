import socket
import sys
import time
import random
import struct
import math

server_address = (sys.argv[1],int(sys.argv[2]))
client = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
client.connect(server_address)
client.settimeout(1.0)

kitalalt=False
max=50
mostani=50
nagyobb=False
kisebb=False
first=True
last=""
packer = struct.Struct('1s I')

#client.sendall(" ".encode())
elozo=0
while not kitalalt:
    try:
        data2 = client.recv(200)
        unpacked_data = packer.unpack(data2)
        data=unpacked_data[0].decode()
        if not data:
            print("Server down")
            sys.exit()
        else:
            print(data)
            if(data=="Y" or data=="V" or data=="K"):
              kitalalt=True
              client.close()
              break
            elif(data=="I"):
                if(last=="<"):
                    kisebb=True;
                    nagyobb=False;
                else:
                    kisebb=False;
                    nagyobb=True;
            elif(data=="N"):
                if(last=="<"):
                    kisebb=False;
                    nagyobb=True;
                else:
                    kisebb=True;
                    nagyobb=False;
            sys.stdout.flush()
            
    except SystemExit as m:
        client.close()
        break
    except socket.timeout:
        pass
    except socket.error as e:
        print("hiba",e)
        break
    try:         
        if(first):
            msg=('<'.encode(),int(mostani))
            last="<"
            first=False  
        elif(nagyobb):
            mostani+=math.ceil(max/2)
            max=math.ceil(max/2)
            
            if((elozo)==(mostani-1)):    
                msg=('='.encode(),int(mostani))
            else:   
                msg=('<'.encode(),int(mostani))
                last="<"
                elozo=mostani
        elif(kisebb): 
            mostani-=math.ceil(max/2)
            max=math.ceil(max/2)
            if((elozo)==(mostani-1)):
                msg=('='.encode(),int(mostani))
            else:
                msg=('>'.encode(),int(mostani))
                last=">"
                elozo=mostani
        time.sleep(random.randint(1,5))
        packed_data = packer.pack(*msg)
        client.sendall(packed_data)  
    except socket.timeout:
        pass
    except socket.error as e:
        print(e)
        break
