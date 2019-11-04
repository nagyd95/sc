import socket
import select
import queue
import sys
import random
import struct

def eldont(kapott_szam,merre,random_number):
  if(str(merre)=='<'):
    if(kapott_szam>random_number):
      return "I"
    else:
      return "N"
  elif(str(merre)==">"):
    if(kapott_szam<random_number):
      return "I"
    else:
        return "N"
  elif(str(merre)=="="):
    if(kapott_szam==random_number):
      return "I"
    else:
      return "N"

server_address = (sys.argv[1],int(sys.argv[2]))

server = socket.socket(socket.AF_INET,socket.SOCK_STREAM)
server.settimeout(1.0)
server.setsockopt(socket.SOL_SOCKET, socket.SO_REUSEADDR,1)

server.bind(server_address)

server.listen(5)

inputs = [server]
random_number=random.randint(0,100)
print(random_number) 
kitalalt=False
unpacker = struct.Struct('1s I')

while inputs:
    timeout = 2
    readable,writeable,exceptable = select.select(inputs,inputs,inputs,timeout)

    if not (readable or writeable or exceptable):
        continue
    
    for s in readable:
        try:
            if s is server:
                client,client_addr=s.accept()
                print("Kliens csatlakozott: ",client_addr)
                inputs.append(client)
                if(kitalalt):
                  msg=('V'.encode(),0)
                  packed=unpacker.pack(*msg)
                  s.sendall(packed)
            else:
                data = s.recv(200).strip()
                if data:
                  unpacked_data = unpacker.unpack(data)
                  kapott_szam=unpacked_data[1]
                  merre=unpacked_data[0].decode()
                  print(kapott_szam,merre)
                  if(kitalalt):
                    msg=('V'.encode(),0)
                    packed=unpacker.pack(*msg)
                    s.sendall(packed) 
                  else:
                    msg=""
                    valasz=eldont(kapott_szam,merre,random_number)
                    if(str(merre)=="=" and valasz=="I"):
                      print("kitalaltaaadd")
                      msg=('Y'.encode(),0)
                      kitalalt=True
                    elif(str(merre)=="=" and valasz=="N"):
                      msg=('K'.encode(),0)
                      
                    elif(valasz=='I'):
                      msg=('I'.encode(),0)
                      
                    elif(valasz=="N"):
                      msg=("N".encode(),0)

                    packed=unpacker.pack(*msg)
                    s.sendall(packed)   
                else:
                    print("Kliens kilepett")
                    inputs.remove(s)
                    if s in writeable:
                        writeable.remove(s)
                    s.close()
        except socket.error as m:
            print("hiba",m)
            inputs.remove(s)
            if s in writeable:
                writeable.remove(s)
            s.close()

